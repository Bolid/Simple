package com.example.WidgetWallPaper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.*;

public class ButActiv extends Activity {
    SharedPreferences mSetting;
    Boolean setSave = true;
    final String TAG = "activSet";
    final String AppSetting = "AppSetting";
    final String formSetStart = "formSetStart";
    final String periodLoad = "periodLoad";


    @Override
    public void onCreate (Bundle saveIns){
        super.onCreate(saveIns);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.form);
        mSetting = getSharedPreferences(AppSetting, Context.MODE_PRIVATE);
        final Editor editor = mSetting.edit();
        final CheckBox checkBox = (CheckBox)findViewById(R.id.CB);
        final RadioGroup RG = (RadioGroup)findViewById(R.id.radioGroup);
        getSettingApp(checkBox, RG);
        Button butSave = (Button)findViewById(R.id.button);
        Button butExit = (Button)findViewById(R.id.butExit);

        butSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (RG.getCheckedRadioButtonId()){
                    case R.id.RB1: editor.putInt(periodLoad, 86400);
                        break;
                    case R.id.RB2: editor.putInt(periodLoad, 21600);
                        break;
                    case R.id.RB3: editor.putInt(periodLoad, 10800);
                        break;
                    case R.id.RB4: editor.putInt(periodLoad, 3600);
                        break;
                    case R.id.RB5: editor.putInt(periodLoad, 3);
                        break;
                }

                editor.putBoolean(formSetStart, checkBox.isChecked());
                editor.commit();
                String startServ = getIntent().getExtras().getString("com.example.WidgetWallPaper.startServ");
                try{
                    if (startServ.equals("true"))
                    {
                        startService(new Intent(getBaseContext(), WallService.class));
                        Toast.makeText(getBaseContext(), "Service the start", Toast.LENGTH_LONG).show();
                        Log.v(TAG, "Service the start");
                    }
                }
                catch (Exception e){
                    Log.e(TAG, "Error start service in activity: ", e);
                }
                finish();
                Log.v(TAG, "Настройки сохранены. Activity закрыта");

            }
        });

        butExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //Intent stop = new Intent(getBaseContext(), formGallery.class);
                Log.v(TAG, "Activity закрыта без сохранения настроек.");
            }
        });

    }

    public void getSettingApp(CheckBox checkBox, RadioGroup RG) {
        if (mSetting.contains(formSetStart))
            if (mSetting.getBoolean(formSetStart, true))
                checkBox.setChecked(true);
        if (mSetting.contains(periodLoad))
            switch (mSetting.getInt(periodLoad,0)){
                case 86400: RG.check(R.id.RB1);
                    break;
                case 21600: RG.check(R.id.RB2);
                    break;
                case 10800: RG.check(R.id.RB3);
                    break;
                case 3600: RG.check(R.id.RB4);
                    break;
                case 3: RG.check(R.id.RB5);
                    break;
            }

        Log.v(TAG, "Настройки считаны.");
    }
}

