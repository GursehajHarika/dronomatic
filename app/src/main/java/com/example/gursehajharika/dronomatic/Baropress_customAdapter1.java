package com.example.gursehajharika.dronomatic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Baropress_customAdapter1 extends ArrayAdapter<String>{


    public Baropress_customAdapter1(@NonNull Context context, String [] DESCRIPTION) {
        super(context,R.layout.custom_row_rightside,DESCRIPTION);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row_rightside,parent,false);


        String singleTimer = getItem(position);
        TextView listview_readingview = (TextView) customView.findViewById(R.id.listview_readingview);



        listview_readingview.setText(singleTimer);
        return customView;
    }
}
