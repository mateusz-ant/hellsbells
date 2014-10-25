package com.aghacks.hellsbells.domain;


public enum DayOfWeek {
    MONDAY("MON", 2),
    TUESDAY("TUE", 3),
    WEDNESDAY("WED", 4),
    THURSDAY("THU", 5),
    FRIDAY("FRI", 6),
    SATURDAY("SAT", 7),
    SUNDAY("SUN", 1);

    DayOfWeek(String shortcut, int calendarOrd) {
        this.shortcut = shortcut;
        this.calendarOrd = calendarOrd;
    }

    private String shortcut;
    private int calendarOrd;

    public String getShortcut() {
        return shortcut;
    }

    public int  getCalendarOrd() {
        return calendarOrd;
    }
}
