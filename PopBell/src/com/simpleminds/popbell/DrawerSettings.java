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
