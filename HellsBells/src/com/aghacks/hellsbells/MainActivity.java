package com.aghacks.hellsbells;

import java.util.LinkedList;
import java.util.Random;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private Class classForPunishment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		LinkedList<String> tasks = new LinkedList<String>();
		if (pref.getBoolean("task_siara_enabled", false)) {
			tasks.add("com.aghacks.hellsbells.task.SiaraActivity");
		}
		if (pref.getBoolean("task_shape_enabled", false)) {
			tasks.add("com.aghacks.hellsbells.task.ShapeActivity");
		}
		if (pref.getBoolean("task_tap_enabled", false)) {
			tasks.add("com.aghacks.hellsbells.task.Tapper");
		}
		if (pref.getBoolean("task_flip_enabled", false)) {
			tasks.add("com.aghacks.hellsbells.task.FlipActivity");
		}
		if (pref.getBoolean("task_backwards_enabled", false)) {
			tasks.add("com.aghacks.hellsbells.task.Backwards");
		}
		if (pref.getBoolean("task_arithmetic_enabled", false)) {
			tasks.add("com.aghacks.hellsbells.task.MathOption");
		}
		Random rand = new Random();
		LinkedList<String> punishments = new LinkedList<String>();
		if (pref.getBoolean("punishment_sms_enabled", false)) {
			punishments.add("com.aghacks.hellsbells.punishments.SendSMS");
		}
		if (pref.getBoolean("punishment_inet_download_enabled", false)) {
			punishments
					.add("com.aghacks.hellsbells.punishments.DownloadFromInternet");
		}
		if (pref.getBoolean("punishment_flashlight_enabled", false)) {
			punishments
					.add("com.aghacks.hellsbells.punishments.FaggotDetector");
		}
		if (pref.getBoolean("punishment_sounds_enabled", false)) {
			punishments
					.add("com.aghacks.hellsbells.punishments.SoundPunishment");
		}
		if (pref.getBoolean("punishment_rickroll_enabled", false)) {
			punishments
					.add("com.aghacks.hellsbells.punishments.WallpaperChanger");
		}

		try {
			Class classForTask = Class.forName(tasks.get(rand.nextInt(tasks
					.size())));
			classForPunishment = Class.forName(punishments.get(rand
					.nextInt(punishments.size())));
		} catch (ClassNotFoundException e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
