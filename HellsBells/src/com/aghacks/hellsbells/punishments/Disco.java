package com.aghacks.hellsbells.punishments;

import android.os.Handler;

public class Disco {
	private static boolean isStarted = false;
	private static Handler handler = new Handler();
	private static FaggotDetector activity;

	private static Runnable stepTimer = new Runnable() { 
		@Override
		public void run() {
			activity.Shine();
			handler.postDelayed(this, 3);
		}
	};
	public static void start(FaggotDetector view) {
		if(!isStarted) {
			handler.postDelayed(stepTimer, 0);
			isStarted = true;
		}
		activity = view;
	}
}