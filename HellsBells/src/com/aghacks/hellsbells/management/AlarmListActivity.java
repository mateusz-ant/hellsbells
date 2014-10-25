package com.aghacks.hellsbells.management;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import com.aghacks.hellsbells.R;
import com.aghacks.hellsbells.domain.Alarm;
import com.aghacks.hellsbells.domain.AlarmOccurrence;
import com.aghacks.hellsbells.domain.DayOfWeek;
import com.aghacks.hellsbells.utils.AlarmListComparator;

import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import static java.lang.String.valueOf;

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

        Collections.sort(alarms, new AlarmListComparator());
        adapter = new AlarmListAdapter(this, alarms);
        adapter.notifyDataSetChanged();

        alarmListView.setAdapter(adapter);

        alarmListView.setOnItemClickListener(new OnAlarmClickListener(this, alarms));

        registerForContextMenu(alarmListView);
    }

    private Alarm getExemplaryAlarm(int hour, int minute, DayOfWeek first, DayOfWeek... others) {
        AlarmOccurrence occurrence = new AlarmOccurrence();
        occurrence.setDaysOfWeek(EnumSet.of(first, others));
        occurrence.setHour(hour);
        occurrence.setMinute(minute);

        Alarm alarm = new Alarm();
        alarm.setId(valueOf(new Date().getTime()));
        alarm.setActive(true);
        alarm.setOccurrence(occurrence);

        AlarmRepository.save(this, alarm);

        return alarm;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.alarm_list_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.alarm_list_add:
                addAlarm();
                return true;
            case R.id.alarm_list_preferences:
                goToPreferences();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goToPreferences() {
        Intent intent = new Intent(this, SettingsActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Actions");
        menu.add(Menu.NONE, 0, 0, "Edit");
        menu.add(Menu.NONE, 1, 1, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Alarm alarm = (Alarm) info.targetView.getTag();
        String alarmId = alarm.getId();
        int menuItemIndex = item.getItemId();

        if (menuItemIndex == 0) {
            Intent intent = new Intent(this, AlarmDetailsActivity.class);
            intent.putExtra("ALARM_ID", alarmId);
            startActivity(intent);
        } else if (menuItemIndex == 1) {
            AlarmRepository.delete(this, alarmId);
            adapter.remove(alarm);
            adapter.notifyDataSetChanged();
        } else {
            return false;
        }
        return true;
    }

    private void addAlarm() {
        Intent intent = new Intent(this, AlarmDetailsActivity.class);
        this.startActivity(intent);
    }

}