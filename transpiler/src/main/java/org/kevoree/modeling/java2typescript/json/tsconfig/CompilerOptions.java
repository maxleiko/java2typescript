
package org.kevoree.modeling.java2typescript.json.tsconfig;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Instructs the TypeScript compiler how to compile .ts files
 * 
 */
@Generated("org.jsonschema2pojo")
public class CompilerOptions {

    /**
     * The character set of the input files.
     * 
     */
    @SerializedName("charset")
    @Expose
    private String charset;
    /**
     * Generates corresponding d.ts files.
     * 
     */
    @SerializedName("declaration")
    @Expose
    private Boolean declaration;
    /**
     * Show diagnostic information.
     * 
     */
    @SerializedName("diagnostics")
    @Expose
    private Boolean diagnostics;
    /**
     * Emit a UTF-8 Byte Order Mark (BOM) in the beginning of output files.
     * 
     */
    @SerializedName("emitBOM")
    @Expose
    private Boolean emitBOM;
    /**
     * Emit a single file with source maps instead of having a separate file.
     * 
     */
    @SerializedName("inlineSourceMap")
    @Expose
    private Boolean inlineSourceMap;
    /**
     * Emit the source alongside the sourcemaps within a single file; requires --inlineSourceMap to be set.
     * 
     */
    @SerializedName("inlineSources")
    @Expose
    private Boolean inlineSources;
    /**
     * Specify JSX code generation: 'preserve' or 'react'.
     * 
     */
    @SerializedName("jsx")
    @Expose
    private CompilerOptions.Jsx jsx;
    /**
     * Specifies the object invoked for createElement and __spread when targeting 'react' JSX emit.
     * 
     */
    @SerializedName("reactNamespace")
    @Expose
    private String reactNamespace;
    /**
     * Print names of files part of the compilation.
     * 
     */
    @SerializedName("listFiles")
    @Expose
    private Boolean listFiles;
    /**
     * The locale to use to show error messages, e.g. en-us.
     * 
     */
    @SerializedName("locale")
    @Expose
    private String locale;
    /**
     * Specifies the location where debugger should locate map files instead of generated locations
     * 
     */
    @SerializedName("mapRoot")
    @Expose
    private URI mapRoot;
    /**
     * Specify module code generation: 'CommonJS', 'Amd', 'System', 'UMD', 'es6', or 'es2015'.
     * 
     */
    @SerializedName("module")
    @Expose
    private CompilerOptions.Module module;
    /**
     * Specifies the end of line sequence to be used when emitting files: 'CRLF' (dos) or 'LF' (unix).
     * 
     */
    @SerializedName("newLine")
    @Expose
    private CompilerOptions.NewLine newLine;
    /**
     * Do not emit output.
     * 
     */
    @SerializedName("noEmit")
    @Expose
    private Boolean noEmit;
    /**
     * Do not generate custom helper functions like __extends in compiled output.
     * 
     */
    @SerializedName("noEmitHelpers")
    @Expose
    private Boolean noEmitHelpers;
    /**
     * Do not emit outputs if any type checking errors were reported.
     * 
     */
    @SerializedName("noEmitOnError")
    @Expose
    private Boolean noEmitOnError;
    /**
     * Warn on expressions and declarations with an implied 'any' type.
     * 
     */
    @SerializedName("noImplicitAny")
    @Expose
    private Boolean noImplicitAny;
    /**
     * Do not include the default library file (lib.d.ts).
     * 
     */
    @SerializedName("noLib")
    @Expose
    private Boolean noLib;
    /**
     *   Do not add triple-slash references or module import targets to the list of compiled files.
     * 
     */
    @SerializedName("noResolve")
    @Expose
    private Boolean noResolve;
    @SerializedName("skipDefaultLibCheck")
    @Expose
    private Boolean skipDefaultLibCheck;
    /**
     * Concatenate and emit output to single file.
     * 
     */
    @SerializedName("outFile")
    @Expose
    private URI outFile;
    /**
     * Redirect output structure to the directory.
     * 
     */
    @SerializedName("outDir")
    @Expose
    private URI outDir;
    /**
     * Do not erase const enum declarations in generated code.
     * 
     */
    @SerializedName("preserveConstEnums")
    @Expose
    private Boolean preserveConstEnums;
    /**
     * Stylize errors and messages using color and context. (experimental)
     * 
     */
    @SerializedName("pretty")
    @Expose
    private Boolean pretty;
    /**
     * Do not emit comments to output.
     * 
     */
    @SerializedName("removeComments")
    @Expose
    private Boolean removeComments;
    /**
     * Specifies the root directory of input files. Use to control the output directory structure with --outDir.
     * 
     */
    @SerializedName("rootDir")
    @Expose
    private URI rootDir;
    /**
     * Unconditionally emit imports for unresolved files.
     * 
     */
    @SerializedName("isolatedModules")
    @Expose
    private Boolean isolatedModules;
    /**
     * Generates corresponding '.map' file.
     * 
     */
    @SerializedName("sourceMap")
    @Expose
    private Boolean sourceMap;
    /**
     * Specifies the location where debugger should locate TypeScript files instead of source locations.
     * 
     */
    @SerializedName("sourceRoot")
    @Expose
    private URI sourceRoot;
    /**
     * Suppress excess property checks for object literals.
     * 
     */
    @SerializedName("suppressExcessPropertyErrors")
    @Expose
    private Boolean suppressExcessPropertyErrors;
    /**
     * Suppress noImplicitAny errors for indexing objects lacking index signatures.
     * 
     */
    @SerializedName("suppressImplicitAnyIndexErrors")
    @Expose
    private Boolean suppressImplicitAnyIndexErrors;
    /**
     * Do not emit declarations for code that has an '@internal' annotation.
     * 
     */
    @SerializedName("stripInternal")
    @Expose
    private Boolean stripInternal;
    /**
     * Specify ECMAScript target version.
     * 
     */
    @SerializedName("target")
    @Expose
    private CompilerOptions.Target target = CompilerOptions.Target.fromValue("es3");
    /**
     * Watch input files.
     * 
     */
    @SerializedName("watch")
    @Expose
    private Boolean watch;
    /**
     * Enables experimental support for ES7 decorators.
     * 
     */
    @SerializedName("experimentalDecorators")
    @Expose
    private Boolean experimentalDecorators;
    /**
     * Emit design-type metadata for decorated declarations in source.
     * 
     */
    @SerializedName("emitDecoratorMetadata")
    @Expose
    private Boolean emitDecoratorMetadata;
    /**
     * Specifies module resolution strategy: 'node' (Node) or 'classic' (TypeScript pre 1.6) .
     * 
     */
    @SerializedName("moduleResolution")
    @Expose
    private CompilerOptions.ModuleResolution moduleResolution = CompilerOptions.ModuleResolution.fromValue("classic");
    /**
     * Do not report errors on unused labels.
     * 
     */
    @SerializedName("allowUnusedLabels")
    @Expose
    private Boolean allowUnusedLabels;
    /**
     * Report error when not all code paths in function return a value.
     * 
     */
    @SerializedName("noImplicitReturns")
    @Expose
    private Boolean noImplicitReturns;
    /**
     * Report errors for fallthrough cases in switch statement.
     * 
     */
    @SerializedName("noFallthroughCasesInSwitch")
    @Expose
    private Boolean noFallthroughCasesInSwitch;
    /**
     * Do not report errors on unreachable code.
     * 
     */
    @SerializedName("allowUnreachableCode")
    @Expose
    private Boolean allowUnreachableCode;
    /**
     * Disallow inconsistently-cased references to the same file.
     * 
     */
    @SerializedName("forceConsistentCasingInFileNames")
    @Expose
    private Boolean forceConsistentCasingInFileNames;
    /**
     * Allow default imports from modules with no default export. This does not affect code emit, just typechecking.
     * 
     */
    @SerializedName("allowSyntheticDefaultImports")
    @Expose
    private Boolean allowSyntheticDefaultImports;
    /**
     * Allow javascript files to be compiled.
     * 
     */
    @SerializedName("allowJs")
    @Expose
    private Boolean allowJs;
    /**
     * Do not emit "use strict" directives in module output.
     * 
     */
    @SerializedName("noImplicitUseStrict")
    @Expose
    private Boolean noImplicitUseStrict;

    /**
     * The character set of the input files.
     * 
     * @return
     *     The charset
     */
    public String getCharset() {
        return charset;
    }

    /**
     * The character set of the input files.
     * 
     * @param charset
     *     The charset
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    public CompilerOptions withCharset(String charset) {
        this.charset = charset;
        return this;
    }

    /**
     * Generates corresponding d.ts files.
     * 
     * @return
     *     The declaration
     */
    public Boolean getDeclaration() {
        return declaration;
    }

    /**
     * Generates corresponding d.ts files.
     * 
     * @param declaration
     *     The declaration
     */
    public void setDeclaration(Boolean declaration) {
        this.declaration = declaration;
    }

    public CompilerOptions withDeclaration(Boolean declaration) {
        this.declaration = declaration;
        return this;
    }

    /**
     * Show diagnostic information.
     * 
     * @return
     *     The diagnostics
     */
    public Boolean getDiagnostics() {
        return diagnostics;
    }

    /**
     * Show diagnostic information.
     * 
     * @param diagnostics
     *     The diagnostics
     */
    public void setDiagnostics(Boolean diagnostics) {
        this.diagnostics = diagnostics;
    }

    public CompilerOptions withDiagnostics(Boolean diagnostics) {
        this.diagnostics = diagnostics;
        return this;
    }

    /**
     * Emit a UTF-8 Byte Order Mark (BOM) in the beginning of output files.
     * 
     * @return
     *     The emitBOM
     */
    public Boolean getEmitBOM() {
        return emitBOM;
    }

    /**
     * Emit a UTF-8 Byte Order Mark (BOM) in the beginning of output files.
     * 
     * @param emitBOM
     *     The emitBOM
     */
    public void setEmitBOM(Boolean emitBOM) {
        this.emitBOM = emitBOM;
    }

    public CompilerOptions withEmitBOM(Boolean emitBOM) {
        this.emitBOM = emitBOM;
        return this;
    }

    /**
     * Emit a single file with source maps instead of having a separate file.
     * 
     * @return
     *     The inlineSourceMap
     */
    public Boolean getInlineSourceMap() {
        return inlineSourceMap;
    }

    /**
     * Emit a single file with source maps instead of having a separate file.
     * 
     * @param inlineSourceMap
     *     The inlineSourceMap
     */
    public void setInlineSourceMap(Boolean inlineSourceMap) {
        this.inlineSourceMap = inlineSourceMap;
    }

    public CompilerOptions withInlineSourceMap(Boolean inlineSourceMap) {
        this.inlineSourceMap = inlineSourceMap;
        return this;
    }

    /**
     * Emit the source alongside the sourcemaps within a single file; requires --inlineSourceMap to be set.
     * 
     * @return
     *     The inlineSources
     */
    public Boolean getInlineSources() {
        return inlineSources;
    }

    /**
     * Emit the source alongside the sourcemaps within a single file; requires --inlineSourceMap to be set.
     * 
     * @param inlineSources
     *     The inlineSources
     */
    public void setInlineSources(Boolean inlineSources) {
        this.inlineSources = inlineSources;
    }

    public CompilerOptions withInlineSources(Boolean inlineSources) {
        this.inlineSources = inlineSources;
        return this;
    }

    /**
     * Specify JSX code generation: 'preserve' or 'react'.
     * 
     * @return
     *     The jsx
     */
    public CompilerOptions.Jsx getJsx() {
        return jsx;
    }

    /**
     * Specify JSX code generation: 'preserve' or 'react'.
     * 
     * @param jsx
     *     The jsx
     */
    public void setJsx(CompilerOptions.Jsx jsx) {
        this.jsx = jsx;
    }

    public CompilerOptions withJsx(CompilerOptions.Jsx jsx) {
        this.jsx = jsx;
        return this;
    }

    /**
     * Specifies the object invoked for createElement and __spread when targeting 'react' JSX emit.
     * 
     * @return
     *     The reactNamespace
     */
    public String getReactNamespace() {
        return reactNamespace;
    }

    /**
     * Specifies the object invoked for createElement and __spread when targeting 'react' JSX emit.
     * 
     * @param reactNamespace
     *     The reactNamespace
     */
    public void setReactNamespace(String reactNamespace) {
        this.reactNamespace = reactNamespace;
    }

    public CompilerOptions withReactNamespace(String reactNamespace) {
        this.reactNamespace = reactNamespace;
        return this;
    }

    /**
     * Print names of files part of the compilation.
     * 
     * @return
     *     The listFiles
     */
    public Boolean getListFiles() {
        return listFiles;
    }

    /**
     * Print names of files part of the compilation.
     * 
     * @param listFiles
     *     The listFiles
     */
    public void setListFiles(Boolean listFiles) {
        this.listFiles = listFiles;
    }

    public CompilerOptions withListFiles(Boolean listFiles) {
        this.listFiles = listFiles;
        return this;
    }

    /**
     * The locale to use to show error messages, e.g. en-us.
     * 
     * @return
     *     The locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * The locale to use to show error messages, e.g. en-us.
     * 
     * @param locale
     *     The locale
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    public CompilerOptions withLocale(String locale) {
        this.locale = locale;
        return this;
    }

    /**
     * Specifies the location where debugger should locate map files instead of generated locations
     * 
     * @return
     *     The mapRoot
     */
    public URI getMapRoot() {
        return mapRoot;
    }

    /**
     * Specifies the location where debugger should locate map files instead of generated locations
     * 
     * @param mapRoot
     *     The mapRoot
     */
    public void setMapRoot(URI mapRoot) {
        this.mapRoot = mapRoot;
    }

    public CompilerOptions withMapRoot(URI mapRoot) {
        this.mapRoot = mapRoot;
        return this;
    }

    /**
     * Specify module code generation: 'CommonJS', 'Amd', 'System', 'UMD', 'es6', or 'es2015'.
     * 
     * @return
     *     The module
     */
    public CompilerOptions.Module getModule() {
        return module;
    }

    /**
     * Specify module code generation: 'CommonJS', 'Amd', 'System', 'UMD', 'es6', or 'es2015'.
     * 
     * @param module
     *     The module
     */
    public void setModule(CompilerOptions.Module module) {
        this.module = module;
    }

    public CompilerOptions withModule(CompilerOptions.Module module) {
        this.module = module;
        return this;
    }

    /**
     * Specifies the end of line sequence to be used when emitting files: 'CRLF' (dos) or 'LF' (unix).
     * 
     * @return
     *     The newLine
     */
    public CompilerOptions.NewLine getNewLine() {
        return newLine;
    }

    /**
     * Specifies the end of line sequence to be used when emitting files: 'CRLF' (dos) or 'LF' (unix).
     * 
     * @param newLine
     *     The newLine
     */
    public void setNewLine(CompilerOptions.NewLine newLine) {
        this.newLine = newLine;
    }

    public CompilerOptions withNewLine(CompilerOptions.NewLine newLine) {
        this.newLine = newLine;
        return this;
    }

    /**
     * Do not emit output.
     * 
     * @return
     *     The noEmit
     */
    public Boolean getNoEmit() {
        return noEmit;
    }

    /**
     * Do not emit output.
     * 
     * @param noEmit
     *     The noEmit
     */
    public void setNoEmit(Boolean noEmit) {
        this.noEmit = noEmit;
    }

    public CompilerOptions withNoEmit(Boolean noEmit) {
        this.noEmit = noEmit;
        return this;
    }

    /**
     * Do not generate custom helper functions like __extends in compiled output.
     * 
     * @return
     *     The noEmitHelpers
     */
    public Boolean getNoEmitHelpers() {
        return noEmitHelpers;
    }

    /**
     * Do not generate custom helper functions like __extends in compiled output.
     * 
     * @param noEmitHelpers
     *     The noEmitHelpers
     */
    public void setNoEmitHelpers(Boolean noEmitHelpers) {
        this.noEmitHelpers = noEmitHelpers;
    }

    public CompilerOptions withNoEmitHelpers(Boolean noEmitHelpers) {
        this.noEmitHelpers = noEmitHelpers;
        return this;
    }

    /**
     * Do not emit outputs if any type checking errors were reported.
     * 
     * @return
     *     The noEmitOnError
     */
    public Boolean getNoEmitOnError() {
        return noEmitOnError;
    }

    /**
     * Do not emit outputs if any type checking errors were reported.
     * 
     * @param noEmitOnError
     *     The noEmitOnError
     */
    public void setNoEmitOnError(Boolean noEmitOnError) {
        this.noEmitOnError = noEmitOnError;
    }

    public CompilerOptions withNoEmitOnError(Boolean noEmitOnError) {
        this.noEmitOnError = noEmitOnError;
        return this;
    }

    /**
     * Warn on expressions and declarations with an implied 'any' type.
     * 
     * @return
     *     The noImplicitAny
     */
    public Boolean getNoImplicitAny() {
        return noImplicitAny;
    }

    /**
     * Warn on expressions and declarations with an implied 'any' type.
     * 
     * @param noImplicitAny
     *     The noImplicitAny
     */
    public void setNoImplicitAny(Boolean noImplicitAny) {
        this.noImplicitAny = noImplicitAny;
    }

    public CompilerOptions withNoImplicitAny(Boolean noImplicitAny) {
        this.noImplicitAny = noImplicitAny;
        return this;
    }

    /**
     * Do not include the default library file (lib.d.ts).
     * 
     * @return
     *     The noLib
     */
    public Boolean getNoLib() {
        return noLib;
    }

    /**
     * Do not include the default library file (lib.d.ts).
     * 
     * @param noLib
     *     The noLib
     */
    public void setNoLib(Boolean noLib) {
        this.noLib = noLib;
    }

    public CompilerOptions withNoLib(Boolean noLib) {
        this.noLib = noLib;
        return this;
    }

    /**
     *   Do not add triple-slash references or module import targets to the list of compiled files.
     * 
     * @return
     *     The noResolve
     */
    public Boolean getNoResolve() {
        return noResolve;
    }

    /**
     *   Do not add triple-slash references or module import targets to the list of compiled files.
     * 
     * @param noResolve
     *     The noResolve
     */
    public void setNoResolve(Boolean noResolve) {
        this.noResolve = noResolve;
    }

    public CompilerOptions withNoResolve(Boolean noResolve) {
        this.noResolve = noResolve;
        return this;
    }

    /**
     * 
     * @return
     *     The skipDefaultLibCheck
     */
    public Boolean getSkipDefaultLibCheck() {
        return skipDefaultLibCheck;
    }

    /**
     * 
     * @param skipDefaultLibCheck
     *     The skipDefaultLibCheck
     */
    public void setSkipDefaultLibCheck(Boolean skipDefaultLibCheck) {
        this.skipDefaultLibCheck = skipDefaultLibCheck;
    }

    public CompilerOptions withSkipDefaultLibCheck(Boolean skipDefaultLibCheck) {
        this.skipDefaultLibCheck = skipDefaultLibCheck;
        return this;
    }

    /**
     * Concatenate and emit output to single file.
     * 
     * @return
     *     The outFile
     */
    public URI getOutFile() {
        return outFile;
    }

    /**
     * Concatenate and emit output to single file.
     * 
     * @param outFile
     *     The outFile
     */
    public void setOutFile(URI outFile) {
        this.outFile = outFile;
    }

    public CompilerOptions withOutFile(URI outFile) {
        this.outFile = outFile;
        return this;
    }

    /**
     * Redirect output structure to the directory.
     * 
     * @return
     *     The outDir
     */
    public URI getOutDir() {
        return outDir;
    }

    /**
     * Redirect output structure to the directory.
     * 
     * @param outDir
     *     The outDir
     */
    public void setOutDir(URI outDir) {
        this.outDir = outDir;
    }

    public CompilerOptions withOutDir(URI outDir) {
        this.outDir = outDir;
        return this;
    }

    /**
     * Do not erase const enum declarations in generated code.
     * 
     * @return
     *     The preserveConstEnums
     */
    public Boolean getPreserveConstEnums() {
        return preserveConstEnums;
    }

    /**
     * Do not erase const enum declarations in generated code.
     * 
     * @param preserveConstEnums
     *     The preserveConstEnums
     */
    public void setPreserveConstEnums(Boolean preserveConstEnums) {
        this.preserveConstEnums = preserveConstEnums;
    }

    public CompilerOptions withPreserveConstEnums(Boolean preserveConstEnums) {
        this.preserveConstEnums = preserveConstEnums;
        return this;
    }

    /**
     * Stylize errors and messages using color and context. (experimental)
     * 
     * @return
     *     The pretty
     */
    public Boolean getPretty() {
        return pretty;
    }

    /**
     * Stylize errors and messages using color and context. (experimental)
     * 
     * @param pretty
     *     The pretty
     */
    public void setPretty(Boolean pretty) {
        this.pretty = pretty;
    }

    public CompilerOptions withPretty(Boolean pretty) {
        this.pretty = pretty;
        return this;
    }

    /**
     * Do not emit comments to output.
     * 
     * @return
     *     The removeComments
     */
    public Boolean getRemoveComments() {
        return removeComments;
    }

    /**
     * Do not emit comments to output.
     * 
     * @param removeComments
     *     The removeComments
     */
    public void setRemoveComments(Boolean removeComments) {
        this.removeComments = removeComments;
    }

    public CompilerOptions withRemoveComments(Boolean removeComments) {
        this.removeComments = removeComments;
        return this;
    }

    /**
     * Specifies the root directory of input files. Use to control the output directory structure with --outDir.
     * 
     * @return
     *     The rootDir
     */
    public URI getRootDir() {
        return rootDir;
    }

    /**
     * Specifies the root directory of input files. Use to control the output directory structure with --outDir.
     * 
     * @param rootDir
     *     The rootDir
     */
    public void setRootDir(URI rootDir) {
        this.rootDir = rootDir;
    }

    public CompilerOptions withRootDir(URI rootDir) {
        this.rootDir = rootDir;
        return this;
    }

    /**
     * Unconditionally emit imports for unresolved files.
     * 
     * @return
     *     The isolatedModules
     */
    public Boolean getIsolatedModules() {
        return isolatedModules;
    }

    /**
     * Unconditionally emit imports for unresolved files.
     * 
     * @param isolatedModules
     *     The isolatedModules
     */
    public void setIsolatedModules(Boolean isolatedModules) {
        this.isolatedModules = isolatedModules;
    }

    public CompilerOptions withIsolatedModules(Boolean isolatedModules) {
        this.isolatedModules = isolatedModules;
        return this;
    }

    /**
     * Generates corresponding '.map' file.
     * 
     * @return
     *     The sourceMap
     */
    public Boolean getSourceMap() {
        return sourceMap;
    }

    /**
     * Generates corresponding '.map' file.
     * 
     * @param sourceMap
     *     The sourceMap
     */
    public void setSourceMap(Boolean sourceMap) {
        this.sourceMap = sourceMap;
    }

    public CompilerOptions withSourceMap(Boolean sourceMap) {
        this.sourceMap = sourceMap;
        return this;
    }

    /**
     * Specifies the location where debugger should locate TypeScript files instead of source locations.
     * 
     * @return
     *     The sourceRoot
     */
    public URI getSourceRoot() {
        return sourceRoot;
    }

    /**
     * Specifies the location where debugger should locate TypeScript files instead of source locations.
     * 
     * @param sourceRoot
     *     The sourceRoot
     */
    public void setSourceRoot(URI sourceRoot) {
        this.sourceRoot = sourceRoot;
    }

    public CompilerOptions withSourceRoot(URI sourceRoot) {
        this.sourceRoot = sourceRoot;
        return this;
    }

    /**
     * Suppress excess property checks for object literals.
     * 
     * @return
     *     The suppressExcessPropertyErrors
     */
    public Boolean getSuppressExcessPropertyErrors() {
        return suppressExcessPropertyErrors;
    }

    /**
     * Suppress excess property checks for object literals.
     * 
     * @param suppressExcessPropertyErrors
     *     The suppressExcessPropertyErrors
     */
    public void setSuppressExcessPropertyErrors(Boolean suppressExcessPropertyErrors) {
        this.suppressExcessPropertyErrors = suppressExcessPropertyErrors;
    }

    public CompilerOptions withSuppressExcessPropertyErrors(Boolean suppressExcessPropertyErrors) {
        this.suppressExcessPropertyErrors = suppressExcessPropertyErrors;
        return this;
    }

    /**
     * Suppress noImplicitAny errors for indexing objects lacking index signatures.
     * 
     * @return
     *     The suppressImplicitAnyIndexErrors
     */
    public Boolean getSuppressImplicitAnyIndexErrors() {
        return suppressImplicitAnyIndexErrors;
    }

    /**
     * Suppress noImplicitAny errors for indexing objects lacking index signatures.
     * 
     * @param suppressImplicitAnyIndexErrors
     *     The suppressImplicitAnyIndexErrors
     */
    public void setSuppressImplicitAnyIndexErrors(Boolean suppressImplicitAnyIndexErrors) {
        this.suppressImplicitAnyIndexErrors = suppressImplicitAnyIndexErrors;
    }

    public CompilerOptions withSuppressImplicitAnyIndexErrors(Boolean suppressImplicitAnyIndexErrors) {
        this.suppressImplicitAnyIndexErrors = suppressImplicitAnyIndexErrors;
        return this;
    }

    /**
     * Do not emit declarations for code that has an '@internal' annotation.
     * 
     * @return
     *     The stripInternal
     */
    public Boolean getStripInternal() {
        return stripInternal;
    }

    /**
     * Do not emit declarations for code that has an '@internal' annotation.
     * 
     * @param stripInternal
     *     The stripInternal
     */
    public void setStripInternal(Boolean stripInternal) {
        this.stripInternal = stripInternal;
    }

    public CompilerOptions withStripInternal(Boolean stripInternal) {
        this.stripInternal = stripInternal;
        return this;
    }

    /**
     * Specify ECMAScript target version.
     * 
     * @return
     *     The target
     */
    public CompilerOptions.Target getTarget() {
        return target;
    }

    /**
     * Specify ECMAScript target version.
     * 
     * @param target
     *     The target
     */
    public void setTarget(CompilerOptions.Target target) {
        this.target = target;
    }

    public CompilerOptions withTarget(CompilerOptions.Target target) {
        this.target = target;
        return this;
    }

    /**
     * Watch input files.
     * 
     * @return
     *     The watch
     */
    public Boolean getWatch() {
        return watch;
    }

    /**
     * Watch input files.
     * 
     * @param watch
     *     The watch
     */
    public void setWatch(Boolean watch) {
        this.watch = watch;
    }

    public CompilerOptions withWatch(Boolean watch) {
        this.watch = watch;
        return this;
    }

    /**
     * Enables experimental support for ES7 decorators.
     * 
     * @return
     *     The experimentalDecorators
     */
    public Boolean getExperimentalDecorators() {
        return experimentalDecorators;
    }

    /**
     * Enables experimental support for ES7 decorators.
     * 
     * @param experimentalDecorators
     *     The experimentalDecorators
     */
    public void setExperimentalDecorators(Boolean experimentalDecorators) {
        this.experimentalDecorators = experimentalDecorators;
    }

    public CompilerOptions withExperimentalDecorators(Boolean experimentalDecorators) {
        this.experimentalDecorators = experimentalDecorators;
        return this;
    }

    /**
     * Emit design-type metadata for decorated declarations in source.
     * 
     * @return
     *     The emitDecoratorMetadata
     */
    public Boolean getEmitDecoratorMetadata() {
        return emitDecoratorMetadata;
    }

    /**
     * Emit design-type metadata for decorated declarations in source.
     * 
     * @param emitDecoratorMetadata
     *     The emitDecoratorMetadata
     */
    public void setEmitDecoratorMetadata(Boolean emitDecoratorMetadata) {
        this.emitDecoratorMetadata = emitDecoratorMetadata;
    }

    public CompilerOptions withEmitDecoratorMetadata(Boolean emitDecoratorMetadata) {
        this.emitDecoratorMetadata = emitDecoratorMetadata;
        return this;
    }

    /**
     * Specifies module resolution strategy: 'node' (Node) or 'classic' (TypeScript pre 1.6) .
     * 
     * @return
     *     The moduleResolution
     */
    public CompilerOptions.ModuleResolution getModuleResolution() {
        return moduleResolution;
    }

    /**
     * Specifies module resolution strategy: 'node' (Node) or 'classic' (TypeScript pre 1.6) .
     * 
     * @param moduleResolution
     *     The moduleResolution
     */
    public void setModuleResolution(CompilerOptions.ModuleResolution moduleResolution) {
        this.moduleResolution = moduleResolution;
    }

    public CompilerOptions withModuleResolution(CompilerOptions.ModuleResolution moduleResolution) {
        this.moduleResolution = moduleResolution;
        return this;
    }

    /**
     * Do not report errors on unused labels.
     * 
     * @return
     *     The allowUnusedLabels
     */
    public Boolean getAllowUnusedLabels() {
        return allowUnusedLabels;
    }

    /**
     * Do not report errors on unused labels.
     * 
     * @param allowUnusedLabels
     *     The allowUnusedLabels
     */
    public void setAllowUnusedLabels(Boolean allowUnusedLabels) {
        this.allowUnusedLabels = allowUnusedLabels;
    }

    public CompilerOptions withAllowUnusedLabels(Boolean allowUnusedLabels) {
        this.allowUnusedLabels = allowUnusedLabels;
        return this;
    }

    /**
     * Report error when not all code paths in function return a value.
     * 
     * @return
     *     The noImplicitReturns
     */
    public Boolean getNoImplicitReturns() {
        return noImplicitReturns;
    }

    /**
     * Report error when not all code paths in function return a value.
     * 
     * @param noImplicitReturns
     *     The noImplicitReturns
     */
    public void setNoImplicitReturns(Boolean noImplicitReturns) {
        this.noImplicitReturns = noImplicitReturns;
    }

    public CompilerOptions withNoImplicitReturns(Boolean noImplicitReturns) {
        this.noImplicitReturns = noImplicitReturns;
        return this;
    }

    /**
     * Report errors for fallthrough cases in switch statement.
     * 
     * @return
     *     The noFallthroughCasesInSwitch
     */
    public Boolean getNoFallthroughCasesInSwitch() {
        return noFallthroughCasesInSwitch;
    }

    /**
     * Report errors for fallthrough cases in switch statement.
     * 
     * @param noFallthroughCasesInSwitch
     *     The noFallthroughCasesInSwitch
     */
    public void setNoFallthroughCasesInSwitch(Boolean noFallthroughCasesInSwitch) {
        this.noFallthroughCasesInSwitch = noFallthroughCasesInSwitch;
    }

    public CompilerOptions withNoFallthroughCasesInSwitch(Boolean noFallthroughCasesInSwitch) {
        this.noFallthroughCasesInSwitch = noFallthroughCasesInSwitch;
        return this;
    }

    /**
     * Do not report errors on unreachable code.
     * 
     * @return
     *     The allowUnreachableCode
     */
    public Boolean getAllowUnreachableCode() {
        return allowUnreachableCode;
    }

    /**
     * Do not report errors on unreachable code.
     * 
     * @param allowUnreachableCode
     *     The allowUnreachableCode
     */
    public void setAllowUnreachableCode(Boolean allowUnreachableCode) {
        this.allowUnreachableCode = allowUnreachableCode;
    }

    public CompilerOptions withAllowUnreachableCode(Boolean allowUnreachableCode) {
        this.allowUnreachableCode = allowUnreachableCode;
        return this;
    }

    /**
     * Disallow inconsistently-cased references to the same file.
     * 
     * @return
     *     The forceConsistentCasingInFileNames
     */
    public Boolean getForceConsistentCasingInFileNames() {
        return forceConsistentCasingInFileNames;
    }

    /**
     * Disallow inconsistently-cased references to the same file.
     * 
     * @param forceConsistentCasingInFileNames
     *     The forceConsistentCasingInFileNames
     */
    public void setForceConsistentCasingInFileNames(Boolean forceConsistentCasingInFileNames) {
        this.forceConsistentCasingInFileNames = forceConsistentCasingInFileNames;
    }

    public CompilerOptions withForceConsistentCasingInFileNames(Boolean forceConsistentCasingInFileNames) {
        this.forceConsistentCasingInFileNames = forceConsistentCasingInFileNames;
        return this;
    }

    /**
     * Allow default imports from modules with no default export. This does not affect code emit, just typechecking.
     * 
     * @return
     *     The allowSyntheticDefaultImports
     */
    public Boolean getAllowSyntheticDefaultImports() {
        return allowSyntheticDefaultImports;
    }

    /**
     * Allow default imports from modules with no default export. This does not affect code emit, just typechecking.
     * 
     * @param allowSyntheticDefaultImports
     *     The allowSyntheticDefaultImports
     */
    public void setAllowSyntheticDefaultImports(Boolean allowSyntheticDefaultImports) {
        this.allowSyntheticDefaultImports = allowSyntheticDefaultImports;
    }

    public CompilerOptions withAllowSyntheticDefaultImports(Boolean allowSyntheticDefaultImports) {
        this.allowSyntheticDefaultImports = allowSyntheticDefaultImports;
        return this;
    }

    /**
     * Allow javascript files to be compiled.
     * 
     * @return
     *     The allowJs
     */
    public Boolean getAllowJs() {
        return allowJs;
    }

    /**
     * Allow javascript files to be compiled.
     * 
     * @param allowJs
     *     The allowJs
     */
    public void setAllowJs(Boolean allowJs) {
        this.allowJs = allowJs;
    }

    public CompilerOptions withAllowJs(Boolean allowJs) {
        this.allowJs = allowJs;
        return this;
    }

    /**
     * Do not emit "use strict" directives in module output.
     * 
     * @return
     *     The noImplicitUseStrict
     */
    public Boolean getNoImplicitUseStrict() {
        return noImplicitUseStrict;
    }

    /**
     * Do not emit "use strict" directives in module output.
     * 
     * @param noImplicitUseStrict
     *     The noImplicitUseStrict
     */
    public void setNoImplicitUseStrict(Boolean noImplicitUseStrict) {
        this.noImplicitUseStrict = noImplicitUseStrict;
    }

    public CompilerOptions withNoImplicitUseStrict(Boolean noImplicitUseStrict) {
        this.noImplicitUseStrict = noImplicitUseStrict;
        return this;
    }

    @Generated("org.jsonschema2pojo")
    public static enum Jsx {

        @SerializedName("preserve")
        PRESERVE("preserve"),
        @SerializedName("react")
        REACT("react");
        private final String value;
        private final static Map<String, CompilerOptions.Jsx> CONSTANTS = new HashMap<String, CompilerOptions.Jsx>();

        static {
            for (CompilerOptions.Jsx c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Jsx(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static CompilerOptions.Jsx fromValue(String value) {
            CompilerOptions.Jsx constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum Module {

        @SerializedName("commonjs")
        COMMONJS("commonjs"),
        @SerializedName("amd")
        AMD("amd"),
        @SerializedName("umd")
        UMD("umd"),
        @SerializedName("system")
        SYSTEM("system"),
        @SerializedName("es6")
        ES_6("es6"),
        @SerializedName("es2015")
        ES_2015("es2015");
        private final String value;
        private final static Map<String, CompilerOptions.Module> CONSTANTS = new HashMap<String, CompilerOptions.Module>();

        static {
            for (CompilerOptions.Module c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Module(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static CompilerOptions.Module fromValue(String value) {
            CompilerOptions.Module constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum ModuleResolution {

        @SerializedName("classic")
        CLASSIC("classic"),
        @SerializedName("node")
        NODE("node");
        private final String value;
        private final static Map<String, CompilerOptions.ModuleResolution> CONSTANTS = new HashMap<String, CompilerOptions.ModuleResolution>();

        static {
            for (CompilerOptions.ModuleResolution c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private ModuleResolution(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static CompilerOptions.ModuleResolution fromValue(String value) {
            CompilerOptions.ModuleResolution constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum NewLine {

        @SerializedName("CRLF")
        CRLF("CRLF"),
        @SerializedName("LF")
        LF("LF");
        private final String value;
        private final static Map<String, CompilerOptions.NewLine> CONSTANTS = new HashMap<String, CompilerOptions.NewLine>();

        static {
            for (CompilerOptions.NewLine c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private NewLine(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static CompilerOptions.NewLine fromValue(String value) {
            CompilerOptions.NewLine constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum Target {

        @SerializedName("es3")
        ES_3("es3"),
        @SerializedName("es5")
        ES_5("es5"),
        @SerializedName("es6")
        ES_6("es6"),
        @SerializedName("es2015")
        ES_2015("es2015");
        private final String value;
        private final static Map<String, CompilerOptions.Target> CONSTANTS = new HashMap<String, CompilerOptions.Target>();

        static {
            for (CompilerOptions.Target c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Target(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static CompilerOptions.Target fromValue(String value) {
            CompilerOptions.Target constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
