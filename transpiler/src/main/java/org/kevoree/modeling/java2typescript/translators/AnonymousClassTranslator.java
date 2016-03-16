
package org.kevoree.modeling.java2typescript.translators;

import com.google.common.base.*;
import com.intellij.psi.*;
import com.intellij.psi.impl.compiled.ClsMethodImpl;
import org.kevoree.modeling.java2typescript.TranslationContext;
import org.kevoree.modeling.java2typescript.helper.TypeHelper;

import java.util.*;

public class AnonymousClassTranslator {

    private static final Joiner joiner = Joiner.on(", ");

    public static void translate(PsiAnonymousClass element, TranslationContext ctx) {
        if (TypeHelper.isCallbackClass(element.getBaseClassType().resolve())) {
            PsiMethod method = element.getAllMethods()[0];
            PsiParameter[] parameters = method.getParameterList().getParameters();
            String[] methodParameters = new String[parameters.length];
            for (int i = 0; i < methodParameters.length; i++) {
                methodParameters[i] = parameters[i].getName() + " : " + TypeHelper.printType(parameters[i].getTypeElement().getType(), ctx);
            }
            ctx.append("(" + String.join(", ", methodParameters) + ") => {\n");
            if (method.getBody() != null) {
                ctx.increaseIdent();
                CodeBlockTranslator.translate(method.getBody(), ctx);
                ctx.decreaseIdent();
            }
            ctx.print("}");
        } else {
            ctx.append("{\n");
            ctx.increaseIdent();
            printClassMembers(element, ctx);
            ctx.decreaseIdent();
            ctx.print("}");
        }
    }

    private static void printClassMembers(PsiClass element, TranslationContext ctx) {
        PsiMethod[] methods = element.getMethods();
        for (int i = 0; i < methods.length; i++) {
            ctx.print(methods[i].getName());
            ctx.append(": function (");
            printParameterList(methods[i], ctx);
            ctx.append(") {\n");
            if (methods[i].getBody() != null) {
                ctx.increaseIdent();
                CodeBlockTranslator.translate(methods[i].getBody(), ctx);
                ctx.decreaseIdent();
            }
            ctx.print("}");
            if (i < methods.length - 1) {
                ctx.append(",\n");
            } else {
                ctx.append("\n");
            }
        }
    }

    private static void printParameterList(PsiMethod element, TranslationContext ctx) {
        List<String> params = new ArrayList<String>();
        for (PsiParameter parameter : element.getParameterList().getParameters()) {
            StringBuilder paramSB = new StringBuilder();
            paramSB.setLength(0);
            if (parameter.isVarArgs()) {
                paramSB.append("...");
            }
            paramSB.append(parameter.getName());
            paramSB.append(": ");
            paramSB.append(TypeHelper.printType(parameter.getType(), ctx));
            params.add(paramSB.toString());
        }
        ctx.append(joiner.join(params));
    }

}
