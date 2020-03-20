package com.example.uilayoutdemos;

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

        Button btnlinearLayout = findViewById(R.id.btn_linearLayout);
        btnlinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LinearLayout.class);
                startActivity(intent);
            }
        });

        Button btnRelativeLayout= findViewById(R.id.btn_RelativeLayout);
        btnRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RelativeLayout.class);
                startActivity(intent);
            }
        });

        Button btn_FrameLayout= findViewById(R.id.btn_FrameLayout);
        btn_FrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FrameLayout.class);
                startActivity(intent);
            }
        });

        Button btn_PercentLayout= findViewById(R.id.btn_PercentLayout);
        btn_PercentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PercentLayout.class);
                startActivity(intent);
            }
        });

        Button btn_PercentRelativeLayout= findViewById(R.id.btn_PercentRelativeLayout);
        btn_PercentRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PercentRelativeLayout.class);
                startActivity(intent);
            }
        });
    }
}
