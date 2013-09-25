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

import wei.mark.standout.StandOutWindow;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    private CardUI mCardView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            
            // init CardView
            mCardView = (CardUI) findViewById(R.id.cardsview);
            mCardView.setSwipeable(false);
            
            MyPlayCard noticescard = new MyPlayCard(
                    getString(R.string.main_accessbility_title), 
                    getString(R.string.main_accessbility_desc), 
                    "#FF0000", "#FF0000", false, true);
            noticescard.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        	Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
                            startActivity(intent);
                        }
                    });
            mCardView.addCard(noticescard);
            
            MyPlayCard mealcard = new MyPlayCard(
                    getString(R.string.main_appblacklist_title), 
                    getString(R.string.main_appblacklist_desc), 
                    "#FFA500", "#FFA500", false, true);
            mealcard.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, AppBlackList.class);
                            startActivity(intent);
                        }
                    });
            mCardView.addCard(mealcard);

            // draw cards
            mCardView.refresh();
    }
    
    

        		 
        // TODO Action to perform
        }