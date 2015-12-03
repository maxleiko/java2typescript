package org.kevoree.modeling.java2typescript;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.psi.*;
import org.kevoree.modeling.java2typescript.helper.PathHelper;
import org.kevoree.modeling.java2typescript.translators.ClassTranslator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class SourceTranslator {

    private JavaAnalyzer analyzer;
    private String srcPath;
    private String outPath;
    private PsiElementVisitor visitor;
    private Set<String> exports = new HashSet<>();
    private Set<String> javaClasses = new HashSet<>();

    public SourceTranslator(String srcPath, String outPath) {
        analyzer = new JavaAnalyzer();
        this.srcPath = srcPath;
        this.outPath = outPath;
    }

    public void process() {
        File srcFolder = new File(this.srcPath);
        File outFolder = new File(this.outPath);
        if (srcFolder.exists()) {
            if (srcFolder.isFile()) {
                throw new IllegalArgumentException("Source path is not a directory");
            }
        } else {
            throw new IllegalArgumentException("Source path "+srcPath+" does not exists");
        }
        if (outFolder.exists()) {
            FileUtil.delete(outFolder);
        }
        outFolder.mkdirs();

        PsiDirectory root = analyzer.analyze(srcFolder);
        this.visitor = new PsiElementVisitor() {
            @Override
            public void visitElement(PsiElement element) {
                if (element instanceof PsiDirectory) {
                    visit((PsiDirectory) element);

                } else if (element instanceof PsiJavaFile) {
                    visit((PsiJavaFile) element);

                } else {
                    visit(element);
                }
            }
        };
        root.acceptChildren(visitor);

        if (!this.javaClasses.isEmpty()) {
            System.out.println("Your code needs \"java\" as dependency");
            System.out.println("Because of:");
            for (String clazz: this.javaClasses) {
                System.out.println("  "+clazz);
            }
            System.out.println();
            System.out.println("You can install it with npm:");
            System.out.println("  npm i kmf-java");
            System.out.println();
        }
    }

    public void genExportAllFile(String name) {
        if (!name.endsWith(".ts")) {
            name += ".ts";
        }
        File declFile = new File(outPath + File.separator + name);
        StringBuilder exports = new StringBuilder();
        Iterator<String> it = this.exports.iterator();
        while (it.hasNext()) {
            exports.append("export * from '.");
            exports.append(it.next());
            exports.append("';");
            if (it.hasNext()) {
                exports.append("\n");
            }
        }
        try {
            FileUtil.writeToFile(declFile, exports.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void visit(PsiDirectory dir) {
        createDirectory(dir);
        dir.acceptChildren(visitor);
    }

    private void visit(PsiJavaFile file) {
        String path = PathHelper.getPath(srcPath, outPath, file);
        exports.add(path.substring(outPath.length()));
        File tsFile = new File(path);

        TranslationContext ctx = new TranslationContext(file, this.srcPath, this.outPath);
        file.acceptChildren(new PsiElementVisitor() {
            @Override
            public void visitElement(PsiElement element) {
                if (element instanceof PsiClass) {
                    ClassTranslator.translate((PsiClass) element, ctx);
                }
            }
        });

        this.javaClasses.addAll(ctx.needsJava());

        try {
            FileUtil.writeToFile(tsFile, ctx.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void visit(PsiElement elem) {
        System.out.println("Unknown file= "+elem);
    }

    private void createDirectory(PsiDirectory dir) {
        String path = PathHelper.getPath(srcPath, outPath, dir);
        File dirFile = new File(path);
        if (!dirFile.exists() && !dirFile.isFile()) {
            dirFile.mkdirs();
        }
    }
}
