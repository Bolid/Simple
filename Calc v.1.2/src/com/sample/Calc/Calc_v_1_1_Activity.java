package com.sample.Calc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ToggleButton;
import android.widget.TextView;
import android.widget.CompoundButton;



public class Calc_v_1_1_Activity extends Activity 
	implements OnClickListener, CompoundButton.OnCheckedChangeListener
	{
	private
	TextView mED;
	ToggleButton button_memory;
	String Symbol = "";
	int n = 0;
	int i = 0;
	String[] Example = new String[n];
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mED = (TextView)findViewById(R.id.ET1);
        button_memory = (ToggleButton)findViewById(R.id.Memory_copy);
        final Button button_1 = (Button)findViewById(R.id.But_1);
        final Button button_2 = (Button)findViewById(R.id.But_2);
        final Button button_3 = (Button)findViewById(R.id.But_3);
        final Button button_4 = (Button)findViewById(R.id.But_4);
        final Button button_5 = (Button)findViewById(R.id.But_5);
        final Button button_6 = (Button)findViewById(R.id.But_6);
        final Button button_7 = (Button)findViewById(R.id.But_7);
        final Button button_8 = (Button)findViewById(R.id.But_8);
        final Button button_9 = (Button)findViewById(R.id.But_9);
        final Button button_0 = (Button)findViewById(R.id.But_0);
        final Button button_M = (Button)findViewById(R.id.But_M);
        final Button button_T = (Button)findViewById(R.id.But_T);
        final Button button_Umnoj = (Button)findViewById(R.id.But_umnoj);
        final Button button_Ravno = (Button)findViewById(R.id.But_ravno);
        final Button button_Del = (Button)findViewById(R.id.But_del);
        final Button button_Plus = (Button)findViewById(R.id.But_plus);
        final Button button_Minus = (Button)findViewById(R.id.But_minus);
        final Button button_Clear = (Button)findViewById(R.id.Clear);
        final Button button_memory_paste = (Button)findViewById(R.id.Memory_paste);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_0.setOnClickListener(this);
        button_Umnoj.setOnClickListener(this);
        button_Ravno.setOnClickListener(this);
        button_Del.setOnClickListener(this);
        button_Plus.setOnClickListener(this);
        button_Minus.setOnClickListener(this);
        button_Clear.setOnClickListener(this);
        button_M.setOnClickListener(this);
        button_T.setOnClickListener(this);
        button_memory_paste.setOnClickListener(this);
        button_memory.setOnCheckedChangeListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.But_1:
			Symbol = Symbol + "1";
			Example[i]=Symbol;
			mED.setText(mED.getText().toString()+Example[i]);
			break;

		default:
			break;
		}
		
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		
	}
}
