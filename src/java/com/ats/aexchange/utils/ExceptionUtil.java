package com.ats.aexchange.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * The utility class for Exceptions.
 * 
 * @author <a href="mailto:wenzhi.li@misys.com">Wenzhi Li</a>
 */
public class ExceptionUtil {

	/**
	 * Strips away the exception class path preceding the exception message,
	 * so the message length would be shortened.
	 * 
	 * @param exceptionMessage
	 * @return a shortened exception message without preceding class path
	 */
	public static String strip(String exceptionMessage) {
		int index = exceptionMessage.lastIndexOf("Exception:");
		if (index == -1) 
			return exceptionMessage;
		else
			return exceptionMessage.substring(index+10);
	}

	/**
	 * Gets the details of this exception.
	 * 
	 * @param e the exception where to get the details
	 * @return the String detail
	 */
	public static String getExceptionDetails(Exception e) {
		if (e == null) 
			return "";
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		e.printStackTrace(ps);

		return "Exception thrown: " + e.getClass().getName() + "\n" + 
			e.getMessage() + "\n" + new String(baos.toByteArray());
	}
}
