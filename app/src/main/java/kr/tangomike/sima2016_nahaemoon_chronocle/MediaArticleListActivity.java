package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
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
        apm = (ApplicationManager)getApplicationContext();

        setContentView(R.layout.activity_article_list);
        rlMain = (RelativeLayout)findViewById(R.id.rl_article_list);

        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        OnClickListener ocl = new OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int)v.getTag();

                Intent intent = new Intent(MediaArticleListActivity.this, ArticleViewActivity.class);
                intent.putExtra("article number", tag);
                startActivity(intent);
                overridePendingTransition(R.anim.left_out, R.anim.right_in);
                finish();


            }
        };

        Button btn01 = new Button(this);
        btn01.setLayoutParams(lp);
        btn01.setText("Article 01");
        btn01.setX(100);
        btn01.setY(20);
        btn01.setOnClickListener(ocl);
        // TODO: set tag as article number
        btn01.setTag(1);


        rlMain.addView(btn01);

        Button btn02 = new Button(this);
        btn02.setLayoutParams(lp);
        btn02.setText("Article 02");
        btn02.setX(100);
        btn02.setY(140);
        btn02.setOnClickListener(ocl);
        // TODO: set tag as article number
        btn02.setTag(2);

        rlMain.addView(btn02);


        Button btn03 = new Button(this);
        btn03.setLayoutParams(lp);
        btn03.setText("Artcle 03");
        btn03.setX(100);
        btn03.setY(260);
        btn03.setOnClickListener(ocl);
        // TODO: set tag as article number
        btn03.setTag(3);

        rlMain.addView(btn03);

        Button btn04 = new Button(this);
        btn04.setLayoutParams(lp);
        btn04.setText("Article 04");
        btn04.setX(100);
        btn04.setY(380);
        btn04.setOnClickListener(ocl);
        // TODO: set tag as article number
        btn04.setTag(4);

        rlMain.addView(btn04);


        Button btn05 = new Button(this);
        btn05.setLayoutParams(lp);
        btn05.setText("Article 05");
        btn05.setX(100);
        btn05.setY(500);
        btn05.setOnClickListener(ocl);
        // TODO: set tag as article number
        btn04.setTag(5);

        rlMain.addView(btn05);


        Button btn06 = new Button(this);
        btn06.setLayoutParams(lp);
        btn06.setText("Article 06");
        btn06.setX(100);
        btn06.setY(620);
        btn06.setOnClickListener(ocl);
        // TODO: set tag as article number
        btn06.setTag(6);

        rlMain.addView(btn06);


        Button btn07 = new Button(this);
        btn07.setLayoutParams(lp);
        btn07.setText("Article 07");
        btn07.setX(100);
        btn07.setY(740);
        btn07.setOnClickListener(ocl);
        // TODO: set tag as article number;
        btn07.setTag(7);

        rlMain.addView(btn07);


        Button btn08 = new Button(this);
        btn08.setLayoutParams(lp);
        btn08.setText("Article 08");
        btn08.setX(100);
        btn08.setY(860);
        btn08.setOnClickListener(ocl);
        // TODO: set tag as article number;
        btn08.setTag(8);

        rlMain.addView(btn08);






        Button btnHome = new Button(this);
        btnHome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaArticleListActivity.this, MediaTopActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                finish();
            }
        });


        btnHome.setLayoutParams(lp);
        btnHome.setText("Back");
        btnHome.setX(100);
        btnHome.setY(980);

        rlMain.addView(btnHome);




    }



    @Override
    protected void onResume(){
        super.onResume();
        apm.setAnimating(false);
    }

}
