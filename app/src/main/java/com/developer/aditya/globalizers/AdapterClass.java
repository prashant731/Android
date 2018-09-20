package com.developer.aditya.globalizers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterClass extends ArrayAdapter<MyName> {

    Context context;

    public AdapterClass(Activity context , ArrayList<MyName> wword)
    {
        super(context,0,wword);
        this.context=context;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem==null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_student, parent, false);
        }
        MyName myString = getItem(position);

        TextView name = (TextView)listItem.findViewById(R.id.studName);
        name.setText(myString.getName());

        TextView skills = (TextView)listItem.findViewById(R.id.studentMobile);
        skills.setText(myString.getNumber());

        return  listItem;
    }
}
