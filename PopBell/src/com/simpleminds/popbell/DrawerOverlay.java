package com.simpleminds.popbell;

<<<<<<< HEAD
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import wei.mark.standout.StandOutWindow;
import wei.mark.standout.StandOutWindow.StandOutLayoutParams;
import wei.mark.standout.constants.StandOutFlags;
=======
import android.widget.FrameLayout;
import wei.mark.standout.StandOutWindow;
>>>>>>> 223d6447277a6f8ba03ba5d9ed5d7e29dc054f70
import wei.mark.standout.ui.Window;

public class DrawerOverlay extends StandOutWindow {

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
<<<<<<< HEAD
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.draweroverlay_layout, frame, true);
=======
		
>>>>>>> 223d6447277a6f8ba03ba5d9ed5d7e29dc054f70
	}

	@Override
	public StandOutLayoutParams getParams(int id, Window window) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		WindowManager win = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = win.getDefaultDisplay();
		//int width = display.getWidth();
		int height = display.getHeight();

		return new StandOutLayoutParams(id, 400,
				height, StandOutLayoutParams.RIGHT,
				StandOutLayoutParams.TOP + 20);
	}
	@Override
	public int getFlags(int id) {
		return super.getFlags(id) | StandOutFlags.FLAG_WINDOW_FOCUSABLE_DISABLE;
=======
		return null;
>>>>>>> 223d6447277a6f8ba03ba5d9ed5d7e29dc054f70
	}

}
