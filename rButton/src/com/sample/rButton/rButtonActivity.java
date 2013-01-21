package com.sample.rButton;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;



public class rButtonActivity extends Activity
	implements OnClickListener
	{ 
	private TextView mText;
/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    final RadioButton radio1 = (RadioButton)findViewById(R.id.radio1);
    final RadioButton radio2 = (RadioButton)findViewById(R.id.radio2);
    final RadioButton radio3 = (RadioButton)findViewById(R.id.radio3);
    
    mText = (TextView)findViewById(R.id.TX);  
    radio1.setOnClickListener(this);
    radio2.setOnClickListener(this);
    radio3.setOnClickListener(this);
    }
@Override
public void onClick(View v) {
	switch (v.getId()){
	case R.id.radio1:
		mText.setText("Select: Mode #1.");
	break;
	case R.id.radio2:
		mText.setText("Select: Mode #2.");
	break;
	case R.id.radio3:
		mText.setText("Select: Mode #3.");
	break;
	
	}
	
}
    
}