package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.view.ViewGroup.LayoutParams;

import java.util.ArrayList;

/**
 * Created by shimaz on 2016-04-16.
 */
public class TimelineDetailActivity extends Activity {


    // TODO: refer to leeumshop-app view pager adaptor

    private ApplicationManager apm;
    private RelativeLayout rlMain;
    private int yearCount;
    private ArrayList<DetailPageData> dpd;

    @Override
    protected void onCreate(Bundle sis){
        super.onCreate(sis);
        super.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        apm = (ApplicationManager)getApplicationContext();

        Intent intent = getIntent();
        yearCount = intent.getExtras().getInt("position");

        dpd = apm.getDetailPageBundle(yearCount);



        setContentView(R.layout.activity_timeline_detail);
        rlMain = (RelativeLayout)findViewById(R.id.rl_timeline_detail);

        Button btnTmp = new Button(this);
        btnTmp.setLayoutParams(new LayoutParams(300, 300));
        btnTmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(RESULT_OK);
                finish();

            }
        });


        rlMain.addView(btnTmp);

    }



    @Override
    protected void onResume(){
        super.onResume();
        apm.setAnimating(false);

    }
}
