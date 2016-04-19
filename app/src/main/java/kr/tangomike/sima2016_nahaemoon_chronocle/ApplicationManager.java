package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Application;
import android.content.Intent;

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


    private void initData(){
        isAnimating = false;
        isTicking = false;
        tickTime = 0;
        bgmManager = new BGMManager();


    }



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
