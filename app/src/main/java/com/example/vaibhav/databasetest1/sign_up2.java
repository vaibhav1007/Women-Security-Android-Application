package com.example.vaibhav.databasetest1;

import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import com.example.vaibhav.databasetest1.database.DbHelper;

import java.util.HashMap;

public class sign_up2 extends AppCompatActivity {


    Context context=this;
    DbHelper dbhelper ;
    SQLiteDatabase sqlitedatabase;
    TextView tadd,tcity;
    SessionManager session;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set up no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set up full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_sign_up2);
        tadd=(TextView)findViewById(R.id.add);
        tcity=(TextView)findViewById(R.id.city);

        // Session class instance
        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // email
        email = user.get(SessionManager.KEY_EMAIL);

    }
    public void sigin(View view){
        String add=tadd.getText().toString();
        String city=tcity.getText().toString();

        if(empty(add,city)!=0) {
            dbhelper = new DbHelper(context);
            sqlitedatabase = dbhelper.getWritableDatabase();
            dbhelper.AddInformationadd(add, city, ret_session(),sqlitedatabase);
            dbhelper.close();
            Toast.makeText(getBaseContext(), "Data Saved", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(sign_up2.this,panic.class);
            sign_up2.this.startActivity(i);
        }
        else{
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            // set title
            alertDialogBuilder.setTitle("Alert!!");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Inconsistent Data")
                    .setCancelable(false)
                    .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, close dialog box
                            dialog.cancel();
                        }
                    });
            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }

    }
    public int empty(String ename,String epno){
        if(ename.isEmpty()||epno.isEmpty()){
            return 0;
        }
        return 1;
    }

    public String ret_session(){
        return email;
    }
}
