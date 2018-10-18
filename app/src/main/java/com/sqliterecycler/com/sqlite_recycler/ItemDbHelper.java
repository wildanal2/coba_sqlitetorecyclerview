package com.sqliterecycler.com.sqlite_recycler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "grocerylist.db";
    public static final int DATABASE_VERSION = 1 ;

    public ItemDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE = "CREATE TABLE belanjaan (id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT NOT NULL, jumlah INTEGER NOT NULL,timestmp TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS belanjaan");
        onCreate(db);
    }
}
