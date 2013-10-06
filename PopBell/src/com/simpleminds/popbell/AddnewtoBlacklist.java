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

import android.content.ContentValues;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.content.Context;
import android.database.Cursor;

public class AddnewtoBlacklist extends ActionBarActivity {
	private ListView mListAppInfo;
	private AppBlackListDBhelper mHelper = null;
	private Cursor mCursor = null;
	
	Context c;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addnewto_blacklist);
		 // load list application
        mListAppInfo = (ListView)findViewById(R.id.listView1);
        mHelper = new AppBlackListDBhelper(this);
        mCursor = mHelper.getWritableDatabase().rawQuery("SELECT _ID, appname, pkgname FROM appblacklist ORDER BY pkgname", null);
        
        // create new adapter
        AppInfoAdapter adapter = new AppInfoAdapter(this, Utilities.getInstalledApplication(this), getPackageManager());
        // set adapter to list view
        mListAppInfo.setAdapter(adapter);
        // implement event when an item on list view is selected
        mListAppInfo.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int pos, long id) {
                // get the list adapter
                AppInfoAdapter appInfoAdapter = (AppInfoAdapter)parent.getAdapter();
                // get selected item on the list
                ApplicationInfo appInfo = (ApplicationInfo)appInfoAdapter.getItem(pos);
                // launch the selected application
                
                ApplicationInfo ai;
            	try {
            	    ai = getPackageManager().getApplicationInfo(appInfo.packageName, 0);
            	} catch (final NameNotFoundException e) {
            	    ai = null;
            	}
            	final String applicationName = (String) (ai != null ? getPackageManager().getApplicationLabel(ai) : "(unknown)");
            	
            	//put values to db
            	ContentValues values = new ContentValues();
            	values.put(AppBlackListDBhelper.APPNAME, applicationName.toString());
            	values.put(AppBlackListDBhelper.PKGNAME, appInfo.packageName.toString());
            	
            	mHelper.getWritableDatabase().insert("appblacklist", AppBlackListDBhelper.APPNAME, values);
            	mCursor.requery();
            	
            	finish();
       // Toast.makeText(c, "appname:" + applicationName + "packagename:" + appInfo.packageName + "added", Toast.LENGTH_LONG);
            }
        });
    }
}