package kr.licubeclub.zionhs;

import kr.licubeclub.zionhs.R.string;

import com.fima.cardsui.objects.CardStack;
import com.fima.cardsui.views.CardUI;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;

public class DashBoard<Home> extends Activity {

    private CardUI mCardView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_dash_board);

            
            // init CardView
            mCardView = (CardUI) findViewById(R.id.cardsview);
            mCardView.setSwipeable(false);
            
            MyPlayCard noticescard = new MyPlayCard(
                    getString(R.string.dash_title_notices), 
                    getString(R.string.dash_desc_notices), 
                    "#FF0000", "#FF0000", false, true);
            noticescard.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        	Intent intent = new Intent(DashBoard.this, Meal.class);
                            startActivity(intent);
                        }
                    });
            mCardView.addCard(noticescard);
            
            MyPlayCard mealcard = new MyPlayCard(
                    getString(R.string.dash_title_meal), 
                    getString(R.string.dash_desc_meal), 
                    "#FFA500", "#FFA500", false, true);
            mealcard.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(DashBoard.this, Meal.class);
                            startActivity(intent);
                        }
                    });
            mCardView.addCard(mealcard);
            
            MyPlayCard schedcard = new MyPlayCard(
                    getString(R.string.dash_title_sched), 
                    getString(R.string.dash_desc_sched), 
                    "#00CD00", "#00CD00", false, true);
            schedcard.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        	Intent intent = new Intent(DashBoard.this, Meal.class);
                            startActivity(intent);
                        }
                    });
            mCardView.addCard(schedcard);
            

            
            MyPlayCard contactcard = new MyPlayCard(
                    getString(R.string.dash_title_contact), 
                    getString(R.string.dash_desc_contact), 
                    "#0000A0", "#0000A0", false, true);
            contactcard.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        	Intent intent = new Intent(DashBoard.this, Meal.class);
                            startActivity(intent);
                        }
                    });
            mCardView.addCard(contactcard);
            
            MyPlayCard notiparentcard = new MyPlayCard(
                    getString(R.string.dash_title_notiparent), 
                    getString(R.string.dash_desc_notiparent), 
                    "#808000", "#808000", false, true);
            notiparentcard.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        	Intent intent = new Intent(DashBoard.this, Meal.class);
                            startActivity(intent);
                        }
                    });
            mCardView.addCard(notiparentcard);
            
            MyPlayCard schoolinfocard = new MyPlayCard(
                    getString(R.string.dash_title_schoolinfo), 
                    getString(R.string.dash_desc_schoolinfo), 
                    "#25383C", "#25383C", false, true);
            schoolinfocard.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        	Intent intent = new Intent(DashBoard.this, Meal.class);
                            startActivity(intent);
                        }
                    });
            mCardView.addCard(schoolinfocard);
            
            MyPlayCard appinfocard = new MyPlayCard(
                    getString(R.string.dash_title_appinfo), 
                    getString(R.string.dash_desc_appinfo), 
                    "#3D2A26", "#3D2A26", false, true);
            appinfocard.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        	Intent intent = new Intent(DashBoard.this, Meal.class);
                            startActivity(intent);
                        }
                    });
            mCardView.addCard(appinfocard);
            
            
            // draw cards
            mCardView.refresh();
    }
    
    

        		 
        // TODO Action to perform
        }
