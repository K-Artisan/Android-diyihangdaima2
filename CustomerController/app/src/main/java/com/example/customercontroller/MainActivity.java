package com.example.customercontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_back_activity = findViewById(R.id.mbtn_back_activity);
        btn_back_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BackActivity.class);
                startActivity(intent);
            }
        });

        Button useBackActy = findViewById(R.id.ma_btn_useBackActy);
        useBackActy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UseBackActivity.class);
                startActivity(intent);
            }
        });
    }
}
