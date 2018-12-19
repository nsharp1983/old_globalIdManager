package com.ats.aempi.configuration.xml.impl;
/**
 * An XML blocking-round(@http://configuration.openempi.openhie.org/mpiconfig).
 *
 * This is a complex type.
 */
public class BlockingRoundImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.ats.aempi.configuration.xml.BlockingRound
{
    
    public BlockingRoundImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName BLOCKINGFIELDS$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "blocking-fields");
    
    
    /**
     * Gets the "blocking-fields" element
     */
    public com.ats.aempi.configuration.xml.BlockingFields getBlockingFields()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.BlockingFields target = null;
            target = (com.ats.aempi.configuration.xml.BlockingFields)get_store().find_element_user(BLOCKINGFIELDS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "blocking-fields" element
     */
    public void setBlockingFields(com.ats.aempi.configuration.xml.BlockingFields blockingFields)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.BlockingFields target = null;
            target = (com.ats.aempi.configuration.xml.BlockingFields)get_store().find_element_user(BLOCKINGFIELDS$0, 0);
            if (target == null)
            {
                target = (com.ats.aempi.configuration.xml.BlockingFields)get_store().add_element_user(BLOCKINGFIELDS$0);
            }
            target.set(blockingFields);
        }
    }
    
    /**
     * Appends and returns a new empty "blocking-fields" element
     */
    public com.ats.aempi.configuration.xml.BlockingFields addNewBlockingFields()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.BlockingFields target = null;
            target = (com.ats.aempi.configuration.xml.BlockingFields)get_store().add_element_user(BLOCKINGFIELDS$0);
            return target;
        }
    }
}
