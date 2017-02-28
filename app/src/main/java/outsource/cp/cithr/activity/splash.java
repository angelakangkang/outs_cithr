package outsource.cp.cithr.activity;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import outsource.cp.cithr.R;



public class splash extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);


        /**
         * 这里可以做自动登录，设置广告什么的
         */
        startActivity(new Intent(this, User_MainActivity.class));
        finish();
    }


}
