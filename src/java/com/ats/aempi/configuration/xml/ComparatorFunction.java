package com.ats.aempi.configuration.xml;


/**
 * An XML comparator-function(@http://configuration.openempi.openhie.org/mpiconfig).
 *
 * This is a complex type.
 */
public interface ComparatorFunction extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ComparatorFunction.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sCA8A133777385F608BAA07BF61E1591A").resolveHandle("comparatorfunctiond076type");
    
    /**
     * Gets the "function-name" element
     */
    java.lang.String getFunctionName();
    
    /**
     * Gets (as xml) the "function-name" element
     */
    org.apache.xmlbeans.XmlString xgetFunctionName();
    
    /**
     * Sets the "function-name" element
     */
    void setFunctionName(java.lang.String functionName);
    
    /**
     * Sets (as xml) the "function-name" element
     */
    void xsetFunctionName(org.apache.xmlbeans.XmlString functionName);
    
    /**
     * Gets the "parameters" element
     */
    com.ats.aempi.configuration.xml.Parameters getParameters();
    
    /**
     * True if has "parameters" element
     */
    boolean isSetParameters();
    
    /**
     * Sets the "parameters" element
     */
    void setParameters(com.ats.aempi.configuration.xml.Parameters parameters);
    
    /**
     * Appends and returns a new empty "parameters" element
     */
    com.ats.aempi.configuration.xml.Parameters addNewParameters();
    
    /**
     * Unsets the "parameters" element
     */
    void unsetParameters();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static com.ats.aempi.configuration.xml.ComparatorFunction newInstance() {
          return (com.ats.aempi.configuration.xml.ComparatorFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.ats.aempi.configuration.xml.ComparatorFunction newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.ats.aempi.configuration.xml.ComparatorFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.ats.aempi.configuration.xml.ComparatorFunction parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.ComparatorFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.ats.aempi.configuration.xml.ComparatorFunction parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.ComparatorFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.ats.aempi.configuration.xml.ComparatorFunction parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.ComparatorFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.ats.aempi.configuration.xml.ComparatorFunction parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.ComparatorFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.ats.aempi.configuration.xml.ComparatorFunction parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.ComparatorFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.ats.aempi.configuration.xml.ComparatorFunction parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.ComparatorFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.ats.aempi.configuration.xml.ComparatorFunction parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.ComparatorFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.ats.aempi.configuration.xml.ComparatorFunction parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.ComparatorFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.ats.aempi.configuration.xml.ComparatorFunction parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.ComparatorFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.ats.aempi.configuration.xml.ComparatorFunction parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.ComparatorFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.ats.aempi.configuration.xml.ComparatorFunction parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.ComparatorFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.ats.aempi.configuration.xml.ComparatorFunction parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.ComparatorFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.ats.aempi.configuration.xml.ComparatorFunction parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.ComparatorFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.ats.aempi.configuration.xml.ComparatorFunction parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.ComparatorFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static com.ats.aempi.configuration.xml.ComparatorFunction parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.ats.aempi.configuration.xml.ComparatorFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static com.ats.aempi.configuration.xml.ComparatorFunction parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.ats.aempi.configuration.xml.ComparatorFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
