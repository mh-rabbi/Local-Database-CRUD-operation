package com.iubat.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class ContactTable extends DBHelper{
    public ContactTable(Context context) {
        super(context);
    }

    public void insertContact(ContactModel cm){
            SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME,cm.getName());
        cv.put(COL_EMAIL,cm.getEmail());
        cv.put(COL_PHONE,cm.getPhone());
        db.insert(TAB_CONTACT,null,cv);
        db.close();
    }

    public ArrayList<ContactModel> readContacts(){
        ArrayList<ContactModel> allContacts = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(TAB_CONTACT,null,null,null,null, null,null);
        while (c.moveToNext()){
            @SuppressLint("Range") int id = c.getInt(c.getColumnIndex(COL_ID));
            @SuppressLint("Range") String name = c.getString(c.getColumnIndex(COL_NAME));
            @SuppressLint("Range") String email = c.getString(c.getColumnIndex(COL_EMAIL));
            @SuppressLint("Range") String phone = c.getString(c.getColumnIndex(COL_PHONE));
            allContacts.add(new ContactModel(id,name,email,phone));
        }
        db.close();
        return allContacts;
    }

    public ContactModel readContact(int id){
        ContactModel cm = null;
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(TAB_CONTACT,null,"id = ?",new String[]{id+""},null, null,null);
        while (c.moveToNext()){
//            @SuppressLint("Range") int con_id = c.getInt(c.getColumnIndex(COL_ID));
            @SuppressLint("Range") String name = c.getString(c.getColumnIndex(COL_NAME));
            @SuppressLint("Range") String email = c.getString(c.getColumnIndex(COL_EMAIL));
            @SuppressLint("Range") String phone = c.getString(c.getColumnIndex(COL_PHONE));
            cm = new ContactModel(id,name,email,phone);
        }
        db.close();
        return cm;
    }

    public void updateContact(ContactModel cm){
        Log.e("Shuvo", "during update in database: "+ cm.getId());
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_ID,cm.getId());
        cv.put(COL_NAME,cm.getName());
        cv.put(COL_EMAIL,cm.getEmail());
        cv.put(COL_PHONE,cm.getPhone());
        db.update(TAB_CONTACT,cv,"id = ?",new String[]{cm.getId()+""});
//        db.update(TAB_CONTACT,cv,"id = ?",new String[]{cm.getId()+""});
        db.close();
    }

    public void deleteContact(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TAB_CONTACT,"id = ?",new String[]{id+""});
        db.close();
    }
}
