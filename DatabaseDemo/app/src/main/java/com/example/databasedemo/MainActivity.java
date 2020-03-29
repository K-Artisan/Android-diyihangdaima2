package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHepler;
    private static final String DataBaseName = "BookStore.db";
    public static int DataBaseVersion = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取 SQLiteOpenHelper 实例，以便操作数据库
        dbHepler = new MyDatabaseHelper(this, DataBaseName, null, DataBaseVersion);

        Button btn_createDatabase = findViewById(R.id.btn_createDatabase);
        btn_createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHepler.getWritableDatabase(); //若无数据库，创建数据库
            }
        });

        /*
        Button btn_updateDatabase = findViewById(R.id.btn_updateDatabase);
        btn_updateDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将数据库版本设置为：2
                DataBaseVersion  = 2;
                //获取 SQLiteOpenHelper 实例
                if (dbHepler != null){
                    dbHepler.close();  //先关闭，再重新赋值，否则还是使用旧版本，抛出异常
                    dbHepler = null;

                    dbHepler = new MyDatabaseHelper( MainActivity.this, DataBaseName, null, DataBaseVersion);
                    dbHepler.getWritableDatabase(); //若无数据库，创建数据库
                }
            }
        });
         */


        //添加数据
        Button btn_insert = findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHepler.getWritableDatabase();
                ContentValues values = new ContentValues();

                // 第一条
                values.put("name", "西瓜");
                values.put("author", "Tom Bsa");
                values.put("pages", 230);
                values.put("price", 63.40);
                //第二个参数用于在未指定添加数据的情况下给某些可为空的列自动赋值NULL，一般我们用不到这个功能，直接传入null即可。
                db.insert("Book", null, values);

                // 第二条
                values.clear();
                values.put("name", "ABC SONG");
                values.put("author", "Jack DD");
                values.put("pages", 60);
                values.put("price", 50.40);
                values.put("pulisher", "李Lee");
                db.insert("Book", null, values);

                // 第三条
                values.clear();
                values.put("category_name", "Abc");
                values.put("category_code", 1);
                db.insert("Category", null, values);

                // 第四条
                values.clear();
                values.put("category_name", "自然科学");
                values.put("category_code", 2);
                db.insert("Category", null, values);
            }
        });

        //查询数据
        Button btn_query = findViewById(R.id.btn_query);
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHepler.getWritableDatabase();
                Cursor cursor = db.query("Book", new String[]{"name", "price"}
                        , "name=?", new String[]{"ABC SONG"}
                        , null, null
                        , null);

                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String price = cursor.getString(cursor.getColumnIndex("price"));

                    Toast.makeText(MainActivity.this, "name:" + name + " | price:" + price, Toast.LENGTH_SHORT).show();
                }

                cursor.close(); //记得关闭指针
            }
        });

        //更新数据
        Button btn_update = findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHepler.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 70);  //只更新标明的字段，其它字段值不会被修改
                db.update("Book", values, "name=?", new String[]{"ABC SONG"});
            }
        });

        //删除数据
        Button btn_delete = findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHepler.getWritableDatabase();
                db.delete("Book", "pages>?", new String[]{"100"});
            }
        });

        //使用SQL插入数据
        Button btn_insert_sql = findViewById(R.id.btn_insert_sql);
        btn_insert_sql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHepler.getWritableDatabase();
                db.execSQL("insert into book(name, author, price, pages) values (?,?,?,?)"
                        , new String[]{"AAA","Jack", "63.55","100"});

                db.execSQL("insert into book(name, author, price, pages) values (?,?,?,?)"
                        , new String[]{"BBB","Jack", "80.55","100" });
            }
        });


        //使用SQL查询
        Button btn_query_sql = findViewById(R.id.btn_query_sql);
        btn_query_sql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHepler.getWritableDatabase();
                Cursor cursor = db.rawQuery("select * from book where pages=?", new String[]{"100"});

                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String price = cursor.getString(cursor.getColumnIndex("price"));

                    Toast.makeText(MainActivity.this, "name:" + name + " | price:" + price, Toast.LENGTH_SHORT).show();
                }

                cursor.close(); //记得关闭指针
            }
        });

    }

}
