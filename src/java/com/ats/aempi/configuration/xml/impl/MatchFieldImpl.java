package com.ats.aempi.configuration.xml.impl;
/**
 * An XML match-field(@http://configuration.openempi.openhie.org/mpiconfig).
 *
 * This is a complex type.
 */
public class MatchFieldImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.ats.aempi.configuration.xml.MatchField
{
    
    public MatchFieldImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FIELDNAME$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "field-name");
    private static final javax.xml.namespace.QName AGREEMENTPROBABILITY$2 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "agreement-probability");
    private static final javax.xml.namespace.QName DISAGREEMENTPROBABILITY$4 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "disagreement-probability");
    private static final javax.xml.namespace.QName COMPARATORFUNCTIONNAME$6 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "comparator-function-name");
    private static final javax.xml.namespace.QName MATCHTHRESHOLD$8 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "match-threshold");
    
    
    /**
     * Gets the "field-name" element
     */
    public java.lang.String getFieldName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FIELDNAME$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "field-name" element
     */
    public org.apache.xmlbeans.XmlString xgetFieldName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(FIELDNAME$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "field-name" element
     */
    public void setFieldName(java.lang.String fieldName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FIELDNAME$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(FIELDNAME$0);
            }
            target.setStringValue(fieldName);
        }
    }
    
    /**
     * Sets (as xml) the "field-name" element
     */
    public void xsetFieldName(org.apache.xmlbeans.XmlString fieldName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(FIELDNAME$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(FIELDNAME$0);
            }
            target.set(fieldName);
        }
    }
    
    /**
     * Gets the "agreement-probability" element
     */
    public float getAgreementProbability()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AGREEMENTPROBABILITY$2, 0);
            if (target == null)
            {
                return 0.0f;
            }
            return target.getFloatValue();
        }
    }
    
    /**
     * Gets (as xml) the "agreement-probability" element
     */
    public org.apache.xmlbeans.XmlFloat xgetAgreementProbability()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlFloat target = null;
            target = (org.apache.xmlbeans.XmlFloat)get_store().find_element_user(AGREEMENTPROBABILITY$2, 0);
            return target;
        }
    }
    
    /**
     * True if has "agreement-probability" element
     */
    public boolean isSetAgreementProbability()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(AGREEMENTPROBABILITY$2) != 0;
        }
    }
    
    /**
     * Sets the "agreement-probability" element
     */
    public void setAgreementProbability(float agreementProbability)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AGREEMENTPROBABILITY$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(AGREEMENTPROBABILITY$2);
            }
            target.setFloatValue(agreementProbability);
        }
    }
    
    /**
     * Sets (as xml) the "agreement-probability" element
     */
    public void xsetAgreementProbability(org.apache.xmlbeans.XmlFloat agreementProbability)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlFloat target = null;
            target = (org.apache.xmlbeans.XmlFloat)get_store().find_element_user(AGREEMENTPROBABILITY$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlFloat)get_store().add_element_user(AGREEMENTPROBABILITY$2);
            }
            target.set(agreementProbability);
        }
    }
    
    /**
     * Unsets the "agreement-probability" element
     */
    public void unsetAgreementProbability()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(AGREEMENTPROBABILITY$2, 0);
        }
    }
    
    /**
     * Gets the "disagreement-probability" element
     */
    public float getDisagreementProbability()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DISAGREEMENTPROBABILITY$4, 0);
            if (target == null)
            {
                return 0.0f;
            }
            return target.getFloatValue();
        }
    }
    
    /**
     * Gets (as xml) the "disagreement-probability" element
     */
    public org.apache.xmlbeans.XmlFloat xgetDisagreementProbability()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlFloat target = null;
            target = (org.apache.xmlbeans.XmlFloat)get_store().find_element_user(DISAGREEMENTPROBABILITY$4, 0);
            return target;
        }
    }
    
    /**
     * True if has "disagreement-probability" element
     */
    public boolean isSetDisagreementProbability()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DISAGREEMENTPROBABILITY$4) != 0;
        }
    }
    
    /**
     * Sets the "disagreement-probability" element
     */
    public void setDisagreementProbability(float disagreementProbability)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DISAGREEMENTPROBABILITY$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DISAGREEMENTPROBABILITY$4);
            }
            target.setFloatValue(disagreementProbability);
        }
    }
    
    /**
     * Sets (as xml) the "disagreement-probability" element
     */
    public void xsetDisagreementProbability(org.apache.xmlbeans.XmlFloat disagreementProbability)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlFloat target = null;
            target = (org.apache.xmlbeans.XmlFloat)get_store().find_element_user(DISAGREEMENTPROBABILITY$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlFloat)get_store().add_element_user(DISAGREEMENTPROBABILITY$4);
            }
            target.set(disagreementProbability);
        }
    }
    
    /**
     * Unsets the "disagreement-probability" element
     */
    public void unsetDisagreementProbability()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DISAGREEMENTPROBABILITY$4, 0);
        }
    }
    
    /**
     * Gets the "comparator-function-name" element
     */
    public java.lang.String getComparatorFunctionName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMPARATORFUNCTIONNAME$6, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "comparator-function-name" element
     */
    public org.apache.xmlbeans.XmlString xgetComparatorFunctionName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COMPARATORFUNCTIONNAME$6, 0);
            return target;
        }
    }
    
    /**
     * True if has "comparator-function-name" element
     */
    public boolean isSetComparatorFunctionName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(COMPARATORFUNCTIONNAME$6) != 0;
        }
    }
    
    /**
     * Sets the "comparator-function-name" element
     */
    public void setComparatorFunctionName(java.lang.String comparatorFunctionName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMPARATORFUNCTIONNAME$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COMPARATORFUNCTIONNAME$6);
            }
            target.setStringValue(comparatorFunctionName);
        }
    }
    
    /**
     * Sets (as xml) the "comparator-function-name" element
     */
    public void xsetComparatorFunctionName(org.apache.xmlbeans.XmlString comparatorFunctionName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COMPARATORFUNCTIONNAME$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(COMPARATORFUNCTIONNAME$6);
            }
            target.set(comparatorFunctionName);
        }
    }
    
    /**
     * Unsets the "comparator-function-name" element
     */
    public void unsetComparatorFunctionName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(COMPARATORFUNCTIONNAME$6, 0);
        }
    }
    
    /**
     * Gets the "match-threshold" element
     */
    public float getMatchThreshold()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MATCHTHRESHOLD$8, 0);
            if (target == null)
            {
                return 0.0f;
            }
            return target.getFloatValue();
        }
    }
    
    /**
     * Gets (as xml) the "match-threshold" element
     */
    public org.apache.xmlbeans.XmlFloat xgetMatchThreshold()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlFloat target = null;
            target = (org.apache.xmlbeans.XmlFloat)get_store().find_element_user(MATCHTHRESHOLD$8, 0);
            return target;
        }
    }
    
    /**
     * True if has "match-threshold" element
     */
    public boolean isSetMatchThreshold()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(MATCHTHRESHOLD$8) != 0;
        }
    }
    
    /**
     * Sets the "match-threshold" element
     */
    public void setMatchThreshold(float matchThreshold)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MATCHTHRESHOLD$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(MATCHTHRESHOLD$8);
            }
            target.setFloatValue(matchThreshold);
        }
    }
    
    /**
     * Sets (as xml) the "match-threshold" element
     */
    public void xsetMatchThreshold(org.apache.xmlbeans.XmlFloat matchThreshold)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlFloat target = null;
            target = (org.apache.xmlbeans.XmlFloat)get_store().find_element_user(MATCHTHRESHOLD$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlFloat)get_store().add_element_user(MATCHTHRESHOLD$8);
            }
            target.set(matchThreshold);
        }
    }
    
    /**
     * Unsets the "match-threshold" element
     */
    public void unsetMatchThreshold()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(MATCHTHRESHOLD$8, 0);
        }
    }
}
