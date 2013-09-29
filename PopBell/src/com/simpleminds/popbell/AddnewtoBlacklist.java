package com.simpleminds.popbell;

import android.app.Activity;
import android.content.ContentValues;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Context;

public class AddnewtoBlacklist extends Activity {
	private ListView mListAppInfo;
	private AppBlackListDBhelper mHelper = null;
	Context c;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addnewto_blacklist);
		 // load list application
        mListAppInfo = (ListView)findViewById(R.id.listView1);
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
            	
            	
            	ContentValues values = new ContentValues();
            	values.put(AppBlackListDBhelper.APPNAME, applicationName);
            	values.put(AppBlackListDBhelper.PKGNAME, appInfo.packageName);
            	
            	mHelper.getWritableDatabase().insert("appblacklist", AppBlackListDBhelper.APPNAME, values);

        Toast.makeText(c, "appname:" + applicationName + "packagename:" + appInfo.packageName + "added", Toast.LENGTH_LONG);
            }
        });
    }
}