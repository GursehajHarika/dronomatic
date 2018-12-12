package com.example.gursehajharika.dronomatic;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class contactuserror extends AppCompatActivity {


    public ImageButton callbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactuserror);


     callbt=(ImageButton)findViewById(R.id.callbutton);
     callbt.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

           //  Uri number = Uri.parse("+4169704414");
          //   Intent callIntent = new Intent(Intent.ACTION_DIAL, number );

          //   startActivity(callIntent);

            String number = "4169704414";
             Intent callIntent = new Intent(Intent.ACTION_DIAL);
             callIntent.setData(Uri.parse("tel:"+Uri.encode(number.trim())));
             callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             startActivity(callIntent);



         }
     });





    }

}
