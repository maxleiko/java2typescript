
package org.kevoree.modeling.java2typescript.translators;

import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiClassReferenceType;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.javadoc.PsiDocTag;
import org.kevoree.modeling.java2typescript.ImportHelper;
import org.kevoree.modeling.java2typescript.TranslationContext;
import org.kevoree.modeling.java2typescript.TypeHelper;
import org.kevoree.modeling.java2typescript.translators.expression.ExpressionTranslator;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FieldTranslator {

    public static void translate(PsiField element, TranslationContext ctx) {
        //Check for native code
        boolean nativeActivated = false;
        boolean ignored = false;
        PsiDocComment comment = element.getDocComment();
        if(comment != null) {
            PsiDocTag[] tags = comment.getTags();
            if(tags != null) {
                for(PsiDocTag tag : tags) {
                    if (tag.getName().equals(NativeTsTranslator.TAG) && tag.getValueElement()!=null && tag.getValueElement().getText().equals(NativeTsTranslator.TAG_VAL_TS)) {
                        nativeActivated = true;
                    }
                    if (tag.getName().equals(NativeTsTranslator.TAG_IGNORE) && tag.getValueElement()!=null && tag.getValueElement().getText().equals(NativeTsTranslator.TAG_VAL_TS)) {
                        ignored = true;
                    }
                }
            }
        }
        if(ignored){
            return;
        }
        if(!nativeActivated) {
            if (element instanceof PsiEnumConstant) {
                translateEnumConstant((PsiEnumConstant) element, ctx);
            } else {
                translateClassField(element, ctx);
            }
        } else {
            NativeTsTranslator.translate(comment, ctx);
        }
    }

    private static void translateEnumConstant(PsiEnumConstant element, TranslationContext ctx) {
        String enumName = ((PsiClass) element.getParent()).getName();
        ctx.print("public static ").append(element.getName()).append(": ").append(enumName);
        ctx.append(" = new ").append(enumName);
        ctx.append('(');
        if (element.getArgumentList() != null) {
            PsiExpression[] arguments = element.getArgumentList().getExpressions();
            for (int i = 0; i < arguments.length; i++) {
                ExpressionTranslator.translate(arguments[i], ctx);
                if (i != arguments.length - 1) {
                    ctx.append(", ");
                }
            }
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
        if (element.getType() instanceof PsiClassReferenceType) {
            PsiElement resolution = ((PsiClassReferenceType) element.getType()).getReference().resolve();
            ImportHelper.importIfValid(element, resolution, ctx);
        }
        ctx.append(TypeHelper.printType(element.getType(), ctx));
        if (element.hasInitializer()) {
            ctx.append(" = ");
            ExpressionTranslator.translate(element.getInitializer(), ctx);
            ctx.append(";\n");
        } else {
            /*
            if (TypeHelper.isPrimitiveField(element)) {
                ctx.append(" = 0");
            } else {
                ctx.append(" = null");
            }*/
            ctx.append(";\n");
        }
    }

}
