package com.ats.aexchange.log;

import javax.xml.soap.SOAPMessage;

import org.apache.axiom.om.OMElement;

import ca.uhn.hl7v2.model.Message;

/**
 * Interface that enables IHE Actors to write stuff to the
 * MESA test log, when appropriate.
 * 
 * @author Jim Firby
 * @version 1.0 - Nov 2, 2005
 */
public interface IMesaLogger {

	/**
	 * Writes a text string to the MESA log
	 * 
	 * @param message The text string to display
	 */
	public void writeString(String message);

	/**
	 * Writes a SOAP message to the MESA log
	 * 
	 * @param message The SOAP message to display
	 */
	public void writeSoapMessage(SOAPMessage message);

	/**
	 * Writes out an Axiom OMElement message to the logger.
	 * 
	 * @param message the element to display
	 */
	public void writeAxiomElementMessage(OMElement message);

	/**
	 * Writes an HL7 message to the MESA log
	 * 
	 * @param message the HL7 message to display
	 */
	public void writeHL7Message(Message message);
}
