import org.kevoree.modeling.java2typescript.SourceTranslator2;

import java.io.IOException;

/**
 * Created by gnain on 04/09/15.
 */
public class TranspilerTest {

    public static void main(String[] args) throws IOException {
        String srcPath = "/home/leiko/dev/kevoree/java2ts-test/src/main/java";
        String outPath = "target/out";
        SourceTranslator2 translator = new SourceTranslator2("model", srcPath, outPath);
        translator.visit();
    }
}