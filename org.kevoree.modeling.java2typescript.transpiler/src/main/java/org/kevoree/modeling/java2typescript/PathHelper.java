package org.kevoree.modeling.java2typescript;

import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;

import java.io.File;

/**
 *
 * Created by leiko on 20/11/15.
 */
public class PathHelper {

    public static String getPath(String srcPath, String outPath, PsiDirectory dir) {
        return outPath + dir.getVirtualFile().getPath().substring(srcPath.length());
    }

    public static String getPath(TranslationContext ctx, PsiDirectory dir) {
        return getPath(ctx.getSrcPath(), ctx.getOutPath(), dir);
    }

    public static String getPath(String srcPath, String outPath, PsiJavaFile file) {
        return getPath(srcPath, outPath, file.getParent()) + File.separator + file.getName().replaceAll("\\.java$", ".ts");
    }

    public static String getPath(TranslationContext ctx, PsiJavaFile file) {
        return getPath(ctx.getSrcPath(), ctx.getOutPath(), file);
    }
}
