
package org.kevoree.modeling.java2typescript.json.tsconfig;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompileOnSave {

    /**
     * Enable Compile-on-Save for this project.
     * 
     */
    @SerializedName("compileOnSave")
    @Expose
    private Boolean compileOnSave;

    /**
     * Enable Compile-on-Save for this project.
     * 
     * @return
     *     The compileOnSave
     */
    public Boolean getCompileOnSave() {
        return compileOnSave;
    }

    /**
     * Enable Compile-on-Save for this project.
     * 
     * @param compileOnSave
     *     The compileOnSave
     */
    public void setCompileOnSave(Boolean compileOnSave) {
        this.compileOnSave = compileOnSave;
    }

    public CompileOnSave withCompileOnSave(Boolean compileOnSave) {
        this.compileOnSave = compileOnSave;
        return this;
    }

}
