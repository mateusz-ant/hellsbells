package com.aghacks.hellsbells.domain;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class MyTimer {

	int time;
	public Timer t;
	public TimerTask task;

	public MyTimer(final Activity activity, final TextView tv) {

		time = PreferenceManager.getDefaultSharedPreferences(
				activity.getApplicationContext()).getInt("Task time", 30);

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
							activity.setResult(activity.RESULT_CANCELED);
							activity.finish();
						}
					}
				});
			}
		};
	}

}
