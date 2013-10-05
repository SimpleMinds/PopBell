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

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.fima.cardsui.views.CardUI;

public class Appinfo extends Activity {

	
    private CardUI mCardView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_appinfo);
            
           
                String app_ver = null;
				try {
					app_ver = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
         
            
            // init CardView
            mCardView = (CardUI) findViewById(R.id.cardsview);
            mCardView.setSwipeable(false);
            
            MyPlayCard version = new MyPlayCard(
                    getString(R.string.appinfo_version_title), 
                    app_ver, 
                    "#FFFFFF", "#000000", false, true);
          
            mCardView.addCard(version);
            
            MyPlayCard github = new MyPlayCard(
                    getString(R.string.appinfo_github_title), 
                    getString(R.string.appinfo_github_desc),
                    "#FFFFFF", "#000000", false, true);
            
            github.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                	Intent src = new Intent(Intent.ACTION_VIEW);
                    src.setData(Uri.parse("http://github.com/SimpleMinds/PopBell"));
                    startActivity(src);
                }
            });
            mCardView.addCard(github);
            
            MyPlayCard notices = new MyPlayCard(
                    getString(R.string.appinfo_notices_title),
                    getString(R.string.appinfo_notices_desc),
                    "#FFFFFF", "#000000", false, true);
            notices.setOnClickListener(new OnClickListener() {
            	@Override
                public void onClick(View v) {
                    Intent intent = new Intent(Appinfo.this, Appinfo_Notices.class);
                    startActivity(intent);
                }
            });
            mCardView.addCard(notices);
  
            // draw cards
            mCardView.refresh();
    }
    
    

        		 
        // TODO Action to perform
        }