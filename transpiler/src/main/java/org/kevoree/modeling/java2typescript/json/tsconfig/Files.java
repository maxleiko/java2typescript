
package org.kevoree.modeling.java2typescript.json.tsconfig;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Files {

    /**
     * If no 'files' property is present in a tsconfig.json, the compiler defaults to including all files the containing directory and subdirectories. When a 'files' property is specified, only those files are included.
     * 
     */
    @SerializedName("files")
    @Expose
    private List<URI> files = new ArrayList<URI>();

    /**
     * If no 'files' property is present in a tsconfig.json, the compiler defaults to including all files the containing directory and subdirectories. When a 'files' property is specified, only those files are included.
     * 
     * @return
     *     The files
     */
    public List<URI> getFiles() {
        return files;
    }

    /**
     * If no 'files' property is present in a tsconfig.json, the compiler defaults to including all files the containing directory and subdirectories. When a 'files' property is specified, only those files are included.
     * 
     * @param files
     *     The files
     */
    public void setFiles(List<URI> files) {
        this.files = files;
    }

    public Files withFiles(List<URI> files) {
        this.files = files;
        return this;
    }

}
