package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
/**
 * Created by shimaz on 2016-04-15.
 */
public class MainActivity extends Activity{

    private RelativeLayout rlMain;
    private ApplicationManager apm;

    @Override
    protected void onCreate(Bundle sis){
        super.onCreate(sis);
        super.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        apm = (ApplicationManager)getApplicationContext();

        rlMain = (RelativeLayout)findViewById(R.id.rl_main);
        rlMain.setBackgroundResource(R.drawable.main_img_bg);

        Button btnTimeline = new Button(this);
        btnTimeline.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        btnTimeline.setBackgroundResource(R.drawable.main_btn_timeline);
        btnTimeline.setX(335);
        btnTimeline.setY(951);
        btnTimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!apm.isAnimating()){

                    apm.setAnimating(true);
                    Intent intent = new Intent(MainActivity.this, TimelineActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
                    finish();

                }



            }
        });

        rlMain.addView(btnTimeline);


        Button btnMedia = new Button(this);
        btnMedia.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        btnMedia.setBackgroundResource(R.drawable.main_btn_media);
        btnMedia.setX(573);
        btnMedia.setY(951);
        btnMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!apm.isAnimating()){

                    apm.setAnimating(true);
                    Intent intent = new Intent(MainActivity.this, MediaTopActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    finish();

                }


            }
        });


        rlMain.addView(btnMedia);







    }



    @Override
    protected void onResume(){
        super.onResume();

        // all interactions are enabled from here

        apm.setAnimating(false);
    }


}
