package com.ex.belltester;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity {
	Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
    	ImageView ref = (ImageView)findViewById(R.id.task);
    	ref.setVisibility(ImageView.INVISIBLE);
    
    
    	Button buttonLeft = (Button)findViewById(R.id.buttonleft);
    	Button buttonRight = (Button)findViewById(R.id.buttonright);
    	
        buttonLeft.setOnClickListener(new Button.OnClickListener(){
		    @Override
		    public void onClick(View arg0) {
		    	vibrator.cancel();
		    }});
        buttonRight.setOnClickListener(new Button.OnClickListener(){
		    @Override
		    public void onClick(View arg0) {
		    	long pattern[] = { 0, 100, 200, 300, 400 };
		    	vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		    	vibrator.vibrate(pattern, 0);
		    		    }});
    }
    
    public void drawTaskDone(){
    	ImageView ref = (ImageView)findViewById(R.id.task);
    	ref.setVisibility(ImageView.VISIBLE);
    }
    
}
