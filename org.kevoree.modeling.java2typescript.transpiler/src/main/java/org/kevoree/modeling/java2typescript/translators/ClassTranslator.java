
package org.kevoree.modeling.java2typescript.translators;

import com.intellij.psi.*;
import com.intellij.psi.codeStyle.arrangement.std.StdArrangementTokens;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.javadoc.PsiDocTag;
import org.kevoree.modeling.java2typescript.TranslationContext;
import org.kevoree.modeling.java2typescript.TypeHelper;

import java.util.ArrayList;

public class ClassTranslator {

    public static void translate(PsiClass clazz, TranslationContext ctx) {

        boolean ignoreClass = false;
        boolean nativeActivated = false;
        PsiDocComment comment = clazz.getDocComment();
        if (comment != null) {
            PsiDocTag[] tags = comment.getTags();
            if (tags != null) {
                for (PsiDocTag tag : tags) {
                    if (tag.getName().equals(NativeTsTranslator.TAG_IGNORE) && tag.getValueElement() != null && tag.getValueElement().getText().equals(NativeTsTranslator.TAG_VAL_TS)) {
                        ignoreClass = true;
                    }
                    if (tag.getName().equals(NativeTsTranslator.TAG) && tag.getValueElement() != null && tag.getValueElement().getText().equals(NativeTsTranslator.TAG_VAL_TS)) {
                        nativeActivated = true;
                    }
                }
            }
        }
        if (ignoreClass) {
            //we skip the class
            return;
        }

        if (clazz.getModifierList().hasExplicitModifier(PsiModifier.ABSTRACT)) {
            ctx.print("export abstract class ");
        } else if (clazz.isInterface()) {
            ctx.print("export interface ");
        } else {
            ctx.print("export class ");
        }

        ctx.append(clazz.getName());
        PsiTypeParameter[] typeParameters = clazz.getTypeParameters();
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
            ctx.append('>');
        }
        PsiClassType[] extendsList = clazz.getExtendsListTypes();
        if (extendsList.length != 0 && !clazz.isEnum()) {
            ctx.append(" extends ");
            writeTypeList(ctx, extendsList);
        }
        PsiClassType[] implementsList = clazz.getImplementsListTypes();
        if (implementsList.length != 0) {
            ctx.append(" implements ");
            writeTypeList(ctx, implementsList);
        }
        ctx.append(" {\n\n");

        if (!nativeActivated) {
            printClassMembers(clazz, ctx);
        } else {
            ctx.increaseIdent();
            NativeTsTranslator.translate(comment, ctx);
            ctx.decreaseIdent();
        }

        ctx.print("}\n");
        ctx.append("\n");
        printInnerClasses(clazz, ctx);
    }

    private static void printInnerClasses(PsiClass element, TranslationContext ctx) {
        PsiClass[] innerClasses = element.getInnerClasses();

        boolean atLeastOne = false;
        for (PsiClass loopClass : innerClasses) {
            boolean ignoreClass = false;
            PsiDocComment comment = loopClass.getDocComment();
            if (comment != null) {
                PsiDocTag[] tags = comment.getTags();
                if (tags != null) {
                    for (PsiDocTag tag : tags) {
                        if (tag.getName().equals(NativeTsTranslator.TAG_IGNORE) && tag.getValueElement() != null && tag.getValueElement().getText().equals(NativeTsTranslator.TAG_VAL_TS)) {
                            ignoreClass = true;
                        }
                    }
                }
            }
            if (!ignoreClass) {
                atLeastOne = true;
            }
        }

        if (innerClasses.length > 0 && atLeastOne) {
            ctx.print("export module ").append(element.getName()).append(" { \n");
            ctx.increaseIdent();
            for (PsiClass innerClass : innerClasses) {
                translate(innerClass, ctx);
                ctx.append("\n");
            }
            ctx.decreaseIdent();
            ctx.print("}\n");
        }
    }

    private static void printClassMembers(PsiClass clazz, TranslationContext ctx) {
        ctx.increaseIdent();
        PsiField[] fields = clazz.getFields();
        for (PsiField field : fields) {
            FieldTranslator.translate(field, ctx);
        }
        PsiClassInitializer[] initializers = clazz.getInitializers();
        for (PsiClassInitializer initializer : initializers) {
            if (initializer.hasModifierProperty("static")) {
                ctx.print("//TODO Resolve static initializer\n");
                ctx.print("static {\n");
            } else {
                ctx.print("//TODO Resolve instance initializer\n");
                ctx.print("{\n");
            }
            ctx.increaseIdent();
            CodeBlockTranslator.translate(initializer.getBody(), ctx);
            ctx.decreaseIdent();
            ctx.print("}\n");
        }
        PsiMethod[] methods = clazz.getMethods();
        if (TypeHelper.isCallbackClass(clazz)) {
            if(methods.length > 0){
                MethodTranslator.translate(methods[0], ctx, true);
            }
        } else {
            for (PsiMethod method : methods) {
                MethodTranslator.translate(method, ctx, false);
            }
        }
        if (clazz.isEnum()) {
            ctx.print("public equals(other: any): boolean {\n");
            ctx.increaseIdent();
            ctx.print("return this == other;\n");
            ctx.decreaseIdent();
            ctx.print("}\n");
            ctx.print("public static _" + clazz.getName() + "VALUES : " + clazz.getName() + "[] = [\n");
            ctx.increaseIdent();
            boolean isFirst = true;
            for (int i = 0; i < clazz.getFields().length; i++) {
                if (clazz.getFields()[i].hasModifierProperty("static")) {
                    if (!isFirst) {
                        ctx.print(",");
                    } else {
                        ctx.print("");
                    }
                    ctx.append(clazz.getName());
                    ctx.append(".");
                    ctx.append(clazz.getFields()[i].getName());
                    ctx.append("\n");
                    isFirst = false;
                }
            }
            ctx.decreaseIdent();
            ctx.print("];\n");

            ctx.print("public static values():");
            ctx.append(clazz.getName());
            ctx.append("[]{\n");
            ctx.increaseIdent();
            ctx.print("return ");
            ctx.append(clazz.getName());
            ctx.append("._");
            ctx.append(clazz.getName());
            ctx.append("VALUES;\n");
            ctx.decreaseIdent();
            ctx.print("}\n");

        }
        ctx.decreaseIdent();
    }

    private static void writeTypeList(TranslationContext ctx, PsiClassType[] typeList) {
        for (int i = 0; i < typeList.length; i++) {
            PsiClassType type = typeList[i];
            ctx.append(TypeHelper.printType(type, ctx, true, true, false));
            if (i != typeList.length - 1) {
                ctx.append(", ");
            }
        }
    }

}
