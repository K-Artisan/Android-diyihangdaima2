package com.example.mybroadcastrecieverDemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBrodcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "收到自定义广播 ", Toast.LENGTH_LONG).show();
        abortBroadcast(); //截断广播，优先级的广播器将无法收到广播
    }
}
