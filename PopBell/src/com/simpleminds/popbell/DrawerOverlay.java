/*
 *PopBell Application for Android
 *Copyright (C) 2013 SimpleMinds Team
 *
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package com.simpleminds.popbell;

import java.util.ArrayList;
import java.util.List;

import wei.mark.standout.StandOutWindow;
import wei.mark.standout.constants.StandOutFlags;
import wei.mark.standout.ui.Window;
import android.content.Context;
import android.database.Cursor;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DrawerOverlay extends StandOutWindow {
	private NotiListDBhelper mHelper = null;
	private Cursor mCursor = null;
	private static final int DELETE_ID = Menu.FIRST + 3;
	ListView lview;
	ListAdapter_TitleAndDesc lviewAdapter;
	
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

		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.draweroverlay_layout, frame, true);

		
		mHelper = new NotiListDBhelper(this);
		mCursor = mHelper.getWritableDatabase().rawQuery("SELECT _ID, title, desc, devntcode FROM notilist ORDER BY pkgname", null);
		//title array
		List<String> titlearray = new ArrayList<String>();
		while(mCursor.moveToNext()){
		    String uname = mCursor.getString(mCursor.getColumnIndex("title"));
		    titlearray.add(uname);
		}
		//desc array
		List<String> descarray = new ArrayList<String>();
		while(mCursor.moveToNext()){
		    String uname = mCursor.getString(mCursor.getColumnIndex("desc"));
		    descarray.add(uname);
		     
		}
	}

	

	@Override
	public StandOutLayoutParams getParams(int id, Window window) {
		// TODO Auto-generated method stub

		WindowManager win = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = win.getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();

		return new StandOutLayoutParams(id, width - 40,
				height, StandOutLayoutParams.RIGHT,
				StandOutLayoutParams.TOP);
	}
	/*
	@Override
	public int getFlags(int id) {
		return super.getFlags(id) | StandOutFlags.FLAG_WINDOW_FOCUSABLE_DISABLE;


	}*/
	@Override
	public boolean onFocusChange(int id, Window window, boolean focus) {
		if (focus) {
			
		} else {
			stopSelf();
		}
		return false;
	}
}
