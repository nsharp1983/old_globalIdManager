package com.ats.aempi.configuration.xml.impl;
/**
 * A document containing one matching-configuration(@http://configuration.openempi.openhie.org/mpiconfig) element.
 *
 * This is a complex type.
 */
public class MatchingConfigurationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.ats.aempi.configuration.xml.MatchingConfigurationDocument
{
    
    public MatchingConfigurationDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName MATCHINGCONFIGURATION$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "matching-configuration");
    private static final org.apache.xmlbeans.QNameSet MATCHINGCONFIGURATION$1 = org.apache.xmlbeans.QNameSet.forArray( new javax.xml.namespace.QName[] { 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/probabilistic-matching", "probabilistic-matching"),
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/exact-matching", "exact-matching"),
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "matching-configuration"),
    });
    
    
    /**
     * Gets the "matching-configuration" element
     */
    public com.ats.aempi.configuration.xml.MatchingConfigurationType getMatchingConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.MatchingConfigurationType target = null;
            target = (com.ats.aempi.configuration.xml.MatchingConfigurationType)get_store().find_element_user(MATCHINGCONFIGURATION$1, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "matching-configuration" element
     */
    public void setMatchingConfiguration(com.ats.aempi.configuration.xml.MatchingConfigurationType matchingConfiguration)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.MatchingConfigurationType target = null;
            target = (com.ats.aempi.configuration.xml.MatchingConfigurationType)get_store().find_element_user(MATCHINGCONFIGURATION$1, 0);
            if (target == null)
            {
                target = (com.ats.aempi.configuration.xml.MatchingConfigurationType)get_store().add_element_user(MATCHINGCONFIGURATION$0);
            }
            target.set(matchingConfiguration);
        }
    }
    
    /**
     * Appends and returns a new empty "matching-configuration" element
     */
    public com.ats.aempi.configuration.xml.MatchingConfigurationType addNewMatchingConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.MatchingConfigurationType target = null;
            target = (com.ats.aempi.configuration.xml.MatchingConfigurationType)get_store().add_element_user(MATCHINGCONFIGURATION$0);
            return target;
        }
    }
}
