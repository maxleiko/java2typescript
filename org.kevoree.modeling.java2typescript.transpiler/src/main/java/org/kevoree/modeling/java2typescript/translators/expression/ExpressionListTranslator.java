package org.kevoree.modeling.java2typescript.translators.expression;

import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiExpressionList;
import org.kevoree.modeling.java2typescript.TranslationContext;

/**
 *
 * Created by leiko on 23/11/15.
 */
public class ExpressionListTranslator {

    public static void translate(PsiExpressionList element, TranslationContext ctx) {
        PsiExpression[] arguments = element.getExpressions();
        for (int i = 0; i < arguments.length; i++) {
            ExpressionTranslator.translate(arguments[i], ctx);
            if (i != arguments.length - 1) {
                ctx.append(", ");
            }
        }
    }
}
