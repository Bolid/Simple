package com.example.WidgetWallPaper;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.Toast;

public class formGallery extends ButActiv {
    @Override
    public void onCreate(Bundle saveIns){
        super.onCreate(saveIns);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.formgallery);
        String TAG = "formGallery";
        Gallery gallery = (Gallery)findViewById(R.id.gallery);
        gallery.setAdapter(new myAdapter(this));
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }
}
