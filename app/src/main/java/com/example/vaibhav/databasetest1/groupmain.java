package com.example.vaibhav.databasetest1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.example.vaibhav.databasetest1.database.DataListActivity;
import com.example.vaibhav.databasetest1.database.DataListActivity2;

public class groupmain  extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set up no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set up full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_groupmain);

        TabHost tabHost = getTabHost();

        // Tab for Emergency
        TabSpec photospec = tabHost.newTabSpec("Emergency");
        // setting Title and Icon for the Tab
        photospec.setIndicator("Emergency", getResources().getDrawable(R.drawable.alerticon));
        Intent photosIntent = new Intent(this, DataListActivity.class);
        photospec.setContent(photosIntent);

        // Tab for Accident
        TabSpec songspec = tabHost.newTabSpec("Accident");
        songspec.setIndicator("Accident", getResources().getDrawable(R.drawable.accidenticon));
        Intent songsIntent = new Intent(this, DataListActivity2.class);
        songspec.setContent(songsIntent);


        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding Emergency tab
        tabHost.addTab(songspec); // Adding Accident tab

    }
}