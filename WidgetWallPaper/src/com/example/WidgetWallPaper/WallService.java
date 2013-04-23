package com.example.WidgetWallPaper;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class WallService extends IntentService{
    final String TAG = "com.example.WidgetWallPaper.Service";
    Boolean servWork = true;
    PendingIntent pendingIntent;
    public WallService(){
        super("WallService");
    }
    public void onCreate(){
        super.onCreate();
        Intent repeatIntentWallService = new Intent(getBaseContext(), WallService.class);
        pendingIntent = PendingIntent.getService(getBaseContext(), 0, repeatIntentWallService, 0);
    }
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences mSetting = getSharedPreferences("AppSetting", Context.MODE_PRIVATE);
        final int sizePicMini = mSetting.getInt("sizePicMini", 154);
        BufferedWriter bw = null;
        WallHistory wallHistory = null;
        if (mSetting.getBoolean("history", false)) {
            wallHistory = new WallHistory();
        }
        try {
            bw = new BufferedWriter(new OutputStreamWriter(openFileOutput("log.txt", MODE_PRIVATE)));
            bw.close();
            bw = null;
        } catch (FileNotFoundException e) {

        } catch (IOException e){

        }
            try {
                bw = new BufferedWriter(new OutputStreamWriter(openFileOutput("log.txt", MODE_APPEND)));
            } catch (FileNotFoundException e) {
            }
            LoadContent loadCont = new LoadContent(getBaseContext(), bw, wallHistory, servWork, sizePicMini);
            loadCont.load();
            try {
                bw.close();
                bw = null;
            } catch (IOException e) {
            }
            loadCont = null;
            System.gc();
    }
    public void onDestroy(){
        super.onDestroy();
    }


}
