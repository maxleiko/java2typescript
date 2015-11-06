package org.kevoree.modeling.java2typescript.mavenplugin;

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
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Mojo(name = "extract", defaultPhase = LifecyclePhase.PROCESS_SOURCES, requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class ExtractDependencies extends AbstractMojo {

    /**
     * The maven project.
     */
    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    /**
     * Source base directory
     */
    @Parameter(defaultValue = "${project.basedir}/src/main/resources")
    private File target;

    @Parameter
    private String[] excludes;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Extracting .js and .ts files from dependencies....");
        for (Artifact artifact : project.getArtifacts()) {
            File currentArte = artifact.getFile();
            if (currentArte.exists() && currentArte.getName().endsWith(".jar")) {
                unzipArchive(currentArte, target);
            }
        }
    }

    public void unzipArchive(File archive, File outputDir) {
        try {
            ZipFile zipfile = new ZipFile(archive);
            for (Enumeration e = zipfile.entries(); e.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) e.nextElement();

                boolean isExclude = false;
                if (excludes != null) {
                    for (int i = 0; i < excludes.length; i++) {
                        isExclude = isExclude || entry.getName().matches(excludes[i]);
                    }
                }
                if (!isExclude) {
                    getLog().info("Extracting " + entry.getName());
                    if (entry.getName().endsWith(".js") || entry.getName().endsWith(".d.ts")) {
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

}
