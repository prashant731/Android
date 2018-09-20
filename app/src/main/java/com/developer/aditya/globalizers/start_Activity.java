package com.developer.aditya.globalizers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class start_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_);
    }

    public void openParentLogin(View view) {
        Intent intent = new Intent(start_Activity.this,parent_login.class);
        startActivity(intent);
    }

    public void openAdminLogin(View view) {
        Intent intent = new Intent(start_Activity.this,admin_login.class);
        startActivity(intent);
    }
}
