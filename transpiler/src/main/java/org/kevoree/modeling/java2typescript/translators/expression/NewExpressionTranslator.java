
package org.kevoree.modeling.java2typescript.translators.expression;

import com.intellij.psi.*;
import org.kevoree.modeling.java2typescript.helper.GenericHelper;
import org.kevoree.modeling.java2typescript.helper.ImportHelper;
import org.kevoree.modeling.java2typescript.TranslationContext;
import org.kevoree.modeling.java2typescript.helper.TypeHelper;
import org.kevoree.modeling.java2typescript.translators.AnonymousClassTranslator;

public class NewExpressionTranslator {

    public static void translate(PsiNewExpression element, TranslationContext ctx) {
        PsiAnonymousClass anonymousClass = element.getAnonymousClass();
        if (anonymousClass != null) {
            AnonymousClassTranslator.translate(anonymousClass, ctx);
        } else {
            boolean arrayDefinition = false;
            PsiJavaCodeReferenceElement classReference = element.getClassReference();
            String className;
            if (classReference != null) {
                className = TypeHelper.printType(element.getType(), ctx);
            } else {
                className = TypeHelper.printType(element.getType().getDeepComponentType(), ctx);
                arrayDefinition = true;
            }
            PsiExpression[] arrayDimensions = element.getArrayDimensions();
            PsiArrayInitializerExpression arrayInitializer = element.getArrayInitializer();
            if (arrayDimensions.length > 0) {
                arrayDefinition = true;
            }
            if (arrayInitializer != null) {
                arrayDefinition = true;
            }
            if (!arrayDefinition) {
                ctx.append("new ").append(className).append('(');
                if (element.getArgumentList() != null) {
                    ExpressionListTranslator.translate(element.getArgumentList(), ctx);
                }
                ctx.append(')');
            } else {
                if (arrayInitializer != null) {
                    ArrayInitializerExpressionTranslator.translate(arrayInitializer, ctx);
                } else {
                    int dimensionCount = arrayDimensions.length;
                    if (ctx.NATIVE_ARRAY && dimensionCount == 1) {
                        if (element.getType().equalsToText("int[]")) {
                            ctx.append("new Int32Array(");
                            ExpressionTranslator.translate(element.getArrayDimensions()[0], ctx);
                            ctx.append(")");
                        } else if (element.getType().equalsToText("double[]")) {
                            ctx.append("new Float64Array(");
                            ExpressionTranslator.translate(element.getArrayDimensions()[0], ctx);
                            ctx.append(")");
                        } else if (element.getType().equalsToText("long[]")) {
                            ctx.append("new Float64Array(");
                            ExpressionTranslator.translate(element.getArrayDimensions()[0], ctx);
                            ctx.append(")");
                        } else {
                            ctx.append("new Array<");
                            if (element.getClassOrAnonymousClassReference() != null) {
                                PsiJavaCodeReferenceElement ref = element.getClassOrAnonymousClassReference();
                                if (ref.getReference() != null && ref.getReference().resolve() != null) {
                                    PsiClass refClass = (PsiClass) ref.getReference().resolve();
                                    GenericHelper.translate(refClass, element.getParent(), ctx);
                                } else {
                                    ctx.append(TypeHelper.printType(element.getType(), ctx, false, false));
                                }
                            } else {
                                ctx.append(TypeHelper.printType(element.getType(), ctx, false, false));
                            }
                            ctx.append(">(");
                            ExpressionTranslator.translate(element.getArrayDimensions()[0], ctx);
                            ctx.append(")");
                        }
                    } else {
                        for (int i = 0; i < dimensionCount; i++) {
                            ctx.append("[");
                        }
                        for (int i = 0; i < dimensionCount; i++) {
                            ctx.append("]");
                        }
                    }
                }
            }
        }
    }
}


