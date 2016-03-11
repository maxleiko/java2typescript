package org.kevoree.modeling.java2typescript.translators;

import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiTypeParameter;
import org.kevoree.modeling.java2typescript.TranslationContext;
import org.kevoree.modeling.java2typescript.helper.TypeHelper;

/**
 *
 * Created by leiko on 23/11/15.
 */
public class TypeParametersTranslator {

    public static void translate(PsiTypeParameter[] parameters, TranslationContext ctx) {
        if (parameters.length > 0) {
            ctx.append('<');
            for (int i = 0; i < parameters.length; i++) {
                PsiTypeParameter p = parameters[i];
                ctx.append(p.getName());
                PsiClassType[] extentions = p.getExtendsList().getReferencedTypes();
                if (extentions.length > 0) {
                    ctx.append(" extends ");
                    for (PsiClassType ext : extentions) {
                        ctx.append(TypeHelper.printType(ext, ctx));
                    }
                }
                if (i != parameters.length - 1) {
                    ctx.append(", ");
                }
            }
            ctx.append('>');
        }
    }
}
