package com.aghacks.hellsbells.task;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.aghacks.hellsbells.R;
import com.aghacks.hellsbells.domain.MyTimer;

public class ShakeActivity extends Activity implements SensorEventListener {
	private SensorManager sensorMgr;
	private Sensor accelerometer;
	private float mAccel; // acceleration apart from gravity
	private float mAccelCurrent; // current acceleration including gravity
	private float mAccelLast; // last acceleration including gravity

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
		accelerometer = sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		setContentView(R.layout.activity_shake);
		sensorMgr.registerListener(this, accelerometer,
				SensorManager.SENSOR_DELAY_FASTEST);
		mAccel = 0.00f;
		mAccelCurrent = SensorManager.GRAVITY_EARTH;
		mAccelLast = SensorManager.GRAVITY_EARTH;
		
		TextView tv = (TextView) findViewById(R.id.timer);
		MyTimer myTimer = new MyTimer(this, tv);
		myTimer.t.scheduleAtFixedRate(myTimer.task, 0, 1000);
	}

	protected void onResume() {
		super.onResume();
		sensorMgr.registerListener(this, accelerometer,
				SensorManager.SENSOR_DELAY_FASTEST);
	}

	protected void onPause() {
		super.onPause();
		sensorMgr.unregisterListener(this);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];
		mAccelLast = mAccelCurrent;
		mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
		float delta = mAccelCurrent - mAccelLast;
		mAccel = mAccel * 0.9f + delta; // perform low-cut filter
		if (mAccel > 10) {
			Toast toast = Toast.makeText(getApplicationContext(),
					Messages.completedTask, Toast.LENGTH_LONG);
			toast.show();
            setResult(RESULT_OK, new Intent());
			finish();
		} 
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		setResult(RESULT_OK, new Intent());
		sensorMgr.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
	
	@Override
    public void onBackPressed() {
    }

}