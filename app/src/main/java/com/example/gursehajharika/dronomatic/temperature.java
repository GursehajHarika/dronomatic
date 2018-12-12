package com.example.gursehajharika.dronomatic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static android.media.CamcorderProfile.get;

public class temperature extends AppCompatActivity {

    public Button homer;
    public EditText readingt;
    ArrayList<String> tempreadings = new ArrayList<String>();
    private ActionBar toolbar;
    DatabaseReference Reading;
    ArrayList<String> list = new ArrayList<>();

    private void  arrayconstruction(){
        tempreadings.add("Temperature  ------    Time\n ");
        tempreadings.add("22.0^0 C            At 10:30am \n ");
        tempreadings.add("20.0^0 C            At 10:31am \n ");
        tempreadings.add("25.0^0 C            At 10:32am \n ");
        tempreadings.add("40.0^0 C            At 10:33am \n ");
        tempreadings.add("50.0^0 C            At 10:34am \n ");
        tempreadings.add("24.0^0 C            At 10:35am \n ");
        tempreadings.add("10.0^0 C            At 10:36am \n ");
        tempreadings.add("20.0^0 C            At 10:37am \n ");
        tempreadings.add("20.5^0 C            At 10:38am \n ");
        tempreadings.add("20.0^0 C            At 10:39am \n ");
        tempreadings.add("20.0^0 C            At 10:40am \n ");
        tempreadings.add("20.0^0 C            At 10:41am \n ");
        tempreadings.add("22.0^0 C            At 10:30am \n ");
        tempreadings.add("20.0^0 C            At 10:31am \n ");
        tempreadings.add("25.0^0 C            At 10:32am \n ");
        tempreadings.add("40.0^0 C            At 10:33am \n ");
        tempreadings.add("50.0^0 C            At 10:34am \n ");
        tempreadings.add("24.0^0 C            At 10:35am \n ");
        tempreadings.add("10.0^0 C            At 10:36am \n ");
        tempreadings.add("20.0^0 C            At 10:37am \n ");
        tempreadings.add("20.5^0 C            At 10:38am \n ");
        tempreadings.add("20.0^0 C            At 10:39am \n ");
        tempreadings.add("20.0^0 C            At 10:40am \n ");
        tempreadings.add("20.0^0 C            At 10:41am \n ");
        tempreadings.add("22.0^0 C            At 10:30am \n ");
        tempreadings.add("20.0^0 C            At 10:31am \n ");
        tempreadings.add("25.0^0 C            At 10:32am \n ");
        tempreadings.add("40.0^0 C            At 10:33am \n ");
        tempreadings.add("50.0^0 C            At 10:34am \n ");
        tempreadings.add("24.0^0 C            At 10:35am \n ");
        tempreadings.add("10.0^0 C            At 10:36am \n ");
        tempreadings.add("20.0^0 C            At 10:37am \n ");
        tempreadings.add("20.5^0 C            At 10:38am \n ");
        tempreadings.add("20.0^0 C            At 10:39am \n ");
        tempreadings.add("20.0^0 C            At 10:40am \n ");
        tempreadings.add("20.0^0 C            At 10:41am \n ");
        tempreadings.add("22.0^0 C            At 10:30am \n ");
        tempreadings.add("20.0^0 C            At 10:31am \n ");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        setTitle("Temperature");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        readingt = findViewById(R.id.TextView);
        arrayconstruction();
        for(int i=0;i<tempreadings.size();i++) {

            readingt.setText(readingt.getText() + tempreadings.get(i));
            //readingt.append(tempreadings.get(i));
        }



        homer = findViewById(R.id.button);
        homer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        toolbar = getSupportActionBar();

        BottomNavigationView navigation = findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(bottomnavigationbar);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void showData() {
        Reading = FirebaseDatabase.getInstance().getReference();
        Reading.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String val = dataSnapshot.getValue(String.class);
                list.add(val);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

private BottomNavigationView.OnNavigationItemSelectedListener bottomnavigationbar = new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.bottomtemp){
            finish();
            Intent bottomtemperature = new Intent(temperature.this,temperature.class);
            startActivity(bottomtemperature);

        }
        else if (menuItem.getItemId() == R.id.bottomotion){
            finish();
            Intent bottommotion = new Intent(temperature.this,Motionsense.class);
            startActivity(bottommotion);
        }
        else{
            finish();
            Intent bottombaro = new Intent(temperature.this,baropress.class);
            startActivity(bottombaro);
        }
        return false;
    }
};
}
