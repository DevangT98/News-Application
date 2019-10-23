package com.example.dailyfeed.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dailyfeed.FavouriteItems;
import com.example.dailyfeed.ListItems;

import java.util.ArrayList;

public class DailyFeedModel {

    public static DBHelper dbHelper;
    public static SQLiteDatabase sqLiteDatabase;


    public static DBHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context);

        }
        return dbHelper;
    }

    public static void open() {
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public static void close() {

        sqLiteDatabase.close();
    }

    public static void insert(String title, String desc, String imgUrl, String url, String date, int checked) {

        title=title.replaceAll("'","\\'");
        title=title.replaceAll(";","\\;");
        ContentValues cv = new ContentValues();
        cv.put(Keys.NEWS_HEADER, title);
        cv.put(Keys.NEWS_DESC, desc);
        cv.put(Keys.NEWS_IMAGE_URL, imgUrl);
        cv.put(Keys.NEWS_URL, url);
        cv.put(Keys.NEWS_DATE, date);
        cv.put(Keys.NEWS_LIKE, checked);



        sqLiteDatabase.insert(Keys.TB_NAME, null, cv);
        Log.i("DEV", "VALUES INSERTED SUCCESSFULLY!!");

    }

    public static ArrayList<FavouriteItems> showFavourites() {

        ArrayList<FavouriteItems> favData = new ArrayList<>();
        sqLiteDatabase = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + Keys.TB_NAME;

        Cursor cr = sqLiteDatabase.rawQuery(sql, null);

        while (cr.moveToNext()) {
            String id = cr.getString(0);
            String title = cr.getString(2);
            String desc = cr.getString(3);
            String imgUrl = cr.getString(4);
            String url = cr.getString(1);
            String date = cr.getString(5);
            int checked = cr.getInt(6);
            title=title.replaceAll("\\'","'");
            title=title.replaceAll("\\;",";");
            favData.add(new FavouriteItems(id, title, desc, imgUrl, url, date, checked));
            Log.i("DEV", "ID: " + id);
            Log.i("DEV", "TITLE: " + title);
            Log.i("DEV", "DESC: " + desc);
            Log.i("DEV", "IMAGE: " + imgUrl);
            Log.i("DEV", "URL: " + url);
            Log.i("DEV", "DATE: " + date);
            Log.i("DEV", "CHECK: " + checked);
            Log.i("DEV", "--------------------------------------------------------------------------------------------------------------------");
        }
        return favData;
    }

    public static void deleteFav(String title) {
        title=title.replaceAll("'","\\'");
        //title=title.replaceAll(";","\\;");
        Log.i("DEV", ""+title.replaceAll("'","\\$"));
        /*sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.delete(Keys.TB_NAME,Keys.NEWS_HEADER+ "="+title,null);

        String q = "select * from Questions_answers where CHAPTERS = ?";
        sqLiteDatabase.rawQuery(q, new String[] { title});*/
        Log.i("DEV", "DELETE FROM " + Keys.TB_NAME + " WHERE " + Keys.NEWS_HEADER + " like '" + title + "'");
        sqLiteDatabase.execSQL("DELETE FROM " + Keys.TB_NAME + " WHERE " + Keys.NEWS_HEADER + " like '" + title + "'");
       /* String sql = "DELETE FROM " + Keys.TB_NAME + " WHERE " + Keys.NEWS_HEADER+ " = " + title;
        sqLiteDatabase.execSQL(sql);
       */ Log.i("DEV","item deleted successfully");

    }


    public static  ArrayList<String> getAllIds(){

        ArrayList<String> newsId =new ArrayList<>();
        String sql = "SELECT "+Keys.NEWS_HEADER+ " FROM " +Keys.TB_NAME;
        Cursor cr =sqLiteDatabase.rawQuery(sql,null);
        while (cr.moveToNext()){
            newsId.add(cr.getString(cr.getColumnIndex(Keys.NEWS_HEADER)));
        }

        return newsId;
    }
}
