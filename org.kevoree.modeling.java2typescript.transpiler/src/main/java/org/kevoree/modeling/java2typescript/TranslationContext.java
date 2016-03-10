
package org.kevoree.modeling.java2typescript;

import com.intellij.psi.PsiJavaFile;

import java.util.*;

/**
 * TranslationContext are a per-file contex, which allows to reason on
 * the wanted generated output
 */
public class TranslationContext {

    public boolean NATIVE_ARRAY = true;

    private StringBuilder sb = new StringBuilder();
    private static final int identSize = 2;
    private int indent = 0;
    private PsiJavaFile file;
    private String srcPath;
    private String outPath;
    private Map<String, Set<String>> imports = new HashMap<>();
    private Map<String, Set<String>> reverseImports = new HashMap<>();
    private Map<String, String> generatedNames = new HashMap<>();
    private Set<String> javaClasses = new HashSet<>();

    public TranslationContext(PsiJavaFile file, String srcPath, String outPath) {
        this.file = file;
        this.srcPath = srcPath;
        this.outPath = outPath;
    }

    public void increaseIdent() {
        indent += identSize;
    }

    public void decreaseIdent() {
        indent -= identSize;
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

    public TranslationContext addImport(String clazz, String fromFile) {
        Set<String> classes = this.imports.get(fromFile);
        if (classes == null) {
            classes = new HashSet<>();
            this.imports.put(fromFile, classes);
        }

        Set<String> fromFiles = this.reverseImports.get(clazz);
        if (fromFiles == null) {
            fromFiles = new HashSet<>();
            this.reverseImports.put(clazz, fromFiles);
        }

        if (!fromFiles.isEmpty() && (!fromFiles.contains(fromFile) && fromFiles.size() == 1)) {
            String generatedName = clazz + (fromFiles.size() - 1);
            this.generatedNames.put(clazz+"_"+fromFile, generatedName);
            clazz = clazz + " as " + generatedName;
        }

        fromFiles.add(fromFile);
        classes.add(clazz);

        return this;
    }

    public String getImportGeneratedName(String clazz, String fromFile) {
        return this.generatedNames.get(clazz + "_" + fromFile);
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
            String javaImport = "import * as java from './java';";
            if (this.imports.isEmpty()) {
                sb.insert(0, javaImport+"\n");
            } else {
                sb.insert(0, javaImport);
            }
        }

        if (!this.imports.isEmpty()) {
            StringBuilder imports = new StringBuilder();
            imports.append("import ");
            Iterator<String> fileIt = this.imports.keySet().iterator();
            while (fileIt.hasNext()) {
                String file = fileIt.next();
                Iterator<String> it = this.imports.get(file).iterator();
                if (this.imports.get(file).size() == 1) {
                    String toImport = it.next();
                    if (toImport.contains("*")) {
                        imports.append(toImport);
                    } else {
                        imports.append("{ ");
                        imports.append(toImport);
                        imports.append(" }");
                    }
                } else {
                    imports.append("{ ");
                    while (it.hasNext()) {
                        imports.append(it.next());
                        if (it.hasNext()) {
                            imports.append(", ");
                        }
                    }
                    imports.append(" }");
                }

                imports.append(" from '");
                imports.append(file);
                imports.append("';\n");
                if (fileIt.hasNext()) {
                    imports.append("import ");
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

    private TranslationContext indent() {
        for (int i = 0; i < indent; i++) {
            sb.append(' ');
        }
        return this;
    }
}
