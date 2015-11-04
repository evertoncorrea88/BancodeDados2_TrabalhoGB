package com.everton.bancodedados2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    private MyApplication myApp;
    private Boolean isNewItem;
    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView name = ((TextView) findViewById(R.id.name));
        final TextView number = ((TextView) findViewById(R.id.number));
        final TextView email = ((TextView) findViewById(R.id.email));

        Button save = ((Button) findViewById(R.id.save));
        Button delete = ((Button) findViewById(R.id.delete));

        myApp = (MyApplication) getApplication();

        isNewItem = getIntent().getExtras().getBoolean("isNewItem");

        final int listId = getIntent().getExtras().getInt("listId");

        if (isNewItem) {
            delete.setVisibility(View.INVISIBLE);
        } else {
            contact = myApp.contactList.get(listId);

            name.setText(contact.getName());
            number.setText("" + contact.getNumber());
            email.setText(contact.getEmail());
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewItem) {
                    contact = new Contact(name.getText().toString(), Integer.parseInt(number.getText().toString()), email.getText().toString());
                    myApp.dbAccess.insert(contact);
                    myApp.contactList = myApp.dbAccess.retrieveItems();
                    myApp.listAdapter.updateResults(myApp.contactList);
                    Toast.makeText(getApplicationContext(), "Contatc "+contact.getName()+" was created.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    contact.setName(name.getText().toString());
                    contact.setNumber(Integer.parseInt(number.getText().toString()));
                    contact.setEmail(email.getText().toString());
                    myApp.dbAccess.update(contact);
                    myApp.listAdapter.updateResults(myApp.contactList);
                    Toast.makeText(getApplicationContext(), "Contatc "+contact.getName()+" was updated.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myApp.dbAccess.delete(contact);
                myApp.contactList.remove(listId);
                myApp.listAdapter.updateResults(myApp.contactList);
//                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Toast.makeText(getApplicationContext(), "Contatc "+contact.getName()+" was removed from the list.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
