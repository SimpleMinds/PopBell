//This Class Is Not Done Yet :P

package com.simpleminds.popbell;



import wei.mark.standout.StandOutWindow;

import android.R.string;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class NotiDetector extends AccessibilityService {
	
    
	@Override
	public void onAccessibilityEvent(AccessibilityEvent event) {
	    System.out.println("onAccessibilityEvent");
	    if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
	        System.out.println("notification: " + event.getText());
	        
	        String pkgnameforfilter = event.getPackageName().toString();
	        String pkgitself = "com.simpleminds.popbell";
	        
	        //Filtering Package Name from Notification
	        if(pkgnameforfilter == pkgitself)
	        {
	        	//Do Not send any data 
	        }
	        else{
	        
	        try {  
	        	// Close SimpleWindow
	        	StandOutWindow.closeAll(this, SimpleWindow.class);
	        	// Open SimpleWindow
	        	StandOutWindow.show(this, SimpleWindow.class, StandOutWindow.DEFAULT_ID);
	        
	        	// Get App Name
	        
	        	// Create Bundle and put data
	        	Bundle dataBundle = new Bundle();
	        	// Get and Put Notification text 
	        	dataBundle.putString("sysnotidata", event.getText().toString());
	        	// Put App Name
	        	dataBundle.putString("pkgname", event.getPackageName().toString());
	        	//Send data to SimpleWindow
	        	StandOutWindow.sendData(this, SimpleWindow.class, StandOutWindow.DEFAULT_ID, 1, dataBundle, null, 0);
		        } catch (Exception e) {
		            Log.e("SYSNOTIDETECTOR", "ERROR IN CODE:"+e.toString());
		        }
	        }
	    }
	    
	    }
	
	@Override
	protected void onServiceConnected() {
	    System.out.println("onServiceConnected");
	    AccessibilityServiceInfo info = new AccessibilityServiceInfo();
	    info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
	    info.notificationTimeout = 100;
	    info.feedbackType = AccessibilityEvent.TYPES_ALL_MASK;
	    setServiceInfo(info);
	}

	@Override
	public void onInterrupt() {
	    System.out.println("onInterrupt");
	}
	}