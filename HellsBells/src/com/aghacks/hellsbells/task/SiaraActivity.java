package com.aghacks.hellsbells.task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.aghacks.hellsbells.R;
import com.aghacks.hellsbells.domain.MyTimer;


public class SiaraActivity extends Activity implements AnimationListener {
	Animation animFadein,animSideDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.siara_activity);
        animSideDown = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.slide_down);
		animSideDown.setAnimationListener(this);
		animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.fade_in);
    	animFadein.setAnimationListener(this);
		Button buttonLeft = (Button)findViewById(R.id.buttonleft);
	    buttonLeft.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg1) {
			    	TextView ref = (TextView)findViewById(R.id.txtmessage);
			    	
			    	ref.setVisibility(View.VISIBLE);
					ref.startAnimation(animFadein);
			    	ref.setText("Dobrze!".toString());
			    	
			    	Vibrator vibrator;
			    	vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			    	vibrator.cancel();
			    	drawTaskDone();
			    }
			});
        Button buttonRight = (Button)findViewById(R.id.buttonright);
    	
        buttonRight.setOnClickListener(new Button.OnClickListener(){
		    @Override
		    public void onClick(View arg0) {
		    	TextView ref = (TextView)findViewById(R.id.txtmessage);
		    	ref.setVisibility(View.VISIBLE);
		    	ref.setText("Źle!".toString());
		    	ref.startAnimation(animFadein);
		    	
		    	Vibrator vibrator;
		    	long pattern[] = { 0, 100, 200, 300, 400 };
		    	vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		    	vibrator.vibrate(pattern, 0);
		     }
        });
        
        TextView tv = (TextView) findViewById(R.id.timer);
		MyTimer myTimer = new MyTimer(this, tv);
		myTimer.t.scheduleAtFixedRate(myTimer.task, 0, 1000);
    }
    @Override
	public void onAnimationEnd(Animation animation) {	
    }
    @Override
	public void onAnimationRepeat(Animation animation) {
	}
	@Override
	public void onAnimationStart(Animation animation) {
	}
    public void drawTaskDone(){
    	ImageView ref = (ImageView)findViewById(R.id.task_done);
    	ref.setVisibility(ImageView.VISIBLE);
    	ref.startAnimation(animSideDown);
        setResult(RESULT_OK, new Intent());
    	finish();
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	setResult(RESULT_OK, new Intent());
    }
}
