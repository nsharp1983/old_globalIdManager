package com.ats.apixpdq.web.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;




/**
 * 工具类
 * 管理SessionFactory和Session
 * @author Administrator
 *
 */
public class HibernateUtil {
	
	private SessionFactory sf;

	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	
	public Session getSession(){
		return sf.getCurrentSession();
	}

}
