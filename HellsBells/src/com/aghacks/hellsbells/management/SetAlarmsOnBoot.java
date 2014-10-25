package com.aghacks.hellsbells.management;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.aghacks.hellsbells.domain.Alarm;

import java.util.List;


public class SetAlarmsOnBoot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
             List<Alarm> alarms = AlarmRepository.loadAll(context);
            for (Alarm alarm :alarms) {
                if (alarm.isActive()) {
                    // TODO:
                }
            }
        }

    }
}
