/*
 *PopBell Application for Android
 *Copyright (C) 2013 SimpleMinds Team
 *
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package com.simpleminds.popbell;



import java.util.Timer;
import java.util.TimerTask;

import wei.mark.standout.StandOutWindow;

import android.R.string;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class NotiDetector extends AccessibilityService {
	private TimerTask mTask;
    private Timer mTimer;
    
	@Override
	public void onAccessibilityEvent(AccessibilityEvent event) {
	    System.out.println("onAccessibilityEvent");
	    if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
	        System.out.println("notification: " + event.getText());
	        
	        String pkgnameforfilter = event.getPackageName().toString();
	        String pkgitself = "com.simpleminds.popbell";
	        
	        //Filtering Package Name from Notification
	        if(pkgnameforfilter.equals(pkgitself))
	        {
	        	//Do Not send any data 
	        }
	        else{
	        
	        try {  
	        	
	        	
	        	//Close and Open Dialog Window
	        	StandOutWindow.closeAll(this, DialogWindow.class);
	        	StandOutWindow.closeAll(this, TouchTrigger.class);
	        	StandOutWindow.show(this, DialogWindow.class, StandOutWindow.DEFAULT_ID);
	        	StandOutWindow.show(this, TouchTrigger.class, StandOutWindow.DEFAULT_ID);
	        	// Create Bundle and put data
	        	Bundle dataBundle = new Bundle();
	        	// Get and Put Notification text 
	        	dataBundle.putString("sysnotitext", event.getText().toString());
	        	// Put App Name
	        	dataBundle.putString("pkgname", event.getPackageName().toString());
	        	dataBundle.putParcelable("ParcelableData", event.getParcelableData());
	        	//Send data to DialogWindow
	        	StandOutWindow.sendData(this, DialogWindow.class, StandOutWindow.DEFAULT_ID, 1, dataBundle, null, 0);
	        	//Close DialogWindow in a few seconds
	        	mTask = new TimerTask() {
		            @Override
		            public void run() {
		            		stopService(new Intent(NotiDetector.this, DialogWindow.class));
		            		stopService(new Intent(NotiDetector.this, TouchTrigger.class));
		            }
		        };
		        mTimer = new Timer();
		        mTimer.schedule(mTask, 5000);
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