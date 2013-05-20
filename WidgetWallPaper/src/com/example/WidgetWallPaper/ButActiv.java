package com.example.WidgetWallPaper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;

public class ButActiv extends Activity {
    SharedPreferences mSetting;
    Boolean setSave = true;
    Boolean workAlarmManager = false;
    final String TAG = "com.example.WidgetWallPaper";
    final String AppSetting = "AppSetting";
    final String formSetStart = "formSetStart";
    final String history = "history";
    final String periodLoad = "periodLoad";
    final String sizePicMini = "sizePicMini";
    final String workAlarm = "workAlarm";
    final String timeWorkAlarm = "timeWorkAlarm";


    @Override
    public void onCreate (Bundle saveIns){
        super.onCreate(saveIns);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.form);
        mSetting = getSharedPreferences(AppSetting, Context.MODE_PRIVATE);
        final Editor editor = mSetting.edit();
        final CheckBox cbPreview = (CheckBox)findViewById(R.id.CB);
        final CheckBox cbHistory = (CheckBox)findViewById(R.id.CBHistory);
        final RadioGroup RG = (RadioGroup)findViewById(R.id.radioGroup);
        Button butSave = (Button)findViewById(R.id.butsaveSetting);
        Button butExit = (Button)findViewById(R.id.butExit);
        final Button butHistoryClear = (Button)findViewById(R.id.ButHistoryClear);
        getSettingApp(cbPreview, cbHistory, RG, butHistoryClear);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        butSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (RG.getCheckedRadioButtonId()){
                    case R.id.RB1: editor.putInt(periodLoad, 86400);
                        break;
                    case R.id.RB2: editor.putInt(periodLoad, 21600);
                        break;
                    case R.id.RB3: editor.putInt(periodLoad, 10800);//3 часа
                        break;
                    case R.id.RB4: editor.putInt(periodLoad, 10);//10 секунд
                        break;
                    case R.id.RB5: editor.putInt(periodLoad, 3);//3 секунды
                        break;
                }

                editor.putBoolean(formSetStart, cbPreview.isChecked());
                editor.putBoolean(history, cbHistory.isChecked());
                editor.putInt(sizePicMini, displayMetrics.widthPixels / 3 - 2);
                editor.putBoolean(workAlarm, workAlarmManager);
                editor.putBoolean(timeWorkAlarm, false);
                editor.commit();
                String startServ = getIntent().getExtras().getString("com.example.WidgetWallPaper.startServ");
                try{
                    if (startServ.equals("true"))
                    {
                        Intent start = new Intent(getBaseContext(), WallPaper.class);
                        start.setAction("But1");
                        getBaseContext().sendBroadcast(start);
                        Toast.makeText(getBaseContext(), "Service the start", Toast.LENGTH_LONG).show();
                        Log.v(TAG, "Service the start");
                    }
                }
                catch (Exception e){
                    Log.e(TAG, "Error start service in activity: ", e);
                }
                Log.v(TAG, "Размеры экрана: " + displayMetrics.widthPixels);
                if (workAlarmManager){
                    Intent workAlarm = new Intent(getBaseContext(), WallPaper.class);
                    workAlarm.setAction("com.example.WidgetWallPaper_WORK_ALARM");
                    getBaseContext().sendBroadcast(workAlarm);
                    Log.v(TAG, "Отправили интент на перезапуск");
                }
                finish();
                Log.v(TAG, "Настройки сохранены. Activity закрыта");

            }
        });

        butExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Log.v(TAG, "Activity закрыта без сохранения настроек.");
            }
        });

        butHistoryClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File file = new File("/data/data/com.example.WidgetWallPaper/files/","history.xml");
                    file.delete();
                    file = new File(Environment.getExternalStorageDirectory()+"/photos/");
                    if (file.isDirectory()){
                        for (File child : file.listFiles())
                            child.delete();
                    }
                    file.delete();
                    file = null;
                    Toast.makeText(getBaseContext(),"История удалена", Toast.LENGTH_LONG).show();
                    butHistoryClear.setEnabled(false);
                }catch (Exception e){
                    Log.e(TAG, "Ошибка при удалении файла настроек" + e);
                }
                Log.v(TAG, "Файл настроек удален");
            }
        });

    }

    public void getSettingApp(CheckBox CBPreview, CheckBox CBHistory, RadioGroup RG, Button butHistoryClear) {
        File file = new File("/data/data/com.example.WidgetWallPaper/files/","history.xml");
        butHistoryClear.setEnabled(file.isFile());

        if (mSetting.contains(workAlarm))
            workAlarmManager = mSetting.getBoolean(workAlarm, false);
        if (mSetting.contains(formSetStart))
            CBPreview.setChecked(mSetting.getBoolean(formSetStart, true));
        if (mSetting.contains(history))
            CBHistory.setChecked(mSetting.getBoolean(history, false));
        if (mSetting.contains(periodLoad))
            switch (mSetting.getInt(periodLoad,0)){
                case 86400: RG.check(R.id.RB1);
                    break;
                case 21600: RG.check(R.id.RB2);
                    break;
                case 10800: RG.check(R.id.RB3);
                    break;
                case 10: RG.check(R.id.RB4);
                    break;
                case 3: RG.check(R.id.RB5);
                    break;
            }

        Log.v(TAG, "Настройки считаны.");
    }
    public void onStop() {
        super.onStop();

        finish();
    }
}

