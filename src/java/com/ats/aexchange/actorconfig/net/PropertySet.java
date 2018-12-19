package com.ats.aexchange.actorconfig.net;

import java.util.Hashtable;


/**
 * @author Jim Firby
 */
public class PropertySet {

    private String name = null;
    private Hashtable<String, String> values = new Hashtable<String, String>();

    //private static String HIMSS_CODE = "HIMSS2005&1.3.6.1.4.1.21367.2005.1.1&ISO";

    public PropertySet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean containsValue(String valueName) {
        if (valueName == null) {
            return false;
        }
        return values.containsKey(valueName);
    }

    public String getValue(String valueName) {
        //if(valueName.equals("codingSystem"))
        //    return HIMSS_CODE;

        if (valueName == null) {
            return null;
        }
        return values.get(valueName);
    }

    public void addValue(String valueName, String value) {
        if (valueName == null) {
            return;
        }
        if (value == null) {
            values.remove(valueName);
        } else {
            values.put(valueName, value);
        }
    }

}
