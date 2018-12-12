package com.example.gursehajharika.dronomatic;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Loginwelcome extends AppCompatActivity {



  public TextView usernm;
    private static int SPLASH_TIME_OUT = 1500 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginwelcome);

        setTitle("");
        //defined the string used in HomeActivity to use the data passed from it.
        usernm = findViewById(R.id.textView2);
        usernm.setText(""+ getIntent().getStringExtra("User") );
        //Used to Create a delay of 1500ms.
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run(){
                String usersession = usernm.getText().toString();
                Intent homeIntent = new Intent(Loginwelcome.this, homepage.class);
                homeIntent.putExtra("session",usersession);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
