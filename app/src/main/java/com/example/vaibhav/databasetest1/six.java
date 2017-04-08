package com.example.vaibhav.databasetest1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatImageView;
import android.text.Html;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaibhav.databasetest1.database.DataProvider;
import com.example.vaibhav.databasetest1.database.DbHelper;

import java.util.HashMap;


public class six extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Session Manager Class
    SessionManager session;
    Intent intent;
    AppCompatImageView imgView;
    SQLiteDatabase sqLiteDatabase;
    DbHelper dbHelper;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set up no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set up full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_six);

        TextView lblName = (TextView) findViewById(R.id.user_profile_name);
        TextView lblEmail = (TextView) findViewById(R.id.user_profile_short_bio);
        TextView address = (TextView) findViewById(R.id.address);
        TextView sixheadername = (TextView) findViewById(R.id.sixheadername);


        // Session class instance
        session = new SessionManager(getApplicationContext());

        session.checkLogin();
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);
       // sixheadername.setText();

        // email
        String email = user.get(SessionManager.KEY_EMAIL);

        // displaying user data
        lblName.setText(Html.fromHtml(name + "</b>"));
        lblEmail.setText(Html.fromHtml(email + "</b>"));


        dbHelper = new DbHelper(getApplicationContext());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        cursor = dbHelper.GetInformationadd(email,sqLiteDatabase);
        if(cursor.moveToFirst()){
            do {
                String dadd, dcity;
                dadd = cursor.getString(1);
                dcity = cursor.getString(2);
                address.setText(Html.fromHtml(dadd + "</b>" +" , "+ dcity + "</b>"));
            }while(cursor.moveToNext());
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void activate(View view){
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.example");
        if (intent != null) {
            // We found the activity now start the activity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            // Bring user to the market or let them choose an app?
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=" + "com.example"));
            startActivity(intent);
        }
    }
    public void reminder(View view){
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.taradov.alarmme");
        if (intent != null) {
            // We found the activity now start the activity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            // Bring user to the market or let them choose an app?
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=" + "com.taradov.alarmme"));
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.six, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.editprofile) {
            // Handle the camera action
        }  else if (id == R.id.emails) {
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
            startActivity(intent);

        } else if (id == R.id.contact) {
            Intent myIntent = new Intent(six.this, contactus.class);
            six.this.startActivity(myIntent);

        } else if (id == R.id.log_out) {
            session.logoutUser();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void mycircle(View view){
        Intent myIntent = new Intent(six.this, three.class);
        six.this.startActivity(myIntent);
    }

    public void panicbut(View view){
        Intent myIntent = new Intent(six.this, panic.class);
        six.this.startActivity(myIntent);
    }
    public void features(View view){
        Intent myIntent = new Intent(six.this, voice.class);
        six.this.startActivity(myIntent);
    }

}
