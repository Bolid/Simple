package com.example.WidgetWallPaper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ButActiv extends Activity {
    TextView TX;
    SharedPreferences mSetting;
    @Override
    public void onCreate (Bundle saveIns){
        super.onCreate(saveIns);
        setContentView(R.layout.form);
        final String AppSetting = "AppSetting";
        final String formSetStart = "formSetStart";
        mSetting = getSharedPreferences(AppSetting, Context.MODE_PRIVATE);
        Editor editor = mSetting.edit();
        editor.putBoolean(formSetStart, false);
        editor.commit();
        TX = (TextView)findViewById(R.id.TX);
        TX.setText("НАстройка");

    }
}
