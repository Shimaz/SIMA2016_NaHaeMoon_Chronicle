package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shimaz on 2016-04-16.
 */
public class TimelineDetailActivity extends Activity {


    // TODO: refer to leeumshop-app view pager adaptor

    private ApplicationManager apm;
    private RelativeLayout rlMain;
    private int yearCount;
    private ArrayList<DetailPageData> dpd;
    private ViewPager viewPager;
    private Button btnNext;
    private Button btnPrev;
    private Button btnClose;
    private TextView tvPageCount;



    @Override
    protected void onCreate(Bundle sis){
        super.onCreate(sis);
        super.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        apm = (ApplicationManager)getApplicationContext();

        Intent intent = getIntent();
        yearCount = intent.getExtras().getInt("position");
        dpd = apm.getDetailPageBundle(yearCount);

        setContentView(R.layout.activity_timeline_detail);
        rlMain = (RelativeLayout)findViewById(R.id.rl_timeline_detail);


        viewPager = (ViewPager)findViewById(R.id.pager_timeline_detail);
        btnNext = (Button)findViewById(R.id.btn_timeline_detail_next);
        btnPrev = (Button)findViewById(R.id.btn_timeline_detail_prev);
        btnClose = (Button)findViewById(R.id.btn_timeline_detail_close);
        tvPageCount = (TextView)findViewById(R.id.tv_timeline_detail_page_counter);


        tvPageCount.setText("1/" + dpd.size());

        final DetailPagerAdapter adapter = new DetailPagerAdapter(getLayoutInflater(), dpd, yearCount);

        viewPager.setAdapter(adapter);
        btnNext.setBackgroundResource(R.drawable.timeline_btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apm.resetTimer();
                if(viewPager.getCurrentItem() + 1 != adapter.getCount()){
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }

            }
        });


        btnPrev.setBackgroundResource(R.drawable.timeline_btn_prev);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apm.resetTimer();

                if(viewPager.getCurrentItem() != 0){

                    viewPager.setCurrentItem((viewPager.getCurrentItem() - 1));

                }else{

                }

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String str = "" + (viewPager.getCurrentItem() + 1) + "/" + (adapter.getCount());
                tvPageCount.setText(str);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        btnClose.setBackgroundResource(R.drawable.timeline_btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(RESULT_OK);
                finish();

            }
        });


    }



    @Override
    protected void onResume(){
        super.onResume();
        apm.setAnimating(false);

    }


    public class DetailPagerAdapter extends PagerAdapter {

        LayoutInflater lf;
        ArrayList<DetailPageData> detailPageData;
        int year;

        public DetailPagerAdapter(LayoutInflater inflater, ArrayList<DetailPageData> dpd, int year){
            this.lf = inflater;
            this.detailPageData = dpd;
            this.year = year;

        }

        @Override
        public int getCount() {


            return detailPageData.size();


        }



        public Object instantiateItem(ViewGroup container, int position){

            View view;

            view = lf.inflate(R.layout.child_timeline_detail, null);

            RelativeLayout rlChild = (RelativeLayout)view.findViewById(R.id.rl_timeline_detail_child);
            ImageView ivChild = (ImageView)view.findViewById(R.id.iv_timeline_detail_viewpager_child);

            DetailPageData dd = detailPageData.get(position);

            String ivStr = "timeline_detail_" + (year + 1) + "_image_" + (position + 1);
            ivChild.setImageResource(getResources().getIdentifier(ivStr, "drawable", getPackageName()));


            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            Button btnImage;
            Button btnNews;
            Button btnPoem;

            // TODO : setup detail page components
            switch (dd.getBtnKind()){
                case 0: // Image
                    // Do nothing

                    break;


                case 1: // Image Button

                    btnImage = new Button(getApplicationContext());

                    btnImage.setBackgroundResource(R.drawable.timeline_btn_photo);
                    btnImage.setLayoutParams(lp);
                    btnImage.setY(dd.getBtnPosition());
                    btnImage.setX(282);
                    btnImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });



                    rlChild.addView(btnImage);

                    break;


                case 2: // news Button
                    btnNews = new Button(getApplicationContext());
                    btnNews.setBackgroundResource(R.drawable.timeline_btn_news);
                    btnNews.setLayoutParams(lp);
                    btnNews.setY(dd.getBtnPosition());
                    btnNews.setX(282);
                    btnNews.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });


                    rlChild.addView(btnNews);

                    break;


                case 3: // Image + News Button
                    btnImage = new Button(getApplicationContext());
                    btnImage.setBackgroundResource(R.drawable.timeline_btn_photo);
                    btnImage.setLayoutParams(lp);
                    btnImage.setY(dd.getBtnPosition());
                    btnImage.setX(131);
                    btnImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }

                    });


                    rlChild.addView(btnImage);

                    btnNews = new Button(getApplicationContext());
                    btnNews.setBackgroundResource(R.drawable.timeline_btn_news);
                    btnNews.setLayoutParams(lp);
                    btnNews.setY(dd.getBtnPosition());
                    btnNews.setX(428);
                    btnNews.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });


                    rlChild.addView(btnNews);


                    break;


                case 4: // Poem Button

                    btnPoem = new Button(getApplicationContext());
                    btnPoem.setBackgroundResource(R.drawable.timeline_btn_poem);
                    btnPoem.setLayoutParams(lp);
                    btnPoem.setY(dd.getBtnPosition());
                    btnPoem.setX(282);
                    btnPoem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });

                    rlChild.addView(btnPoem);

                    break;
            }





            container.addView(view);

            return view;
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
