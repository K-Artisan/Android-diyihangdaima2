package com.arzirtime.iremoter.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arzirtime.iremoter.R;
import com.arzirtime.iremoter.datas.Device;
import com.arzirtime.support.util.LogUtil;
import com.arzirtime.support.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GasMonitorDeviceActivity extends AppCompatActivity {

    private static final String TAG ="GasMonitorDeviceActivity";
    private static final String KEY_INTENT_DEVICE ="keyIntent_device";

    //region AppCompatActivity 接口函数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_monitor_device);

        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.device_menu, menu); //创建系统菜单
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.toolbar_setting:
                ToastUtils.showToast(this, "配置设备");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.v(TAG, "onStart");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        LogUtil.v(TAG, "onPostResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.v(TAG, "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.v(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.v(TAG, "onDestroy");
    }

    //endregion

    public void initView(){
        Device device = (Device) getIntent().getSerializableExtra(KEY_INTENT_DEVICE);

        //折叠布局：CollapsingToolbarLayout
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle(device.getName());

        //使用Toolbar替换系统的ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); //启用HomeAsUp按钮，其id永远为：android.R.id.home
        }

        //图片
        ImageView deviceImageView = findViewById(R.id.device_imgview);
        Glide.with(this).load(R.drawable.img_iot_world)
                .placeholder(R.drawable.img_iot_world) //加载中显示d 图片
                //.error(R.drawable.device_default) //异常显示的图片
                //.fallback(R.drawable.device_default) //图片为null时显示的图片
                .centerCrop()
                .into(deviceImageView);

        //文字信息
        TextView textView = findViewById(R.id.tv_device_content);
        textView.setText(copyText(device.getName()));

        FloatingActionButton floatBtn = findViewById(R.id.floatBtn);
        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(GasMonitorDeviceActivity.this, "刷新");
            }
        });

    }

    /**
     * 提供一个静态方法，用于开启该Activity
     * */
    public static void startActivity(Context context, Device device){
        Intent intent = new Intent(context, GasMonitorDeviceActivity.class);
        intent.putExtra(KEY_INTENT_DEVICE, device);
        context.startActivity(intent);
    }


    private String copyText(String text){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            stringBuilder.append(text);
        }

        return stringBuilder.toString();
    }
}
