package com.ats.aexchange.actorconfig.net;

import java.util.Hashtable;

/**
 * @author Jim Firby
 */
public class EnumMap {

    private static final String BASE_PACKAGE = "com.misyshealthcare.connect.base.";

    private Class enumClass = null;
    private Hashtable<Enum, String> entries = null;
    private Hashtable<String, Enum> inverse = null;

    /**
     * Create a new enum map from enum values from the given
     * enum class to IHE affinity domain specific codes.
     *
     * @param enumClassName The name of the enum class being mapped
     * @throws ClassNotFoundException When the named enum class does not exist
     */
    public EnumMap(String enumClassName) throws ClassNotFoundException {
        try {
            enumClass = Class.forName(BASE_PACKAGE + "SharedEnums$" + enumClassName);
        } catch (ClassNotFoundException e) {
            try {
                enumClass = Class.forName(BASE_PACKAGE + enumClassName);
            } catch (ClassNotFoundException e1) {
                enumClass = Class.forName(enumClassName);
            }
        }
        entries = new Hashtable<Enum, String>();
        inverse = new Hashtable<String, Enum>();
    }

    /**
     * @return The number of codes in this code set
     */
    public int size() {
        return entries.size();
    }

    /**
     * Add an entry to this enum map.
     *
     * @param enumValue The name of the enum value to map
     * @param codeValue The corresponding IHE code value
     */
    public void addEntry(String enumValue, String codeValue) {
        if ((enumValue != null) && (codeValue != null)) {
            Object[] values = enumClass.getEnumConstants();
            if (values != null) {
                for (Object o : values) {
                    if (o instanceof Enum) {
                        Enum value = (Enum) o;
                        if (value.name().equals(enumValue)) {
                            entries.put(value, codeValue);
                            if (!inverse.containsKey(codeValue)) {
                                inverse.put(codeValue, value);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * @return The enum class this class maps
     */
    public Class getEnumClass() {
        return enumClass;
    }

    /**
     * Check whether this enum map contains a particular enum value.
     *
     * @param enumValue The enum value to check
     * @return True if this map contains this value
     */
    public boolean containsEnumValue(Enum enumValue) {
        return entries.containsKey(enumValue);
    }

    /**
     * Check whether this enum map contains a mapping to a particular code value.
     *
     * @param codeValue The code value to check
     * @return True if this map contains a mapping to this code value
     */
    public boolean containsCodeValue(String codeValue) {
        return inverse.containsKey(codeValue);
    }

    /**
     * Get the IHE code value corresponding to this enum value.
     *
     * @param enumValue The enum value
     * @return The corresponding IHE value
     */
    public String getCodeValue(Enum enumValue) {
        return entries.get(enumValue);
    }

    /**
     * Get the Misys Connect Enum value that maps to this IHE code value.
     *
     * @param codeValue The IHE code value
     * @return The Misys Connect enum value
     */
    public Enum getEnumValue(String codeValue) {
        return inverse.get(codeValue);
	}
	
	
}
