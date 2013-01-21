package com.sample.buttonapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class ButtonAppActivity extends Activity {
	private TextView mText;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mText = (TextView)findViewById(R.id.textview);
        
        final Button Button_1 = (Button)findViewById(R.id.Button_1);
        Button_1.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v){
        		mText.setText ("1");
        	}
        });
        final Button Button_2 = (Button)findViewById(R.id.Button_2);
        Button_2.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v){
        		mText.setText ("2");
        	}
        });
			
		
        
    }
}