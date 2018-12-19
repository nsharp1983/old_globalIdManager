package com.ats.aempi.configuration.xml.basicblocking.impl;
/**
 * An XML blocking-fields(@http://configuration.openempi.openhie.org/basic-blocking).
 *
 * This is a complex type.
 */
public class BlockingFieldsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.ats.aempi.configuration.xml.basicblocking.BlockingFields
{
    
    public BlockingFieldsImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName BLOCKINGFIELD$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/basic-blocking", "blocking-field");
    
    
    /**
     * Gets array of all "blocking-field" elements
     */
    @SuppressWarnings("unchecked")
	public com.ats.aempi.configuration.xml.basicblocking.BlockingField[] getBlockingFieldArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(BLOCKINGFIELD$0, targetList);
            com.ats.aempi.configuration.xml.basicblocking.BlockingField[] result = new com.ats.aempi.configuration.xml.basicblocking.BlockingField[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "blocking-field" element
     */
    public com.ats.aempi.configuration.xml.basicblocking.BlockingField getBlockingFieldArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.basicblocking.BlockingField target = null;
            target = (com.ats.aempi.configuration.xml.basicblocking.BlockingField)get_store().find_element_user(BLOCKINGFIELD$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "blocking-field" element
     */
    public int sizeOfBlockingFieldArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(BLOCKINGFIELD$0);
        }
    }
    
    /**
     * Sets array of all "blocking-field" element
     */
    public void setBlockingFieldArray(com.ats.aempi.configuration.xml.basicblocking.BlockingField[] blockingFieldArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(blockingFieldArray, BLOCKINGFIELD$0);
        }
    }
    
    /**
     * Sets ith "blocking-field" element
     */
    public void setBlockingFieldArray(int i, com.ats.aempi.configuration.xml.basicblocking.BlockingField blockingField)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.basicblocking.BlockingField target = null;
            target = (com.ats.aempi.configuration.xml.basicblocking.BlockingField)get_store().find_element_user(BLOCKINGFIELD$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(blockingField);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "blocking-field" element
     */
    public com.ats.aempi.configuration.xml.basicblocking.BlockingField insertNewBlockingField(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.basicblocking.BlockingField target = null;
            target = (com.ats.aempi.configuration.xml.basicblocking.BlockingField)get_store().insert_element_user(BLOCKINGFIELD$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "blocking-field" element
     */
    public com.ats.aempi.configuration.xml.basicblocking.BlockingField addNewBlockingField()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.basicblocking.BlockingField target = null;
            target = (com.ats.aempi.configuration.xml.basicblocking.BlockingField)get_store().add_element_user(BLOCKINGFIELD$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "blocking-field" element
     */
    public void removeBlockingField(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(BLOCKINGFIELD$0, i);
        }
    }
}
