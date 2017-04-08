package com.example.vaibhav.databasetest1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vaibhav.databasetest1.database.DbHelper;

public class Searchcontent extends AppCompatActivity {

    EditText Search_email;
    TextView display_name;
    DbHelper dbhelper;
    SQLiteDatabase sqLiteDatabase;
    String search_email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set up no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set up full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_searchcontent);
        Search_email=(EditText)findViewById(R.id.semail);
        display_name=(TextView) findViewById(R.id.namedisplay);
        display_name.setVisibility(View.GONE);
    }


    public void search_email(View view){

        System.out.println("Searching..................................");

        String dbString="";
        search_email=Search_email.getText().toString();
        dbhelper = new DbHelper(getApplicationContext());
        sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor= dbhelper.CheckExisting(search_email,sqLiteDatabase);

        if(cursor.moveToFirst()){

            //iterate over rows
            for (int i = 0; i < cursor.getCount(); i++) {

                //iterate over the columns
                for(int j = 0; j < cursor.getColumnNames().length; j++){

                    //append the column value to the string builder and delimit by a pipe symbol
                    dbString+=(cursor.getString(j) + "|");
                }
                //add a new line carriage return
                dbString+="\n";

                //move to the next row
                cursor.moveToNext();
            }
            display_name.setText(dbString);

            //System.out.println("returning true");
            //display_name.setText(cursor.getString(0));
            display_name.setVisibility(View.VISIBLE);
        }
        else{
            display_name.setText("No data");
            display_name.setVisibility(View.VISIBLE);
        }

    }


}
