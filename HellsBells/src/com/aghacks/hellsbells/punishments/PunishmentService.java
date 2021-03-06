package com.aghacks.hellsbells.punishments;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class PunishmentService extends Service {

    private Binder binder = new Binder();

    private final String[] PUNISHMENT_CONFIG = {
            "punishment_sms_enabled",
            "punishment_inet_download_enabled",
            "punishment_flashlight_enabled",
            "punishment_sounds_enabled",
            "punishment_rickroll_enabled",
            "punishment_crazy_caller_enabled",
            "punishment_garbage_enabled"
    };

    private final Map<String, Class<?>> PUNISHMENT_CONFIG_CLASSES = new HashMap<String, Class<?>>() {
        {
            put(PUNISHMENT_CONFIG[0], SendSMS.class);
            put(PUNISHMENT_CONFIG[1], DownloadFromInternet.class);
            put(PUNISHMENT_CONFIG[2], FaggotDetector.class);
            put(PUNISHMENT_CONFIG[3], SoundPunishment.class);
            put(PUNISHMENT_CONFIG[4], WallpaperChanger.class);
            put(PUNISHMENT_CONFIG[5], CrazyCaller.class);
            put(PUNISHMENT_CONFIG[6], GarbagePunishment.class);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void punish(Class classForPunishment, Context context) {
        try {
            Punishment punishment = (Punishment) classForPunishment.getConstructor().newInstance();
            punishment.punish(context);

        } catch (InstantiationException e) { 
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
        	throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
        	throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
        	throw new RuntimeException(e);
        }
    }

    private void startActivity() {
        Intent dialogIntent = new Intent(getBaseContext(), FaggotDetector.class);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(dialogIntent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Random rand = new Random(new Date().getTime());

        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        ArrayList<Class> punishments = new ArrayList<Class>();
        for (String punishmentKey : PUNISHMENT_CONFIG) {
            if (pref.getBoolean(punishmentKey, false)) {
                punishments.add(PUNISHMENT_CONFIG_CLASSES.get(punishmentKey));
            }
        }

        if (punishments.isEmpty()) {
            return binder;
        }

        Class classForPunishment = punishments.get(rand.nextInt(punishments.size()));

        Log.d("PunishmentService", classForPunishment.toString());
        Context context = getBaseContext();
        if (classForPunishment == FaggotDetector.class) {
            startActivity();
        } else {
            punish(classForPunishment, context);
        }

        return binder;
    }


}
