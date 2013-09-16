package com.simpleminds.popbell;

import java.util.ArrayList;
import java.util.List;

import wei.mark.standout.StandOutWindow;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class LittleOverlay extends Service {
	private ImageView mPopupView;							
	private WindowManager.LayoutParams mParams;		
	private WindowManager mWindowManager;			
	
	private float START_X, START_Y;							
	private int PREV_X, PREV_Y;								
	private int MAX_X = -1, MAX_Y = -1;					
	
	//Class for using multiple OnClickListener
	private class CompositeOnClickListener implements View.OnClickListener{
	    List<View.OnClickListener> listeners;

	    public CompositeOnClickListener(){
	        listeners = new ArrayList<View.OnClickListener>();
	    }

	    public void addOnClickListener(View.OnClickListener listener){
	        listeners.add(listener);
	    }

	    @Override
	    public void onClick(View v){
	       for(View.OnClickListener listener : listeners){
	          listener.onClick(v);
	       }
	    }
	}
	
	//Class for using multiple OnLongClickListener
		private class CompositeOnLongClickListener implements View.OnLongClickListener{
		    List<OnLongClickListener> listeners;

		    public CompositeOnLongClickListener(){
		        listeners = new ArrayList<View.OnLongClickListener>();
		    }

		    public void addOnLongClickListener(OnLongClickListener listener){
		        listeners.add(listener);
		    }

		    @Override
		    public boolean onLongClick(View v){
		       for(View.OnLongClickListener listener : listeners){
		          listener.onLongClick(v);
		       }
			return false;
		    }
		}
		
	
	public void onReceive(Context context, Intent intent) {
	    
	    Bundle b = intent.getExtras();
	    String pkgname = b.getString("pkgname");
	    String sysnotitext = b.getString("sysnotitext");
	    
	    final PackageManager pm = getApplicationContext().getPackageManager();
    	ApplicationInfo ai;
    	try {
    	    ai = pm.getApplicationInfo( (String) pkgname, 0);
    	} catch (final NameNotFoundException e) {
    	    ai = null;
    	}
    	final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
    	Drawable appicon = pm.getApplicationIcon(ai);
    	
	}
	
	private OnTouchListener mViewTouchListener = new OnTouchListener() {
		@Override public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:				
					if(MAX_X == -1)
						setMaxPosition();
					START_X = event.getRawX();					
					START_Y = event.getRawY();					
					PREV_X = mParams.x;							
					PREV_Y = mParams.y;							
					break;
				case MotionEvent.ACTION_MOVE:
					int x = (int)(event.getRawX() - START_X);	
					int y = (int)(event.getRawY() - START_Y);	
					
					
					mParams.x = PREV_X + x;
					mParams.y = PREV_Y + y;
					
					optimizePosition();
					mWindowManager.updateViewLayout(mPopupView, mParams);	
					break;
			}
			
			return false;
		}
	};
	
	@Override
	public IBinder onBind(Intent arg0) { return null; }
	
	@Override
	public void onCreate() {
		super.onCreate();

		mPopupView = new ImageView(this);																
		mPopupView.setImageResource(R.drawable.ic_launcher);	
		mPopupView.setBackgroundColor(Color.argb(127, 0, 255, 255));								
		//OnClick for appicon
		CompositeOnClickListener groupListener = new CompositeOnClickListener();
		mPopupView.setOnClickListener(groupListener);
		groupListener.addOnClickListener(new View.OnClickListener(){
			   @Override
			   public void onClick(View v){
			      /**** Custom implementation ****/
				   System.out.println("onClick");
				   StandOutWindow.closeAll(LittleOverlay.this, DrawerOverlay.class);
					StandOutWindow.show(LittleOverlay.this, DrawerOverlay.class, StandOutWindow.DEFAULT_ID);
			   }
			});
		
		//OnLongClick for appicon
		CompositeOnLongClickListener LongListener = new CompositeOnLongClickListener();
		mPopupView.setOnLongClickListener(LongListener);
		LongListener.addOnLongClickListener(new View.OnLongClickListener(){
			   @Override
			   public boolean onLongClick(View v){
			      /**** Custom implementation ****/
				   System.out.println("onLongClick");
				   stopSelf();
					return false;
			   }
			});
		mPopupView.setOnTouchListener(mViewTouchListener);										
		
		
		mParams = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                        | WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);			
		mParams.gravity = Gravity.LEFT | Gravity.TOP;						
		
		mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);	
		mWindowManager.addView(mPopupView, mParams);		
		
	}
	
	
	private void setMaxPosition() {
	DisplayMetrics matrix = new DisplayMetrics();
	mWindowManager.getDefaultDisplay().getMetrics(matrix);
	MAX_X = matrix.widthPixels - mPopupView.getWidth();
	MAX_Y = matrix.heightPixels - mPopupView.getHeight();
	}
	

	private void optimizePosition() {

		if(mParams.x > MAX_X) mParams.x = MAX_X;
		if(mParams.y > MAX_Y) mParams.y = MAX_Y;
		if(mParams.x < 0) mParams.x = 0;
		if(mParams.y < 0) mParams.y = 0;
	}
	
	@Override
	public void onDestroy() {
		if(mWindowManager != null) {		
			if(mPopupView != null) mWindowManager.removeView(mPopupView);
		}
		super.onDestroy();
	}
}