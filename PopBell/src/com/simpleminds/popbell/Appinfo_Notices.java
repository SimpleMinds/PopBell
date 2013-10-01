package com.simpleminds.popbell;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.fima.cardsui.views.CardUI;

public class Appinfo_Notices extends Activity {

	
    private CardUI mCardView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_appinfo_notices);

            mCardView = (CardUI) findViewById(R.id.cardsview);
            MyPlayCard readme = new MyPlayCard(
                    getString(R.string.readme),
                    "",
                    "#FFFFFF", "#000000", false, true);
            readme.setOnClickListener(new OnClickListener() {
            	@Override
                public void onClick(View v) {
                    Intent intent = new Intent(Appinfo_Notices.this,Doc_Readme.class);
                    startActivity(intent);
                }
            });
            mCardView.addCard(readme);
            
            MyPlayCard notices = new MyPlayCard(
                    getString(R.string.notices),
                    "",
                    "#FFFFFF", "#000000", false, true);
            notices.setOnClickListener(new OnClickListener() {
            	@Override
                public void onClick(View v) {
                    Intent intent = new Intent(Appinfo_Notices.this,Doc_Notices.class);
                    startActivity(intent);
                }
            });
            mCardView.addCard(notices);
            
            MyPlayCard copying = new MyPlayCard(
                    getString(R.string.copying),
                    "",
                    "#FFFFFF", "#000000", false, true);
            copying.setOnClickListener(new OnClickListener() {
            	@Override
                public void onClick(View v) {
                    Intent intent = new Intent(Appinfo_Notices.this,Doc_Copying.class);
                    startActivity(intent);
                }
            });
            mCardView.addCard(copying);
            
            MyPlayCard contributors = new MyPlayCard(
                    getString(R.string.contributors),
                    "",
                    "#FFFFFF", "#000000", false, true);
            contributors.setOnClickListener(new OnClickListener() {
            	@Override
                public void onClick(View v) {
                    Intent intent = new Intent(Appinfo_Notices.this,Doc_Contributors.class);
                    startActivity(intent);
                }
            });
            mCardView.addCard(contributors);
  
            // draw cards
            mCardView.refresh();
    }
    
    

        		 
        // TODO Action to perform
        }