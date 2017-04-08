package com.example.vaibhav.databasetest1.database;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vaibhav.databasetest1.R;
import com.example.vaibhav.databasetest1.SessionManager;
import com.example.vaibhav.databasetest1.six;
import com.example.vaibhav.databasetest1.three;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class addinfo extends Activity implements AdapterView.OnItemSelectedListener {

    EditText  ContactName,ContactPno;
    Context context=this;
    DbHelper dbhelper ;
    SQLiteDatabase sqlitedatabase;
    Spinner spinner;
    String spp;
    // Session Manager Class
    SessionManager session;
     static String email;
    String name;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set up no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set up full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.addinfo);

        ContactName=(EditText)findViewById(R.id.name);
        ContactPno=(EditText)findViewById(R.id.pno);

        Intent intent = getIntent();
        if(intent.hasExtra("name")) {
            Bundle bundle = getIntent().getExtras();
            name = bundle.getString("name");
            number = bundle.getString("number");
            //set name and number
            ContactName.setText(name);
            ContactPno.setText(number);
        }

        // Spinner element
         spinner= (Spinner) findViewById(R.id.static_spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Emergency");
        categories.add("Accident");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        // Session class instance
        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // email
        email = user.get(SessionManager.KEY_EMAIL);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
         spp = parent.getItemAtPosition(position).toString();
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void addcontact(View v){
        String name=ContactName.getText().toString();
        String phone=ContactPno.getText().toString();

        if(empty(name,phone)!=0) {
            dbhelper = new DbHelper(context);
            sqlitedatabase = dbhelper.getWritableDatabase();
            dbhelper.AddInformation(name, phone, spp, sqlitedatabase);

            Toast.makeText(getBaseContext(), "Data Saved", Toast.LENGTH_LONG).show();
            dbhelper.close();

            Intent myIntent = new Intent(getApplicationContext(), three.class);
            startActivity(myIntent);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.spinner, menu);
        return true;
    }
    public String ret_session(){
        return email;
    }

    public void back(View view){
        Intent myIntent = new Intent(addinfo.this, six.class);
        addinfo.this.startActivity(myIntent);
    }
}


