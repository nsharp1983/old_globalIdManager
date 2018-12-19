package com.ats.apixpdq.web.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.ats.aempi.model.MaritalStatusDict;
import com.ats.aempi.model.NationalityDict;
import com.ats.aempi.model.RelationshipTypeDict;
import com.ats.apixpdq.api.IMessageStoreLogger;
import com.ats.apixpdq.api.MessageStore;
import com.ats.apixpdq.web.dao.IDictionaryDao;
import com.ats.apixpdq.web.dao.MessageStoreDAO;
import com.ats.apixpdq.web.dao.MessageStoreDAOImpl;
import com.ats.apixpdq.web.service.DictionaryService;


public class DictionaryService implements IDictionaryService {

	private static Logger log = Logger.getLogger(DictionaryService.class);
	private IDictionaryDao dictionaryDao;
	

	public IDictionaryDao getDictionaryDao() {
		return dictionaryDao;
	}

	public void setDictionaryDao(IDictionaryDao dictionaryDao) {
		this.dictionaryDao = dictionaryDao;
	}

	public List<NationalityDict> findNationalityDicts(){
		return dictionaryDao.findNationalityDicts();
	}
	
	public List<MaritalStatusDict> findMaritalStatusDicts(){
		return dictionaryDao.findMaritalStatusDicts();
	}

	@Override
	public List<RelationshipTypeDict> findRelationshipTypeDicts() {
		// TODO Auto-generated method stub
		return dictionaryDao.findRelationshipTypeDicts();
	}
	
	
	
	
}
