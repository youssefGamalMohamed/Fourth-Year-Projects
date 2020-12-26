package com.example.handsonlab6;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MovieDataBaseClass extends SQLiteOpenHelper {

    private static String databasename = "moviedatabase";
    SQLiteDatabase moviedatabase;

    public MovieDataBaseClass(Context context) {
        super(context, databasename, null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table movie(id integer primary key autoincrement,"+
                "name text , description text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists movie");
        onCreate(db);
    }


    public void Insertmovie(String mname , String mdescription)
    {
        moviedatabase = getWritableDatabase();
        moviedatabase.execSQL("insert into movie(name , description) values('"+mname+"' , '"+mdescription+"')");
        moviedatabase.close();
    }
    public void Updatemovie(String oldname , String newname , String newdescription)
    {
        moviedatabase = getWritableDatabase();
        moviedatabase.execSQL("update movie set name = '"+newname+"' , description = '"+newdescription+"' where name = '"+oldname+"' ");
        moviedatabase.close();
    }
    
    public Cursor getAllmovies()
    {
        moviedatabase = getReadableDatabase();
        Cursor c = moviedatabase.rawQuery("select * from movie",new String[]{});
        if(c != null)
            c.moveToFirst();
        moviedatabase.close();
        return c;
    }
}
