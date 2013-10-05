package com.simpleminds.popbell;

import wei.mark.standout.StandOutWindow;
import wei.mark.standout.constants.StandOutFlags;
import wei.mark.standout.ui.Window;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class TouchTrigger extends StandOutWindow {
    private ImageView mTouchDetector;       
    
    
    @Override
    public String getAppName() {
            return null;
    }
    
    @Override
public IBinder onBind(Intent arg0) { return null; }
@Override
    public void onCreate() {
super.onCreate();
           
    }
    @Override
    public int getAppIcon() {
            return android.R.drawable.ic_menu_close_clear_cancel;
    }

     
    @Override
    public void createAndAttachView(int id, FrameLayout frame) {
            // create a new layout from body.xml
            mTouchDetector = new ImageView(this);                                         
   mTouchDetector.setImageResource(R.drawable.detector_right);
   frame.addView(mTouchDetector);
    
   mTouchDetector.setOnTouchListener(new OnTouchListener(){
   
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(10);
                    Intent intent = new Intent(TouchTrigger.this, DrawerActivity.class);
                    startActivity(intent);
            }
            return true;
        }});
    }
    

    // the window will be centered
    @Override
    public StandOutLayoutParams getParams(int id, Window window) {
            return new StandOutLayoutParams(id, 10, 600,
                            StandOutLayoutParams.RIGHT, StandOutLayoutParams.TOP);
    }

    // move the window by dragging the view
    @Override
    public int getFlags(int id) {
            return super.getFlags(id) | StandOutFlags.FLAG_WINDOW_FOCUSABLE_DISABLE;
    }

}