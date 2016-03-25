package org.kevoree.modeling.java2typescript;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class SourceTranslatorTest {

    @Test
    public void generics() throws IOException {
        String source = Paths.get("src", "test", "java", "sources", "generics").toAbsolutePath().toString();
        String target = Paths.get("target", "generated-sources", "java2ts").toAbsolutePath().toString();

        SourceTranslator translator = new SourceTranslator(source, target, "generics");
        translator.process();
        Assert.assertEquals(
                FileUtils.readFileToString(Paths.get("src", "test", "resources", "generics", "output.ts").toFile()).trim(),
                translator.getCtx().toString().trim());
    }

    @Test
    public void strings() throws IOException {
        String source = Paths.get("src", "test", "java", "sources", "strings").toAbsolutePath().toString();
        String target = Paths.get("target", "generated-sources", "java2ts").toAbsolutePath().toString();

        SourceTranslator translator = new SourceTranslator(source, target, "strings");
        translator.addPackageTransform("sources.strings", "");
        translator.process();
        Assert.assertEquals(
                FileUtils.readFileToString(Paths.get("src", "test", "resources", "strings", "output.ts").toFile()).trim(),
                translator.getCtx().toString().trim());
    }
}