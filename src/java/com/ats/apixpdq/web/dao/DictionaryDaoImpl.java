package com.ats.apixpdq.web.dao;

import java.util.List;

import org.hibernate.Query;

import com.ats.aempi.model.MaritalStatusDict;
import com.ats.aempi.model.NationalityDict;
import com.ats.aempi.model.RelationshipTypeDict;

public class DictionaryDaoImpl extends HibernateUtil implements IDictionaryDao{

	@Override
	public List<MaritalStatusDict> findMaritalStatusDicts() {
		// TODO Auto-generated method stub
						
		String hql="from MaritalStatusDict";
		Query query=getSession().createQuery(hql);
		List<MaritalStatusDict> list=query.list();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public List<NationalityDict> findNationalityDicts() {
		// TODO Auto-generated method stub
		String hql="from NationalityDict";
		Query query=getSession().createQuery(hql);
		List<NationalityDict> list=query.list();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public List<RelationshipTypeDict> findRelationshipTypeDicts() {
		// TODO Auto-generated method stub
		String hql="from RelationshipTypeDict";
		Query query=getSession().createQuery(hql);
		List<RelationshipTypeDict> list=query.list();
		if(list.size()>0){
			return list;
		}
		return null;
	}
	
	
	
	
	

}
