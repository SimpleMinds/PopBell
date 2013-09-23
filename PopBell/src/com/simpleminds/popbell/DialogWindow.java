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
import wei.mark.standout.constants.StandOutFlags;
import wei.mark.standout.ui.Window;
import android.app.Notification;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.simpleminds.popbell.PinedDialogWindow;

public class DialogWindow extends StandOutWindow {
	
	String[] array;
	
    public boolean onShow(int id, Window window) {
    	Log.d("PopBell", "DialogWindow Show");
		return false;
	}
    
    @Override
	public int getAppIcon() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getAppName() {
		return null;
	}

	@Override
	public void createAndAttachView(int id, FrameLayout frame) {
		
		// create a new layout from body.xml
		LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.dialog, frame, true);

		 ImageView PinBtn = (ImageView) view.findViewById(R.id.pinit);

		 PinBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("PopBell", "DialogWindow Pinit Button");
				StandOutWindow.closeAll(DialogWindow.this, PinedDialogWindow.class);
	        	StandOutWindow.show(DialogWindow.this, PinedDialogWindow.class, StandOutWindow.DEFAULT_ID);
				stopSelf();
			}
		});

	}

	// the window will be centered
	@Override
	public StandOutLayoutParams getParams(int id, Window window) {
		WindowManager win = (WindowManager) getSystemService(Context.WINDOW_SERVICE); 
	    Display display = win.getDefaultDisplay();
	    int width = display.getWidth();
	    int height = display.getHeight();

		return new StandOutLayoutParams(id, width*7/8, StandOutLayoutParams.WRAP_CONTENT,
				  StandOutLayoutParams.CENTER, StandOutLayoutParams.TOP + 20);
	}

	// move the window by draggin(g the view
	@Override
	public int getFlags(int id) {
		return super.getFlags(id) | StandOutFlags.FLAG_WINDOW_FOCUS_INDICATOR_DISABLE;
	}

	//Receive data from NotiDetector
	@Override
	public void onReceiveData(int id, int requestCode, Bundle data,
			Class<? extends StandOutWindow> fromCls, int fromId) 
	{		
		Window window = getWindow(id);

			//Get Received String
			String PkgName = data.getString("pkgname");
			String NotiText = data.getString("sysnotitext");
			
			
			
			TextView AppNameField = (TextView) window.findViewById(R.id.appnametext);
			TextView NotiField = (TextView) window.findViewById(R.id.notitext);
			ImageView AppIconField = (ImageView) window.findViewById(R.id.appicon);
			
			// Get App Name and App Icon
			final PackageManager pm = getApplicationContext().getPackageManager();
        	ApplicationInfo ai;
        	try {
        	    ai = pm.getApplicationInfo( (String) PkgName, 0);
        	} catch (final NameNotFoundException e) {
        	    ai = null;
        	}
        	final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
        	Drawable appicon = pm.getApplicationIcon(ai);
        	
        	AppNameField.setText(applicationName);
        	NotiField.setText(NotiText);
        	AppIconField.setImageDrawable(appicon);
        	
        	final Notification n = (Notification) data.getParcelable("ParcelableData");
        	AppIconField.setOnClickListener(new OnClickListener() {
   			@Override
   			public void onClick(View v) {
   				try {
					n.contentIntent.send();
				} catch (CanceledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   			}
   		});
        	
        	SharedPreferences prefs = getSharedPreferences(getPackageName() + "_preferences", MODE_PRIVATE);
        	SharedPreferences.Editor ed = prefs.edit();
        	ed.putString("string_pkgname", PkgName);
        	ed.putString("string_notitext", NotiText);
        	ed.commit();
		

	}
	public boolean onCloseAll() {
		Log.d("PopBell", "CloseAll DialodWindow");
		return false;
	}
}
