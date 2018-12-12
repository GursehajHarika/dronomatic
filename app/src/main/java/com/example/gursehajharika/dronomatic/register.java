package com.example.gursehajharika.dronomatic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {

    private Button alreadyre;
    private Button register;
    private EditText fname;
    private EditText passwrd;
    private EditText lname;
    private EditText emailaddress;
    private EditText proID;
    private static final String TAG = "register";
private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth firebaseAuth;
    private String userID;


    DatabaseReference dbr;

    String firstname;
    String password;
    String lastname;
    String emailaddres;
    String productstring;



    public void backtostart(){
        alreadyre = findViewById(R.id.alreadyreg);
        alreadyre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void reger(){
        register= findViewById(R.id.reg);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creator();
                auther();
            }
        });
    }

    public void sett(){



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // DatabaseReference name = database.getReference("Firstname");
        // DatabaseReference passw = database.getReference("password");
        // DatabaseReference lname = database.getReference("Lastname");
        // DatabaseReference product = database.getReference("ProductID");
        // DatabaseReference emailaddr = database.getReference("email");


        //FirebaseUser user = mAuth.getCurrentUser();
       // userID = user.getUid();
        Userinformation userinformation = new Userinformation(firstname,emailaddres,password,productstring,userID);
        dbr.child("user").child(userID).setValue(userinformation);


    }

   /* public void creator(){

    fname = findViewById(R.id.editText2);
    passwrd = findViewById(R.id.editText7);
    lname = findViewById(R.id.editText4);
    emailaddress = (EditText)findViewById(R.id.editText5);
    proID = (EditText)findViewById(R.id.editText6);


    //Converts View Values to String.
    firstname  = fname.getText().toString();
    password = passwrd.getText().toString();
    lastname = lname.getText().toString();
    emailaddres = emailaddress.getText().toString();
    productstring = proID.getText().toString();


    //Writing to the Database.

    //Creates New user.
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        dbr = mFirebaseDatabase.getReference();
     //   FirebaseUser user = mAuth.getCurrentUser();




        auther(emailaddres,password);
        sett();


    }  */

 public void auther(){

     fname = findViewById(R.id.editText2);
     passwrd = findViewById(R.id.editText7);
     lname = findViewById(R.id.editText4);
     emailaddress = (EditText)findViewById(R.id.editText5);
     proID = (EditText)findViewById(R.id.editText6);


     //Converts View Values to String.
     firstname  = fname.getText().toString();
     password = passwrd.getText().toString();
     lastname = lname.getText().toString();
     emailaddres = emailaddress.getText().toString();
     productstring = proID.getText().toString();


     //Writing to the Database.

     //Creates New user.
     mAuth = FirebaseAuth.getInstance();
     mFirebaseDatabase = FirebaseDatabase.getInstance();
     dbr = mFirebaseDatabase.getReference();
     //   FirebaseUser user = mAuth.getCurrentUser();






     mAuth.createUserWithEmailAndPassword(emailaddres, password)
             .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()) {
                         // Sign in success, update UI with the signed-in user's information
                         Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                         userID = user.getUid();
                       // sett();
                    Log.d(TAG,"Current User ID" + mAuth);
                     } else {
                         // If sign in fails, display a message to the user.
                         Log.w(TAG, "createUserWithEmail:failure", task.getException());
                         Toast.makeText(register.this, "Authentication failed.",
                                 Toast.LENGTH_SHORT).show();

                     }


                     FirebaseDatabase database = FirebaseDatabase.getInstance();
                     // DatabaseReference name = database.getReference("Firstname");
                     // DatabaseReference passw = database.getReference("password");
                     // DatabaseReference lname = database.getReference("Lastname");
                     // DatabaseReference product = database.getReference("ProductID");
                     // DatabaseReference emailaddr = database.getReference("email");

                   //  FirebaseUser user = mAuth.getCurrentUser();

                     Userinformation userinformation = new Userinformation(firstname,emailaddres,password,productstring,userID);
                     dbr.child("user").child(userID).setValue(userinformation);


                     Toast.makeText(register.this,"User has been created.",Toast.LENGTH_LONG).show();
                     finish();


                     // ...
                 }
             });
 }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        backtostart();
        reger();
    }
}
