package com.developer.aditya.globalizers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterClassStats extends ArrayAdapter<MyStats> {

        Context context;

public AdapterClassStats(Activity context , ArrayList<MyStats> wword)
        {
        super(context,0,wword);
        this.context=context;
        }

public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem==null) {
        listItem = LayoutInflater.from(getContext()).inflate(R.layout.stats_prototype_cell, parent, false);
        }
        MyStats myString = getItem(position);

        TextView name = (TextView)listItem.findViewById(R.id.stats_name);
        name.setText(myString.getName());

        TextView code = (TextView)listItem.findViewById(R.id.stats_code);
        code.setText(myString.getCode());

        TextView date = (TextView)listItem.findViewById(R.id.stats_date);
        date.setText(myString.getDate());
        return  listItem;
        }
}
