package com.aghacks.hellsbells;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.aghacks.hellsbells.punishments.PunishmentService;
import com.aghacks.hellsbells.task.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

public class MainActivity extends Activity {
    private Ringtone r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getStringExtra("dupA").equals("dupaDUPA")) {
        	SharedPreferences pref = PreferenceManager
                    .getDefaultSharedPreferences(getApplicationContext());
            String strRingtonePreference = pref.getString("ringtone", "DEFAULT_SOUND");
            Uri sound = Uri.parse(strRingtonePreference);
         	r = RingtoneManager.getRingtone(getApplicationContext(), sound);
            r.play();
            
            LinkedList<Class> tasks = new LinkedList<Class>();
            if (pref.getBoolean("task_siara_enabled", false)) {
                tasks.add(SiaraActivity.class);
            }
            if (pref.getBoolean("task_shake_enabled", false)) {
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

            Random rand = new Random(new Date().getTime());
            Class classForTask = tasks.get(rand.nextInt(tasks.size()));

            Intent intent = new Intent(getApplicationContext(), classForTask);
            intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            startActivityForResult(intent, 1);
        } else {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        r.stop();
        if (resultCode == RESULT_OK) {
            
            finish();
        } else {
            Intent intent = new Intent(this, PunishmentService.class);
            finish();
            this.startService(intent);
            

        }
        super.onActivityResult(requestCode, resultCode, data);
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
