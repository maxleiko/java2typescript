package org.kevoree.modeling.java2typescript;

import com.intellij.core.JavaCoreApplicationEnvironment;
import com.intellij.core.JavaCoreProjectEnvironment;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiManager;

import java.io.File;

/**
 *
 * Created by duke on 11/6/14.
 */
public class JavaAnalyzer {

    private JavaCoreProjectEnvironment environment;

    public JavaAnalyzer() {
        environment = new JavaCoreProjectEnvironment(() -> {}, new JavaCoreApplicationEnvironment(() -> {}));
    }

    public void addClasspath(String filePath) {
        environment.addJarToClassPath(new File(filePath));
    }

    public PsiDirectory analyze(File srcDir) {
        VirtualFile vf = environment.getEnvironment().getLocalFileSystem().findFileByIoFile(srcDir);
        if (vf != null) {
            environment.addSourcesToClasspath(vf);
            return PsiManager.getInstance(environment.getProject()).findDirectory(vf);
        }
        return null;
    }
}
