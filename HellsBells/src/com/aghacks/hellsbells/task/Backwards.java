package com.aghacks.hellsbells.task;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aghacks.hellsbells.R;
import com.aghacks.hellsbells.domain.MyTimer;

public class Backwards extends Activity {

	
	int time = 60;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_backwards);

		Button button = (Button) findViewById(R.id.button1);
		TextView word = (TextView) findViewById(R.id.textView2);

		Random r = new Random();
		int number1 = (r.nextInt(3 - 1) + 1) - 1;

		String[] table = new String[10];
		table[0] = "tiger";
		table[1] = "yellow";
		table[2] = "hello";
		table[3] = "welcome";
		table[4] = "lion";
		table[5] = "window";
		table[6] = "clock";
		table[7] = "forrest";
		table[8] = "elephant";
		table[9] = "department";

		String text = table[number1];

		word.setText(text);

		char[] backwards_text = new char[text.length()];

		int start = text.length() - 1;
		int position = 0;

		for (int i = start; i >= 0; i--) {
			backwards_text[position] = text.charAt(i);
			position++;
		}

		final String result = new String(backwards_text);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				EditText textfield = (EditText) findViewById(R.id.answer);
				TextView answer = (TextView) findViewById(R.id.textView1);
				String text = textfield.getText().toString();

				if (text.equals(result)) {
					finish();
				} else
					answer.setText("Try again!");
			}
		});
		
		
		TextView tv = (TextView) findViewById(R.id.timer);
		MyTimer myTimer = new MyTimer(this, tv);
		myTimer.t.scheduleAtFixedRate(myTimer.task, 0, 1000);
	}

	@Override
	protected void onDestroy() {
		setResult(RESULT_OK);
		super.onDestroy();
	}
}
