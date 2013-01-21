package com.sample.httpExample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class childActivity extends Activity{
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.child);
		final Button childBut_1 = (Button)findViewById(R.id.childBut_1);
		childBut_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onDestroy();
			}
		});
		
	}
	public void onDestroy(){
		super.onDestroy();
	}

}
