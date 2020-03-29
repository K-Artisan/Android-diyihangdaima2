package com.example.contactsdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> contactsList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView viewContact = findViewById(R.id.view_contacts);
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, contactsList);
        viewContact.setAdapter(adapter);

        //检查是否已经授权
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            //申请授权
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            readContacts();
        }

    }

    /**
     * 读取通讯录
     * */
    private void readContacts() {
        Cursor cursor = null;

        try {
            //获取 ContentResolver
            ContentResolver contentResolver = getContentResolver();
            //通讯录URL：ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                    , null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phonenum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    contactsList.add(displayName + "\n" + phonenum);
                    adapter.notifyDataSetChanged(); //更新ListView数据
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /**
     * 授权回调
     * */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts();
                } else {
                    Toast.makeText(this, "无法读取联系人，未经授权", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
