package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shimaz on 2016-04-16.
 */
public class TimelineImageViewActivity extends Activity {

    private ApplicationManager apm;
    private ViewPager viewPager;
    private int year;
    private int position;
    private int count;

    private RelativeLayout rlMain;
    private Button btnClose;
    private Button btnPrev;
    private Button btnNext;
    private TextView tvPageCount;


    @Override
    protected void onCreate(Bundle sis){
        super.onCreate(sis);
        super.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_timeline_detail_photoview);
        apm = (ApplicationManager)getApplicationContext();

        rlMain = (RelativeLayout)findViewById(R.id.rl_timeline_detail_photoview);
        rlMain.setBackgroundResource(R.drawable.timeline_detail_bg);

        Intent intent = getIntent();
        year = intent.getExtras().getInt("year");
        position = intent.getExtras().getInt("position");
        count = intent.getExtras().getInt("count");


        final PhotoViewAdapter adapter = new PhotoViewAdapter(getLayoutInflater(), year, position, count);
        viewPager = (ViewPager)findViewById(R.id.pager_timeline_detail_photo);
        viewPager.setAdapter(adapter);

        btnClose = (Button)findViewById(R.id.btn_timeline_detail_photo_close);
        btnNext = (Button)findViewById(R.id.btn_timeline_detail_photo_next);
        btnPrev = (Button)findViewById(R.id.btn_timeline_detail_photo_prev);



        btnClose.setBackgroundResource(R.drawable.timeline_btn_photo_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                overridePendingTransition(R.anim.fade_in_short, R.anim.fade_out_short);
                finish();


            }
        });


        if(count > 1){

            btnNext.setBackgroundResource(R.drawable.timeline_btn_photo_next);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(viewPager.getCurrentItem() < count -1){
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }

                }
            });

            btnPrev.setBackgroundResource(R.drawable.timeline_btn_photo_prev);
            btnPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(viewPager.getCurrentItem() > 0)

                        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                }
            });

        }else{
            btnNext.setVisibility(View.GONE);
            btnPrev.setVisibility(View.GONE);
        }


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvPageCount.setText((position + 1) + "/" + count);

                apm.resetTimer();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        tvPageCount = (TextView)findViewById(R.id.tv_timeline_detail_photo_page_counter);
        tvPageCount.setText("1/" + count);




    }


    @Override
    protected void onResume(){
        super.onResume();
        apm.setAnimating(false);
    }



    public class PhotoViewAdapter extends PagerAdapter {

        LayoutInflater lf;
        int year;
        int detailPosition;
        int count;

        public PhotoViewAdapter(LayoutInflater inflater, int year, int position, int count){
            this.lf = inflater;
            this.year = year;
            this.detailPosition = position;
            this.count = count;

        }

        @Override
        public int getCount() {


            return count;


        }



        public Object instantiateItem(ViewGroup container, int position){

            View view;

            view = lf.inflate(R.layout.child_timeline_detail_photo, null);

            ImageView ivChild = (ImageView)view.findViewById(R.id.iv_timeline_detail_photo_viewpager_child);


            String ivStr = "timeline_detail_" + (year + 1) + "_image_" + (detailPosition + 1) + "_photo_" + (position + 1);
            ivChild.setImageResource(getResources().getIdentifier(ivStr, "drawable", getPackageName()));


            android.util.Log.i("shimaz", ivStr);


            container.addView(view);

            return view;
        }


        @Override
        public void destroyItem(ViewGroup container, int arg1, Object arg2){

            container.removeView((View)arg2);

        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);

        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0 == ((View) arg1);


        }

        @Override
        public Parcelable saveState() {
            return null;
        }


    }

}
