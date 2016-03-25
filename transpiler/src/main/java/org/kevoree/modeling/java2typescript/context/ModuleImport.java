package org.kevoree.modeling.java2typescript.context;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * Created by leiko on 3/22/16.
 */
public class ModuleImport {

    private String importAllAs;
    private Map<String, String> properties = new HashMap<>();
    private String name;

    public Map<String, String> getProperties() {
        return properties;
    }


    public void addProperty(String name) {
        this.properties.put(name, null);
    }

    public void addProperty(String name, String rename) {
        this.properties.put(name, rename);
    }

    public void importAll(String asName) {
        if (asName == null) {
            throw new NullPointerException("ModuleImport importAll(...) cannot be called with a null name");
        }
        this.properties.clear();
        this.importAllAs = asName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        if (properties.isEmpty()) {
            if (importAllAs != null) {
                return "import "+importAllAs+" from '"+name+"';";
            } else {
                return "import '"+name+"'";
            }
        } else {
            StringBuilder props = new StringBuilder("{ ");
            Set<String> keySet = properties.keySet();
            Iterator<String> it = keySet.iterator();
            while (it.hasNext()) {
                String name = it.next();
                String rename = properties.get(name);
                if (rename != null && rename.length() > 0) {
                    props.append(name);
                    props.append(" as ");
                    props.append(rename);
                } else {
                    props.append(name);
                }
                if (it.hasNext()) {
                    props.append(", ");
                }
            }
            props.append(" }");
            return "import " +props.toString()+ " from '"+name+"';";
        }
    }
}
