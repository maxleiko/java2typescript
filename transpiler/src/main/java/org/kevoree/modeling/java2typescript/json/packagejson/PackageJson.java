
package org.kevoree.modeling.java2typescript.json.packagejson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * JSON schema for NPM package.json files
 */
public class PackageJson {

    /**
     * The name of the package.
     *
     */
    @SerializedName("name")
    @Expose
    private String name;
    /**
     * Version must be parseable by node-semver, which is bundled with npm as a dependency.
     *
     */
    @SerializedName("version")
    @Expose
    private String version;

    /**
     * If set to true, then npm will refuse to publish it.
     *
     */
    @SerializedName("private")
    @Expose
    private boolean _private;

    /**
     * This helps people discover your package, as it's listed in 'npm search'.
     *
     */
    @SerializedName("description")
    @Expose
    private String description;
    /**
     * This helps people discover your package as it's listed in 'npm search'.
     *
     */
    @SerializedName("keywords")
    @Expose
    private List<String> keywords = new ArrayList<String>();
    /**
     * The url to the project homepage.
     *
     */
    @SerializedName("homepage")
    @Expose
    private URI homepage;

    /**
     * You should specify a license for your package so that people know how they are permitted to use it, and any restrictions you're placing on it.
     *
     */
    @SerializedName("license")
    @Expose
    private String license;

    /**
     * TypeScript declaration file resolution specification
     */
    @SerializedName("typings")
    @Expose
    private String typings;

    /**
     * The 'files' field is an array of files to include in your project. If you name a folder in the array, then it will also include the files inside that folder.
     *
     */
    @SerializedName("files")
    @Expose
    private List<String> files = new ArrayList<String>();
    /**
     * The main field is a module ID that is the primary entry point to your program.
     *
     */
    @SerializedName("main")
    @Expose
    private String main;

    @SerializedName("bin")
    @Expose
    private Map<String, URI> bin = new HashMap<String, URI>();

    /**
     * Specify either a single file or an array of filenames to put in place for the man program to find.
     *
     */
    @SerializedName("man")
    @Expose
    private List<String> man = new ArrayList<String>();

    /**
     * Dependencies are specified with a simple hash of package name to version range. The version range is a string which has one or more space-separated descriptors. Dependencies can also be identified with a tarball or git URL.
     *
     */
    @SerializedName("dependencies")
    @Expose
    private Map<String, String> dependencies = new HashMap<String, String>();

    /**
     * Dependencies are specified with a simple hash of package name to version range. The version range is a string which has one or more space-separated descriptors. Dependencies can also be identified with a tarball or git URL.
     *
     */
    @SerializedName("devDependencies")
    @Expose
    private Map<String, String> devDependencies = new HashMap<String, String>();
    /**
     * Dependencies are specified with a simple hash of package name to version range. The version range is a string which has one or more space-separated descriptors. Dependencies can also be identified with a tarball or git URL.
     *
     */
    @SerializedName("optionalDependencies")
    @Expose
    private Map<String, String> optionalDependencies = new HashMap<String, String>();

    /**
     * Dependencies are specified with a simple hash of package name to version range. The version range is a string which has one or more space-separated descriptors. Dependencies can also be identified with a tarball or git URL.
     *
     */
    @SerializedName("peerDependencies")
    @Expose
    private Map<String, String> peerDependencies = new HashMap<String, String>();

    @SerializedName("engineStrict")
    @Expose
    private boolean engineStrict;

    @SerializedName("os")
    @Expose
    private List<String> os = new ArrayList<String>();

    @SerializedName("cpu")
    @Expose
    private List<String> cpu = new ArrayList<String>();

    /**
     * If your package is primarily a command-line application that should be installed globally, then set this value to true to provide a warning if it is installed locally.
     *
     */
    @SerializedName("preferGlobal")
    @Expose
    private boolean preferGlobal;

    @SerializedName("readme")
    @Expose
    private String readme;

    /**
     * A person who has been involved in creating or maintaining this package
     *
     */
    @SerializedName("person")
    @Expose
    private Person person;

    /**
     * Array of package names that will be bundled when publishing the package.
     *
     */
    @SerializedName("bundledDependency")
    @Expose
    private List<String> bundledDependency = new ArrayList<String>();

    /**
     * Dependencies are specified with a simple hash of package name to version range. The version range is a string which has one or more space-separated descriptors. Dependencies can also be identified with a tarball or git URL.
     *
     */
    @SerializedName("dependency")
    @Expose
    private Map<String, String> dependency;

    /**
     * The name of the package.
     *
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * The name of the package.
     *
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Version must be parseable by node-semver, which is bundled with npm as a dependency.
     *
     * @return
     *     The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Version must be parseable by node-semver, which is bundled with npm as a dependency.
     *
     * @param version
     *     The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * This helps people discover your package, as it's listed in 'npm search'.
     *
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * This helps people discover your package, as it's listed in 'npm search'.
     *
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This helps people discover your package as it's listed in 'npm search'.
     *
     * @return
     *     The keywords
     */
    public List<String> getKeywords() {
        return keywords;
    }

    /**
     * This helps people discover your package as it's listed in 'npm search'.
     *
     * @param keywords
     *     The keywords
     */
    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * The url to the project homepage.
     *
     * @return
     *     The homepage
     */
    public URI getHomepage() {
        return homepage;
    }

    /**
     * The url to the project homepage.
     *
     * @param homepage
     *     The homepage
     */
    public void setHomepage(URI homepage) {
        this.homepage = homepage;
    }


    /**
     * You should specify a license for your package so that people know how they are permitted to use it, and any restrictions you're placing on it.
     *
     * @return
     *     The license
     */
    public String getLicense() {
        return license;
    }

    /**
     * You should specify a license for your package so that people know how they are permitted to use it, and any restrictions you're placing on it.
     *
     * @param license
     *     The license
     */
    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * The 'files' field is an array of files to include in your project. If you name a folder in the array, then it will also include the files inside that folder.
     *
     * @return
     *     The files
     */
    public List<String> getFiles() {
        return files;
    }

    /**
     * The 'files' field is an array of files to include in your project. If you name a folder in the array, then it will also include the files inside that folder.
     *
     * @param files
     *     The files
     */
    public void setFiles(List<String> files) {
        this.files = files;
    }

    /**
     * The main field is a module ID that is the primary entry point to your program.
     *
     * @return
     *     The main
     */
    public String getMain() {
        return main;
    }

    /**
     * The main field is a module ID that is the primary entry point to your program.
     *
     * @param main
     *     The main
     */
    public void setMain(String main) {
        this.main = main;
    }

    /**
     *
     * @return
     *     The bin
     */
    public Map<String, URI> getBin() {
        return bin;
    }

    /**
     * add a bin
     * @param name
     * @param file
     */
    public void addBin(String name, URI file) {
        this.bin.put(name, file);
    }

    /**
     * Specify either a single file or an array of filenames to put in place for the man program to find.
     *
     * @return
     *     The man
     */
    public List<String> getMan() {
        return man;
    }

    /**
     * Specify either a single file or an array of filenames to put in place for the man program to find.
     *
     * @param man
     *     The man
     */
    public void setMan(List<String> man) {
        this.man = man;
    }

    /**
     *
     * @return
     *     The engineStrict
     */
    public boolean isEngineStrict() {
        return engineStrict;
    }

    /**
     *
     * @param engineStrict
     *     The engineStrict
     */
    public void setEngineStrict(boolean engineStrict) {
        this.engineStrict = engineStrict;
    }

    /**
     *
     * @return
     *     The os
     */
    public List<String> getOs() {
        return os;
    }

    /**
     *
     * @param os
     *     The os
     */
    public void setOs(List<String> os) {
        this.os = os;
    }

    /**
     *
     * @return
     *     The cpu
     */
    public List<String> getCpu() {
        return cpu;
    }

    /**
     *
     * @param cpu
     *     The cpu
     */
    public void setCpu(List<String> cpu) {
        this.cpu = cpu;
    }

    /**
     * If your package is primarily a command-line application that should be installed globally, then set this value to true to provide a warning if it is installed locally.
     *
     * @return
     *     The preferGlobal
     */
    public boolean isPreferGlobal() {
        return preferGlobal;
    }

    /**
     * If your package is primarily a command-line application that should be installed globally, then set this value to true to provide a warning if it is installed locally.
     *
     * @param preferGlobal
     *     The preferGlobal
     */
    public void setPreferGlobal(boolean preferGlobal) {
        this.preferGlobal = preferGlobal;
    }

    /**
     * If set to true, then npm will refuse to publish it.
     *
     * @return
     *     The _private
     */
    public boolean isPrivate() {
        return _private;
    }

    /**
     * If set to true, then npm will refuse to publish it.
     *
     * @param _private
     *     The private
     */
    public void setPrivate(boolean _private) {
        this._private = _private;
    }

    /**
     *
     * @return
     *     The readme
     */
    public String getReadme() {
        return readme;
    }

    /**
     *
     * @param readme
     *     The readme
     */
    public void setReadme(String readme) {
        this.readme = readme;
    }

    /**
     * A person who has been involved in creating or maintaining this package
     * 
     * @return
     *     The person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * A person who has been involved in creating or maintaining this package
     * 
     * @param person
     *     The person
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Array of package names that will be bundled when publishing the package.
     * 
     * @return
     *     The bundledDependency
     */
    public List<String> getBundledDependency() {
        return bundledDependency;
    }

    /**
     * Array of package names that will be bundled when publishing the package.
     * 
     * @param bundledDependency
     *     The bundledDependency
     */
    public void setBundledDependency(List<String> bundledDependency) {
        this.bundledDependency = bundledDependency;
    }

    public void addDependency(String name, String version) {
        this.dependencies.put(name, version);
    }

    public void setTypings(String typings) {
        this.typings = typings;
    }
}
