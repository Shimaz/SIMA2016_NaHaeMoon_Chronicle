package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

/**
 * Created by shima on 2016-04-16.
 */
public class TimelineActivity extends Activity {

    private ApplicationManager apm;
    private RelativeLayout rlMain;
    private RelativeLayout rlMainUI;
    private ScrollView scrlTimeline;

    @Override
    protected void onCreate(Bundle sis){
        super.onCreate(sis);

        setContentView(R.layout.activity_timeline);

        apm = (ApplicationManager)getApplicationContext();
        rlMain = (RelativeLayout)findViewById(R.id.rl_timeline);
        rlMainUI = (RelativeLayout)findViewById(R.id.rl_timeline_ui);
        scrlTimeline = (ScrollView)findViewById(R.id.scrl_timeline_main);


        Button btnHome = new Button(this);
        btnHome.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        btnHome.setText("Home");
        btnHome.setX(340);
        btnHome.setY(10);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimelineActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.top_in, R.anim.bottom_out);
                finish();

            }
        });

        rlMainUI.addView(btnHome);





    }


    @Override
    protected void onResume(){
        super.onResume();
        apm.setAnimating(false);
    }



}



