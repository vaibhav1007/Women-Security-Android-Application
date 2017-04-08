package com.example.vaibhav.databasetest1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.vaibhav.databasetest1.SessionManager;
import com.example.vaibhav.databasetest1.six;

import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {

    //    Database name
    private static final String DATABASE_NAME = "NEWUSERINFO6.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY=
    "CREATE TABLE "+ UserContract.NewUserInfo.TABLE_NAME+"("+UserContract.NewUserInfo.USER_NAME+" TEXT,"+
            UserContract.NewUserInfo.USER_GROUP+" TEXT,"+
            UserContract.NewUserInfo.USER_PNO+" TEXT,"+
            UserContract.NewUserInfo.USER_ADD+" TEXT,"+
            UserContract.NewUserInfo.USER_CITY+" TEXT,"+
            UserContract.NewUserInfo.USER_EMAIL+" TEXT);";

    public DbHelper(Context context) {
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


    public void AddInformation(String name, String pno, String spp, SQLiteDatabase db) {
        ContentValues contentValues= new ContentValues();
        addinfo s1=new addinfo();
        contentValues.put(UserContract.NewUserInfo.USER_EMAIL,s1.ret_session());
        contentValues.put(UserContract.NewUserInfo.USER_NAME,name);
        contentValues.put(UserContract.NewUserInfo.USER_PNO,pno);
        contentValues.put(UserContract.NewUserInfo.USER_GROUP,spp);
        db.insert(UserContract.NewUserInfo.TABLE_NAME,null,contentValues);
    }

    public Cursor GetInformatione(String grp,SQLiteDatabase db,String email){

        Cursor cursor;
        String [] projections={UserContract.NewUserInfo.USER_EMAIL,UserContract.NewUserInfo.USER_NAME,UserContract.NewUserInfo.USER_PNO,UserContract.NewUserInfo.USER_GROUP};
        String selection=UserContract.NewUserInfo.USER_GROUP+" LIKE ? AND "+UserContract.NewUserInfo.USER_EMAIL+" LIKE ?";
        String[] selection_args = {grp,email};
        cursor=db.query(UserContract.NewUserInfo.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }

    public Cursor GetInformationadd(String email,SQLiteDatabase db){

        Cursor cursor;
        String [] projections={UserContract.NewUserInfo.USER_EMAIL,UserContract.NewUserInfo.USER_ADD,UserContract.NewUserInfo.USER_CITY};
        String selection=UserContract.NewUserInfo.USER_EMAIL+" LIKE ?";
        String[] selection_args = {email};
        cursor=db.query(UserContract.NewUserInfo.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }


    public Cursor CheckExisting(String email,SQLiteDatabase sqLiteDatabase){

        String[] projection={UserContract.NewUserInfo.USER_NAME};
        String selection=UserContract.NewUserInfo.USER_NAME+" LIKE ?";
        String[] selection_args = {email};
        Cursor cursor = sqLiteDatabase.query(UserContract.NewUserInfo.TABLE_NAME,projection,selection,selection_args,null,null,null);
        return  cursor;
    }
    public void AddInformationadd(String hadd, String cname, String email,SQLiteDatabase db) {
        ContentValues contentValues= new ContentValues();
        contentValues.put(UserContract.NewUserInfo.USER_EMAIL,email);
        contentValues.put(UserContract.NewUserInfo.USER_ADD,hadd);
        contentValues.put(UserContract.NewUserInfo.USER_CITY,cname);
        db.insert(UserContract.NewUserInfo.TABLE_NAME,null,contentValues);
    }

}
