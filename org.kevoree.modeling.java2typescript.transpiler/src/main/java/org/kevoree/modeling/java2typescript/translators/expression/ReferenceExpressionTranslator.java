
package org.kevoree.modeling.java2typescript.translators.expression;

import com.intellij.psi.*;
import org.kevoree.modeling.java2typescript.helper.ImportHelper;
import org.kevoree.modeling.java2typescript.TranslationContext;
import org.kevoree.modeling.java2typescript.helper.TypeHelper;

public class ReferenceExpressionTranslator {

    public static void translate(PsiReferenceExpression element, TranslationContext ctx) {
        PsiElement resolution = element.resolve();
        if (element.getQualifierExpression() != null) {
            ExpressionTranslator.translate(element.getQualifierExpression(), ctx);
            if (resolution instanceof PsiMethod) {
                PsiMethod method = (PsiMethod) resolution;
                if (!TypeHelper.isCallbackClass(method.getContainingClass())) {
                    ctx.append('.');
                }
            } else {
                ctx.append('.');
            }
        } else {
            if (resolution != null) {
                String qualifier = "this";
                if (resolution instanceof PsiField) {
                    PsiField field = (PsiField) resolution;
                    if (field.getModifierList() != null && field.getModifierList().hasModifierProperty("static")) {
                        qualifier = field.getContainingClass().getQualifiedName();
                    }
                    ctx.append(qualifier).append('.');
                } else if (resolution instanceof PsiMethod) {
                    PsiMethod method = (PsiMethod) resolution;
                    if (method.getModifierList().hasModifierProperty("static")) {
                        qualifier = method.getContainingClass().getQualifiedName();
                    }
                    if (!element.getReferenceName().equals("super")) {
                        ctx.append(qualifier).append('.');
                    }
                } else if (resolution instanceof PsiClass) {
//                    ImportHelper.importIfValid(resolution, ctx);
                }
            }
        }
        if (resolution instanceof PsiMethod) {
            PsiMethod method = (PsiMethod) resolution;
            if (!TypeHelper.isCallbackClass(method.getContainingClass())) {
                ctx.append(element.getReferenceName());
            }
        } else {
            if (element.getReference() != null && element.getReference().resolve() instanceof PsiClass) {
                PsiClass clazz = (PsiClass) element.getReference().resolve();
                ctx.append(TypeHelper.primitiveStaticCall(clazz.getQualifiedName()));
            } else {
                ctx.append(TypeHelper.primitiveStaticCall(element.getReferenceName()));
            }
        }
    }

}
