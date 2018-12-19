package com.ats.apixpdq.web.service;

import org.apache.log4j.Logger;

import com.ats.aempi.model.Contactperson;
import com.ats.apixpdq.web.dao.HibernateUtil;
import com.ats.apixpdq.web.dao.IContactpersonDao;



public class ContactpersonServiceImpl extends HibernateUtil implements IContactpersonService {
	
	private static Logger log = Logger.getLogger(ContactpersonServiceImpl.class);
	private IContactpersonDao contactpersonDao;
	
	

	public IContactpersonDao getContactpersonDao() {
		return contactpersonDao;
	}



	public void setContactpersonDao(IContactpersonDao contactpersonDao) {
		this.contactpersonDao = contactpersonDao;
	}



	/**
	 * 保存联系人信息
	 * @param contactperson
	 * @throws Exception 
	 */
	public void saveContactperson(Contactperson contactperson) throws Exception{
		try {
			log.debug("save Contactperson ");
			contactpersonDao.saveContactperson(contactperson);
			log.debug("save Contactperson successful");
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("save Contactperson failed"+e.getMessage(),e);
			throw e;
		}
	}
	
}
