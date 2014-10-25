package com.aghacks.hellsbells.punishments;



import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.aghacks.hellsbells.R;

public class FaggotDetector extends Activity {
	private Camera camera;
	@Override
	protected void onStop() {
		super.onStop();

		if (camera != null) {
			camera.release();
		}
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.faggot_activity);
		
		Disco.start(this);
		Context context = this;
		PackageManager pm = context.getPackageManager();
		if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			Log.e("err", "Device has no camera!");
			return;
		}
		camera = Camera.open();
	}
	public void Shine(){
		final Parameters p = camera.getParameters();
		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
		camera.setParameters(p);
		camera.startPreview();
		p.setFlashMode(Parameters.FLASH_MODE_OFF);
		camera.setParameters(p);
		camera.startPreview();
	}
}