package com.example.WidgetWallPaper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;

public class FormGallery extends Activity {
    public String TAG = "FormGallery";
    @Override
    public void onCreate(Bundle saveIns){
        super.onCreate(saveIns);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.formgallery);
        Gallery gallery = (Gallery)findViewById(R.id.gallery);
        Button but1 = (Button)findViewById(R.id.Hbut1);
        gallery.setAdapter(new MyAdapter(this));
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WallHistory wallHistory = new WallHistory();
                Log.v("История.", "Разбор истории начат");
                wallHistory.getUrl();
                Log.v("История.", "Разбор истории завершен");
            }
        });

    }
    public void onStop() {
        super.onStop();
        finish();
        Log.v(TAG, "Форма убита");

    }
}
