package com.sample.scroll;





import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ScrollAppActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView text = (TextView)findViewById(R.id.textview);
        text.setText("Hello, World! Привет Привет Привет Привет Привет Привет Привет Привет Привет");
    }
}