package com.ats.aempi.configuration.xml.exactmatching.impl;
/**
 * An XML exact-matching-type(@http://configuration.openempi.openhie.org/exact-matching).
 *
 * This is a complex type.
 */
public class ExactMatchingTypeImpl extends com.ats.aempi.configuration.xml.impl.MatchingConfigurationTypeImpl implements com.ats.aempi.configuration.xml.exactmatching.ExactMatchingType
{
    
    public ExactMatchingTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName MATCHFIELDS$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/exact-matching", "match-fields");
    
    
    /**
     * Gets the "match-fields" element
     */
    public com.ats.aempi.configuration.xml.exactmatching.MatchFields getMatchFields()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.exactmatching.MatchFields target = null;
            target = (com.ats.aempi.configuration.xml.exactmatching.MatchFields)get_store().find_element_user(MATCHFIELDS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "match-fields" element
     */
    public void setMatchFields(com.ats.aempi.configuration.xml.exactmatching.MatchFields matchFields)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.exactmatching.MatchFields target = null;
            target = (com.ats.aempi.configuration.xml.exactmatching.MatchFields)get_store().find_element_user(MATCHFIELDS$0, 0);
            if (target == null)
            {
                target = (com.ats.aempi.configuration.xml.exactmatching.MatchFields)get_store().add_element_user(MATCHFIELDS$0);
            }
            target.set(matchFields);
        }
    }
    
    /**
     * Appends and returns a new empty "match-fields" element
     */
    public com.ats.aempi.configuration.xml.exactmatching.MatchFields addNewMatchFields()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.exactmatching.MatchFields target = null;
            target = (com.ats.aempi.configuration.xml.exactmatching.MatchFields)get_store().add_element_user(MATCHFIELDS$0);
            return target;
        }
    }
}
