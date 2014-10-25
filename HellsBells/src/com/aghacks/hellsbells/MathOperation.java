package com.aghacks.hellsbells;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MathOperation extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_math_operation);
		
		TextView answer = (TextView) findViewById(R.id.textView2);
		Button button= (Button) findViewById(R.id.button1);
		
		Random r = new Random();
		int number1;
		int number2;
		final int result;
		
		do{
			number1 = r.nextInt(10 - 1) + 1;
			number2 = r.nextInt(10 - 1) + 1;
		}
		while(number1 < number2);
		
		int operation = r.nextInt(3 - 1) + 1;
		
		switch(operation){
			case 1: 	result = number1 + number2; 
						answer.setText(number1+"+"+number2);
						break;
			case 2: 	result = number1 - number2;
						answer.setText(number1+"-"+number2);
						break;
			case 3: 	result = number1 * number2; 
						answer.setText(number1+"*"+number2);
						break;
			default: 	result = number1 + number2; 
						answer.setText(number1+"+"+number2);
		}
		
		
		button.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	
		    	EditText textfield = (EditText) findViewById(R.id.answer);
		    	TextView answer = (TextView) findViewById(R.id.textView1);
				int text = Integer.parseInt(textfield.getText().toString());
				
				if(text == result){
					finish();
				}
				else
					answer.setText("Try again!");
		    }
		});
		
		
		
	}
	
	@Override
    protected void onDestroy() {
		setResult(RESULT_OK);
		super.onDestroy();
    }
	
}
