package com.android.demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Sukrut on 9/12/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private  static  final String Database_Name="Employee";

    public DatabaseHandler(Context context) {
        super(context, Database_Name, null, 1);
        Log.d("Database Operations","Database Created");
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table employee_details(name text,gender text,age text,occupation text,description text,address text)");
        Log.d("Database Operation","Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exist"+"employee_details");
        onCreate(db);
    }

    void addDetails(DataObject dataObject)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", dataObject.getName());
            contentValues.put("gender", dataObject.getGender());
            contentValues.put("age", dataObject.getAge());
            contentValues.put("occupation", dataObject.getOccupation());
            contentValues.put("description", dataObject.getDescription());
            contentValues.put("address", dataObject.getAddress());
            db.insert("employee_details", null, contentValues);
            Log.d("Database Operation","Row Inserted");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }




    public ArrayList<DataObject> getAllContacts()
    {
        ArrayList<DataObject>  dataObjects=new ArrayList<DataObject>();

        SQLiteDatabase db= this.getReadableDatabase();
        String[] projection={"name","gender","age","occupation","description","address"};
        Cursor cursor=db.rawQuery("select * from employee_details",null);
        if(cursor.moveToFirst())
        {
            do {

                DataObject dataObject=new DataObject(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
                dataObjects.add(dataObject);

                }while(cursor.moveToNext());
           db.close();
        }

        return  dataObjects;

    }

    public void delete(String name)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        try {

            db.delete("employee_details","name=?",new String[] {name.toString()});


        } catch (Exception e)
        {
            e.printStackTrace();
        }

        db.close();

    }



}
