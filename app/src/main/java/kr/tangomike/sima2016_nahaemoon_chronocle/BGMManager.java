package kr.tangomike.sima2016_nahaemoon_chronocle;

/**
 * Created by shimaz on 2016-04-15.
 * shimaz.bsh@gmail.com
 */
public class BGMManager {

    private boolean isPlaying;

    public BGMManager(){

        isPlaying = false;
        initBGM();

    }


    private void initBGM(){

    }

    private void setPlaying(boolean status){
        if(isPlaying){
            isPlaying = false;
        }else{
            isPlaying = true;
        }
    }

    public void playBGM(){

    }

    public void stopBGM(){

    }

    public void fadeOut(){

    }



    public void fadeIn(){

    }



    public boolean playing(){
        return isPlaying;
    }


}
