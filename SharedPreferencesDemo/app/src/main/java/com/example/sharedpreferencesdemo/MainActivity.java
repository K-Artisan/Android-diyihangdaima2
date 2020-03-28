package com.example.sharedpreferencesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("my_shared_pres", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", "Kevin");
                editor.putInt("age",29);
                editor.putBoolean("married", false);
                editor.apply();
            }
        });

        Button btn_restore = findViewById(R.id.btn_restore);
        btn_restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("my_shared_pres", MODE_PRIVATE);
                String name = sharedPreferences.getString("name", "");
                int age = sharedPreferences.getInt("age",0);
                boolean married = sharedPreferences.getBoolean("married", false);

                Toast.makeText(MainActivity.this,
                        "name:" +name +"age:" + age + "married:" + String.valueOf(married),
                        Toast.LENGTH_SHORT )
                        .show();

            }
        });

    }
}
