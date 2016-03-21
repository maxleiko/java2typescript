package org.kevoree.modeling.java2typescript.mavenplugin;

import com.google.common.collect.Lists;
import de.flapdoodle.embed.nodejs.*;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Mojo(name = "tsc", defaultPhase = LifecyclePhase.COMPILE, requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class TscPlugin extends AbstractMojo {

    private static final String TSC = "tsc.js";
    private static final String TS_LIB = "lib.d.ts";

    @Parameter(defaultValue = "${project.build.directory}/generated-sources/java2ts")
    protected File source;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        NodejsStarter runtime = new NodejsStarter(new NodejsRuntimeConfigBuilder().defaults().build());
        NodejsProcess nodeProcess = null;
        Path tmpDir = null;
        File tscFile, tsLibFile;

        try {
            tmpDir = Files.createTempDirectory("tsc-compiler");
            tscFile = new File(tmpDir.toFile(), "tsc.js");
            tsLibFile = new File(tmpDir.toFile(), "lib.d.ts");
            InputStream tscIs = TscPlugin.class.getClassLoader().getResourceAsStream(TSC);
            InputStream tsLibIs = TscPlugin.class.getClassLoader().getResourceAsStream(TS_LIB);
            if (tscIs != null) {
                if (tsLibIs != null) {
                    Files.copy(tscIs, tscFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    Files.copy(tsLibIs, tsLibFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    NodejsConfig nodejsConfig = new NodejsConfig(NodejsVersion.Main.V0_10, tscFile.getAbsolutePath(), Lists.newArrayList(), source.getAbsolutePath());
                    NodejsExecutable e = runtime.prepare(nodejsConfig);
                    nodeProcess = e.start();
                    int retVal = nodeProcess.waitFor();
                    if (retVal != 0) {
                        throw new Exception("Unable to compile generated TypeScript from "+source.getPath());
                    }
                } else {
                    throw new Exception("Unable to find "+TS_LIB);
                }
            } else {
                throw new Exception("Unable to find "+TSC);
            }
        } catch (Exception e) {
            getLog().error(e);
        } finally {
            if (tmpDir != null) {
                tmpDir.toFile().delete();
            }
            if (nodeProcess != null) {
                nodeProcess.stop();
            }
        }
    }
}
