package com.example.gursehajharika.dronomatic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class baropress extends AppCompatActivity {

    public Button homer;
    public EditText readingb;
    ArrayList<String> baroreadings = new ArrayList<String>();
    private ActionBar toolbar;
    public ListView listView ;
    public ListView listView2;
    private static final String TAG = "baropress";
    public TextView testreading;
    public TextView testtimer;
    private FirebaseAuth.AuthStateListener mAuthListener;
    long a;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
  //private FirebaseAuth.AuthStateListener mAuthlistener;
    private DatabaseReference mRef;
    private String userID, readings,timer;

    SharedPreferences sharedPref;



   String[] TIMER = {"10:30","10:31","10:32","10:33","10:34","10:35","10:36","10:37","10:38","10:39","10:30","10:31","10:32","10:33"};
   String[] DESCRIPTION = {"200ft   --  31.02","400ft   --  35.02","200ft   --  31.02","400ft   --  35.02","200ft   --  31.02","400ft   --  35.02","200ft   --  31.02","400ft   --  35.02","200ft   --  31.02","400ft   --  35.02","200ft   --  31.02","400ft   --  35.02","200ft   --  31.02","400ft   --  35.02"};

    private void arraybarometric(){
        baroreadings.add("    Altitude   -- Baro Pressure \n");
        baroreadings.add("      200ft   --      31.02  \n");
        baroreadings.add("      400ft   --      32.51 \n");
        baroreadings.add("      750ft   --      45.67 \n");
        baroreadings.add("      920ft   --      58.12 \n");
        baroreadings.add("      869ft   --      10.22 \n");
        baroreadings.add("      420ft   --      90.23 \n");
        baroreadings.add("      120ft   --      37.55 \n");
        baroreadings.add("      200ft   --      31.02 \n");
        baroreadings.add("      400ft   --      32.51 \n");
        baroreadings.add("      750ft   --      45.67 \n");
        baroreadings.add("      920ft   --      58.12 \n");
        baroreadings.add("      869ft   --      10.22 \n");
        baroreadings.add("      420ft   --      90.23 \n");
        baroreadings.add("      120ft   --      37.55 \n");
        baroreadings.add("      200ft   --      31.02 \n");
        baroreadings.add("      400ft   --      32.51 \n");
        baroreadings.add("      750ft   --      45.67 \n");
        baroreadings.add("      920ft   --      58.12 \n");
        baroreadings.add("      869ft   --      10.22 \n");
        baroreadings.add("      420ft   --      90.23 \n");
        baroreadings.add("      120ft   --      37.55 \n");
        baroreadings.add("      200ft   --      31.02 \n");
        baroreadings.add("      400ft   --      32.51 \n");
        baroreadings.add("      750ft   --      45.67 \n");
        baroreadings.add("      920ft   --      58.12 \n");
        baroreadings.add("      869ft   --      10.22 \n");
        baroreadings.add("      420ft   --      90.23 \n");
        baroreadings.add("      120ft   --      37.55 \n");
        baroreadings.add("      200ft   --      31.02 \n");
        baroreadings.add("      400ft   --      32.51 \n");
        baroreadings.add("      750ft   --      45.67 \n");
        baroreadings.add("      920ft   --      58.12 \n");
        baroreadings.add("      869ft   --      10.22 \n");
        baroreadings.add("      420ft   --      90.23 \n");
        baroreadings.add("      120ft   --      37.55 \n");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baropress);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        setTitle("Barometric Pressure");


        databaseread();



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

    public void databaseread(){

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
                                                mRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                shower(dataSnapshot);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void shower(DataSnapshot dataSnapshot) {
        ArrayList<String> array = new ArrayList<>();
        baropress_databaseread uinfor = new baropress_databaseread(readings,timer);
        for(DataSnapshot ds : dataSnapshot.child("user").child(userID).child("Reading").getChildren()){
            uinfor.setValuer(ds.getValue(baropress_databaseread.class).getValuer());
            uinfor.setTimestamp(ds.getValue(baropress_databaseread.class).getTimestamp());
           // convertTimestamp(uinfor.getTimestamp());
            Log.d(TAG, " Getting Children Time Stamp : - "+convertTimestamp(uinfor.getTimestamp()));
            Log.d(TAG, " Getting Children Values : - "+uinfor.getValuer());
            array.add(" Time                : " + convertTimestamp(uinfor.getTimestamp()));
            array.add("Readings         : " + uinfor.getValuer());

            //ListView to display the data.
            listView = (ListView)findViewById(R.id.listview);
            ArrayAdapter adapter = new ArrayAdapter(baropress.this,android.R.layout.simple_list_item_1,array);
            listView.setAdapter(adapter);

        }

        //Tester Textview
        testtimer = (TextView)findViewById(R.id.testertime);
        testreading = (TextView)findViewById(R.id.testerreading);
        testtimer.setVisibility(View.INVISIBLE);
        testreading.setVisibility(View.INVISIBLE);
        testtimer.setText("Tester");
        testreading.setText("tester");


    }

    private String convertTimestamp(String timestamp){

        long yourSeconds = Long.valueOf(timestamp);
        Date mDate = new Date(yourSeconds * 1000);
        DateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");
        return df.format(mDate);
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
                Intent bottomtemperature = new Intent(baropress.this,temperature.class);
                startActivity(bottomtemperature);

            }
            else if (menuItem.getItemId() == R.id.bottomotion){
                finish();
                Intent bottommotion = new Intent(baropress.this,Motionsense.class);
                startActivity(bottommotion);
            }
            else{
                finish();
                Intent bottombaro = new Intent(baropress.this,baropress.class);
                startActivity(bottombaro);
            }
            return false;
        }
    };



}
