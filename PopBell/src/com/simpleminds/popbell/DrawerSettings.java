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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CheckBox;
import android.widget.Spinner;
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
		CheckBox Useleft = (CheckBox)findViewById(R.id.useleft);
		Boolean Toggle_Boolean = pref.getBoolean("toggledata", false);
		Boolean Toggle_Useleft = pref.getBoolean("useleft", false);
		Toggle.setChecked(Toggle_Boolean);
		Useleft.setChecked(Toggle_Useleft);
	
	
	

	  Spinner spinner = (Spinner) findViewById(R.id.spinner1);
	  spinner.setOnItemSelectedListener(new OnItemSelectedListener(){
	  public void onItemSelected(AdapterView<?> parent, View view, 
	            int pos, long id) {
		  SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE); // Save UI State
	    	SharedPreferences.Editor editor = pref.edit(); 
		  if(pos == 0){
			  editor.putString("touchtrigger_align", "top");
			  editor.commit();
			  Log.d("touchtrigger_align", "top");
		  }
		  else if(pos ==1){
			  editor.putString("touchtrigger_align", "middle");
			  editor.commit();
			  Log.d("touchtrigger_align", "middle");
		  }
		  else{
			  editor.putString("touchtrigger_align", "bottom");
			  editor.commit();
			  Log.d("touchtrigger_align", "bottom");
		  }
	        // An item was selected. You can retrieve the selected item using
	        // parent.getItemAtPosition(pos)
	    }

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	  });}
	  

	public void onStop(){ 
    	super.onStop();
    	SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE); // Save UI State
    	SharedPreferences.Editor editor = pref.edit(); // Load Editor
    	CheckBox check1 = (CheckBox)findViewById(R.id.toggle);
    	CheckBox check2 = (CheckBox)findViewById(R.id.useleft);
                // Input values
    	editor.putBoolean("toggledata", check1.isChecked());
    	editor.putBoolean("useleft", check2.isChecked());
    	editor.commit(); // Save calues
    }

}
