package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruits = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitFruits();

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);

       /*
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); //横向布局
        recyclerView.setLayoutManager(linearLayoutManager);
        */

        //瀑布流布局：其中参数StaggeredGridLayoutManager.VERTICAL表示 从左往右依次排开，满行另起一行
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        FruitAdapter fruitAdapter = new FruitAdapter(fruits);
        recyclerView.setAdapter(fruitAdapter);
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
