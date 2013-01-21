package com.sample.imageview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageViewActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final ImageView image = (ImageView)findViewById(R.id.image2);
        image.setImageResource(R.drawable.android_2);
        
        
    }
}