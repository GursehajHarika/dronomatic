
package com.example.gursehajharika.dronomatic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class Baropress_customAdapter extends ArrayAdapter<String> {


    public Baropress_customAdapter(@NonNull Context context, String [] TIMER) {
        super(context, R.layout.custom_row,TIMER);

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row,parent,false);


        String singleTimer = getItem(position);
        TextView listview_timeview = (TextView) customView.findViewById(R.id.listview_timeview);




        listview_timeview.setText(singleTimer);
        return customView;
    }
}

