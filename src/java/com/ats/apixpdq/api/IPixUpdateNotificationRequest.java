package com.ats.apixpdq.api;


/**
 * The request interface for PIX Update Notification. This
 * is the data type placed in the blocking queue used in PIX Update
 * Notification consumer, namely {@link PixUpdateNotifier}.
 * 
 * @author Wenzhi Li
 * @version 1.0, Feb 14, 2009
 * @see PixUpdateNotifier
 */
public interface IPixUpdateNotificationRequest {
    
	/**
	 * Executes the PIX update notification of this request, which
	 * actually sends a PIX update notification to subscribed 
	 * PIX Consumers
	 */
	public void execute();
	
}
