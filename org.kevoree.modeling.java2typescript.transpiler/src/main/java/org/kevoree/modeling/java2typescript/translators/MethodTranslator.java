package org.kevoree.modeling.java2typescript.translators;

import com.intellij.psi.*;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.javadoc.PsiDocTag;
import com.intellij.psi.javadoc.PsiDocTagValue;
import org.kevoree.modeling.java2typescript.TranslationContext;
import org.kevoree.modeling.java2typescript.TypeHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duke on 11/6/14.
 */
public class MethodTranslator {

    public static void translate(PsiMethod method, TranslationContext ctx, boolean isAnonymous) {

        boolean nativeActivated = false;
        boolean ignore = false;
        //Check for native code
        PsiDocComment comment = method.getDocComment();
        if (comment != null) {
            PsiDocTag[] tags = comment.getTags();
            if (tags != null) {
                for (PsiDocTag tag : tags) {
                    if (tag.getName().equals(NativeTsTranslator.TAG) && tag.getValueElement() != null && tag.getValueElement().getText().equals(NativeTsTranslator.TAG_VAL_TS)) {
                        nativeActivated = true;
                    }
                    if (tag.getName().equals(NativeTsTranslator.TAG_IGNORE) && tag.getValueElement() != null && tag.getValueElement().getText().equals(NativeTsTranslator.TAG_VAL_TS)) {
                        ignore = true;
                    }
                }
            }
        }
        if (ignore) {
            return;
        }
        PsiModifierList modifierList = method.getModifierList();
        PsiClass containingClass = (PsiClass) method.getParent();
        if (method.isConstructor()) {
            ctx.print("constructor");
        } else {
            if (method.getContainingClass() != null && method.getContainingClass().isInterface()) {
                ctx.print("");
            } else {
                if (modifierList.hasModifierProperty("private")) {
                    ctx.print("private ");
                } else {
                    ctx.print("public ");
                }
            }
            if (modifierList.hasModifierProperty("static")) {
                ctx.append("static ");
            }
            if (!containingClass.isInterface() && modifierList.hasModifierProperty(PsiModifier.ABSTRACT)) {
                ctx.append("abstract ");
            }
            if (!isAnonymous) {
                ctx.append(method.getName());
            }
            PsiTypeParameter[] typeParameters = method.getTypeParameters();
            if (typeParameters.length > 0) {
                ctx.append('<');
                for (int i = 0; i < typeParameters.length; i++) {
                    PsiTypeParameter p = typeParameters[i];
                    ctx.append(p.getName());
                    PsiClassType[] extentions = p.getExtendsList().getReferencedTypes();
                    if (extentions.length > 0) {
                        ctx.append(" extends ");
                        for (PsiClassType ext : extentions) {
                            ctx.append(TypeHelper.printType(ext, ctx));
                        }
                    }
                    if (i != typeParameters.length - 1) {
                        ctx.append(", ");
                    }
                }
                ctx.append("> ");
            }
        }
        ctx.append('(');
        List<String> params = new ArrayList<String>();
        StringBuilder paramSB = new StringBuilder();
        for (PsiParameter parameter : method.getParameterList().getParameters()) {
            paramSB.setLength(0);
            if (parameter.isVarArgs()) {
                paramSB.append("...");
            }
            paramSB.append(parameter.getName());
            paramSB.append(": ");
            paramSB.append(TypeHelper.printType(parameter.getType(), ctx));
            params.add(paramSB.toString());
        }
        ctx.append(String.join(", ", params));
        ctx.append(')');
        if (!method.isConstructor()) {
            ctx.append(": ");
            ctx.append(TypeHelper.printType(method.getReturnType(), ctx));
        }
        if (!containingClass.isInterface()) {
            if (method.getBody() == null) {
                ctx.append(";");
            } else {
                ctx.append(" {\n");
                ctx.increaseIdent();
                if (!nativeActivated) {
                    if (method.getBody() == null) {
                        ctx.print("throw \"Empty body\";\n");
                    } else {
                        CodeBlockTranslator.translate(method.getBody(), ctx);
                    }
                } else {
                    NativeTsTranslator.translate(comment, ctx);
                }
                ctx.decreaseIdent();
                ctx.print("}\n");
            }
        } else {
            ctx.append(";\n");
        }
        ctx.append("\n");
    }

}
