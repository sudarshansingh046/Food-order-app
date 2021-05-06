package com.example.foody;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME="demodb";
    private static final int DB_VERSION=1;
    private static final String TABLE_NAME="record";
    private static final String ID_COL="id";
    private static final String Name="name";
    private static final String Price="price";
    private static final String Quantity="quantity";
    private static final String Image="image";
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+TABLE_NAME+" ("+Name+ " TEXT,"+Price+" TEXT,"+Image+" TEXT,"+Quantity+" TEXT)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            // Create table again
            onCreate(db);
        }
        public void insertRecord(String name, String price, String image,String quantity){
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put(Name,name);
            values.put(Price,price);
            values.put(Image,image);
            values.put(Quantity,quantity);
            db.insert(TABLE_NAME,null,values);
            db.close();
        }
    public void updateRecord(String name,String price,String image,String quantity){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Name,name);
        values.put(Price,price);
        values.put(Image,image);
        values.put(Quantity,quantity);
        db.update(TABLE_NAME,values,"image=?",new String[]{image});
        db.close();
    }
    public void deleteRecord(String image){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NAME + " WHERE " + Image + " = "+image+";");
        db.close();
    }



}
