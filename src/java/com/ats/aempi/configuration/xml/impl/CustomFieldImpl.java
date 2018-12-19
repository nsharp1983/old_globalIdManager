package com.ats.aempi.configuration.xml.impl;
/**
 * An XML custom-field(@http://configuration.openempi.openhie.org/mpiconfig).
 *
 * This is a complex type.
 */
public class CustomFieldImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.ats.aempi.configuration.xml.CustomField
{
    
    public CustomFieldImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FIELDNAME$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "field-name");
    private static final javax.xml.namespace.QName SOURCEFIELDNAME$2 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "source-field-name");
    private static final javax.xml.namespace.QName TRANSFORMATIONFUNCTIONNAME$4 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "transformation-function-name");
    
    
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
     * Gets the "source-field-name" element
     */
    public java.lang.String getSourceFieldName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SOURCEFIELDNAME$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "source-field-name" element
     */
    public org.apache.xmlbeans.XmlString xgetSourceFieldName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(SOURCEFIELDNAME$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "source-field-name" element
     */
    public void setSourceFieldName(java.lang.String sourceFieldName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SOURCEFIELDNAME$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SOURCEFIELDNAME$2);
            }
            target.setStringValue(sourceFieldName);
        }
    }
    
    /**
     * Sets (as xml) the "source-field-name" element
     */
    public void xsetSourceFieldName(org.apache.xmlbeans.XmlString sourceFieldName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(SOURCEFIELDNAME$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(SOURCEFIELDNAME$2);
            }
            target.set(sourceFieldName);
        }
    }
    
    /**
     * Gets the "transformation-function-name" element
     */
    public java.lang.String getTransformationFunctionName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TRANSFORMATIONFUNCTIONNAME$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "transformation-function-name" element
     */
    public org.apache.xmlbeans.XmlString xgetTransformationFunctionName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TRANSFORMATIONFUNCTIONNAME$4, 0);
            return target;
        }
    }
    
    /**
     * Sets the "transformation-function-name" element
     */
    public void setTransformationFunctionName(java.lang.String transformationFunctionName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TRANSFORMATIONFUNCTIONNAME$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TRANSFORMATIONFUNCTIONNAME$4);
            }
            target.setStringValue(transformationFunctionName);
        }
    }
    
    /**
     * Sets (as xml) the "transformation-function-name" element
     */
    public void xsetTransformationFunctionName(org.apache.xmlbeans.XmlString transformationFunctionName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TRANSFORMATIONFUNCTIONNAME$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TRANSFORMATIONFUNCTIONNAME$4);
            }
            target.set(transformationFunctionName);
        }
    }
}
