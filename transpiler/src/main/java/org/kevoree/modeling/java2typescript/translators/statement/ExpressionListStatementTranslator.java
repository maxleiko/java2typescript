
package org.kevoree.modeling.java2typescript.translators.statement;

import com.intellij.psi.*;
import org.kevoree.modeling.java2typescript.context.TranslationContext;
import org.kevoree.modeling.java2typescript.translators.expression.ExpressionTranslator;

public class ExpressionListStatementTranslator {

    public static void translate(PsiExpressionListStatement element, TranslationContext ctx) {

        PsiExpression[] expressions = element.getExpressionList().getExpressions();

        for (int i = 0; i < expressions.length; i++) {
            if (i > 0) {
                ctx.append(", ");
            }
            ExpressionTranslator.translate(expressions[i], ctx);
        }


    }

}
