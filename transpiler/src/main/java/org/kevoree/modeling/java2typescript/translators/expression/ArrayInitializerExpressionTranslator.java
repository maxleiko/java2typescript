package org.kevoree.modeling.java2typescript.translators.expression;

import com.intellij.psi.PsiArrayInitializerExpression;
import com.intellij.psi.PsiExpression;
import org.kevoree.modeling.java2typescript.TranslationContext;

public class ArrayInitializerExpressionTranslator {

    public static void translate(PsiArrayInitializerExpression element, TranslationContext ctx) {
        boolean hasToBeClosed;
        if (ctx.NATIVE_ARRAY && element.getType() != null && element.getType().equalsToText("int[]")) {
            ctx.append("new Int32Array([");
            hasToBeClosed = true;
        } else if (ctx.NATIVE_ARRAY && element.getType() != null && element.getType().equalsToText("double[]")) {
            ctx.append("new Float64Array([");
            hasToBeClosed = true;
        } else if (ctx.NATIVE_ARRAY && element.getType() != null && element.getType().equalsToText("long[]")) {
            ctx.append("new Float64Array([");
            hasToBeClosed = true;
        } else {
            ctx.append("[");
            hasToBeClosed = false;
        }
        PsiExpression[] initializers = element.getInitializers();
        if (initializers.length > 0) {
            for (int i = 0; i < initializers.length; i++) {
                ExpressionTranslator.translate(initializers[i], ctx);
                if (i != initializers.length - 1) {
                    ctx.append(", ");
                }
            }
        }
        ctx.append("]");
        if (hasToBeClosed) {
            ctx.append(")");
        }
    }
}
