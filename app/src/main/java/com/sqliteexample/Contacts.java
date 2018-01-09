package com.sqliteexample;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sqliteexample.Database.DBhelper;

public class Contacts extends AppCompatActivity {
    Button mSaveContact;
    EditText mEditTextName, mEditTextPhone, mEditTextStreet, mEditTextEmail, mEditTextCity;
    int from_Where_I_Am_Coming = 0;
    private DBhelper mydb;
    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        mydb = new DBhelper(this);
        mSaveContact = (Button) findViewById(R.id.saveContact);
        mEditTextName = (EditText) findViewById(R.id.editTextName);
        mEditTextPhone = (EditText) findViewById(R.id.editTextPhone);
        mEditTextStreet = (EditText) findViewById(R.id.editTextStreet);
        mEditTextEmail = (EditText) findViewById(R.id.editTextEmail);
        mEditTextCity = (EditText) findViewById(R.id.editTextCity);



        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");

            if(Value>0){
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String nam = rs.getString(rs.getColumnIndex(DBhelper.CONTACTS_COLUMN_NAME));
                String phon = rs.getString(rs.getColumnIndex(DBhelper.CONTACTS_COLUMN_PHONE));
                String emai = rs.getString(rs.getColumnIndex(DBhelper.CONTACTS_COLUMN_EMAIL));
                String stree = rs.getString(rs.getColumnIndex(DBhelper.CONTACTS_COLUMN_STREET));
                String plac = rs.getString(rs.getColumnIndex(DBhelper.CONTACTS_COLUMN_CITY));

                if (!rs.isClosed())  {
                    rs.close();
                }

             /*   Button b = (Button)findViewById(R.id.button1);
                b.setVisibility(View.INVISIBLE);*/

                mEditTextName.setText((CharSequence)nam);
                mEditTextName.setFocusable(false);
                mEditTextName.setClickable(false);

                mEditTextPhone.setText((CharSequence)phon);
                mEditTextPhone.setFocusable(false);
                mEditTextPhone.setClickable(false);

                mEditTextEmail.setText((CharSequence)emai);
                mEditTextEmail.setFocusable(false);
                mEditTextEmail.setClickable(false);

                mEditTextStreet.setText((CharSequence)stree);
                mEditTextStreet.setFocusable(false);
                mEditTextStreet.setClickable(false);

                mEditTextCity.setText((CharSequence)plac);
                mEditTextCity.setFocusable(false);
                mEditTextCity.setClickable(false);
            }
        }


        mSaveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendDataTable();

            }
        });

    }

    public void sendDataTable() {
/*
      Bundle extras = getIntent().getExtras();
       if (extras != null) {
            int Value = extras.getInt("id");
            if (Value != 0) {
                if (mydb.updateContact(id_To_Update, mEditTextName.getText().toString(),
                        mEditTextPhone.getText().toString(), mEditTextEmail.getText().toString(),
                        mEditTextStreet.getText().toString(), mEditTextCity.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            } else {*/
                if (true) {
                    mydb.insertContact(mEditTextName.getText().toString(),mEditTextEmail.getText().toString(),mEditTextPhone.getText().toString()
                          , mEditTextStreet.getText().toString(),
                            mEditTextCity.getText().toString());
                    Toast.makeText(getApplicationContext(), "done",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "not done",
                            Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
         // }
    //   }
    }
}
