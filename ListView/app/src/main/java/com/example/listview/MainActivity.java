package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

/*    private List<String> listDatas = Arrays.asList("Apple", "Banana", "Orange", "Watermelon",
                "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango"
                , "Apple", "Banana"
                , "Orange", "Watermelon"
                , "Pear", "Grape",
                "Pineapple"
                , "Strawberry", "Cherry", "Mango");*/

    private List<Fruit> fruits = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        //ArrayAdapter的构造函数传入3个数据：
        // 1是Context 传入当前的上下文
        // ,2是ListView子项布局的ID
        // ,3是要适配的数据
        //android.R.layout.simple_list_item_1,listDatas是内置的
        ArrayAdapter<String> dapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,listDatas);
        ListView listView =  findViewById(R.id.m_listView1);
        listView.setAdapter(dapter);*/

        InitFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruits);
        final ListView listView = findViewById(R.id.m_listView1);
        listView.setAdapter(adapter);

        //ListView 子项点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Adapter adapter = parent.getAdapter();
                Fruit fruit = (Fruit) adapter.getItem(position);
                Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();

                //Fruit fruit = fruits.get(position);
                //Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_LONG).show();

/*              TextView fruitName =  view.findViewById(R.id.fruit_name);
                Toast.makeText(MainActivity.this, fruitName.getText().toString(), Toast.LENGTH_LONG).show();*/
            }
        });
    }

    private  void InitFruits(){

        for (int i= 0; i < 10 ; i++){
            Fruit taozi = new Fruit(R.drawable.taozi, "桃子");
            fruits.add(taozi);

            Fruit putao = new Fruit(R.drawable.putao, "葡萄");
            fruits.add(putao);

            Fruit huolongguo = new Fruit(R.drawable.huolongguo, "火龙果");
            fruits.add(huolongguo);

            Fruit lamei = new Fruit(R.drawable.lanmei, "蓝莓");
            fruits.add(lamei);

            Fruit xiguang = new Fruit(R.drawable.xigua, "西瓜");
            fruits.add(xiguang);
        }
    }
}
