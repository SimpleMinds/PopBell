package com.example.aapp;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private Intent intent;
	NotificationManager notificationManager;
	int i = 1;
	int notiid = 1;//노티피케이션 id
	int cancel = 0;
	long when = System.currentTimeMillis();
	TextView data;
	
	void Noti(int d){
		notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		Builder notiBuilder = new NotificationCompat.Builder(getApplicationContext());
		notiBuilder.setSmallIcon(R.drawable.ic_launcher);
        notiBuilder.setContentTitle("PopBell Plugin 테스트하기");
        notiBuilder.setContentText("안드로이드 테스트 " + i);
        notiBuilder.setWhen(when);
        Notification noti = notiBuilder.build();
		if(d == 0){
			i++;
	        notificationManager.notify("Popbell", notiid, noti);
		}else if(d == 1){
			notificationManager.cancel("Popbell", notiid);
			finish();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		cancel = MainActivity.this.getIntent().getIntExtra("Cancel", 0);
		
		
		if(cancel == 1){
			Noti(1);
        }else{
			//if(i == 1){
			//	Noti(0);
			//}
			Button btn  = (Button)findViewById(R.id.install);
			btn.setOnClickListener(new OnClickListener(){
				
				@Override
				public void onClick(View v) {
					//sendBroadcast to PopBell's PluginDataReceiver
					intent = new Intent("com.sompleminds.popbell.PLUGIN.PASSDATA");
					ComponentName compName = new ComponentName("com.example.bapp", "com.example.bapp.MainActivity");
					intent.setComponent(compName);
					intent.putExtra("AppName", getResources().getString(R.string.app_name)/*어플 이름 부분만 바꾸도록 허용*/);
					intent.putExtra("PackageName", getPackageName());
					intent.putExtra("Notiid", notiid);
					intent.putExtra("Title", "Sample Plugin Title");
					intent.putExtra("Desc", "Sample Plugin description");
					sendBroadcast(intent);
				}
			});
			
			Button add  = (Button)findViewById(R.id.notification);
			add.setOnClickListener(new OnClickListener(){
				
				@Override
				public void onClick(View v) {
			    	Noti(0);
			    }
			});
        }
	}
}