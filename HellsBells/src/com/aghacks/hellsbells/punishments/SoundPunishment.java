package com.aghacks.hellsbells.punishments;

import android.content.Context;
import android.media.MediaPlayer;
import com.aghacks.hellsbells.R;

public class SoundPunishment implements Punishment {

    @Override
    public void punish(Context context) {
        MediaPlayer mPlayer = MediaPlayer.create(context, R.raw.sound);
        mPlayer.setVolume(1.0f, 1.0f);
        mPlayer.start();
    }
}
