package com.ats.aempi.blocking.basicblocking;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ats.aempi.blocking.RecordPairIterator;
import com.ats.aempi.blocking.RecordPairSource;
import com.ats.aempi.configuration.BaseField;
import com.ats.aempi.dao.BlockingDao;
import com.ats.aempi.model.NameValuePair;

public class BasicRecordPairSource implements RecordPairSource
{
	protected final Log log = LogFactory.getLog(getClass());
	private List<BlockingRound> blockingRounds;
	private BlockingDao blockingDao;
	private List<List<NameValuePair>> valueList;
	
	public void init() {
		log.trace("Initializing the Record Pair Source");
		valueList = new ArrayList<List<NameValuePair>>();
		for (BlockingRound round : blockingRounds) {
			List<BaseField> fields = round.getFields();
			List<List<NameValuePair>> values = blockingDao.getDistinctValues(getBlockingFieldList(fields));
			valueList.addAll(values);
		}		
	}

	private List<String> getBlockingFieldList(List<BaseField> fields) {
		List<String> fieldList  = new java.util.ArrayList<String>(fields.size());
		for (BaseField field : fields) {
			fieldList.add(field.getFieldName());
		}
		return fieldList;
	}

	public List<List<NameValuePair>> getBlockingValueList() {
		return valueList;
	}

	public RecordPairIterator iterator() {
		BasicRecordPairIterator iterator = new BasicRecordPairIterator(this);
		return iterator;
	}

	public List<BlockingRound> getBlockingRounds() {
		return blockingRounds;
	}

	public void setBlockingRounds(List<BlockingRound> blockingRounds) {
		this.blockingRounds = blockingRounds;
	}
	
	public BlockingDao getBlockingDao() {
		return blockingDao;
	}

	public void setBlockingDao(BlockingDao blockingDao) {
		this.blockingDao = blockingDao;
	}
}
