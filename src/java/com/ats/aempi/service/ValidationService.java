package com.ats.aempi.service;

import com.ats.aempi.ValidationException;

public interface ValidationService
{
	public void validate(Object obj) throws ValidationException;
}
