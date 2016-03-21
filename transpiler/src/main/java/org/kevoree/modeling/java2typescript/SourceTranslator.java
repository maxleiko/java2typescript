package org.kevoree.modeling.java2typescript;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.psi.*;
import org.kevoree.modeling.java2typescript.json.packagejson.PackageJson;
import org.kevoree.modeling.java2typescript.json.tsconfig.Atom;
import org.kevoree.modeling.java2typescript.json.tsconfig.CompilerOptions;
import org.kevoree.modeling.java2typescript.json.tsconfig.TsConfig;
import org.kevoree.modeling.java2typescript.translators.ClassTranslator;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SourceTranslator {

    private JavaAnalyzer analyzer;
    private String srcPath;
    private String outPath;
    private String name;
    private PsiElementVisitor visitor;
    private TranslationContext ctx;
    private TsConfig tsConfig;
    private PackageJson pkgJson;

    public SourceTranslator(String srcPath, String outPath, String name) {
        this.analyzer = new JavaAnalyzer();
        this.srcPath = srcPath;
        this.outPath = outPath;
        this.name = name;
        this.ctx = new TranslationContext(null, srcPath, outPath);
        this.tsConfig = new TsConfig();
        this.pkgJson = new PackageJson();
        initTsConfig();
        initPackageJson();
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

        if (!ctx.needsJava().isEmpty()) {
            pkgJson.addDependency("java2ts-java", "*");
        }
    }

    public void generate() {
        String[] testPath = new String[] { "src", "test", "test.ts" };
        String[] modelPath = new String[] { "src", "main", name+".ts" };
        File testFile = Paths.get(outPath, testPath).toFile();
        File modelFile = Paths.get(outPath, modelPath).toFile();
        try {
            FileUtil.writeToFile(modelFile, ctx.toString().getBytes());
            FileUtil.writeToFile(testFile, "// TODO add some tests".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        File tsConfigFile = Paths.get(outPath, "tsconfig.json").toFile();
        File pkgJsonFile = Paths.get(outPath, "package.json").toFile();
        // files
        List<URI> files = new ArrayList<>();
        files.add(URI.create(Paths.get("", modelPath).toString()));
        files.add(URI.create(Paths.get("", testPath).toString()));

        tsConfig.withFiles(files);
        tsConfig.withFilesGlob(Collections.singletonList(URI.create(Paths.get("src", "**", "*.ts").toString())));
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileUtil.writeToFile(tsConfigFile, gson.toJson(tsConfig, TsConfig.class).getBytes());
            FileUtil.writeToFile(pkgJsonFile, gson.toJson(pkgJson, PackageJson.class).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void visit(PsiDirectory dir) {
        ctx.enterPackage(dir.getName());
        dir.acceptChildren(visitor);
        ctx.leavePackage();
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

    private void initPackageJson() {
        pkgJson.setName(name);
        pkgJson.setMain(Paths.get("built", "main", name).toString());
        pkgJson.setTypings(Paths.get("built", "main", name+".d.ts").toString());
        pkgJson.addDevDependency("typescript", "1.8.9");
        pkgJson.addScript("build", "node node_modules/.bin/tsc");
    }

    private void initTsConfig() {
        tsConfig.setCompileOnSave(true);
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
        // atom
        Atom atom = new Atom();
        atom.setRewriteTsConfig(true);
        tsConfig.setAtom(atom);
    }

    public JavaAnalyzer getAnalyzer() {
        return this.analyzer;
    }

    public TsConfig getTsConfig() {
        return this.tsConfig;
    }

    public PackageJson getPkgJson() {
        return this.pkgJson;
    }
}
