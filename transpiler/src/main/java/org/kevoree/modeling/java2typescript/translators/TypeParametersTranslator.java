package org.kevoree.modeling.java2typescript.translators;

import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiTypeParameter;
import org.kevoree.modeling.java2typescript.context.TranslationContext;
import org.kevoree.modeling.java2typescript.helper.KeywordHelper;
import org.kevoree.modeling.java2typescript.helper.TypeHelper;

/**
 *
 * Created by leiko on 23/11/15.
 */
public class TypeParametersTranslator {

    public static String print(PsiTypeParameter[] parameters, TranslationContext ctx) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            PsiTypeParameter p = parameters[i];
            builder.append(p.getName());
            PsiClassType[] extensions = p.getExtendsList().getReferencedTypes();
            if (extensions.length > 0) {
                builder.append(" extends ");
                for (PsiClassType ext : extensions) {
                    builder.append(TypeHelper.printType(ext, ctx));
                }
            }
            if (i != parameters.length - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }
}
