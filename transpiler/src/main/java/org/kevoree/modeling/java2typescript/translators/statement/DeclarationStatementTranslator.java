package org.kevoree.modeling.java2typescript.translators.statement;

import com.intellij.psi.PsiDeclarationStatement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLocalVariable;
import com.intellij.psi.PsiStatement;
import org.kevoree.modeling.java2typescript.context.TranslationContext;

public class DeclarationStatementTranslator {

    public static void translate(PsiDeclarationStatement stmt, TranslationContext ctx) {
        for (int i = 0; i < stmt.getDeclaredElements().length; i++) {

            if(i > 0) {
                ctx.append(", ");
            }

            PsiElement element1 = stmt.getDeclaredElements()[i];
            if (element1 instanceof PsiStatement) {
                StatementTranslator.translate((PsiStatement) element1, ctx);
            } else if (element1 instanceof PsiLocalVariable) {
                LocalVariableTranslator.translate((PsiLocalVariable) element1, ctx);
            } else {
                System.err.println("Not managed " + element1);
            }
        }
    }

}
