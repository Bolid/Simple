package com.sample.button;

import java.security.PublicKey;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

public class ButtonActivity extends Activity {
	private TextView mText;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mText = (TextView)findViewById(R.id.textview);
        final Button Button_1 = (Button)findViewById(R.id.Button_1);
        final Button Button_2 = (Button)findViewById(R.id.Button_2);
        Button_1.setOnClickListener(this);
        Button_2.setOnClickListener(this);
        @Override
        public void onClick(View v)
        switch (v.getId()) {
		case R.id.Button_1:
			mText.setText ("1");			
			break;
		case R.id.Button_2:
			mText.setText("2");
			break;
		}
    }
}