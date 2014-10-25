package com.aghacks.hellsbells.punishment;

import android.app.Activity;
import android.media.MediaPlayer;

import com.aghacks.hellsbells.R;

public class SoundPunishment {

	private Activity context;

	public SoundPunishment(Activity activity) {
		this.context = activity;
	}

	public void execute() {
		MediaPlayer mPlayer = MediaPlayer.create(context, R.raw.sound);
		mPlayer.setVolume(1.0f, 1.0f);
        mPlayer.start();
	}

}
