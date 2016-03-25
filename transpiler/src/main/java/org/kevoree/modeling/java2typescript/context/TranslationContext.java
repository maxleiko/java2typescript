
package org.kevoree.modeling.java2typescript.context;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiType;

import java.util.*;

public class TranslationContext {

    public boolean NATIVE_ARRAY = true;
    private static final int indentSize = 2;

    private StringBuilder sb = new StringBuilder();
    private int indent = 0;
    private PsiJavaFile file;
    private Set<String> javaClasses = new HashSet<>();
    private String srcPath;
    private List<ModuleImport> moduleImports = new ArrayList<>();
    private Map<String, String> pkgTransforms = new HashMap<>();
    private ArrayList<String> genericParameterNames;

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

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
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

    public void needsJava(String clazz) {
        if (this.javaClasses.isEmpty()) {
            ModuleImport java = new ModuleImport();
            java.setName("java2ts-java");
            java.importAll("* as java");
            this.addModuleImport(java);
        }
        this.javaClasses.add(clazz);
    }

    public Set<String> needsJava() {
        return this.javaClasses;
    }

    @Override
    public String toString() {
        StringBuilder importsBuilder = new StringBuilder();

        for (ModuleImport moduleImport : moduleImports) {
            importsBuilder.append(moduleImport.toString());
            importsBuilder.append("\n");
        }

        if (!moduleImports.isEmpty()) {
            importsBuilder.append("\n");
        }

        return importsBuilder.append(sb.toString()).toString();
    }

    public String getContent() {
        return sb.toString();
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

    public String getSrcPath() {
        return srcPath;
    }

    public void addModuleImport(ModuleImport moduleImport) {
        this.moduleImports.add(moduleImport);
    }

    public void addPackageTransform(String initialName, String newName) {
        this.pkgTransforms.put(initialName, newName);
    }

    public String packageTransform(String name) {
        int lastIndexOfDot = name.lastIndexOf(".");
        if (lastIndexOfDot >= 0) {
            String pkg = name.substring(0, lastIndexOfDot);
            String transform = this.pkgTransforms.get(pkg);
            if (transform != null) {
                if (transform.trim().isEmpty()) {
                    return name.substring(lastIndexOfDot + 1);
                } else {
                    return name.replace(pkg, transform);
                }
            }
        }
        return name;
    }

    public void setGenericParameterNames(ArrayList<String> genericParameterNames) {
        this.genericParameterNames = genericParameterNames;
    }

    public ArrayList<String> getGenericParameterNames() {
        return this.genericParameterNames;
    }

    public void removeGenericParameterNames() {
        this.genericParameterNames = null;
    }
}
