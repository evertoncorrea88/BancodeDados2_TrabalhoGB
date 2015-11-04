package com.everton.bancodedados2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private MyApplication myApp;
    private ListView mainListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myApp = (MyApplication) getApplication();

        myApp.dbAccess = new DbAccess(this);

//        createDummy(dbAccess);

        myApp.contactList = myApp.dbAccess.retrieveItems();

        myApp.listAdapter = new AdapterListView(this, myApp.contactList);

        // Find the ListView resource.
        mainListView = (ListView) findViewById(R.id.mainListView);
        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter(myApp.listAdapter);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("listId", arg2);
                intent.putExtra("isNewItem", false);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("isNewItem", true);
                startActivity(intent);
            }
        });
    }

    public void createDummy(DbAccess dbAccess) {
        Contact contact;

        contact = new Contact("Everton", 96747531, "everton.correa88@gmail.com");
        dbAccess.insert(contact);
        contact = new Contact("Emanuella", 99331305, "emanuella@gmail.com");
        dbAccess.insert(contact);
        contact = new Contact("Kaue", 88331003, "kaue@gmail.com");
        dbAccess.insert(contact);
        contact = new Contact("Marcos", 98771210, "marcos@gmail.com");
        dbAccess.insert(contact);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
