package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

/**
 * Created by shimaz on 2016-04-16.
 */
public class TimelineDetailActivity extends Activity {

    private ApplicationManager apm;
    private RelativeLayout rlMain;

    @Override
    protected void onCreate(Bundle sis){
        super.onCreate(sis);
        apm = (ApplicationManager)getApplicationContext();
        rlMain = (RelativeLayout)findViewById(R.id.rl_timeline_detail);

    }



    @Override
    protected void onResume(){
        super.onResume();
        apm.setAnimating(false);

    }
}
