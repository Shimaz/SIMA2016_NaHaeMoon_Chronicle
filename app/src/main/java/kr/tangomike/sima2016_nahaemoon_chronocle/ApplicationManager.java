package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Application;

/**
 * Created by shimaz on 2016-04-15.
 */


// TODO: Global Timer - reset all activities on time
// TODO:
public class ApplicationManager extends Application {

    private BGMManager bgmManager;
    private boolean isAnimating;


    private void initData(){
        isAnimating = false;
        bgmManager = new BGMManager();


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
