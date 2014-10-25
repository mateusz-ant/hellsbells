package com.aghacks.hellsbells.task;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import com.aghacks.hellsbells.R;

public class FlipActivity extends Activity implements SensorEventListener {
	private SensorManager sensorMgr;
	private Sensor accelerometer;
	private float mGZ = 0;// gravity acceleration along the z axis
	private int mEventCountSinceGZChanged = 0;
	private static final int MAX_COUNT_GZ_CHANGE = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
		accelerometer = sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		setContentView(R.layout.activity_shake);
		sensorMgr.registerListener(this, accelerometer,
				SensorManager.SENSOR_DELAY_FASTEST);
		setContentView(R.layout.activity_flip);

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
	protected void onDestroy() {
		setResult(RESULT_OK);
		sensorMgr.unregisterListener(this);
		super.onDestroy();
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float gz = event.values[2];
		if (mGZ == 0) {
			mGZ = gz;
		} else {
			if ((mGZ * gz) < 0) {
				mEventCountSinceGZChanged++;
				if (mEventCountSinceGZChanged == MAX_COUNT_GZ_CHANGE) {
					mGZ = gz;
					mEventCountSinceGZChanged = 0;
					if (gz < 0) {
						Toast toast = Toast.makeText(getApplicationContext(),
								Messages.completedTask, Toast.LENGTH_LONG);
						toast.show();
						finish();
					}
				}
			} else {
				if (mEventCountSinceGZChanged > 0) {
					mGZ = gz;
					mEventCountSinceGZChanged = 0;
				}
			}
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
}
