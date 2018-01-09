package com.sqliteexample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.sqliteexample.Database.DBhelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button mAddContact;
    public final static String EXTRA_MESSAGE = "MESSAGE";
    private ListView obj;
    DBhelper mydb;
    Context mContext;
    Data_Adapter dataAdapter;
    Data_Adapter1 dataAdapter1;
    AppCompatActivity appCompatActivity;
    RecyclerView mRecyclerview;
    Button mSenddata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSenddata = (Button) findViewById(R.id.senddata);
        mydb = new DBhelper(this);

        ArrayList arrayList = mydb.getALLContacts();

        ArrayList<ContactDataList> contactDataListArrayList = mydb.getAllContacts1();
        appCompatActivity = this;
        mContext = this;
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(appCompatActivity.getApplicationContext());
        mRecyclerview.setLayoutManager(mLayoutManager);

        // String Array

 /*       dataAdapter = new Data_Adapter(appCompatActivity,arrayList,this);
        mRecyclerview.setAdapter(dataAdapter);*/

        dataAdapter1 = new Data_Adapter1(appCompatActivity, contactDataListArrayList, this, mContext);
        mRecyclerview.setAdapter(dataAdapter1);


        mAddContact = (Button) findViewById(R.id.addContact);
        mAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent contact = new Intent(MainActivity.this, Contacts.class);
                startActivity(contact);

            }
        });


        mSenddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openWhatsApp();
                //text();
                sendAppMsg();
            }
        });

    }


    private void openWhatsApp() {
        String smsNumber = "7****"; // E164 format without '+' sign
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
        sendIntent.setPackage("com.whatsapp");
      /*  Intent intent=null;
        if (intent.resolveActivity(appCompatActivity.getPackageManager()) == null) {
         //   Toast.makeText(this, "Error/n" + e.toString(), Toast.LENGTH_SHORT).show();

        }*/
        //return;
        startActivity(sendIntent);
    }

    public void text() {

        String toNumber = "+91 98765 43210"; // contains spaces.
        toNumber = toNumber.replace("+", "").replace(" ", "");
        Intent sendIntent = new Intent("android.intent.action.MAIN");
        sendIntent.putExtra("jid", toNumber + "@s.whatsapp.net");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "hello ");
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setPackage("com.whatsapp");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);

    }


    public void sendAppMsg() {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String text = " message you want to share..";
        // change with required  application package
        intent.setPackage("com.whatsapp");
        if (intent != null) {
            intent.putExtra(Intent.EXTRA_TEXT, text);//
            startActivity(Intent.createChooser(intent, text));
        } else {
            Toast.makeText(this, "App not found", Toast.LENGTH_SHORT)
                    .show();
        }
    }

}
