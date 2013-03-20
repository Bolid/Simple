package com.example.WidgetWallPaper;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;


public class WallService extends IntentService{
    final String TAG = "com.example.WidgetWallPaper.WallService";
    Boolean servWork = true;
    AlarmManager alarmManager;
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
        Calendar calendar = Calendar.getInstance();
        WallHistory wallHistory = null;
        if (mSetting.getBoolean("history", false)) {
            wallHistory = new WallHistory();
            //wallHistory.createdocumentHistory();
        }
        try {
            bw = new BufferedWriter(new OutputStreamWriter(openFileOutput("log.txt", MODE_PRIVATE)));
            bw.close();
            bw = null;
        } catch (FileNotFoundException e) {

        } catch (IOException e){

        }
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), mSetting.getInt("periodLoad", 86400)*1000, pendingIntent);
        //while (servWork){
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

            try {
                Log.v(TAG, "Период скачивания: "+Integer.toString(mSetting.getInt("periodLoad", 86400)*1000));
                Thread.sleep(mSetting.getInt("periodLoad", 86400)*1000);
            } catch (InterruptedException ie) {
                Log.e(TAG, "ОШИБКА ЗАДЕРЖКИ: ", ie);
                try {
                    bw.write("ОШИБКА ЗАДЕРЖКИ: "+String.valueOf(ie));
                } catch (IOException ioe) {

                }
            }
        //}


    }
    public void onDestroy()
    {   alarmManager.cancel(pendingIntent);
        super.onDestroy();
    }


}
