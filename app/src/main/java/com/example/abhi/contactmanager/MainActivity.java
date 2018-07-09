package com.example.abhi.contactmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import Data.DatabaseHandler;
import Model.Contact;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("Insert","Inserting");
        db.addContact(new Contact("Abhi","9766318"));
        db.addContact(new Contact("Dhruv","879032"));
        db.addContact(new Contact("XYZ","09230832"));
        db.addContact(new Contact("ASD","0211091"));

        Log.d("Reading","reading");
        List<Contact> contactList=db.getAllContent();
        for(Contact c:contactList){
            String log ="ID: "+c.getId()+" ,Name:"+c.getName()+", Phone:"+c.getPhoneNumber();
            Log.d("Name",log);
        }
    }
}
