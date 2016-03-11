package org.kevoree.modeling.java2typescript.translators;

import com.intellij.psi.javadoc.*;
import org.kevoree.modeling.java2typescript.TranslationContext;

/**
 * Created by gregory.nain on 08/01/15.
 */

public class DocTagTranslator {

    public static final String NATIVE = "native";

    public static final String IGNORE = "ignore";

    public static final String TS = "ts";

    public static final String TS_CALLBACK = "ts_callback";

    public static final String OPTIONAL = "optional";


    public static void translate(PsiDocComment comment, TranslationContext ctx) {
        PsiDocTag[] tags = comment.getTags();
        for (PsiDocTag tag : tags) {
            if (tag.getName().equals(NATIVE) && tag.getValueElement()!=null && tag.getValueElement().getText().equals(TS)) {
                String value = tag.getText();
                String[] lines = value.split("\n");
                for (String line : lines) {
                    String trimmedLine = line.trim();
                    if (trimmedLine.length() > 0 && !trimmedLine.contains("@"+ NATIVE) && !trimmedLine.contains("@"+ IGNORE)) {
                        if(trimmedLine.charAt(0) == '*'){
                            trimmedLine = trimmedLine.substring(1);
                        }
                        ctx.print(trimmedLine).append("\n");
                    }
                }
            } else if (tag.getName().equals(OPTIONAL) && tag.getValueElement() != null) {
                System.out.println("OPTIONAL> "+tag.getValueElement().getText());
            }
        }
    }
}
