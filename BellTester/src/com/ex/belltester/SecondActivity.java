//package com.ex.belltester;
//
//import java.util.Random;
//
//import android.app.Activity;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import android.graphics.drawable.AnimationDrawable;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.MotionEvent;
//import android.widget.ImageView;
//
//
//class SecondActivity extends Activity {
//	
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
////    public void initDrawables(){
////		winner = BitmapFactory.decodeResource(getResources(), R.drawable.wintask);
////		winner = winner.createScaledBitmap(winner, 400,300,true);
////	}
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//    public float Distance(float x,float y){
//    	return (x>y) ? (x-y) : (y-x);
//    }
//    //@Override
////	public boolean onTouchEvent(MotionEvent event) {
////		super.onTouchEvent(event);
////		float tappedX = event.getX()-50;
////		float tappedY = event.getY()-50;
////		ImageView ref = (ImageView)findViewById(R.id.Poke);
////		
////		if(event.getActionMasked() == MotionEvent.ACTION_UP){
////			if(Distance(tappedX,ref.getX() ) < 100
////		    && Distance(tappedY,ref.getY() )<100){
////				++CorrectTapped;
////				dx = (generator.nextFloat()-0.5f)*ScreenWidth/2;
////				dy = (generator.nextFloat()-0.5f)*ScreenHeight/2;
////				ref.setX(180+dx);
////				ref.setY(200+dy);
////		        AnimationDrawable anim = (AnimationDrawable)ref.getDrawable();
////		        anim.start();
////		        if(CorrectTapped>3){
////		        	
////		        	
////		        }
////			}
////		}
////		return true;
////	}	
//}
