package com.ats.apixpdq.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ats.aexchange.config.BootStrapProperties;
import com.ats.aexchange.config.ConfigurationException;
import com.ats.aexchange.config.PropertyFacade;

import com.ats.apixpdq.common.PatientBroker;
import com.ats.apixpdq.common.PixPdqFactory;
import com.ats.apixpdq.web.servlet.ConfigServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.misys.hieportal.sysmon.IJMXEventNotifier;

;/**
 *The starting servlet.Main functionality is to destroy all Actors 
 *
 */
 public class ConfigServlet extends HttpServlet  {
    private static Log log = LogFactory.getLog(ConfigServlet.class);
   
	public ConfigServlet() {
		System.setProperty("ca.uhn.hl7v2.llp.charset", "UTF-8");
		//System.setProperty("ca.uhn.hl7v2.llp.charset", "GB2312");
	} 
	
	/* 
	 * Destroys all Actors 
	 */
	public void destroy() {
		try {
			PixPdqConfigurationLoader.getInstance().resetConfiguration(null);
		} catch (Exception e) {
			log.error("Failed to destroy APIXPDQ actor configuration.", e);
		}
	}   	 	  	  	  
	
	public void init() throws ServletException {
        try {
            String[] propertyFiles = BootStrapProperties.getPropertyFiles(
                new String[]{"apixpdq.properties"});
            PropertyFacade.loadProperties(propertyFiles);
        } catch (ConfigurationException e) {
            log.error("Failed to load apixpdq.properties", e);
        }

		
		PixPdqConfigurationLoader loader = PixPdqConfigurationLoader.getInstance();
		
		boolean eventEnabled = PropertyFacade.getBoolean("enable.event");
		if(eventEnabled){
	        IJMXEventNotifier eventBean = (IJMXEventNotifier)PixPdqFactory.getInstance().getBean("pixEvent");
			loader.setEventBean(eventBean);
		}
		
		loader.loadActorConfiguration();
	}   
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		//do nothing
	}
}
