package com.example.WidgetWallPaper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class ButActiv extends Activity {
    SharedPreferences mSetting;
    Boolean setSave = true;
    final String TAG = "activSet";
    /*public ButActiv(Boolean startSer) {
        try {
        this.startServ = startSer;
        }
        catch (Exception e) {
            Log.e(TAG, "Ошибка конструктора: ", e);
        }
    }*/

    @Override
    public void onCreate (Bundle saveIns){
        super.onCreate(saveIns);
        setContentView(R.layout.form);
        final String AppSetting = "AppSetting";
        final String formSetStart = "formSetStart";
        final String periodLoad = "periodLoad";
        mSetting = getSharedPreferences(AppSetting, Context.MODE_PRIVATE);
        final Editor editor = mSetting.edit();
        Button butSave = (Button)findViewById(R.id.button);
        final CheckBox checkBox = (CheckBox)findViewById(R.id.CB);
        butSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putBoolean(formSetStart, checkBox.isChecked());
                editor.commit();
                String startServ = null;
                startServ = getIntent().getExtras().getString("com.example.WidgetWallPaper.startServ");
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

            }
        });
    }
}
