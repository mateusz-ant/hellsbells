package com.aghacks.hellsbells.punishments;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DownloadFromInternet {
	
	
	public static void execute(){
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					DownloadFromInternet.getBitmapFromURL("http://wallfoy.com/wp-content/uploads/2014/08/landscape_city_night_building_manhatton_ultrahd_4k_wallpaper.jpg");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
	
	public static Bitmap getBitmapFromURL(String imageUrl) {
		try {
			URL url = new URL(imageUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			return myBitmap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
