package com.sample.textview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TextViewActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final TextView text3 = (TextView)findViewById(R.id.text3);
        text3.setText("Hello, World! Привет Привет Привет Привет Привет Привет Привет Привет Привет");
        final TextView text4 = (TextView)findViewById(R.id.text4);
        text4.setText(R.string.text_hello);
    }
}