package com.ats.aempi.configuration.xml;


/**
 * An XML match-field(@http://configuration.openempi.openhie.org/mpiconfig).
 *
 * This is a complex type.
 */
public interface MatchField extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(MatchField.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sC821FAD6FF832672F8813822659E4CCD").resolveHandle("matchfieldf7f9type");
    
    /**
     * Gets the "field-name" element
     */
    java.lang.String getFieldName();
    
    /**
     * Gets (as xml) the "field-name" element
     */
    org.apache.xmlbeans.XmlString xgetFieldName();
    
    /**
     * Sets the "field-name" element
     */
    void setFieldName(java.lang.String fieldName);
    
    /**
     * Sets (as xml) the "field-name" element
     */
    void xsetFieldName(org.apache.xmlbeans.XmlString fieldName);
    
    /**
     * Gets the "agreement-probability" element
     */
    float getAgreementProbability();
    
    /**
     * Gets (as xml) the "agreement-probability" element
     */
    org.apache.xmlbeans.XmlFloat xgetAgreementProbability();
    
    /**
     * True if has "agreement-probability" element
     */
    boolean isSetAgreementProbability();
    
    /**
     * Sets the "agreement-probability" element
     */
    void setAgreementProbability(float agreementProbability);
    
    /**
     * Sets (as xml) the "agreement-probability" element
     */
    void xsetAgreementProbability(org.apache.xmlbeans.XmlFloat agreementProbability);
    
    /**
     * Unsets the "agreement-probability" element
     */
    void unsetAgreementProbability();
    
    /**
     * Gets the "disagreement-probability" element
     */
    float getDisagreementProbability();
    
    /**
     * Gets (as xml) the "disagreement-probability" element
     */
    org.apache.xmlbeans.XmlFloat xgetDisagreementProbability();
    
    /**
     * True if has "disagreement-probability" element
     */
    boolean isSetDisagreementProbability();
    
    /**
     * Sets the "disagreement-probability" element
     */
    void setDisagreementProbability(float disagreementProbability);
    
    /**
     * Sets (as xml) the "disagreement-probability" element
     */
    void xsetDisagreementProbability(org.apache.xmlbeans.XmlFloat disagreementProbability);
    
    /**
     * Unsets the "disagreement-probability" element
     */
    void unsetDisagreementProbability();
    
    /**
     * Gets the "comparator-function-name" element
     */
    java.lang.String getComparatorFunctionName();
    
    /**
     * Gets (as xml) the "comparator-function-name" element
     */
    org.apache.xmlbeans.XmlString xgetComparatorFunctionName();
    
    /**
     * True if has "comparator-function-name" element
     */
    boolean isSetComparatorFunctionName();
    
    /**
     * Sets the "comparator-function-name" element
     */
    void setComparatorFunctionName(java.lang.String comparatorFunctionName);
    
    /**
     * Sets (as xml) the "comparator-function-name" element
     */
    void xsetComparatorFunctionName(org.apache.xmlbeans.XmlString comparatorFunctionName);
    
    /**
     * Unsets the "comparator-function-name" element
     */
    void unsetComparatorFunctionName();
    
    /**
     * Gets the "match-threshold" element
     */
    float getMatchThreshold();
    
    /**
     * Gets (as xml) the "match-threshold" element
     */
    org.apache.xmlbeans.XmlFloat xgetMatchThreshold();
    
    /**
     * True if has "match-threshold" element
     */
    boolean isSetMatchThreshold();
    
    /**
     * Sets the "match-threshold" element
     */
    void setMatchThreshold(float matchThreshold);
    
    /**
     * Sets (as xml) the "match-threshold" element
     */
    void xsetMatchThreshold(org.apache.xmlbeans.XmlFloat matchThreshold);
    
    /**
     * Unsets the "match-threshold" element
     */
    void unsetMatchThreshold();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static com.ats.aempi.configuration.xml.MatchField newInstance() {
          return (com.ats.aempi.configuration.xml.MatchField) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.ats.aempi.configuration.xml.MatchField newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.ats.aempi.configuration.xml.MatchField) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.ats.aempi.configuration.xml.MatchField parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.MatchField) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.ats.aempi.configuration.xml.MatchField parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.MatchField) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.ats.aempi.configuration.xml.MatchField parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.MatchField) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.ats.aempi.configuration.xml.MatchField parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.MatchField) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.ats.aempi.configuration.xml.MatchField parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.MatchField) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.ats.aempi.configuration.xml.MatchField parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.MatchField) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.ats.aempi.configuration.xml.MatchField parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.MatchField) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.ats.aempi.configuration.xml.MatchField parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.MatchField) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.ats.aempi.configuration.xml.MatchField parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.MatchField) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.ats.aempi.configuration.xml.MatchField parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.MatchField) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.ats.aempi.configuration.xml.MatchField parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.MatchField) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.ats.aempi.configuration.xml.MatchField parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.MatchField) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.ats.aempi.configuration.xml.MatchField parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.MatchField) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.ats.aempi.configuration.xml.MatchField parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.MatchField) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static com.ats.aempi.configuration.xml.MatchField parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.ats.aempi.configuration.xml.MatchField) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static com.ats.aempi.configuration.xml.MatchField parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.ats.aempi.configuration.xml.MatchField) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
