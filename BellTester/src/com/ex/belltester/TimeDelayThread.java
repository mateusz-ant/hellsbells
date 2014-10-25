//package com.ex.belltester;
//
//import android.os.Handler;
//
//public class TimeDelayThread {
//	private static boolean isStarted = false;
//	private static Handler handler = new Handler();
//	private static PokeActivity activity;
//
//	private static Runnable stepTimer = new Runnable() { 
//		@Override
//		public void run() {
//			activity.MeasureTime();
//			handler.postDelayed(this, 1000);
//		}
//	};
//	public static void start(PokeActivity view) {
//		if(!isStarted) {
//			handler.postDelayed(stepTimer, 0);
//			isStarted = true;
//		}
//		activity = view;
//	}
//}