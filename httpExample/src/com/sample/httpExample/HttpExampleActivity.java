package com.sample.httpExample;

import java.text.SimpleDateFormat;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.Calendar;


public class HttpExampleActivity extends Activity
//implements OnClickListener
{
 
LinearLayout ll;	
TextView TX;
ImageView IM;
Bitmap bitmap = null;
String result = null;
Gallery gal;
int i = 0;
boolean item;

/** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.main);
    gal = (Gallery)findViewById(R.id.Gall);
	TX = (TextView)findViewById(R.id.TX1);
	IM = (ImageView)findViewById(R.id.IM);
    TX.setVisibility(TextView.INVISIBLE);
    final Button but_1 = (Button)findViewById(R.id.But1);
    final Button but_2 = (Button)findViewById(R.id.But2);
    final Button but_3 = (Button)findViewById(R.id.But3);
    //but_1.setOnClickListener(this);
    //but_2.setOnClickListener(this);
   /*gal.setAdapter(new ImageAdapter(this));
    gal.setOnItemClickListener(new OnItemClickListener() {
    	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
    		TX.setText("123");
    		
    	}
    	
	});*/
    but_1.setOnClickListener(new View.OnClickListener(){

		@Override
		public void onClick(View v) {
			String url = "";
			String fDate = "";
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, 0);
			SimpleDateFormat pattern = new SimpleDateFormat();
			pattern.applyPattern("yyyy-MM-dd");
			fDate = pattern.format(calendar.getTime());
			url = "http://api-fotki.yandex.ru/api/podhistory/poddate;"+fDate+"/?limit=1";
			ACDemo loadFile = new ACDemo(url, TX, IM, getApplicationContext());	
			loadFile.execute();
		}
    	
    });
    but_2.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			IM.setImageBitmap(null);
			TX.setText(null);			
		}
	});
    but_3.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//TX.setVisibility(TextView.VISIBLE);
			//TX.append("ГУЙ не блокируется! ");
			Intent childForm = new Intent(HttpExampleActivity.this, childActivity.class);
			startActivity(childForm);
		}
	});
    }



}


