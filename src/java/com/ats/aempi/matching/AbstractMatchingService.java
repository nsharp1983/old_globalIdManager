package com.ats.aempi.matching;

import com.ats.aempi.ApplicationException;
import com.ats.aempi.service.impl.BaseServiceImpl;

public abstract class AbstractMatchingService extends BaseServiceImpl implements MatchingService
{
	public boolean initialized = false;
	
	public boolean isInitialized() {
		return initialized;
	}
	
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	public void initializeRepository() throws ApplicationException {
		// The default implementation does nothing		
	}
}
