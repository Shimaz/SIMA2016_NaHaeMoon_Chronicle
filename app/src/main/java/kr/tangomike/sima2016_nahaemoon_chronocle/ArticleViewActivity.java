package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.view.ViewGroup.LayoutParams;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by shimaz on 2016-04-16.
 */
public class ArticleViewActivity extends Activity {

    private ApplicationManager apm;

    private int articleNumber;
    private int essayNumber;
    private RelativeLayout rlMain;
    private RelativeLayout rlTopTitle;
    private RelativeLayout rlBottomMenu;
    private RelativeLayout rlTranslated;
    private ScrollView scrlArticleTranslated;
    private Button btnNext;
    private Button btnPrev;
    private Button btnOpenClose;
    private Button btnBack;
    private ImageView ivNewspaper;
    private ImageView ivArticleTitle;
    private ImageView ivArticleTransTitle;

    private TextView tvPageCount;
    private TextView tvTranslatedText;

    private boolean isTranslationOpen;
    private boolean isMenuOpen;


    private enum btnKind {
        BTN_NEXT,
        BTN_PREV
    }


    @Override
    protected void onCreate(Bundle sis){
        super.onCreate(sis);
        super.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        apm = (ApplicationManager)getApplicationContext();



        setContentView(R.layout.activity_article_view);
        rlMain = (RelativeLayout)findViewById(R.id.rl_article_view);
        rlTopTitle = (RelativeLayout)findViewById(R.id.rl_article_top_title);
        rlBottomMenu = (RelativeLayout)findViewById(R.id.rl_article_bottom_menu);
        scrlArticleTranslated = (ScrollView)findViewById(R.id.scrl_article_translated);
        tvPageCount = (TextView)findViewById(R.id.tv_article_page_number);
        ivNewspaper = (ImageView)findViewById(R.id.iv_article_zoom);
        ivArticleTitle = (ImageView)findViewById(R.id.iv_article_view_title);
        ivArticleTransTitle = (ImageView)findViewById(R.id.iv_article_view_trans_title);
        tvTranslatedText = (TextView)findViewById(R.id.tv_article_translated);
        rlTranslated = (RelativeLayout)findViewById(R.id.rl_article_translated);


        Intent intent = this.getIntent();
        articleNumber = intent.getIntExtra("article number", 0);
        essayNumber = articleNumber/10;

        if(essayNumber != 0) { // if essayNumber is not 1, it's essay.

            articleNumber = articleNumber % 10;

        }

        isTranslationOpen = false;

        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        btnBack = new Button(this);
        btnBack.setLayoutParams(lp);
        btnBack.setBackgroundResource(R.drawable.article_btn_back);
        btnBack.setX(644);
        btnBack.setY(0);
        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!apm.isAnimating()){
                    apm.setAnimating(true);
                    Intent intent = new Intent(ArticleViewActivity.this, MediaArticleListActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.left_in, R.anim.right_out);
                    finish();
                }



            }
        });


        rlTopTitle.addView(btnBack);

        OnClickListener ocl = new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!apm.isAnimating()){

                    apm.setAnimating(true);

                    Intent intent = new Intent(ArticleViewActivity.this, ArticleViewActivity.class);
                    if(v.getTag() == btnKind.BTN_NEXT){

                        articleNumber++;

                        intent.putExtra("article number", articleNumber + (essayNumber * 10));
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in, R.anim.left_out);

                    }else{

                        articleNumber--;
                        intent.putExtra("article number", articleNumber + (essayNumber * 10));
                        startActivity(intent);
                        overridePendingTransition(R.anim.left_in, R.anim.right_out);

                    }

                    finish();

                }




            }
        };


        btnNext = new Button(this);
        btnNext.setLayoutParams(lp);
        btnNext.setBackgroundResource(R.drawable.article_btn_next);
        btnNext.setX(233);
        btnNext.setY(0);
        btnNext.setOnClickListener(ocl);
        btnNext.setTag(btnKind.BTN_NEXT);

        // attach next button if it's not a single article
        if(essayNumber != 0 && articleNumber < apm.getArticleEssayCount(essayNumber - 1)){
            rlBottomMenu.addView(btnNext);
        }


        btnPrev = new Button(this);
        btnPrev.setLayoutParams(lp);
        btnPrev.setBackgroundResource(R.drawable.article_btn_prev);
        btnPrev.setX(436);
        btnPrev.setY(0);
        btnPrev.setOnClickListener(ocl);
        btnPrev.setTag(btnKind.BTN_PREV);

        // attach previous button if it's not a single article.
        if(essayNumber != 0 && articleNumber != 1) {
            rlBottomMenu.addView(btnPrev);
        }




        btnOpenClose = new Button(this);
        btnOpenClose.setLayoutParams(lp);
        btnOpenClose.setBackgroundResource(R.drawable.article_btn_open);
        btnOpenClose.setX(582);
        btnOpenClose.setY(0);
        btnOpenClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!apm.isAnimating()){


                    apm.setAnimating(true);


                    if(isTranslationOpen){
                        // TODO: close translation


                    }else{
                        // TODO: open translation

                    }

                }




            }
        });

        rlBottomMenu.addView(btnOpenClose);


        // Settings for Content Data
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/jabml.ttf");
        tvPageCount.setTextSize(18.0f);
        tvPageCount.setTypeface(tf);

        rlTranslated.setBackgroundResource(R.drawable.article_img_bg);
        tvTranslatedText.setTypeface(tf);
        tvTranslatedText.setTextSize(18.0f);
        tvTranslatedText.setLineSpacing(0, 1.25f);




        if(essayNumber == 0){
            tvPageCount.setText("1/1");
            ivNewspaper.setImageResource(apm.getArticleInterview(articleNumber - 1));
            ivArticleTitle.setImageResource(apm.getArticleInterviewTitle(articleNumber - 1, false));

            // TODO: Load article text

            try{


            }catch (Exception e){
                e.printStackTrace();
            }



        }else{
            String tmp;
            tmp = String.format("%d/%d", articleNumber, apm.getArticleEssayCount(essayNumber - 1));

            tvPageCount.setText(tmp);
            ivNewspaper.setImageResource(apm.getArticleEssay(essayNumber - 1, articleNumber - 1));
            ivArticleTitle.setImageResource(apm.getArticleEssayTitle(essayNumber - 1, articleNumber - 1 , false));

        }


        PhotoViewAttacher pva = new PhotoViewAttacher(ivNewspaper);

        pva.update();


        // TODO: Add scrollview contents



    }

    private void openMenu(){

        // TODO: open top and bottom menu when touch on zoomable area
        if(!isMenuOpen){

        }

    }


    private void closeMenu(){

        // TODO: close top and bottom menu on timer setting
        if(isMenuOpen){

        }

    }




    @Override
    protected void onResume(){
        super.onResume();

        apm.setAnimating(false);
    }
}
