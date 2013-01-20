package com.example.WallPapperPhotoDay;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MyActivity extends Activity {
    boolean servStar = true;
    TextView TX;
        @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            TX = (TextView)findViewById(R.id.TX);
            Button but_1 = (Button)findViewById(R.id.But1);
            Button but_2 = (Button)findViewById(R.id.But2);
            final Spinner spin = (Spinner)findViewById(R.id.spinner);
            TX.setText("0");
            ActivityManager am = (ActivityManager)this.getSystemService(ACTIVITY_SERVICE);
            List <ActivityManager.RunningServiceInfo> rs = am.getRunningServices(50);
            but_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int period = 0;
                int i = 0;
                i = spin.getId();
                switch (i){
                    case 0: period = 1440; break;
                    //case 1: period =

                }

                startService(new Intent(MyActivity.this, WallService.class));
                TX.setText("1");
            }
        });
        but_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MyActivity.this, WallService.class));
            }
        });
    }
}
