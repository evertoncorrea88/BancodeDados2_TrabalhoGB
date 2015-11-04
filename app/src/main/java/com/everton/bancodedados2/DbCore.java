package com.everton.bancodedados2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Everton on 11/3/15.
 */
public class DbCore extends SQLiteOpenHelper {
    private static final String DB_NAME = "BancoDeDados2";
    private static final int DB_VERSION = 10;

    public DbCore(Context ctx){
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE items(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, number REAL, email TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE items;");
        onCreate(db);
    }
}