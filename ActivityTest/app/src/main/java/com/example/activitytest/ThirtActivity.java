package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThirtActivity extends  BaseActivity {//AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirt);

        Button button  = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInfoBack();
            }
        });
    }

    @Override
    public void onBackPressed() {
        setInfoBack();
    }

    private void setInfoBack(){
        Intent intent  = new Intent();
        intent.putExtra("return_data", "MSG:I'm From 3th Activity！~~~~");
        setResult(3, intent);
        finish();
    }
}
