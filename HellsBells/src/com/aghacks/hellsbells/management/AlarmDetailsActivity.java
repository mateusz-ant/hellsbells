package com.aghacks.hellsbells.management;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TimePicker;
import android.widget.Toast;
import com.aghacks.hellsbells.R;
import com.aghacks.hellsbells.domain.Alarm;
import com.aghacks.hellsbells.domain.AlarmOccurrence;
import com.aghacks.hellsbells.domain.DayOfWeek;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.aghacks.hellsbells.domain.DayOfWeek.*;
import static java.lang.String.valueOf;

public class AlarmDetailsActivity extends Activity {
    private Alarm alarm;

    private CheckBox mondayCheckBox;
    private CheckBox tuesdayCheckBox;
    private CheckBox wednesdayCheckBox;
    private CheckBox thursdayCheckBox;
    private CheckBox fridayCheckBox;
    private CheckBox saturdayCheckBox;
    private CheckBox sundayCheckBox;

    private CheckBox activeCheckBox;
    private TimePicker timePicker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_details);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String alarmId = extras != null ? extras.getString("ALARM_ID") : null;

        if (alarmId == null) {
            alarm = new Alarm();
            alarm.setId(valueOf(new Date().getTime()));
        } else {
            alarm = AlarmRepository.load(this, alarmId);
        }
        activeCheckBox = (CheckBox) findViewById(R.id.alarm_details_is_active);

        timePicker = (TimePicker) findViewById(R.id.alarm_details_time_picker);

        mondayCheckBox = (CheckBox) findViewById(R.id.alarm_details_is_monday);
        tuesdayCheckBox = (CheckBox) findViewById(R.id.alarm_details_is_tuesday);
        wednesdayCheckBox = (CheckBox) findViewById(R.id.alarm_details_is_wednesday);
        thursdayCheckBox = (CheckBox) findViewById(R.id.alarm_details_is_thursday);
        fridayCheckBox = (CheckBox) findViewById(R.id.alarm_details_is_friday);
        saturdayCheckBox = (CheckBox) findViewById(R.id.alarm_details_is_saturday);
        sundayCheckBox = (CheckBox) findViewById(R.id.alarm_details_is_sunday);

        activeCheckBox.setChecked(alarm.isActive());
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(alarm.getOccurrence().getHour());
        timePicker.setCurrentMinute(alarm.getOccurrence().getMinute());

        Set<DayOfWeek> daysOfWeek = alarm.getOccurrence().getDaysOfWeek();
        setDayOfWeekCheckBox(mondayCheckBox, MONDAY, daysOfWeek);
        setDayOfWeekCheckBox(tuesdayCheckBox, TUESDAY, daysOfWeek);
        setDayOfWeekCheckBox(wednesdayCheckBox, WEDNESDAY, daysOfWeek);
        setDayOfWeekCheckBox(thursdayCheckBox, THURSDAY, daysOfWeek);
        setDayOfWeekCheckBox(fridayCheckBox, FRIDAY, daysOfWeek);
        setDayOfWeekCheckBox(saturdayCheckBox, SATURDAY, daysOfWeek);
        setDayOfWeekCheckBox(sundayCheckBox, SUNDAY, daysOfWeek);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.alarm_details_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.alarm_details_save:
                saveAlarm();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveAlarm() {
        alarm.setActive(activeCheckBox.isChecked());

        AlarmOccurrence occurrence = new AlarmOccurrence();

        Set<DayOfWeek> daysOfWeek = new HashSet<DayOfWeek>();
        addDayIfChecked(mondayCheckBox, MONDAY, daysOfWeek);
        addDayIfChecked(tuesdayCheckBox, TUESDAY, daysOfWeek);
        addDayIfChecked(wednesdayCheckBox, WEDNESDAY, daysOfWeek);
        addDayIfChecked(thursdayCheckBox, THURSDAY, daysOfWeek);
        addDayIfChecked(fridayCheckBox, FRIDAY, daysOfWeek);
        addDayIfChecked(saturdayCheckBox, SATURDAY, daysOfWeek);
        addDayIfChecked(sundayCheckBox, SUNDAY, daysOfWeek);

        occurrence.setDaysOfWeek(daysOfWeek);

        occurrence.setHour(timePicker.getCurrentHour());
        occurrence.setMinute(timePicker.getCurrentMinute());

        alarm.setOccurrence(occurrence);

        AlarmRepository.save(this, alarm);

        if (alarm.isActive()) {
            AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
            PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            for (Calendar calOccurrence : alarm.getNearestOccurrences()) {
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calOccurrence.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY * 7, alarmIntent);
            }
        }

        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

    private void addDayIfChecked(CheckBox checkBox, DayOfWeek dayOfWeek, Set<DayOfWeek> daysOfWeek) {
        if (checkBox.isChecked()) {
            daysOfWeek.add(dayOfWeek);
        }
    }

    private void setDayOfWeekCheckBox(CheckBox mondayCheckBox, DayOfWeek monday, Set<DayOfWeek> daysOfWeek) {
        mondayCheckBox.setChecked(daysOfWeek.contains(monday));
    }
}