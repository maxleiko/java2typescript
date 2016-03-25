
package org.kevoree.modeling.java2typescript.translators.statement;

import com.intellij.psi.PsiForeachStatement;
import com.intellij.psi.PsiParameter;
import org.kevoree.modeling.java2typescript.context.TranslationContext;
import org.kevoree.modeling.java2typescript.helper.KeywordHelper;
import org.kevoree.modeling.java2typescript.helper.TypeHelper;
import org.kevoree.modeling.java2typescript.translators.expression.ExpressionTranslator;

public class ForEachStatementTranslator {

    public static void translate(PsiForeachStatement element, TranslationContext ctx) {
        PsiParameter parameter = element.getIterationParameter();
        ctx.print("");
        ExpressionTranslator.translate(element.getIteratedValue(), ctx);
        ctx.append(".forEach((");
        ctx.append(KeywordHelper.process(parameter.getName(), ctx));
        ctx.append(": ");
        ctx.append(TypeHelper.printType(parameter.getType(), ctx));
        ctx.append(") => {\n");
        ctx.increaseIdent();
        StatementTranslator.translate(element.getBody(), ctx);
        ctx.decreaseIdent();
        ctx.print("});\n");
    }
}
