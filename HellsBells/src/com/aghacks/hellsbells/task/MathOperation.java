package com.aghacks.hellsbells.task;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.aghacks.hellsbells.R;
import com.aghacks.hellsbells.domain.MyTimer;

import java.util.Date;
import java.util.Random;

public class MathOperation extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_math_operation);

		TextView answer = (TextView) findViewById(R.id.textView2);
		Button button = (Button) findViewById(R.id.button1);
		
		TextView tv = (TextView) findViewById(R.id.timer);
		MyTimer myTimer = new MyTimer(this, tv);
		myTimer.t.scheduleAtFixedRate(myTimer.task, 0, 1000);

		Random r = new Random(new Date().getTime());
		int number1;
		int number2;
		final int result;

		do {
			number1 = r.nextInt(10 - 1) + 1;
			number2 = r.nextInt(10 - 1) + 1;
		} while (number1 < number2);

		int operation = r.nextInt(3) + 1;
		
		switch (operation) {
		case 1:
			result = number1 + number2;
			answer.setText(number1 + "+" + number2);
			break;
		case 2:
			result = number1 - number2;
			answer.setText(number1 + "-" + number2);
			break;
		case 3:
			result = number1 * number2;
			answer.setText(number1 + "*" + number2);
			break;
		default:
			result = number1 + number2;
			answer.setText(number1 + "+" + number2);
		}

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				EditText textfield = (EditText) findViewById(R.id.answer);
				TextView answer = (TextView) findViewById(R.id.textView1);

				try {
					int text = Integer.parseInt(textfield.getText().toString());

					if (text == result) {
                        setResult(RESULT_OK, new Intent());
						finish();
					} else {
						answer.setText("Try again!");
					}
				} catch (NumberFormatException e) {
					answer.setText("Try again!");
				}

			}
		});

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		setResult(RESULT_OK, new Intent());
	}

}
