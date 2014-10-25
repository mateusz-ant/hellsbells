package com.aghacks.hellsbells.management;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.aghacks.hellsbells.R;
import com.aghacks.hellsbells.domain.Alarm;

import java.util.List;

public class AlarmListAdapter extends ArrayAdapter<Alarm> {
    private final List<Alarm> alarms;
    private Context context;

    public AlarmListAdapter(Context context, List<Alarm> alarms) {
        super(context, R.layout.alarm_list, alarms);
        this.context = context;
        this.alarms = alarms;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alarmListItem = inflater.inflate(R.layout.alarm_list_item, parent, false);
        TextView itemHours = (TextView) alarmListItem.findViewById(R.id.alarm_list_item_hour);
        TextView itemDays = (TextView) alarmListItem.findViewById(R.id.alarm_list_item_days);

        Alarm alarm = alarms.get(position);

        itemHours.setText(alarm.getFormattedHours());
        itemDays.setText(alarm.getFormattedDaysOfWeek());
        if (!alarm.isActive()) {
            itemHours.setTextColor(Color.rgb(0x80, 0x80, 0x80));
            itemDays.setTextColor(Color.rgb(0x80, 0x80, 0x80));
        }

        alarmListItem.setTag(alarm);

        return alarmListItem;
    }
}
