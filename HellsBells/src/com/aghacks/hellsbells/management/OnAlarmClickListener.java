package com.aghacks.hellsbells.management;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import com.aghacks.hellsbells.domain.Alarm;

import java.util.List;

class OnAlarmClickListener implements AdapterView.OnItemClickListener {
    private List<Alarm> alarms;
    private Context context;

    public OnAlarmClickListener(Context context, List<Alarm> alarms) {
        this.context = context;
        this.alarms = alarms;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String alarmId = alarms.get(position).getId();
        Intent intent = new Intent(context, AlarmDetailsActivity.class);
        intent.putExtra("ALARM_ID", alarmId);
        context.startActivity(intent);
    }
}
