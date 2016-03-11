package org.kevoree.modeling.java2typescript.helper;

import com.intellij.psi.PsiElement;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.javadoc.PsiDocTag;
import org.kevoree.modeling.java2typescript.metas.DocMeta;
import org.kevoree.modeling.java2typescript.translators.DocTagTranslator;

import java.util.Arrays;

/**
 *
 * Created by leiko on 23/11/15.
 */
public class DocHelper {

    public static DocMeta process(PsiDocComment comment) {
        DocMeta metas = new DocMeta();
        if (comment != null) {
            PsiDocTag[] tags = comment.getTags();
            for (PsiDocTag tag : tags) {
                if (tag.getName().equals(DocTagTranslator.NATIVE) && tag.getValueElement()!=null && tag.getValueElement().getText().equals(DocTagTranslator.TS)) {
                    metas.nativeActivated = true;
                }
                if (tag.getName().equals(DocTagTranslator.IGNORE) && tag.getValueElement()!=null && tag.getValueElement().getText().equals(DocTagTranslator.TS)) {
                    metas.ignored = true;
                }
                if (tag.getName().equals(DocTagTranslator.OPTIONAL)) {
                    for (PsiElement elem : tag.getDataElements()) {
                        if (!elem.getText().contains(" ")) {
                            metas.optional.add(elem.getText());
                        }
                    }
                }
            }
        }
        return metas;
    }
}
