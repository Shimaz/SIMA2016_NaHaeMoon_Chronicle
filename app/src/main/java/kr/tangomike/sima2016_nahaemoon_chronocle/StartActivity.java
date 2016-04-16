package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    private RelativeLayout rlMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        rlMain = (RelativeLayout)findViewById(R.id.rl_start);

        TextView tv = new TextView(this);
        tv.setText("Start Splash Screen");

        rlMain.addView(tv);


        Button btnTmp = new Button(this);
        btnTmp.setText("Start");
        btnTmp.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        btnTmp.setX(100);
        btnTmp.setY(100);
        btnTmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();


            }
        });


    }
}
