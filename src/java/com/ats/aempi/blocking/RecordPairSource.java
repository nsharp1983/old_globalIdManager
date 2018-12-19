package com.ats.aempi.blocking;

import java.util.List;

import com.ats.aempi.model.NameValuePair;

public interface RecordPairSource
{
	public void init();
	public RecordPairIterator iterator();
	public List<List<NameValuePair>> getBlockingValueList();
}
