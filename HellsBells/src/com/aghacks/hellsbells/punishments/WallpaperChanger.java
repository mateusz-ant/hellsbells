package com.aghacks.hellsbells.punishments;

import android.app.WallpaperManager;
import android.content.Context;
import com.aghacks.hellsbells.R;

import java.io.IOException;

public class WallpaperChanger implements Punishment {
@Override
    public void punish(Context context) {
        WallpaperManager myWallpaperManager
                = WallpaperManager.getInstance(context);
        try {
            myWallpaperManager.setResource(R.raw.wallpaper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}