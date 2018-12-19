package com.ats.aexchange.utils;

import java.util.List;

/**
 * This class defines String utility methods.
 *
 * @author <a href="mailto:wenzhi.li@misys.com">Wenzhi Li</a>
 */
public class StringUtil {

    public static final boolean goodString(String s) {
        return ((s != null) && (s.trim().length() > 0));
    }

    /**
     * Check whether a string is null or empty.
     *
     * @param s The string to check
     * @return True if the string is null or empty
     */
    public static final boolean isNullString(String s) {
        return ((s == null) || (s.length() == 0));
    }

    /**
     * Trim a string and if it is empty, return null.
     *
     * @param s The string to trim
     * @return The trimmed string, or null if the string is empty
     */
    public static final String trimString(String s) {
        if (s == null) {
            return null;
        }
        String trimmed = s.trim();
        if (trimmed.equals("")) {
            return null;
        }
        return trimmed;
    }

    /**
     * Converts a list of string to a string concatenation separated by the given delimiter.
     *
     * @param list           the given list to be converted to a string
     * @param delimiter      the delimiter
     * @param elementWrapper the String wrapper on each list element
     * @return the string concatenation of the list
     */
    public static final String toString(List<String> list, String delimiter, String elementWrapper) {
        if (list == null || list.size() == 0) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            String value = list.get(i);
            sb.append(elementWrapper);
            sb.append(list.get(i));
            sb.append(elementWrapper);
            //Don't add the delimiter to last one
            if (i != list.size() - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    /**
     * Converts a list of string to a string concatenation separated by the given delimiter.
     *
     * @param list      the given list to be converted to a string
     * @param delimiter the delimiter
     * @return the string concatenation of the list
     */
    public static final String toString(List<String> list, String delimiter) {
        if (list == null || list.size() == 0) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            //Don't add the delimiter to last one
            if (i != list.size() - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    /**
     * Converts an array of string to a string concatenation separated by the given delimiter.
     *
     * @param array     the given string array to be converted to a string
     * @param delimiter the delimiter
     * @return the string concatenation of the string array
     */
    public static final String toString(String[] array, String delimiter) {
        if (array == null || array.length == 0) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            //Don't add the delimiter to last one
            if (i != array.length - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

}
