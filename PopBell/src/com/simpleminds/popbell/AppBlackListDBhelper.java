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
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppBlackListDBhelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "appblacklist.db";
	static final String APPNAME = "appname";
	static final String PKGNAME = "pkgname";
	
	public AppBlackListDBhelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE appblacklist (_id INTEGER PRIMARY KEY AUTOINCREMENT, appname TEXT, pkgname TEXT);");
		
		ContentValues cv = new ContentValues();
		
		cv.put(APPNAME, "PopBell");
		cv.put(PKGNAME, "com.simpleminds.popbell");
		db.insert("appblacklist", PKGNAME, cv);

	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS appblacklist");
		onCreate(db);
	}

}