package kr.tangomike.sima2016_nahaemoon_chronocle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by shimaz on 2016-04-16.
 */
public class ArticleViewActivity extends Activity {

    private int articleNumber;

    @Override
    protected void onCreate(Bundle sis){
        super.onCreate(sis);
        Intent intent = this.getIntent();
        articleNumber = intent.getIntExtra("article number", 0);



    }
}
