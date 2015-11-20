package org.kevoree.modeling.java2typescript;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * Created by leiko on 20/11/15.
 */
public class ImportHelper {

    public static void importIfValid(PsiElement element, PsiElement resolution, TranslationContext ctx) {
        if (!element.getContainingFile().getVirtualFile().getPath().toString().equals(resolution.getContainingFile().getVirtualFile().getPath().toString())) {
            Path elemPath = Paths.get(element.getContainingFile().getParent().getVirtualFile().getPath());
            Path resolPath = Paths.get(resolution.getContainingFile().getVirtualFile().getPath());

            String pathToResol = elemPath.relativize(resolPath).toString();
            if (!pathToResol.isEmpty()) {
                ctx.addImport(((PsiClass) resolution).getName(), "./"+pathToResol.substring(0, pathToResol.lastIndexOf(".")));
            }
        }
    }
}
