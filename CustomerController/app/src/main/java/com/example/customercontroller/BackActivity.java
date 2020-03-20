package com.example.customercontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);

        Button btn_goBack = findViewById(R.id.ba_btn_goBack);
        Button btn_detail = findViewById(R.id.ba_btn_detail);

        btn_goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BackActivity.this, "btn_goBack", Toast.LENGTH_LONG).show();
                //Intent intent  = new Intent(BackActivity.this, MainActivity.class);
                Intent intent  = new Intent(BackActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BackActivity.this, "ba_btn_detail", Toast.LENGTH_LONG).show();
            }
        });

    }
}
