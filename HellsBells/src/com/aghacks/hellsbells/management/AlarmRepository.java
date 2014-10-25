package com.aghacks.hellsbells.management;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.aghacks.hellsbells.domain.Alarm;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class AlarmRepository {
    private static final String TAG = AlarmRepository.class.getSimpleName();

    private static final String ALL = "all_alarms";

    private AlarmRepository() {
    }

    public static void save(Context context, Alarm alarm) {
        try {
            SharedPreferences preferences = context.getSharedPreferences(ALL, MODE_PRIVATE);
            Set<String> alarms = preferences.getStringSet(ALL, new HashSet<String>());
            SharedPreferences.Editor editor = preferences.edit();
            Set<String> newAlarms = new HashSet<>(alarms);
            newAlarms.add(alarm.getId());

            editor.putStringSet(ALL, newAlarms);
            editor.apply();

            FileOutputStream fileOutputStream = context.openFileOutput(alarm.getId(), MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(alarm);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            Log.e(TAG, ";(", e);
        }
    }

    public static Alarm load(Context context, String alarmId) {
        Alarm createResumeForm = null;
        try {
            FileInputStream fileInputStream = context.openFileInput(alarmId);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            createResumeForm = (Alarm) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            delete(context, alarmId);
            Log.e(TAG, ";(", e);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, ";((", e);
        }
        return createResumeForm;
    }

    public static List<Alarm> loadAll(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(ALL, MODE_PRIVATE);
        Set<String> alarmIds = preferences.getStringSet(ALL, new HashSet<String>());

        ArrayList<Alarm> alarms = new ArrayList<>(alarmIds.size());

        for (String alarmId : alarmIds) {
            alarms.add(load(context, alarmId));
        }

        return alarms;
    }

    public static void delete(Context context, String alarmId) {

        SharedPreferences preferences = context.getSharedPreferences(ALL, MODE_PRIVATE);
        Set<String> alarms = preferences.getStringSet(ALL, new HashSet<String>());
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> newAlarms = new HashSet<>(alarms);
        newAlarms.remove(alarmId);

        editor.putStringSet(ALL, newAlarms);
        editor.apply();

        context.deleteFile(alarmId);
    }
}
