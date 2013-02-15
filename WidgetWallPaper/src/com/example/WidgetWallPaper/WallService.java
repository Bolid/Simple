package com.example.WidgetWallPaper;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class WallService extends IntentService{
    final String TAG = "Serv";
    Boolean servWork = true;
    public WallService(){
        super("WallService");
    }
    public void onCreate(){
        super.onCreate();
    }
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences mSetting = getSharedPreferences("AppSetting", Context.MODE_PRIVATE);
        BufferedWriter bw = null;
        WallHistory wallHistory = null;
        if (mSetting.getBoolean("history", false)) {
            wallHistory = new WallHistory();
            wallHistory.createdocumentHistory();
        }
        try {
            bw = new BufferedWriter(new OutputStreamWriter(openFileOutput("log.txt", MODE_PRIVATE)));
            bw.close();
            bw = null;
        } catch (FileNotFoundException e) {

        } catch (IOException e){

        }
        while (servWork){
            try {
                bw = new BufferedWriter(new OutputStreamWriter(openFileOutput("log.txt", MODE_APPEND)));
            } catch (FileNotFoundException e) {
            }
            LoadContent loadCont = new LoadContent(getBaseContext(), bw, wallHistory);
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
        }


    }
    public void onDestroy()
    {
        servWork = false;
        super.onDestroy();
    }


}
