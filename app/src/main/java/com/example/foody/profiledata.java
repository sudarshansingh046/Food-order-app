package com.example.foody;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class profiledata extends SQLiteOpenHelper {
    private static final String DB_NAME = "profiledata";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "record";
    private static final String ID_COL = "id";
    private static final String Name = "name";
    private static final String Email = "email";
    private static final String Mobile = "mobile";
    private static final String Address = "address";

    public profiledata(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + Name + " TEXT," + Email + " TEXT," + Mobile + " TEXT," + Address + " TEXT)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create table again
        onCreate(db);
    }

    public void insertRecord(String name, String email, String mobile, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Name, name);
        values.put(Email, email);
        values.put(Mobile, mobile);
        values.put(Address, address);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}
