import org.kevoree.modeling.java2typescript.SourceTranslator;

import java.io.IOException;
import java.nio.file.Paths;

/**
 *
 * Created by gnain on 04/09/15.
 */
public class TranspilerTest {

    public static void main(String[] args) throws IOException {
        String srcPath = Paths.get("src", "test", "resources").toAbsolutePath().toString();
        String outPath = Paths.get("target", "out", "ts").toString();
        SourceTranslator translator = new SourceTranslator(srcPath, outPath);
        System.out.println("Transpiling...");
        translator.process();
        translator.genExportAllFile("api");
        System.out.println("Done");
    }
}