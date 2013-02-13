package com.example.WidgetWallPaper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;

public class FormGallery extends Activity {
    public String TAG = "FormGallery";
    @Override
    public void onCreate(Bundle saveIns){
        super.onCreate(saveIns);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.formgallery);
        Gallery gallery = (Gallery)findViewById(R.id.gallery);
        gallery.setAdapter(new MyAdapter(this));
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }
    public void onStop() {
        super.onStop();
        finish();
        Log.v(TAG, "Форма убита");

    }
}
