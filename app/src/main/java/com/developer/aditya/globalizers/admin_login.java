package com.developer.aditya.globalizers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class admin_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
    }

    public void myAuthCheckAdmin(View view) {
        EditText username = (EditText) findViewById(R.id.adminUsername);
        EditText password = (EditText) findViewById(R.id.adminPassword);
        try {
            username.getText();
            password.getText();
            Log.e("username", "myAuthCheckAdmin: "+username.getText().toString());
            Log.e("password", "myAuthCheckAdmin: "+password.getText().toString() );
            if (username.getText().toString().equals("admin@globalizers.com") || username.getText().toString().equals("prashant@globalizers.com")) {
                if (password.getText().toString().equals("Globalizers10")) {
//                    TextView adminName = (TextView)findViewById(R.id.textViewName);
//                    adminName.setText(username.getText().toString());
                    Intent intent = new Intent(admin_login.this, adminPage.class);
                    startActivity(intent);
                } else {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.mytoast, (ViewGroup) findViewById(R.id.toast_layout_root));
                    Toast toast = new Toast(this);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();
                    Log.e("Pssword", "myAuthCheckAdmin: "+password.toString() );

                }

            } else {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.mytoast, (ViewGroup) findViewById(R.id.toast_layout_root));
                Toast toast = new Toast(this);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
                Log.e("username", "myAuthCheckAdmin: "+username.toString());

            }
        }
        catch (Exception e)
        {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.mytoast, (ViewGroup) findViewById(R.id.toast_layout_root));
            Toast toast = new Toast(this);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            TextView textView = (TextView) layout.findViewById(R.id.txtmessage);
            textView.setText("Wrong Username");
            Log.e("Exception", "myAuthCheckAdmin: "+e.getMessage());
            toast.show();
        }
    }
}
