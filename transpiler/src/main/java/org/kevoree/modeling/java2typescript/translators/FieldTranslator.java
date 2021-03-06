
package org.kevoree.modeling.java2typescript.translators;

import com.intellij.psi.*;
import org.kevoree.modeling.java2typescript.helper.DocHelper;
import org.kevoree.modeling.java2typescript.context.TranslationContext;
import org.kevoree.modeling.java2typescript.helper.KeywordHelper;
import org.kevoree.modeling.java2typescript.helper.TypeHelper;
import org.kevoree.modeling.java2typescript.metas.DocMeta;
import org.kevoree.modeling.java2typescript.translators.expression.ExpressionListTranslator;
import org.kevoree.modeling.java2typescript.translators.expression.ExpressionTranslator;

public class FieldTranslator {

    public static void translate(PsiField element, TranslationContext ctx) {
        //Check for native code
        DocMeta docMeta = DocHelper.process(element.getDocComment());
        if (docMeta.ignored) {
            return;
        }
        if (!docMeta.nativeActivated) {
            if (element instanceof PsiEnumConstant) {
                translateEnumConstant((PsiEnumConstant) element, ctx);
            } else {
                translateClassField(element, ctx);
            }
        } else {
            DocTagTranslator.translate(element.getDocComment(), ctx);
        }
    }

    private static void translateEnumConstant(PsiEnumConstant element, TranslationContext ctx) {
        String enumName = ((PsiClass) element.getParent()).getName();
        ctx.print("public static ").append(element.getName()).append(": ").append(enumName);
        ctx.append(" = new ").append(enumName);
        ctx.append('(');
        if (element.getArgumentList() != null) {
            ExpressionListTranslator.translate(element.getArgumentList(), ctx);
        }
        ctx.append(");\n");
    }

    private static void translateClassField(PsiField element, TranslationContext ctx) {
        PsiModifierList modifierList = element.getModifierList();
        if (modifierList != null && modifierList.hasModifierProperty("private")) {
            ctx.print("private ");
        } else {
            ctx.print("public ");
        }
        if (modifierList != null && modifierList.hasModifierProperty("static")) {
            ctx.append("static ");
        }
        ctx.append(element.getName()).append(": ");
        ctx.append(TypeHelper.printType(element.getType(), ctx));
        if (element.hasInitializer()) {
            ctx.append(" = ");
            ExpressionTranslator.translate(element.getInitializer(), ctx);
            ctx.append(";\n");
        } else {
            ctx.append(";\n");
        }
    }

}
