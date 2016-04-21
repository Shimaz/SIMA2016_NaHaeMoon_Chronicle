package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle sis){
        super.onCreate(sis);
        super.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        apm = (ApplicationManager)getApplicationContext();

        setContentView(R.layout.activity_article_list);
        rlMain = (RelativeLayout)findViewById(R.id.rl_article_list);
        rlMain.setBackgroundResource(R.drawable.media_articlelist_img_bg);

        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        OnClickListener ocl = new OnClickListener() {
            @Override
            public void onClick(View v) {

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
        btn01.setX(155);
        btn01.setY(282);
        btn01.setOnClickListener(ocl);
        // TODO: set tag as article number
        btn01.setTag(1);


        rlMain.addView(btn01);

        Button btn02 = new Button(this);
        btn02.setLayoutParams(lp);
        btn02.setBackgroundResource(R.drawable.media_articlelist_btn_02);
        btn02.setX(155);
        btn02.setY(366);
        btn02.setOnClickListener(ocl);
        // TODO: set tag as article number
        btn02.setTag(2);

        rlMain.addView(btn02);


        Button btn03 = new Button(this);
        btn03.setLayoutParams(lp);
        btn03.setBackgroundResource(R.drawable.media_articlelist_btn_03);
        btn03.setX(155);
        btn03.setY(419);
        btn03.setOnClickListener(ocl);
        // TODO: set tag as article number
        btn03.setTag(3);

        rlMain.addView(btn03);

        Button btn04 = new Button(this);
        btn04.setLayoutParams(lp);
        btn04.setBackgroundResource(R.drawable.media_articlelist_btn_04);
        btn04.setX(155);
        btn04.setY(478);
        btn04.setOnClickListener(ocl);
        // TODO: set tag as article number
        btn04.setTag(4);

        rlMain.addView(btn04);


        Button btn05 = new Button(this);
        btn05.setLayoutParams(lp);
        btn05.setBackgroundResource(R.drawable.media_articlelist_btn_05);
        btn05.setX(155);
        btn05.setY(529);
        btn05.setOnClickListener(ocl);
        // TODO: set tag as article number
        btn05.setTag(5);

        rlMain.addView(btn05);


        Button btn06 = new Button(this);
        btn06.setLayoutParams(lp);
        btn06.setBackgroundResource(R.drawable.media_articlelist_btn_06);
        btn06.setX(155);
        btn06.setY(731);
        btn06.setOnClickListener(ocl);
        // TODO: set tag as article number
        btn06.setTag(11);

        rlMain.addView(btn06);


        Button btn07 = new Button(this);
        btn07.setLayoutParams(lp);
        btn07.setBackgroundResource(R.drawable.media_articlelist_btn_07);
        btn07.setX(155);
        btn07.setY(804);
        btn07.setOnClickListener(ocl);
        // TODO: set tag as article number;
        btn07.setTag(21);

        rlMain.addView(btn07);


        Button btn08 = new Button(this);
        btn08.setLayoutParams(lp);
        btn08.setBackgroundResource(R.drawable.media_articlelist_btn_08);
        btn08.setX(155);
        btn08.setY(864);
        btn08.setOnClickListener(ocl);
        // TODO: set tag as article number;
        btn08.setTag(31);

        rlMain.addView(btn08);






        Button btnHome = new Button(this);
        btnHome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
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
        btnHome.setX(56);
        btnHome.setY(948);

        rlMain.addView(btnHome);




    }



    @Override
    protected void onResume(){
        super.onResume();
        apm.setAnimating(false);
    }

}
