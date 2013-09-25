package com.simpleminds.popbell;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import com.simpleminds.popbell.CustomCursorAdapter;

public class AppBlackList extends ListActivity{

Cursor cursor;
SQLiteDatabase dh;
CustomCursorAdapter myCursorAdapter;
ContentValues values;
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
AndroidContext.setContext(this);

dh=DatabaseHelper.getInstance().getDb();
values = new ContentValues();
// Inserting some data in SQLite to populate list view

createListView();
}

private void createListView() {
setContentView(R.layout.activity_app_black_list);

cursor=dh.query(DatabaseHelper.USER_PASSWORD, new String[]{"_id","username","selected"}, null, null, null, null, "firstname asc");

startManagingCursor(cursor);
myCursorAdapter= new CustomCursorAdapter(this,cursor);
this.getListView().setAdapter(myCursorAdapter);
}

private void insertData(String firstName ,String password){
if(values!= null){
values.clear();
}
values.put("username",firstName);
values.put("password", password);
dh.insert(DatabaseHelper.USER_PASSWORD, null, values);
}

private void clickHandler(View view){

if(view.getId() == R.id.checkbox){
cursor.requery(); /* to get the updated values from sqlite
on changing the check of checkbox*/
}
}
}