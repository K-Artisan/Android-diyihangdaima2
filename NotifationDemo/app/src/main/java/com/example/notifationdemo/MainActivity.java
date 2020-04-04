package com.example.notifationdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initNotificationChannel();

        //发送通知(< Andriod 8.0)
        Button btn_send_notification = findViewById(R.id.btn_send_notification);
        btn_send_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                NotificationCompat.Builder builder =  new NotificationCompat.Builder(MainActivity.this);

                //点击通知的响应动作
                Intent intent = new Intent(MainActivity.this, NotifationAtivity.class);
                intent.putExtra("extData_notifi_xiao-v26", "发送通知(< Andriod 8.0)时的额外信息");
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                Notification notification = builder
                        .setContentTitle("通知标题")
                        .setContentText("通知内容")
                        .setSmallIcon(R.mipmap.ic_launcher) //小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round)) //大图标
                        .setContentIntent(pendingIntent)//点击通知的响应动作
                        .setAutoCancel(true) //设置点击通知后自动删除通知
                        .build();

                //发送通知：
                //第一个参数：是通知的Id，可用此id进行手动关闭
                notificationManager.notify(AppConstant.NotificationId_Goto_NotifationActivity, notification);

            }
        });

        //发送通知:Chat
        Button btn_send_notification_chat = findViewById(R.id.btn_send_notification_chat);
        btn_send_notification_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                //点击通知的响应动作
                Intent intent = new Intent(MainActivity.this, NotifationAtivity.class);
                intent.putExtra("extData_notifi_Chat", "送通知:Chat的额外信息");
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                Notification notification = new NotificationCompat.Builder(MainActivity.this, AppConstant.ChannelId_Chat)
                        .setContentTitle("收到一条聊天消息")
                        .setContentText("今天中午吃什么？")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.notif_ico_round) //小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.notif_ico)) //大图标
                        .setAutoCancel(true) //设置点击通知后自动删除通知
                        .setContentIntent(pendingIntent)//点击通知的响应动作
                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg"))) //通知声音
                        .setVibrate(new long[]{0, 1000,2000, 3000 }) //振动，频率：静止，振动1秒，静止2秒，振动3秒 /*得添加权限：android.permission.VIBRATE*/
                        .setLights(Color.RED, 1000, 3000) /*亮1秒， 暗3秒*/
                        //.setDefaults(NotificationCompat.DEFAULT_ALL) //使用当前手机环境的铃声、如何振动
                        /*
                        setStyle() 构建富文本内容：长文字，图片
                         */
                        // .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.notif_ico_round)))
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("这是一个很长很长的文本，我要全部显示，而不要默认截断！" +
                                "这是一个很长很长的文本，我要全部显示，而不要默认截断！" +
                                "这是一个很长很长的文本，我要全部显示，而不要默认截断！" +
                                "这是一个很长很长的文本，我要全部显示，而不要默认截断！" +
                                "这是一个很长很长的文本，我要全部显示，而不要默认截断！" +
                                "这是一个很长很长的文本，我要全部显示，而不要默认截断！"))
                        .setPriority(NotificationCompat.PRIORITY_MAX) //设置优先级
                        .build();
                manager.notify(AppConstant.NotificationId_Chat, notification);

            }
        });

        //发送通知:Subcribe
        Button btn_send_notification_subcribe = findViewById(R.id.btn_send_notification_subcribe);
        btn_send_notification_subcribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Intent intent = new Intent(MainActivity.this, NotifationAtivity.class);
                intent.putExtra("extData_notifi_Subcribe", "发送通知:Subcribe的额外信息");
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                Notification notification = new NotificationCompat.Builder(MainActivity.this, AppConstant.ChannelId_Subcribe)
                        .setContentTitle("收到一条订阅消息")
                        .setContentText("地铁沿线30万商铺抢购中！")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.notif_ico_round) //小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.notif_ico)) //大图标
                        .setAutoCancel(true) //设置点击通知后自动删除通知
                        .setContentIntent(pendingIntent)//点击通知的响应动作
                        .build();

                manager.notify(AppConstant.NotificationId_Subcribe, notification);
            }
        });
    }

    /**
     * 因为低版本的手机系统并没有通知渠道这个功能，不做系统版本检查的话会在低版本手机上造成崩溃。
     * */
    private void initNotificationChannel() {
        // targetSdkVersion >=26
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String channelName = "聊天消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(AppConstant.ChannelId_Chat, channelName, importance);

/*            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(AppConstant.ChannelId_Subcribe, channelName, importance);*/
            initNotificationChannelForSubcribe();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initNotificationChannelForSubcribe(){
        String channelName = "订阅消息";
        String description = "订阅消息，接收一些日常刚兴趣的资讯！！！";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        NotificationChannel channel = new NotificationChannel(AppConstant.ChannelId_Subcribe, channelName, importance);
        channel.enableVibration(true);
        channel.setVibrationPattern(new long[]{0, 1000,2000, 3000, 5000, 2000,2000,1000 } );
        channel.enableLights(true);
        channel.setLightColor(Color.GREEN);
        channel.setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")); //通知声音

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    /**
     * 创建 NotificationChannel
     * */
    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {

        /*-------------------------------------------------------------------
         创建一个通知渠道至少需要渠道ID、渠道名称以及重要等级这三个参数，
         其中
         渠道ID:  可以随便定义，只要保证全局唯一性就可以。
         渠道名称:是给用户看的，需要能够表达清楚这个渠道的用途。
         重要等级:重要等级的不同则会决定通知的不同行为，当然这里只是初始状态下的重要等级，
                 用户可以随时手动更改某个渠道的重要等级，App是无法干预的
         */
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }
}

