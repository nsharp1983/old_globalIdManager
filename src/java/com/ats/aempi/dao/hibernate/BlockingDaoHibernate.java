package com.ats.aempi.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.ats.aempi.dao.BlockingDao;
import com.ats.aempi.model.Criteria;
import com.ats.aempi.model.Criterion;
import com.ats.aempi.model.NameValuePair;
import com.ats.aempi.model.Operation;
import com.ats.aempi.model.Person;
import com.ats.aempi.model.PersonIdentifierEMPI;
import com.ats.aempi.model.Record;

import org.springframework.orm.hibernate3.HibernateCallback;

public class BlockingDaoHibernate extends UniversalDaoHibernate implements BlockingDao
{
	public List<Record> blockRecords(Criteria criteria) {
		List<Person> persons = getPersons(criteria);
		List<Record> records = new java.util.ArrayList<Record>(persons.size());
		for (Person person : persons) {
			Record record = new Record(person);
			record.setRecordId(new Long(person.getPersonId()));
			records.add(record);
		}
		return records;
	}

	@SuppressWarnings("unchecked")
	public List<NameValuePair> getDistinctValues(String field) {
		if (field == null || field.length() == 0) {
			return new java.util.ArrayList<NameValuePair>();
		}
		String query = "select distinct p." + field + " from Person p";
        List<String> values = getHibernateTemplate().find(query);
        log.trace("Found the following: " + values);
        List<NameValuePair> nameValuePairs = new java.util.ArrayList<NameValuePair>();
        for (String value : values) {
        	if (value != null) {
        		nameValuePairs.add(new NameValuePair(field, value));
        	}
        }
        return nameValuePairs;
	}
	

	@SuppressWarnings("unchecked")
	public List<List<NameValuePair>> getDistinctValues(final List<String> fields) {
		return  (List<List<NameValuePair>>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				ProjectionList projectionList = Projections.projectionList();
				for (String field : fields) {
					projectionList.add(Projections.property(field), field);					
				}
				org.hibernate.Criteria criteria = session.createCriteria(Person.class, "person_")
					.setProjection(Projections.distinct(projectionList));
				for (String field : fields) {
					criteria.add(Expression.isNotNull("person_."+field));
				}
				log.debug("Blocking criteria query: " + criteria.toString());
				@SuppressWarnings("rawtypes")
				List list = (List<Object[]>) criteria.list();
				List<List<NameValuePair>> pairs = new ArrayList<List<NameValuePair>>(list.size());
				if (list.size() == 0) {
					return pairs;
				}
				// Query returns either a list of Object or a list of arrays of objects depending on
				// whether one or more than one blocking fields are used.
				if (!(list.get(0) instanceof Object[])) {
					List<Object> theValues = (List<Object>) list;
					for (Object value : theValues) {
						List<NameValuePair> distinctRowValues = new ArrayList<NameValuePair>(1);
						distinctRowValues.add(new NameValuePair(fields.get(0), value));
						pairs.add(distinctRowValues);
					}
					return pairs;
				}
				List<Object[]> objectArrayValues = (List<Object[]>) list;
				for (Object[] row : objectArrayValues) {
					List<NameValuePair> distinctRowValues = new ArrayList<NameValuePair>(row.length);
					for (int i = 0; i < row.length; i++) {
						NameValuePair pair = new NameValuePair(fields.get(i), row[i]);
						distinctRowValues.add(pair);
					}
					pairs.add(distinctRowValues);
				}
				return pairs;
			}
		});
	}

	@SuppressWarnings("unchecked")
	private List<Person> getPersons(final Criteria criteria) {
		return  (List<Person>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				org.hibernate.Criteria criteriaHibernate = buildHibernateCriteria(session, criteria);
				log.debug("Querying by criteria using " + criteriaHibernate.toString());
				List<Person> list = (List<Person>) criteriaHibernate.setResultTransformer(org.hibernate.Criteria.DISTINCT_ROOT_ENTITY).list();
				log.debug("Query by criteria returned: " + list.size() + " elements.");
				return list;
			}
		});
	}
	
	//public List<String> EmpiBlockRecords(String myHQL);
	
	@SuppressWarnings("unchecked")
	public List<String> EmpiBlockRecords(final String mySQL)
	{
		return (List<String>) getHibernateTemplate().execute(new HibernateCallback() 
		{
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{

				Query queryObject = session.createSQLQuery(mySQL);
				
				//System.out.println(queryObject.getQueryString());
				
				List<String> list=queryObject.list();

				//log.debug("Query by identifier returned: " + list.size() + " elements.");
				if (list.size() == 0) {
					return null;
				}
	
				session.evict(list);
				return list;
			}
		}
		);
	}
	
	private org.hibernate.Criteria buildHibernateCriteria(Session session, Criteria criteria) {
		org.hibernate.Criteria criteriaHibernate = session.createCriteria(Person.class);
		for (Criterion criterion : criteria.getCriteria()) {
			addCriterion(criteriaHibernate, criterion);
		}
		addCriterion(criteriaHibernate, new Criterion("dateVoided", Operation.ISNULL, null));
		return criteriaHibernate;
	}

	private void addCriterion(org.hibernate.Criteria criteriaHibernate, Criterion criterion) {
		if (criterion.getOperation().equals(Operation.EQ)) {
			criteriaHibernate.add(Restrictions.eq(criterion.getName(), criterion.getValue()));
		} else if (criterion.getOperation().equals(Operation.ISNOTNULL)) {
			criteriaHibernate.add(Restrictions.isNotNull(criterion.getName()));
		} else if (criterion.getOperation().equals(Operation.ISNULL)) {
			criteriaHibernate.add(Restrictions.isNull(criterion.getName()));
		} else if (criterion.getOperation().equals(Operation.LIKE)) {
			criteriaHibernate.add(Restrictions.like(criterion.getName(), criterion.getValue()));			
			//criteriaHibernate.add(Restrictions.eq(criterion.getName(), criterion.getValue()));
		} else if (criterion.getOperation().equals(Operation.NE)) {
			criteriaHibernate.add(Restrictions.ne(criterion.getName(), criterion.getValue()));
		}
	}
}
