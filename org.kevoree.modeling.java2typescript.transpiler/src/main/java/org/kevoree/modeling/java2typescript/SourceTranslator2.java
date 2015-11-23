package org.kevoree.modeling.java2typescript;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.psi.*;
import org.kevoree.modeling.java2typescript.translators.ClassTranslator;

import java.io.File;
import java.io.IOException;

public class SourceTranslator2 {

    private JavaAnalyzer analyzer;
    private String name;
    private String srcPath;
    private String outPath;
    private PsiElementVisitor visitor;

    public SourceTranslator2(String name, String srcPath, String outPath) {
        analyzer = new JavaAnalyzer();
        this.name = name;
        this.srcPath = srcPath;
        this.outPath = outPath;
    }

    public void visit() {
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
    }

    private void visit(PsiDirectory dir) {
        createDirectory(dir);
        dir.acceptChildren(visitor);
    }

    private void visit(PsiJavaFile file) {
        String path = PathHelper.getPath(srcPath, outPath, file);
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

        try {
            FileUtil.writeToFile(tsFile, ctx.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void visit(PsiClass clazz) {
        System.out.println("Class "+clazz.getName());
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
