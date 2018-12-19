package com.ats.aempi.dao;

import java.util.List;
import java.util.Set;

import com.ats.aempi.model.Empi;
import com.ats.aempi.model.ExtendForPerson;
import com.ats.aempi.model.PatientVisit;
import com.ats.aempi.model.PatientVisitHistory;
import com.ats.aempi.model.Person;
import com.ats.aempi.model.PersonIdentifierEMPI;

public interface EmpiDao extends UniversalDao
{
	public void addEmpi(Empi empi);
	
	public Empi getPersonByEmpi(Person person);
	
	public void updateEmpi(Empi empi);

}
