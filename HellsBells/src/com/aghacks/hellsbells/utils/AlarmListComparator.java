package com.aghacks.hellsbells.utils;

import com.aghacks.hellsbells.domain.Alarm;

import java.util.Comparator;

public class AlarmListComparator implements Comparator<Alarm> {
    @Override
    public int compare(Alarm lhs, Alarm rhs) {
        if (lhs.isActive() == rhs.isActive()) return compareHours(lhs, rhs);
        if (lhs.isActive()) return -1;
        else return 1;
    }

    private int compareHours(Alarm lhs, Alarm rhs) {
        int lhsMinutes = getMinutes(lhs);
        int rhsMinutes = getMinutes(rhs);
        return lhsMinutes - rhsMinutes;
    }

    private int getMinutes(Alarm alarm) {
        return alarm.getOccurrence().getHour() * 60 + alarm.getOccurrence().getMinute();
    }
}
