package com.ats.aempi.configuration.xml.impl;
/**
 * An XML match-fields(@http://configuration.openempi.openhie.org/mpiconfig).
 *
 * This is a complex type.
 */
public class MatchFieldsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.ats.aempi.configuration.xml.MatchFields
{
    
    public MatchFieldsImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName MATCHFIELD$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "match-field");
    
    
    /**
     * Gets array of all "match-field" elements
     */
    public com.ats.aempi.configuration.xml.MatchField[] getMatchFieldArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(MATCHFIELD$0, targetList);
            com.ats.aempi.configuration.xml.MatchField[] result = new com.ats.aempi.configuration.xml.MatchField[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "match-field" element
     */
    public com.ats.aempi.configuration.xml.MatchField getMatchFieldArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.MatchField target = null;
            target = (com.ats.aempi.configuration.xml.MatchField)get_store().find_element_user(MATCHFIELD$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "match-field" element
     */
    public int sizeOfMatchFieldArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(MATCHFIELD$0);
        }
    }
    
    /**
     * Sets array of all "match-field" element
     */
    public void setMatchFieldArray(com.ats.aempi.configuration.xml.MatchField[] matchFieldArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(matchFieldArray, MATCHFIELD$0);
        }
    }
    
    /**
     * Sets ith "match-field" element
     */
    public void setMatchFieldArray(int i, com.ats.aempi.configuration.xml.MatchField matchField)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.MatchField target = null;
            target = (com.ats.aempi.configuration.xml.MatchField)get_store().find_element_user(MATCHFIELD$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(matchField);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "match-field" element
     */
    public com.ats.aempi.configuration.xml.MatchField insertNewMatchField(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.MatchField target = null;
            target = (com.ats.aempi.configuration.xml.MatchField)get_store().insert_element_user(MATCHFIELD$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "match-field" element
     */
    public com.ats.aempi.configuration.xml.MatchField addNewMatchField()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.MatchField target = null;
            target = (com.ats.aempi.configuration.xml.MatchField)get_store().add_element_user(MATCHFIELD$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "match-field" element
     */
    public void removeMatchField(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(MATCHFIELD$0, i);
        }
    }
}
