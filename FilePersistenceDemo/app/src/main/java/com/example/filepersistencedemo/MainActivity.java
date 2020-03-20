package com.example.filepersistencedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        String text = loadTextFromFile();
        if (!TextUtils.isEmpty(text)){ //Returns true if the string is null or 0-length.
            editText.setText(text);
            editText.setSelection(text.length()); //setSelection()方法将输入光标移动到文本的末尾位置以便于继续输入，
            Toast.makeText(MainActivity.this,"从文件加载文本成功:"+ text, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        String text = editText.getText().toString();
        saveToFile(text); // 按Back键，关闭程序，保存文本到文件
    }

    private void saveToFile(String inputText){
        FileOutputStream fos =null;
        BufferedWriter writer = null;

        try {
            fos = openFileOutput("myFile", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write(inputText);

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (null != writer){
                    writer.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }

    private String loadTextFromFile(){
        FileInputStream fis = null;
        BufferedReader reader = null;
        StringBuilder context = new StringBuilder();

        try {
            fis = openFileInput("myFile");
            reader = new BufferedReader(new InputStreamReader(fis));
            String line = "";
            while ((line = reader.readLine()) != null ){
                context.append(line);
            }

        }catch (Exception e){
            e.printStackTrace();
            //如果myFile不存在会发生异常
            Toast.makeText(MainActivity.this,"读取文件myFile出现异常", Toast.LENGTH_LONG).show();
        }finally {
            try {
                if (null != reader){
                    reader.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return  context.toString();
    }
}
