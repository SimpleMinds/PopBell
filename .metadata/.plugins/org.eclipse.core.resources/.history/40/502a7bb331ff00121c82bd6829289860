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

public class DashBoard extends Activity {

    private CardUI mCardView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_dash_board);

            
            // init CardView
            mCardView = (CardUI) findViewById(R.id.cardsview);
            mCardView.setSwipeable(false);

            

            mCardView.addCard(new MyPlayCard(
                    getString(R.string.dash_title_notices), 
                    getString(R.string.dash_desc_notices), 
                    "#FF0000", "#FF0000", false, true));
            mCardView.addCard(new MyPlayCard(
                    getString(R.string.dash_title_meal), 
                    getString(R.string.dash_desc_meal), 
                    "#FFA500", "#FFA500", false, true));
            mCardView.addCard(new MyPlayCard(
                    getString(R.string.dash_title_sched), 
                    getString(R.string.dash_desc_sched), 
                    "#00CD00", "#00CD00", false, true));
            mCardView.addCard(new MyPlayCard(
                    getString(R.string.dash_title_contact), 
                    getString(R.string.dash_desc_contact), 
                    "#0000A0", "#0000A0", false, true));
            mCardView.addCard(new MyPlayCard(
                    getString(R.string.dash_title_notiparent), 
                    getString(R.string.dash_desc_notiparent), 
                    "#808000", "#808000", false, true));
            mCardView.addCard(new MyPlayCard(
                    getString(R.string.dash_title_schoolinfo), 
                    getString(R.string.dash_desc_schoolinfo), 
                    "#25383C", "#25383C", false, true));
            mCardView.addCard(new MyPlayCard(
                    getString(R.string.dash_title_appinfo), 
                    getString(R.string.dash_desc_appinfo), 
                    "#3D2A26", "#3D2A26", false, true));
            // draw cards
            mCardView.refresh();
    }
}