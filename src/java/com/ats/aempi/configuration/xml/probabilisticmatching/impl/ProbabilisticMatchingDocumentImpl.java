package com.ats.aempi.configuration.xml.probabilisticmatching.impl;
/**
 * A document containing one probabilistic-matching(@http://configuration.openempi.openhie.org/probabilistic-matching) element.
 *
 * This is a complex type.
 */
public class ProbabilisticMatchingDocumentImpl extends com.ats.aempi.configuration.xml.impl.MatchingConfigurationDocumentImpl implements com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingDocument
{
    
    public ProbabilisticMatchingDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName PROBABILISTICMATCHING$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/probabilistic-matching", "probabilistic-matching");
    
    
    /**
     * Gets the "probabilistic-matching" element
     */
    public com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType getProbabilisticMatching()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType target = null;
            target = (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType)get_store().find_element_user(PROBABILISTICMATCHING$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "probabilistic-matching" element
     */
    public void setProbabilisticMatching(com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType probabilisticMatching)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType target = null;
            target = (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType)get_store().find_element_user(PROBABILISTICMATCHING$0, 0);
            if (target == null)
            {
                target = (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType)get_store().add_element_user(PROBABILISTICMATCHING$0);
            }
            target.set(probabilisticMatching);
        }
    }
    
    /**
     * Appends and returns a new empty "probabilistic-matching" element
     */
    public com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType addNewProbabilisticMatching()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType target = null;
            target = (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType)get_store().add_element_user(PROBABILISTICMATCHING$0);
            return target;
        }
    }
}
