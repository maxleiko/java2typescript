import org.kevoree.modeling.java2typescript.SourceTranslator2;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by gnain on 04/09/15.
 */
public class TranspilerTest {

    public static void main(String[] args) throws IOException {
        String srcPath = Paths.get("src", "test" , "resources").toAbsolutePath().toString();
        String outPath = Paths.get("target", "out").toString();
        System.out.println(srcPath);
        System.out.println(outPath);
        SourceTranslator2 translator = new SourceTranslator2("model", srcPath, outPath);
        translator.visit();
    }
}