package org.kevoree.modeling.java2typescript;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.psi.*;
import org.apache.commons.io.IOUtils;
import org.kevoree.modeling.java2typescript.helper.PathHelper;
import org.kevoree.modeling.java2typescript.json.tsconfig.CompilerOptions;
import org.kevoree.modeling.java2typescript.json.tsconfig.TsConfig;
import org.kevoree.modeling.java2typescript.translators.ClassTranslator;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SourceTranslator {

    private JavaAnalyzer analyzer;
    private String srcPath;
    private String outPath;
    private String name;
    private PsiElementVisitor visitor;
    private TranslationContext ctx;
    private TsConfig tsConfig;

    public SourceTranslator(String srcPath, String outPath, String name) {
        this.analyzer = new JavaAnalyzer();
        this.srcPath = srcPath;
        this.outPath = outPath;
        this.name = name;
        this.ctx = new TranslationContext(null, srcPath, outPath);
        this.tsConfig = new TsConfig();
        initTsConfig();
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
    }

    public void process() {
        File srcFolder = new File(this.srcPath);
        if (srcFolder.exists()) {
            if (srcFolder.isFile()) {
                throw new IllegalArgumentException("Source path is not a directory");
            }
        } else {
            throw new IllegalArgumentException("Source path "+srcPath+" does not exists");
        }
        File outFolder = new File(this.outPath);
        if (outFolder.exists()) {
            FileUtil.delete(outFolder);
        }
        outFolder.mkdirs();

        PsiDirectory root = analyzer.analyze(srcFolder);
        root.acceptChildren(visitor);
    }

    public void generate() {
        String[] modelPath = new String[] { "src", "main", name+".ts" };
        String[] javaPath = new String[] { "src", "main", "java.ts"};
        File modelFile = Paths.get(outPath, modelPath).toFile();
        File javaFile = Paths.get(outPath, javaPath).toFile();
        try {
            if (!ctx.needsJava().isEmpty()) {
                FileUtil.writeToFile(javaFile, IOUtils.toByteArray(getClass().getResourceAsStream("/java.ts")));
            }
            FileUtil.writeToFile(modelFile, ctx.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        File tsConfigFile = Paths.get(outPath, "tsconfig.json").toFile();
        // files
        List<URI> files = new ArrayList<>();
        files.add(URI.create(Paths.get("", modelPath).toString()));
        if (!ctx.needsJava().isEmpty()) {
            files.add(URI.create(Paths.get("", javaPath).toString()));
        }
        tsConfig.withFiles(files);
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileUtil.writeToFile(tsConfigFile, gson.toJson(tsConfig, TsConfig.class).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void visit(PsiDirectory dir) {
        ctx.print("export namespace ");
        ctx.append(dir.getName());
        ctx.append(" {\n");
        ctx.increaseIdent();
        dir.acceptChildren(visitor);
        ctx.decreaseIdent();
        ctx.print("}\n");
    }

    private void visit(PsiJavaFile file) {
        ctx.setFile(file);
        file.acceptChildren(new PsiElementVisitor() {
            @Override
            public void visitElement(PsiElement element) {
                if (element instanceof PsiClass) {
                    ClassTranslator.translate((PsiClass) element, ctx);
                }
            }
        });
    }

    private void visit(PsiElement elem) {
        System.out.println("Unknown file= "+elem);
    }

    private void initTsConfig() {
        // compilerOptions
        CompilerOptions compilerOptions = new CompilerOptions();
        compilerOptions.setTarget(CompilerOptions.Target.ES_5);
        compilerOptions.setModule(CompilerOptions.Module.COMMONJS);
        compilerOptions.setModuleResolution(CompilerOptions.ModuleResolution.NODE);
        compilerOptions.setExperimentalDecorators(true);
        compilerOptions.setEmitDecoratorMetadata(true);
        compilerOptions.setNoImplicitAny(true);
        compilerOptions.setDeclaration(true);
        compilerOptions.setRemoveComments(true);
        compilerOptions.setPreserveConstEnums(true);
        compilerOptions.setSuppressImplicitAnyIndexErrors(true);
        compilerOptions.setOutDir(URI.create("built"));
        tsConfig.setCompilerOptions(compilerOptions);
    }

    public JavaAnalyzer getAnalyzer() {
        return this.analyzer;
    }

    public TsConfig getTsConfig() {
        return this.tsConfig;
    }
}
