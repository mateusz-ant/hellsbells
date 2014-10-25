package com.aghacks.hellsbells.task;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.aghacks.hellsbells.R;

public class SiaraActivity  extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView ref = (ImageView)findViewById(R.id.task_done);
    	ref.setVisibility(ImageView.INVISIBLE);
    	
    	Button buttonLeft = (Button)findViewById(R.id.buttonleft);
        buttonLeft.setOnClickListener(new Button.OnClickListener(){
		    @Override
		    public void onClick(View arg1) {
		    	TextView ref = (TextView)findViewById(R.id.textView1);
		    	ref.setText("Dobrze!".toString());
		    	Vibrator vibrator;
		    	vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		    	vibrator.cancel();
		    	drawTaskDone();
		    }});
        Button buttonRight = (Button)findViewById(R.id.buttonright);
    	
        buttonRight.setOnClickListener(new Button.OnClickListener(){
		    @Override
		    public void onClick(View arg0) {
		    	TextView ref = (TextView)findViewById(R.id.textView1);
		    	ref.setText("Źle!".toString());
		    	Vibrator vibrator;
		    	long pattern[] = { 0, 100, 200, 300, 400 };
		    	vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		    	vibrator.vibrate(pattern, 0);
		    		    }});
    }
    
    public void drawTaskDone(){
    	ImageView ref = (ImageView)findViewById(R.id.task_done);
    	ref.setVisibility(ImageView.VISIBLE);
    	finish();
    }
    
    @Override
    protected void onDestroy() {
    	setResult(RESULT_OK);
    	super.onDestroy();
    }
}