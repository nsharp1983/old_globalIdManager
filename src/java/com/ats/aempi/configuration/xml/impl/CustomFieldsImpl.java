package com.ats.aempi.configuration.xml.impl;
/**
 * An XML custom-fields(@http://configuration.openempi.openhie.org/mpiconfig).
 *
 * This is a complex type.
 */
public class CustomFieldsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.ats.aempi.configuration.xml.CustomFields
{
    
    public CustomFieldsImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CUSTOMFIELD$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "custom-field");
    
    
    /**
     * Gets array of all "custom-field" elements
     */
    public com.ats.aempi.configuration.xml.CustomField[] getCustomFieldArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(CUSTOMFIELD$0, targetList);
            com.ats.aempi.configuration.xml.CustomField[] result = new com.ats.aempi.configuration.xml.CustomField[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "custom-field" element
     */
    public com.ats.aempi.configuration.xml.CustomField getCustomFieldArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.CustomField target = null;
            target = (com.ats.aempi.configuration.xml.CustomField)get_store().find_element_user(CUSTOMFIELD$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "custom-field" element
     */
    public int sizeOfCustomFieldArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CUSTOMFIELD$0);
        }
    }
    
    /**
     * Sets array of all "custom-field" element
     */
    public void setCustomFieldArray(com.ats.aempi.configuration.xml.CustomField[] customFieldArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(customFieldArray, CUSTOMFIELD$0);
        }
    }
    
    /**
     * Sets ith "custom-field" element
     */
    public void setCustomFieldArray(int i, com.ats.aempi.configuration.xml.CustomField customField)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.CustomField target = null;
            target = (com.ats.aempi.configuration.xml.CustomField)get_store().find_element_user(CUSTOMFIELD$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(customField);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "custom-field" element
     */
    public com.ats.aempi.configuration.xml.CustomField insertNewCustomField(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.CustomField target = null;
            target = (com.ats.aempi.configuration.xml.CustomField)get_store().insert_element_user(CUSTOMFIELD$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "custom-field" element
     */
    public com.ats.aempi.configuration.xml.CustomField addNewCustomField()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.CustomField target = null;
            target = (com.ats.aempi.configuration.xml.CustomField)get_store().add_element_user(CUSTOMFIELD$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "custom-field" element
     */
    public void removeCustomField(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CUSTOMFIELD$0, i);
        }
    }
}
