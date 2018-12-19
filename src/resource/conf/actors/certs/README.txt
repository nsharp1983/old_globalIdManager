README.txt - This file.
OpenPIXPDQ_2011_Keystore.p12 - keystore with root certs and signed master key
OpenPIXPDQ_2011_Keystore.jks - keystore with root certs and signed master key
OpenPIXPDQ_2011_Truststore.jks - truststore for holding CA cert that signed all other certs 

Connectathon 2011 (CA signed certificates)
==========================================
#generate keystore:
openssl pkcs12 -export -out OpenPIXPDQ_2011_Keystore.p12 -in MOSS/PIX_X_REF_MGR_MOSS/PIX_X_REF_MGR_MOSS.cert.pem -inkey MOSS/PIX_X_REF_MGR_MOSS/PIX_X_REF_MGR_MOSS.key.pem

#generate truststore:
keytool -import -v -trustcacerts -file MOSS/PIX_X_REF_MGR_MOSS\ca.cert.pem -keystore OpenPIXPDQ_2011_Truststore.jks -storepass password -alias mir

# import my cert into truststore
keytool -importkeystore -srckeystore OpenPIXPDQ_2011_Keystore.p12 -destkeystore OpenPIXPDQ_2011_Truststore.jks -srcstoretype PKCS12 -deststoretype JKS -srcstorepass password -deststorepass password

#list results
keytool -list -v -keystore OpenPIXPDQ_2011_Truststore.jks -storepass password

#Converting p12 keystore to jks keystore
java -cp c:\tools\jetty\jetty-6.1.14\lib\jetty-6.1.14.jar org.mortbay.jetty.security.PKCS12Import OpenPIXPDQ_2011_Keystore.p12 OpenPIXPDQ_2011_Keystore.jks
