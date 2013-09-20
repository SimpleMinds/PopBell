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

import wei.mark.standout.StandOutWindow;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class StandOutExampleActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		int deviceWidth = displayMetrics.widthPixels;
		int deviceHeight =displayMetrics.heightPixels;
	    SharedPreferences pref = getSharedPreferences("display", 0);
	    SharedPreferences.Editor editor = pref.edit();
	    editor.putString("xy", deviceWidth+"/"+deviceHeight);
	    editor.commit();
		StandOutWindow.closeAll(this, DialogWindow.class);
		// show a MultiWindow, SimpleWindow

		StandOutWindow.show(this, DialogWindow.class, StandOutWindow.DEFAULT_ID);
		//StandOutWindow.show(this, MultiWindow.class, StandOutWindow.DEFAULT_ID);
		//StandOutWindow.show(this, WidgetsWindow.class,
		//		StandOutWindow.DEFAULT_ID);
		
		// show a MostBasicWindow. It is commented out because it does not
		// support closing.

		//StandOutWindow.show(this, StandOutMostBasicWindow.class, StandOutWindow.DEFAULT_ID);
		
		finish();
	}
}
