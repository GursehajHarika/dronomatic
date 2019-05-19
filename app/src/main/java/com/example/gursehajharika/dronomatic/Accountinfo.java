package com.example.gursehajharika.dronomatic;


import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Accountinfo extends AppCompatActivity {

    public Button home;
    public MenuView.ItemView quit;
    public EditText fullnam, emailadd,oldpasswrd,newpassrd,passwordneew;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountinfo);

        setTitle( "Account Info");

        userinfogetter();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        home = findViewById(R.id.button2);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void userinfogetter(){

        fullnam = (EditText)findViewById(R.id.fullname);
        emailadd = (EditText)findViewById(R.id.emailler);
        oldpasswrd = (EditText)findViewById(R.id.opaswrd);
        newpassrd = (EditText)findViewById(R.id.newpas);
        passwordneew = (EditText)findViewById(R.id.newpasswordET);


        fullnam.setText(fulna);
        emailadd.setText(emailr);
        oldpasswrd.setText(older);
        newpassrd.setText(prod);
        passwordneew.setText(newpass);

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

        fullnam.setText(uinfo.getEmail());
        emailadd.setText(uinfo.getName());
        oldpasswrd.setText(uinfo.getPassword());
        newpassrd.setText(uinfo.getProductID());

    }


    //Test Ends here

    private void toastmessage(String message){
        Toast.makeText(Accountinfo.this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



}
