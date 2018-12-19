package com.ats.aempi.service.impl;

import com.ats.aempi.ValidationException;
import com.ats.aempi.service.ValidationService;

public class ValidationServiceImpl extends BaseServiceImpl implements ValidationService
{

	public void validate(Object obj) throws ValidationException {
		log.debug("Validating object : " + obj);
	}

}
