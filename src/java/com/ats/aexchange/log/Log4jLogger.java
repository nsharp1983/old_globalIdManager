package com.ats.aexchange.log;

import java.io.StringWriter;
import java.util.Iterator;

import javax.xml.soap.AttachmentPart;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;

import com.ats.aexchange.utils.AxiomUtil;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.EncodingCharacters;
import ca.uhn.hl7v2.parser.PipeParser;


/**
 * This class implements an IHE Actor logger that uses log4j.
 * The MESA Logger interface is used because it was convenient.
 * That interface should probably be renamed.
 * 
 * @author Jim Firby
 * @version 1.0 - Jan 12, 2006
 */
public class Log4jLogger implements IMesaLogger {
	
	/* The logger to write too */
	private static final Logger log = Logger.getLogger(Log4jLogger.class);

	public void writeString(String message) {
		log.info(message);
	}

	public void writeSoapMessage(SOAPMessage message) {
		if (log.isInfoEnabled()) {
			if (message != null) {
				// Write out the SOAP part of the message
				DOMSource source = new DOMSource(message.getSOAPPart());
				StreamResult result = new StreamResult(new StringWriter());
				Transformer transformer = null;
				try {
					TransformerFactory factory = TransformerFactory.newInstance();
					try {
						// Try this to indent the XML output, it works for some XML implementors
						factory.setAttribute("indent-number", new Integer(2));
					} catch (Exception e) {}
					transformer = factory.newTransformer();
					try {
						// Try this to indent the XML output, it works for some other XML implementors
						transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
					} catch (Exception e) {}
					transformer.setOutputProperty(OutputKeys.INDENT, "yes");
					transformer.setOutputProperty(OutputKeys.METHOD, "xml");
					transformer.setOutputProperty(OutputKeys.MEDIA_TYPE, "text/xml");	
					transformer.transform(source, result);
					log.info(result.getWriter().toString());
				} catch (Exception e) {
					log.error("Can't write SOAP message to logger", e);
				}
				// Write out information about the attachments
				Iterator attachments = message.getAttachments();
				while (attachments.hasNext()) {
					AttachmentPart attachment = (AttachmentPart)attachments.next();
					log.info("Attachment: " + attachment.getContentId() + ", " + attachment.getContentType());
				}
			} else {
				log.info("NULL");
			}
		}
	}
	
	public void writeAxiomElementMessage(OMElement message) {
		if (log.isInfoEnabled()) {
			if (message != null) {
				StreamResult result = new StreamResult(new StringWriter());
				try {
					AxiomUtil.prettify(message, result);
					log.info(result.getWriter().toString());
				}catch(Exception e) {
					log.error("Can't write OMElement to logger", e);
				}
				log.info(message);
			} else {
				log.info("NULL");
			}
		}
	}

	public void writeHL7Message(Message message) {
		if (log.isInfoEnabled()) {
			if (message != null) {
				// Write out the message
				try {
					log.info(PipeParser.encode(message, new EncodingCharacters('|', "^~\\&")));
				} catch (HL7Exception e) {
					log.info("Invalid HL7 message", e);
				}
			} else {
				log.info("NULL");
			}
		}
	}

}
