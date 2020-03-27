package com.example.broadcastbestpractice;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private ForceOfflineReceiver forceOfflineReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityManager.addActivity(this);
    }

    /**
     * 前台生存期 。活动在 onResume() 方法和 onPause()方法之间所经历的就是前台生存期。在前台生存期内，
     * 活动总是处于运行状态的，此时的活动是可以和用户进行交互的，我们平时看到和接触最多的也就是这个状态下的活动
     * */
    @Override
    protected void onResume() {
        super.onResume();

        /*--------------------------------------------------
         活动进入活动栈顶端，注册广播接收器
        * -------------------------------------------------*/
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AppConstanct.BROADCAST_FORCE_OFFLINE);
        forceOfflineReceiver = new ForceOfflineReceiver();
        registerReceiver(forceOfflineReceiver, intentFilter);
    }

    /**
     * 前台生存期 。活动在 onResume() 方法和 onPause()方法之间所经历的就是前台生存期。在前台生存期内，
     * 活动总是处于运行状态的，此时的活动是可以和用户进行交互的，我们平时看到和接触最多的也就是这个状态下的活动
     * */
    @Override
    protected void onPause() {
        super.onPause();

        /*--------------------------------------------------
         活动离开活动栈顶端，注销广播接收器
        * -------------------------------------------------*/
        if (forceOfflineReceiver != null){
            unregisterReceiver(forceOfflineReceiver);
            forceOfflineReceiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityManager.removeActivity(this);
    }
}
