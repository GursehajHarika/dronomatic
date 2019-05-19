package com.example.gursehajharika.dronomatic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
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
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Motionsense extends AppCompatActivity {


    public Button homer;

    public EditText motionreadings;
    ArrayList<String> readingm = new ArrayList<String>();
    private ActionBar toolbar;
    private static final String TAG = "Motion sense";
    private FirebaseAuth.AuthStateListener mAuthListener;
    long a;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    //private FirebaseAuth.AuthStateListener mAuthlistener;
    private DatabaseReference mRef;
    private String userID, readings,timer;
    public ListView motionviewr;
    ArrayList<String> lister = new ArrayList<>();

    SharedPreferences sharedPref;



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
        databaseread();


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

        for(DataSnapshot ds : dataSnapshot.child("user").child(userID).child("Motion").getChildren()){
            uinfor.setValuer(ds.getValue(baropress_databaseread.class).getValuer());
            uinfor.setTimestamp(ds.getValue(baropress_databaseread.class).getTimestamp());

            // convertTimestamp(uinfor.getTimestamp());
            Log.d(TAG, " Getting Children Time Stamp(Motion) : - "+convertTimestamp(uinfor.getTimestamp()));
            Log.d(TAG, " Getting Children Values(Motion) : - "+uinfor.getValuer());
            array.add(" Time                : " + convertTimestamp(uinfor.getTimestamp()));
            array.add("Readings         : " + uinfor.getValuer());

            //ListView to display the data.
            motionviewr = (ListView)findViewById(R.id.listview_motion);
            ArrayAdapter adapter = new ArrayAdapter(Motionsense.this,android.R.layout.simple_list_item_1,array);
            motionviewr.setAdapter(adapter);


        }



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
    private String convertTimestamp(String timestamp){

        long yourSeconds = Long.valueOf(timestamp);
        Date mDate = new Date(yourSeconds * 1000);
        DateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");
        return df.format(mDate);
    }


}
