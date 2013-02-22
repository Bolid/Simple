package com.example.WidgetWallPaper;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class WallPaper extends AppWidgetProvider {
    public static String ACTION_WIDGET_BUTTON1 = "But1";
    public static String ACTION_WIDGET_BUTTON2 = "But2";
    public static String ACTION_WIDGET_BUTTON3 = "But3";
    public static String ACTION_WIDGET_BUTTON4 = "But4";
    public static String SetStart = "SetActic";
    public static String Recev = "Recever";
    public static PendingIntent button1PendingIntent = null;
    public static PendingIntent button2PendingIntent = null;
    public static PendingIntent button3PendingIntent = null;
    public static PendingIntent button4PendingIntent = null;
    final String TAG = "App";
   @Override
    public  void onEnabled(Context context){
    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //But1
        Intent button1intent = new Intent(context, WallPaper.class);
        button1intent.setAction(ACTION_WIDGET_BUTTON1);
        button1intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        button1PendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 0, button1intent, 0);
        //But2
        Intent button2intent = new Intent(context, WallPaper.class);
        button2intent.setAction(ACTION_WIDGET_BUTTON2);
        button2intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        button2PendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 0, button2intent, 0);
        //But3
        Intent button3intent = new Intent(context, WallPaper.class);
        button3intent.setAction(ACTION_WIDGET_BUTTON3);
        button3intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        button3PendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 0, button3intent, 0);
        //But4
        Intent button4intent = new Intent(context, WallPaper.class);
        button4intent.setAction(ACTION_WIDGET_BUTTON4);
        button4intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        button4PendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 0, button4intent, 0);
        //Создаем новый RemoteViews
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main);
        //регистрируем наше событие
        remoteViews.setOnClickPendingIntent(R.id.But1, button1PendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.But2, button2PendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.But3, button3PendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.But4, button4PendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
        //обновляем виджет

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        //Ловим наш Broadcast, проверяем и выводим сообщение
        final String formSetStart = "formSetStart";
        final String action = intent.getAction();
        {
            if (action.equals(ACTION_WIDGET_BUTTON1)) {

                try
                {
                    SharedPreferences mSetting = context.getSharedPreferences("AppSetting", Context.MODE_PRIVATE);
                   if (mSetting.contains(formSetStart))
                       if (mSetting.getBoolean(formSetStart, true))
                       {
                           Intent start = new Intent(context, WallService.class);
                           context.startService(start);
                           Toast.makeText(context, "Служба запущена.", Toast.LENGTH_LONG).show();
                           Log.v(TAG, "------------------------");
                           Log.v(TAG, "Служба запущена.");
                       }
                       else
                       {
                           Intent show = new Intent(context, ButActiv.class);
                           show.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                           show.putExtra("com.example.WidgetWallPaper.startServ", "true");
                           context.startActivity(show);
                           Log.v(TAG, "Служба не запущена. Запущена activity настроек.");
                       }
                }
                catch (Exception e){Log.e(TAG, "Ошибка запуска: ", e);}
            }
            else
            if (action.equals(ACTION_WIDGET_BUTTON2)){
                    try
                    {
                        Intent servStop = new Intent(context, WallService.class);
                        servStop.putExtra("com.example.WidgetWallPaper.stopServ", "true");
                        Toast toast = new Toast(context);
                        toast.setDuration(500);
                        context.stopService(servStop);
                        toast.makeText(context, "Служба остановлена.", Toast.LENGTH_LONG).show();
                        Log.v(TAG, "Служба остановлена.");
                        Log.v(TAG, "======================");

                    }
                    catch (Exception e){Log.e(TAG, "Ошибка остановки: ", e);}
                }
            else
            if (action.equals(ACTION_WIDGET_BUTTON3)){
                Intent show = new Intent(context, ButActiv.class);
                show.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                show.putExtra("com.example.WidgetWallPaper.startServ", "false");
                context.startActivity(show);
                Log.v(TAG, "Запущена activity настроек.");

            }
            else
            if (action.equals(ACTION_WIDGET_BUTTON4)){
                Intent formGallery = new Intent(context, FormGallery.class);
                formGallery.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(formGallery);
                }

        }
        super.onReceive(context, intent);
    }

}

