package com.example.vaibhav.databasetest1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.vaibhav.databasetest1.contactload.four;
import com.example.vaibhav.databasetest1.database.MainActivity;
import com.example.vaibhav.databasetest1.database.addinfo;

public class three extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set up no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set up full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_three);
    }

    public void add(View view){
        Intent myIntent = new Intent(three.this,addinfo.class);
        three.this.startActivity(myIntent);
    }
    public void load(View view){
        Intent myIntent = new Intent(three.this,four.class);
        three.this.startActivity(myIntent);
    }
    public void sc(View view){
        Intent myIntent = new Intent(three.this,Searchcontent.class);
        three.this.startActivity(myIntent);
    }
    public void mc(View view){
        Intent myIntent = new Intent(three.this,groupmain.class);
        three.this.startActivity(myIntent);
    }

}
