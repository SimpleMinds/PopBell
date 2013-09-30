package com.simpleminds.popbell;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.support.v4.view.PagerAdapter;

public class Tutorial extends Activity {
	private ViewPager mPager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tutorial);
		

        
        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(new PagerAdapterClass(getApplicationContext()));
	}
	   private void setCurrentInflateItem(int type){
	        if(type==0){
	            mPager.setCurrentItem(0);
	        }else if(type==1){
	            mPager.setCurrentItem(1);
	        }
	        else if(type==2){
	            mPager.setCurrentItem(2);
	        }
	        else if(type==3){
	            mPager.setCurrentItem(3);
	        }
	        else if(type==4){
	            mPager.setCurrentItem(4);
	        }
	        else{
	            mPager.setCurrentItem(5);
	        }
	    }
	/**
     * PagerAdapter 
     */
    private class PagerAdapterClass extends PagerAdapter{
         
        private LayoutInflater mInflater;
 
        public PagerAdapterClass(Context c){
            super();
            mInflater = LayoutInflater.from(c);
        }
         
        @Override
        public int getCount() {
            return 3;
        }
        private View.OnClickListener mPagerListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        };
        @Override
        public Object instantiateItem(View pager, int position) {
            View v = null;
            if(position==0){
                v = mInflater.inflate(R.layout.tutorial_1, null);
            }
            else if(position==1){
                v = mInflater.inflate(R.layout.tutorial_2, null);}
            else if(position==2){
                v = mInflater.inflate(R.layout.tutorial_3, null);}
            else if(position==3){
                v = mInflater.inflate(R.layout.tutorial_4, null);}
            else if(position==4){
                v = mInflater.inflate(R.layout.tutorial_5, null);}
            else{
                v = mInflater.inflate(R.layout.tutorial_6, null);
                v.findViewById(R.id.close).setOnClickListener(mPagerListener);
            }
             
            ((ViewPager)pager).addView(v, 0);
             
            return v; 
        }
 
        @Override
        public void destroyItem(View pager, int position, Object view) {    
            ((ViewPager)pager).removeView((View)view);
        }
         
        @Override
        public boolean isViewFromObject(View pager, Object obj) {
            return pager == obj; 
        }
 
        @Override public void restoreState(Parcelable arg0, ClassLoader arg1) {}
        @Override public Parcelable saveState() { return null; }
        @Override public void startUpdate(View arg0) {}
        @Override public void finishUpdate(View arg0) {}
    }
     
}