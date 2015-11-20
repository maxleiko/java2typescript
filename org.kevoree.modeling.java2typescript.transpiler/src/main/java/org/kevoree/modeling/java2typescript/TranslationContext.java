
package org.kevoree.modeling.java2typescript;

import java.util.*;

public class TranslationContext {

    public boolean NATIVE_ARRAY = true;

    private StringBuilder sb = new StringBuilder();
    private static final int identSize = 4;
    private int ident = 0;
    private String srcPath;
    private String outPath;
    private Map<String, Set<String>> imports = new HashMap<>();

    public TranslationContext() {}

    public TranslationContext(String srcPath, String outPath) {
        this.srcPath = srcPath;
        this.outPath = outPath;
    }

    public void increaseIdent() {
        ident += identSize;
    }

    public void decreaseIdent() {
        ident -= identSize;
        if (ident < 0) {
            throw new IllegalStateException("Decrease ident was called more times than increase");
        }
    }

    public TranslationContext print(String str) {
        ident();
        sb.append(str);
        return this;
    }

    public TranslationContext print(char str) {
        ident();
        sb.append(str);
        return this;
    }

    public TranslationContext addImport(String clazz, String file) {
        Set<String> classes = this.imports.get(file);
        if (classes == null) {
            classes = new HashSet<>();
            this.imports.put(file, classes);
        }
        classes.add(clazz);
        return this;
    }

    public TranslationContext ident() {
        for (int i = 0; i < ident; i++) {
            sb.append(' ');
        }
        return this;
    }

    public TranslationContext append(String str) {
        sb.append(str);
        return this;
    }

    public TranslationContext append(char c) {
        sb.append(c);
        return this;
    }

    public String getSrcPath() {
        return srcPath;
    }

    @Override
    public String toString() {
        if (!this.imports.isEmpty()) {
            StringBuilder imports = new StringBuilder();
            imports.append("import { ");
            Iterator<String> fileIt = this.imports.keySet().iterator();
            while (fileIt.hasNext()) {
                String file = fileIt.next();
                Iterator<String> it = this.imports.get(file).iterator();
                while (it.hasNext()) {
                    imports.append(it.next());
                    if (it.hasNext()) {
                        imports.append(", ");
                    }
                }
                imports.append(" } from '");
                imports.append(file);
                imports.append("';\n");
                if (fileIt.hasNext()) {
                    imports.append("import { ");
                }
            }
            imports.append("\n");
            imports.append(sb);
            return imports.toString();
        } else {
            return sb.toString();
        }
    }

    public String getOutPath() {
        return outPath;
    }
}
