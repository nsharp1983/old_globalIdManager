package com.ats.apixpdq.web.service;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;

import com.ats.apixpdq.web.service.HiberUtil;

import java.sql.Connection;
import java.sql.SQLException;



/**
 * @author : Anil kumar
 * @version : 1.0  March 03, 2009
 */
public class HiberUtil {
    // TODO: Get the proper logging scheme later
    private static Logger logger = Logger.getLogger(HiberUtil.class.getName());
    private static final SessionFactory sessionFactory;


    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration config = new Configuration().configure();
            sessionFactory = config.buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            logger.fatal("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static final ThreadLocal<Session> threadSession = new ThreadLocal<Session>();
    private static final ThreadLocal<Transaction> threadTransaction = new ThreadLocal<Transaction>();

    public static Session getSession() {
        Session session = threadSession.get();
        try {
            if(session == null) {

            session = sessionFactory.openSession();
           // session.connection().setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            logger.debug("Opened session for thread :" + Thread.currentThread().getId());
            threadSession.set(session);
            }
        } catch(HibernateException e) {
           logger.error("Can not open session"+e);
        } /*catch(SQLException e) {
        	logger.error("Can not open session"+e);
        }*/
        return threadSession.get();
    }

    public static void closeSession(){
        try {
            Session session = threadSession.get();
            threadSession.set(null);
            if(session != null && session.isOpen())
                session.close();
            logger.debug("Closed session for thread :" + Thread.currentThread().getId());
        } catch(HibernateException e) {
        	logger.error("Can not close session"+e);
        }
    }

    public static void beginTransaction(){
        Transaction tx = threadTransaction.get();
        try {
            if(tx == null) {
                logger.debug("Starting transaction for Thread : " + Thread.currentThread().getId());
                tx = getSession().beginTransaction();
                threadTransaction.set(tx);
            }

        } catch(HibernateException e) {
        	logger.error("Can not start transaction"+e);
        }
    }

    public static void commitTransaction(){
        Transaction tx = threadTransaction.get();
        try {
            if(tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
                logger.debug("Commiting transaction for Thread : " + Thread.currentThread().getId());
                tx.commit();
            }
            threadTransaction.set(null);
        } catch(HibernateException e) {
        	logger.error("Can not commit transaction"+e);
        }
    }

    public static void rollbackTransaction() {
        Transaction tx = threadTransaction.get();
        try {
            threadTransaction.set(null);
            if(tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
                logger.debug("Trying to rollback transaction for Thread : " + Thread.currentThread().getId());
                tx.rollback();
            }
        } catch(HibernateException e) {
        	logger.error("Can not rollback transaction"+e);
        } finally {
            closeSession();
        }
    }
}

