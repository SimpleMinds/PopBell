package com.simpleminds.popbell;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;
import com.simpleminds.popbell.NotiDetector.Constants;

public class MainActivity extends Activity {
 
    private static final String TAG = "ToastOrNotificationTestActivity";
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         
        final IntentFilter mIntentFilter = new IntentFilter(Constants.ACTION_CATCH_NOTIFICATION); 
        mIntentFilter.addAction(Constants.ACTION_CATCH_TOAST);
        registerReceiver(toastOrNotificationCatcherReceiver, mIntentFilter);
        Log.v(TAG, "Receiver registered.");
    }
             
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(toastOrNotificationCatcherReceiver);
    }
         
    private final BroadcastReceiver toastOrNotificationCatcherReceiver = new BroadcastReceiver() {
         
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v(TAG, "Received message"); 
            Log.v(TAG, "intent.getAction() :: " + intent.getAction());
            Log.v(TAG, "intent.getStringExtra(Constants.EXTRA_PACKAGE) :: " + intent.getStringExtra(Constants.EXTRA_PACKAGE));
            Log.v(TAG, "intent.getStringExtra(Constants.EXTRA_MESSAGE) :: " + intent.getStringExtra(Constants.EXTRA_MESSAGE));          
        }
    };
}