
package org.kevoree.modeling.java2typescript.json.tsconfig;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * JSON schema for the TypeScript compiler's configuration file
 */
@Generated("org.jsonschema2pojo")
public class TsConfig {

    /**
     * Instructs the TypeScript compiler how to compile .ts files
     *
     */
    @SerializedName("compilerOptions")
    @Expose
    private CompilerOptions compilerOptions;

    /**
     * The 'exclude' property cannot be used in conjunction with the 'files' property. If both are specified then the 'files' property takes precedence.
     * 
     */
    @SerializedName("exclude")
    @Expose
    private List<URI> exclude = new ArrayList<URI>();

    @SerializedName("filesGlob")
    @Expose
    private List<URI> filesGlob = new ArrayList<URI>();

    /**
     * Enable Compile-on-Save for this project.
     * 
     */
    @SerializedName("compileOnSave")
    @Expose
    private Boolean compileOnSave;

    @SerializedName("atom")
    @Expose
    private Atom atom;

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

    public TsConfig withFiles(List<URI> files) {
        this.files.addAll(files);
        return this;
    }

    public List<URI> getFilesGlob() {
        return filesGlob;
    }

    public void setFilesGlob(List<URI> files) {
        this.filesGlob = files;
    }

    public TsConfig withFilesGlob(List<URI> files) {
        this.filesGlob.addAll(files);
        return this;
    }

    /**
     * The 'exclude' property cannot be used in conjunction with the 'files' property. If both are specified then the 'files' property takes precedence.
     * 
     * @return
     *     The exclude
     */
    public List<URI> getExclude() {
        return exclude;
    }

    /**
     * The 'exclude' property cannot be used in conjunction with the 'files' property. If both are specified then the 'files' property takes precedence.
     * 
     * @param exclude
     *     The exclude
     */
    public void setExclude(List<URI> exclude) {
        this.exclude = exclude;
    }

    public TsConfig withExclude(List<URI> exclude) {
        this.exclude.addAll(exclude);
        return this;
    }

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

    public TsConfig withCompileOnSave(Boolean compileOnSave) {
        this.compileOnSave = compileOnSave;
        return this;
    }

    /**
     * Instructs the TypeScript compiler how to compile .ts files
     * 
     * @return
     *     The compilerOptions
     */
    public CompilerOptions getCompilerOptions() {
        return compilerOptions;
    }

    /**
     * Instructs the TypeScript compiler how to compile .ts files
     * 
     * @param compilerOptions
     *     The compilerOptions
     */
    public void setCompilerOptions(CompilerOptions compilerOptions) {
        this.compilerOptions = compilerOptions;
    }

    public TsConfig withCompilerOptions(CompilerOptions compilerOptions) {
        this.compilerOptions = compilerOptions;
        return this;
    }

    public void setAtom(Atom atom) {
        this.atom = atom;
    }
}
