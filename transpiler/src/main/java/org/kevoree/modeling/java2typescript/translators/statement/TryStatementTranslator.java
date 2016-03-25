
package org.kevoree.modeling.java2typescript.translators.statement;

import com.intellij.psi.PsiCatchSection;
import com.intellij.psi.PsiTryStatement;
import org.kevoree.modeling.java2typescript.context.TranslationContext;
import org.kevoree.modeling.java2typescript.helper.KeywordHelper;
import org.kevoree.modeling.java2typescript.helper.TypeHelper;
import org.kevoree.modeling.java2typescript.translators.CodeBlockTranslator;

public class TryStatementTranslator {

    private static final String EXCEPTION_VAR = "$ex$";

    public static void translate(PsiTryStatement element, TranslationContext ctx) {
        ctx.print("try {\n");
        ctx.increaseIdent();
        CodeBlockTranslator.translate(element.getTryBlock(), ctx);
        ctx.decreaseIdent();
        ctx.print("}");
        PsiCatchSection[] catchSections = element.getCatchSections();
        if (catchSections.length > 0) {
            ctx.append(" catch (").append(EXCEPTION_VAR).append(") {\n");
            ctx.increaseIdent();
            for (int i = 0; i < catchSections.length; i++) {
                PsiCatchSection catchSection = catchSections[i];
                String exceptionType = TypeHelper.printType(catchSection.getCatchType(), ctx);
                ctx.print("if (").append(EXCEPTION_VAR).append(" instanceof ").append(exceptionType).append(") {\n");
                ctx.increaseIdent();
                ctx.print("var ").append(KeywordHelper.process(catchSection.getParameter().getName(), ctx));
                ctx.append(": ").append(exceptionType).append(" = <").append(exceptionType).append(">").append(EXCEPTION_VAR).append(";\n");
                CodeBlockTranslator.translate(catchSection.getCatchBlock(), ctx);
                ctx.decreaseIdent();
                ctx.print("} else ");
            }

            ctx.append("{\n");
            ctx.increaseIdent();
            ctx.print("throw ").append(EXCEPTION_VAR).append(";\n");
            ctx.decreaseIdent();
            ctx.print("}\n");

            ctx.decreaseIdent();
            ctx.print("}");
        }
        if (element.getFinallyBlock() != null) {
            ctx.append(" finally {\n");

            ctx.increaseIdent();
            CodeBlockTranslator.translate(element.getFinallyBlock(), ctx);
            ctx.decreaseIdent();
            ctx.print("}");
        }
        ctx.append("\n");
    }

}
