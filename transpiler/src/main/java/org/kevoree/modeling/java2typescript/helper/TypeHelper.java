
package org.kevoree.modeling.java2typescript.helper;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiClassReferenceType;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.kevoree.modeling.java2typescript.context.TranslationContext;
import org.kevoree.modeling.java2typescript.metas.DocMeta;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TypeHelper {

    public static String printType(PsiType element, TranslationContext ctx) {
        return printType(element, ctx, true, false);
    }


    public static String printType(PsiType element, TranslationContext ctx, boolean withGenericParams, boolean avoidNativeOptim) {
        String result = element.getPresentableText();

        if (result.equals("Throwable") || result.equals("Exception") || result.equals("RuntimeException") || result.equals("IndexOutOfBoundsException")) {
            return "Error";
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
            if (result.equals("null")) {
                System.err.println("TypeHelper::printType -> Result null with elem:" + element.toString());
            }
            return result;
        } else if (element instanceof PsiArrayType) {
            PsiArrayType typedElement = (PsiArrayType) element;
            String partialResult = printType(typedElement.getComponentType(), ctx, withGenericParams, avoidNativeOptim);
            if (withGenericParams) {
                result = partialResult + "[]";
            } else {
                result = partialResult;
            }
            return result;
        } else if (element instanceof PsiClassReferenceType) {
            PsiClassReferenceType elementClassRefType = ((PsiClassReferenceType) element);
            PsiClass resolvedClass = elementClassRefType.resolve();
            if (((PsiClassReferenceType) element).getClassName().startsWith("Class")) {
                // "Class" concept does not exists in TypeScript => any
                result = "any";
            } else {
                if (resolvedClass != null) {
                    if (resolvedClass.getQualifiedName() == null) {
                        result = resolvedClass.getName();
                    } else {
                        result = resolvedClass.getQualifiedName();
                        result += GenericHelper.process(elementClassRefType, ctx, withGenericParams);
                    }
                } else {
                    String tryJavaUtil = javaTypes.get(elementClassRefType.getClassName());
                    if (tryJavaUtil != null) {
                        ctx.needsJava(tryJavaUtil);
                        result = tryJavaUtil;
                    } else {
                        result = elementClassRefType.getReference().getQualifiedName();
                    }
                    result += GenericHelper.process(elementClassRefType, ctx, withGenericParams);
                }
            }
        } else if (element instanceof PsiWildcardType) {
            PsiType bound = ((PsiWildcardType) element).getBound();
            if (bound != null) {
                result = "T";
            } else {
                result = "any";
            }
        } else {
            System.out.println("TypeHelper: unhandled type -> " + element);
        }

        if (result.equals("null")) {
            // this is kind of desperate but well...
            result = element.getPresentableText();
        }

        return ctx.packageTransform(result);
    }

    public static boolean isCallbackClass(PsiClass clazz) {
        if (clazz == null) {
            return false;
        }
        DocMeta metas = DocHelper.process(clazz.getDocComment());
        return metas.functionType;
    }

    public static String primitiveStaticCall(String clazz, TranslationContext ctx) {
        String result = javaTypes.get(clazz);
        if (result != null) {
            ctx.needsJava(result);
            return result;
        }
        return KeywordHelper.process(clazz, ctx);
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
        javaTypes.put("Iterator", "java.util.Iterator");
        javaTypes.put("ListIterator", "java.util.ListIterator");

        javaTypes.put("Long", "java.lang.Long");
        javaTypes.put("Double", "java.lang.Double");
        javaTypes.put("Float", "java.lang.Float");
        javaTypes.put("Integer", "java.lang.Integer");
        javaTypes.put("Short", "java.lang.Short");
        javaTypes.put("Boolean", "java.lang.Boolean");
        javaTypes.put("String", "java.lang.String");
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
            BigDecimal.class.getSimpleName(),
            Number.class.getName(),
            Number.class.getSimpleName()
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

    private static final String genericLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String availableGenericType(HashSet<String> usedGenerics) {
        if (usedGenerics.isEmpty()) {
            return "A";
        } else {
            return availableGenericType(usedGenerics, 0);
        }
    }

    private static String availableGenericType(HashSet<String> usedGenerics, int count) {
        if (count == genericLetters.length() - 1) {
            throw new UnsupportedOperationException("Unable to find a suitable generic type character");
        } else {
            String genType = genericLetters.substring(count, count+1);
            if (!usedGenerics.contains(genType)) {
                return genType;
            } else {
                return availableGenericType(usedGenerics, count+1);
            }
        }
    }
}
