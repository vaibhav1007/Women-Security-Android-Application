package com.example.vaibhav.databasetest1.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.vaibhav.databasetest1.R;
import com.example.vaibhav.databasetest1.Searchcontent;
import com.example.vaibhav.databasetest1.groupmain;


public class MainActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void insert(View v){
        Intent myIntent = new Intent(getApplicationContext(),addinfo.class);
        startActivity(myIntent);
    }

    public void viewcont(View v){
        Intent myIntent = new Intent(getApplicationContext(),groupmain.class);
        startActivity(myIntent);

    }
    public void contentacti(View v){
        Intent myIntent = new Intent(getApplicationContext(),Searchcontent.class);
        startActivity(myIntent);

    }
}