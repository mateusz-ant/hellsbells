package com.aghacks.hellsbells.domain;

public class Alarm {
    private AlarmOccurrence occurrence;
    private boolean isActive;

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
