package com.ats.aempi.configuration.xml.impl;
/**
 * A document containing one mpi-config(@http://configuration.openempi.openhie.org/mpiconfig) element.
 *
 * This is a complex type.
 */
public class MpiConfigDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.ats.aempi.configuration.xml.MpiConfigDocument
{
    
    public MpiConfigDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName MPICONFIG$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "mpi-config");
    
    
    /**
     * Gets the "mpi-config" element
     */
    public com.ats.aempi.configuration.xml.MpiConfigDocument.MpiConfig getMpiConfig()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.MpiConfigDocument.MpiConfig target = null;
            target = (com.ats.aempi.configuration.xml.MpiConfigDocument.MpiConfig)get_store().find_element_user(MPICONFIG$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "mpi-config" element
     */
    public void setMpiConfig(com.ats.aempi.configuration.xml.MpiConfigDocument.MpiConfig mpiConfig)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.MpiConfigDocument.MpiConfig target = null;
            target = (com.ats.aempi.configuration.xml.MpiConfigDocument.MpiConfig)get_store().find_element_user(MPICONFIG$0, 0);
            if (target == null)
            {
                target = (com.ats.aempi.configuration.xml.MpiConfigDocument.MpiConfig)get_store().add_element_user(MPICONFIG$0);
            }
            target.set(mpiConfig);
        }
    }
    
    /**
     * Appends and returns a new empty "mpi-config" element
     */
    public com.ats.aempi.configuration.xml.MpiConfigDocument.MpiConfig addNewMpiConfig()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.MpiConfigDocument.MpiConfig target = null;
            target = (com.ats.aempi.configuration.xml.MpiConfigDocument.MpiConfig)get_store().add_element_user(MPICONFIG$0);
            return target;
        }
    }
    /**
     * An XML mpi-config(@http://configuration.openempi.openhie.org/mpiconfig).
     *
     * This is a complex type.
     */
    public static class MpiConfigImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.ats.aempi.configuration.xml.MpiConfigDocument.MpiConfig
    {
        
        public MpiConfigImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName GLOBALIDENTIFIER$0 = 
            new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "global-identifier");
        private static final javax.xml.namespace.QName FILELOADERCONFIGURATION$2 = 
            new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "file-loader-configuration");
        private static final org.apache.xmlbeans.QNameSet FILELOADERCONFIGURATION$3 = org.apache.xmlbeans.QNameSet.forArray( new javax.xml.namespace.QName[] { 
            new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/file-loader", "file-loader"),
            new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "file-loader-configuration"),
        });
        private static final javax.xml.namespace.QName CUSTOMFIELDS$4 = 
            new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "custom-fields");
        private static final javax.xml.namespace.QName BLOCKINGCONFIGURATION$6 = 
            new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "blocking-configuration");
        private static final org.apache.xmlbeans.QNameSet BLOCKINGCONFIGURATION$7 = org.apache.xmlbeans.QNameSet.forArray( new javax.xml.namespace.QName[] { 
            new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "blocking-configuration"),
            new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/basic-blocking", "basic-blocking"),
        });
        private static final javax.xml.namespace.QName MATCHINGCONFIGURATION$8 = 
            new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "matching-configuration");
        private static final org.apache.xmlbeans.QNameSet MATCHINGCONFIGURATION$9 = org.apache.xmlbeans.QNameSet.forArray( new javax.xml.namespace.QName[] { 
            new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/probabilistic-matching", "probabilistic-matching"),
            new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/exact-matching", "exact-matching"),
            new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "matching-configuration"),
        });
        private static final javax.xml.namespace.QName ADMINCONFIGURATION$10 = 
            new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpiconfig", "admin-configuration");
        
        
        /**
         * Gets the "global-identifier" element
         */
        public com.ats.aempi.configuration.xml.GlobalIdentifier getGlobalIdentifier()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.ats.aempi.configuration.xml.GlobalIdentifier target = null;
                target = (com.ats.aempi.configuration.xml.GlobalIdentifier)get_store().find_element_user(GLOBALIDENTIFIER$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "global-identifier" element
         */
        public boolean isSetGlobalIdentifier()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(GLOBALIDENTIFIER$0) != 0;
            }
        }
        
        /**
         * Sets the "global-identifier" element
         */
        public void setGlobalIdentifier(com.ats.aempi.configuration.xml.GlobalIdentifier globalIdentifier)
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.ats.aempi.configuration.xml.GlobalIdentifier target = null;
                target = (com.ats.aempi.configuration.xml.GlobalIdentifier)get_store().find_element_user(GLOBALIDENTIFIER$0, 0);
                if (target == null)
                {
                    target = (com.ats.aempi.configuration.xml.GlobalIdentifier)get_store().add_element_user(GLOBALIDENTIFIER$0);
                }
                target.set(globalIdentifier);
            }
        }
        
        /**
         * Appends and returns a new empty "global-identifier" element
         */
        public com.ats.aempi.configuration.xml.GlobalIdentifier addNewGlobalIdentifier()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.ats.aempi.configuration.xml.GlobalIdentifier target = null;
                target = (com.ats.aempi.configuration.xml.GlobalIdentifier)get_store().add_element_user(GLOBALIDENTIFIER$0);
                return target;
            }
        }
        
        /**
         * Unsets the "global-identifier" element
         */
        public void unsetGlobalIdentifier()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(GLOBALIDENTIFIER$0, 0);
            }
        }
        
        /**
         * Gets the "file-loader-configuration" element
         */
        public com.ats.aempi.configuration.xml.FileLoaderConfigurationType getFileLoaderConfiguration()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.ats.aempi.configuration.xml.FileLoaderConfigurationType target = null;
                target = (com.ats.aempi.configuration.xml.FileLoaderConfigurationType)get_store().find_element_user(FILELOADERCONFIGURATION$3, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "file-loader-configuration" element
         */
        public void setFileLoaderConfiguration(com.ats.aempi.configuration.xml.FileLoaderConfigurationType fileLoaderConfiguration)
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.ats.aempi.configuration.xml.FileLoaderConfigurationType target = null;
                target = (com.ats.aempi.configuration.xml.FileLoaderConfigurationType)get_store().find_element_user(FILELOADERCONFIGURATION$3, 0);
                if (target == null)
                {
                    target = (com.ats.aempi.configuration.xml.FileLoaderConfigurationType)get_store().add_element_user(FILELOADERCONFIGURATION$2);
                }
                target.set(fileLoaderConfiguration);
            }
        }
        
        /**
         * Appends and returns a new empty "file-loader-configuration" element
         */
        public com.ats.aempi.configuration.xml.FileLoaderConfigurationType addNewFileLoaderConfiguration()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.ats.aempi.configuration.xml.FileLoaderConfigurationType target = null;
                target = (com.ats.aempi.configuration.xml.FileLoaderConfigurationType)get_store().add_element_user(FILELOADERCONFIGURATION$2);
                return target;
            }
        }
        
        /**
         * Gets the "custom-fields" element
         */
        public com.ats.aempi.configuration.xml.CustomFields getCustomFields()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.ats.aempi.configuration.xml.CustomFields target = null;
                target = (com.ats.aempi.configuration.xml.CustomFields)get_store().find_element_user(CUSTOMFIELDS$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "custom-fields" element
         */
        public boolean isSetCustomFields()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(CUSTOMFIELDS$4) != 0;
            }
        }
        
        /**
         * Sets the "custom-fields" element
         */
        public void setCustomFields(com.ats.aempi.configuration.xml.CustomFields customFields)
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.ats.aempi.configuration.xml.CustomFields target = null;
                target = (com.ats.aempi.configuration.xml.CustomFields)get_store().find_element_user(CUSTOMFIELDS$4, 0);
                if (target == null)
                {
                    target = (com.ats.aempi.configuration.xml.CustomFields)get_store().add_element_user(CUSTOMFIELDS$4);
                }
                target.set(customFields);
            }
        }
        
        /**
         * Appends and returns a new empty "custom-fields" element
         */
        public com.ats.aempi.configuration.xml.CustomFields addNewCustomFields()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.ats.aempi.configuration.xml.CustomFields target = null;
                target = (com.ats.aempi.configuration.xml.CustomFields)get_store().add_element_user(CUSTOMFIELDS$4);
                return target;
            }
        }
        
        /**
         * Unsets the "custom-fields" element
         */
        public void unsetCustomFields()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(CUSTOMFIELDS$4, 0);
            }
        }
        
        /**
         * Gets the "blocking-configuration" element
         */
        public com.ats.aempi.configuration.xml.BlockingConfigurationType getBlockingConfiguration()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.ats.aempi.configuration.xml.BlockingConfigurationType target = null;
                target = (com.ats.aempi.configuration.xml.BlockingConfigurationType)get_store().find_element_user(BLOCKINGCONFIGURATION$7, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "blocking-configuration" element
         */
        public void setBlockingConfiguration(com.ats.aempi.configuration.xml.BlockingConfigurationType blockingConfiguration)
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.ats.aempi.configuration.xml.BlockingConfigurationType target = null;
                target = (com.ats.aempi.configuration.xml.BlockingConfigurationType)get_store().find_element_user(BLOCKINGCONFIGURATION$7, 0);
                if (target == null)
                {
                    target = (com.ats.aempi.configuration.xml.BlockingConfigurationType)get_store().add_element_user(BLOCKINGCONFIGURATION$6);
                }
                target.set(blockingConfiguration);
            }
        }
        
        /**
         * Appends and returns a new empty "blocking-configuration" element
         */
        public com.ats.aempi.configuration.xml.BlockingConfigurationType addNewBlockingConfiguration()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.ats.aempi.configuration.xml.BlockingConfigurationType target = null;
                target = (com.ats.aempi.configuration.xml.BlockingConfigurationType)get_store().add_element_user(BLOCKINGCONFIGURATION$6);
                return target;
            }
        }
        
        /**
         * Gets the "matching-configuration" element
         */
        public com.ats.aempi.configuration.xml.MatchingConfigurationType getMatchingConfiguration()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.ats.aempi.configuration.xml.MatchingConfigurationType target = null;
                target = (com.ats.aempi.configuration.xml.MatchingConfigurationType)get_store().find_element_user(MATCHINGCONFIGURATION$9, 0);
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
                target = (com.ats.aempi.configuration.xml.MatchingConfigurationType)get_store().find_element_user(MATCHINGCONFIGURATION$9, 0);
                if (target == null)
                {
                    target = (com.ats.aempi.configuration.xml.MatchingConfigurationType)get_store().add_element_user(MATCHINGCONFIGURATION$8);
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
                target = (com.ats.aempi.configuration.xml.MatchingConfigurationType)get_store().add_element_user(MATCHINGCONFIGURATION$8);
                return target;
            }
        }
        
        /**
         * Gets the "admin-configuration" element
         */
        public com.ats.aempi.configuration.xml.AdminConfiguration getAdminConfiguration()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.ats.aempi.configuration.xml.AdminConfiguration target = null;
                target = (com.ats.aempi.configuration.xml.AdminConfiguration)get_store().find_element_user(ADMINCONFIGURATION$10, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "admin-configuration" element
         */
        public void setAdminConfiguration(com.ats.aempi.configuration.xml.AdminConfiguration adminConfiguration)
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.ats.aempi.configuration.xml.AdminConfiguration target = null;
                target = (com.ats.aempi.configuration.xml.AdminConfiguration)get_store().find_element_user(ADMINCONFIGURATION$10, 0);
                if (target == null)
                {
                    target = (com.ats.aempi.configuration.xml.AdminConfiguration)get_store().add_element_user(ADMINCONFIGURATION$10);
                }
                target.set(adminConfiguration);
            }
        }
        
        /**
         * Appends and returns a new empty "admin-configuration" element
         */
        public com.ats.aempi.configuration.xml.AdminConfiguration addNewAdminConfiguration()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.ats.aempi.configuration.xml.AdminConfiguration target = null;
                target = (com.ats.aempi.configuration.xml.AdminConfiguration)get_store().add_element_user(ADMINCONFIGURATION$10);
                return target;
            }
        }
    }
}
