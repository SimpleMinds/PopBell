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
                    "#f2a400", "#9d36d0", false, true));
          
            // draw cards
            mCardView.refresh();
    }
}