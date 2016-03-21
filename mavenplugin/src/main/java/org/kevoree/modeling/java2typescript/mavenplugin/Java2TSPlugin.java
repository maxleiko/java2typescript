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
import org.kevoree.modeling.java2typescript.json.packagejson.PackageJson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mojo(name = "java2ts", defaultPhase = LifecyclePhase.COMPILE, requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class Java2TSPlugin extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Parameter(defaultValue = "${project.basedir}/src/main/java")
    protected File source;

    @Parameter()
    protected List<File> sources = new ArrayList<>();

    @Parameter(defaultValue = "${project.build.directory}/generated-sources/java2ts")
    protected File target;

    @Parameter(defaultValue = "${project.artifactId}")
    private String name;

    @Parameter(defaultValue = "${project.artifactId}")
    private String packageName;

    @Parameter(defaultValue = "${project.version}")
    private String packageVersion;

    @Parameter()
    private List<Dependency> dependencies = new ArrayList<Dependency>();

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        List<String> sources = new ArrayList<>();
        if (sources.isEmpty()) {
            sources.add(source.getAbsolutePath());
        } else {
            sources.addAll(this.sources.stream().map(File::getAbsolutePath).collect(Collectors.toList()));
        }

        SourceTranslator sourceTranslator = new SourceTranslator(sources, target.getPath(), name);

        PackageJson pkgJson = sourceTranslator.getPkgJson();

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

        pkgJson.setName(packageName);
        pkgJson.setVersion(packageVersion);
        for (Dependency dep : dependencies) {
            pkgJson.addDependency(dep.getName(), dep.getVersion());
        }

        sourceTranslator.generate();
    }
}