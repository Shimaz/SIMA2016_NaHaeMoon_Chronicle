package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by shimaz on 2016-04-22.
 * shimaz.bsh@gmail.com
 */
public class TimelinePoemViewActivity extends Activity {


    private ApplicationManager apm;

    private TextView tvPoem;
    private Button btnClose;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            finish();

        }
    };


    private IntentFilter mFilter = new IntentFilter("shimaz.close");


    @Override
    protected void onCreate(Bundle sis){
        super.onCreate(sis);
        super.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        apm = (ApplicationManager)getApplicationContext();

        registerReceiver(mReceiver, mFilter);

        setContentView(R.layout.activity_timeline_detail_poemview);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/yun330.ttf");
        tvPoem = (TextView)findViewById(R.id.tv_poem);
        tvPoem.setTypeface(tf);


        btnClose = (Button)findViewById(R.id.btn_poem_close);
        btnClose.setBackgroundResource(R.drawable.timeline_btn_photo_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

                overridePendingTransition(R.anim.fade_in_short, R.anim.fade_out_short);
            }
        });



        try{
            InputStream is = getResources().openRawResource(R.raw.poem);
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String interviewText;
            StringBuilder sb = new StringBuilder("");
            while((interviewText = br.readLine()) != null){
                sb.append(interviewText);
                sb.append("\n");
            }

            is.close();

            tvPoem.setText(sb.toString());


        }catch (Exception e){
            e.printStackTrace();
        }


        ScrollView scrl = (ScrollView)findViewById(R.id.scrl_poem);
        scrl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                apm.resetTimer();

                return false;
            }
        });




    }





    @Override
    protected void onResume(){
        super.onResume();
        apm.setAnimating(false);
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

}
