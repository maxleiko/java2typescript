
package org.kevoree.modeling.java2typescript;

import com.intellij.psi.PsiJavaFile;

import java.util.*;

public class TranslationContext {

    public boolean NATIVE_ARRAY = true;
    private static final int indentSize = 2;

    private StringBuilder sb = new StringBuilder();
    private int indent = 0;
    private PsiJavaFile file;
    private String srcPath;
    private String outPath;
    private Set<String> javaClasses = new HashSet<>();

    public TranslationContext(PsiJavaFile file, String srcPath, String outPath) {
        this.file = file;
        this.srcPath = srcPath;
        this.outPath = outPath;
    }

    public void increaseIdent() {
        indent += indentSize;
    }

    public void decreaseIdent() {
        indent -= indentSize;
        if (indent < 0) {
            throw new IllegalStateException("Decrease indent was called more times than increase");
        }
    }

    public TranslationContext print(String str) {
        indent();
        sb.append(str);
        return this;
    }

    public TranslationContext print(char str) {
        indent();
        sb.append(str);
        return this;
    }

    public void setFile(PsiJavaFile file) {
        this.file = file;
    }

    public PsiJavaFile getFile() {
        return file;
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

    public void needsJava(String clazz) {
        this.javaClasses.add(clazz);
    }

    public Set<String> needsJava() {
        return this.javaClasses;
    }

    @Override
    public String toString() {
        if (!this.javaClasses.isEmpty()) {
            String javaImport = "import * as java from 'java2ts-java';\n\n";
            sb.insert(0, javaImport);
        }

        return sb.toString();
    }

    public String getOutPath() {
        return outPath;
    }

    private TranslationContext indent() {
        for (int i = 0; i < indent; i++) {
            sb.append(' ');
        }
        return this;
    }

    public void enterPackage(String pkgName) {
        this.print("export namespace ");
        this.append(pkgName);
        this.append(" {\n");
        this.increaseIdent();
    }

    public void leavePackage() {
        this.decreaseIdent();
        this.print("}\n");
    }
}
