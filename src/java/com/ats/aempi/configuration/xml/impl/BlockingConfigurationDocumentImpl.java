package com.ats.aempi.configuration.xml.impl;
/**
 * A document containing one blocking-configuration(@http://configuration.openempi.openhie.org/mpiconfig) element.
 *
 * This is a complex type.
 */
public class BlockingConfigurationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.ats.aempi.configuration.xml.BlockingConfigurationDocument
{
    
    public BlockingConfigurationDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName BLOCKINGCONFIGURATION$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "blocking-configuration");
    private static final org.apache.xmlbeans.QNameSet BLOCKINGCONFIGURATION$1 = org.apache.xmlbeans.QNameSet.forArray( new javax.xml.namespace.QName[] { 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "blocking-configuration"),
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/basic-blocking", "basic-blocking"),
    });
    
    
    /**
     * Gets the "blocking-configuration" element
     */
    public com.ats.aempi.configuration.xml.BlockingConfigurationType getBlockingConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.BlockingConfigurationType target = null;
            target = (com.ats.aempi.configuration.xml.BlockingConfigurationType)get_store().find_element_user(BLOCKINGCONFIGURATION$1, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "blocking-configuration" element
     */
    public void setBlockingConfiguration(com.ats.aempi.configuration.xml.BlockingConfigurationType blockingConfiguration)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.BlockingConfigurationType target = null;
            target = (com.ats.aempi.configuration.xml.BlockingConfigurationType)get_store().find_element_user(BLOCKINGCONFIGURATION$1, 0);
            if (target == null)
            {
                target = (com.ats.aempi.configuration.xml.BlockingConfigurationType)get_store().add_element_user(BLOCKINGCONFIGURATION$0);
            }
            target.set(blockingConfiguration);
        }
    }
    
    /**
     * Appends and returns a new empty "blocking-configuration" element
     */
    public com.ats.aempi.configuration.xml.BlockingConfigurationType addNewBlockingConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.BlockingConfigurationType target = null;
            target = (com.ats.aempi.configuration.xml.BlockingConfigurationType)get_store().add_element_user(BLOCKINGCONFIGURATION$0);
            return target;
        }
    }
}
