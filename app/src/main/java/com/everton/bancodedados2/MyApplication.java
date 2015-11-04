package com.everton.bancodedados2;

import android.app.Application;

import java.util.List;

/**
 * Created by Everton on 11/3/15.
 */
public class MyApplication extends Application {
    public DbAccess dbAccess;
    public List<Contact> contactList;
    public AdapterListView listAdapter;
}
