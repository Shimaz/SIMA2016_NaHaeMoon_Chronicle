package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

/**
 * Created by shimaz on 2016-04-16.
 */
public class TimelineImageViewActivity extends Activity {

    private ApplicationManager apm;

    @Override
    protected void onCreate(Bundle sis){
        super.onCreate(sis);
        super.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        apm = (ApplicationManager)getApplicationContext();


    }


    @Override
    protected void onResume(){
        super.onResume();
        apm.setAnimating(false);
    }

}
