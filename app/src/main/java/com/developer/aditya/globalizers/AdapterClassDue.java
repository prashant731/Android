package com.developer.aditya.globalizers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterClassDue extends ArrayAdapter<MyDue> {

    Context context;

    public AdapterClassDue(Activity context , ArrayList<MyDue> wword)
    {
        super(context,0,wword);
        this.context=context;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem==null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.cardview_item, parent, false);
        }
        MyDue myString = getItem(position);

        TextView name = (TextView)listItem.findViewById(R.id.due_name);
        name.setText(myString.getName());

        TextView skills = (TextView)listItem.findViewById(R.id.due_date);
        skills.setText(myString.getDate());

        TextView course = (TextView)listItem.findViewById(R.id.due_course);
        course.setText(myString.getCourse());
        return  listItem;
    }
}