package com.ats.aempi.dao;

import java.util.List;

import com.ats.aempi.model.UserSession;

public interface UserSessionDao extends UniversalDao
{
	public void saveUserSession(UserSession session);
	
	public List<UserSession> findAll();
	
	public UserSession findById(Integer sessionId);
	
	public UserSession findBySessionKey(String sessionKey);
}
