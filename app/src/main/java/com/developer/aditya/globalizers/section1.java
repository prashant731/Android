package com.developer.aditya.globalizers;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class section1 extends Fragment {

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if (view != null) {
//            if ((ViewGroup)view.getParent() != null)
//                ((ViewGroup)view.getParent()).removeView(view);
//            return view;
//        }
        view = inflater.inflate(R.layout.fragment_section1, container, false);
      //  onSaveInstanceState(savedInstanceState);
        //setRetainInstance(true);
       // getFragmentManager().beginTransaction().
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's state here
    }


}
