package com.ats.aempi.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.ats.aempi.dao.EmpiDao;

import com.ats.aempi.model.Empi;
import com.ats.aempi.model.Person;
import com.ats.aempi.model.PersonIdentifierEMPI;

public class EmpiDaoHibernate extends UniversalDaoHibernate implements EmpiDao
{
	
	
	public void addEmpi(Empi empi) 
	{
		log.debug("Saving empi record: " + empi);
		getHibernateTemplate().saveOrUpdate(empi);
		getHibernateTemplate().flush();
		log.debug("Finished saving the empi.");
	}
	
	@SuppressWarnings("unchecked")
	public Empi getPersonByEmpi(final Person person) 
	{
		return (Empi) getHibernateTemplate().execute(new HibernateCallback() 
		{
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				Criteria criteria = session.createCriteria(Empi.class)
                                           .add(Restrictions.eq("empi", person.getEmpi()));
				
				List<Empi> list = criteria.list();
				
				log.debug("Query by partial identifier returned: " + list.size() + " elements.");
				
				Empi empi = new Empi();
				
				if(list.size()==0)
				{
					return null;
				}
				
				empi = list.get(0);
				
				return empi;
			}
		}
		);		
	}

	public void updateEmpi(Empi empi) 
	{
		log.debug("Saving empi record: " + empi);
		
		//loadAssociations(person);
		
		getHibernateTemplate().merge(empi);
		
		getHibernateTemplate().flush();
		
		log.debug("Finished saving the empi.");
		
	}
		
}
