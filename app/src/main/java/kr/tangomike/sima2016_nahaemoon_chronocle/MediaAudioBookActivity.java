package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

/**
 * Created by shimaz on 2016-04-16.
 */
public class MediaAudioBookActivity extends Activity {

    private ApplicationManager apm;

    private RelativeLayout rlMain;

    @Override
    protected void onCreate(Bundle sis){
        super.onCreate(sis);

        apm = (ApplicationManager)getApplicationContext();

        setContentView(R.layout.activity_audiobook);
        rlMain = (RelativeLayout)findViewById(R.id.rl_audio_book);

    }



    @Override
    protected void onResume(){
        super.onResume();
        apm.setAnimating(false);
    }
}
