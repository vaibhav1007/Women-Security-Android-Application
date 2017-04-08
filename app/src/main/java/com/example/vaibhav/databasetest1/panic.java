package com.example.vaibhav.databasetest1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


import com.cocosw.bottomsheet.BottomSheet;
import com.example.vaibhav.databasetest1.location.Main2Activity;

public class panic extends AppCompatActivity {

    int x=0;
    Context context ;
    Button p1_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set up no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set up full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_panic);

        p1_button = (Button)findViewById(R.id.button10);
    }
    public void openAndroidBottomMenu(View view) {
        x=0;
        new BottomSheet.Builder(this).title("Categories: ").sheet(R.menu.android_bottom_menu_item).listener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case R.id.menu_emer:
                        p1_button.setText("Emergency");
                        x=1;
                        break;
                    case R.id.menu_acci:
                        p1_button.setText("Accident");
                        Intent intent = new Intent(panic.this,accident.class);
                        panic.this.startActivity(intent);
                        break;
                    case R.id.menu_pro:
                        Intent myIntent = new Intent(panic.this,six.class);
                        panic.this.startActivity(myIntent);
                        break;
                    case R.id.menu_help:
                        Intent Intent = new Intent(panic.this, help.class);
                        panic.this.startActivity(Intent);
                        break;
                }
            }
        }).show();
    }
    public void panic(View view) {

        if (x == 1) {
            Intent myIntent = new Intent(panic.this, Main2Activity.class);
            panic.this.startActivity(myIntent);
        }
        else if(x==0){
            Toast.makeText(context, "Select category",
                    Toast.LENGTH_LONG).show();
        }

    }
}
