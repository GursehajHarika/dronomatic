package com.example.gursehajharika.dronomatic;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    //define before creating a splash screen
    private static int SPLASH_TIME_OUT = 2500 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("");


        //splash screen creation.
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run(){

//intent used to go to the login page that is named as (HomeActivity).
                Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);

                startActivity(homeIntent);
                finish();

                             }
            },SPLASH_TIME_OUT);



    }

}
