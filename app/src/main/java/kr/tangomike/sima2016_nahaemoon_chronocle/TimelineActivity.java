package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
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

    private final static int MOVE_HEIGHT = 500;


    @Override
    protected void onCreate(Bundle sis){
        super.onCreate(sis);
        super.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_timeline);

        apm = (ApplicationManager)getApplicationContext();
        rlMain = (RelativeLayout)findViewById(R.id.rl_timeline);
        rlMainUI = (RelativeLayout)findViewById(R.id.rl_timeline_ui);
        scrlTimeline = (ScrollView)findViewById(R.id.scrl_timeline_main);
        llTimeline = (LinearLayout)findViewById(R.id.ll_timeline);

        scrlTimeline.setSmoothScrollingEnabled(true);

        Button btnHome = new Button(this);
        btnHome.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        btnHome.setText("Home");
        btnHome.setX(340);
        btnHome.setY(10);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        // TODO: add timeline objects to layout

        rlTimelineObjects = new ArrayList();

        lastOpenPosition = 0;

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!apm.isAnimating()){

                    apm.setAnimating(true);

                    final int tag = (int)v.getTag();
                    final int position = apm.getTimelinePosition(tag);

                    lastOpenPosition = tag;


                    ObjectAnimator oa = ObjectAnimator.ofInt(scrlTimeline, "scrollY", position);
                    oa.setDuration(500);
                    oa.setInterpolator(new AccelerateDecelerateInterpolator());
                    oa.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                            openTimeline(tag);

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {

                            // TODO: delete this when detail activity work has done
//                            apm.setAnimating(false);

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



        for(int i = 0; i < apm.getTimelinePositionCount(); i++){

            RelativeLayout rl = new RelativeLayout(this);

            // calculate each layout's height;
            int rlHeight;
            if(i == apm.getTimelinePositionCount() - 1) { // last object

                rlHeight = 1024; // last object's height here

            }else{
                rlHeight = apm.getTimelinePosition(i + 1) - apm.getTimelinePosition(i);

            }

            rl.setLayoutParams(new LayoutParams(768, rlHeight));

            if(i % 2 == 1){

                rl.setBackgroundColor(Color.RED);

            }else{
                rl.setBackgroundColor(Color.BLUE);
            }

            Button btn = new Button(this);
            btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            btn.setText("Open Detail 0" + i);
            btn.setOnClickListener(ocl);
            btn.setX(300);
            btn.setY(300);
            btn.setTag(i);

            rl.addView(btn);
            rlTimelineObjects.add(rl);

            llTimeline.addView(rl);




        }



    }



    private void openTimeline(int position) {

        // TODO : move timeline components to open position
        // TODO : open detail activity when animation ended


        // TODO: detail work for open - close animation
        // TODO: <ScrollView><LinearLayout><RelativeLayout> event objects </RelativeLayout><LinearLayout><ScrollView>
        // TODO; animate event objects inside relative layout

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




    }


    private void closeTimeline(int position){

        // TODO : move timeline components to original position from activityRESULT;

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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){

        closeTimeline(lastOpenPosition);


    }



    @Override
    protected void onResume(){
        super.onResume();
        apm.setAnimating(false);
    }





}



