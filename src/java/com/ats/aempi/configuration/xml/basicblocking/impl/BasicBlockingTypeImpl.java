package com.ats.aempi.configuration.xml.basicblocking.impl;
/**
 * An XML basic-blocking-type(@http://configuration.openempi.openhie.org/basic-blocking).
 *
 * This is a complex type.
 */
public class BasicBlockingTypeImpl extends com.ats.aempi.configuration.xml.impl.BlockingConfigurationTypeImpl implements com.ats.aempi.configuration.xml.basicblocking.BasicBlockingType
{
    
    public BasicBlockingTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName BLOCKINGROUNDS$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/basic-blocking", "blocking-rounds");
    
    
    /**
     * Gets the "blocking-rounds" element
     */
    public com.ats.aempi.configuration.xml.basicblocking.BlockingRounds getBlockingRounds()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.basicblocking.BlockingRounds target = null;
            target = (com.ats.aempi.configuration.xml.basicblocking.BlockingRounds)get_store().find_element_user(BLOCKINGROUNDS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "blocking-rounds" element
     */
    public void setBlockingRounds(com.ats.aempi.configuration.xml.basicblocking.BlockingRounds blockingRounds)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.basicblocking.BlockingRounds target = null;
            target = (com.ats.aempi.configuration.xml.basicblocking.BlockingRounds)get_store().find_element_user(BLOCKINGROUNDS$0, 0);
            if (target == null)
            {
                target = (com.ats.aempi.configuration.xml.basicblocking.BlockingRounds)get_store().add_element_user(BLOCKINGROUNDS$0);
            }
            target.set(blockingRounds);
        }
    }
    
    /**
     * Appends and returns a new empty "blocking-rounds" element
     */
    public com.ats.aempi.configuration.xml.basicblocking.BlockingRounds addNewBlockingRounds()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.basicblocking.BlockingRounds target = null;
            target = (com.ats.aempi.configuration.xml.basicblocking.BlockingRounds)get_store().add_element_user(BLOCKINGROUNDS$0);
            return target;
        }
    }
}
