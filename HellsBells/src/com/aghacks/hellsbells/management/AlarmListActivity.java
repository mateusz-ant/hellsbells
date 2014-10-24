package com.aghacks.hellsbells.management;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.aghacks.hellsbells.R;
import com.aghacks.hellsbells.domain.Alarm;
import com.aghacks.hellsbells.domain.AlarmOccurrence;
import com.aghacks.hellsbells.domain.DayOfWeek;

import java.util.EnumSet;

import static com.aghacks.hellsbells.domain.DayOfWeek.*;
import static java.util.Arrays.asList;


public class AlarmListActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_list);

        Alarm alarm1 = getExemplaryAlarm(12, 34, MONDAY, WEDNESDAY, FRIDAY);
        Alarm alarm2 = getExemplaryAlarm(13, 34, MONDAY, THURSDAY, FRIDAY);

        AlarmListAdapter adapter = new AlarmListAdapter(this, asList(alarm1, alarm2));

        ListView alarmList = (ListView) findViewById(R.id.alarm_list);
        alarmList.setAdapter(adapter);
    }

    private Alarm getExemplaryAlarm(int hour, int minute, DayOfWeek first, DayOfWeek... others) {
        AlarmOccurrence occurrence = new AlarmOccurrence();
        occurrence.setDaysOfWeek(EnumSet.of(first, others));
        occurrence.setHour(hour);
        occurrence.setMinute(minute);

        Alarm alarm = new Alarm();
        alarm.setActive(true);
        alarm.setOccurrence(occurrence);

        return alarm;
    }
}