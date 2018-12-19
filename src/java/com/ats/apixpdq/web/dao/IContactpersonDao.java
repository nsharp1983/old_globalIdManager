package com.ats.apixpdq.web.dao;

import com.ats.aempi.model.Contactperson;

public interface IContactpersonDao {
	
	/**
	 * 保存联系人信息
	 * @param contactperson
	 */
	public void saveContactperson(Contactperson contactperson);

}
