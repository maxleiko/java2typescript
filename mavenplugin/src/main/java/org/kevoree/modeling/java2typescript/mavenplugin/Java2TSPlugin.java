package org.kevoree.modeling.java2typescript.mavenplugin;

import com.google.common.collect.Lists;
import org.apache.commons.io.FilenameUtils;
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
import org.kevoree.modeling.java2typescript.SourceTranslator;
import org.kevoree.modeling.java2typescript.json.tsconfig.TsConfig;

import java.io.*;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Mojo(name = "java2ts", defaultPhase = LifecyclePhase.COMPILE, requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class Java2TSPlugin extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Parameter(defaultValue = "${project.basedir}/src/main/java")
    protected File source;

    @Parameter(defaultValue = "${project.build.directory}/generated-sources/java2ts")
    protected File target;

    @Parameter(defaultValue = "${project.artifactId}")
    private String name;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        SourceTranslator sourceTranslator = new SourceTranslator(source.getPath(), target.getPath(), name);
        TsConfig tsConfig = sourceTranslator.getTsConfig();

        for (Artifact a : project.getDependencyArtifacts()) {
            File file = a.getFile();
            if (file != null) {
                if (file.isFile()) {
                    sourceTranslator.getAnalyzer().addClasspath(file.getAbsolutePath());
                    getLog().info(file.getAbsolutePath() + " added to Java2TS analyzer");
                }
            }
        }

        sourceTranslator.process();

//        for (Artifact a : project.getDependencyArtifacts()) {
//            File file = a.getFile();
//            if (file != null) {
//                if (file.isFile()) {
//                    String folderName = a.getGroupId() + "_" + a.getArtifactId() + "_" + a.getVersion();
//                    unzipArchive(tsConfig, file, Paths.get(target.toString(), "src", "deps", folderName).toFile());
//                }
//            }
//        }

        sourceTranslator.generate();
    }

    private void unzipArchive(TsConfig tsConfig, File archive, File outputDir) {
        try {
            ZipFile zipfile = new ZipFile(archive);
            List<URI> files = new ArrayList<>();
            for (Enumeration e = zipfile.entries(); e.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) e.nextElement();
                if (!entry.isDirectory()) {
                    if (entry.getName().endsWith(".ts")) {
                        File tsFile = Paths.get(outputDir.toString(), entry.getName().substring(entry.getName().lastIndexOf(File.separator)+1)).toFile();
                        outputDir.mkdirs();
                        tsFile.createNewFile();
                        BufferedInputStream inputStream = new BufferedInputStream(zipfile.getInputStream(entry));
                        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(tsFile));
                        try {
                            IOUtils.copy(inputStream, outputStream);
                            files.add(URI.create(tsFile.getPath().substring(target.getPath().length()+1)));
                            getLog().info("Copied "+tsFile.getPath());
                        } finally {
                            outputStream.close();
                            inputStream.close();
                        }
                    }
                }
            }
            tsConfig.withFiles(files);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}