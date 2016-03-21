package org.kevoree.modeling.java2typescript;

import com.google.common.collect.Lists;

import java.nio.file.Paths;

public class SourceTranslatorTest {

    public static void main(String[] args) {
        String source = Paths.get("transpiler", "src", "test", "resources").toAbsolutePath().toString();
        String source2 = Paths.get("test-mavenplugin", "src", "main", "java").toAbsolutePath().toString();
        String target = Paths.get("transpiler", "target", "generated-sources", "java2ts").toAbsolutePath().toString();
        SourceTranslator translator = new SourceTranslator(Lists.newArrayList(source, source2), target, "foo");
        translator.process();
        translator.generate();
    }
}