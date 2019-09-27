package com.example.dailyfeed.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dailyfeed.FavouriteItems;

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

    public static void insert(int position, String title, String desc, String imgUrl, String url, String date) {

        ContentValues cv = new ContentValues();
        cv.put(Keys.NEWS_ID,position);
        cv.put(Keys.NEWS_HEADER, title);
        cv.put(Keys.NEWS_DESC, desc);
        cv.put(Keys.NEWS_IMAGE_URL, imgUrl);
        cv.put(Keys.NEWS_URL, url);
        cv.put(Keys.NEWS_DATE, date);

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
            String title = cr.getString(3);
            String desc = cr.getString(2);
            String imgUrl= cr.getString(4);
            String url = cr.getString(1);
            String date = cr.getString(5);

            favData.add(new FavouriteItems(id,title,desc,imgUrl,url,date));
            Log.i("DEV","ID: "+id);
            Log.i("DEV","TITLE: "+title);
            Log.i("DEV","DESC: "+desc);
            Log.i("DEV","IMAGE: "+imgUrl);
            Log.i("DEV","URL: "+url);
            Log.i("DEV","DATE: "+date);
            Log.i("DEV","--------------------------------------------------------------------------------------------------------------------");
        }
        return favData;
    }
}
