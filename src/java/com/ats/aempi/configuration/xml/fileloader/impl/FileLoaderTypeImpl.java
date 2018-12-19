package com.ats.aempi.configuration.xml.fileloader.impl;
/**
 * An XML file-loader-type(@http://configuration.openempi.openhie.org/file-loader).
 *
 * This is a complex type.
 */
public class FileLoaderTypeImpl extends com.ats.aempi.configuration.xml.impl.FileLoaderConfigurationTypeImpl implements com.ats.aempi.configuration.xml.fileloader.FileLoaderType
{
    
    public FileLoaderTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FILELOADERCONFIG$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/file-loader", "file-loader-config");
    
    
    /**
     * Gets the "file-loader-config" element
     */
    public com.ats.aempi.configuration.xml.fileloader.FileLoaderConfig getFileLoaderConfig()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.fileloader.FileLoaderConfig target = null;
            target = (com.ats.aempi.configuration.xml.fileloader.FileLoaderConfig)get_store().find_element_user(FILELOADERCONFIG$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "file-loader-config" element
     */
    public void setFileLoaderConfig(com.ats.aempi.configuration.xml.fileloader.FileLoaderConfig fileLoaderConfig)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.fileloader.FileLoaderConfig target = null;
            target = (com.ats.aempi.configuration.xml.fileloader.FileLoaderConfig)get_store().find_element_user(FILELOADERCONFIG$0, 0);
            if (target == null)
            {
                target = (com.ats.aempi.configuration.xml.fileloader.FileLoaderConfig)get_store().add_element_user(FILELOADERCONFIG$0);
            }
            target.set(fileLoaderConfig);
        }
    }
    
    /**
     * Appends and returns a new empty "file-loader-config" element
     */
    public com.ats.aempi.configuration.xml.fileloader.FileLoaderConfig addNewFileLoaderConfig()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.fileloader.FileLoaderConfig target = null;
            target = (com.ats.aempi.configuration.xml.fileloader.FileLoaderConfig)get_store().add_element_user(FILELOADERCONFIG$0);
            return target;
        }
    }
}
