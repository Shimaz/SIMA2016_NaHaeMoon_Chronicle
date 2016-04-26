package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * Created by shimaz on 2016-04-16.
 */
public class MediaVideoActivity extends Activity implements Runnable{
    private ApplicationManager apm;
    private RelativeLayout rlMain;

    private VideoView vv;

    private Button btnPlayPause;
    private Button btnStop;
    private SeekBar sbAudio;
    private SeekBar sbVolume;

    private AudioManager am;
    private TextView totalTime;
    private TextView nowTime;

    private int videoDuration;

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

        registerReceiver(mReceiver, mFilter);

        apm = (ApplicationManager)getApplicationContext();
        setContentView(R.layout.activity_video);
        rlMain = (RelativeLayout)findViewById(R.id.rl_media_video);
        rlMain.setBackgroundResource(R.drawable.media_video_img_bg);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        vv = new VideoView(this);

        sbAudio = (SeekBar)findViewById(R.id.sb_audio);
        sbVolume = (SeekBar)findViewById(R.id.sb_volume);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);



        Button btnBack = new Button(this);
        btnBack.setBackgroundResource(R.drawable.media_common_btn_back);
        btnBack.setLayoutParams(lp);
        btnBack.setX(28);
        btnBack.setY(949);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                apm.resetTimer();

                if(!apm.isAnimating()){

                    apm.setAnimating(true);

                    vv.stopPlayback();
                    vv = null;

                    Intent intent = new Intent(MediaVideoActivity.this, MediaTopActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.left_in, R.anim.right_out);
                    finish();
                }

            }
        });

        rlMain.addView(btnBack);

        btnPlayPause = new Button(this);
        btnPlayPause.setBackgroundResource(R.drawable.audiobook_btn_pause);
        btnPlayPause.setLayoutParams(lp);
        btnPlayPause.setX(118);
        btnPlayPause.setY(767);
        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(vv.isPlaying()){
                    vv.pause();
                    btnPlayPause.setBackgroundResource(R.drawable.audiobook_btn_play);
                }else{
                    vv.start();
                    btnPlayPause.setBackgroundResource(R.drawable.audiobook_btn_pause);
                }

            }
        });

        rlMain.addView(btnPlayPause);

        btnStop = new Button(this);
        btnStop.setBackgroundResource(R.drawable.audiobook_btn_stop);
        btnStop.setLayoutParams(lp);
        btnStop.setX(182);
        btnStop.setY(767);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vv.isPlaying()){
                    vv.pause();
                    vv.seekTo(0);
                    sbAudio.setProgress(0);
                    btnPlayPause.setBackgroundResource(R.drawable.audiobook_btn_play);
                }
            }
        });


        vv.setLayoutParams(lp);
        vv.setX(0);
        vv.setY(301);

        rlMain.addView(vv);

        String path = "android.resource://" + getPackageName() + "/" + R.raw.movie;
        vv.setVideoURI(Uri.parse(path));
        vv.start();

        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                videoDuration = vv.getDuration();
                setupLeftOvers();

            }
        });

        vv.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });

        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                sbAudio.setProgress(0);
                btnPlayPause.setBackgroundResource(R.drawable.audiobook_btn_play);

            }
        });




    }


    private void setupLeftOvers(){

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        sbAudio = (SeekBar)findViewById(R.id.sb_audio);
        sbAudio.setMax(videoDuration);
        sbAudio.setOnSeekBarChangeListener(onSeekAudio);
        sbAudio.setProgressDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.media_progress, null));
        sbAudio.setThumb(ResourcesCompat.getDrawable(getResources(), R.drawable.thumb_audio, null));


        am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);


        sbVolume = (SeekBar)findViewById(R.id.sb_volume);

        sbVolume.setMax(am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        sbVolume.setProgress(am.getStreamVolume(AudioManager.STREAM_MUSIC));
        sbVolume.setProgressDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.media_progress, null));
        sbVolume.setThumb(ResourcesCompat.getDrawable(getResources(), R.drawable.thumb_volume, null));
        sbVolume.setOnSeekBarChangeListener(onSeekVolume);




        totalTime = new TextView(this);
        totalTime.setLayoutParams(lp);
        totalTime.setText(getTimeString(sbAudio.getMax()));
        totalTime.setTextColor(Color.rgb(134, 109, 75));
        totalTime.setTextSize(20);
        totalTime.setX(608);
        totalTime.setY(874);

        rlMain.addView(totalTime);


        nowTime = new TextView(this);
        nowTime.setLayoutParams(lp);
        nowTime.setText("00:00");
        nowTime.setTextColor(Color.rgb(134, 109, 75));
        nowTime.setTextSize(20);
        nowTime.setX(108);
        nowTime.setY(874);

        rlMain.addView(nowTime);

        new Thread(MediaVideoActivity.this).start();

    }

    SeekBar.OnSeekBarChangeListener onSeekAudio = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser) vv.seekTo(progress);
            sbAudio.setProgress(progress);
            nowTime.setText(getTimeString(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

            if(vv.isPlaying()) vv.pause();

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

            vv.start();
        }
    };

    SeekBar.OnSeekBarChangeListener onSeekVolume = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    private String getTimeString(int time){

        float tmp = time / 1000;
        int origin = Math.round(tmp);

        int second = origin % 60;
        int minute = origin / 60;

        String retVal = String.format("%02d:%02d", minute, second);

        android.util.Log.i("shimaz", "" + time);

        return retVal;



    }

    @Override
    public void run() {
        int current = 0;
        while(vv != null)
        {
            try{
                Thread.sleep(1000);
                current = vv.getCurrentPosition();
                if(vv.isPlaying()){
                    apm.resetTimer();
                    sbAudio.setProgress(current);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        apm.setAnimating(false);
        apm.stopBGM();
    }

    @Override
    protected void onDestroy(){

        if(vv !=null){
            if(vv.isPlaying()) vv.stopPlayback();
            vv = null;
        }

        unregisterReceiver(mReceiver);

        apm.playBGM();

        super.onDestroy();

    }

}
