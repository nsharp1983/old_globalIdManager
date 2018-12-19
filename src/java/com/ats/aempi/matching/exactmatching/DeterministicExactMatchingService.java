package com.ats.aempi.matching.exactmatching;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.ats.aempi.InitializationException;
import com.ats.aempi.configuration.MatchField;
import com.ats.aempi.context.Context;
import com.ats.aempi.matching.AbstractMatchingService;
import com.ats.aempi.matching.MatchingService;
import com.ats.aempi.model.Record;
import com.ats.aempi.model.RecordPair;

public class DeterministicExactMatchingService extends AbstractMatchingService implements MatchingService
{
	private List<String> matchFieldNames;
	
	public void init() throws InitializationException {
		Object obj = Context.getConfiguration().lookupConfigurationEntry(ExactMatchingConstants.EXACT_MATCHING_FIELDS_REGISTRY_KEY);
		if (obj == null) {
			log.error("Deterministic exact matching service has not been configured properly; no match fields have been defined.");
			throw new RuntimeException("Deterministic exact maching service has not been configured properly.");
		}
		@SuppressWarnings("unchecked")
		List<MatchField> matchFields = (List<MatchField>) obj;
		matchFieldNames = new ArrayList<String>(matchFields.size());
		for (MatchField field : matchFields) {
			matchFieldNames.add(field.getFieldName());
		}		
		log.debug("Matching service " + getClass().getName() + " will perform matching using " + toString());
	}

	//PANMIN-20150625
	public List<String> matchEmpi(Record record){
		return Context.getBlockingService().findMatchEmpi(record);
	}
	
	//PANMIN-20150714
		public List<String> matchPersonID(Record record){
			return Context.getBlockingService().findMatchPersonID(record);
		}
	
	
	public Set<RecordPair> match(Record record) {
		
		//panmin--数据库查询匹配设置字段的记录
		log.debug("Looking for matches on record " + record);
		
		List<RecordPair> candidates = Context.getBlockingService().findCandidates(record);
		
		//关键字段逐一再次匹配，全部子段匹配增添加进记录
		Set<RecordPair> matches = new java.util.HashSet<RecordPair>();
		
		if (candidates==null)
		{
			return matches;
		}
		for (RecordPair entry : candidates) 
		{
			log.debug("Potential matching record pair found: " + entry);
			
			boolean overallMatch = true;
			
			int MatchCounts=0;
			
			for (String matchField : getMatchFieldNames()) 
			{
				boolean fieldsMatch = isExactMatch(matchField, entry.getLeftRecord(), entry.getRightRecord());
				
				if(entry.getLeftRecord().getAsString(matchField)==null && entry.getRightRecord().getAsString(matchField)==null)
				{
					MatchCounts++;
				}
				
				log.debug("Comparison of records on field " + matchField + " returned " + fieldsMatch);
				
				//有一字段不匹配则终止
				if (!fieldsMatch) 
				{
					overallMatch = false;
					
					break;
				}
			}
			
			//如果匹配记录段均为空时也默认为匹配失败
			if(MatchCounts==getMatchFieldNames().size() && getMatchFieldNames().size()!=0)
			{
				overallMatch = false;
			}
			
			// If the two records match but they don't refer to the same exact record then these records
			// should be linked together
			//System.out.println(entry.getRightRecord().getRecordId().longValue() + " | " + record.getRecordId().longValue());
			
			if (overallMatch && entry.getRightRecord().getRecordId().longValue() != record.getRecordId().longValue()) 
			{
				log.debug("Adding to matches entry: " + entry);
				
				entry.setWeight(1.0);
				
				matches.add(entry);
				
			}
		}
		return matches;
	}

	public void linkRecords() {
		
	}

	private boolean isExactMatch(String matchField, Record left, Record right) {
		String lVal = left.getAsString(matchField);
		String rVal = right.getAsString(matchField);
		if (lVal == null) {
			if (rVal == null) {
				return true;
			}
			return false;
		}
		return lVal.equals(rVal);
	}

	public List<String> getMatchFieldNames() {
		return matchFieldNames;
	}

	public void setMatchFieldNames(List<String> matchFieldNames) {
		this.matchFieldNames = matchFieldNames;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("matchFieldNames", matchFieldNames).toString();
	}
}
