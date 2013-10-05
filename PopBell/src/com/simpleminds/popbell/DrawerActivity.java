package com.simpleminds.popbell;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class DrawerActivity extends Activity {
	private NotiListDBhelper mHelper = null;
	private Cursor mCursor = null;
	private static final int DELETE_ID = Menu.FIRST + 3;
	ListView lview;
	ListAdapter_TitleAndDesc lviewAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drawer);
		
		//load DB
		mHelper = new NotiListDBhelper(this);
		mCursor = mHelper.getWritableDatabase().rawQuery("SELECT _ID, title, desc, devntcode FROM notilist ORDER BY pkgname", null);
		//title array
		List<String> titlearray = new ArrayList<String>();
		while(mCursor.moveToNext()){
		    String title = mCursor.getString(mCursor.getColumnIndex("title"));
		    titlearray.add(title);
		}
		//desc array
		List<String> descarray = new ArrayList<String>();
		while(mCursor.moveToNext()){
		    String desc = mCursor.getString(mCursor.getColumnIndex("desc"));
		    descarray.add(desc);
		    
		    String item[] = {titlearray.toString()};
		    String subitem[] = {descarray.toString()};
		    
		    lview = (ListView) findViewById(R.id.listView1);   
	        lviewAdapter = new ListAdapter_TitleAndDesc(this, item, subitem);

	        System.out.println("adapter => "+lviewAdapter.getCount());

	        lview.setAdapter(lviewAdapter);
	}

	

}
}
