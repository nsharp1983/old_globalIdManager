package com.ats.aempi.configuration.xml.exactmatching.impl;
/**
 * An XML match-field(@http://configuration.openempi.openhie.org/exact-matching).
 *
 * This is a complex type.
 */
public class MatchFieldImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.ats.aempi.configuration.xml.exactmatching.MatchField
{
    
    public MatchFieldImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FIELDNAME$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/exact-matching", "field-name");
    private static final javax.xml.namespace.QName COMPARATORFUNCTIONNAME$2 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/exact-matching", "comparator-function-name");
    
    
    /**
     * Gets the "field-name" element
     */
    public java.lang.String getFieldName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FIELDNAME$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "field-name" element
     */
    public org.apache.xmlbeans.XmlString xgetFieldName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(FIELDNAME$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "field-name" element
     */
    public void setFieldName(java.lang.String fieldName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FIELDNAME$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(FIELDNAME$0);
            }
            target.setStringValue(fieldName);
        }
    }
    
    /**
     * Sets (as xml) the "field-name" element
     */
    public void xsetFieldName(org.apache.xmlbeans.XmlString fieldName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(FIELDNAME$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(FIELDNAME$0);
            }
            target.set(fieldName);
        }
    }
    
    /**
     * Gets the "comparator-function-name" element
     */
    public java.lang.String getComparatorFunctionName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMPARATORFUNCTIONNAME$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "comparator-function-name" element
     */
    public org.apache.xmlbeans.XmlString xgetComparatorFunctionName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COMPARATORFUNCTIONNAME$2, 0);
            return target;
        }
    }
    
    /**
     * True if has "comparator-function-name" element
     */
    public boolean isSetComparatorFunctionName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(COMPARATORFUNCTIONNAME$2) != 0;
        }
    }
    
    /**
     * Sets the "comparator-function-name" element
     */
    public void setComparatorFunctionName(java.lang.String comparatorFunctionName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMPARATORFUNCTIONNAME$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COMPARATORFUNCTIONNAME$2);
            }
            target.setStringValue(comparatorFunctionName);
        }
    }
    
    /**
     * Sets (as xml) the "comparator-function-name" element
     */
    public void xsetComparatorFunctionName(org.apache.xmlbeans.XmlString comparatorFunctionName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COMPARATORFUNCTIONNAME$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(COMPARATORFUNCTIONNAME$2);
            }
            target.set(comparatorFunctionName);
        }
    }
    
    /**
     * Unsets the "comparator-function-name" element
     */
    public void unsetComparatorFunctionName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(COMPARATORFUNCTIONNAME$2, 0);
        }
    }
}
