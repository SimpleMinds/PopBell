package com.simpleminds.popbell;

import android.app.ListActivity;
import android.os.Bundle;
import com.simpleminds.popbell.AppBlackListDBhelper;

import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor;

public class AppBlackList extends ListActivity{
	private AppBlackListDBhelper mHelper = null;
	private Cursor mCursor = null;
	
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_app_black_list);

mHelper = new AppBlackListDBhelper(this);
mCursor = mHelper.getWritableDatabase().rawQuery("SELECT _ID, appname, pkgname FROM appblacklist ORDER BY pkgname", null);
@SuppressWarnings("deprecation")
ListAdapter adapter = new SimpleCursorAdapter(this, 
		R.layout.custom,
		mCursor,
		new String[] {AppBlackListDBhelper.APPNAME, AppBlackListDBhelper.PKGNAME},
		new int[] {R.id.appnametext, R.id.pkgnametext});
setListAdapter(adapter);
registerForContextMenu(getListView());
}
@Override
public void onDestroy() {
	super.onDestroy();
	
	mCursor.close();
	mHelper.close();
}
}