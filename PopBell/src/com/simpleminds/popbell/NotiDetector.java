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
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import wei.mark.standout.StandOutWindow;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class NotiDetector extends AccessibilityService {
	private TimerTask mTask;
    private Timer mTimer;
	private AppBlackListDBhelper mHelper = null;
	private Cursor mCursor = null;
	private Cursor mCursor2 = null;
	private NotiListDBhelper mHelper2 = null;
    
	@Override
	public void onAccessibilityEvent(AccessibilityEvent event) {
		//Load BlackList
		mHelper = new AppBlackListDBhelper(this);
		mCursor = mHelper.getWritableDatabase().rawQuery("SELECT _ID, pkgname FROM appblacklist ORDER BY pkgname", null);
		
		
		List<String> array = new ArrayList<String>();
		while(mCursor.moveToNext()){
		    String uname = mCursor.getString(mCursor.getColumnIndex("pkgname"));
		    array.add(uname);
		}

		Log.d("DBVALUES", array.toString());
	    System.out.println("onAccessibilityEvent");
	    if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
	        System.out.println("notification: " + event.getText());
	        
	        String pkgnameforfilter = event.getPackageName().toString();
	        String pkgitself = "com.simpleminds.popbell";
	        
	        //Filtering Package Name from Notification
	        if(pkgnameforfilter.equals(pkgitself)){
	        	//Do Nothing
	        	Log.d("SYSNOTIDETECTOR", "BLOCKED : PKG_ITSELF");
	        }
	        else if(array.toString().contains(pkgnameforfilter)){
	        	//Do Nothing
	        	Log.d("SYSNOTIDETECTOR", "BLOCKED : PKG_BLACKLISTED");
	        }
	        else{
	        try {  
	        	//Get app name
	        	final PackageManager pm = getApplicationContext().getPackageManager();
	        	ApplicationInfo ai;
	        	try {
	        	    ai = pm.getApplicationInfo( (String) event.getPackageName().toString(), 0);
	        	} catch (final NameNotFoundException e) {
	        	    ai = null;
	        	}
	        	final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
	        	
	        	//put values to db
	        	mHelper2 = new NotiListDBhelper(this);
	            mCursor2 = mHelper2.getWritableDatabase().rawQuery("SELECT _ID, title, desc FROM notilist ORDER BY title", null);
            	ContentValues values = new ContentValues();
            	values.put(NotiListDBhelper.TITLE, applicationName.toString());
            	values.put(NotiListDBhelper.DESC, event.getText().toString());
            	
            	mHelper2.getWritableDatabase().insert("notilist", NotiListDBhelper.TITLE, values);
            	mCursor2.requery();
	        	
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