package com.ats.aempi.configuration.xml.fileloader.impl;
/**
 * A document containing one file-loader(@http://configuration.openempi.openhie.org/file-loader) element.
 *
 * This is a complex type.
 */
public class FileLoaderDocumentImpl extends com.ats.aempi.configuration.xml.impl.FileLoaderConfigurationDocumentImpl implements com.ats.aempi.configuration.xml.fileloader.FileLoaderDocument
{
    
    public FileLoaderDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FILELOADER$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/file-loader", "file-loader");
    
    
    /**
     * Gets the "file-loader" element
     */
    public com.ats.aempi.configuration.xml.fileloader.FileLoaderType getFileLoader()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.fileloader.FileLoaderType target = null;
            target = (com.ats.aempi.configuration.xml.fileloader.FileLoaderType)get_store().find_element_user(FILELOADER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "file-loader" element
     */
    public void setFileLoader(com.ats.aempi.configuration.xml.fileloader.FileLoaderType fileLoader)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.fileloader.FileLoaderType target = null;
            target = (com.ats.aempi.configuration.xml.fileloader.FileLoaderType)get_store().find_element_user(FILELOADER$0, 0);
            if (target == null)
            {
                target = (com.ats.aempi.configuration.xml.fileloader.FileLoaderType)get_store().add_element_user(FILELOADER$0);
            }
            target.set(fileLoader);
        }
    }
    
    /**
     * Appends and returns a new empty "file-loader" element
     */
    public com.ats.aempi.configuration.xml.fileloader.FileLoaderType addNewFileLoader()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.fileloader.FileLoaderType target = null;
            target = (com.ats.aempi.configuration.xml.fileloader.FileLoaderType)get_store().add_element_user(FILELOADER$0);
            return target;
        }
    }
}
