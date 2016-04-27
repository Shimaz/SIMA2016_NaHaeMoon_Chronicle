package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by shimaz on 2016-04-16.
 */
public class MediaArticleListActivity extends Activity {


    private RelativeLayout rlMain;
    private ApplicationManager apm;

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


        registerReceiver(mReceiver, mFilter);

        apm = (ApplicationManager)getApplicationContext();

        setContentView(R.layout.activity_article_list);
        rlMain = (RelativeLayout)findViewById(R.id.rl_article_list);
        rlMain.setBackgroundResource(R.drawable.media_articlelist_img_bg);

        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        OnClickListener ocl = new OnClickListener() {
            @Override
            public void onClick(View v) {

                apm.resetTimer();

                if(!apm.isAnimating()){

                    apm.setAnimating(true);

                    int tag = (int)v.getTag();

                    Intent intent = new Intent(MediaArticleListActivity.this, ArticleViewActivity.class);
                    intent.putExtra("article number", tag);
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    finish();
                }



            }
        };

        Button btn01 = new Button(this);
        btn01.setLayoutParams(lp);
        btn01.setBackgroundResource(R.drawable.media_articlelist_btn_01);
        btn01.setX(127);
        btn01.setY(236);
        btn01.setOnClickListener(ocl);
        btn01.setTag(11);


        rlMain.addView(btn01);

        Button btn02 = new Button(this);
        btn02.setLayoutParams(lp);
        btn02.setBackgroundResource(R.drawable.media_articlelist_btn_02);
        btn02.setX(127);
        btn02.setY(310);
        btn02.setOnClickListener(ocl);
        btn02.setTag(21);

        rlMain.addView(btn02);


        Button btn03 = new Button(this);
        btn03.setLayoutParams(lp);
        btn03.setBackgroundResource(R.drawable.media_articlelist_btn_03);
        btn03.setX(127);
        btn03.setY(372);
        btn03.setOnClickListener(ocl);
        btn03.setTag(31);

        rlMain.addView(btn03);

        Button btn04 = new Button(this);
        btn04.setLayoutParams(lp);
        btn04.setBackgroundResource(R.drawable.media_articlelist_btn_04);
        btn04.setX(127);
        btn04.setY(577);
        btn04.setOnClickListener(ocl);
        btn04.setTag(1);

        rlMain.addView(btn04);


        Button btn05 = new Button(this);
        btn05.setLayoutParams(lp);
        btn05.setBackgroundResource(R.drawable.media_articlelist_btn_05);
        btn05.setX(127);
        btn05.setY(633);
        btn05.setOnClickListener(ocl);
        btn05.setTag(2);

        rlMain.addView(btn05);


        Button btn06 = new Button(this);
        btn06.setLayoutParams(lp);
        btn06.setBackgroundResource(R.drawable.media_articlelist_btn_06);
        btn06.setX(127);
        btn06.setY(689);
        btn06.setOnClickListener(ocl);
        btn06.setTag(3);

        rlMain.addView(btn06);


        Button btn07 = new Button(this);
        btn07.setLayoutParams(lp);
        btn07.setBackgroundResource(R.drawable.media_articlelist_btn_07);
        btn07.setX(127);
        btn07.setY(745);
        btn07.setOnClickListener(ocl);
        btn07.setTag(4);

        rlMain.addView(btn07);


        Button btn08 = new Button(this);
        btn08.setLayoutParams(lp);
        btn08.setBackgroundResource(R.drawable.media_articlelist_btn_08);
        btn08.setX(127);
        btn08.setY(801);
        btn08.setOnClickListener(ocl);
        btn08.setTag(5);

        rlMain.addView(btn08);


//        Button btn09 = new Button(this);
//        btn09.setLayoutParams(lp);
//        btn09.setBackgroundResource(R.drawable.media_articlelist_btn_09);
//        btn09.setX(127);
//        btn09.setY(874);
//        btn09.setOnClickListener(ocl);
//        btn09.setTag(6);
//
//        rlMain.addView(btn09);




        Button btnHome = new Button(this);
        btnHome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                apm.resetTimer();

                if(!apm.isAnimating()){

                    apm.setAnimating(true);

                    Intent intent = new Intent(MediaArticleListActivity.this, MediaTopActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.left_in, R.anim.right_out);
                    finish();
                }

            }
        });


        btnHome.setLayoutParams(lp);
        btnHome.setBackgroundResource(R.drawable.media_common_btn_back);
        btnHome.setX(28);
        btnHome.setY(949);

        rlMain.addView(btnHome);




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
