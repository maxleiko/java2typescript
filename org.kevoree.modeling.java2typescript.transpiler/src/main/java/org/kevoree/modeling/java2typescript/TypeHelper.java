
package org.kevoree.modeling.java2typescript;

import com.google.common.collect.ImmutableSet;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiClassReferenceType;
import com.intellij.psi.impl.source.PsiImmediateClassType;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.javadoc.PsiDocTag;
import org.kevoree.modeling.java2typescript.translators.NativeTsTranslator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Set;

public class TypeHelper {

    public static String printType(PsiType element, TranslationContext ctx) {
        return printType(element, ctx, true, false, false);
    }

    public static String printType(PsiType element, TranslationContext ctx, boolean withGenericParams, boolean explicitType, boolean avoidNativeOptim) {
        String result = element.getPresentableText();

        if (result.equals("Throwable") || result.equals("Exception") || result.equals("RuntimeException") || result.equals("IndexOutOfBoundsException")) {
            return "Error";
        }

        if (result.contains("?")) {
            System.out.println("TypeHelper: Java wildcard converted to \"any\" for \""+ element.getPresentableText()+"\"");
            return "any";
        }

        if (objects.contains(result) || classes.contains(result)) {
            return "any";
        } else if (primitiveNumbers.contains(result) || objectNumbers.contains(result)) {
            return "number";
        } else if (strings.contains(result)) {
            return "string";
        } else if (booleans.contains(result)) {
            return "boolean";
        }
        if (ctx.NATIVE_ARRAY && !avoidNativeOptim && element.getArrayDimensions() == 1) {
            if (element.equalsToText("int[]")) {
                return "Int32Array";
            } else if (element.equalsToText("double[]")) {
                return "Float64Array";
            } else if (element.equalsToText("long[]")) {
                return "Float64Array";
            }
        }
        if (element instanceof PsiPrimitiveType) {
            if (result == null || result.equals("null")) {
                System.err.println("TypeHelper::printType -> Result null with elem:" + element.toString());
            }
            return result;
        } else if (element instanceof PsiArrayType) {
            PsiArrayType typedElement = (PsiArrayType) element;
            String partialResult = printType(typedElement.getComponentType(), ctx, true, false, true);
            result = partialResult + "[]";
            return result;
        } else if (element instanceof PsiClassReferenceType) {
            PsiClassReferenceType elementClassRefType = ((PsiClassReferenceType) element);
            PsiClass resolvedClass = elementClassRefType.resolve();

            if (resolvedClass != null) {
                String qualifiedName = resolvedClass.getName();
                if (qualifiedName != null) {
                    result = resolvedClass.getName();
                }
                if (withGenericParams) {
                    PsiTypeParameter[] typeParameters = resolvedClass.getTypeParameters();
                    PsiType[] referenceParameters = elementClassRefType.getParameters();
                    if (typeParameters.length > 0) {
                        String[] generics = new String[typeParameters.length];
                        PsiElement parent = elementClassRefType.getReference().getElement().getParent();
                        for (int i = 0; i < typeParameters.length; i++) {
                            if (referenceParameters.length > i) {
                                // Support for the new Foo<>() Java8 construct.
                                if (parent instanceof PsiNewExpression && referenceParameters[i] instanceof PsiImmediateClassType) {
                                    PsiTypeElement resolvedGeneric = ((PsiField) ((PsiAssignmentExpression) parent.getParent()).getLExpression().getReference().resolve()).getTypeElement().getInnermostComponentReferenceElement().getParameterList().getTypeParameterElements()[i];
                                    generics[i] = printType(resolvedGeneric.getType(), ctx);
                                } else {
                                    generics[i] = printType(referenceParameters[i], ctx);
                                }
                            } else {
                                generics[i] = "any";
                            }
                        }
                        result += "<" + String.join(", ", generics) + ">";
                    }
                }
            } else {
                String tryJavaUtil = javaTypes.get(elementClassRefType.getClassName());
                if (tryJavaUtil != null) {
                    result = tryJavaUtil;
                } else {
                    result = elementClassRefType.getReference().getQualifiedName();
                }
                if (withGenericParams) {
                    if (elementClassRefType.getParameterCount() > 0) {
                        String[] generics = new String[elementClassRefType.getParameterCount()];
                        PsiType[] genericTypes = elementClassRefType.getParameters();
                        for (int i = 0; i < genericTypes.length; i++) {
                            generics[i] = printType(genericTypes[i], ctx);
                        }
                        result += "<" + String.join(", ", generics) + ">";
                    }
                }
            }
        } else {
            System.out.println("TypeHelper: unhandled type -> " + element.getClass());
        }

        if (result == null || result.equals("null")) {
            System.err.println("TypeHelper::printType -> Result null with elem:" + element.toString());
        }

        return result;
    }

    public static boolean isCallbackClass(PsiClass clazz) {
        if (clazz == null) {
            return false;
        }
        if (clazz.isInterface() &&
                clazz.getAllFields().length == 0 &&
                clazz.getAllMethods().length == 1) {
            PsiDocComment comment = clazz.getDocComment();
            if (comment != null) {
                PsiDocTag[] tags = comment.getTags();
                for (PsiDocTag tag : tags) {
                    if (tag.getName().equals(NativeTsTranslator.TAG) &&
                            tag.getValueElement() != null &&
                            tag.getValueElement().getText().equals(NativeTsTranslator.TAG_VAL_TS_CALLBACK)) {
                        return true;
                    }
                }
            }
        } else {
            return false;
        }

        return false;
    }

    public static String primitiveStaticCall(String clazz) {
        if (clazz.equals("String")) {
            return "StringUtils";
        }
        String result = javaTypes.get(clazz);
        if (result != null) {
            return result;
        }
        return clazz;
    }


    public static final HashMap<String, String> javaTypes = new HashMap<String, String>();

    static {

        javaTypes.put("System", "java.lang.System");

        javaTypes.put("AtomicInteger", "java.util.concurrent.atomic.AtomicInteger");
        javaTypes.put("AtomicLong", "java.util.concurrent.atomic.AtomicLong");
        javaTypes.put("AtomicReference", "java.util.concurrent.atomic.AtomicReference");
        javaTypes.put("AtomicIntegerArray", "java.util.concurrent.atomic.AtomicIntegerArray");

        javaTypes.put("Arrays", "java.util.Arrays");
        javaTypes.put("Collections", "java.util.Collections");
        javaTypes.put("Map", "java.util.Map");
        javaTypes.put("Stack", "java.util.Stack");
        javaTypes.put("HashMap", "java.util.HashMap");
        javaTypes.put("List", "java.util.List");
        javaTypes.put("Set", "java.util.Set");
        javaTypes.put("HashSet", "java.util.HashSet");
        javaTypes.put("ArrayList", "java.util.ArrayList");
        javaTypes.put("LinkedList", "java.util.LinkedList");
        javaTypes.put("Random", "java.util.Random");

        javaTypes.put("Long", "java.lang.Long");
        javaTypes.put("Double", "java.lang.Double");
        javaTypes.put("Float", "java.lang.Float");
        javaTypes.put("Integer", "java.lang.Integer");
        javaTypes.put("Short", "java.lang.Short");
        javaTypes.put("Boolean", "java.lang.Boolean");
        javaTypes.put("StringBuilder", "java.lang.StringBuilder");

        javaTypes.put("Throwable", "java.lang.Throwable");
        javaTypes.put("Exception", "java.lang.Exception");
        javaTypes.put("Runnable", "java.lang.Runnable");
        javaTypes.put("RuntimeException", "java.lang.RuntimeException");
        javaTypes.put("IndexOutOfBoundsException", "java.lang.IndexOutOfBoundsException");
        javaTypes.put("WeakReference", "java.lang.ref.WeakReference");
    }

    public static final Set<String> primitiveNumbers = ImmutableSet.of("byte", "short", "int", "long", "float", "double");

    public static final Set<String> objectNumbers = ImmutableSet.of(
            Byte.class.getName(),
            Byte.class.getSimpleName(),
            Short.class.getName(),
            Short.class.getSimpleName(),
            Integer.class.getName(),
            Integer.class.getSimpleName(),
            Long.class.getName(),
            Long.class.getSimpleName(),
            Float.class.getName(),
            Float.class.getSimpleName(),
            Double.class.getName(),
            Double.class.getSimpleName(),
            BigInteger.class.getName(),
            BigInteger.class.getSimpleName(),
            BigDecimal.class.getName(),
            BigDecimal.class.getSimpleName()
    );

    public static final Set<String> strings = ImmutableSet.of(
            "char",
            Character.class.getName(),
            Character.class.getSimpleName(),
            String.class.getName(),
            String.class.getSimpleName()
    );

    public static final Set<String> booleans = ImmutableSet.of(
            "boolean",
            Boolean.class.getName(),
            Boolean.class.getSimpleName()
    );

    public static final Set<String> objects = ImmutableSet.of(
            Object.class.getName(),
            Object.class.getSimpleName()
    );

    public static final Set<String> classes = ImmutableSet.of(
            Class.class.getName(),
            Class.class.getSimpleName()
    );
}
