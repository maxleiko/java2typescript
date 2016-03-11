package org.kevoree.modeling.java2typescript.translators.statement;

import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiClassReferenceType;
import org.kevoree.modeling.java2typescript.TranslationContext;
import org.kevoree.modeling.java2typescript.helper.ImportHelper;
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

        ctx.append(element.getName());

        if (element.hasInitializer() && element.getInitializer() != null && element.getInitializer().getType() != null) {
            if (!element.getType().toString().equals(element.getInitializer().getType().toString())) {
                ctx.append(": ");
//                ImportHelper.importIfValid(((PsiClassReferenceType) element.getType()).resolve(), ctx);
                ctx.append(TypeHelper.printType(element.getType(), ctx));
            }
            ctx.append(" = ");
            ExpressionTranslator.translate(element.getInitializer(), ctx);
        }

        boolean listDecl = false;
        PsiElement next = element.getNextSibling();
        while(next instanceof PsiWhiteSpace) {
            next = next.getNextSibling();
        }
        if(next instanceof PsiJavaToken) {
            listDecl = true;
        }

        if (!loopDeclaration && !listDecl) {
            ctx.append(";");
            ctx.append("\n");
        }
    }
}
