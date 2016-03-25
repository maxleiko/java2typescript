
package org.kevoree.modeling.java2typescript.translators.expression;

import com.intellij.psi.PsiTypeCastExpression;
import org.kevoree.modeling.java2typescript.context.TranslationContext;
import org.kevoree.modeling.java2typescript.helper.TypeHelper;

public class TypeCastExpressionTranslator {

    public static void translate(PsiTypeCastExpression element, TranslationContext ctx) {
        ctx.append('<').append(TypeHelper.printType(element.getType(), ctx, true, false)).append('>');
        ExpressionTranslator.translate(element.getOperand(), ctx);
    }

}
