package com.ats.aempi.blocking;

import java.util.List;

import com.ats.aempi.blocking.basicblocking.BlockingRound;
import com.ats.aempi.model.Record;
import com.ats.aempi.model.RecordPair;

public interface BlockingService
{
	/**
	 * The getRecordPairSource returns an object that represents a collection of
	 * all the record pairs from the default data source of the system (the entire
	 * repository of records). The record pairs are retrieved from the system
	 * based on the configuration of the blocking algorithm so this data source
	 * should typically return a number of record pairs that is considerably
	 * smaller than the complete set of n(n-1) record pairs.
	 * 
	 * @return
	 */
	public RecordPairSource getRecordPairSource();
	
	/**
	 * The getRecordPairSource returns an object that represents a collection of
	 * all the record pairs from the default data source of the system (the entire
	 * repository of records). The record pairs are retrieved from the system
	 * based on the configuration of the blocking algorithm specified by
	 * the list of BlockingRound objects. Depending on the blocking algorithm
	 * this data source should typically return a number of record pairs that is
	 * considerably smaller than the complete set of n(n-1) record pairs.
	 * 
	 * @return
	 */
	public RecordPairSource getRecordPairSource(List<BlockingRound> blockingRounds);
	
	/**
	 * Given a particular record, the findCandidates method returns a list
	 * of records that given the current configuration of the blocking algorithm
	 * are potential matches to the record passed in.
	 * 
	 * @param record A record that should be used along with the blocking algorithm
	 * to determine similar records that are potential matches.
	 * 
	 * @return
	 */
	public List<RecordPair> findCandidates(Record record);
	
	/**
	 * PANMIN-20150625
	 * 通过关联条件查询EMPI
	 * @param record
	 * @return EMPI
	 */
	public List<String> findMatchEmpi(Record record);
	
	/**
	 * PANMIN-20150714
	 * 通过关联条件查询PersonID
	 * @param record
	 * @return PersonID
	 */
	public List<String> findMatchPersonID(Record record);
}
