package com.example.vaibhav.databasetest1;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vaibhav.databasetest1.database.DbHelper;
import com.example.vaibhav.databasetest1.location.Main2Activity;

import java.util.HashMap;

public class sms extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    DbHelper dbHelper;
    Cursor cursor;
    SessionManager session;
    EditText etPhoneNo, etMsg;
    Button btnSend;
    String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set up no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set up full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.modsms);

        btnSend = (Button) findViewById(R.id.buttonsms);
       // etPhoneNo = (EditText) findViewById(R.id.editText);
        etMsg = (EditText) findViewById(R.id.details);

        Intent intent = getIntent();
        if(intent.hasExtra("lat")) {
            Bundle bundle = getIntent().getExtras();
            msg="Need your help.My location details.\nSub locale: "+bundle.getString("sl")+"\nCity: "+bundle.getString("city")+"\nState: "+bundle.getString("state")
           +"\nCountry: "+bundle.getString("country") +"\nLatitude: "+bundle.getString("lat") +"\nLongitude: "+bundle.getString("long");

            etMsg.setText(msg);
        }

        // Session class instance
        session = new SessionManager(getApplicationContext());

        session.checkLogin();
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // email
        String semail = user.get(SessionManager.KEY_EMAIL);

        dbHelper = new DbHelper(getApplicationContext());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        cursor = dbHelper.GetInformatione("Emergency",sqLiteDatabase,semail);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNo;

                if(cursor.moveToFirst()){
                    do{
                        phoneNo=cursor.getString(2);
                        try {
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
                            Toast.makeText(getApplicationContext(), "Message Sent",
                                    Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {
                            Toast.makeText(getApplicationContext(),
                                    ex.getMessage().toString(),
                                    Toast.LENGTH_LONG).show();
                            ex.printStackTrace();
                        }
                    }while (cursor.moveToNext());
                }
                Intent myIntent = new Intent(sms.this, voice.class);
                sms.this.startActivity(myIntent);
                }
        });
    }

}