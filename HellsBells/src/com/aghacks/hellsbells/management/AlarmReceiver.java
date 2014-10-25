package com.aghacks.hellsbells.management;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        // here you can start an activity or service depending on your need
        // for ex you can start an activity to vibrate phone or to ring the phone
        // Show the toast  like in above screen shot
        Toast.makeText(context, "EVIL EVIL EVIL", Toast.LENGTH_LONG).show();
    }
}
