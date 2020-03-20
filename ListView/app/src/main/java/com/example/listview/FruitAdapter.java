package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;

    public FruitAdapter(@NonNull Context context, int textViewResourceId, List<Fruit> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    /**
     * 每个子项被滚动到屏幕时调用
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Fruit fruit = getItem(position);
/*        View view = LayoutInflater.from(getContext()) //获得LayoutInflater对象
                .inflate(resourceId ,parent ,false); //根据布局id把这个布局加载成一个View并返回*/

        /****************************优化ListView运行效率********************************************************
         1.getView()方法中，每次都将布局重新加载了一遍，当ListView快速滚动的时候，这就会成为性能的瓶颈。
           convertView参数，这个参数用于将之前加载好的布局进行缓存，以便之后可以进行重用。

         2.调用View 的 findViewById()方法来获取一次控件的实例。借助一个ViewHolder来对这部分性能进行优化
       *********************************************************************************************************/

        View view = null;
        ViewHolder viewHolder;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()) //获得LayoutInflater对象
                    .inflate(resourceId, parent, false); //根据布局id把这个布局加载成一个View并返回

            viewHolder = new ViewHolder();
            viewHolder.fruitImage  = view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);  //将ViewHolder存储在View中

        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); //从View中获取ViewHolder
        }

        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());

        return view;
    }

    class ViewHolder{
        ImageView fruitImage;
        TextView fruitName;
    }
}

