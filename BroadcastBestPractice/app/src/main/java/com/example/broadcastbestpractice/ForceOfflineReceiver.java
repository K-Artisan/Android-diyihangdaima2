package com.example.broadcastbestpractice;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class ForceOfflineReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("提示");
        dialog.setMessage("您的账号被迫下线，请重新登录");
        dialog.setCancelable(false);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ActivityManager.finishAllActivity(); //销毁所有活动

                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);  //重启LoginActivity
            }
        });

        dialog.show();
    }
}
