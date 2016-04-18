package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Application;

/**
 * Created by shima on 2016-04-15.
 */
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
