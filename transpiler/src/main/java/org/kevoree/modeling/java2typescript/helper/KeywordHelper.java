package org.kevoree.modeling.java2typescript.helper;

import com.google.common.collect.Sets;
import org.kevoree.modeling.java2typescript.context.TranslationContext;

import java.util.Set;

public class KeywordHelper {

    public static final Set<String> reservedWords = Sets.newHashSet(
            "let", "var", "debugger", "constructor", "yield", "export", "with", "function", "typeof", "in");

    public static String process(String name, TranslationContext ctx) {
        if (reservedWords.contains(name)) {
            throw new IllegalArgumentException("Name \""+name+"\" in " + PathHelper.lastPart(ctx) +
                    " should not use TypeScript reserved keywords: " + reservedWords.toString());
        }
        return name;
    }
}
