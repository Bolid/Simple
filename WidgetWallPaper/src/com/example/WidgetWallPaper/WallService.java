package com.example.WidgetWallPaper;

import android.app.IntentService;
import android.content.Intent;
import android.app.WallpaperManager;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;


public class WallService extends IntentService{
    final String TAG = "Serv";
    public WallService(){
        super("Proba");
    }
    public void onCreate(){
        super.onCreate();
    }
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //To change body of implemented methods use File | Settings | File Templates.
        WallpaperManager wall = WallpaperManager.getInstance(getApplicationContext());
        try {
            wall.setResource(R.drawable.img_1);
        } catch (IOException e) {
            Log.e(TAG, "Ошибка: ", e);
        }
    }
}
