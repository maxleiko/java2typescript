package org.kevoree.modeling.java2typescript.helper;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import org.kevoree.modeling.java2typescript.TranslationContext;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * Created by leiko on 20/11/15.
 */
public class ImportHelper {

    public static void importIfValid(PsiElement resolution, TranslationContext ctx) {
//        if (resolution != null) {
//            if (!ctx.getFile().getVirtualFile().getPath().equals(resolution.getContainingFile().getVirtualFile().getPath())) {
//                Path elemPath = Paths.get(ctx.getFile().getParent().getVirtualFile().getPath());
//                Path resolPath = Paths.get(resolution.getContainingFile().getVirtualFile().getPath());
//
//                String pathToResol = elemPath.relativize(resolPath).toString();
//                if (!pathToResol.isEmpty()) {
//                    ctx.addImport(((PsiClass) resolution).getName(), "./"+pathToResol.substring(0, pathToResol.lastIndexOf(".")));
//                }
//            }
//        }
    }

    public static String getGeneratedName(PsiElement resolution, TranslationContext ctx) {
//        if (resolution != null) {
//            if (!ctx.getFile().getVirtualFile().getPath().equals(resolution.getContainingFile().getVirtualFile().getPath())) {
//                Path elemPath = Paths.get(ctx.getFile().getParent().getVirtualFile().getPath());
//                Path resolPath = Paths.get(resolution.getContainingFile().getVirtualFile().getPath());
//
//                String pathToResol = elemPath.relativize(resolPath).toString();
//                if (!pathToResol.isEmpty()) {
//                    return ctx.getImportGeneratedName(((PsiClass) resolution).getName(), "./"+pathToResol.substring(0, pathToResol.lastIndexOf(".")));
//                }
//            }
//        }

        return null;
    }
}
