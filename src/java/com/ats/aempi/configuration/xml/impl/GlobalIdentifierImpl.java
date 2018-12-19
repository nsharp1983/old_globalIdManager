package com.ats.aempi.configuration.xml.impl;
/**
 * An XML global-identifier(@http://configuration.openempi.openhie.org/mpiconfig).
 *
 * This is a complex type.
 */
public class GlobalIdentifierImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.ats.aempi.configuration.xml.GlobalIdentifier
{
    
    public GlobalIdentifierImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ASSIGNGLOBALIDENTIFIER$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "assign-global-identifier");
    private static final javax.xml.namespace.QName NAMESPACEIDENTIFIER$2 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "namespace-identifier");
    private static final javax.xml.namespace.QName UNIVERSALIDENTIFIER$4 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "universal-identifier");
    private static final javax.xml.namespace.QName UNIVERSALIDENTIFIERTYPE$6 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "universal-identifier-type");
    
    
    /**
     * Gets the "assign-global-identifier" element
     */
    public boolean getAssignGlobalIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ASSIGNGLOBALIDENTIFIER$0, 0);
            if (target == null)
            {
                return false;
            }
            return target.getBooleanValue();
        }
    }
    
    /**
     * Gets (as xml) the "assign-global-identifier" element
     */
    public org.apache.xmlbeans.XmlBoolean xgetAssignGlobalIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBoolean target = null;
            target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(ASSIGNGLOBALIDENTIFIER$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "assign-global-identifier" element
     */
    public void setAssignGlobalIdentifier(boolean assignGlobalIdentifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ASSIGNGLOBALIDENTIFIER$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ASSIGNGLOBALIDENTIFIER$0);
            }
            target.setBooleanValue(assignGlobalIdentifier);
        }
    }
    
    /**
     * Sets (as xml) the "assign-global-identifier" element
     */
    public void xsetAssignGlobalIdentifier(org.apache.xmlbeans.XmlBoolean assignGlobalIdentifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBoolean target = null;
            target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(ASSIGNGLOBALIDENTIFIER$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(ASSIGNGLOBALIDENTIFIER$0);
            }
            target.set(assignGlobalIdentifier);
        }
    }
    
    /**
     * Gets the "namespace-identifier" element
     */
    public java.lang.String getNamespaceIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NAMESPACEIDENTIFIER$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "namespace-identifier" element
     */
    public org.apache.xmlbeans.XmlString xgetNamespaceIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NAMESPACEIDENTIFIER$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "namespace-identifier" element
     */
    public void setNamespaceIdentifier(java.lang.String namespaceIdentifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NAMESPACEIDENTIFIER$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(NAMESPACEIDENTIFIER$2);
            }
            target.setStringValue(namespaceIdentifier);
        }
    }
    
    /**
     * Sets (as xml) the "namespace-identifier" element
     */
    public void xsetNamespaceIdentifier(org.apache.xmlbeans.XmlString namespaceIdentifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NAMESPACEIDENTIFIER$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(NAMESPACEIDENTIFIER$2);
            }
            target.set(namespaceIdentifier);
        }
    }
    
    /**
     * Gets the "universal-identifier" element
     */
    public java.lang.String getUniversalIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(UNIVERSALIDENTIFIER$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "universal-identifier" element
     */
    public org.apache.xmlbeans.XmlString xgetUniversalIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(UNIVERSALIDENTIFIER$4, 0);
            return target;
        }
    }
    
    /**
     * Sets the "universal-identifier" element
     */
    public void setUniversalIdentifier(java.lang.String universalIdentifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(UNIVERSALIDENTIFIER$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(UNIVERSALIDENTIFIER$4);
            }
            target.setStringValue(universalIdentifier);
        }
    }
    
    /**
     * Sets (as xml) the "universal-identifier" element
     */
    public void xsetUniversalIdentifier(org.apache.xmlbeans.XmlString universalIdentifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(UNIVERSALIDENTIFIER$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(UNIVERSALIDENTIFIER$4);
            }
            target.set(universalIdentifier);
        }
    }
    
    /**
     * Gets the "universal-identifier-type" element
     */
    public java.lang.String getUniversalIdentifierType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(UNIVERSALIDENTIFIERTYPE$6, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "universal-identifier-type" element
     */
    public org.apache.xmlbeans.XmlString xgetUniversalIdentifierType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(UNIVERSALIDENTIFIERTYPE$6, 0);
            return target;
        }
    }
    
    /**
     * Sets the "universal-identifier-type" element
     */
    public void setUniversalIdentifierType(java.lang.String universalIdentifierType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(UNIVERSALIDENTIFIERTYPE$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(UNIVERSALIDENTIFIERTYPE$6);
            }
            target.setStringValue(universalIdentifierType);
        }
    }
    
    /**
     * Sets (as xml) the "universal-identifier-type" element
     */
    public void xsetUniversalIdentifierType(org.apache.xmlbeans.XmlString universalIdentifierType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(UNIVERSALIDENTIFIERTYPE$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(UNIVERSALIDENTIFIERTYPE$6);
            }
            target.set(universalIdentifierType);
        }
    }
}
