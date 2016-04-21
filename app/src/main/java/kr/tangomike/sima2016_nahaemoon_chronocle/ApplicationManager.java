package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Application;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

/**
 * Created by shimaz on 2016-04-15.
 */


// TODO: Global Timer - reset all activities on time
// TODO:
public class ApplicationManager extends Application {

    private BGMManager bgmManager;
    private boolean isAnimating;
    private boolean isTicking;
    private int tickTime;
    private static int STOP_TIME = 90;


    private ArrayList<Integer> articleInterviewImg;
    private ArrayList<ArrayList> articleEsseyImg;


    private void initData(){
        isAnimating = false;
        isTicking = false;
        tickTime = 0;
        bgmManager = new BGMManager();

        String PACKAGE_NAME = getPackageName();


        articleInterviewImg = new ArrayList();

        for(int i = 0; i < 6; i++){

            String num = String.format("%d", i + 1);
            String str = "article_interview_img_news_" + num;
            int ir = getResources().getIdentifier(str, "drawable", PACKAGE_NAME);
            articleInterviewImg.add(ir);


        }


        articleEsseyImg = new ArrayList();

        ArrayList<Integer> arrTmp01 = new ArrayList();
        for(int i = 0; i < 4; i++){

            String num = String.format("%d", i + 1);
            String str = "article_essay_1_img_news_" + num;
            int ir = getResources().getIdentifier(str, "drawable", PACKAGE_NAME);
            arrTmp01.add(ir);

        }

        articleEsseyImg.add(arrTmp01);


        ArrayList<Integer> arrTmp02 = new ArrayList();
        for(int i = 0; i < 7; i++){
            String num = String.format("%d", i + 1);
            String str = "article_essay_2_img_news_" + num;
            int ir = getResources().getIdentifier(str, "drawable", PACKAGE_NAME);
            arrTmp02.add(ir);

        }

        articleEsseyImg.add(arrTmp02);

        ArrayList<Integer> arrTmp03 = new ArrayList();
        for(int i = 0; i < 11; i++){
            String num = String.format("%d", i + 1);
            String str = "article_essay_3_img_news_" + num;
            int ir = getResources().getIdentifier(str, "drawable", PACKAGE_NAME);
            arrTmp03.add(ir);

        }

        articleEsseyImg.add(arrTmp03);





    }

    /**
     *
     *
     * Data Functions
     *
     *
     */


    public int getArticleInterview(int num){

        int retVal;

        retVal = articleInterviewImg.get(num);

        return retVal;

    }


    public int getArticleEssay(int category, int num){

        int retVal;

        ArrayList<Integer> arrTmp = articleEsseyImg.get(category);
        retVal = arrTmp.get(num);

        return retVal;

    }

    public int getArticleEssayCount(int category){
        int retVal;

        ArrayList<Integer> arr;
        arr = articleEsseyImg.get(category);

        retVal = arr.size();

        return retVal;

    }



    /**
     *
     *
     * Timer Functions
     *
     *
     */


    private void resetApplication(){
        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public void resetTimer(){
        // TODO: resets timer.
        tickTime = 0;


    }

    public void startTimer(){
        isTicking = true;

    }

    public void stopTimer(){
        tickTime = 0;
        isTicking = false;
    }

    public boolean isTicking(){

        return isTicking;
    }


    /**
     *
     *
     * Animation Functions
     *
     *
     */

    public void setAnimating(boolean val){
        isAnimating = val;
    }


    public boolean isAnimating(){
        return isAnimating;
    }



    public void onCreate(){
        super.onCreate();

        initData();

    }

}
