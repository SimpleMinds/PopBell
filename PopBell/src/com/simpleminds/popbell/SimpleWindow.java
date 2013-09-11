package com.simpleminds.popbell;

import java.util.Timer;
import java.util.TimerTask;

import wei.mark.standout.StandOutWindow;
import wei.mark.standout.constants.StandOutFlags;
import wei.mark.standout.ui.Window;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
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
		
			String changedText = data.getString("sysnotidata");
			TextView status = (TextView) window.findViewById(R.id.textView2);
			status.setText(changedText);
			
			String Appnametext = data.getString("appnamedata");
			TextView appname = (TextView) window.findViewById(R.id.textView1);
			appname.setText(Appnametext);
			
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
