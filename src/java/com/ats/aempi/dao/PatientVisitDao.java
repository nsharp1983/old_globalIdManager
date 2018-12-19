package com.ats.aempi.dao;

import java.util.List;
import java.util.Set;

import com.ats.aempi.model.ExtendForPerson;
import com.ats.aempi.model.PatientVisit;
import com.ats.aempi.model.PatientVisitHistory;
import com.ats.aempi.model.Person;
import com.ats.aempi.model.PersonIdentifierEMPI;
import com.ats.aempi.model.PersonInOut;
import com.ats.aempi.model.TransPatientVisit;

public interface PatientVisitDao extends UniversalDao
{
	public void SaveTransPatientVisit(TransPatientVisit patientvisit);
	
	public void addPatientVisit(PatientVisit patientvisit);
	
	public void updatePatientVisit(PatientVisit patientvisit);
	
	public PatientVisit getPatientVisitById(Person person);
	
	public List<PatientVisit>getPatientVisitsById(Person person);
	
	public List<PatientVisit>getPatientVisitsByHisId(Person person, String visitflowid);
	
	public PatientVisit getVisitById(Person person);
	
	public List<Person> getPersonVisitById(PersonIdentifierEMPI personIdentifier,PatientVisit patientvisit);

	public List<Person> getPersons(String StrSql);
	
	public List<ExtendForPerson>GetExtendFields();
	
	public List<ExtendForPerson> FindExtendByProperty(String propertyName, Object value);
	
	public void SavePatientVisitHistory(PatientVisitHistory patientvisithistory);
	
	public void addPersonInOut(PersonInOut personinout);

}
