package com.ats.aempi.dao.hibernate;

import java.util.List;

import com.ats.aempi.dao.UserSessionDao;
import com.ats.aempi.model.UserSession;

public class UserSessionDaoHibernate extends UniversalDaoHibernate implements UserSessionDao
{
	public UserSession findById(Integer sessionId) {
		log.debug("Locating session by id: " + sessionId);
		UserSession session = (UserSession) getHibernateTemplate().load(UserSession.class, sessionId);
		log.debug("Found session: " + session);
		return session;
	}

	@SuppressWarnings("unchecked")
	public UserSession findBySessionKey(String sessionKey) {
		log.debug("Locating session by key: " + sessionKey);
		List<UserSession> list = (List<UserSession>) getHibernateTemplate().find("from UserSession where sessionKey = ?", new String[] { sessionKey });
		if (list.size() == 0) {
			return null;
		}
		UserSession session = list.get(0);
		log.debug("Found session: " + session);
		return session;
	}

	public void saveUserSession(UserSession session) {
		log.debug("Saving session record: " + session);
		getHibernateTemplate().saveOrUpdate(session);
		log.debug("Finished saving the session.");
	}

	@SuppressWarnings("unchecked")
	public List<UserSession> findAll() {
		List<UserSession> sessions = (List<UserSession>) this.getAll(UserSession.class);
		return sessions;
	}
}
