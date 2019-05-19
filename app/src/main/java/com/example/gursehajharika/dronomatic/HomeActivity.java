package com.example.gursehajharika.dronomatic;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomeActivity extends AppCompatActivity {

    //Define these before using buttons, textview and etc with differnet names.
    public Button register;
    public EditText email;
    public EditText password;
    public Button signin;
    private int counter = 3;
    private TextView tv;
    public MenuView.ItemView quit;
    public ToggleButton saver;
    private FirebaseAuth mAuth;
    private static int SPLASH_TIME_OUT = 1500 ;
    public CheckBox saveinfo;
    SharedPreferences sharedPref;


    private FirebaseAuth.AuthStateListener mAuthListener;
    public Button signout;

    private static final String TAG = "HomeActivity";


    //Not Using this for now -----------
    public void AuthenticationDB(){

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               FirebaseUser user = firebaseAuth.getCurrentUser();
               if (user !=null)
               {
                   Log.d(TAG,"onAuthStateChanged:signed_in" + user.getUid());
                   Toast.makeText(HomeActivity.this,"Succesfullly signed in with " + user.getUid(),Toast.LENGTH_SHORT).show();
                   String nameuser = email.getText().toString();
                   Intent sign = new Intent(HomeActivity.this, loginwelcomeadmin.class);
                   sign.putExtra("User", nameuser);
                   startActivity(sign);
               }
               else{
                   Log.d(TAG,"onAuthStateChanged:signed_out");
                   Toast.makeText(HomeActivity.this,"Succesfullly signed Out with ",Toast.LENGTH_SHORT).show();
               }
            }
        };

    }
//--------- till here.






    public void reg() {
        //linking definations with objects from xml
        signin = findViewById(R.id.signin);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(HomeActivity.this, register.class);
                startActivity(signup);
            }
        });
    }


    //Login Button

    public void goinghome() {
        //Defined all the view.
        mAuth = FirebaseAuth.getInstance();
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        saver = findViewById(R.id.toggleButton);
        signout = (Button)findViewById(R.id.signout);

        userinfosaver();


        Boolean checked = sharedPref.getBoolean("checkbox", false);
        String suser = sharedPref.getString("username", "no");
        String semail = sharedPref.getString("email", "no");
        // set the preference based on the saved data.

        Log.d(TAG," " + suser + " " + semail + "" + checked);

        email.setText(suser);
        saveinfo.setChecked(checked);
        password.setText(semail);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emails = email.getText().toString();
                String pass = password.getText().toString();
                authenti(emails,pass);
            }
        });

        saver.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {




            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    //Uses the TOggle buttons online option.
                    saver.setBackgroundColor(Color.parseColor("#C6FF00"));
                    toastmessage("App is now Online");
                    online();
                }
                else {
                    //Uses the toggle button's offline option
                    saver.setBackgroundResource(android.R.drawable.btn_default);
                    toastmessage("App is now Offline");
                    signin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String emails = email.getText().toString();
                            String pass = password.getText().toString();
                               authenti(emails,pass);
                        }
                    });
                }
            }
        });
    }





    //Shared Prefs to save User's login information.

    public void userinfosaver() {
        saveinfo = (CheckBox) findViewById(R.id.checkBox);
        if (saveinfo.isChecked()) {  // save the current user name for next time use.
            // save the current settings from UI, i.e. Username and E-mail.
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("username", email.getText().toString());
            editor.putString("email", password.getText().toString());
            editor.putBoolean("checkbox", saveinfo.isChecked());
            editor.commit(); // Save the preference.


        }
    }


    public void online(){

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emails = email.getText().toString();
                String pass = password.getText().toString();
                if(!emails.equals("") && !pass.equals("")){
                    //  mAuth.signInWithEmailAndPassword(emails,pass);
                    mAuth.signInWithEmailAndPassword(emails, pass)
                            .addOnCompleteListener(HomeActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        String nameuser = email.getText().toString();
                                        Intent sign = new Intent(HomeActivity.this, loginwelcomeadmin.class);
                                        sign.putExtra("User", nameuser);
                                        startActivity(sign);

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());

                                        Toast.makeText(HomeActivity.this,"Database Error, Try Again",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(HomeActivity.this, "Forgot to fill in the EMail and Passsord", Toast.LENGTH_SHORT).show();
                }
                signout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAuth.signOut();
                    }
                });

            }
        });
    }
    //Login authentication
    private void authenti(String userName, String userPassword) {
        if ((userName.equals("Gursehaj")) && (userPassword.equals("Gursehaj"))) {
            String nameuser = email.getText().toString();
            Intent sign = new Intent(HomeActivity.this, loginwelcomeadmin.class);
            sign.putExtra("User", nameuser);
            startActivity(sign);
        } else if (userName.equals("Shubham") && (userPassword.equals("$hubham"))) {
            //defined string to send data from one activity to another.
            String nameuser = email.getText().toString();
            Intent sign = new Intent(HomeActivity.this, loginwelcomeadmin.class);

           //inserting the string in the intent to go to another application
            sign.putExtra("User", nameuser);
            startActivity(sign);

        } else if (userName.equals("Arman") && (userPassword.equals("Velani"))) {
            String nameuser = email.getText().toString();
            Intent sign = new Intent(HomeActivity.this, loginwelcomeadmin.class);
            sign.putExtra("User", nameuser);
            startActivity(sign);
        }
        else if (userName.equals("User") && (userPassword.equals("user"))) {

            String nameuser = email.getText().toString();

            Intent sign = new Intent(HomeActivity.this, Loginwelcome.class);
            sign.putExtra("User", nameuser);
            startActivity(sign);
        }
        else {
            Context context = getApplicationContext();
            CharSequence text = "Invalid Email And password";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            counter--;
            attemps();
        }
    }

    //Login Attempts Counter
    public void attemps() {

        tv = findViewById(R.id.tvinfo);
        tv.setText("Number of attempts left : " + counter);

        if (counter == 0) {
            signin.setEnabled(false);

            new Handler().postDelayed(new Runnable(){

                @Override
                public void run(){
                    Intent homeIntent = new Intent(HomeActivity.this, contactuserror.class);
                    startActivity(homeIntent);

                    finish();

                }
            },SPLASH_TIME_OUT);


        } else {
            signin.setEnabled(true);
        }
    }


    //Main

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        sharedPref=this.getPreferences(Context.MODE_PRIVATE);
        //userinfosaver();
       // loaduserinfo();
        reg();
        goinghome();

    }
    //used to create a quit button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.homepagequit, menu);
        return true;
    }
    //used to creat a quit button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.quit:
                finish();
              //  finishAndRemoveTask();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        userinfosaver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        userinfosaver();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    private void toastmessage(String message){
        Toast.makeText(HomeActivity.this,message,Toast.LENGTH_SHORT).show();
    }
}
