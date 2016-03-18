
package org.kevoree.modeling.java2typescript.json.packagejson;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Dist {

    @SerializedName("shasum")
    @Expose
    private String shasum;
    @SerializedName("tarball")
    @Expose
    private String tarball;

    /**
     * 
     * @return
     *     The shasum
     */
    public String getShasum() {
        return shasum;
    }

    /**
     * 
     * @param shasum
     *     The shasum
     */
    public void setShasum(String shasum) {
        this.shasum = shasum;
    }

    /**
     * 
     * @return
     *     The tarball
     */
    public String getTarball() {
        return tarball;
    }

    /**
     * 
     * @param tarball
     *     The tarball
     */
    public void setTarball(String tarball) {
        this.tarball = tarball;
    }

}
