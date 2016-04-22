package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

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
    private ViewPager viewPager;
    private Button btnNext;
    private Button btnPrev;
    private Button btnClose;
    private TextView tvPageCount;

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


        viewPager = (ViewPager)findViewById(R.id.pager_timeline_detail);
        btnNext = (Button)findViewById(R.id.btn_timeline_detail_next);
        btnPrev = (Button)findViewById(R.id.btn_timeline_detail_prev);
        btnClose = (Button)findViewById(R.id.btn_timeline_detail_close);
        tvPageCount = (TextView)findViewById(R.id.tv_timeline_detail_page_counter);






        btnNext.setBackgroundResource(R.drawable.timeline_btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        btnPrev.setBackgroundResource(R.drawable.timeline_btn_prev);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        btnClose.setBackgroundResource(R.drawable.timeline_btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(RESULT_OK);
                finish();

            }
        });


    }



    @Override
    protected void onResume(){
        super.onResume();
        apm.setAnimating(false);

    }
}
