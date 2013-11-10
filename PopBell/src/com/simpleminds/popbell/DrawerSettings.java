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

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;

public class DrawerSettings extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drawer_settings);
		// Load Preference Value
		SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
		CheckBox Toggle = (CheckBox)findViewById(R.id.toggle);
		Boolean Toggle_Boolean = pref.getBoolean("toggledata", false);
		Toggle.setChecked(Toggle_Boolean);
	
	}

	public void onStop(){ 
    	super.onStop();
    	SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE); // Save UI State
    	SharedPreferences.Editor editor = pref.edit(); // Load Editor
    	CheckBox check2 = (CheckBox)findViewById(R.id.toggle);
                // Input values
    	editor.putBoolean("toggledata", check2.isChecked());
    	editor.commit(); // Save calues
    }

}
