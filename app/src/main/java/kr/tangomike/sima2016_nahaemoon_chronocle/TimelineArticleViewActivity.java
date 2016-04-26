package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by shimaz on 2016-04-22.
 * shimaz.bsh@gmail.com
 */
public class TimelineArticleViewActivity extends Activity {

    private ApplicationManager apm;

    private int articleNumber;
    private int position;
    private int year;
    private int count;
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

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            finish();

        }
    };


    private IntentFilter mFilter = new IntentFilter("shimaz.close");

    private enum btnKind {
        BTN_NEXT,
        BTN_PREV
    }


    @Override
    protected void onCreate(Bundle sis){
        super.onCreate(sis);
        super.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        apm = (ApplicationManager)getApplicationContext();

        registerReceiver(mReceiver, mFilter);



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
        articleNumber = intent.getExtras().getInt("article number", 0);
        year = intent.getExtras().getInt("year", 0);
        position = intent.getExtras().getInt("position", 0);
        count = intent.getExtras().getInt("count", 0);

        isTranslationOpen = false;
        rlTranslated.setTranslationY(1024);


        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        btnBack = new Button(this);
        btnBack.setLayoutParams(lp);
        btnBack.setBackgroundResource(R.drawable.article_btn_back);
        btnBack.setX(644);
        btnBack.setY(0);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                apm.resetTimer();

                if(!apm.isAnimating() && !isTranslationOpen){
                    apm.setAnimating(true);
                    overridePendingTransition(R.anim.left_in, R.anim.right_out);
                    finish();
                }



            }
        });


        rlTopTitle.addView(btnBack);

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                apm.resetTimer();

                if(!apm.isAnimating()){

                    apm.setAnimating(true);

                    Intent intent = new Intent(TimelineArticleViewActivity.this, TimelineArticleViewActivity.class);
                    if(v.getTag() == btnKind.BTN_NEXT){

                        articleNumber++;

                        intent.putExtra("year", year);
                        intent.putExtra("position", position);
                        intent.putExtra("count", count);
                        intent.putExtra("article number", articleNumber);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in, R.anim.left_out);

                    }else{

                        articleNumber--;
                        intent.putExtra("year", year);
                        intent.putExtra("position", position);
                        intent.putExtra("count", count);
                        intent.putExtra("article number", articleNumber);
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
        btnNext.setX(436);
        btnNext.setY(0);
        btnNext.setOnClickListener(ocl);
        btnNext.setTag(btnKind.BTN_NEXT);

        // attach next button if it's not a single article
        if(count > 1  && articleNumber < count ){
            rlBottomMenu.addView(btnNext);
        }


        btnPrev = new Button(this);
        btnPrev.setLayoutParams(lp);
        btnPrev.setBackgroundResource(R.drawable.article_btn_prev);
        btnPrev.setX(233);
        btnPrev.setY(0);
        btnPrev.setOnClickListener(ocl);
        btnPrev.setTag(btnKind.BTN_PREV);

        // attach previous button if it's not a single article.
        if(count > 1 && articleNumber != 1) {
            rlBottomMenu.addView(btnPrev);
        }




        btnOpenClose = new Button(this);
        btnOpenClose.setLayoutParams(lp);
        btnOpenClose.setBackgroundResource(R.drawable.article_btn_open);
        btnOpenClose.setX(582);
        btnOpenClose.setY(0);
        btnOpenClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                apm.resetTimer();

                if(!apm.isAnimating()){


                    apm.setAnimating(true);


                    if(isTranslationOpen){

                        closeTranslation();


                    }else{


                        openTranslation();

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
        tvTranslatedText.setTextColor(Color.BLACK);





        String tmp;
        tmp = String.format("%d/%d", articleNumber, count);

        tvPageCount.setText(tmp);


        // TODO: continue from here

        String strNewsIR = "timeline_detail_" + (year  + 1) + "_image_" + (position + 1) + "_news_" + articleNumber;

        ivNewspaper.setImageResource(getResources().getIdentifier(strNewsIR, "drawable", getPackageName()));
        ivArticleTitle.setImageResource(getResources().getIdentifier(strNewsIR + "_title", "drawable", getPackageName()));
        ivArticleTransTitle.setImageResource(getResources().getIdentifier(strNewsIR + "_trans_title", "drawable", getPackageName()));


        try{
            InputStream is = getResources().openRawResource(getResources().getIdentifier(strNewsIR + "_trans_text", "raw", getPackageName()));
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String interviewText;
            StringBuilder sb = new StringBuilder("");
            while((interviewText = br.readLine()) != null){
                sb.append(interviewText);
            }

            is.close();

            tvTranslatedText.setText(sb.toString());


        }catch (Exception e){
            e.printStackTrace();
        }


//            android.util.Log.i("shimaz", tvTranslatedText.getText().toString());



        PhotoViewAttacher pva = new PhotoViewAttacher(ivNewspaper);

        pva.update();


        scrlArticleTranslated.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        scrlArticleTranslated.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                apm.resetTimer();
                return false;
            }
        });



    }

    private void openTranslation(){



        ObjectAnimator openAni = ObjectAnimator.ofFloat(rlTranslated, "translationY", 1024, 88);
        openAni.setDuration(700);
        openAni.setInterpolator(new AccelerateDecelerateInterpolator());
        openAni.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                apm.setAnimating(false);
                isTranslationOpen = true;

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        openAni.start();


        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out_short);
        btnOpenClose.setBackgroundResource(R.drawable.article_btn_close);
        ivArticleTitle.startAnimation(fadeOut);
        btnBack.startAnimation(fadeOut);



    }


    private void closeTranslation(){





        ObjectAnimator closeAni = ObjectAnimator.ofFloat(rlTranslated, "translationY", 88, 1024);
        closeAni.setDuration(700);
        closeAni.setInterpolator(new AccelerateDecelerateInterpolator());
        closeAni.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                apm.setAnimating(false);
                isTranslationOpen = false;

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


        closeAni.start();

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_short);
        btnOpenClose.setBackgroundResource(R.drawable.article_btn_open);
        ivArticleTitle.startAnimation(fadeIn);
        btnBack.startAnimation(fadeIn);



    }



    private void openMenu(){
        // TODO: open top and bottom menu


    }

    private void closeMenu(){
        // TODO: close top and bottom menu

        // TODO: close top and bottom menu on timer setting

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
