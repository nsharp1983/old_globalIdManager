package com.ats.aempi.configuration.xml.basicblocking.impl;
/**
 * An XML blocking-rounds(@http://configuration.openempi.openhie.org/basic-blocking).
 *
 * This is a complex type.
 */
public class BlockingRoundsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.ats.aempi.configuration.xml.basicblocking.BlockingRounds
{
    
    public BlockingRoundsImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName BLOCKINGROUND$0 = 
        new javax.xml.namespace.QName("http://configuration.openempi.openhie.org/basic-blocking", "blocking-round");
    
    
    /**
     * Gets array of all "blocking-round" elements
     */
    public com.ats.aempi.configuration.xml.basicblocking.BlockingRound[] getBlockingRoundArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(BLOCKINGROUND$0, targetList);
            com.ats.aempi.configuration.xml.basicblocking.BlockingRound[] result = new com.ats.aempi.configuration.xml.basicblocking.BlockingRound[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "blocking-round" element
     */
    public com.ats.aempi.configuration.xml.basicblocking.BlockingRound getBlockingRoundArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.basicblocking.BlockingRound target = null;
            target = (com.ats.aempi.configuration.xml.basicblocking.BlockingRound)get_store().find_element_user(BLOCKINGROUND$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "blocking-round" element
     */
    public int sizeOfBlockingRoundArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(BLOCKINGROUND$0);
        }
    }
    
    /**
     * Sets array of all "blocking-round" element
     */
    public void setBlockingRoundArray(com.ats.aempi.configuration.xml.basicblocking.BlockingRound[] blockingRoundArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(blockingRoundArray, BLOCKINGROUND$0);
        }
    }
    
    /**
     * Sets ith "blocking-round" element
     */
    public void setBlockingRoundArray(int i, com.ats.aempi.configuration.xml.basicblocking.BlockingRound blockingRound)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.basicblocking.BlockingRound target = null;
            target = (com.ats.aempi.configuration.xml.basicblocking.BlockingRound)get_store().find_element_user(BLOCKINGROUND$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(blockingRound);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "blocking-round" element
     */
    public com.ats.aempi.configuration.xml.basicblocking.BlockingRound insertNewBlockingRound(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.basicblocking.BlockingRound target = null;
            target = (com.ats.aempi.configuration.xml.basicblocking.BlockingRound)get_store().insert_element_user(BLOCKINGROUND$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "blocking-round" element
     */
    public com.ats.aempi.configuration.xml.basicblocking.BlockingRound addNewBlockingRound()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.ats.aempi.configuration.xml.basicblocking.BlockingRound target = null;
            target = (com.ats.aempi.configuration.xml.basicblocking.BlockingRound)get_store().add_element_user(BLOCKINGROUND$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "blocking-round" element
     */
    public void removeBlockingRound(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(BLOCKINGROUND$0, i);
        }
    }
}
