package com.aghacks.hellsbells.punishments;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.TextView;

import com.aghacks.hellsbells.R;
import com.aghacks.hellsbells.domain.RandomNumber;

public class SendSMS extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_sms);
		
		String[] data = RandomNumber.execute(this);
		
		TextView text = (TextView) findViewById(R.id.textView);
		text.setText("The message was send to:\n" + data[0]);
		//sendSMS("881524538", "Hi You got a message!");
		
	
	}
	
	private static void sendSMS(String phoneNumber, String message) {
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, null, null);
	}
}
