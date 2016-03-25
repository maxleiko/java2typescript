package org.kevoree.modeling.java2typescript.translators;

import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiClassReferenceType;
import org.kevoree.modeling.java2typescript.context.TranslationContext;
import org.kevoree.modeling.java2typescript.helper.DocHelper;
import org.kevoree.modeling.java2typescript.helper.KeywordHelper;
import org.kevoree.modeling.java2typescript.helper.TypeHelper;
import org.kevoree.modeling.java2typescript.metas.DocMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
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
        ArrayList<String> paramGenParamTypeName = new ArrayList<>();
        ctx.setGenericParameterNames(paramGenParamTypeName);
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
                ctx.append(KeywordHelper.process(method.getName(), ctx));
            }
            HashSet<String> usedGenerics = new HashSet<>();
            PsiTypeParameter[] parameters = method.getTypeParameters();
            for (PsiTypeParameter parameter : parameters) {
                usedGenerics.add(parameter.getName());
            }
            String genericParams = TypeParametersTranslator.print(method.getTypeParameters(), ctx);
            if (!genericParams.isEmpty()) {
                genericParams = "<" + genericParams;
            }
            for (int i=0; i < method.getParameterList().getParameters().length; i++) {
                PsiParameter param = method.getParameterList().getParameters()[i];
                if (param.getType() instanceof PsiClassReferenceType) {
                    PsiClassReferenceType paramClassRef = (PsiClassReferenceType) param.getType();
                    if (paramClassRef.getParameters().length > 0) {
                        // param has generic params
                        for (PsiType paramGenParamType : paramClassRef.getParameters()) {
                            if (paramGenParamType instanceof PsiWildcardType) {
                                if (((PsiWildcardType) paramGenParamType).getBound() != null) {
                                    if (!genericParams.isEmpty()) {
                                        genericParams += ", ";
                                    } else {
                                        genericParams = "<";
                                    }
                                    String genType = TypeHelper.availableGenericType(usedGenerics);
                                    usedGenerics.add(genType);
                                    paramGenParamTypeName.add(i, genType);
                                    genericParams += genType +
                                            " extends " +
                                            TypeHelper.printType(((PsiWildcardType) paramGenParamType).getBound(), ctx);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (!genericParams.isEmpty()) {
                genericParams += ">";
            }
            ctx.append(genericParams);
        }
        ctx.append('(');
        List<String> params = new ArrayList<>();
        StringBuilder paramSB = new StringBuilder();
        for (PsiParameter parameter : method.getParameterList().getParameters()) {
            paramSB.setLength(0);
            if (parameter.isVarArgs()) {
                paramSB.append("...");
            }
            paramSB.append(KeywordHelper.process(parameter.getName(), ctx));
            if (docMeta.optional.contains(parameter.getName())) {
                paramSB.append("?");
            }
            paramSB.append(": ");

            paramSB.append(TypeHelper.printType(parameter.getType(), ctx, false, true));
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
                ctx.append(";\n");
            } else {
                ctx.append(" {");
                if (method.getBody().getStatements().length > 0) {
                    ctx.append("\n");
                    ctx.increaseIdent();
                    if (!docMeta.nativeActivated) {
                        if (method.getBody() == null) {
                            ctx.print("throw \"Empty body\";\n");
                        } else {
                            CodeBlockTranslator.translate(method.getBody(), ctx);
                        }
                    } else {
                        DocTagTranslator.translate(method.getDocComment(), ctx);
                    }
                    ctx.decreaseIdent();
                    ctx.print("}\n");
                } else {
                    ctx.append("}\n");
                }
            }
        } else {
            ctx.append(";\n");
        }
        ctx.removeGenericParameterNames();
    }
}
