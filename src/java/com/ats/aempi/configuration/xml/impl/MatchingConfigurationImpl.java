package com.ats.aempi.configuration.xml.impl;
/**
 * An XML matching-configuration(@http://configuration.openempi.openhie.org/mpiconfig).
 *
 * This is a complex type.
 */
public class MatchingConfigurationImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.ats.aempi.configuration.xml.MatchingConfiguration
{
    
    public MatchingConfigurationImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FALSENEGATIVEPROBABILITY$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "false-negative-probability");
    private static final javax.xml.namespace.QName FALSEPOSITIVEPROBABILITY$2 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "false-positive-probability");
    private static final javax.xml.namespace.QName MATCHFIELDS$4 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "match-fields");
    private static final javax.xml.namespace.QName CONFIGFILEDIRECTORY$6 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "config-file-directory");
    
    
    /**
     * Gets the "false-negative-probability" element
     */
    public float getFalseNegativeProbability()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FALSENEGATIVEPROBABILITY$0, 0);
            if (target == null)
            {
                return 0.0f;
            }
            return target.getFloatValue();
        }
    }
    
    /**
     * Gets (as xml) the "false-negative-probability" element
     */
    public org.apache.xmlbeans.XmlFloat xgetFalseNegativeProbability()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlFloat target = null;
            target = (org.apache.xmlbeans.XmlFloat)get_store().find_element_user(FALSENEGATIVEPROBABILITY$0, 0);
            return target;
        }
    }
    
    /**
     * True if has "false-negative-probability" element
     */
    public boolean isSetFalseNegativeProbability()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(FALSENEGATIVEPROBABILITY$0) != 0;
        }
    }
    
    /**
     * Sets the "false-negative-probability" element
     */
    public void setFalseNegativeProbability(float falseNegativeProbability)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FALSENEGATIVEPROBABILITY$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(FALSENEGATIVEPROBABILITY$0);
            }
            target.setFloatValue(falseNegativeProbability);
        }
    }
    
    /**
     * Sets (as xml) the "false-negative-probability" element
     */
    public void xsetFalseNegativeProbability(org.apache.xmlbeans.XmlFloat falseNegativeProbability)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlFloat target = null;
            target = (org.apache.xmlbeans.XmlFloat)get_store().find_element_user(FALSENEGATIVEPROBABILITY$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlFloat)get_store().add_element_user(FALSENEGATIVEPROBABILITY$0);
            }
            target.set(falseNegativeProbability);
        }
    }
    
    /**
     * Unsets the "false-negative-probability" element
     */
    public void unsetFalseNegativeProbability()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(FALSENEGATIVEPROBABILITY$0, 0);
        }
    }
    
    /**
     * Gets the "false-positive-probability" element
     */
    public float getFalsePositiveProbability()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FALSEPOSITIVEPROBABILITY$2, 0);
            if (target == null)
            {
                return 0.0f;
            }
            return target.getFloatValue();
        }
    }
    
    /**
     * Gets (as xml) the "false-positive-probability" element
     */
    public org.apache.xmlbeans.XmlFloat xgetFalsePositiveProbability()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlFloat target = null;
            target = (org.apache.xmlbeans.XmlFloat)get_store().find_element_user(FALSEPOSITIVEPROBABILITY$2, 0);
            return target;
        }
    }
    
    /**
     * True if has "false-positive-probability" element
     */
    public boolean isSetFalsePositiveProbability()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(FALSEPOSITIVEPROBABILITY$2) != 0;
        }
    }
    
    /**
     * Sets the "false-positive-probability" element
     */
    public void setFalsePositiveProbability(float falsePositiveProbability)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FALSEPOSITIVEPROBABILITY$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(FALSEPOSITIVEPROBABILITY$2);
            }
            target.setFloatValue(falsePositiveProbability);
        }
    }
    
    /**
     * Sets (as xml) the "false-positive-probability" element
     */
    public void xsetFalsePositiveProbability(org.apache.xmlbeans.XmlFloat falsePositiveProbability)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlFloat target = null;
            target = (org.apache.xmlbeans.XmlFloat)get_store().find_element_user(FALSEPOSITIVEPROBABILITY$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlFloat)get_store().add_element_user(FALSEPOSITIVEPROBABILITY$2);
            }
            target.set(falsePositiveProbability);
        }
    }
    
    /**
     * Unsets the "false-positive-probability" element
     */
    public void unsetFalsePositiveProbability()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(FALSEPOSITIVEPROBABILITY$2, 0);
        }
    }
    
    /**
     * Gets the "match-fields" element
     */
    public com.ats.aempi.configuration.xml.MatchFields getMatchFields()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.MatchFields target = null;
            target = (com.ats.aempi.configuration.xml.MatchFields)get_store().find_element_user(MATCHFIELDS$4, 0);
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
    public void setMatchFields(com.ats.aempi.configuration.xml.MatchFields matchFields)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.MatchFields target = null;
            target = (com.ats.aempi.configuration.xml.MatchFields)get_store().find_element_user(MATCHFIELDS$4, 0);
            if (target == null)
            {
                target = (com.ats.aempi.configuration.xml.MatchFields)get_store().add_element_user(MATCHFIELDS$4);
            }
            target.set(matchFields);
        }
    }
    
    /**
     * Appends and returns a new empty "match-fields" element
     */
    public com.ats.aempi.configuration.xml.MatchFields addNewMatchFields()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.MatchFields target = null;
            target = (com.ats.aempi.configuration.xml.MatchFields)get_store().add_element_user(MATCHFIELDS$4);
            return target;
        }
    }
    
    /**
     * Gets the "config-file-directory" element
     */
    public java.lang.String getConfigFileDirectory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CONFIGFILEDIRECTORY$6, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "config-file-directory" element
     */
    public org.apache.xmlbeans.XmlString xgetConfigFileDirectory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CONFIGFILEDIRECTORY$6, 0);
            return target;
        }
    }
    
    /**
     * Sets the "config-file-directory" element
     */
    public void setConfigFileDirectory(java.lang.String configFileDirectory)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CONFIGFILEDIRECTORY$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CONFIGFILEDIRECTORY$6);
            }
            target.setStringValue(configFileDirectory);
        }
    }
    
    /**
     * Sets (as xml) the "config-file-directory" element
     */
    public void xsetConfigFileDirectory(org.apache.xmlbeans.XmlString configFileDirectory)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CONFIGFILEDIRECTORY$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CONFIGFILEDIRECTORY$6);
            }
            target.set(configFileDirectory);
        }
    }
}
