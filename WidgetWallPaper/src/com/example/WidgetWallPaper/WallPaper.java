package com.example.WidgetWallPaper;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;
import android.util.Log;

public class WallPaper extends AppWidgetProvider {
    public static String ACTION_WIDGET_BUTTON1 = "But1";
    public static String ACTION_WIDGET_BUTTON2 = "But2";
    public  static PendingIntent button1PendingIntent = null;
    public  static PendingIntent button2PendingIntent = null;
    final String TAG = "App";

   /*@Override
    public  void onEnabled(Context context){
        //Подготавливаем Intent для Broadcast


    }      */
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
        //Создаем новый RemoteViews
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main);
        //регистрируем наше событие
        remoteViews.setOnClickPendingIntent(R.id.But1, button1PendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.But2, button2PendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
        //обновляем виджет

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        //Ловим наш Broadcast, проверяем и выводим сообщение
        final String action = intent.getAction();
        {
            if (action.equals(ACTION_WIDGET_BUTTON1)) {

                try
                {
                    context.startService(new Intent(context, WallService.class));
                }
                catch (Exception e){Log.e(TAG, "Ошибка запуска: ", e);}
            }
            else
                if (action.equals(ACTION_WIDGET_BUTTON2)){
                    try
                    {
                        context.stopService(new Intent(context, WallService.class));
                    }
                    catch (Exception e){Log.e(TAG, "Ошибка остановки: ", e);}
                }
        }
        super.onReceive(context, intent);
    }





}

