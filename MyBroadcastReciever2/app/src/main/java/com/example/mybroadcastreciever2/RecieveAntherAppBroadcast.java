package com.example.mybroadcastreciever2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class RecieveAntherAppBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"mybroadcastreciever2:收到其它程序的广播(com.example.mybroadcastDemo.MY_BROADCAST)", Toast.LENGTH_SHORT).show();
    }
}
