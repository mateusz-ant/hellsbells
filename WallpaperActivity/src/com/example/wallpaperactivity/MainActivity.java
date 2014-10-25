package com.example.wallpaperactivity;
import java.io.IOException;

import android.app.Activity;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
 
 Bitmap bitmap;
 
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       WallpaperManager myWallpaperManager
       = WallpaperManager.getInstance(getApplicationContext());
      try {
       myWallpaperManager.setResource(R.drawable.wallpaper);
      } catch (IOException e) {
       e.printStackTrace();
      }
   }
}