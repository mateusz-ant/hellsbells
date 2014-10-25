package com.aghacks.hellsbells.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class AlarmOccurrence implements Serializable {
    private Set<DayOfWeek> daysOfWeek = new HashSet<DayOfWeek>();
    private int hour;
    private int minute;

    public Set<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(Set<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
