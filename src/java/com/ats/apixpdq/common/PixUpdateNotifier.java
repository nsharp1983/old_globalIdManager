package com.ats.apixpdq.common;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.ats.apixpdq.api.IPixUpdateNotificationRequest;


/**
 * The consumer class to consume the {@link IPixUpdateNotificationRequest}
 * in the blocking queue. The producer of requests is the PixFeedHandler which
 * generates {@link IPixUpdateNotificationRequest}.
 * 
 * @author Wenzhi Li
 * @version 1.0, Feb 14, 2009
 * @see IPixUpdateNotificationRequest
 */
public class PixUpdateNotifier extends Thread {
	private static PixUpdateNotifier instance = null;

	/* The blocking queue to hold all the PixUpdateNotification requests */
	private final BlockingQueue<IPixUpdateNotificationRequest> queue =
	      new LinkedBlockingQueue<IPixUpdateNotificationRequest>();

	//private constructor
	private PixUpdateNotifier() {}
	
	/**
	 * Gets the singleton class. 
	 */
	public static synchronized PixUpdateNotifier getInstance() {
		if (instance == null) {
			instance = new PixUpdateNotifier();
			instance.start();
		}
		return instance;	
	}
	
	/**
	 * Accepts a {@link IPixUpdateNotificationRequest} from a producer.
	 * 
	 * @param pixUpdateNotificationRequest
	 */
	public void accept(IPixUpdateNotificationRequest pixUpdateNotificationRequest) {
		while (true) {
			try {  
		    	queue.put(pixUpdateNotificationRequest);
		    	return;
	        }catch (InterruptedException e) {
	        }
		}
	}

	@Override
    public void run() {
       while (true) {
    	   try {
	            execute(queue.take());
           }catch (InterruptedException e) {
           }
       }
   }

   /**
    * Executes a {@link IPixUpdateNotificationRequest}
    * 
    * @param pixUpdateNotificationRequest the request to execute
    */
   private void execute(final IPixUpdateNotificationRequest pixUpdateNotificationRequest) {
      new Thread(new Runnable() {
         public void run() {
            pixUpdateNotificationRequest.execute();
         }
      }).start();
   }

}
