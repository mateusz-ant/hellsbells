package com.aghacks.hellsbells.domain;


public enum DayOfWeek {
    MONDAY("MON"), TUESDAY("TUE"), WEDNESDAY("WED"), THURSDAY("THU"), FRIDAY("FRI"), SATURDAY("SAT"), SUNDAY("SUN");

    DayOfWeek(String shortcut) {
        this.shortcut = shortcut;
    }

    private String shortcut;

    public String getShortcut() {
        return shortcut;
    }
}
