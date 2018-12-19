package com.ats.apixpdq.web.service;

import com.ats.aempi.model.Contactperson;



public interface IContactpersonService {

	/**
	 * 保存联系人信息
	 * @param contactperson
	 * @throws Exception 
	 */
	public void saveContactperson(Contactperson contactperson) throws Exception;
	
}
