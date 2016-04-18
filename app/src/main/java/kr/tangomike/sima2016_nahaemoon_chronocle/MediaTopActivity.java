package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by shimaz on 2016-04-16.
 */
public class MediaTopActivity extends Activity {

    private RelativeLayout rlMain;
    private ApplicationManager apm;

    @Override
    protected void onCreate(Bundle sis){
        super.onCreate(sis);
        setContentView(R.layout.activity_media_top);
        apm = (ApplicationManager)getApplicationContext();

        rlMain = (RelativeLayout)findViewById(R.id.rl_media_top);
        Button btnNewspaper = new Button(this);
        btnNewspaper.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        btnNewspaper.setX(100);
        btnNewspaper.setY(10);
        btnNewspaper.setText("View Newspaper");
        btnNewspaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        btnAudiobook.setX(100);
        btnAudiobook.setY(110);
        btnAudiobook.setText("Audiobook List");
        btnAudiobook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        btnVideo.setX(100);
        btnVideo.setY(220);
        btnVideo.setText("Movie Player");
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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
        btnHome.setX(680);
        btnHome.setY(10);
        btnHome.setText("Back");
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    }

}
