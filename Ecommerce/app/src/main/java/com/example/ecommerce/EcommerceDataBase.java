package com.example.ecommerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class EcommerceDataBase extends SQLiteOpenHelper {
    private static String dbname ="EcommerceDB";
    SQLiteDatabase EcommerceDB;

    public EcommerceDataBase(@Nullable Context context) {
        super(context, dbname, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Customers(CustID integer primary key autoincrement, Custname text, Username text, Password text, Gender text, Birthdate text, Job text , Question text , Answer text)");

        db.execSQL("Create table Categories(CatID integer primary key autoincrement, Catname text)");


        db.execSQL("Create table Orders(OrdID integer primary key autoincrement, OrdDate text, CustID integer, Address text," +
                " foreign key(CustID) references Customers(CustID))");


        db.execSQL("Create table Products(ProID integer primary key autoincrement, ProName text, Price integer, Quantity integer, CatID integer," +
                " foreign key(CatID) references Categories(CatID))");


        db.execSQL("Create table OrderDetails(OrdID integer not null , Quantity integer , ProID integer not null , primary key(OrdID,ProID) ," +
                " foreign key(OrdID) references Orders(OrdID) , foreign key(ProID) references Products(ProID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Customers");
        db.execSQL("drop table if exists Categories");
        db.execSQL("drop table if exists Orders");
        db.execSQL("drop table if exists Products");
        db.execSQL("drop table if exists OrderDetails");
        onCreate(db);
    }


    // User Function

    public void SignUp(Customer c)
    {
        EcommerceDB = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("Custname" , c.Name);
        content.put("Username" , c.Username);
        content.put("Password" , c.Password);
        content.put("Gender" , c.Gender);
        content.put("Birthdate" , c.BirthDate);
        content.put("Job" , c.Job);
        content.put("Question" , c.Question);
        content.put("Answer" , c.Answer);

        long ret = EcommerceDB.insert("Customers" , null , content);
        EcommerceDB.close();
    }

    public boolean Login(String inusername , String inpassword){
        EcommerceDB = getReadableDatabase();
        String[] mycols = new String[] {"Username" , "Password" , "Question" , "Answer"};
        String[] myargs = new String[] {inusername , inpassword};
        Cursor c = EcommerceDB.query("Customers" , mycols , "Username = ? and Password = ?" ,  myargs , null , null , null);
        System.out.println(c.getCount());

        if(c.getCount() > 0)
            return true;
        return false;
    }


    public Cursor ForgetPassword(String inusername){
        EcommerceDB = getReadableDatabase();
        String[] mycols = new String[] {"Username" , "Password" , "Question" , "Answer"};
        String[] myargs = new String[] {inusername};
        Cursor c = EcommerceDB.query("Customers" , mycols , "Username = ?" ,  myargs , null , null , null);
        if(c != null)
            c.moveToFirst();
        return c;
    }
    //----------------------------------------------------------------------------------

}
