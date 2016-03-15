package org.kevoree.modeling.java2typescript.helper;

import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiImmediateClassType;
import org.kevoree.modeling.java2typescript.TranslationContext;

/**
 *
 * Created by leiko on 3/15/16.
 */
public class GenericHelper {

    public static void translate(PsiClass clazz, PsiElement parent, TranslationContext ctx) {
        String type = clazz.getQualifiedName();
        PsiTypeParameter[] typeParameters = clazz.getTypeParameters();
        PsiTypeParameter[] referenceParameters = clazz.getTypeParameters();
        if (typeParameters.length > 0) {
            String[] generics = new String[typeParameters.length];
            for (int i = 0; i < typeParameters.length; i++) {
//                if (referenceParameters.length > i) {
//                    if (parent instanceof PsiField) {
//                        System.out.println("PSIFIELD> "+((PsiField) parent).getType().getPresentableText());
//                        //TypeHelper.printType(((PsiField) parent).getType(), ctx);
//                    } else if (parent instanceof PsiNewExpression && referenceParameters[i] instanceof PsiImmediateClassType) {
//                        System.out.println("YEAH WELL");
//                        PsiTypeElement resolvedGeneric = ((PsiField) ((PsiAssignmentExpression) parent.getParent()).getLExpression().getReference().resolve()).getTypeElement().getInnermostComponentReferenceElement().getParameterList().getTypeParameterElements()[i];
//                        generics[i] = TypeHelper.printType(resolvedGeneric.getType(), ctx);
//                        System.out.println("GEN> "+generics[i]);
//                    } else {
//                        System.out.println("Yeah ? > "+referenceParameters[i]);
//                        generics[i] = referenceParameters[i].getText();
//                    }
//                } else {
//                    generics[i] = "any";
//                }
                generics[i] = "any";
            }
            type += "<"+String.join(", ", generics)+">";
        }
        System.out.println("append> "+type);
        ctx.append(type);
    }
}
