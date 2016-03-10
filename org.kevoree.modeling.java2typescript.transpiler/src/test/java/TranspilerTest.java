import org.junit.Test;
import org.kevoree.modeling.java2typescript.SourceTranslator;

import java.io.IOException;
import java.nio.file.Paths;

/**
 *
 * Created by gnain on 04/09/15.
 */
public class TranspilerTest {

    public static void main(String[] args) {
        String srcPath = Paths.get("org.kevoree.modeling.java2typescript.transpiler", "src", "test", "resources").toAbsolutePath().toString();
        String outPath = Paths.get("org.kevoree.modeling.java2typescript.transpiler", "target", "generated-sources", "java2ts").toString();
        SourceTranslator translator = new SourceTranslator(srcPath, outPath, "my-lib");
        System.out.println("Transpiling...");
        translator.process();
//        translator.genExportAllFile("api");
        System.out.println("Done");
    }
}