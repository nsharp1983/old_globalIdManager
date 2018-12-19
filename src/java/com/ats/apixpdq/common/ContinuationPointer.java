package com.ats.apixpdq.common;


import java.util.ArrayList;
import java.util.List;

import com.ats.aexchange.datamodel.Identifier;
import com.ats.aexchange.datamodel.Patient;

/**
 * This class is a container to store the PDQ continuation information that pointer pointers to.
 *
 * @author Wenzhi Li
 * @version 1.0, Apr 25, 2007
 */
public class ContinuationPointer {
    private String pointer;
    private String queryTag;
    private List<List<Patient>> patients = null;
    private List<Identifier> returnDomains = null;
    private long lastRequestTime = System.currentTimeMillis();
    private int totalRecords = -1;
    
    /**
     * Gets the continuation pointer
     * 
     * @return the pointer
     */
    public String getPointer() {
        return pointer;
    }
    
    /**
     * Sets the continuation pointer.
     * 
     * @param pointer the pointer to set
     */
    public void setPointer(String pointer) {
        this.pointer = pointer;
    }
    
    /**
     * Gets the query tag that this ContinuationPointer is 
     * associated with.
     * 
	 * @return the query tag
	 */
	public String getQueryTag() {
		return queryTag;
	}
	
	/**
	 * Sets the query tag. 
	 * 
	 * @param queryTag the queryTag to set
	 */
	public void setQueryTag(String queryTag) {
		this.queryTag = queryTag;
	}
	
	/**
	 * Gets the patients stored in this Continuation Pointer.
	 * 
	 * @return a list of list of {@link Patient}s. The first list
	 *         is for different logic patients, while the second list is for
	 *         the same logic patient in different domains. 
	 */
	public List<List<Patient>> getPatients() {
        if (patients == null)
            return new ArrayList<List<Patient>>();
        else
            return patients;
    }
	
	/**
	 * Sets the patients for this Continuation Pointer.
	 * 
	 * @param patients a list of list of {@link Patient}s. The first list
	 *         is for different logic patients, while the second list is for
	 *         the same logic patient in different domains.
	 */
    public void setPatients(List<List<Patient>> patients) {
        this.patients = patients;
    }

    /**
     * Gets the return domains of the PDQ request.
     * 
     * @return a list of return domains
     */
    public List<Identifier> getReturnDomain() {
        if (returnDomains == null)
            return new ArrayList<Identifier>();
        else
            return returnDomains;
    }
    
    /**
     * Sets the return domain of the PDQ request.
     *  
     * @param domains a list of domains to set 
     */
    public void setReturnDomain(List<Identifier> domains) {
        this.returnDomains = domains;
    }

    /**
     * Gets the last continuation request time.
     * 
     * @return the last request time in long
     */
    public long getLastRequestTime() {
        return lastRequestTime;
    }
    
    /**
     * Sets the last continuation request time.
     * 
     * @param time the time to set
     */
    public void setLastRequestTime(long time) {
        this.lastRequestTime = time;
    }
    
	/**
	 * Gets the total number of matching records for the PDQ request.
	 * 
	 * @return the total number of matching records
	 */
	public int getTotalRecords() {
		return totalRecords;
	}
	
	/**
	 * Sets the total number of matching records for the PDQ request.
	 * 
	 * @param totalRecords the total number of records to set
	 */
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
    
}
