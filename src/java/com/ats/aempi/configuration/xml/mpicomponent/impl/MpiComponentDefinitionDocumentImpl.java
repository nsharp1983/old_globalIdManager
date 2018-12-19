package com.ats.aempi.configuration.xml.mpicomponent.impl;
/**
 * A document containing one mpi-component-definition(@http://configuration.openempi.openhie.org/mpicomponent) element.
 *
 * This is a complex type.
 */
public class MpiComponentDefinitionDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.ats.aempi.configuration.xml.mpicomponent.MpiComponentDefinitionDocument
{
    
    public MpiComponentDefinitionDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName MPICOMPONENTDEFINITION$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpicomponent", "mpi-component-definition");
    
    
    /**
     * Gets the "mpi-component-definition" element
     */
    public com.ats.aempi.configuration.xml.mpicomponent.MpiComponentDefinitionDocument.MpiComponentDefinition getMpiComponentDefinition()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.mpicomponent.MpiComponentDefinitionDocument.MpiComponentDefinition target = null;
            target = (com.ats.aempi.configuration.xml.mpicomponent.MpiComponentDefinitionDocument.MpiComponentDefinition)get_store().find_element_user(MPICOMPONENTDEFINITION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "mpi-component-definition" element
     */
    public void setMpiComponentDefinition(com.ats.aempi.configuration.xml.mpicomponent.MpiComponentDefinitionDocument.MpiComponentDefinition mpiComponentDefinition)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.mpicomponent.MpiComponentDefinitionDocument.MpiComponentDefinition target = null;
            target = (com.ats.aempi.configuration.xml.mpicomponent.MpiComponentDefinitionDocument.MpiComponentDefinition)get_store().find_element_user(MPICOMPONENTDEFINITION$0, 0);
            if (target == null)
            {
                target = (com.ats.aempi.configuration.xml.mpicomponent.MpiComponentDefinitionDocument.MpiComponentDefinition)get_store().add_element_user(MPICOMPONENTDEFINITION$0);
            }
            target.set(mpiComponentDefinition);
        }
    }
    
    /**
     * Appends and returns a new empty "mpi-component-definition" element
     */
    public com.ats.aempi.configuration.xml.mpicomponent.MpiComponentDefinitionDocument.MpiComponentDefinition addNewMpiComponentDefinition()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.mpicomponent.MpiComponentDefinitionDocument.MpiComponentDefinition target = null;
            target = (com.ats.aempi.configuration.xml.mpicomponent.MpiComponentDefinitionDocument.MpiComponentDefinition)get_store().add_element_user(MPICOMPONENTDEFINITION$0);
            return target;
        }
    }
    /**
     * An XML mpi-component-definition(@http://configuration.openempi.openhie.org/mpicomponent).
     *
     * This is a complex type.
     */
    public static class MpiComponentDefinitionImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.ats.aempi.configuration.xml.mpicomponent.MpiComponentDefinitionDocument.MpiComponentDefinition
    {
        
        public MpiComponentDefinitionImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName MPICOMPONENT$0 = 
            new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/mpicomponent", "mpi-component");
        
        
        /**
         * Gets the "mpi-component" element
         */
        public com.ats.aempi.configuration.xml.mpicomponent.MpiComponentType getMpiComponent()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.ats.aempi.configuration.xml.mpicomponent.MpiComponentType target = null;
                target = (com.ats.aempi.configuration.xml.mpicomponent.MpiComponentType)get_store().find_element_user(MPICOMPONENT$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "mpi-component" element
         */
        public void setMpiComponent(com.ats.aempi.configuration.xml.mpicomponent.MpiComponentType mpiComponent)
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.ats.aempi.configuration.xml.mpicomponent.MpiComponentType target = null;
                target = (com.ats.aempi.configuration.xml.mpicomponent.MpiComponentType)get_store().find_element_user(MPICOMPONENT$0, 0);
                if (target == null)
                {
                    target = (com.ats.aempi.configuration.xml.mpicomponent.MpiComponentType)get_store().add_element_user(MPICOMPONENT$0);
                }
                target.set(mpiComponent);
            }
        }
        
        /**
         * Appends and returns a new empty "mpi-component" element
         */
        public com.ats.aempi.configuration.xml.mpicomponent.MpiComponentType addNewMpiComponent()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.ats.aempi.configuration.xml.mpicomponent.MpiComponentType target = null;
                target = (com.ats.aempi.configuration.xml.mpicomponent.MpiComponentType)get_store().add_element_user(MPICOMPONENT$0);
                return target;
            }
        }
    }
}
