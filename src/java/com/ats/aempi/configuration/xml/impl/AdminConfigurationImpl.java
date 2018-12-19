package com.ats.aempi.configuration.xml.impl;
/**
 * An XML admin-configuration(@http://configuration.openempi.openhie.org/mpiconfig).
 *
 * This is a complex type.
 */
public class AdminConfigurationImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.ats.aempi.configuration.xml.AdminConfiguration
{
    
    public AdminConfigurationImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FILEREPOSITORYDIRECTORY$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "file-repository-directory");
    private static final javax.xml.namespace.QName AUTOSTARTPIXPDQ$2 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "autostart-pixpdq");
    
    
    /**
     * Gets the "file-repository-directory" element
     */
    public java.lang.String getFileRepositoryDirectory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FILEREPOSITORYDIRECTORY$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "file-repository-directory" element
     */
    public org.apache.xmlbeans.XmlString xgetFileRepositoryDirectory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(FILEREPOSITORYDIRECTORY$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "file-repository-directory" element
     */
    public void setFileRepositoryDirectory(java.lang.String fileRepositoryDirectory)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FILEREPOSITORYDIRECTORY$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(FILEREPOSITORYDIRECTORY$0);
            }
            target.setStringValue(fileRepositoryDirectory);
        }
    }
    
    /**
     * Sets (as xml) the "file-repository-directory" element
     */
    public void xsetFileRepositoryDirectory(org.apache.xmlbeans.XmlString fileRepositoryDirectory)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(FILEREPOSITORYDIRECTORY$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(FILEREPOSITORYDIRECTORY$0);
            }
            target.set(fileRepositoryDirectory);
        }
    }
    
    /**
     * Gets the "autostart-pixpdq" element
     */
    public boolean getAutostartPixpdq()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AUTOSTARTPIXPDQ$2, 0);
            if (target == null)
            {
                return false;
            }
            return target.getBooleanValue();
        }
    }
    
    /**
     * Gets (as xml) the "autostart-pixpdq" element
     */
    public org.apache.xmlbeans.XmlBoolean xgetAutostartPixpdq()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBoolean target = null;
            target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(AUTOSTARTPIXPDQ$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "autostart-pixpdq" element
     */
    public void setAutostartPixpdq(boolean autostartPixpdq)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AUTOSTARTPIXPDQ$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(AUTOSTARTPIXPDQ$2);
            }
            target.setBooleanValue(autostartPixpdq);
        }
    }
    
    /**
     * Sets (as xml) the "autostart-pixpdq" element
     */
    public void xsetAutostartPixpdq(org.apache.xmlbeans.XmlBoolean autostartPixpdq)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBoolean target = null;
            target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(AUTOSTARTPIXPDQ$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(AUTOSTARTPIXPDQ$2);
            }
            target.set(autostartPixpdq);
        }
    }
}
