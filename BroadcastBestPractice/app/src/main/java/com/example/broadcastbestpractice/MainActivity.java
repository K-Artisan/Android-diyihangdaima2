package com.example.broadcastbestpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_force_offline = findViewById(R.id.btn_force_offline);
        btn_force_offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //发送强制下线广播
                Intent intent = new Intent(AppConstanct.BROADCAST_FORCE_OFFLINE);
                sendBroadcast(intent);
            }
        });
    }
}
