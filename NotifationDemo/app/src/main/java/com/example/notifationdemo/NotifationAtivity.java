package com.example.notifationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class NotifationAtivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifation_ativity);

        Intent intent = getIntent();
        String extData_notifi_xiao_v26 = intent.getStringExtra("extData_notifi_xiao-v26");
        Log.d("extData", "extData: " + extData_notifi_xiao_v26);
        if (extData_notifi_xiao_v26!= null && !extData_notifi_xiao_v26.isEmpty()){
            Toast.makeText(this, "点击通知跳转，额外信息->" + extData_notifi_xiao_v26, Toast.LENGTH_LONG).show();
        }

        String extData_notifi_Chat = intent.getStringExtra("extData_notifi_Chat");
        Log.d("extData", "extData: " + extData_notifi_Chat);
        if (extData_notifi_Chat!= null && !extData_notifi_Chat.isEmpty()){
            Toast.makeText(this, "点击通知跳转，额外信息->" + extData_notifi_Chat, Toast.LENGTH_LONG).show();
        }
        String extData_notifi_Subcribe = intent.getStringExtra("extData_notifi_Subcribe");
        Log.d("extData", "extData: " + extData_notifi_Subcribe);
        if (extData_notifi_Subcribe!= null && !extData_notifi_Subcribe.isEmpty()){
            Toast.makeText(this, "点击通知跳转，额外信息->" + extData_notifi_Subcribe, Toast.LENGTH_LONG).show();
        }

    }
}
