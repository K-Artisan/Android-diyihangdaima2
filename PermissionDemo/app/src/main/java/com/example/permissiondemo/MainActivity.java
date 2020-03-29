package com.example.permissiondemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_callphone = findViewById(R.id.btn_callphone);
        btn_callphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //判定是否已经授权
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    //申请授权，弹出授权申请框
                    ActivityCompat.requestPermissions(MainActivity.this
                            , new String[]{Manifest.permission.CALL_PHONE}
                            , 1);
                } else {
                    callPhone();
                }
            }
        });
    }

    /**
     * 拨打电话
     * */
    private void callPhone() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    /**
     * 授权回调函数
     * 使用requestCode区别请求
     * */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    callPhone();
                }else {
                    Toast.makeText(MainActivity.this, "拨打电话权限未授权", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            default:
                break;
        }
    }
}
