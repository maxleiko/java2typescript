package org.kevoree.modeling.java2typescript.mavenplugin;

/**
 *
 * Created by leiko on 3/18/16.
 */
public class Dependency {

    private String name;
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
