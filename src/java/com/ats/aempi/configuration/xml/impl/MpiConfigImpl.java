package com.ats.aempi.configuration.xml.impl;
/**
 * An XML mpi-config(@http://configuration.openempi.openhie.org/mpiconfig).
 *
 * This is a complex type.
 */
public class MpiConfigImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.ats.aempi.configuration.xml.MpiConfig
{
    
    public MpiConfigImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CUSTOMFIELDS$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "custom-fields");
    private static final javax.xml.namespace.QName BLOCKINGCONFIGURATION$2 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "blocking-configuration");
    
    
    /**
     * Gets the "custom-fields" element
     */
    public com.ats.aempi.configuration.xml.CustomFields getCustomFields()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.CustomFields target = null;
            target = (com.ats.aempi.configuration.xml.CustomFields)get_store().find_element_user(CUSTOMFIELDS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "custom-fields" element
     */
    public boolean isSetCustomFields()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CUSTOMFIELDS$0) != 0;
        }
    }
    
    /**
     * Sets the "custom-fields" element
     */
    public void setCustomFields(com.ats.aempi.configuration.xml.CustomFields customFields)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.CustomFields target = null;
            target = (com.ats.aempi.configuration.xml.CustomFields)get_store().find_element_user(CUSTOMFIELDS$0, 0);
            if (target == null)
            {
                target = (com.ats.aempi.configuration.xml.CustomFields)get_store().add_element_user(CUSTOMFIELDS$0);
            }
            target.set(customFields);
        }
    }
    
    /**
     * Appends and returns a new empty "custom-fields" element
     */
    public com.ats.aempi.configuration.xml.CustomFields addNewCustomFields()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.CustomFields target = null;
            target = (com.ats.aempi.configuration.xml.CustomFields)get_store().add_element_user(CUSTOMFIELDS$0);
            return target;
        }
    }
    
    /**
     * Unsets the "custom-fields" element
     */
    public void unsetCustomFields()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CUSTOMFIELDS$0, 0);
        }
    }
    
    /**
     * Gets the "blocking-configuration" element
     */
    public com.ats.aempi.configuration.xml.BlockingConfiguration getBlockingConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.BlockingConfiguration target = null;
            target = (com.ats.aempi.configuration.xml.BlockingConfiguration)get_store().find_element_user(BLOCKINGCONFIGURATION$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "blocking-configuration" element
     */
    public boolean isSetBlockingConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(BLOCKINGCONFIGURATION$2) != 0;
        }
    }
    
    /**
     * Sets the "blocking-configuration" element
     */
    public void setBlockingConfiguration(com.ats.aempi.configuration.xml.BlockingConfiguration blockingConfiguration)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.BlockingConfiguration target = null;
            target = (com.ats.aempi.configuration.xml.BlockingConfiguration)get_store().find_element_user(BLOCKINGCONFIGURATION$2, 0);
            if (target == null)
            {
                target = (com.ats.aempi.configuration.xml.BlockingConfiguration)get_store().add_element_user(BLOCKINGCONFIGURATION$2);
            }
            target.set(blockingConfiguration);
        }
    }
    
    /**
     * Appends and returns a new empty "blocking-configuration" element
     */
    public com.ats.aempi.configuration.xml.BlockingConfiguration addNewBlockingConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.BlockingConfiguration target = null;
            target = (com.ats.aempi.configuration.xml.BlockingConfiguration)get_store().add_element_user(BLOCKINGCONFIGURATION$2);
            return target;
        }
    }
    
    /**
     * Unsets the "blocking-configuration" element
     */
    public void unsetBlockingConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(BLOCKINGCONFIGURATION$2, 0);
        }
    }
}
