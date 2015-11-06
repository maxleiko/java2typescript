package org.kevoree.modeling.java2typescript.mavenplugin;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import java.io.*;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Mojo(name = "tsc", defaultPhase = LifecyclePhase.COMPILE, requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class TSCompilePlugin extends AbstractMojo {

    /**
     * The maven project.
     */
    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Parameter(defaultValue = "${project.build.directory}/classes")
    protected File outputClasses;

    @Parameter(defaultValue = "${project.basedir}/src/main/resources")
    protected File inputTS;

    @Parameter(defaultValue = "${project.build.directory}/generated-sources/tsc")
    protected File outputTempJS;

    @Parameter(defaultValue = "${project.build.directory}/jsdeps")
    protected File jsdeps;

    @Parameter
    private File[] libraries;

    @Parameter
    private String moduleType = null;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (!outputClasses.exists()) {
            outputClasses.mkdirs();
        }
        if (!outputTempJS.exists()) {
            outputTempJS.mkdirs();
        }
        if (!jsdeps.exists()) {
            jsdeps.mkdirs();
        }
        try {
            if (inputTS.exists()) {
                Collection<File> tsfiles = FileUtils.listFiles(inputTS, new String[]{"ts"}, true);
                for (File f : tsfiles) {
                    File outPut = new File(outputTempJS, f.getName());
                    List<String> lines = FileUtils.readLines(f, "UTF-8");
                    FileWriter writer = new FileWriter(outPut);
                    for (String line : lines) {
                        if (!line.contains("///<reference path=")) {
                            writer.write(line);
                            writer.write("\n");
                        }
                    }
                    writer.flush();
                    writer.close();
                    String basePath = f.getAbsolutePath().replace(inputTS.getAbsolutePath(), "");
                    if (basePath.startsWith(File.separator)) {
                        basePath = basePath.substring(1);
                    }
                    File alreadyTs = new File(outputClasses, basePath);
                    if (alreadyTs.exists()) {
                        alreadyTs.delete();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Artifact artifact : project.getArtifacts()) {
            File target = artifact.getFile();
            if (target.exists() && target.getName().endsWith(".jar")) {
                unzipArchive(target, jsdeps);
            }
        }

        File[] libs;
        if (libraries == null) {
            libs = new File[1];
            libs[0] = jsdeps;
        } else {
            libs = new File[libraries.length + 1];
            for (int i = 0; i < libraries.length; i++) {
                libs[i] = libraries[i];
            }
            libs[libraries.length] = jsdeps;
        }
        try {
            TSCRunner.run(outputTempJS, outputClasses, libs, true, moduleType);
        } catch (Exception e) {
            throw new MojoExecutionException("TypeScript compilation failed !", e);
        }

        File packageJSON = new File(outputClasses, "package.json");
        if (packageJSON.exists()) {
            try {
                updateCONTENT(packageJSON, project);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            packageJSON = new File(inputTS, "package.json");
            if (packageJSON.exists()) {
                try {
                    updateCONTENT(packageJSON, project);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void unzipArchive(File archive, File outputDir) {
        try {
            ZipFile zipfile = new ZipFile(archive);
            for (Enumeration e = zipfile.entries(); e.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) e.nextElement();
                if (entry.getName().endsWith(".js") || entry.getName().endsWith(".d.ts")) {
                    if (!entry.getName().equals("tsc.js") && !entry.getName().equals("lib.d.ts")) {
                        if (
                                entry.getName().equals("java.ts") ||
                                        entry.getName().equals("java.d.ts") ||
                                        entry.getName().equals("java.js") ||
                                        entry.getName().equals("junit.d.ts") ||
                                        entry.getName().equals("junit.ts") ||
                                        entry.getName().equals("junit.js")) {

                        } else {
                            unzipEntry(zipfile, entry, outputDir);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void unzipEntry(ZipFile zipfile, ZipEntry entry, File outputDir) throws IOException {
        if (entry.isDirectory()) {
            createDir(new File(outputDir, entry.getName()));
            return;
        }
        File outputFile = new File(outputDir, entry.getName());
        if (!outputFile.getParentFile().exists()) {
            createDir(outputFile.getParentFile());
        }
        BufferedInputStream inputStream = new BufferedInputStream(zipfile.getInputStream(entry));
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
        try {
            IOUtils.copy(inputStream, outputStream);
        } finally {
            outputStream.close();
            inputStream.close();
        }
    }

    private void createDir(File dir) {
        if (!dir.mkdirs()) throw new RuntimeException("Can not create dir " + dir);
    }


    public static void updateCONTENT(File descriptor, MavenProject project)
            throws IOException {

        FileInputStream inputStream = new FileInputStream(descriptor);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        String content = new String(baos.toByteArray());
        content = content.replace("VERSION_TO_REPLACE", project.getVersion()).replace("NAME_TO_REPLACE", project.getArtifactId());
        ;

        FileWriter writer = new FileWriter(descriptor);
        writer.write(content);
        writer.flush();
        writer.close();

    }

}
