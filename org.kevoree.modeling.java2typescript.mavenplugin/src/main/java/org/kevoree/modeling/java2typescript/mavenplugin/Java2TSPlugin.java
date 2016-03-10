package org.kevoree.modeling.java2typescript.mavenplugin;

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

import java.io.File;

@Mojo(name = "java2ts", defaultPhase = LifecyclePhase.PROCESS_SOURCES, requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
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

        for (Artifact a : project.getDependencyArtifacts()) {
            File file = a.getFile();
            if (file != null) {
                sourceTranslator.getAnalyzer().addClasspath(file.getAbsolutePath());
                getLog().info(file.getAbsolutePath() + " added to Java2TS analyzer");
            }
        }

        sourceTranslator.process();
    }
}