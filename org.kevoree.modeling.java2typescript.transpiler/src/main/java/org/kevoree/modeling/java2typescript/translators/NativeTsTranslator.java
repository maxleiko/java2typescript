package org.kevoree.modeling.java2typescript.translators;

import com.intellij.psi.javadoc.*;
import org.kevoree.modeling.java2typescript.TranslationContext;

/**
 * Created by gregory.nain on 08/01/15.
 */

public class NativeTsTranslator {

    public static final String TAG = "native";

    public static final String TAG_IGNORE = "ignore";

    public static final String TAG_VAL_TS = "ts";

    public static final String TAG_VAL_TS_CALLBACK = "ts_callback";

    public static void translate(PsiDocComment comment, TranslationContext ctx) {
        PsiDocTag[] tags = comment.getTags();
        for (PsiDocTag tag : tags) {
            if (tag.getName().equals(NativeTsTranslator.TAG) && tag.getValueElement()!=null && tag.getValueElement().getText().equals(NativeTsTranslator.TAG_VAL_TS)) {
                String value = tag.getText();
                String[] lines = value.split("\n");
                for (String line : lines) {
                    String trimmedLine = line.trim();
                    if (trimmedLine.length() > 0 && !trimmedLine.contains("@"+TAG) && !trimmedLine.contains("@"+TAG_IGNORE)) {
                        if(trimmedLine.charAt(0) == '*'){
                            trimmedLine = trimmedLine.substring(1);
                        }
                        ctx.print(trimmedLine).append("\n");
                    }
                }
            }
        }
    }
}
