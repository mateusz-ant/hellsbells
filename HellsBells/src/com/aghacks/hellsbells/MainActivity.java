package com.aghacks.hellsbells;

import java.util.LinkedList;
import java.util.Random;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import com.aghacks.hellsbells.punishments.DownloadFromInternet;
import com.aghacks.hellsbells.punishments.FaggotDetector;
import com.aghacks.hellsbells.punishments.SendSMS;
import com.aghacks.hellsbells.punishments.SoundPunishment;
import com.aghacks.hellsbells.punishments.WallpaperChanger;
import com.aghacks.hellsbells.task.Backwards;
import com.aghacks.hellsbells.task.FlipActivity;
import com.aghacks.hellsbells.task.MathOperation;
import com.aghacks.hellsbells.task.ShakeActivity;
import com.aghacks.hellsbells.task.SiaraActivity;
import com.aghacks.hellsbells.task.Tapper;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private Class classForPunishment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		LinkedList<Class> tasks = new LinkedList<Class>();
		if (pref.getBoolean("task_siara_enabled", false)) {
			tasks.add(SiaraActivity.class);
		}
		if (pref.getBoolean("task_shakee_enabled", false)) {
			tasks.add(ShakeActivity.class);
		}
		if (pref.getBoolean("task_tap_enabled", false)) {
			tasks.add(Tapper.class);
		}
		if (pref.getBoolean("task_flip_enabled", false)) {
			tasks.add(FlipActivity.class);
		}
		if (pref.getBoolean("task_backwards_enabled", false)) {
			tasks.add(Backwards.class);
		}
		if (pref.getBoolean("task_arithmetic_enabled", false)) {
			tasks.add(MathOperation.class);
		}
		Random rand = new Random();
		LinkedList<Class> punishments = new LinkedList<Class>();
		if (pref.getBoolean("punishment_sms_enabled", false)) {
			punishments.add(SendSMS.class);
		}
		if (pref.getBoolean("punishment_inet_download_enabled", false)) {
			punishments.add(DownloadFromInternet.class);
		}
		if (pref.getBoolean("punishment_flashlight_enabled", false)) {
			punishments.add(FaggotDetector.class);
		}
		if (pref.getBoolean("punishment_sounds_enabled", false)) {
			punishments.add(SoundPunishment.class);
		}
		if (pref.getBoolean("punishment_rickroll_enabled", false)) {
			punishments.add(WallpaperChanger.class);
		}

		Class classForTask = tasks.get(rand.nextInt(tasks.size()));
		classForPunishment = punishments.get(rand.nextInt(punishments.size()));

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
