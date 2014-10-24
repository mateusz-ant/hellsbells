package com.aghacks.hellsbells.task;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import com.aghacks.hellsbells.R;

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
				SensorManager.SENSOR_DELAY_GAME);
		mAccel = 0.00f;
		mAccelCurrent = SensorManager.GRAVITY_EARTH;
		mAccelLast = SensorManager.GRAVITY_EARTH;
	}

	protected void onResume() {
		super.onResume();
		sensorMgr.registerListener(this, accelerometer,
				SensorManager.SENSOR_DELAY_GAME);
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
		if (mAccel > 16) {
			Toast toast = Toast.makeText(getApplicationContext(),
					Messages.completedTask, Toast.LENGTH_LONG);
			toast.show();
			onDestroy();
		}
	}

	@Override
	protected void onDestroy() {
		setResult(RESULT_OK);
		super.onDestroy();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

}