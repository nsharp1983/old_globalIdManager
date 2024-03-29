package com.ats.aempi.configuration.xml.fileloader;


/**
 * An XML data-fields(@http://configuration.openempi.openhie.org/file-loader).
 *
 * This is a complex type.
 */
public interface DataFields extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(DataFields.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sCA8A133777385F608BAA07BF61E1591A").resolveHandle("datafieldsa71ftype");
    
    /**
     * Gets array of all "data-field" elements
     */
    com.ats.aempi.configuration.xml.fileloader.DataField[] getDataFieldArray();
    
    /**
     * Gets ith "data-field" element
     */
    com.ats.aempi.configuration.xml.fileloader.DataField getDataFieldArray(int i);
    
    /**
     * Returns number of "data-field" element
     */
    int sizeOfDataFieldArray();
    
    /**
     * Sets array of all "data-field" element
     */
    void setDataFieldArray(com.ats.aempi.configuration.xml.fileloader.DataField[] dataFieldArray);
    
    /**
     * Sets ith "data-field" element
     */
    void setDataFieldArray(int i, com.ats.aempi.configuration.xml.fileloader.DataField dataField);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "data-field" element
     */
    com.ats.aempi.configuration.xml.fileloader.DataField insertNewDataField(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "data-field" element
     */
    com.ats.aempi.configuration.xml.fileloader.DataField addNewDataField();
    
    /**
     * Removes the ith "data-field" element
     */
    void removeDataField(int i);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static com.ats.aempi.configuration.xml.fileloader.DataFields newInstance() {
          return (com.ats.aempi.configuration.xml.fileloader.DataFields) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.ats.aempi.configuration.xml.fileloader.DataFields newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.ats.aempi.configuration.xml.fileloader.DataFields) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.ats.aempi.configuration.xml.fileloader.DataFields parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.fileloader.DataFields) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.ats.aempi.configuration.xml.fileloader.DataFields parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.fileloader.DataFields) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.ats.aempi.configuration.xml.fileloader.DataFields parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.fileloader.DataFields) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.ats.aempi.configuration.xml.fileloader.DataFields parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.fileloader.DataFields) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.ats.aempi.configuration.xml.fileloader.DataFields parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.fileloader.DataFields) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.ats.aempi.configuration.xml.fileloader.DataFields parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.fileloader.DataFields) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.ats.aempi.configuration.xml.fileloader.DataFields parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.fileloader.DataFields) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.ats.aempi.configuration.xml.fileloader.DataFields parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.fileloader.DataFields) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.ats.aempi.configuration.xml.fileloader.DataFields parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.fileloader.DataFields) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.ats.aempi.configuration.xml.fileloader.DataFields parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.ats.aempi.configuration.xml.fileloader.DataFields) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.ats.aempi.configuration.xml.fileloader.DataFields parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.fileloader.DataFields) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.ats.aempi.configuration.xml.fileloader.DataFields parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.fileloader.DataFields) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.ats.aempi.configuration.xml.fileloader.DataFields parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.fileloader.DataFields) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.ats.aempi.configuration.xml.fileloader.DataFields parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.ats.aempi.configuration.xml.fileloader.DataFields) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static com.ats.aempi.configuration.xml.fileloader.DataFields parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.ats.aempi.configuration.xml.fileloader.DataFields) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static com.ats.aempi.configuration.xml.fileloader.DataFields parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.ats.aempi.configuration.xml.fileloader.DataFields) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
