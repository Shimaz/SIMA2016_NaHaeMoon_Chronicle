package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by shimaz on 2016-04-16.
 */
public class MediaTopActivity extends Activity {

    private RelativeLayout rlMain;
    private ApplicationManager apm;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            finish();

        }
    };


    private IntentFilter mFilter = new IntentFilter("shimaz.close");


    @Override
    protected void onCreate(Bundle sis){
        super.onCreate(sis);
        super.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_media_top);

        registerReceiver(mReceiver, mFilter);

        apm = (ApplicationManager)getApplicationContext();

        rlMain = (RelativeLayout)findViewById(R.id.rl_media_top);
        rlMain.setBackgroundResource(R.drawable.mediatop_img_bg);

        Button btnNewspaper = new Button(this);
        btnNewspaper.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        btnNewspaper.setBackgroundResource(R.drawable.mediatop_btn_article);
        btnNewspaper.setX(98);
        btnNewspaper.setY(204);
        btnNewspaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                apm.resetTimer();

                if(!apm.isAnimating()){

                    apm.setAnimating(true);

                    Intent intent = new Intent(MediaTopActivity.this, MediaArticleListActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    finish();

                }


            }
        });

        rlMain.addView(btnNewspaper);


        Button btnAudiobook = new Button(this);
        btnAudiobook.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        btnAudiobook.setBackgroundResource(R.drawable.mediatop_btn_audiobook);
        btnAudiobook.setX(98);
        btnAudiobook.setY(431);
        btnAudiobook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                apm.resetTimer();

                if(!apm.isAnimating()){
                    apm.setAnimating(true);

                    Intent intent = new Intent(MediaTopActivity.this, MediaAudioBookActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    finish();
                }



            }
        });

        rlMain.addView(btnAudiobook);



        Button btnVideo = new Button(this);
        btnVideo.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        btnVideo.setBackgroundResource(R.drawable.mediatop_btn_video);
        btnVideo.setX(98);
        btnVideo.setY(658);
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                apm.resetTimer();

                if(!apm.isAnimating()){

                    apm.setAnimating(true);

                    Intent intent = new Intent(MediaTopActivity.this, MediaVideoActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    finish();


                }



            }
        });

        rlMain.addView(btnVideo);



        Button btnHome = new Button(this);
        btnHome.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        btnHome.setBackgroundResource(R.drawable.media_common_btn_back);
        btnHome.setX(28);
        btnHome.setY(949);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                apm.resetTimer();

                if(!apm.isAnimating()){
                    apm.setAnimating(true);

                    Intent intent = new Intent(MediaTopActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.left_in, R.anim.right_out);

                    finish();

                }


            }
        });

        rlMain.addView(btnHome);


    }


    @Override
    protected void onResume(){
        super.onResume();

        apm.setAnimating(false);
        if(!apm.isAnimating()) {
            apm.startTimer();
        }


    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

}
