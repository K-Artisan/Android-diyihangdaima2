package com.example.databasedemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseContentProvider extends ContentProvider {

    public static final int Book_Dir = 0;
    public static final int Book_Item = 1;
    public static final int Gategory_Dir = 2;
    public static final int Gategory_Item = 3;

    public static final String Authority = "com.example.databasedemo.provider";
    private static UriMatcher uriMatcher;

    private MyDatabaseHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(Authority, "book", Book_Dir);
        uriMatcher.addURI(Authority, "book/#", Book_Item);
        uriMatcher.addURI(Authority, "gategory", Gategory_Dir);
        uriMatcher.addURI(Authority, "gategory/#", Gategory_Item);
    }

    public DatabaseContentProvider() {
    }

    @Override
    public boolean onCreate() {
        //获取 SQLiteOpenHelper 实例，以便操作数据库
        dbHelper = new MyDatabaseHelper(getContext(), "BookStore.db", null, MainActivity.DataBaseVersion);
        return true;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (uriMatcher.match(uri)) {
            case Book_Dir:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.book";
            case Book_Item:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.book";
            case Gategory_Dir:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.gategory";
            case Gategory_Item:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.gategory";
        }
        return null;
    }

    /**
     * 查询
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;

        switch (uriMatcher.match(uri)) {
            case Book_Dir:
                cursor = db.query("Book", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case Book_Item:
                String bookId = uri.getPathSegments().get(1); //获取Id
                cursor = db.query("Book", projection, "id=?", new String[]{ bookId}, null, null, sortOrder);
                break;
            case Gategory_Dir:
                cursor = db.query("category", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case Gategory_Item:
                String categoryId = uri.getPathSegments().get(1); //获取Id
                cursor = db.query("category", projection, "id=?", new String[]{ categoryId}, null, null, sortOrder);
                break;
        }

        return cursor;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri retunUrl = null;

        switch (uriMatcher.match(uri)) {
            case Book_Dir:
            case Book_Item:
                long newbookId = db.insert("Book", null, values);
                retunUrl = Uri.parse("content://" + Authority + "/book/" + newbookId);
                break;

            case Gategory_Dir:
            case Gategory_Item:
                long newGategoryId = db.insert("Book", null, values);
                retunUrl = Uri.parse("content://" + Authority + "/gategory/" + newGategoryId);
                break;
        }

        return  retunUrl;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updateRows = 0; //影响的行数

        switch (uriMatcher.match(uri)) {
            case Book_Dir:
                updateRows = db.update("book", values, selection, selectionArgs);
                break;
            case Book_Item:
                String bookId =  uri.getPathSegments().get(1);
                updateRows = db.update("book", values, "id=?", new String[]{ bookId});
                break;
            case Gategory_Dir:
                updateRows = db.update("gategory", values, selection, selectionArgs);
                break;
            case Gategory_Item:
                String gategoryId =  uri.getPathSegments().get(1);
                updateRows = db.update("gategory", values, "id=?", new String[]{ gategoryId});
                break;
        }
        return  updateRows;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deleteRows = 0; //影响的行数

        switch (uriMatcher.match(uri)) {
            case Book_Dir:
                deleteRows = db.delete("book", selection, selectionArgs);
                break;
            case Book_Item:
                String bookId =  uri.getPathSegments().get(1);
                deleteRows = db.delete("book", "id=?", new String[]{ bookId});
                break;
            case Gategory_Dir:
                deleteRows = db.delete("gategory", selection, selectionArgs);
                break;
            case Gategory_Item:
                String gategoryId =  uri.getPathSegments().get(1);
                deleteRows = db.delete("gategory", "id=?", new String[]{ gategoryId});
                break;
        }
        return  deleteRows;
    }

}
