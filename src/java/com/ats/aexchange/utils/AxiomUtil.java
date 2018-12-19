package com.ats.aexchange.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.axiom.om.OMElement;

/**
 * This class takes care of the utility methods related to 
 * Axiom elements.
 *  
 * @author Wenzhi Li
 * @version 2.1 Jan 22, 2008
 */
public class AxiomUtil {
    private static final String prettyPrintStylesheet = 
        "<xsl:stylesheet xmlns:xsl='http://www.w3.org/1999/XSL/Transform' version='1.0' " +  
                " xmlns:xalan='http://xml.apache.org/xslt' " +  
                " exclude-result-prefixes='xalan'>" +  
        "  <xsl:output method='xml' indent='yes' xalan:indent-amount='4'/>"  +  
        "  <xsl:strip-space elements='*'/>" +  
        "  <xsl:template match='/'>"  + 
        "      <xsl:apply-templates/>"  +
        "  </xsl:template>"   +
        "  <xsl:template match='node() | @*'>" +  
        "        <xsl:copy>"  + 
        "          <xsl:apply-templates select='node() | @*'/>" +  
        "        </xsl:copy>"   +
        "  </xsl:template>"   +
        "</xsl:stylesheet>"; 
    
    /**
     * Writes this OMElement in a prettified format to a StreamResult.
     *  
     * @param element the Axiom OMElement to be prettified
     * @param result the StreamResult as the output
     * @throws XMLStreamException
     * @throws TransformerConfigurationException
     * @throws TransformerException
     */
	public static void prettify(OMElement element, StreamResult result) 
		throws XMLStreamException, TransformerConfigurationException, TransformerException { 
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		element.serialize(baos); 
		
		Source stylesheetSource = new StreamSource(new ByteArrayInputStream(prettyPrintStylesheet.getBytes())); 
		Source xmlSource = new StreamSource(new ByteArrayInputStream(baos.toByteArray())); 
		
		TransformerFactory tf = TransformerFactory.newInstance(); 
		Templates templates = tf.newTemplates(stylesheetSource); 
		Transformer transformer = templates.newTransformer(); 
		transformer.transform(xmlSource, result); 
	}  

    /**
     * Converts this OMElement in a prettified format to a String.
     *  
     * @param element the Axiom OMElement to be prettified
     * @throws XMLStreamException
     * @throws TransformerConfigurationException
     * @throws TransformerException
     * @return the prettified String 
     */
	public static String prettify(OMElement element)
		throws XMLStreamException, TransformerConfigurationException, TransformerException { 
		if (element == null)
			return null;
		
		StreamResult result = new StreamResult(new StringWriter());
		AxiomUtil.prettify(element, result);
		return result.getWriter().toString();
	}
}
