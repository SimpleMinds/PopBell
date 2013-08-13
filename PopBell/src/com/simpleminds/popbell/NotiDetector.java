//This Class Is Not Done Yet :P

package com.simpleminds.popbell;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityEvent;

public class NotiDetector extends AccessibilityService {

@Override
public void onAccessibilityEvent(AccessibilityEvent event) {
    if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
        //Do something, eg getting packagename
        final String packagename = String.valueOf(event.getPackageName());  
}
}

@Override
protected void onServiceConnected() {
}

@Override
public void onInterrupt() {
   
}
}