package com.example.customercontroller;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class UseBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_back);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide(); //隐藏标题栏
        }
    }
}
