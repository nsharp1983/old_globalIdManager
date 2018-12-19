package com.ats.apixpdq.common;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMException;
import org.apache.axiom.soap.SOAPFault;
//import org.apache.axis2.context.MessageContext;
//import org.apache.axis2.transport.http.TransportHeaders;
import org.apache.commons.logging.LogFactory;
import com.ats.aexchange.syslog.Log;
import com.ats.aexchange.syslog.LogMessage;
import com.ats.aexchange.syslog.LoggerException;
import com.ats.aexchange.config.PropertyFacade;

public class PixPdqSysLogService {
	protected Log log = null;
	private final static org.apache.commons.logging.Log logger = LogFactory.getLog(PixPdqSysLogService.class);
	String serviceName;
	boolean is_secure;
	String incomingIPAaddress;
	public LogMessage logMessage;
	//protected MessageContext messageContext = null;

	// Fields
	static public final String date = "Date";
	static public final String isSecure = "is Secure";
	static public final String service = "Service"; 
	static public final String fromIpAddress = "IP address From";
	static public final String endpoint = "URI To";

	/*
	protected boolean isSecure() {
		String protocol = getMessageContext().getIncomingTransportName();
		if (protocol != null && protocol.equalsIgnoreCase("https")) {
			return true;
		}
		return false;
	}

	public MessageContext getMessageContext() {
		return MessageContext.getCurrentMessageContext();
	}
	*/

	protected String getClientIPAddress() {
		return incomingIPAaddress;
	}

	/*
	protected OMElement beginTransaction(String service_name, OMElement request) {

		this.serviceName = service_name;

		String incoming_ip_address = null;
		if (getMessageContext().getFrom() != null) {
			incoming_ip_address = getMessageContext().getFrom().getAddress();
		} else {
			incoming_ip_address = (String) getMessageContext().getProperty(MessageContext.REMOTE_ADDR);
		}

		logger.info("Start " + service_name + " : " + incoming_ip_address + " : " + getMessageContext().getTo().toString());
		try {
			startTransactionLog();
			if (log != null && logMessage == null) {
				logMessage = log.createMessage(incoming_ip_address);
			}
			if(logMessage != null){
			logMessage.addOtherParam(service, service_name);
			is_secure = getMessageContext().getTo().toString().indexOf("https://") != -1;
			logMessage.addHTTPParam(isSecure, (is_secure) ? "true" : "false");
			logMessage.addHTTPParam(date, getDateTime());
			logMessage.setSecure(is_secure);
			if (request != null)
				logMessage.addOtherParam("Request", request.toString());
			else {
				logMessage.addErrorParam("Error", "Cannot access request body in DsubSysLogService.beginTransaction()");
				return null;
			}

			TransportHeaders transportHeaders = (TransportHeaders) getMessageContext().getProperty("TRANSPORT_HEADERS");
			for (Object o_key : transportHeaders.keySet()) {
				String key = (String) o_key;
				String value = (String) transportHeaders.get(key);

				if (logger.isDebugEnabled()) {
					logger.debug("request header=" + key + ", value=" + value);
				}

				Vector<String> thdrs = new Vector<String>();
				thdrs.add(key + " : " + value);
				addHttp("HTTP Header", thdrs);
			}

			if (getMessageContext().getEnvelope().getHeader() != null) {
				try {
					addSoap("Soap Header", getMessageContext().getEnvelope().getHeader().toStringWithConsume());
				} catch (OMException e) {
				} catch (XMLStreamException e) {
				}
			}

			if (getMessageContext().getEnvelope().getBody() != null) {
				try {
					addSoap("Soap Envelope", getMessageContext().getEnvelope().toStringWithConsume());
				} catch (OMException e) {
				} catch (XMLStreamException e) {
				}
			}
			logMessage.addHTTPParam(fromIpAddress, incoming_ip_address);
			logMessage.addHTTPParam(endpoint, getMessageContext().getTo().toString());
			}
			return null; // no error
		} catch (LoggerException e) {
			logger.error("LoggerException: new Log: " + e.getMessage());
			e.printStackTrace();
			stopTransactionLog();
			return start_up_error(request, e, "Internal Error:Cannot access Test Log Facility");
		}
	}
	*/

	protected void endTransaction(boolean status) {
		logger.info(serviceName + " " + " : " + ((status) ? "Pass" : "Fail"));
		stopTransactionLog();
	}

	public void generateLogMessage(OMElement response) {
		try {
			if(logMessage != null)
				logMessage.addOtherParam("Response", response.toString());
		} catch (Exception e) {

		}
	}

	protected OMElement endTransaction(OMElement request, Exception e,
			String message) {
		if (message == null || message.equals(""))
			message = e.getMessage();
		logger.error("Exception: " + exception_details(e));
		OMElement response = start_up_error(request, e, message);
		generateLogMessage(response);
		endTransaction(false);
		return response;
	}

	protected OMElement endTransaction(OMElement request, Exception e,
			String message, String error_type) {
		if (message == null || message.equals(""))
			message = e.getMessage();
		logger.error("Exception: " + exception_details(e));
		OMElement response = start_up_error(request, e, message);
		generateLogMessage(response);
		endTransaction(false);
		return response;
	}

	public OMElement start_up_error(OMElement request, Exception e,
			String message) {
		return start_up_error(request, e, message, false);
	}

	public OMElement start_up_error(OMElement request, Object e,
			String message, boolean log) {
		String error_type = null;

		if (e instanceof PixPdqErrorCodeException) {
			PixPdqErrorCodeException ec = (PixPdqErrorCodeException) e;
			error_type = ec.getErrorCode();
			message = "Forced Error";
		}
		if (logMessage != null)
			try {
				logMessage.addErrorParam("Error:- ErrorType:" + error_type, e + "\n" + message);
			} catch (LoggerException e1) {
				e1.printStackTrace();
			}
		return null;
	}

	public String getRTransactionName(OMElement req) {

		if ("PRPA_IN201301UV02".equalsIgnoreCase(req.getLocalName()))
			return "Pix.Create";
		else if ("PRPA_IN201302UV02".equalsIgnoreCase(req.getLocalName()))
			return "Pix.Update";
		else if ("PRPA_IN201304UV02".equalsIgnoreCase(req.getLocalName()))
			return "Pix.Merge";
		else if ("PRPA_IN201309UV02".equalsIgnoreCase(req.getLocalName()))
			return "Pix.Query";
		else if ("PRPA_IN201305UV02".equalsIgnoreCase(req.getLocalName()))
			return "Pdq.FindCandidatesQuery";
		else if ("QUQI_IN000003UV01".equalsIgnoreCase(req.getLocalName()))
			return "Pdq.ActivateQueryContinue";
		else if ("QUQI_IN000003UV01_Cancel".equalsIgnoreCase(req.getLocalName()))
			return "Pdq.QueryCancel";
		else
			return "Unknown";

	}

	protected void startTransactionLog() throws LoggerException {
		String enableSysLog = PropertyFacade.getString(PixPdqConstants.ENABLE_SYSLOG);
		if (log == null && "true".equalsIgnoreCase(enableSysLog)) {
			logger.info("+++++++++++++++++++++ start transaction log");
			try {
				log = (Log) PixPdqFactory.getInstance().getBean("logsService");
			} catch (Exception e) {
				e.printStackTrace();
				new LoggerException("Exception while creating logsService bean object:" + e.getMessage(), e);
			}

		}
	}

	protected void stopTransactionLog() {
		try {
			if (log != null) {
				if (logMessage != null)
					log.writeMessage(logMessage);
				logger.info("end "
						+ serviceName
						+ " :"
						+ ((logMessage == null) ? "null" : logMessage.getMessageID()));
				log = null;
				logMessage = null;
				logger.info("+++++++++++++++++++++ stop transaction log");
			}
		} catch (LoggerException e) {
			logger.error("LoggerException: " + exception_details(e));
		}
	}

	protected String exception_details(Object e) {
		if (e == null)
			return "No Additional Details Available";
		if (e instanceof Exception)
			return exception_details((Exception) e);
		if (e instanceof String)
			return exception_details((String) e);
		return exception_details(e.toString());
	}

	protected String exception_details(Exception e) {
		if (e == null)
			return "No stack trace available";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		e.printStackTrace(ps);

		return "Exception thrown: " + e.getClass().getName() + "\n" + e.getMessage() + "\n" + new String(baos.toByteArray());
	}

	protected String exception_details(String e) {
		return e;
	}

	private void addHttp(String title, Vector<String> t) {
		StringBuffer buffer = new StringBuffer();
		for (String s : t) {
			buffer.append(s + "  ");
		}

		try {
			if (logMessage != null)
				logMessage.addHTTPParam(title, buffer.toString());
		} catch (LoggerException e) {
		}
	}

	private void addSoap(String t, String s) {
		try {
			if (logMessage != null)	
				logMessage.addSoapParam(t, s);
		} catch (LoggerException e) {
		}
	}

	protected void addError(String s) {
		try {
			if (logMessage != null)
				logMessage.addErrorParam("Error", s);
		} catch (LoggerException e) {
		}
	}

	protected void addOther(String name, String s) {
		try {
			if (logMessage != null)
				logMessage.addOtherParam(name, s);
		} catch (LoggerException e) {
		}
	}

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/*
	protected String getForcedErrorCode() {
		String code = null;
		try {
			code = getMessageContext().getParameter("ForceErrorCode")
					.getValue().toString();
		} catch (Exception e) {

		}
		if (code == null)
			return null;
		if ("".equals(code))
			return null;
		return code;
	}
	

	protected void forceForcedError() throws PixPdqErrorCodeException {
		String code = getForcedErrorCode();
		if (code == null)
			return;
		throw new PixPdqErrorCodeException("Forced Error", code);
	}
	*/

	public void logResponse(OMElement response) {

		// generateAuditLog(response);

		if (logMessage == null) {
			logger.fatal("logMessage is null\n");
			return;
		}
		try {
			if (response instanceof SOAPFault) {
				logMessage.setPass(false);
				SOAPFault soapFault = (SOAPFault) response;
				logMessage.addErrorParam("Errors", soapFault.getDetail().getText());
			} else
				logMessage.setPass(true);

			logMessage.addOtherParam("Response", response.toString());
		} catch (LoggerException e) {
			logger.error("**************ERROR: Logger exception attempting to return to user");
		}
	}
}
