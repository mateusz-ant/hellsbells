package com.aghacks.hellsbells.domain;

import android.app.Activity;
import android.preference.PreferenceManager;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {

    int time;
    public Timer t;
    public TimerTask task;

    public MyTimer(final Activity activity, final TextView tv) {

        time = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(
                activity.getApplicationContext()).getString("task_time", "30"));

        t = new Timer();
        task = new TimerTask() {

            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        tv.setText(time + "");
                        if (time > 0)
                            time -= 1;
                        else {
                            activity.setResult(Activity.RESULT_CANCELED);
                            activity.finish();
                        }
                    }
                });
            }
        };
    }

}
