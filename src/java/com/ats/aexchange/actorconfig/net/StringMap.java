package com.ats.aexchange.actorconfig.net;

import java.util.Hashtable;

/**
 * @author Jim Firby
 */
public class StringMap {

    private String name = null;
    private Hashtable<String, String> entries = null;
    private Hashtable<String, String> inverse = null;

    /**
     * Create a new string map from string values to IHE affinity domain specific codes.
     *
     * @param name The name of the string value set being mapped
     */
    public StringMap(String name) {
        this.name = name;
        entries = new Hashtable<String, String>();
        inverse = new Hashtable<String, String>();
    }

    /**
     * @return The number of string values in this map
     */
    public int size() {
        return entries.size();
    }

    /**
     * Add an entry to this string map.
     *
     * @param stringValue The name of the srting value to map
     * @param codeValue   The corresponding IHE code value
     */
    public void addEntry(String stringValue, String codeValue) {
        if ((stringValue != null) && (codeValue != null)) {
            entries.put(stringValue, codeValue);
            // Create an inverse entry if this is the first code for this string
            if (!inverse.containsKey(codeValue)) {
                inverse.put(codeValue, stringValue);
            }
        }
    }

    /**
     * @return The name of the string value types this class maps
     */
    public String getName() {
        return name;
    }

    /**
     * Check whether this string map contains a particular string value.
     *
     * @param stringValue The string value to check
     * @return True if this map contains this value
     */
    public boolean containsStringValue(String stringValue) {
        return entries.containsKey(stringValue);
    }

    /**
     * Check whether this string map contains a particular code value.
     *
     * @param codeValue The code value to check
     * @return True if this map contains a mapping to this code value
     */
    public boolean containsCodeValue(String codeValue) {
        return inverse.containsKey(codeValue);
    }

    /**
     * Get the IHE code value corresponding to this string value.
     *
     * @param stringValue The string value
     * @return The corresponding IHE value
     */
    public String getCodeValue(String stringValue) {
        return entries.get(stringValue);
    }

    /**
     * Get the internal Misys string corresponding to this IHE code value.
     *
     * @param codeValue The IHE code value
     * @return The Misys string for this code value
     */
    public String getStringValue(String codeValue) {
        return inverse.get(codeValue);
    }

}
