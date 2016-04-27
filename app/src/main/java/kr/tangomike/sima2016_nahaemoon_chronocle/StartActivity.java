package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StartActivity extends Activity {

    private RelativeLayout rlMain;
    private ApplicationManager apm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        super.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        apm = (ApplicationManager)getApplicationContext();


        rlMain = (RelativeLayout)findViewById(R.id.rl_start);




        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/nanum.ttf");

        TextView fontTest = new TextView(this);
        fontTest.setText("본 저작물에는 한겨레결체를 사용한 콘텐츠가 포함되어있습니다.");
        fontTest.setTextSize(25);
        fontTest.setTypeface(tf);
        fontTest.setX(20);
        fontTest.setY(100);
        fontTest.setLineSpacing(0, 1.25f);
        rlMain.addView(fontTest);


        TextView tvSound = new TextView(this);
        tvSound.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        tvSound.setText("Chris Zabriskie의 Cylinder Six은(는)" + "\n" + "Creative Commons Attribution 라이선스(https://creativecommons.org/licenses/by/4.0/)에" + "\n" + " 따라 라이선스가 부여됩니다.\n" +
                "출처: http://chriszabriskie.com/cylinders/"+"\n"+"아티스트: http://chriszabriskie.com/");
        tvSound.setTextSize(15);
        tvSound.setX(20);
        tvSound.setY(200);
        tvSound.setLineSpacing(25, 0.125f);
        rlMain.addView(tvSound);





//        apm.resetBGM();



//        Button btnTmp = new Button(this);
//        btnTmp.setText("Start");
//        btnTmp.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//        btnTmp.setX(100);
//        btnTmp.setY(100);
//        btnTmp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(!apm.isAnimating()){
//
//                    apm.setAnimating(true);
//                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.fade_in_short, R.anim.fade_out_short);
//                    finish();
//
//                }
//
//            }
//        });



//        Handler handler = new Handler(){
//            public void handlerMessage(Message msg){
//
//                apm.setAnimating(true);
//                Intent intent = new Intent(StartActivity.this, MainActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in_short, R.anim.fade_out_short);
//                finish();
//
//            }
//
//
//        };
//
//        handler.sendEmptyMessageDelayed(0, 1500);



        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){

                apm.setAnimating(true);
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_short, R.anim.fade_out_short);
                finish();


            }
        }, 1800);




    }
}
