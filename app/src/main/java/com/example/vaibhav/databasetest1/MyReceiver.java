package com.example.vaibhav.databasetest1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

public class MyReceiver  extends BroadcastReceiver{
    private static final int MY_NOTIFICATION_ID=1;
    NotificationManager notificationManager;
    Notification myNotification;
    public void onReceive(Context c, Intent i) {
        PendingIntent pi = PendingIntent.getBroadcast(c, 0, new Intent("com.example.vaibhav.databasetest1"),0 );
        myNotification=new NotificationCompat.Builder(c)
                .setContentTitle("This is a notification that uses a custom sound.")
                .setContentText("Notification")
                .setTicker("Notification!")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pi)
                .setSound(Uri.parse("android.resource://com.example.vaibhav.databasetest1/"+R.raw.police_siren2))
                .setAutoCancel(true)
                .build();

        notificationManager = (NotificationManager)c.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(MY_NOTIFICATION_ID, myNotification);

    }
}

