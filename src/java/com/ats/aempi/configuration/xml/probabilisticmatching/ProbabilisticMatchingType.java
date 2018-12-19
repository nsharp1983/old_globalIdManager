package com.ats.aempi.configuration.xml.probabilisticmatching;


/**
 * An XML probabilistic-matching-type(@http://configuration.openempi.openhie.org/probabilistic-matching).
 *
 * This is a complex type.
 */
public interface ProbabilisticMatchingType extends com.ats.aempi.configuration.xml.MatchingConfigurationType
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ProbabilisticMatchingType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sCA8A133777385F608BAA07BF61E1591A").resolveHandle("probabilisticmatchingtypea638type");
    
    /**
     * Gets the "false-negative-probability" element
     */
    float getFalseNegativeProbability();
    
    /**
     * Gets (as xml) the "false-negative-probability" element
     */
    org.apache.xmlbeans.XmlFloat xgetFalseNegativeProbability();
    
    /**
     * True if has "false-negative-probability" element
     */
    boolean isSetFalseNegativeProbability();
    
    /**
     * Sets the "false-negative-probability" element
     */
    void setFalseNegativeProbability(float falseNegativeProbability);
    
    /**
     * Sets (as xml) the "false-negative-probability" element
     */
    void xsetFalseNegativeProbability(org.apache.xmlbeans.XmlFloat falseNegativeProbability);
    
    /**
     * Unsets the "false-negative-probability" element
     */
    void unsetFalseNegativeProbability();
    
    /**
     * Gets the "false-positive-probability" element
     */
    float getFalsePositiveProbability();
    
    /**
     * Gets (as xml) the "false-positive-probability" element
     */
    org.apache.xmlbeans.XmlFloat xgetFalsePositiveProbability();
    
    /**
     * True if has "false-positive-probability" element
     */
    boolean isSetFalsePositiveProbability();
    
    /**
     * Sets the "false-positive-probability" element
     */
    void setFalsePositiveProbability(float falsePositiveProbability);
    
    /**
     * Sets (as xml) the "false-positive-probability" element
     */
    void xsetFalsePositiveProbability(org.apache.xmlbeans.XmlFloat falsePositiveProbability);
    
    /**
     * Unsets the "false-positive-probability" element
     */
    void unsetFalsePositiveProbability();
    
    /**
     * Gets the "match-fields" element
     */
    com.ats.aempi.configuration.xml.probabilisticmatching.MatchFields getMatchFields();
    
    /**
     * Sets the "match-fields" element
     */
    void setMatchFields(com.ats.aempi.configuration.xml.probabilisticmatching.MatchFields matchFields);
    
    /**
     * Appends and returns a new empty "match-fields" element
     */
    com.ats.aempi.configuration.xml.probabilisticmatching.MatchFields addNewMatchFields();
    
    /**
     * Gets the "config-file-directory" element
     */
    java.lang.String getConfigFileDirectory();
    
    /**
     * Gets (as xml) the "config-file-directory" element
     */
    org.apache.xmlbeans.XmlString xgetConfigFileDirectory();
    
    /**
     * Sets the "config-file-directory" element
     */
    void setConfigFileDirectory(java.lang.String configFileDirectory);
    
    /**
     * Sets (as xml) the "config-file-directory" element
     */
    void xsetConfigFileDirectory(org.apache.xmlbeans.XmlString configFileDirectory);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType newInstance() {
          return (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
