package com.simpleminds.popbell;

import java.util.Timer;
import java.util.TimerTask;

import wei.mark.standout.StandOutWindow;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

public class PluginDataReceiver extends BroadcastReceiver {
	private Cursor mCursor2 = null;
	private NotiListDBhelper mHelper2 = null;
	private TimerTask mTask;
    private Timer mTimer;
 
    @Override
    public void onReceive(final Context context, Intent intent) {
         Log.i("PluginDataReceiver", "Received Data from Plugin");
        String name = intent.getAction();
         
        if(name.equals("com.sompleminds.popbell.PLUGIN.PASSDATA")){
        	//Get data from broadcasted intent
        	String AppName = intent.getStringExtra("AppName");
        	String PackageName = intent.getStringExtra("PackageName");
        	String Notiid = intent.getStringExtra("Notiid");
        	String Title = intent.getStringExtra("Title");
        	String Desc = intent.getStringExtra("Desc");
        	Log.i("PluginDataReceiver", "Received Data :" + AppName +"/"+ PackageName +"/"+ Notiid +"/"+ Title +"/"+ Desc);
        	
        	//put values to db
        	mHelper2 = new NotiListDBhelper(context);
            mCursor2 = mHelper2.getWritableDatabase().rawQuery("SELECT _ID, title, desc FROM notilist ORDER BY title", null);
        	ContentValues values = new ContentValues();
        	values.put(NotiListDBhelper.TITLE, Title);
        	values.put(NotiListDBhelper.DESC, Desc);
        	
        	mHelper2.getWritableDatabase().insert("notilist", NotiListDBhelper.TITLE, values);
        	mCursor2.requery();
        	
        	//Close and Open Dialog Window
        	StandOutWindow.closeAll(context, DialogWindow.class);
        	StandOutWindow.closeAll(context, TouchTrigger.class);
        	StandOutWindow.show(context, DialogWindow.class, StandOutWindow.DEFAULT_ID);
        	StandOutWindow.show(context, TouchTrigger.class, StandOutWindow.DEFAULT_ID);

        	// Create Bundle and put data
        	Bundle dataBundle = new Bundle();
        	dataBundle.putString("AppName", AppName);
        	dataBundle.putString("Title", Title);
        	dataBundle.putString("Notiid", Notiid);
        	dataBundle.putString("Desc", Desc);
        	dataBundle.putString("PackageName", PackageName);
        	//Send data to DialogWindow
        	StandOutWindow.sendData(context, DialogWindow.class, StandOutWindow.DEFAULT_ID, 2, dataBundle, null, 0);
        	//Close All Window in a few Seconds
        	mTask = new TimerTask() {
	            @Override
	            public void run() {
	            	StandOutWindow.closeAll(context, DialogWindow.class);
	            	StandOutWindow.closeAll(context, TouchTrigger.class);
	            }
	        };
	        mTimer = new Timer();
	        mTimer.schedule(mTask, 5000);
    }
    }
}
