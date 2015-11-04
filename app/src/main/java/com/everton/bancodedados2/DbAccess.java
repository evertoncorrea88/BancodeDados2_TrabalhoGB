package com.everton.bancodedados2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Everton on 11/3/15.
 */
public class DbAccess {
    private SQLiteDatabase db;

    public DbAccess(Context context) {
        DbCore dbCore = new DbCore(context);
        db = dbCore.getWritableDatabase();
    }

    public void insert(Contact item){
        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        values.put("number", item.getNumber());
        values.put("email", item.getEmail());

        db.insert("items", null, values);
    }

    public void update(Contact item){
        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        values.put("number", item.getNumber());
        values.put("email", item.getEmail());

        db.update("items", values, "_id = "+item.getId(), null);
    }

    public void delete(Contact item){
        db.delete("items", "_id = "+item.getId(), null);
    }

    public List<Contact> retrieveItems(){
        List<Contact> listItems = new ArrayList<Contact>();

        String[] columns = new String[]{"_id", "name", "number", "email"};

        Cursor cursor = db.query("items", columns, null, null, null, null, "name ASC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Contact item = new Contact();
            item.setId(cursor.getLong(0));
            item.setName(cursor.getString(1));
            item.setNumber(cursor.getInt(2));
            item.setEmail(cursor.getString(3));
            listItems.add(item);

            cursor.moveToNext();
        }
        cursor.close();
        return listItems;
    }

}