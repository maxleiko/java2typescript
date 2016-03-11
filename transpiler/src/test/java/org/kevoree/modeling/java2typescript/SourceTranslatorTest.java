package org.kevoree.modeling.java2typescript;

import java.nio.file.Paths;

public class SourceTranslatorTest {

    public static void main(String[] args) {
        String source = Paths.get("transpiler", "src", "test", "resources").toAbsolutePath().toString();
        String target = Paths.get("transpiler", "target", "generated-sources", "java2ts").toAbsolutePath().toString();
        String name = "test";
        SourceTranslator translator = new SourceTranslator(source, target, name);
        translator.getAnalyzer().addClasspath("/home/leiko/.m2/repository/org/kevoree/modeling/microframework/4.27.0/microframework-4.27.0.jar");
        translator.process();
        translator.generate();
    }
}