package com.developer.aditya.globalizers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterClassNew extends ArrayAdapter<MyStudent> {

    Context context;

    public AdapterClassNew(Activity context , ArrayList<MyStudent> wword)
    {
        super(context,0,wword);
        this.context=context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem==null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_student_info, parent, false);
        }
        MyStudent myString = getItem(position);

        TextView checktime = (TextView)listItem.findViewById(R.id.checktime);
        checktime.setText(myString.getTimeStamp());

        return  listItem;
    }
}

