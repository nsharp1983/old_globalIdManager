package com.ats.aempi.matching;

import java.util.List;
import java.util.Set;

import com.ats.aempi.ApplicationException;
import com.ats.aempi.InitializationException;
import com.ats.aempi.model.Record;
import com.ats.aempi.model.RecordPair;

public interface MatchingService
{
	/**
	 * The init method is used for initialized of the matching service. Depending on
	 * the implementation of the service, the initialization requirements will vary. An exact
	 * matching service may not need any initialization at all, whereas a probabilistic 
	 * matching service will require considerable initialization.
	 * 
	 * @throws InitializationException
	 */
	public void init() throws InitializationException;
	
	/**
	 * The isInitialized method returns the current state of the matching service regarding initialization. It
	 * is used to support lazy initialization of a matching service only when it is needed.
	 * 
	 * @return the initialization state of the matching service.
	 */
	public boolean isInitialized();
	
	/**
	 * Is used to set the initialization state of the matching service.
	 * 
	 * @param initialized
	 */
	public void setInitialized(boolean initialized);
	
	/**
	 * The initializeRepository method first removes all the person associations that have been
	 * established in the database between records that belong to the same physical entity and
	 * performs matching of all the records from the beginning. This operation may be very
	 * time consuming and may be destructive in nature. I should only be performed when an
	 * instance of OpenEMPI is first created or when a different matching algorithm is used
	 * or the matching parameters of the matching algorithm are modified.
	 *   
	 * @throws ApplicationException
	 */
	public void initializeRepository() throws ApplicationException;

	/**
	 * This match method takes a record as a parameter and returns all the records that the
	 * given record is linked to by returning them in the form of record pairs. The first record
	 * in each record pair returned is the record passed into the call.
	 * 
	 * @param record
	 * @return
	 */
	public Set<RecordPair> match(Record record) throws ApplicationException;
	
	/**
	 * This method will generate and persist all the record pairs found in the system based
	 * on the matching algorithm implemented by the particular service. This method should only
	 * be run once after the system is loaded with the initial set of person data. This operation
	 * may be time intensive and the amount of time it takes may be considerable depending on the
	 * complexity of the matching algorithm.
	 */
	public void linkRecords();
	
	/**
	 * PANMIN-20150625
	 * 用于自动合并，通过关联 字段计算EMPI数量，去重
	 * @return EMPI
	 */
	public List<String> matchEmpi(Record record);
	
	/**
	 * PANMIN-20150714
	 * 用于自动合并，通过关联 字段计算PERSON_ID数量，去重
	 * @return PERSON_ID
	 */
	public List<String> matchPersonID(Record record);
}
