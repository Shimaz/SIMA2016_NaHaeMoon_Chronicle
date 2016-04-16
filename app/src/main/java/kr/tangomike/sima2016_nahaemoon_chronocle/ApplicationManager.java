package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Application;

/**
 * Created by shima on 2016-04-15.
 */
public class ApplicationManager extends Application {

    private BGMManager bgmManager;


    private void initData(){
        bgmManager = new BGMManager();


    }






    public void onCreate(){
        super.onCreate();

        initData();

    }



}
