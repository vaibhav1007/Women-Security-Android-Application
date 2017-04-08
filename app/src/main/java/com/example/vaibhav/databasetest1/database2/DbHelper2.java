package com.example.vaibhav.databasetest1.database2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper2 extends SQLiteOpenHelper {

    //  private static String CREATE_TABLE1;

    //    Database name
    private static final String DATABASE_NAME = "REGINFO.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY=
            "CREATE TABLE "+ UserContract2.NewUserInfo.TABLE_NAME+"("+UserContract2.NewUserInfo.USER_NAME+" TEXT,"+
                    UserContract2.NewUserInfo.USER_EMAIL+" TEXT,"+ UserContract2.NewUserInfo.USER_PASS+" TEXT);";

    public DbHelper2(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        System.out.println("Table is created...........................!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void AddInformation(String name, String email, String pass, SQLiteDatabase db) {
        ContentValues contentValues= new ContentValues();
        contentValues.put(UserContract2.NewUserInfo.USER_NAME,name);
        contentValues.put(UserContract2.NewUserInfo.USER_EMAIL,email);
        contentValues.put(UserContract2.NewUserInfo.USER_PASS,pass);
        db.insert(UserContract2.NewUserInfo.TABLE_NAME,null,contentValues);
    }


    public Cursor CheckExisting(String email,SQLiteDatabase sqLiteDatabase){

        String[] projection={UserContract2.NewUserInfo.USER_NAME};
        String selection=UserContract2.NewUserInfo.USER_EMAIL+" LIKE ?";
        String[] selection_args = {email};
        Cursor cursor = sqLiteDatabase.query(UserContract2.NewUserInfo.TABLE_NAME,projection,selection,selection_args,null,null,null);
        return  cursor;
    }

    public Cursor CheckExistingpass(String email,String pass,SQLiteDatabase sqLiteDatabase){

        String[] projection={UserContract2.NewUserInfo.USER_NAME};
        String selection=UserContract2.NewUserInfo.USER_PASS+" LIKE ? AND "+UserContract2.NewUserInfo.USER_EMAIL+" LIKE ?";
        String[] selection_args = {pass,email};
        Cursor cursor = sqLiteDatabase.query(UserContract2.NewUserInfo.TABLE_NAME,projection,selection,selection_args,null,null,null);
        return  cursor;
    }

}
