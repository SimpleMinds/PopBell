package com.simpleminds.popbell;

import java.util.Timer;
import java.util.TimerTask;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import wei.mark.standout.StandOutWindow;
import wei.mark.standout.constants.StandOutFlags;
import wei.mark.standout.ui.Window;


public class NotiListOverlay extends StandOutWindow  {

	@Override
	public String getAppName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAppIcon() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void createAndAttachView(int id, FrameLayout frame) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.simple, frame, true);
	}

	@Override
	public int getFlags(int id) {
		return StandOutFlags.FLAG_DECORATION_SYSTEM | StandOutFlags.FLAG_BODY_MOVE_ENABLE | StandOutFlags.FLAG_WINDOW_EDGE_LIMITS_ENABLE | StandOutFlags.FLAG_WINDOW_BRING_TO_FRONT_ON_TAP | StandOutFlags.FLAG_WINDOW_BRING_TO_FRONT_ON_TOUCH | StandOutFlags.FLAG_WINDOW_PINCH_RESIZE_ENABLE;
	}
	
	@Override
	public StandOutLayoutParams getParams(int id, Window window) {
		// TODO Auto-generated method stub
		return null;
	}
	//Receive data from NotiDetector
	@Override
	public void onReceiveData(int id, int requestCode, Bundle data,
			Class<? extends StandOutWindow> fromCls, int fromId) 
	{
		Window window = getWindow(id);
		
		   
			//Get Received String
			String PkgName = data.getString("pkgname");
			String NotiText = data.getString("sysnotidata");
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
		
		
	}
}
