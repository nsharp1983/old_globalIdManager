package com.ats.aexchange.utils;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;

/**
 * This utility class defines some help methods for OMElement.
 * 
 * @author <a href="mailto:wenzhi.li@misys.com">Wenzhi Li</a>
 */
public class OMUtil {

	/**
	 * Converts from an well-structured XML string to an OMElement.
	 *  
	 * @param input_string the input XML string to be converted
	 * @return an OMElement represents the XML string
	 * @throws XMLStreamException
	 */
	public static OMElement xmlStringToOM(String inputString) throws XMLStreamException {
		byte[] ba = inputString.getBytes();

//		create the parser
		XMLStreamReader parser = XMLInputFactory.newInstance().createXMLStreamReader(new ByteArrayInputStream(ba));
//		create the builder
		StAXOMBuilder builder = new StAXOMBuilder(parser);

//		get the root element (in this case the envelope)
		OMElement documentElement =  builder.getDocumentElement();

		return documentElement;
	}

	public static OMElement deepCopy(OMElement in) throws XMLStreamException {
		if (in == null)
			return null;

		StringBuffer buf = new StringBuffer(in.toString());

		return xmlStringToOM(buf.toString());
	}

	public static OMElement firstChildWithLocalName(OMElement ele, String localName) {
		for (Iterator it=ele.getChildElements(); it.hasNext(); ) {
			OMElement child = (OMElement) it.next();
			if (child.getLocalName().equals(localName))
				return child;
		}
		return null;
	}

	public static OMElement firstChildWithLocalNameEndingWith(OMElement ele, String localNameSuffix) {
		for (Iterator it=ele.getChildElements(); it.hasNext(); ) {
			OMElement child = (OMElement) it.next();
			if (child.getLocalName().endsWith(localNameSuffix))
				return child;
		}
		return null;
	}

	public static List<OMElement> childrenWithLocalName(OMElement ele, String localName) {
		List<OMElement> al = new ArrayList<OMElement>();
		for (Iterator it=ele.getChildElements(); it.hasNext(); ) {
			OMElement child = (OMElement) it.next();
			if (child.getLocalName().equals(localName))
				al.add(child);
		}
		return al;
	}

	public static List<String> childrenLocalNames(OMElement ele) {
		List<String> al = new ArrayList<String>();
		for (Iterator it=ele.getChildElements(); it.hasNext(); ) {
			OMElement child = (OMElement) it.next();
				al.add(child.getLocalName());
		}
		return al;
	}

}
