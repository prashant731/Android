package com.developer.aditya.globalizers;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

        TextView checkdate = (TextView)listItem.findViewById(R.id.checkDate);
        checkdate.setText(myString.getDate());

        TextView checktime = (TextView)listItem.findViewById(R.id.checktime);
        Log.e("Timestamp : ", "getView: "+myString.getTimeStamp() );
        checktime.setText(myString.getTimeStamp());

        ImageView imageView  = (ImageView)listItem.findViewById(R.id.imgInOut);

        if(position%2==0)
        {
            checkdate.setTextColor(Color.parseColor("#00FF00"));
            imageView.setImageResource(R.drawable.incoming);
        }
        else if(position%2!=0)
        {
            checkdate.setTextColor(Color.parseColor("#FF0000"));
            imageView.setImageResource(R.drawable.uplink);
        }
        return  listItem;
    }
}

