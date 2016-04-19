package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.view.ViewGroup.LayoutParams;
import android.view.View.OnClickListener;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by shimaz on 2016-04-16.
 */
public class ArticleViewActivity extends Activity {

    private ApplicationManager apm;

    private int articleNumber;
    private RelativeLayout rlMain;
    private RelativeLayout rlTopTitle;
    private RelativeLayout rlBottomMenu;
    private ScrollView scrlArticleTranslated;
    private Button btnNext;
    private Button btnPrev;
    private Button btnOpenClose;
    private Button btnBack;

    private boolean isTranslationOpen;
    private boolean isMenuOpen;


    private enum btnKind {
        BTN_NEXT,
        BTN_PREV
    }


    @Override
    protected void onCreate(Bundle sis){
        super.onCreate(sis);

        apm = (ApplicationManager)getApplicationContext();



        setContentView(R.layout.activity_article_view);
        rlMain = (RelativeLayout)findViewById(R.id.rl_article_view);
        rlTopTitle = (RelativeLayout)findViewById(R.id.rl_article_top_title);
        rlBottomMenu = (RelativeLayout)findViewById(R.id.rl_article_bottom_menu);
        scrlArticleTranslated = (ScrollView)findViewById(R.id.scrl_article_translated);

        Intent intent = this.getIntent();
        articleNumber = intent.getIntExtra("article number", 0);

        isTranslationOpen = false;

        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        btnBack = new Button(this);
        btnBack.setLayoutParams(lp);
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



        OnClickListener ocl = new OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ArticleViewActivity.this, ArticleViewActivity.class);
                if(v.getTag() == btnKind.BTN_NEXT){

                    int temp = articleNumber++;
                    intent.putExtra("article number", temp);

                }else{

                    int temp = articleNumber--;
                    intent.putExtra("article number", temp);

                }
                startActivity(intent);
                overridePendingTransition(R.anim.left_out, R.anim.right_in);
                finish();


            }
        };


        btnNext = new Button(this);
        btnNext.setLayoutParams(lp);
        btnNext.setText("Next");
        btnNext.setX(200);
        btnNext.setY(50);
        btnNext.setOnClickListener(ocl);
        btnNext.setTag(btnKind.BTN_NEXT);

        rlBottomMenu.addView(btnNext);


        btnPrev = new Button(this);
        btnPrev.setLayoutParams(lp);
        btnPrev.setText("Prev");
        btnPrev.setX(600);
        btnPrev.setY(50);
        btnPrev.setOnClickListener(ocl);
        btnPrev.setTag(btnKind.BTN_PREV);

        rlBottomMenu.addView(btnPrev);



        btnOpenClose = new Button(this);
        btnOpenClose.setLayoutParams(lp);
        btnOpenClose.setX(900);
        btnOpenClose.setY(50);
        btnOpenClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isTranslationOpen){
                    // TODO: close translation


                }else{
                    // TODO: open translation

                }



            }
        });

        rlBottomMenu.addView(btnOpenClose);





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
