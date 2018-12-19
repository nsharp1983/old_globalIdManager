package com.ats.apixpdq.common;

/**
 * This class defines all the constants used by APIXPDQ.
 *
 * @author <a href="mailto:wenzhi.li@misys.com">Wenzhi Li</a>
 *
 */
public class PixPdqConstants {

	//Actor Types
	public static final String PIX_MANAGER    = "PixManager";
	public static final String PIX_MANAGER_V3 = "PixManagerV3";
	public static final String PD_SUPPLIER    = "PdSupplier";
	public static final String PD_SUPPLIER_V3 = "PdSupplierV3";
	public static final String HIUP_STATUS    = "StatusChange";
	public static final String SECURE_NODE    = "SecureNode";
	
	//Connection Types
	public static final String SERVER = "Server";
	public static final String XDS_REGISTRY = "XdsRegistry";
	public static final String PIX_CONSUMER = "PixConsumer";

	public static final String RECEIVING_APPLICATION = "ReceivingApplication";
	public static final String RECEIVING_FACILITY = "ReceivingFacility";
	
	//property names
	public static final String VALIDATE_RECEIVING_APPLICATION = "validate.receiving.application";
	public static final String VALIDATE_RECEIVING_FACILITY    = "validate.receiving.facility";
	
	//Constants
	public static final String SSN_OID = "2.16.840.1.113883.4.1";
	
	//Dsub SysLog 
    public static final String ENABLE_SYSLOG = "enableSysLog";
    
    //dict-trans
    public static final String ENABLE_DICT_CHECK = "enable.dict.check";
    
    public static final String ENABLE_TRANS="enable.trans";
    
    public static final String ENABLE_NEW_UPDATE="enable.new.update";
    
    public static final String ENABLE_NEW_AUTO_MERGE="enable.new.auto.merge";
    
    public static final String CHECK_FIELD_BASE_NAME ="check.field.base.name";
    
    public static final String CHECK_FIELD_BASE_LS_NAME = "check.field.base.ls.name";
    
    public static final String CHECK_FIELD_BASE_DOMAIN ="check.field.base.domain";
}
