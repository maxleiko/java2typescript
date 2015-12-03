package org.kevoree.modeling.java2typescript.helper;

import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.javadoc.PsiDocTag;
import org.kevoree.modeling.java2typescript.metas.DocMeta;
import org.kevoree.modeling.java2typescript.translators.NativeTsTranslator;

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
                if (tag.getName().equals(NativeTsTranslator.TAG) && tag.getValueElement()!=null && tag.getValueElement().getText().equals(NativeTsTranslator.TAG_VAL_TS)) {
                    metas.nativeActivated = true;
                }
                if (tag.getName().equals(NativeTsTranslator.TAG_IGNORE) && tag.getValueElement()!=null && tag.getValueElement().getText().equals(NativeTsTranslator.TAG_VAL_TS)) {
                    metas.ignored = true;
                }
            }
        }
        return metas;
    }
}
