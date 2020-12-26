package com.example.employeeassignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDatabaseClass extends SQLiteOpenHelper {
    private static String databaseName = "companydatabase";
    private SQLiteDatabase companydatabase;

    public MyDatabaseClass(Context context){
        super(context,databaseName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table department(DeptID integer primary key autoincrement , name text)");
        db.execSQL("create table employee(EmptID integer primary key autoincrement , Name text not null , Title text not null , Phone text not null , Email text not null , DeptID integer , foreign key(DeptID) references department(DeptID))");
        //InsertEmployee(new Employee("Ahmed Gamal" , "Scientist" , "23232323232" , "123@gmail.com"));
        //InsertEmployee(new Employee("Ahmed Ibrahim" , "Businessman" , "2434342323232" , "124@gmail.com"));
        ContentValues dep1 = new ContentValues();
        dep1.put("name" , "X");
        db.insert("department",null,dep1);

        ContentValues dep2 = new ContentValues();
        dep2.put("name" , "Y");
        db.insert("department",null,dep2);


        ContentValues row = new ContentValues();
        row.put("Name" , "Ahmed Gamal");
        row.put("Title" , "Quality Accurance");
        row.put("Phone" , "01445656789");
        row.put("Email" , "ahmed123@gmail.com");
        row.put("DeptID" , "1");
        db.insert("employee",null,row);

        ContentValues row2 = new ContentValues();
        row2.put("Name" , "Ahmed Mohamed");
        row2.put("Title" , "IT");
        row2.put("Phone" , "01178904567");
        row2.put("Email" , "ahmedZ1234@gmail.com");
        row2.put("DeptID" , "1");
        db.insert("employee",null,row2);


        ContentValues row3 = new ContentValues();
        row3.put("Name" , "Youssef Gamal");
        row3.put("Title" , "Android Developer");
        row3.put("Phone" , "01178904567");
        row3.put("Email" , "ahmedZ1234@gmail.com");
        row3.put("DeptID" , "2");
        db.insert("employee",null,row3);


        ContentValues row4 = new ContentValues();
        row4.put("Name" , "Mahmoud Medhat");
        row4.put("Title" , "Problem Solver");
        row4.put("Phone" , "01178904567");
        row4.put("Email" , "ahmedZ1234@gmail.com");
        row4.put("DeptID" , "2");
        db.insert("employee",null,row4);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists department");
        db.execSQL("drop table if exists employee");
        onCreate(db);
    }

    /*
    public void InsertEmployee(Employee e){
        ContentValues row = new ContentValues();
        row.put("Name" , e.Name);
        row.put("Title" , e.Title);
        row.put("Phone" , e.Phone);
        row.put("Email" , e.Email);
        companydatabase = getWritableDatabase();
        companydatabase.insert("employee",null,row);
        companydatabase.close();
    }*/

    public Cursor GetAllEmployeesContainsThisName(String name)
    {
       companydatabase = getReadableDatabase();
        Cursor c = (!name.equals(""))?companydatabase.rawQuery("select * from employee where Name Like ?" ,new String[]{"%"+name+"%"}):companydatabase.rawQuery("select * from employee where Name like ?" ,new String[]{"%"});
        if(c != null)
            c.moveToFirst();
        companydatabase.close();
        return c;
    }
}
