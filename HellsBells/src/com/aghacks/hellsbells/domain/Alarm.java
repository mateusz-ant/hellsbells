package com.aghacks.hellsbells.domain;

import java.io.Serializable;

public class Alarm implements Serializable {
    private AlarmOccurrence occurrence;
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

        for (DayOfWeek dayOfWeek : occurrence.getDaysOfWeek()) {
            builder.append(dayOfWeek.getShortcut()).append(" ");
        }

        return builder.toString();
    }

    public String getFormattedHours() {
        return String.format("%d:%d", occurrence.getHour(), occurrence.getMinute());
    }
}
