package com.sample.texteditor;

import android.R.color;
import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.FloatMath;
import android.view.View;
import android.view.View.OnClickListener;
import android.graphics.Typeface;


public class TextEditorActivity extends Activity 
	implements OnClickListener{
	
	private float mTextSize = 26;
	private EditText mText;
	private TextView mTx;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mText = (EditText)findViewById(R.id.editor);
        mTx = (TextView)findViewById(R.id.label);
        	final Button buttonR = (Button)findViewById(R.id.button_r);
        	final Button buttonB = (Button)findViewById(R.id.button_b);
        	final Button buttonI = (Button)findViewById(R.id.button_i);
        	final Button buttonPlus = (Button)findViewById(R.id.button_plus);
        	final Button buttonMinus = (Button)findViewById(R.id.button_minus);
        	
        	
        	buttonR.setOnClickListener(this);
        	buttonB.setOnClickListener(this);
        	buttonI.setOnClickListener(this);
        	buttonPlus.setOnClickListener(this);
        	buttonMinus.setOnClickListener(this);}
        	public void onClick(View v){
        	switch (v.getId()) {
			case R.id.button_r:
				mText.setTypeface(null, Typeface.NORMAL);
				break;
			case R.id.button_b:
				mText.setTypeface(null, Typeface.BOLD);
				break;
			case R.id.button_i:
				mText.setTypeface(null, Typeface.ITALIC);
				break;	
			case R.id.button_plus:
				if (mTextSize <= 36)
				{					
					mTextSize+=2;
					mText.setTextSize(mTextSize);
					mTx.setText(Float.toString(mTextSize));
					if (mTextSize >= 36)
						mTx.setText("Размер текста слишком большой (" +
								Float.toString(mTextSize)+")! Уменьшите его.");
							
				}	
				
				break;
			case R.id.button_minus:
				if (mTextSize >= 10)
				{
					mTextSize-=2;
					mTx.setText(Float.toString(mTextSize));
					mText.setTextSize(mTextSize);
					if (mTextSize <= 10)
						mTx.setText("Размер текста слишком маленький(" +
								Float.toString(mTextSize)+")! Увеличьте его.");
					
				}
				break;

			default:
				break;
			}
			
    }
}