package org.kevoree.modeling.java2typescript.helper;

import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiClassReferenceType;
import com.intellij.psi.impl.source.PsiImmediateClassType;
import org.kevoree.modeling.java2typescript.context.TranslationContext;
import org.kevoree.modeling.java2typescript.translators.TypeParametersTranslator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Created by leiko on 3/15/16.
 */
public class GenericHelper {

    public static String process(PsiClass clazz) {
        String type = clazz.getQualifiedName();
        PsiTypeParameter[] typeParameters = clazz.getTypeParameters();
        if (typeParameters.length > 0) {
            String[] generics = new String[typeParameters.length];
            for (int i = 0; i < typeParameters.length; i++) {
                generics[i] = "any";
            }
            type += "<"+String.join(", ", generics)+">";
        }
        return type;
    }

    public static String process(PsiClassReferenceType classRefType, TranslationContext ctx, boolean withGenerics) {
        String result = "";
        PsiJavaCodeReferenceElement ref = classRefType.getReference();
        if (ref.getText().endsWith("<>")) {
            result = processDiamondOperator(ref.getParent().getParent(), ctx);
        } else {
            ArrayList<String> genericParameterNames = ctx.getGenericParameterNames();
            if (classRefType.getParameterCount() > 0) {
                String[] generics = new String[classRefType.getParameterCount()];
                PsiType[] genericTypes = classRefType.getParameters();
                for (int i = 0; i < genericTypes.length; i++) {
                    if (genericTypes[i] instanceof PsiWildcardType) {
                        if (((PsiWildcardType) genericTypes[i]).getBound() != null) {
                            if (classRefType.getReference().getParent().getParent() instanceof PsiParameter) {
                                PsiParameter param = (PsiParameter) classRefType.getReference().getParent().getParent();
                                PsiParameterList paramList = (PsiParameterList) param.getParent();
                                generics[i] = genericParameterNames.get(paramList.getParameterIndex(param));
                            }
                        } else {
                            generics[i] = "any";
                        }
                    } else {
                        generics[i] = TypeHelper.printType(genericTypes[i], ctx, false, false);
                    }
                }
                result += "<" + String.join(", ", generics) + ">";
            } else if (withGenerics) {
                PsiClass clazz = classRefType.resolve();
                if (clazz != null) {
                    if (clazz.getTypeParameters().length > 0) {
                        String[] genericParams = new String[clazz.getTypeParameters().length];
                        for (int i=0; i < clazz.getTypeParameters().length; i++) {
                            genericParams[i] = "any";
                        }
                        result = "<" + String.join(", ", genericParams) + ">";
                    }
                } else {
                    if (ref.getText().endsWith("<>")) {
                        result = processDiamondOperator(ref.getParent().getParent(), ctx);
                    }
                }
            } else {
                if (classRefType.getPresentableText().contains("<")) {
                    result += "<any>";
                }
            }
        }

        return result;
    }

    private static String processDiamondOperator(PsiElement element, TranslationContext ctx) {
        String result;
        // Java 7 diamond operator not possible in TypeScript
        if (element instanceof PsiField) {
            PsiField field = (PsiField) element;
            result = process((PsiClassReferenceType) field.getType(), ctx, true);
        } else if (element instanceof PsiLocalVariable) {
            System.out.println("DIAMOND LOCALVAR> " + ((PsiLocalVariable) element).getType());
            PsiLocalVariable localVar = (PsiLocalVariable) element;
            result = process((PsiClassReferenceType) localVar.getType(), ctx, true);
        } else if (element instanceof PsiAssignmentExpression) {
            PsiAssignmentExpression assign = (PsiAssignmentExpression) element;
            result = process((PsiClassReferenceType) assign.getLExpression().getType(), ctx, true);
        } else {
            System.out.println("DIAMOND > "+element);
            result = "<any>";
        }
        return result;
    }
}
