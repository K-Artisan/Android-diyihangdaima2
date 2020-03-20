package com.example.activitytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends BaseActivity {//AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("return_data", "MSG: I'm From SeconActivity~!~~~");
                setResult(2, intent);
                finish();
            }
        });


/*        Intent intent = getIntent();
        String data1 = intent.getStringExtra("param1");
        String data2 = intent.getStringExtra("param2");
        Log.i("param1", data1);
        Log.i("param1", data2);
        Toast.makeText(SecondActivity.this, data1 + "/" + data2,Toast.LENGTH_LONG).show();
        */

        Intent intent = getIntent();
        String data = intent.getStringExtra("key_ExtData");
        Log.i("key_ExtData", data);
        Toast.makeText(SecondActivity.this, data, Toast.LENGTH_LONG).show();
    }

    public static void actionStart(Context context, String data1, String data2) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy", "第二个Activity被销毁");
    }

}

