package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by shimaz on 2016-04-16.
 */
public class MediaAudioBookActivity extends Activity implements Runnable{



    private ApplicationManager apm;
    private RelativeLayout rlMain;
    private MediaPlayer mp;

    private Button btnPlayPause;
    private Button btnStop;
    private SeekBar sbAudio;
    private SeekBar sbVolume;

    private AudioManager am;
    private TextView totalTime;
    private TextView nowTime;
    private int nowTrack;

    private ArrayList<Button> btnTracks;

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
        apm = (ApplicationManager)getApplicationContext();

        registerReceiver(mReceiver, mFilter);

        setContentView(R.layout.activity_audiobook);
        rlMain = (RelativeLayout)findViewById(R.id.rl_audio_book);
        rlMain.setBackgroundResource(R.drawable.media_audiobook_img_bg);


        mp = new MediaPlayer();

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        Button btnBack = new Button(this);
        btnBack.setBackgroundResource(R.drawable.media_common_btn_back);
        btnBack.setLayoutParams(lp);
        btnBack.setX(28);
        btnBack.setY(949);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!apm.isAnimating()){

                    apm.setAnimating(true);

                    mp.stop();
                    mp = null;

                    Intent intent = new Intent(MediaAudioBookActivity.this, MediaTopActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.left_in, R.anim.right_out);
                    finish();
                }

            }
        });

        rlMain.addView(btnBack);


        nowTrack = 1;

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int tag = (int)v.getTag();

                playTrack(tag);

            }
        };



        Button btnTrack01 = new Button(this);
        btnTrack01.setLayoutParams(lp);
        btnTrack01.setBackgroundResource(R.drawable.media_audiobook_playlist_1_on);
        btnTrack01.setX(196);
        btnTrack01.setY(500);
        btnTrack01.setOnClickListener(ocl);
        btnTrack01.setTag(1);

        rlMain.addView(btnTrack01);

        Button btnTrack02 = new Button(this);
        btnTrack02.setLayoutParams(lp);
        btnTrack02.setBackgroundResource(R.drawable.media_audiobook_playlist_2);
        btnTrack02.setX(196);
        btnTrack02.setY(552);
        btnTrack02.setOnClickListener(ocl);
        btnTrack02.setTag(2);

        rlMain.addView(btnTrack02);

        Button btnTrack03 = new Button(this);
        btnTrack03.setBackgroundResource(R.drawable.media_audiobook_playlist_3);
        btnTrack03.setLayoutParams(lp);
        btnTrack03.setX(196);
        btnTrack03.setY(605);
        btnTrack03.setOnClickListener(ocl);
        btnTrack03.setTag(3);

        rlMain.addView(btnTrack03);

        Button btnTrack04 = new Button(this);
        btnTrack04.setBackgroundResource(R.drawable.media_audiobook_playlist_4);
        btnTrack04.setLayoutParams(lp);
        btnTrack04.setX(196);
        btnTrack04.setY(658);
        btnTrack04.setOnClickListener(ocl);
        btnTrack04.setTag(4);

        rlMain.addView(btnTrack04);


        btnTracks = new ArrayList();

        btnTracks.add(btnTrack01);
        btnTracks.add(btnTrack02);
        btnTracks.add(btnTrack03);
        btnTracks.add(btnTrack04);

        btnPlayPause = new Button(this);
        btnPlayPause.setBackgroundResource(R.drawable.audiobook_btn_play);
        btnPlayPause.setLayoutParams(lp);
        btnPlayPause.setX(118);
        btnPlayPause.setY(767);
        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mp.isPlaying()){
                    mp.pause();
                    btnPlayPause.setBackgroundResource(R.drawable.audiobook_btn_play);
                }else{
                    mp.start();
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
                if(mp.isPlaying()){
                    mp.pause();
                    mp.seekTo(0);
                    sbAudio.setProgress(0);
                    btnPlayPause.setBackgroundResource(R.drawable.audiobook_btn_play);
                }
            }
        });

        rlMain.addView(btnStop);



        mp = MediaPlayer.create(getBaseContext(), R.raw.track01);
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

//                mp.start();
//                btnPlayPause.setBackgroundResource(R.drawable.audiobook_btn_pause);
            }
        });

        mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                sbAudio.setProgress(0);
                btnPlayPause.setBackgroundResource(R.drawable.audiobook_btn_play);
            }
        });

        sbAudio = (SeekBar)findViewById(R.id.sb_audio);

        sbAudio.setMax(mp.getDuration());
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


        new Thread(MediaAudioBookActivity.this).start();

    }


    private void playTrack(int track){

        if(nowTrack != track) {

            nowTrack = track;

            if (mp.isPlaying()) {
                mp.stop();
            }
            sbAudio.setProgress(0);

            mp = null;

            int rid = getResources().getIdentifier("track0" + track, "raw", getPackageName());
            mp = MediaPlayer.create(getBaseContext(), rid);
            sbAudio.setMax(mp.getDuration());
            btnPlayPause.setBackgroundResource(R.drawable.audiobook_btn_play);
            totalTime.setText(getTimeString(sbAudio.getMax()));





            for(int i = 0; i < btnTracks.size(); i++){

                Button btn = btnTracks.get(i);

                int rID;

                String str = "media_audiobook_playlist_" + (i + 1);

                if(nowTrack == i + 1){

                    rID = getResources().getIdentifier(str + "_on", "drawable", getPackageName());

                }else{

                    rID = getResources().getIdentifier(str, "drawable", getPackageName());

                }

                btn.setBackgroundResource(rID);



            }




        }

    }




    @Override
    public void run() {
        int current = 0;
        while(mp != null)
        {
            try{
                Thread.sleep(1000);
                current = mp.getCurrentPosition();
                if(mp.isPlaying()){
                    apm.resetTimer();
                    sbAudio.setProgress(current);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    SeekBar.OnSeekBarChangeListener onSeekAudio = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser) mp.seekTo(progress);
            sbAudio.setProgress(progress);
            nowTime.setText(getTimeString(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

            if(mp.isPlaying()) mp.pause();

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

            mp.start();
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

        return retVal;

    }

    @Override
    protected void onResume(){
        super.onResume();
        apm.setAnimating(false);
        apm.stopBGM();
    }


    @Override
    protected void onDestroy(){
        if(mp!=null){
            if(mp.isPlaying()) mp.stop();
            mp = null;
        }

        unregisterReceiver(mReceiver);

        apm.playBGM();
        super.onDestroy();

    }
}
