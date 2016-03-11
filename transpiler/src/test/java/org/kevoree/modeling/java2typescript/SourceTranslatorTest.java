package org.kevoree.modeling.java2typescript;

import java.nio.file.Paths;

public class SourceTranslatorTest {

    public static void main(String[] args) {
        String source = Paths.get("transpiler", "src", "test", "resources").toAbsolutePath().toString();
        String target = Paths.get("transpiler", "target", "generated-sources", "java2ts").toAbsolutePath().toString();
        String name = "test";
        SourceTranslator translator = new SourceTranslator(source, target, name);
        translator.process();
    }
}