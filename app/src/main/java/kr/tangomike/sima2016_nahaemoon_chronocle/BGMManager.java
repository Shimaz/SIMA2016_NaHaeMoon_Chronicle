package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by shimaz on 2016-04-15.
 * shimaz.bsh@gmail.com
 */
public class BGMManager {

    private boolean isPlaying;

    private MediaPlayer bgm;
    private MediaPlayer click01;
    private MediaPlayer click02;
    private MediaPlayer click03;
    private MediaPlayer click04;
    private MediaPlayer click05;
    private MediaPlayer swipeStart;
    private MediaPlayer swipeEnd;
    private MediaPlayer swipe;

    private Context ctx;

    public BGMManager(Context context){

        ctx = context;

        isPlaying = false;
        initBGM();

    }


    private void initBGM(){


        bgm = MediaPlayer.create(ctx, R.raw.bgm);
        bgm.setLooping(true);
        click01 = MediaPlayer.create(ctx, R.raw.click_newspaper); // change article
        click02 = MediaPlayer.create(ctx, R.raw.click_viewpager); // swipe
        click03 = MediaPlayer.create(ctx, R.raw.click_newspaper);
        click04 = MediaPlayer.create(ctx, R.raw.click_newspaper);
        click05 = MediaPlayer.create(ctx, R.raw.click_newspaper);



    }

//    private void setPlaying(boolean status){
//        isPlaying = status;
//
//    }

    public void playBGM(){
        if(!isPlaying){
            isPlaying = true;
            bgm.start();

        }


    }

    public void stopBGM(){

        if(isPlaying){

            isPlaying = false;
            bgm.pause();

        }


    }

    public void resetBGM(){
        bgm.pause();
        bgm.seekTo(0);
    }


    public void clickSound(int soundNum){

        switch(soundNum){
            case 1:
                click01.start();
                break;

            case 2:
                click02.start();
                break;
        }


    }

    public void playFromStart(){
        bgm.seekTo(0);
        bgm.start();
    }



    public boolean playing(){
        return isPlaying;
    }

    public void releaseAll(){
        bgm.stop();
        click01.stop();
        click02.stop();
        click03.stop();
        click04.stop();
        click05.stop();

        bgm.release();
        click01.release();
        click02.release();
        click03.release();
        click04.release();
        click05.release();
    }

}
