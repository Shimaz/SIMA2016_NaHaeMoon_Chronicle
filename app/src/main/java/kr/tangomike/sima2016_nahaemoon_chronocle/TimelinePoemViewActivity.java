package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by shimaz on 2016-04-22.
 * shimaz.bsh@gmail.com
 */
public class TimelinePoemViewActivity extends Activity {


    private ApplicationManager apm;

    @Override
    protected void onCreate(Bundle sis){
        super.onCreate(sis);
        apm = (ApplicationManager)getApplicationContext();


    }


    @Override
    protected void onResume(){
        super.onResume();
        apm.setAnimating(false);
    }


}
