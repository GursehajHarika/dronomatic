package com.example.gursehajharika.dronomatic;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Customlv  extends ArrayAdapter<String>{


    private String [] images;
    private String [] TIMER;
    private String[]  DESCRIPTION;
    private Activity Context;

    public Customlv(Activity Context, String [] images, String [] TIMER, String [] DESCRIPTION) {
        super(Context,R.layout.activity_baropress,images);

        this.Context = Context;
        this.images = images;
        this.TIMER = TIMER;
        this.DESCRIPTION = DESCRIPTION;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    class ViewHolder{

        TextView tv;
        TextView tv2;
        ImageView iv3;
        ViewHolder(View v)
        {
            //tv = v.findViewById(R.id.);

        }

    }
}
