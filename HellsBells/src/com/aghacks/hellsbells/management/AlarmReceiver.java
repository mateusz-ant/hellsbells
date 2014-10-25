package com.aghacks.hellsbells.management;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.aghacks.hellsbells.MainActivity;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        // here you can start an activity or service depending on your need
        // for ex you can start an activity to vibrate phone or to ring the phone
        // Show the toast  like in above screen shot
        Intent activityIntent = new Intent(context, MainActivity.class);
        activityIntent.putExtra("dupA", "dupaDUPA");
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(activityIntent);
        Toast.makeText(context, "EVIL EVIL EVIL", Toast.LENGTH_LONG).show();
    }
}
