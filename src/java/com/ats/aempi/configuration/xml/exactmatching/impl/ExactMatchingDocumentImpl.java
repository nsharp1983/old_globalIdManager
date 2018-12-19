package com.ats.aempi.configuration.xml.exactmatching.impl;
/**
 * A document containing one exact-matching(@http://configuration.openempi.openhie.org/exact-matching) element.
 *
 * This is a complex type.
 */
public class ExactMatchingDocumentImpl extends com.ats.aempi.configuration.xml.impl.MatchingConfigurationDocumentImpl implements com.ats.aempi.configuration.xml.exactmatching.ExactMatchingDocument
{
    
    public ExactMatchingDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName EXACTMATCHING$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/exact-matching", "exact-matching");
    
    
    /**
     * Gets the "exact-matching" element
     */
    public com.ats.aempi.configuration.xml.exactmatching.ExactMatchingType getExactMatching()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.exactmatching.ExactMatchingType target = null;
            target = (com.ats.aempi.configuration.xml.exactmatching.ExactMatchingType)get_store().find_element_user(EXACTMATCHING$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "exact-matching" element
     */
    public void setExactMatching(com.ats.aempi.configuration.xml.exactmatching.ExactMatchingType exactMatching)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.exactmatching.ExactMatchingType target = null;
            target = (com.ats.aempi.configuration.xml.exactmatching.ExactMatchingType)get_store().find_element_user(EXACTMATCHING$0, 0);
            if (target == null)
            {
                target = (com.ats.aempi.configuration.xml.exactmatching.ExactMatchingType)get_store().add_element_user(EXACTMATCHING$0);
            }
            target.set(exactMatching);
        }
    }
    
    /**
     * Appends and returns a new empty "exact-matching" element
     */
    public com.ats.aempi.configuration.xml.exactmatching.ExactMatchingType addNewExactMatching()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.exactmatching.ExactMatchingType target = null;
            target = (com.ats.aempi.configuration.xml.exactmatching.ExactMatchingType)get_store().add_element_user(EXACTMATCHING$0);
            return target;
        }
    }
}
