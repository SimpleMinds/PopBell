package com.simpleminds.popbell;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import wei.mark.standout.StandOutWindow;
import wei.mark.standout.StandOutWindow.StandOutLayoutParams;
import wei.mark.standout.constants.StandOutFlags;
import wei.mark.standout.ui.Window;


public class NotiListOverlay extends StandOutWindow  {
	
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
	
		ImageView appicon = (ImageView) view.findViewById(R.id.appicon);
		
		//OnClick for appicon
		CompositeOnClickListener groupListener = new CompositeOnClickListener();
		appicon.setOnClickListener(groupListener);
		groupListener.addOnClickListener(new View.OnClickListener(){
			   @Override
			   public void onClick(View v){
			      /**** Custom implementation ****/
				   StandOutWindow.closeAll(NotiListOverlay.this, DrawerOverlay.class);
					StandOutWindow.show(NotiListOverlay.this, DrawerOverlay.class, StandOutWindow.DEFAULT_ID);
			   }
			});
		
		//OnLongClick for appicon
		CompositeOnLongClickListener LongListener = new CompositeOnLongClickListener();
		appicon.setOnLongClickListener(LongListener);
		LongListener.addOnLongClickListener(new View.OnLongClickListener(){
			   @Override
			   public boolean onLongClick(View v){
			      /**** Custom implementation ****/
				   stopSelf();
					return false;
			   }
			});
		/*
		
		appicon.setOnClickListener(new OnClickListener() {
		@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				StandOutWindow.closeAll(NotiListOverlay.this, DrawerOverlay.class);
				StandOutWindow.show(NotiListOverlay.this, DrawerOverlay.class, StandOutWindow.DEFAULT_ID);
			}
		});
		*/
			
		}
	

	@Override
	public int getFlags(int id) {
		return StandOutFlags.FLAG_BODY_MOVE_ENABLE |
				StandOutFlags.FLAG_WINDOW_EDGE_LIMITS_ENABLE | 
				StandOutFlags.FLAG_WINDOW_BRING_TO_FRONT_ON_TAP | 
				StandOutFlags.FLAG_WINDOW_BRING_TO_FRONT_ON_TOUCH | 
				StandOutFlags.FLAG_WINDOW_PINCH_RESIZE_ENABLE;
	}
	
	@Override
	public StandOutLayoutParams getParams(int id, Window window) {
		// TODO Auto-generated method stub
		
		return new StandOutLayoutParams(id, 150, 150,
				StandOutLayoutParams.TOP, StandOutLayoutParams.LEFT);
		
		
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
