package com.ats.aempi.configuration.xml.basicblocking.impl;
/**
 * A document containing one basic-blocking(@http://configuration.openempi.openhie.org/basic-blocking) element.
 *
 * This is a complex type.
 */
public class BasicBlockingDocumentImpl extends com.ats.aempi.configuration.xml.impl.BlockingConfigurationDocumentImpl implements com.ats.aempi.configuration.xml.basicblocking.BasicBlockingDocument
{
    
    public BasicBlockingDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName BASICBLOCKING$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/basic-blocking", "basic-blocking");
    
    
    /**
     * Gets the "basic-blocking" element
     */
    public com.ats.aempi.configuration.xml.basicblocking.BasicBlockingType getBasicBlocking()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.basicblocking.BasicBlockingType target = null;
            target = (com.ats.aempi.configuration.xml.basicblocking.BasicBlockingType)get_store().find_element_user(BASICBLOCKING$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "basic-blocking" element
     */
    public void setBasicBlocking(com.ats.aempi.configuration.xml.basicblocking.BasicBlockingType basicBlocking)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.basicblocking.BasicBlockingType target = null;
            target = (com.ats.aempi.configuration.xml.basicblocking.BasicBlockingType)get_store().find_element_user(BASICBLOCKING$0, 0);
            if (target == null)
            {
                target = (com.ats.aempi.configuration.xml.basicblocking.BasicBlockingType)get_store().add_element_user(BASICBLOCKING$0);
            }
            target.set(basicBlocking);
        }
    }
    
    /**
     * Appends and returns a new empty "basic-blocking" element
     */
    public com.ats.aempi.configuration.xml.basicblocking.BasicBlockingType addNewBasicBlocking()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.basicblocking.BasicBlockingType target = null;
            target = (com.ats.aempi.configuration.xml.basicblocking.BasicBlockingType)get_store().add_element_user(BASICBLOCKING$0);
            return target;
        }
    }
}
