package com.ats.aempi.dao;

import java.util.List;

import com.ats.aempi.model.Criteria;
import com.ats.aempi.model.NameValuePair;
import com.ats.aempi.model.Record;

public interface BlockingDao
{
	public List<Record> blockRecords(Criteria criteria);
	
	public List<NameValuePair> getDistinctValues(String field);
	
	public List<List<NameValuePair>> getDistinctValues(List<String> fields);
	 
	public List<String> EmpiBlockRecords(final String myHQL);
}
