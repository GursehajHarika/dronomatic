package com.example.gursehajharika.dronomatic;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginwelcomeadmin extends AppCompatActivity {

    public TextView usernm;
    private static int SPLASH_TIME_OUT = 1500 ;

    public Button home;
    public MenuView.ItemView quit;
    public EditText emailadd,oldpasswrd,newpassrd,passwordneew;

    String fulna = null;
    String emailr = null;
    String older = null;
    String prod = null;
    String newpass = null;

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mRef;
    private String userID;


    private static final String TAG = "Accountinfo";


    public void userinfogetter(){
        usernm = findViewById(R.id.textView2);
       // fullnam = (EditText)findViewById(R.id.fullname);
        emailadd = (EditText)findViewById(R.id.emailler);
        oldpasswrd = (EditText)findViewById(R.id.opaswrd);
        newpassrd = (EditText)findViewById(R.id.newpas);
        passwordneew = (EditText)findViewById(R.id.newpasswordET);


        usernm.setText(fulna);
      //  emailadd.setText(emailr);
      //  oldpasswrd.setText(older);
      // newpassrd.setText(prod);
      //  passwordneew.setText(newpass);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        mRef = mFirebaseDatabase.getReference("user").child(userID).child("userinfo");

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


    //Database userInfo Test Starts Here

    private void shower(DataSnapshot dataSnapshot) {


        Userinformation uinfo = new Userinformation(emailr,fulna,older,prod,userID);
        uinfo.setEmail(dataSnapshot.getValue(Userinformation.class).getEmail());
        Log.d(TAG," Email Info " + uinfo.getEmail());
        uinfo.setName(dataSnapshot.getValue(Userinformation.class).getName());
        Log.d(TAG," Name Info " + uinfo.getName());
        uinfo.setPassword(dataSnapshot.getValue(Userinformation.class).getPassword());
        Log.d(TAG," Password Info " + uinfo.getPassword());
        uinfo.setProductID(dataSnapshot.getValue(Userinformation.class).getProductID());
        Log.d(TAG," ProID Info " + uinfo.getProductID());
        uinfo.setUserID(dataSnapshot.getValue(Userinformation.class).getUserID());
        Log.d(TAG," User Info " + uinfo.getUserID());
        usernm.setText(uinfo.getEmail());

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginwelcomeadmin);

        setTitle("");
        //used the string defined in HomeActivity
        usernm = findViewById(R.id.textView2);
        userinfogetter();
       // usernm.setText(""+ getIntent().getStringExtra("User") );

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run(){

                String usersession = usernm.getText().toString();
                Intent homeIntent = new Intent(loginwelcomeadmin.this, homepage.class);
                homeIntent.putExtra("session",usersession);
                startActivity(homeIntent);


                finish();

            }
        },SPLASH_TIME_OUT);
    }

}
