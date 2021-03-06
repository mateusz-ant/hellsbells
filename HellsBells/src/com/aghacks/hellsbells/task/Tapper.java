package com.aghacks.hellsbells.task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.aghacks.hellsbells.R;
import com.aghacks.hellsbells.domain.MyTimer;

import java.util.Random;

///
public class Tapper extends Activity implements AnimationListener {
	Animation animSideDown,animFadeOut;
	public Random generator = new Random();
	boolean created = false;
    int ScreenHeight=600;
	int ScreenWidth=400;
	float dx=200,dy=200;
	int CorrectTapped = 0;
	boolean ispsyduck=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tapper_activity);
        ispsyduck = generator.nextBoolean();
        ImageView pok1 = (ImageView)findViewById(R.id.psyduck);
        ImageView pok2 = (ImageView)findViewById(R.id.clefairy);
        ImageView pokemon = (ispsyduck) ? pok1 : pok2;
        pokemon.setVisibility(ImageView.VISIBLE);
        pokemon.setX(dx);
        pokemon.setY(dy);
        animSideDown= AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.slide_down);
        animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.fade_out);
        
        TextView tv = (TextView) findViewById(R.id.timer);
		MyTimer myTimer = new MyTimer(this, tv);
		myTimer.t.scheduleAtFixedRate(myTimer.task, 0, 1000);
    }
@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public float Distance(float x,float y){
    	return (x>y) ? (x-y) : (y-x);
    }
    @Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		float tappedX = event.getX()-50;
		float tappedY = event.getY()-50;
		ImageView pok1 = (ImageView)findViewById(R.id.psyduck);
        ImageView pok2 = (ImageView)findViewById(R.id.clefairy);
        ImageView ref = (ispsyduck) ? pok1 : pok2;
		
		if(event.getActionMasked() == MotionEvent.ACTION_UP){
			if(Distance(tappedX,ref.getX() ) < 120
		    && Distance(tappedY,ref.getY() )<120){
				++CorrectTapped;
				dx = (generator.nextFloat()-0.5f)*ScreenWidth/2;
				dy = (generator.nextFloat()-0.5f)*ScreenHeight/2;
				
				ref.setX(180+dx);
				ref.setY(200+dy);
		        AnimationDrawable anim = (AnimationDrawable)ref.getDrawable();
		        anim.start();
		        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		        if(CorrectTapped<=5){
		        	long pattern[] = { 0, 100, 200, 300, 400 };
		        	vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		        	vibrator.vibrate(pattern, 0);
		        }
		        if(CorrectTapped>5){
		        	vibrator.cancel();
		        	drawTaskDone();
		        }
			}
		}
		return true;
	}	
    @Override
	public void onAnimationEnd(Animation animation) {
    	try{
    		Thread.sleep(10000);
    	}catch(InterruptedException e){
    		throw new RuntimeException(e);
    	}
    }
    @Override
	public void onAnimationRepeat(Animation animation) {}
	@Override
	public void onAnimationStart(Animation animation) {}
    public void drawTaskDone(){
    	ImageView ref = (ImageView)findViewById(R.id.task);
    	ref.setVisibility(ImageView.VISIBLE);
    	ImageView pok1 = (ImageView)findViewById(R.id.psyduck);
        ImageView pok2 = (ImageView)findViewById(R.id.clefairy);
        pok1.setVisibility(ImageView.INVISIBLE);
        pok2.setVisibility(ImageView.INVISIBLE);
        ref.setVisibility(ImageView.VISIBLE);
        pok1 = (ispsyduck) ? pok1 : pok2;
        pok1.setAnimation(animFadeOut);
    	ref.startAnimation(animSideDown);
        setResult(RESULT_OK, new Intent());
    	finish();
   }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	setResult(RESULT_OK, new Intent());
    }
    
    @Override
    public void onBackPressed() {
    }
}
