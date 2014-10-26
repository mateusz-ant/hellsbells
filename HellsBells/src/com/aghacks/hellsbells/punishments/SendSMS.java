package com.aghacks.hellsbells.punishments;

import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.aghacks.hellsbells.domain.RandomNumber;
import com.aghacks.hellsbells.task.Messages;

public class SendSMS implements Punishment {

    @Override
    public void punish(Context context) {
        String[] data = RandomNumber.execute(context);

        String name = data[0];
        String phoneNumber = data[1];
        String message = "The message was send to " + name;
        Toast toast = Toast.makeText(context,
        		"The message was send to " + name, Toast.LENGTH_LONG);
		toast.show();
        Log.i(this.getClass().getSimpleName(), message);

        sendSMS(phoneNumber, "I miss you, call me!");
    }

    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }
}
