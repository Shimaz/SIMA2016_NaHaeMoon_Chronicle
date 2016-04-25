package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Application;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by shimaz on 2016-04-15.
 */



// TODO: Delay on animation status (false)

public class ApplicationManager extends Application {

    private BGMManager bgmManager;
    private boolean isAnimating;
    private boolean isTicking;
    private int tickTime;
    private static int STOP_TIME = 90;

    private Handler mHandler;

    private String PACKAGE_NAME;

    private int POSITION_MARGIN = 152;

    // ArrayLists for ArticleView Data
    private ArrayList<Integer> articleInterviewImg;
    private ArrayList<ArrayList> articleEssayImg;
    private ArrayList<Integer> articleInterviewTitle;
    private ArrayList<ArrayList> articleEssayTitle;
    private ArrayList<Integer> articleInterviewTransTitle;
    private ArrayList<ArrayList> articleEssayTransTitle;
    private ArrayList<Integer> articleInterviewTranslated;
    private ArrayList<ArrayList> articleEssayTranslated;



    // ArrayLists for Timeline and Timeline
    private ArrayList<Integer> timelinePositions;
    private ArrayList<ArrayList> timelineDetails;






    private void initData(){
        isAnimating = false;
        isTicking = false;
        tickTime = 0;
        bgmManager = new BGMManager();

        PACKAGE_NAME = getPackageName();

    }


    private void initArticleData(){

        articleInterviewImg = new ArrayList();
        articleInterviewTitle = new ArrayList();
        articleInterviewTransTitle = new ArrayList();

        articleInterviewTranslated = new ArrayList();


        for(int i = 0; i < 6; i++){

            String num = String.format("%d", i + 1);
            String str = "article_interview_img_news_" + num;
            int ir = getResources().getIdentifier(str, "drawable", PACKAGE_NAME);
            articleInterviewImg.add(ir);


            String str2 = "article_interview_img_title_" + num;
            int ir2 = getResources().getIdentifier(str2, "drawable", PACKAGE_NAME);
            articleInterviewTitle.add(ir2);


            String str3 = "article_interview_trans_img_title_" + num;
            int ir3 = getResources().getIdentifier(str3, "drawable", PACKAGE_NAME);
            articleInterviewTransTitle.add(ir3);

            String str4 = "article_interview_trans_text_" + num;
            int ir4 = getResources().getIdentifier(str4, "raw", PACKAGE_NAME);
            articleInterviewTranslated.add(ir4);

        }


        articleEssayImg = new ArrayList();
        articleEssayTitle = new ArrayList();
        articleEssayTransTitle = new ArrayList();
        articleEssayTranslated = new ArrayList();

        ArrayList<Integer> arrTmp01 = new ArrayList();
        ArrayList<Integer> arrTmp011 = new ArrayList();
        ArrayList<Integer> arrTmp0111 = new ArrayList();
        ArrayList<Integer> arrTmp01111 = new ArrayList();
        for(int i = 0; i < 4; i++){

            String num = String.format("%d", i + 1);
            String str = "article_essay_1_img_news_" + num;
            int ir = getResources().getIdentifier(str, "drawable", PACKAGE_NAME);
            arrTmp01.add(ir);

            String str2 = "article_essay_1_img_title_" + num;
            int ir2 = getResources().getIdentifier(str2, "drawable", PACKAGE_NAME);
            arrTmp011.add(ir2);

            String str3 = "article_essay_1_trans_img_title_" + num;
            int ir3 = getResources().getIdentifier(str3, "drawable", PACKAGE_NAME);
            arrTmp0111.add(ir3);

            String str4 = "article_essay_1_trans_text_" + num;
            int ir4 = getResources().getIdentifier(str4, "raw", PACKAGE_NAME);
            arrTmp01111.add(ir4);

        }

        articleEssayImg.add(arrTmp01);
        articleEssayTitle.add(arrTmp011);
        articleEssayTransTitle.add(arrTmp0111);
        articleEssayTranslated.add(arrTmp01111);



        ArrayList<Integer> arrTmp02 = new ArrayList();
        ArrayList<Integer> arrTmp022 = new ArrayList();
        ArrayList<Integer> arrTmp0222 = new ArrayList();
        ArrayList<Integer> arrTmp02222 = new ArrayList();
        for(int i = 0; i < 7; i++){
            String num = String.format("%d", i + 1);
            String str = "article_essay_2_img_news_" + num;
            int ir = getResources().getIdentifier(str, "drawable", PACKAGE_NAME);
            arrTmp02.add(ir);

            String str2 = "article_essay_2_img_title_" + num;
            int ir2 = getResources().getIdentifier(str2, "drawable", PACKAGE_NAME);
            arrTmp022.add(ir2);

            String str3 = "article_essay_2_trans_img_title_" + num;
            int ir3 = getResources().getIdentifier(str3, "drawable", PACKAGE_NAME);
            arrTmp0222.add(ir3);

            String str4 = "article_essay_2_trans_text_" + num;
            int ir4 = getResources().getIdentifier(str4, "raw", PACKAGE_NAME);
            arrTmp02222.add(ir4);

        }

        articleEssayImg.add(arrTmp02);
        articleEssayTitle.add(arrTmp022);
        articleEssayTransTitle.add(arrTmp0222);
        articleEssayTranslated.add(arrTmp02222);


        ArrayList<Integer> arrTmp03 = new ArrayList();
        ArrayList<Integer> arrTmp033 = new ArrayList();
        ArrayList<Integer> arrTmp0333= new ArrayList();
        ArrayList<Integer> arrTmp03333 = new ArrayList();
        for(int i = 0; i < 11; i++){
            String num = String.format("%d", i + 1);
            String str = "article_essay_3_img_news_" + num;
            int ir = getResources().getIdentifier(str, "drawable", PACKAGE_NAME);
            arrTmp03.add(ir);

            String str2 = "article_essay_3_img_title_" + num;
            int ir2 = getResources().getIdentifier(str2, "drawable", PACKAGE_NAME);
            arrTmp033.add(ir2);

            String str3 = "article_essay_3_trans_img_title_" + num;
            int ir3 = getResources().getIdentifier(str3, "drawable", PACKAGE_NAME);
            arrTmp0333.add(ir3);

            String str4 = "article_essay_3_trans_text_" + num;
            int ir4 = getResources().getIdentifier(str4, "raw", PACKAGE_NAME);
            arrTmp03333.add(ir4);

        }

        articleEssayImg.add(arrTmp03);
        articleEssayTitle.add(arrTmp033);
        articleEssayTransTitle.add(arrTmp0333);
        articleEssayTranslated.add(arrTmp03333);




    }



    private void initTimelineData(){



        timelinePositions = new ArrayList();
        timelineDetails = new ArrayList();




        // Load timeline detail data from csv file
        InputStream is = getResources().openRawResource(R.raw.timeline);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        ArrayList<String> tempData = new ArrayList();

        try {
            while((line = reader.readLine()) != null) {
                tempData.add(line);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }




        for(int i = 0; i < tempData.size(); i++){

            String oneEvent = tempData.get(i);
            String[] values = oneEvent.split(",");

            ArrayList<DetailPageData> dpd = new ArrayList();

            for(int j = 0; j < values.length; j++){

                String rawData = values[j];
                String[] detailpageString = rawData.split("/");


                DetailPageData dd;

                if(Integer.parseInt(detailpageString[0]) != 0){

                    dd = new DetailPageData(Integer.parseInt(detailpageString[1]), Integer.parseInt(detailpageString[0]), Integer.parseInt(detailpageString[2]), Integer.parseInt(detailpageString[3]));


                }else{
                    dd = new DetailPageData(0,0,0,0);

                }

                dpd.add(dd);

            }

            timelineDetails.add(dpd);


        }




        // Timeine position data

        InputStream iss = getResources().openRawResource(R.raw.open_position);

        BufferedReader readerr = new BufferedReader(new InputStreamReader(iss));
        String linee;


        try {
            while((linee = readerr.readLine()) != null) {

                int tmp = Integer.parseInt(linee) - POSITION_MARGIN;

                timelinePositions.add(tmp);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }







    }


    /**
     *
     *
     * Timeline Data Functions
     *
     *
     */


    public int getTimelinePosition(int num){

        int retVal;

        retVal = timelinePositions.get(num);

        return retVal;

    }

    public int getTimelinePositionCount(){

        return timelinePositions.size();


    }


    /**
     *
     *
     * Timeline Detail Fucntions
     *
     *
     */


    public ArrayList<DetailPageData> getDetailPageBundle(int yearCount){
        ArrayList<DetailPageData> retVal;

        retVal = timelineDetails.get(yearCount);


        return retVal;

    }


    /**
     *
     *
     * Media ArticleView Data Functions
     *
     *
     */


    public int getArticleInterview(int num){

        int retVal;

        retVal = articleInterviewImg.get(num);

        return retVal;

    }

    public int getArticleInterviewTitle(int articleNumber, boolean isTranslated){
        int retVal;

        if(isTranslated){

            retVal = articleInterviewTransTitle.get(articleNumber);

        }else{

            retVal = articleInterviewTitle.get(articleNumber);

        }

        return retVal;

    }


    public int getArticleEssay(int category, int num) {

        int retVal;

        ArrayList<Integer> arrTmp = articleEssayImg.get(category);
        retVal = arrTmp.get(num);

        return retVal;

    }

    public int getArticleEssayTitle(int category, int num, boolean isTranslated){
        int retVal;

        ArrayList<Integer> arr;
        if(isTranslated){
            arr = articleEssayTransTitle.get(category);

        }else{
            arr = articleEssayTitle.get(category);

        }

        retVal = arr.get(num);

        return retVal;


    }

    public int getArticleEssayCount(int category){
        int retVal;

        ArrayList<Integer> arr;
        arr = articleEssayImg.get(category);

        retVal = arr.size();

        return retVal;

    }



    public int getInterviewTranslatedText(int num){

        int retVal;
        retVal = articleInterviewTranslated.get(num);

        return retVal;


    }

    public int getEssayText(int category, int num){

        int retVal;

        ArrayList<Integer> arrTmp;
        arrTmp = articleEssayTranslated.get(category);

        retVal = arrTmp.get(num);

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
//        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);

        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }


    public void resetTimer(){
        tickTime = 0;


    }

    public void startTimer(){
        isTicking = true;
        mHandler.sendEmptyMessage(0);

    }

    public void stopTimer(){
        mHandler.removeMessages(0);
        tickTime = 0;
        isTicking = false;
    }




    /**
     *
     *
     * Animation Functions
     *
     *
     */

    public void setAnimating(boolean val){
        resetTimer();
        isAnimating = val;

        if(val){
            android.util.Log.i("shimaz", "true");
        }else{
            android.util.Log.i("shimaz", "false");
        }

    }


    public boolean isAnimating(){
        resetTimer();
        return isAnimating;
    }



    public void onCreate(){
        super.onCreate();

        initData();
        initArticleData();
        initTimelineData();

        mHandler = new Handler() {
            public void handleMessage(Message msg){

                if(isTicking) tickTime++;


                android.util.Log.i("shimaz", ""+tickTime);



                if(tickTime <= STOP_TIME){

                    mHandler.sendEmptyMessageDelayed(0, 1000);

                }else if(tickTime > STOP_TIME){

                    tickTime = 0;
                    mHandler.removeMessages(0);
                    android.util.Log.i("shimaz", "reset application");
                    resetApplication();

                }


            }
        };

        mHandler.sendEmptyMessage(0);


    }

}
