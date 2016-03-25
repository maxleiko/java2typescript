
package org.kevoree.modeling.java2typescript.translators.expression;

import com.intellij.psi.*;
import org.kevoree.modeling.java2typescript.context.TranslationContext;
import org.kevoree.modeling.java2typescript.helper.TypeHelper;

public class MethodCallExpressionTranslator {

    public static void translate(PsiMethodCallExpression element, TranslationContext ctx) {
        PsiReferenceExpression methodExpr = element.getMethodExpression();
        boolean hasBeenTransformed = tryNativeTransform(element, ctx);
        if (!hasBeenTransformed) {
            ReferenceExpressionTranslator.translate(methodExpr, ctx);
            ctx.append('(');
            printParameters(element.getArgumentList().getExpressions(), ctx);
            ctx.append(")");
        }
    }

    private static void printParameters(PsiExpression[] arguments, TranslationContext ctx) {
        for (int i = 0; i < arguments.length; i++) {
            if (arguments[i] instanceof PsiReferenceExpression) {
                ReferenceExpressionTranslator.translate((PsiReferenceExpression) arguments[i], ctx);
            } else {
                ExpressionTranslator.translate(arguments[i], ctx);
            }
            if (i != arguments.length - 1) {
                ctx.append(", ");
            }
        }
    }

    private static boolean tryNativeTransform(PsiMethodCallExpression element, TranslationContext ctx) {
        if (element.getText().matches("^(java\\.lang\\.)?System\\.out.*$")) {
            ctx.append("console.log(");
            PsiExpression[] arguments = element.getArgumentList().getExpressions();
            for (int i = 0; i < arguments.length; i++) {
                ExpressionTranslator.translate(arguments[i], ctx);
                if (i != arguments.length - 1) {
                    ctx.append(", ");
                }
            }
            ctx.append(")");
            return true;

        } else if (element.getText().matches("^(java\\.lang\\.)?System\\.err.*$")) {
            ctx.append("console.error(");
            PsiExpression[] arguments = element.getArgumentList().getExpressions();
            for (int i = 0; i < arguments.length; i++) {
                ExpressionTranslator.translate(arguments[i], ctx);
                if (i != arguments.length - 1) {
                    ctx.append(", ");
                }
            }
            ctx.append(")");
            return true;

        } else if (element.getText().matches("^(java\\.lang\\.)?String\\.join\\(.*$")) {
            ctx.append(TypeHelper.javaTypes.get("String"));
            ctx.append(".join(");
            PsiExpression[] arguments = element.getArgumentList().getExpressions();
            for (int i = 0; i < arguments.length; i++) {
                PsiType type = arguments[i].getType();
                if (type != null && type.getPresentableText().endsWith("[]")) {
                    ctx.append("...");
                }
                if (arguments[i] instanceof PsiReferenceExpression) {
                    ReferenceExpressionTranslator.translate((PsiReferenceExpression) arguments[i], ctx);
                } else {
                    ExpressionTranslator.translate(arguments[i], ctx);
                }
                if (i != arguments.length - 1) {
                    ctx.append(", ");
                }
            }
            ctx.append(")");
            ctx.needsJava(TypeHelper.javaTypes.get("String"));
            return true;
        } else {
            PsiReferenceExpression expr = element.getMethodExpression();
            if (expr.getQualifierExpression() instanceof PsiReferenceExpression) {
                PsiElement resolvedRootElem = ((PsiReferenceExpression) expr.getQualifierExpression()).resolve();
                if (resolvedRootElem != null) {
                    if (resolvedRootElem instanceof PsiVariable) {
                        return processRootElem(element, ((PsiVariable) resolvedRootElem).getType(), ctx);

                    } else if (resolvedRootElem instanceof PsiMethodCallExpression) {
                        MethodCallExpressionTranslator.translate((PsiMethodCallExpression) resolvedRootElem, ctx);
                        return true;

                    } else if (resolvedRootElem.getParent() instanceof PsiCatchSection) {
                        ctx.append("console.error(");
                        ExpressionTranslator.translate(element.getMethodExpression().getQualifierExpression(), ctx);
                        ctx.append("['stack'])");
                        return true;
                    }
                }
            } else if (expr.getQualifierExpression() != null && expr.getQualifierExpression().getType() != null) {
                if (expr.getQualifierExpression() instanceof PsiMethodCallExpression) {
                    String[] parts = element.getMethodExpression().getText().split("\\.");
                    String methodName = parts[parts.length - 1];
                    ctx.append(TypeHelper.javaTypes.get("String"));
                    ctx.append(".");
                    ctx.append(methodName);
                    ctx.append("(");
                    TranslationContext innerCtx = new TranslationContext();
                    MethodCallExpressionTranslator.translate((PsiMethodCallExpression) expr.getQualifierExpression(), innerCtx);
                    ctx.append(innerCtx.getContent());
                    if (element.getArgumentList().getExpressions().length > 0) {
                        ctx.append(", ");
                    }
                    printParameters(element.getArgumentList().getExpressions(), ctx);
                    ctx.append(")");
                    ctx.needsJava(TypeHelper.javaTypes.get("String"));

                    return true;
                }
            }
        }

        return false;
    }

    private static boolean processRootElem(PsiMethodCallExpression methodCall, PsiType rootType, TranslationContext ctx) {
        if (rootType.getPresentableText().equals("String")) {
            String methodPath = methodCall.getText().split("\\(", 2)[0];
            PsiElement methodNameElement = methodCall.findElementAt(methodPath.lastIndexOf(".") + 1);
            if (methodNameElement != null) {
                String methodName = methodNameElement.getText();
                String rootRef = ReferenceExpressionTranslator.translate(methodCall.getMethodExpression(), ctx, false);
                rootRef = rootRef.substring(0, rootRef.length() - methodName.length());
                if (rootRef.endsWith(".")) {
                    rootRef = rootRef.substring(0, rootRef.length() - 1);
                }
                if (methodName.equals("concat")) {
                    ctx.append(rootRef);
                    ctx.append(" + ");
                    ExpressionTranslator.translate(methodCall.getArgumentList().getExpressions()[0], ctx);
                } else {
                    ctx.append(TypeHelper.javaTypes.get("String"));
                    ctx.append(".");
                    ctx.append(methodName);
                    ctx.append("(");
                    ctx.append(rootRef);
                    if (methodCall.getArgumentList().getExpressions().length > 0) {
                        ctx.append(", ");
                    }
                    printParameters(methodCall.getArgumentList().getExpressions(), ctx);
                    ctx.append(")");
                    ctx.needsJava(TypeHelper.javaTypes.get("String"));
                }

                return true;
            }
        }
        return false;
    }
}
