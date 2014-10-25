package com.aghacks.hellsbells.management;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.aghacks.hellsbells.R;
import com.aghacks.hellsbells.domain.Alarm;
import com.aghacks.hellsbells.domain.AlarmOccurrence;
import com.aghacks.hellsbells.domain.DayOfWeek;

import java.util.Date;
import java.util.EnumSet;
import java.util.List;

public class AlarmListActivity extends Activity {
    private ListView alarmListView;
    private AlarmListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_list);

        alarmListView = (ListView) findViewById(R.id.alarm_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Alarm> alarms = AlarmRepository.loadAll(this);

        adapter = new AlarmListAdapter(this, AlarmRepository.loadAll(this));
        adapter.notifyDataSetChanged();

        alarmListView.setAdapter(adapter);

        alarmListView.setOnItemClickListener(new OnAlarmClickListener(this, alarms));
    }

    private Alarm getExemplaryAlarm(int hour, int minute, DayOfWeek first, DayOfWeek... others) {
        AlarmOccurrence occurrence = new AlarmOccurrence();
        occurrence.setDaysOfWeek(EnumSet.of(first, others));
        occurrence.setHour(hour);
        occurrence.setMinute(minute);

        Alarm alarm = new Alarm();
        alarm.setId(String.valueOf(new Date().getTime()));
        alarm.setActive(true);
        alarm.setOccurrence(occurrence);

        AlarmRepository.save(this, alarm);

        return alarm;
    }

}