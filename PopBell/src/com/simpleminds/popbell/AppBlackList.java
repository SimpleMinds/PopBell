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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class AppBlackList extends ActionBarActivity {
	private AppBlackListDBhelper mHelper = null;
	private Cursor mCursor = null;
	private static final int DELETE_ID = Menu.FIRST + 3;
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_app_black_list);

Button AddNew = (Button) findViewById(R.id.addnew);

AddNew.setOnClickListener(new OnClickListener() {
	@Override
	public void onClick(View v) {
		Log.d("PopBell", "DialogWindow Pinit Button");
		Intent addnew = new Intent(AppBlackList.this, AddnewtoBlacklist.class);
		startActivity(addnew);
	}
});

mHelper = new AppBlackListDBhelper(this);
mCursor = mHelper.getWritableDatabase().rawQuery("SELECT _ID, appname, pkgname FROM appblacklist ORDER BY appname", null);
@SuppressWarnings("deprecation")
ListAdapter adapter = new SimpleCursorAdapter(this, 
		R.layout.custom,
		mCursor,
		new String[] {AppBlackListDBhelper.APPNAME, AppBlackListDBhelper.PKGNAME},
		new int[] {R.id.bigtext, R.id.smalltext});
		ListView myList=(ListView)findViewById(android.R.id.list);
		myList.setAdapter(adapter);
		registerForContextMenu(myList);
}


@Override
public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
	menu.add(Menu.NONE, DELETE_ID, Menu.NONE, "Delete");
}
@Override
public boolean onContextItemSelected(MenuItem item) {
	switch (item.getItemId()) {
		case DELETE_ID:
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

			delete(info.id);
			return(true);
	}

	return(super.onOptionsItemSelected(item));
}
private void delete(final long rowId) {
	if (rowId > 0) {
		new AlertDialog.Builder(this)
			.setTitle(R.string.removecheck)
			.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					processDelete(rowId);
				}
			})
			.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
				// ignore, just dismiss
				}
			})
			.show();
	}
}

private void processDelete(long rowId) {
	String[] args = {String.valueOf(rowId)};
	
	mHelper.getWritableDatabase().delete("appblacklist", "_ID=?", args);
	mCursor.requery();
}
@Override
public void onDestroy() {
	super.onDestroy();
	
	mCursor.close();
	mHelper.close();
}
}