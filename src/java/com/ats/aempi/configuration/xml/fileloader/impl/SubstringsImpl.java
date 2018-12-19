package com.ats.aempi.configuration.xml.fileloader.impl;
/**
 * An XML substrings(@http://configuration.openempi.openhie.org/file-loader).
 *
 * This is a complex type.
 */
public class SubstringsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.ats.aempi.configuration.xml.fileloader.Substrings
{
    
    public SubstringsImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SUBSTRING$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/file-loader", "substring");
    
    
    /**
     * Gets array of all "substring" elements
     */
    public com.ats.aempi.configuration.xml.fileloader.Substring[] getSubstringArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SUBSTRING$0, targetList);
            com.ats.aempi.configuration.xml.fileloader.Substring[] result = new com.ats.aempi.configuration.xml.fileloader.Substring[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "substring" element
     */
    public com.ats.aempi.configuration.xml.fileloader.Substring getSubstringArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.fileloader.Substring target = null;
            target = (com.ats.aempi.configuration.xml.fileloader.Substring)get_store().find_element_user(SUBSTRING$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "substring" element
     */
    public int sizeOfSubstringArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SUBSTRING$0);
        }
    }
    
    /**
     * Sets array of all "substring" element
     */
    public void setSubstringArray(com.ats.aempi.configuration.xml.fileloader.Substring[] substringArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(substringArray, SUBSTRING$0);
        }
    }
    
    /**
     * Sets ith "substring" element
     */
    public void setSubstringArray(int i, com.ats.aempi.configuration.xml.fileloader.Substring substring)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.fileloader.Substring target = null;
            target = (com.ats.aempi.configuration.xml.fileloader.Substring)get_store().find_element_user(SUBSTRING$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(substring);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "substring" element
     */
    public com.ats.aempi.configuration.xml.fileloader.Substring insertNewSubstring(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.fileloader.Substring target = null;
            target = (com.ats.aempi.configuration.xml.fileloader.Substring)get_store().insert_element_user(SUBSTRING$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "substring" element
     */
    public com.ats.aempi.configuration.xml.fileloader.Substring addNewSubstring()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.fileloader.Substring target = null;
            target = (com.ats.aempi.configuration.xml.fileloader.Substring)get_store().add_element_user(SUBSTRING$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "substring" element
     */
    public void removeSubstring(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SUBSTRING$0, i);
        }
    }
}
