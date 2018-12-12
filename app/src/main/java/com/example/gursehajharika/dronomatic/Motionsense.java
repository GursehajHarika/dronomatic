package com.example.gursehajharika.dronomatic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Motionsense extends AppCompatActivity {


    public Button homer;

    public EditText motionreadings;
    ArrayList<String> readingm = new ArrayList<String>();
    private ActionBar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motionsense);

        //Title
        setTitle("Motion sensor");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);


        //return button.
        homer = findViewById(R.id.button);
        homer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
      motionreadings = findViewById(R.id.motionView);
      motionsensereading();
      for (int i = 0;i<readingm.size();i++){
        //  motionreadings.append(readingm.get(i));
          motionreadings.setText(motionreadings.getText() + readingm.get(i));
      }

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(bottomnavigationbar);

    }

    //Data readings
    public void motionsensereading(){
        readingm.add("    x-axis  --  y-axis  --  z-axis  \n");
        readingm.add("     0.98   --   0.90   --   1.00   \n");
        readingm.add("     2.98   --   0.92   --   1.23   \n");
        readingm.add("     0.76   --   0.95   --   1.34   \n");
        readingm.add("    65.50   --   9.99   --   0.40   \n");
        readingm.add("    10.10   --   0.20   --   30.56  \n");
        readingm.add("    43.30   --   6.30   --   56.33  \n");
        readingm.add("     0.99   --   2.90   --   1.00   \n");
        readingm.add("     0.98   --   0.90   --   1.00   \n");
        readingm.add("     2.98   --   0.92   --   1.23   \n");
        readingm.add("     0.76   --   0.95   --   1.34   \n");
        readingm.add("    65.50   --   9.99   --   0.40   \n");
        readingm.add("    10.10   --   0.20   --   30.56  \n");
        readingm.add("    43.30   --   6.30   --   56.33  \n");
        readingm.add("     0.99   --   2.90   --   1.00   \n");
        readingm.add("     0.98   --   0.90   --   1.00   \n");
        readingm.add("     2.98   --   0.92   --   1.23   \n");
        readingm.add("     0.76   --   0.95   --   1.34   \n");
        readingm.add("    65.50   --   9.99   --   0.40   \n");
        readingm.add("    10.10   --   0.20   --   30.56  \n");
        readingm.add("    43.30   --   6.30   --   56.33  \n");
        readingm.add("     0.99   --   2.90   --   1.00   \n");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomnavigationbar = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            if (menuItem.getItemId() == R.id.bottomtemp){
                finish();
                Intent bottomtemperature = new Intent(Motionsense.this,temperature.class);
                startActivity(bottomtemperature);

            }
            else if (menuItem.getItemId() == R.id.bottomotion){
                finish();
                Intent bottommotion = new Intent(Motionsense.this,Motionsense.class);
                startActivity(bottommotion);
            }
            else{
                finish();
                Intent bottombaro = new Intent(Motionsense.this,baropress.class);
                startActivity(bottombaro);
            }
            return false;
        }
    };

}
