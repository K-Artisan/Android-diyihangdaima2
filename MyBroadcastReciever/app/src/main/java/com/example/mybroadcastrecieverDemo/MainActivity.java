package com.example.mybroadcastrecieverDemo;

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

        Button btn_send_my_broadcast = findViewById(R.id.btn_send_my_broadcast);
        btn_send_my_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.mybroadcastDemo.MY_BROADCAST");
                //发起标准广播
                //sendBroadcast(intent);

                //发起有序广播,sendOrderedBroadcast()方法接收两个参数，
                // 第一个参数仍然是Intent，
                // 第二个参数是一个与权限相关的字符串，这里传入null就行
                sendOrderedBroadcast(intent, null);
            }
        });
    }
}
