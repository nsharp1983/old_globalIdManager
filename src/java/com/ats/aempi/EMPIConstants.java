package com.ats.aempi;

import com.ats.aexchange.config.PropertyFacade;


/**
 * Constant values used throughout the application.
 * 
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class EMPIConstants {
    //~ Static fields/initializers =============================================

    /**
     * The name of the ResourceBundle used in this application
     */
    public static final String BUNDLE_KEY = "ApplicationResources";

    /**
     * File separator from System properties
     */
    public static final String FILE_SEP = System.getProperty("file.separator");

    /**
     * User home from System properties
     */
    public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;

    /**
     * AEMPI home directory
     */
    public static final String AEMPI_HOME = "aempi.home";
    
    public static final String AEMPI_HOME_VALUE = System.getProperty(AEMPI_HOME);

	public static final String AEMPI_HOME_ENV_VARIABLE = "AEMPI_HOME";
    
	//PM-2012-10-30 修改环境变量读取方式从默认系统环境变量放入配置文件中
	public static final String AEMPI_HOME_ENV_VALUE =PropertyFacade.getString(AEMPI_HOME); 
	
    /**
     * System parameter name for list of extension contexts
     */
    public static final String AEMPI_EXTENSION_CONTEXTS = "aempi.extension.contexts";
    
    /**
     * Name for extension contexts property file name
     */
    public static final String AEMPI_EXTENSION_CONTEXTS_PROPERTY_FILENAME = "aempi-extension-contexts.properties";
    	
    /**
     * Default upload file directory
     */
    public static final String DEFAULT_FILE_REPOSITORY_DIRECTORY = "fileRepository";
    
    /**
     * The name of the configuration hashmap stored in application scope.
     */
    public static final String CONFIG = "appConfig";

    /**
     * Session scope attribute that holds the locale set by the user. By setting this key
     * to the same one that Struts uses, we get synchronization in Struts w/o having
     * to do extra work or have two session-level variables.
     */
    public static final String PREFERRED_LOCALE_KEY = "org.apache.struts2.action.LOCALE";

    /**
     * The request scope attribute under which an editable user form is stored
     */
    public static final String USER_KEY = "userForm";

    /**
     * The request scope attribute that holds the user list
     */
    public static final String USER_LIST = "userList";

    /**
     * The request scope attribute for indicating a newly-registered user
     */
    public static final String REGISTERED = "registered";

    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String ADMIN_ROLE = "ROLE_ADMIN";

    /**
     * The name of the User role, as specified in web.xml
     */
    public static final String USER_ROLE = "ROLE_USER";

    /**
     * The name of the user's role list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String USER_ROLES = "userRoles";

    /**
     * The name of the available roles list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String AVAILABLE_ROLES = "availableRoles";

    /**
     * The name of the CSS Theme setting.
     */
    public static final String CSS_THEME = "csstheme";

    /**
     * The weight that is assigned to a pair of records when they are merged intentionally and not by the matching algorithm
     */
	public static final Double MERGE_RECORDS_WEIGHT = 1.0;

	/**
	 * File name of the serialized FellegiSunterConfiguration information file
	 */
	public static final String FELLEGI_SUNTER_CONFIG_FILE_NAME = "FellegiSunterConfiguration.ser";

	/**
	 * Maximum number of custom fields
	 */
	public static final Integer CUSTOM_FIELD_MAX_NUMBER = 30;

}
