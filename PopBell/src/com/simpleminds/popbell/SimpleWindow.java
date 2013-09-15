package com.simpleminds.popbell;

import java.util.Timer;
import java.util.TimerTask;

import wei.mark.standout.StandOutWindow;
import wei.mark.standout.constants.StandOutFlags;
import wei.mark.standout.ui.Window;
import android.content.SharedPreferences;
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

public class SimpleWindow extends StandOutWindow {
	
	String[] array;
	private TimerTask mTask;
    private Timer mTimer;
    
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
		View view = inflater.inflate(R.layout.simple, frame, true);
		
	
		/*Button back = (Button)view.findViewById(R.id.button1);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {         
					public void run() {                 
						new Instrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_VOLUME_UP);
					}   
				}).start();
			}
		});*/
		
		
	}
	
	
	// the window will be centered
	@Override
	public StandOutLayoutParams getParams(int id, Window window) {
		/*return new StandOutLayoutParams(id, 200, 200,
				StandOutLayoutParams.CENTER, StandOutLayoutParams.CENTER);*/
		SharedPreferences pref = getSharedPreferences("display", 0);
        array = pref.getString("xy", "0/0").split("/");
		return new StandOutLayoutParams(id, Integer.parseInt(array[0])*7/8, Integer.parseInt(array[1])/8,
				  StandOutLayoutParams.CENTER, StandOutLayoutParams.TOP + 20);
	}

	// move the window by dragging the view
	@Override
	public int getFlags(int id) {
		return super.getFlags(id) /*| StandOutFlags.FLAG_BODY_MOVE_ENABLE*/ | StandOutFlags.FLAG_WINDOW_FOCUSABLE_DISABLE;
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
			
			mTask = new TimerTask() {
	            @Override
	            public void run() {
	            	stopSelf();
	            }
	        };
	         
	        mTimer = new Timer();
	         
	        mTimer.schedule(mTask, 5000);
		
	}



	
}
