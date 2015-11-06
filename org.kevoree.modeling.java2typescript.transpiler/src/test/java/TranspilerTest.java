import org.kevoree.modeling.java2typescript.SourceTranslator;

import java.io.IOException;

/**
 * Created by gnain on 04/09/15.
 */
public class TranspilerTest {

    //private static final String baseDir = "/Users/gnain/Sources/Kevoree-Modeling/java2typescript/org.kevoree.modeling.java2typescript.transpiler/src/test/resources";
    private static final String baseDir = "/Users/gnain/Sources/Kevoree-Modeling/framework/org.kevoree.modeling.microframework/src/main/java";

    public static void main(String[] args) throws IOException {
        SourceTranslator sourceTranslator = new SourceTranslator();
        sourceTranslator.getAnalyzer().addClasspath("/Users/gnain/.m2/repository/org/kevoree/modeling/org.kevoree.modeling.microframework/4.25.1-SNAPSHOT/org.kevoree.modeling.microframework-4.25.1-SNAPSHOT.jar");
        sourceTranslator.getAnalyzer().addClasspath("/Users/gnain/.m2/repository/junit/junit/4.11/junit-4.11.jar");
        sourceTranslator.translateSources(baseDir, "target", "out", false, false, false, null);
    }



}
