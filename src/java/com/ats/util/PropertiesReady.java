package com.ats.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReady {
	private static Properties dataproperties = null;
	 /**
	 * @see
	 * @param key
	 * @param
	 * @return
	 * @since modefy dengwuping 20141113
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String GetName(String key,String file)
			throws FileNotFoundException, IOException {
		InputStream is = null;
		try{
			if(dataproperties==null){
				dataproperties = new Properties();
			}
			 is = Thread.currentThread().getContextClassLoader()
			        .getResourceAsStream(file);
			dataproperties.load(is);
			String wsdl = dataproperties.getProperty(key).toString();
			return wsdl;
		}finally{
			is.close();
		}
	}
	
	public static Properties getProperties(String file){
		InputStream is = null;
		try{
			if(dataproperties==null){
				dataproperties = new Properties();
			}
			 is = Thread.currentThread().getContextClassLoader()
			        .getResourceAsStream(file);
			try {
				dataproperties.load(is);
			} catch (IOException e) {
				return dataproperties;
			}
			return dataproperties;
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
