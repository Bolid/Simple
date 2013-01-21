package com.sample.checkbox;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class CheckBoxActivity extends Activity 
	implements CompoundButton.OnCheckedChangeListener
	{
	private
		int A = 0;
		CheckBox mCheckB;
		CheckBox mCheckI;
		EditText mED;
		ToggleButton mTG;
	private	TextView mTX;		
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mCheckB = (CheckBox)findViewById(R.id.ChBox1);
        mCheckI = (CheckBox)findViewById(R.id.ChBox2);
        mED = (EditText)findViewById(R.id.ED);
        mTX = (TextView)findViewById(R.id.TV);
        mTG = (ToggleButton)findViewById(R.id.TB);
        mCheckB.setOnCheckedChangeListener(this);
        mCheckI.setOnCheckedChangeListener(this);
        mTG.setOnCheckedChangeListener(this);
    }

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		//Italic
		A = 1+1;
		if (mCheckI.isChecked()) 
		{
			mED.setTypeface(null, Typeface.ITALIC);
			mCheckI.setText("Курсив");
		}
		else
		{
			mED.setTypeface(null, Typeface.NORMAL);
			mCheckI.setText("Нормальный");
		}
		//Bold
		if (mCheckB.isChecked()) 
		{
			mED.setTypeface(null, Typeface.BOLD);
			mCheckB.setText("Жирный");
		}
		else
		{
			mED.setTypeface(null, Typeface.NORMAL);
			mCheckB.setText("Нормальный");
		}
		//ToggleButton
		if (mTG.isChecked()) 
			mTX.setText("ON");
		else
			mTX.setText("OFF");
		
	}
}