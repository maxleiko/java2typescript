
package org.kevoree.modeling.java2typescript.json.tsconfig;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exclude {

    /**
     * The 'exclude' property cannot be used in conjunction with the 'files' property. If both are specified then the 'files' property takes precedence.
     * 
     */
    @SerializedName("exclude")
    @Expose
    private List<URI> exclude = new ArrayList<URI>();

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

    public Exclude withExclude(List<URI> exclude) {
        this.exclude = exclude;
        return this;
    }

}
