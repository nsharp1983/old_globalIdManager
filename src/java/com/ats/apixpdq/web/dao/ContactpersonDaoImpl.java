package com.ats.apixpdq.web.dao;

import com.ats.aempi.model.Contactperson;

public class ContactpersonDaoImpl extends HibernateUtil implements IContactpersonDao{

	@Override
	public void saveContactperson(Contactperson contactperson) {
		// TODO Auto-generated method stub
		getSession().save(contactperson);
	}
	
	

}
