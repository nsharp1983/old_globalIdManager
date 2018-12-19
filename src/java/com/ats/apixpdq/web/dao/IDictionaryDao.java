package com.ats.apixpdq.web.dao;

import java.util.List;

import com.ats.aempi.model.MaritalStatusDict;
import com.ats.aempi.model.NationalityDict;
import com.ats.aempi.model.RelationshipTypeDict;

public interface IDictionaryDao {
	
	/**
	 * 国籍
	 * @return
	 */
	public List<NationalityDict> findNationalityDicts();
	
	/**
	 * 婚姻
	 */
	public List<MaritalStatusDict> findMaritalStatusDicts();
	
	
	/**
	 * 关系
	 * @return
	 */
	public List<RelationshipTypeDict> findRelationshipTypeDicts();

}
