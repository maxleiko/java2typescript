
package org.kevoree.modeling.java2typescript.json.packagejson;

import java.net.URI;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Specify the place where your code lives. This is helpful for people who want to contribute.
 * 
 */
@Generated("org.jsonschema2pojo")
public class Repository {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("url")
    @Expose
    private URI url;

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The url
     */
    public URI getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(URI url) {
        this.url = url;
    }

}
