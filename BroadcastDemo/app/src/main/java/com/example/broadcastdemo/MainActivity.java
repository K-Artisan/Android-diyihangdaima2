package com.example.broadcastdemo;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NetWorkChangeReciever netWorkChangeReciever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        //当网络变化时系统发送该广播
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        //注册广播接收器
        netWorkChangeReciever = new NetWorkChangeReciever();
        registerReceiver(netWorkChangeReciever, intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //注销广播接收器
        unregisterReceiver(netWorkChangeReciever);
    }

    class NetWorkChangeReciever extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //Toast.makeText(context, "network changes", Toast.LENGTH_SHORT).show();
            Log.d("network", "network changes");

            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isAvailable()){
                Toast.makeText(context, "网络已连接", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "网络不可用", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
