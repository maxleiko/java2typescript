
package org.kevoree.modeling.java2typescript.translators.expression;

import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiLambdaExpression;
import com.intellij.psi.PsiParameter;
import org.kevoree.modeling.java2typescript.TranslationContext;
import org.kevoree.modeling.java2typescript.translators.CodeBlockTranslator;

public class LambaExpressionTranslator {

    public static void translate(PsiLambdaExpression element, TranslationContext ctx) {
        ctx.append("(");
        PsiParameter[] paramList = element.getParameterList().getParameters();
        for (int i=0; i < paramList.length; i++) {
            PsiParameter param = paramList[i];
            ctx.append(param.getName());
            if (i < paramList.length - 1) {
                ctx.append(", ");
            }
        }
        ctx.append(") => {\n");
        ctx.increaseIdent();
        CodeBlockTranslator.translate((PsiCodeBlock) element.getBody(), ctx);
        ctx.decreaseIdent();
        ctx.print("}");
    }

}
