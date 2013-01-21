package com.example.WidgetWallPaper;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;
import java.util.Random;

public class WallPaper extends  AppWidgetProvider{
    public static String ACTION_WIDGET_BUTTON1 = "But1";
    public  static PendingIntent button1PendingIntent = null;
    Random random = new Random();
    int i = 0;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //Создаем новый RemoteViews
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main);



        //Подготавливаем Intent для Broadcast
        Intent button1intent = new Intent(context, ButActiv.class);
        button1intent.setAction(ACTION_WIDGET_BUTTON1);
        button1PendingIntent = PendingIntent.getActivity(context, 0, button1intent, 0);

        //регистрируем наше событие
        remoteViews.setOnClickPendingIntent(R.id.But2, button1PendingIntent);
        remoteViews.setTextViewText(R.id.TX, "Круть");


        //обновляем виджет
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //Ловим наш Broadcast, проверяем и выводим сообщение
        final String action = intent.getAction();
        if (intent.getAction().equals(ACTION_WIDGET_BUTTON1)) {
            Toast.makeText(context, "Click",Toast.LENGTH_LONG).show();
        }
        super.onReceive(context, intent);
    }


}
