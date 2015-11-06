package org.kevoree.modeling.java2typescript.mavenplugin;

import de.flapdoodle.embed.nodejs.*;
import de.flapdoodle.embed.process.config.IRuntimeConfig;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;

public class TSCRunner {

    public static void run(File src, File target, File[] libraries, boolean copyLibDTs, String moduleType) throws Exception {
        IRuntimeConfig runtimeConfig = (new NodejsRuntimeConfigBuilder()).defaults().build();
        NodejsProcess node = null;
        ArrayList<String> paramsCol = new ArrayList<String>();
        File[] files = src.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().endsWith(".ts")) {
                paramsCol.add(files[i].getAbsolutePath());
            }
        }
        if (libraries != null) {
            for (int i = 0; i < libraries.length; i++) {
                File[] lib = libraries[i].listFiles();
                for (int j = 0; j < lib.length; j++) {
                    if (lib[j].getName().endsWith(".ts")) {
                        paramsCol.add(lib[j].getAbsolutePath());
                    }
                }
            }
        }

        File targetTSCBIN = new File(target, "tsc.js");

        Files.copy(TSCRunner.class.getClassLoader().getResourceAsStream("tsc.js"), targetTSCBIN.toPath(), StandardCopyOption.REPLACE_EXISTING);

        File targetLIBD = null;
        if (copyLibDTs) {
            targetLIBD = new File(target, "lib.d.ts");
            Files.copy(TSCRunner.class.getClassLoader().getResourceAsStream("lib.d.ts"), targetLIBD.toPath(), StandardCopyOption.REPLACE_EXISTING);
            boolean founded = false;
            for (String alreadyAdded : paramsCol) {
                if (alreadyAdded.endsWith("lib.d.ts")) {
                    founded = true;
                }
            }
            if (!founded) {
                paramsCol.add(targetLIBD.getAbsolutePath());
            }
        }

        paramsCol.add("--outDir");
        paramsCol.add(target.getAbsolutePath());
        paramsCol.add("-d");
        if (moduleType != null) {
            paramsCol.add("--module");
            paramsCol.add(moduleType);
        }

        NodejsConfig nodejsConfig = new NodejsConfig(NodejsVersion.Main.V0_10, targetTSCBIN.getAbsolutePath(), paramsCol, target.getAbsolutePath());
        NodejsStarter runtime = new NodejsStarter(runtimeConfig);
        try {
            NodejsExecutable e = runtime.prepare(nodejsConfig);
            node = e.start();
            int retVal = node.waitFor();
            if (retVal != 0) {
                throw new Exception("There were TypeScript compilation errors. "/*+ Arrays.toString(paramsCol.toArray())*/);
            }
        } catch (InterruptedException var11) {
            var11.printStackTrace();
        } finally {
            if (targetTSCBIN != null) {
                targetTSCBIN.delete();
            }
            if (targetLIBD != null) {
                targetLIBD.delete();
            }
            if (node != null) {
                node.stop();
            }
        }
    }

}
