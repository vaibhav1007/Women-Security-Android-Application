package com.example.vaibhav.databasetest1;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vaibhav.databasetest1.database2.DbHelper2;
import com.example.vaibhav.databasetest1.database2.googlessignin;

public class one extends Activity {

    DbHelper2 dbhelper ;
    SQLiteDatabase sqlitedatabase;
    Context context=this;
    EditText  email,password;
    // Session Manager Class
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set up no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set up full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_one);

        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
    }

    public void signup(View view){


        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompts, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);
        final EditText userInput2 = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput2);
        final EditText userInput3 = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput3);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                //result.setText(userInput.getText());
                                String ui1=userInput.getText().toString();
                                String ui2=userInput2.getText().toString();
                                String ui3=userInput3.getText().toString();

                                if(ui1.isEmpty()||ui2.isEmpty()||ui3.isEmpty()){
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                            context);

                                    // set title
                                    alertDialogBuilder.setTitle("Alert!!");

                                    // set dialog message
                                    alertDialogBuilder
                                            .setMessage("Please fill all the entries")
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
                                else {
                                    dbhelper = new DbHelper2(getApplicationContext());
                                    sqlitedatabase = dbhelper.getReadableDatabase();
                                    Cursor cursor= dbhelper.CheckExisting(ui2,sqlitedatabase);
                                    cursor.moveToFirst();
                                    if(cursor.getCount()==0){

                                        dbhelper = new DbHelper2(context);
                                        sqlitedatabase = dbhelper.getWritableDatabase();
                                        dbhelper.AddInformation(ui1, ui2, ui3, sqlitedatabase);
                                        dbhelper.close();
                                        // Session Manager
                                       session = new SessionManager(getApplicationContext());
                                       session.createLoginSession(cursor.getString(0), ui2);



                                        Intent myIntent = new Intent(one.this, sign_up2.class);
                                        one.this.startActivity(myIntent);
                                    }

                                    else{
                                        alertshow("We already have this email registered");
                                    }
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    public void done(View view){

        String e=email.getText().toString();
        String pass=password.getText().toString();

        if(e.isEmpty()||pass.isEmpty()) {

            alertshow("Please fill the entries to proceed");
        }

        else {

            dbhelper = new DbHelper2(getApplicationContext());
            sqlitedatabase = dbhelper.getReadableDatabase();
            Cursor cursor2 = dbhelper.CheckExistingpass(e, pass, sqlitedatabase);
            cursor2.moveToFirst();
            if (cursor2.getCount() == 0) {
                alertshow("No such user exist!");

            } else {
                System.out.println("user exists....................................");
                Toast.makeText(getBaseContext(),"Welcome "+cursor2.getString(0),Toast.LENGTH_LONG).show();

                // Session Manager
                session = new SessionManager(getApplicationContext());
                session.createLoginSession(cursor2.getString(0), e);

                Intent myIntent = new Intent(one.this, sign_up2.class);
                one.this.startActivity(myIntent);
            }
        }
    }

    public void gsignin(View view){
        Intent myIntent = new Intent(one.this, googlessignin.class);
        one.this.startActivity(myIntent);
    }

    public void alertshow(String msg){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle("Alert!!");

        // set dialog message
        alertDialogBuilder
                .setMessage(msg)
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

