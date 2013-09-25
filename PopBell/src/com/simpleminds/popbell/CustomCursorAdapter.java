package com.simpleminds.popbell;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomCursorAdapter extends CursorAdapter{

SQLiteDatabase dh=DatabaseHelper.getInstance().getDb();
private LayoutInflater mInflater;
private Context mContext;
Cursor cursor;
public CustomCursorAdapter(Context context, Cursor c) {
super(context, c);
// TODO Auto-generated constructor stub
mInflater = LayoutInflater.from(context);
mContext = context;
cursor=c;
}

@Override
public void bindView(View view, Context context, final Cursor cursor) {
// TODO Auto-generated method stub
ViewHolder holder=(ViewHolder)view.getTag();

holder.setTextView((TextView)view.findViewById(R.id.textView1));
holder.setCheckBox((CheckBox)view.findViewById(R.id.checkBox1));
CheckBox cb=holder.getCheckBox();

holder.getTextView().setText( cursor.getString(cursor.getColumnIndex("username")));

cb.setTag(new Integer(cursor.getPosition()));

CompoundButton.OnCheckedChangeListener checkedChange= new CompoundButton.OnCheckedChangeListener() {

public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
// TODO Auto-generated method stub

ContentValues contentValues=new ContentValues();

Integer currentPosition = (Integer)buttonView.getTag();

String currentPositionString=Double.toString(currentPosition);
if(cursor.moveToPosition(currentPosition))
{

String rowID=cursor.getString(cursor.getColumnIndex("_id"));
if(isChecked){
contentValues.put("selected", "1");
dh.update(DatabaseHelper.USER_PASSWORD, contentValues, "_id=?", new String[]{rowID});

}else if(!isChecked){
contentValues.put("selected", "0");
dh.update(DatabaseHelper.USER_PASSWORD, contentValues, "_id=?", new String[]{rowID});
}

}

}
};
cb.setOnCheckedChangeListener(checkedChange);

if(cursor.getString(cursor.getColumnIndex("selected")).compareTo("1")==0)
{
cb.setChecked(true);
}
else
{
cb.setChecked(false);
}
}

@Override
public View newView(Context context, Cursor cursor, ViewGroup parent) {
// TODO Auto-generated method stub
ViewHolder holder;
View convertView = mInflater.inflate(R.layout.custom, parent,false);
holder = new ViewHolder(convertView);
convertView.setTag(holder);
return convertView;
}

}