package com.aghacks.hellsbells.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static java.util.EnumSet.allOf;

public class Alarm implements Serializable {

    private static final long serialVersionUID = -8374023520717177932L;

    private AlarmOccurrence occurrence = new AlarmOccurrence();
    private boolean isActive;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AlarmOccurrence getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(AlarmOccurrence occurrence) {
        this.occurrence = occurrence;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getFormattedDaysOfWeek() {
        StringBuilder builder = new StringBuilder();

        ArrayList<DayOfWeek> daysOfWeek = new ArrayList<DayOfWeek>(occurrence.getDaysOfWeek());
        Collections.sort(daysOfWeek);
        for (DayOfWeek dayOfWeek : daysOfWeek) {
            builder.append(dayOfWeek.getShortcut()).append(" ");
        }

        return builder.toString();
    }

    public String getFormattedHours() {
        return String.format("%d:%02d", occurrence.getHour(), occurrence.getMinute());
    }


    public List<Calendar> getNearestOccurrences() {

        List<Calendar> calendars = new ArrayList<Calendar>();

        for (DayOfWeek dayOfWeek : occurrence.getDaysOfWeek()) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, occurrence.getHour());
            calendar.set(Calendar.MINUTE, occurrence.getMinute());
            int todayDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            int dayDiff = dayOfWeek.getCalendarOrd() - todayDayOfWeek;

            if (dayDiff < 0) {
                dayDiff += allOf(DayOfWeek.class).size();
            }

            calendar.add(Calendar.DAY_OF_MONTH, dayDiff);

            if (calendar.getTimeInMillis() < Calendar.getInstance().getTimeInMillis()) {
//                calendar.add(Calendar.DAY_OF_MONTH, allOf(DayOfWeek.class).size());
            }

            calendars.add(calendar);
        }

        return calendars;
    }
}
