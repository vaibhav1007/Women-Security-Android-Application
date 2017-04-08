package com.example.vaibhav.databasetest1.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.vaibhav.databasetest1.R;
import com.example.vaibhav.databasetest1.SessionManager;

import java.util.HashMap;

public class DataListActivity extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    DbHelper dbHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_list_layout);

        listView=(ListView) findViewById(R.id.list_view);
        listDataAdapter = new ListDataAdapter(getApplicationContext(),R.layout.row_layout);
        listView.setAdapter(listDataAdapter);

        dbHelper = new DbHelper(getApplicationContext());
        sqLiteDatabase = dbHelper.getReadableDatabase();

        // Session class instance
        session = new SessionManager(getApplicationContext());

        session.checkLogin();
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // email
        String semail = user.get(SessionManager.KEY_EMAIL);


        cursor = dbHelper.GetInformatione("Emergency",sqLiteDatabase,semail);

        if(cursor.moveToFirst()){
            do{
                String name,email,pno,grp;
                email=cursor.getString(0);//0 for 1st column
                name=cursor.getString(1);
                pno=cursor.getString(2);
                grp=cursor.getString(3);
                DataProvider dataProvider = new DataProvider(email,name,pno,grp);
                listDataAdapter.add(dataProvider);
            }while (cursor.moveToNext());
        }

    }
}
