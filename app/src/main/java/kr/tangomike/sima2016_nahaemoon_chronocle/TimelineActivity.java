package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

/**
 * Created by shimaz on 2016-04-16.
 */
public class TimelineActivity extends Activity {

    private ApplicationManager apm;
    private RelativeLayout rlMain;
    private RelativeLayout rlMainUI;
    private ScrollView scrlTimeline;
    private LinearLayout llTimeline;

    private int lastOpenPosition;

    private ArrayList<RelativeLayout> rlTimelineObjects;
    private ArrayList<Button> btnTimeline;

    private final static int MOVE_HEIGHT = 763;

    private Button btnHome;

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

        setContentView(R.layout.activity_timeline);

        apm = (ApplicationManager)getApplicationContext();
        rlMain = (RelativeLayout)findViewById(R.id.rl_timeline);
        rlMainUI = (RelativeLayout)findViewById(R.id.rl_timeline_ui);
        scrlTimeline = (ScrollView)findViewById(R.id.scrl_timeline_main);
        llTimeline = (LinearLayout)findViewById(R.id.ll_timeline);

        scrlTimeline.setSmoothScrollingEnabled(true);
        rlMain.setBackgroundResource(R.drawable.timeline_detail_img_open_bg);

        btnHome = new Button(this);
        btnHome.setBackgroundResource(R.drawable.timeline_btn_home);
        btnHome.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

//        btnHome.setLayoutParams(new LayoutParams(100, 100));
        btnHome.setX(0);
        btnHome.setY(30);

//        btnHome.setBackgroundColor(Color.CYAN);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0; i <rlTimelineObjects.size(); i++){
                    RelativeLayout rl = rlTimelineObjects.get(i);

                    android.util.Log.i("nhs height", "" + rl.getMeasuredHeight());
                }

                apm.resetTimer();

                Intent intent = new Intent(TimelineActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.top_in, R.anim.bottom_out);
                finish();

            }
        });

        rlMainUI.addView(btnHome);





        initTimeline();



    }


    private void initTimeline(){


        rlTimelineObjects = new ArrayList();
        btnTimeline = new ArrayList();

        lastOpenPosition = 0;

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                apm.resetTimer();

                if(!apm.isAnimating()){

                    apm.setAnimating(true);

                    final int tag = (int)v.getTag();
                    final int position = apm.getTimelinePosition(tag);

                    lastOpenPosition = tag;


                    ObjectAnimator oa = ObjectAnimator.ofInt(scrlTimeline, "scrollY", position);
                    android.util.Log.i("shimaz",""+ tag + "_" + position);
                    oa.setDuration(500);
                    oa.setInterpolator(new AccelerateDecelerateInterpolator());
                    oa.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                            openTimeline(tag);

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {


                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });

                    oa.start();

                }


            }
        };

        // add first layout object

        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        RelativeLayout rlFirst = new RelativeLayout(this);
        rlFirst.setLayoutParams(lp);
        ImageView ivFirst = new ImageView(this);
        ivFirst.setLayoutParams(lp);
        ivFirst.setImageResource(R.drawable.timeline_1_1);
        rlFirst.addView(ivFirst);
        llTimeline.addView(rlFirst);


//        int tmpHeight;

        for(int i = 0; i < apm.getTimelinePositionCount(); i++){

            RelativeLayout rl = new RelativeLayout(this);

            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.VERTICAL);
            int tmp = i + 1;

            String str1 = "timeline_" + tmp + "_2";
            String str2 = "timeline_" + (tmp + 1) + "_1";

            ImageView iv1 = new ImageView(this);
            iv1.setImageResource(getResources().getIdentifier(str1, "drawable", getPackageName()));
            iv1.setLayoutParams(lp);
            ll.addView(iv1);

            if(tmp != apm.getTimelinePositionCount()){

                ImageView iv2 = new ImageView(this);
                iv2.setImageResource(getResources().getIdentifier(str2, "drawable", getPackageName()));
                iv2.setLayoutParams(lp);
                ll.addView(iv2);

            }




            ll.setLayoutParams(lp);
            rl.addView(ll);

            rl.setLayoutParams(lp);


            Button btn = new Button(this);
            btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            btn.setBackgroundResource(R.drawable.timeline_btn_open);
            btn.setOnClickListener(ocl);
            btn.setX(349);

            int yPos = 14;

            switch (i){

                case 2:
                    yPos += 3;
                    break;

                case 0:
                case 3:
                case 4:
                case 5:
                case 6:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:

                    yPos += 1;
                    break;

                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                    yPos += 2;
                    break;



                default:
                    yPos = 14;

                    break;
            }

            btn.setY(yPos);
            btn.setTag(i);

            rl.addView(btn);

            rlTimelineObjects.add(rl);
            btnTimeline.add(btn);




            llTimeline.addView(rl);





        }

        scrlTimeline.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                apm.resetTimer();
                return false;
            }
        });


    }



    private void openTimeline(int position) {




        final int pos = position;

        RelativeLayout rl = rlTimelineObjects.get(position);


        ObjectAnimator oa = ObjectAnimator.ofFloat(rl, "translationY", MOVE_HEIGHT);
        oa.setInterpolator(new AccelerateDecelerateInterpolator());
        oa.setDuration(500);
        oa.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                android.util.Log.i("shimaz", "" + pos);

                Intent intent = new Intent(TimelineActivity.this, TimelineDetailActivity.class);
                intent.putExtra("position", pos);

                startActivityForResult(intent, pos);
                overridePendingTransition(R.anim.fade_in_short ,0);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });



        oa.start();

        Button btn = btnTimeline.get(position);

        btn.setBackgroundResource(R.drawable.timeline_btn_open_r);


        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out_short);
        btnHome.startAnimation(fadeOut);



    }


    private void closeTimeline(int position){


        apm.setAnimating(true);

        RelativeLayout rl = rlTimelineObjects.get(position);

        ObjectAnimator oa = ObjectAnimator.ofFloat(rl, "translationY", 0);
        oa.setInterpolator(new AccelerateDecelerateInterpolator());
        oa.setStartDelay(300);
        oa.setDuration(250);
        oa.addListener(new Animator.AnimatorListener() {
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                apm.setAnimating(false);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        oa.start();


        Button btn = btnTimeline.get(position);
        btn.setBackgroundResource(R.drawable.timeline_btn_open);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_short);
        btnHome.startAnimation(fadeIn);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){

        closeTimeline(lastOpenPosition);


    }



    @Override
    protected void onResume(){
        super.onResume();
        apm.setAnimating(false);
        if(!apm.isTicking()){
            apm.startTimer();
        }




    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        unregisterReceiver(mReceiver);
    }





}



