package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitViewHolder> {

    private List<Fruit> fruits;

    static class FruitViewHolder extends RecyclerView.ViewHolder{
        View fruitView; //子项的最外层布局的实例
        ImageView fruitImage;
        TextView fruitName;

        /**
         * ViewHolder的构造函数中要传入一个View参数，
         * 这个参数通常就是RecyclerView子项的最外层布局，
         * 那么我们就可以通过findViewById()方法来获取到布局中的ImageView和TextView的实例
         * */
        public FruitViewHolder(@NonNull View itemView) {
            super(itemView);

            fruitView = itemView; //子项的最外层布局的实例
            fruitImage = itemView.findViewById(R.id.fruit_image);
            fruitName = itemView.findViewById(R.id.fruit_name);

        }
    }

    public FruitAdapter(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    @NonNull
    @Override
    public FruitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fruit_item, parent, false);
        final FruitViewHolder holder = new FruitViewHolder(view);

        //为子项的最外层布局注册点击事件：
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = fruits.get(position);
                Toast.makeText( v.getContext(), "点击子项View："+ fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        //为子项的图片注册点击事件：
        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = fruits.get(position);
                Toast.makeText(v.getContext(), "点击子项View中的ImageView："+ fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return  holder;
    }

    /**
     *用于对RecyclerView子项的数据进行赋值的，会在每个子项被滚动到屏幕内的时候执行
     * */
    @Override
    public void onBindViewHolder(@NonNull FruitViewHolder holder, int position) {
        Fruit fruit = fruits.get(position);

        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

}
