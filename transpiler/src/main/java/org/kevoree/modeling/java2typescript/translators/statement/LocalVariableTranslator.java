package org.kevoree.modeling.java2typescript.translators.statement;

import com.intellij.psi.*;
import org.kevoree.modeling.java2typescript.context.TranslationContext;
import org.kevoree.modeling.java2typescript.helper.KeywordHelper;
import org.kevoree.modeling.java2typescript.helper.TypeHelper;
import org.kevoree.modeling.java2typescript.translators.expression.ExpressionTranslator;

public class LocalVariableTranslator {

    public static void translate(PsiLocalVariable element, TranslationContext ctx) {
        PsiElement parent = element.getParent();
        boolean loopDeclaration = false;

        if (parent instanceof PsiDeclarationStatement) {
            parent = parent.getParent();
            if (parent instanceof PsiLoopStatement) {
                loopDeclaration = true;
            }
        }
        if (element.getPrevSibling() == null) {
            if (loopDeclaration) {
                ctx.append("var ");
            } else {
                ctx.print("var ");
            }
        }

        ctx.append(KeywordHelper.process(element.getName(), ctx));

        // explicit local variable type
        ctx.append(": ");
        ctx.append(TypeHelper.printType(element.getType(), ctx));

        if (element.hasInitializer() && element.getInitializer() != null) {
            ctx.append(" = ");
            ExpressionTranslator.translate(element.getInitializer(), ctx);
            if (element.getType().getPresentableText().equals("byte") &&
                    element.getInitializer().getType() != null &&
                    element.getInitializer().getType().getPresentableText().equals("char")) {
                ctx.append(".charCodeAt(0)");
            }
        }

        boolean listDecl = false;
        PsiElement next = element.getNextSibling();
        while (next instanceof PsiWhiteSpace) {
            next = next.getNextSibling();
        }
        if (next instanceof PsiJavaToken) {
            listDecl = true;
        }

        if (!loopDeclaration && !listDecl) {
            ctx.append(";");
            ctx.append("\n");
        }
    }
}
