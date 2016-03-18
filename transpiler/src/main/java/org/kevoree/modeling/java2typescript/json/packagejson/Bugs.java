
package org.kevoree.modeling.java2typescript.json.packagejson;

import java.net.URI;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * The url to your project's issue tracker and / or the email address to which issues should be reported. These are helpful for people who encounter issues with your package.
 * 
 */
@Generated("org.jsonschema2pojo")
public class Bugs {

    /**
     * The url to your project's issue tracker.
     * 
     */
    @SerializedName("url")
    @Expose
    private URI url;
    /**
     * The email address to which issues should be reported.
     * 
     */
    @SerializedName("email")
    @Expose
    private String email;

    /**
     * The url to your project's issue tracker.
     * 
     * @return
     *     The url
     */
    public URI getUrl() {
        return url;
    }

    /**
     * The url to your project's issue tracker.
     * 
     * @param url
     *     The url
     */
    public void setUrl(URI url) {
        this.url = url;
    }

    /**
     * The email address to which issues should be reported.
     * 
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * The email address to which issues should be reported.
     * 
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
