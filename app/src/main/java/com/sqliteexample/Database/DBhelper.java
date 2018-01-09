package com.sqliteexample.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sqliteexample.ContactDataList;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Server on 12/27/2017.
 */

public class DBhelper extends SQLiteOpenHelper {



 /*   public DBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }*/

    public static final String DATABASE_NAME = "UserDetails.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_STREET = "street";
    public static final String CONTACTS_COLUMN_CITY = "city";
    public static final String CONTACTS_COLUMN_PHONE = "phone";
    private HashMap hp;


    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table contacts" + "(id integer primary key,name text, email text,street text,city text,phone text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST contacts" + CONTACTS_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertContact(String name,String email,String street,String city, String phone){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("street", street);
        contentValues.put("city", city);
        contentValues.put("phone",phone);
        db.insert("contacts",null,contentValues);
        return true;

    }

    public Cursor getData(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from contacts where id="+id+"",null);
        return res;
    }


    public int numberOfRows(){
        SQLiteDatabase db= this.getReadableDatabase();
        int numOfRows=(int) DatabaseUtils.queryNumEntries(db,CONTACTS_TABLE_NAME);
        return numOfRows;
    }
    public Integer deleteContacts (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }
    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
/*int id1=0;
        Cursor res = db.rawQuery("select * from contacts",null);
        res.moveToFirst();

        while (res.isAfterLast()==false){
            if (res.getPosition()==id){
                id1=id;
            }
            res.moveToNext();
      //      arrayList.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));

        }*/
       // res.close();

        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }


    //delete all records.


    public void deleteRecords(){

        SQLiteDatabase db =this.getWritableDatabase();

        db.delete(CONTACTS_TABLE_NAME,null,null);
        db.close();

    }


    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public ArrayList<String>getALLContacts(){
        ArrayList<String>arrayList=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts",null);
        res.moveToFirst();

        while (res.isAfterLast()==false){
            arrayList.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        res.close();
        return arrayList;
    }


    public ArrayList<ContactDataList>getAllContacts1(){

        ArrayList<ContactDataList>contactDataList1=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts",null);
        res.moveToFirst();
        while (res.isAfterLast()==false){
            ContactDataList contactDataList2= new ContactDataList();
            contactDataList2.setName(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            contactDataList2.setEmail(res.getString(res.getColumnIndex(CONTACTS_COLUMN_EMAIL)));
            contactDataList2.setPhone(res.getString(res.getColumnIndex(CONTACTS_COLUMN_PHONE)));
            contactDataList2.setPlace(res.getString(res.getColumnIndex(CONTACTS_COLUMN_CITY)));
            contactDataList2.setStreet(res.getString(res.getColumnIndex(CONTACTS_COLUMN_STREET)));
            contactDataList1.add(contactDataList2);
            res.moveToNext();
        }
        res.close();
         return contactDataList1;
    }
}
