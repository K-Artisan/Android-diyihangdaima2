package com.example.localbroadcastdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;
    private final String localBrodcastName = "com.example.localbroadcastdemo.LOCAL_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(localBrodcastName);
        localReceiver = new LocalReceiver();
        //注册本地广播接收器
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);

        Button btn_send_loacl_broadcast = findViewById(R.id.btn_send_loacl_broadcast);
        btn_send_loacl_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(localBrodcastName);
                //发送本地广播
                localBroadcastManager.sendBroadcast(intent);
            }
        });
    }
}
