package org.kevoree.modeling.java2typescript.translators;

import com.intellij.compiler.ant.taskdefs.Import;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiClassReferenceType;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.javadoc.PsiDocTag;
import com.intellij.psi.javadoc.PsiDocTagValue;
import org.kevoree.modeling.java2typescript.DocHelper;
import org.kevoree.modeling.java2typescript.ImportHelper;
import org.kevoree.modeling.java2typescript.TranslationContext;
import org.kevoree.modeling.java2typescript.TypeHelper;
import org.kevoree.modeling.java2typescript.metas.DocMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duke on 11/6/14.
 */
public class MethodTranslator {

    public static void translate(PsiMethod method, TranslationContext ctx, boolean isAnonymous) {
        DocMeta docMeta = DocHelper.process(method.getDocComment());

        if (docMeta.ignored) {
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
            TypeParametersTranslator.translate(method.getTypeParameters(), ctx);
        }
        ctx.append('(');
        List<String> params = new ArrayList<>();
        StringBuilder paramSB = new StringBuilder();
        for (PsiParameter parameter : method.getParameterList().getParameters()) {
            paramSB.setLength(0);
            if (parameter.isVarArgs()) {
                paramSB.append("...");
            }
            paramSB.append(parameter.getName());
            paramSB.append(": ");
            if (parameter.getType() instanceof PsiClassReferenceType) {
                PsiElement resolution = ((PsiClassReferenceType) parameter.getType()).getReference().resolve();
                if (resolution != null) {
                    ImportHelper.importIfValid(resolution, ctx);
                }
            }
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
                if (!docMeta.nativeActivated) {
                    if (method.getBody() == null) {
                        ctx.print("throw \"Empty body\";\n");
                    } else {
                        CodeBlockTranslator.translate(method.getBody(), ctx);
                    }
                } else {
                    NativeTsTranslator.translate(method.getDocComment(), ctx);
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
