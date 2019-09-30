package com.example.dailyfeed.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DBHelper extends SQLiteOpenHelper {

   String create_table = "create table " + Keys.TB_NAME + "(" + Keys.NEWS_ID + " integer primary key autoincrement," + Keys.NEWS_URL + " text not null," + Keys.NEWS_HEADER + " text not null,"
           + Keys.NEWS_DESC + " text not null," + Keys.NEWS_IMAGE_URL + " text not null," + Keys.NEWS_DATE + " text not null,"+
           Keys.NEWS_LIKE + " integer)";

   public DBHelper(Context context) {
      super(context, Keys.DB_NAME, null, Keys.DB_VERSION);
      Log.i("DEV", create_table);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
//      Log.i("DEV", create_table);
      db.execSQL(create_table);

   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

   }
}
