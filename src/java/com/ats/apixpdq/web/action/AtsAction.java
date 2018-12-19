package com.ats.apixpdq.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.xsom.impl.scd.Iterators.Map;

public class AtsAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;

	public ActionContext ctx;
	public HttpServletRequest request;
	public HttpServletResponse response;
	/*
	public SessionMap session;
	
	public void setSession(Map map) {   
        this.session = (SessionMap) map;   
    }
    */

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public AtsAction() {
		ctx = ActionContext.getContext();
		request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
		//session = request.getSession();
	}
	
	
}
