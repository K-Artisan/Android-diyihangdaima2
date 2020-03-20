package com.example.activitytest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {//AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

       if (savedInstanceState != null){
            String tempData= savedInstanceState.getString("key_tempData");
            Log.d("savedInstanceState",tempData);
        }

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this, "Button被点击", Toast.LENGTH_LONG)
                        .show();

            /*  Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            */

/*            Intent intent2 = new Intent("com.example.activitytest.Xyz_Shuibian");
            intent2.addCategory("com.example.activitytest.Shuibian_category");
            startActivity(intent2);*/

     /*        Intent intent3 = new Intent(Intent.ACTION_VIEW);
             intent3.setData(Uri.parse("http://www.baidu.com"));
             startActivity(intent3);*/

/*                Intent intent4 = new Intent(Intent.ACTION_DIAL);
                intent4.setData(Uri.parse("tel:10086"));
                startActivity(intent4);*/



/*               Intent intent5 = new Intent(FirstActivity.this,SecondActivity.class);
                intent5.putExtra("key_ExtData", "Hello，I'm from FirstActivity！！！");
                startActivity(intent5);*/

                // SecondActivity.actionStart(FirstActivity.this,"参数1", "参数2");

                Intent intent6 = new Intent(FirstActivity.this, SecondActivity.class);
                intent6.putExtra("key_ExtData", "Hello，I'm from FirstActivity！！！");
                startActivityForResult(intent6, 2);

            }
        });

        Button button3 = findViewById(R.id.button1_2);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, ThirtActivity.class);
                intent.putExtra("key_ExtData", "Hello，I'm from FirstActivity！！！");
                startActivityForResult(intent, 3);
            }
        });

        Button btn_AlertDialog = findViewById(R.id.btn_AlertDialog);
        btn_AlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog= new AlertDialog.Builder (FirstActivity.this);
                dialog.setTitle("This is Dialog");
                dialog.setMessage("Something important.");
                dialog.setCancelable(false); //true：表示点弹框以外的位置可关闭弹框
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(FirstActivity.this, "Ok", Toast.LENGTH_LONG)
                                .show();
                    }
                });

                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(FirstActivity.this, "Cancel", Toast.LENGTH_LONG)
                                .show();
                    }
                });

                dialog.show();
            }
        });

        Button btn_ProgressDialog = findViewById(R.id.btn_ProgressDialog);
        btn_ProgressDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ProgressDialog 已经被弃用
                ProgressDialog dialog = new ProgressDialog(FirstActivity.this);
                dialog.setTitle("标题");
                dialog.setMessage("正在加载...");
                dialog.setCancelable(true);
                dialog.show();
            }
        });

        //final ProgressBar progressBar = findViewById(R.id.pg_progressBar);
        Button btn_ProgressBar = findViewById(R.id.btn_ProgressBar);
        btn_ProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressBar progressBar = findViewById(R.id.pg_progressBar);
                progressBar.setVisibility(View.VISIBLE);
                int progress = progressBar.getProgress();

                if (progress >= 100){
                    progress = 0;
                }else {
                    progress = progress + 10;
                }
                progressBar.setProgress(progress);
                Log.d("ProgressBar", "progress:" + progress);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.add_item:
                Toast.makeText(this, "添加", Toast.LENGTH_LONG).show();
                Log.d("itemClick", "添加");
                break;
            case R.id.remove_item:
                Toast.makeText(this, "删除", Toast.LENGTH_LONG).show();
                Log.d("itemClick", "删除");
                break;
            case R.id.quitApp:
                Toast.makeText(this, "退出App", Toast.LENGTH_LONG).show();
                Log.d("itemClick", "退出App");
                ActivityCollector.finishAll();
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
            default:
                Toast.makeText(this, "无效点击", Toast.LENGTH_LONG).show();
                Log.d("itemClick", "无效点击");
                break;

        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 2: {
                String string = data.getStringExtra("return_data");
                Log.d("onActivityResult", string);
                Toast.makeText(this,string, Toast.LENGTH_LONG).show();
                break;
            }
            case 3: {
                String string = data.getStringExtra("return_data");
                Log.d("onActivityResult", string);
                Toast.makeText(this,string, Toast.LENGTH_LONG).show();

                break;
            }
            default:
                Log.e("onActivityResult", "no such requestCode:" + requestCode);
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        String tempData = "Activity回收前保存的数据";
        outState.putString("key_tempData", tempData);
        Log.d("savedInstanceState", tempData);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy", "第一个Activity被销毁");
    }


}