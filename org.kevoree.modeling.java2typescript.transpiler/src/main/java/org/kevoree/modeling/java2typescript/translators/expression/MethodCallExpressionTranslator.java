
package org.kevoree.modeling.java2typescript.translators.expression;

import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiMethodCallExpression;
import org.kevoree.modeling.java2typescript.TranslationContext;

public class MethodCallExpressionTranslator {

    public static void translate(PsiMethodCallExpression element, TranslationContext ctx) {

        if(element.getMethodExpression().toString().contains("System.out")){
            ctx.append("console.log(");
            PsiExpression[] arguments = element.getArgumentList().getExpressions();
            for (int i = 0; i < arguments.length; i++) {
                ExpressionTranslator.translate(arguments[i], ctx);
                if (i != arguments.length - 1) {
                    ctx.append(", ");
                }
            }
            ctx.append(")");
        } else if(element.getMethodExpression().toString().contains("System.err")){
            ctx.append("console.error(");
            PsiExpression[] arguments = element.getArgumentList().getExpressions();
            for (int i = 0; i < arguments.length; i++) {
                ExpressionTranslator.translate(arguments[i], ctx);
                if (i != arguments.length - 1) {
                    ctx.append(", ");
                }
            }
            ctx.append(")");
        } else if(element.getMethodExpression().toString().contains("printStackTrace")){
            ctx.append("console.error(");
            ExpressionTranslator.translate(element.getMethodExpression().getQualifierExpression(), ctx);
            ctx.append("['stack'])");
        } else {
            ReferenceExpressionTranslator.translate(element.getMethodExpression(), ctx);
            if (!element.getMethodExpression().toString().endsWith(".length")) {
                ctx.append('(');
                PsiExpression[] arguments = element.getArgumentList().getExpressions();
                for (int i = 0; i < arguments.length; i++) {
                    ExpressionTranslator.translate(arguments[i], ctx);
                    if (i != arguments.length - 1) {
                        ctx.append(", ");
                    }
                }
                ctx.append(")");
            }
        }


    }

}
