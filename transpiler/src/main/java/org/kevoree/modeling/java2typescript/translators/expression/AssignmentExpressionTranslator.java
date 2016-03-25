package org.kevoree.modeling.java2typescript.translators.expression;

import com.intellij.psi.PsiAssignmentExpression;
import org.kevoree.modeling.java2typescript.context.TranslationContext;
import org.kevoree.modeling.java2typescript.translators.JavaTokenTranslator;

public class AssignmentExpressionTranslator {

    public static void translate(PsiAssignmentExpression element, TranslationContext ctx) {
        ExpressionTranslator.translate(element.getLExpression(), ctx);
        ctx.append(' ');
        JavaTokenTranslator.translate(element.getOperationSign(), ctx);
        ctx.append(' ');
        ExpressionTranslator.translate(element.getRExpression(), ctx);

        if (element.getLExpression().getType() != null &&
                element.getRExpression() != null &&
                element.getRExpression().getType() != null &&
                element.getLExpression().getType().getPresentableText().equals("byte") &&
                element.getRExpression().getType().getPresentableText().equals("char")) {
            ctx.append(".charCodeAt(0)");
        }
    }

}
