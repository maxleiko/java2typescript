
package org.kevoree.modeling.java2typescript.json.packagejson;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Directories {

    /**
     * If you specify a 'bin' directory, then all the files in that folder will be used as the 'bin' hash.
     * 
     */
    @SerializedName("bin")
    @Expose
    private String bin;
    /**
     * Put markdown files in here. Eventually, these will be displayed nicely, maybe, someday.
     * 
     */
    @SerializedName("doc")
    @Expose
    private String doc;
    /**
     * Put example scripts in here. Someday, it might be exposed in some clever way.
     * 
     */
    @SerializedName("example")
    @Expose
    private String example;
    /**
     * Tell people where the bulk of your library is. Nothing special is done with the lib folder in any way, but it's useful meta info.
     * 
     */
    @SerializedName("lib")
    @Expose
    private String lib;
    /**
     * A folder that is full of man pages. Sugar to generate a 'man' array by walking the folder.
     * 
     */
    @SerializedName("man")
    @Expose
    private String man;
    @SerializedName("test")
    @Expose
    private String test;

    /**
     * If you specify a 'bin' directory, then all the files in that folder will be used as the 'bin' hash.
     * 
     * @return
     *     The bin
     */
    public String getBin() {
        return bin;
    }

    /**
     * If you specify a 'bin' directory, then all the files in that folder will be used as the 'bin' hash.
     * 
     * @param bin
     *     The bin
     */
    public void setBin(String bin) {
        this.bin = bin;
    }

    /**
     * Put markdown files in here. Eventually, these will be displayed nicely, maybe, someday.
     * 
     * @return
     *     The doc
     */
    public String getDoc() {
        return doc;
    }

    /**
     * Put markdown files in here. Eventually, these will be displayed nicely, maybe, someday.
     * 
     * @param doc
     *     The doc
     */
    public void setDoc(String doc) {
        this.doc = doc;
    }

    /**
     * Put example scripts in here. Someday, it might be exposed in some clever way.
     * 
     * @return
     *     The example
     */
    public String getExample() {
        return example;
    }

    /**
     * Put example scripts in here. Someday, it might be exposed in some clever way.
     * 
     * @param example
     *     The example
     */
    public void setExample(String example) {
        this.example = example;
    }

    /**
     * Tell people where the bulk of your library is. Nothing special is done with the lib folder in any way, but it's useful meta info.
     * 
     * @return
     *     The lib
     */
    public String getLib() {
        return lib;
    }

    /**
     * Tell people where the bulk of your library is. Nothing special is done with the lib folder in any way, but it's useful meta info.
     * 
     * @param lib
     *     The lib
     */
    public void setLib(String lib) {
        this.lib = lib;
    }

    /**
     * A folder that is full of man pages. Sugar to generate a 'man' array by walking the folder.
     * 
     * @return
     *     The man
     */
    public String getMan() {
        return man;
    }

    /**
     * A folder that is full of man pages. Sugar to generate a 'man' array by walking the folder.
     * 
     * @param man
     *     The man
     */
    public void setMan(String man) {
        this.man = man;
    }

    /**
     * 
     * @return
     *     The test
     */
    public String getTest() {
        return test;
    }

    /**
     * 
     * @param test
     *     The test
     */
    public void setTest(String test) {
        this.test = test;
    }

}
