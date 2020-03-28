package com.example.databasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String Creat_Table_Book = "create table Book ( " +
            " id integer primary key autoincrement, " +
            " author ntext, " +
            " price real, " +
            " pages integer, " +
            " name ntext, " +
            " category int);";


    public  static  final String Create_Table_Category = "create table category(" +
            "id integer primary key autoincrement, " +
            "category_name nvarchar(100)," +
            "category_code integer);";


    private Context mContext;

    public MyDatabaseHelper(@Nullable Context context,
                            @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory,
                            int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    /**
     *
    * */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Creat_Table_Book);

        Log.d("db", "onCreate: ");
        Toast.makeText(mContext, "创建数据库成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //版本2：
        //db.execSQL("alter table book add column pulisher nvarchar(100)");
        //版本3:
        //db.execSQL(Create_Table_Category);

        Log.d("db", "onUpgrade: " + "数据库升级成功，版本：" + newVersion);
        Toast.makeText(mContext, "数据库升级成功，版本：" + newVersion, Toast.LENGTH_SHORT).show();
    }
}
