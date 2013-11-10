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


import com.fima.cardsui.views.CardUI;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends ActionBarActivity {

    private CardUI mCardView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            //launch tutorial activity on firstrun
            boolean firstrun = getSharedPreferences("BOOT_PREF", MODE_PRIVATE).getBoolean("firstrun", true);
            
            if (firstrun) {
            	Intent guide = new Intent(MainActivity.this, Tutorial.class); 
    	    	startActivity(guide);
            	
                getSharedPreferences("BOOT_PREF", MODE_PRIVATE)
                    .edit()
                    .putBoolean("firstrun", false)
                    .commit();
            }
            
            // init CardView
            mCardView = (CardUI) findViewById(R.id.cardsview);
            mCardView.setSwipeable(false);
            
            MyPlayCard accessbility = new MyPlayCard(
                    getString(R.string.main_accessbility_title), 
                    getString(R.string.main_accessbility_desc), 
                    "#FF0000", "#FF0000", false, true);
            accessbility.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        	Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
                            startActivity(intent);
                        }
                    });
            mCardView.addCard(accessbility);
            
            MyPlayCard appblacklist = new MyPlayCard(
                    getString(R.string.main_appblacklist_title), 
                    getString(R.string.main_appblacklist_desc), 
                    "#FFA500", "#FFA500", false, true);
            appblacklist.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, AppBlackList.class);
                            startActivity(intent);
                        }
                    });
            mCardView.addCard(appblacklist);
            
            MyPlayCard drawerset = new MyPlayCard(
                    getString(R.string.main_drawersettings_title), 
                    getString(R.string.main_drawersettings_desc), 
                    "#16A085", "#16A085", false, true);
            drawerset.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, DrawerSettings.class);
                            startActivity(intent);
                        }
                    });
            mCardView.addCard(drawerset);
            
            MyPlayCard tutorial = new MyPlayCard(
                    getString(R.string.main_tutorial_title), 
                    getString(R.string.main_tutorial_desc), 
                    "#ADD8E6", "#ADD8E6", false, true);
            tutorial.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, Tutorial.class);
                            startActivity(intent);
                        }
                    });
            
            mCardView.addCard(tutorial);
            
            MyPlayCard appinfo = new MyPlayCard(
                    getString(R.string.main_appinfo_title), 
                    getString(R.string.main_appinfo_desc), 
                    "#33B5E5", "#33B5E5", false, true);
            appinfo.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, Appinfo.class);
                            startActivity(intent);
                        }
                    });
            
            mCardView.addCard(appinfo);

            // draw cards
            mCardView.refresh();
    }
    
}