package com.simpleminds.popbell;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

private static DatabaseHelper o_instance = null;
public static final String DATABASE_NAME = "BlackListDB";
public static final int DATABASE_VERSION = 1;
public SQLiteDatabase o_db = null;
public static final String USER_PASSWORD = "userpassword";

public DatabaseHelper() {
super(AndroidContext.getContext(), DATABASE_NAME, null,
DATABASE_VERSION);
o_db = getWritableDatabase();
}

public static DatabaseHelper getInstance() {
if (o_instance == null) {
o_instance = new DatabaseHelper();
}
return o_instance;
}

@Override
public void onCreate(SQLiteDatabase db) {
final String[] creatStatments = new String[] { "create table " + USER_PASSWORD + " (_id INTEGER PRIMARY KEY,username TEXT,password TEXT,selected INTEGER DEFAULT 0)" };

for (String sStmt : creatStatments) {
db.execSQL(sStmt);
}

}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS "+USER_PASSWORD);
onCreate(db);
}

public SQLiteDatabase getDb() {
return o_db;
}

}