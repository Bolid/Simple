package com.sample.sliding;

import org.xmlpull.v1.XmlPullParser;

import android.R.layout;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

public class SlidingActivity extends Activity 
implements OnTouchListener
{
	private
	float fromPosition;
	ViewFlipper flipper = null;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        LinearLayout mainLayout = (LinearLayout)findViewById(R.id.main_layout);
        mainLayout.setOnTouchListener(this);
        
        flipper = (ViewFlipper)findViewById(R.id.VF);
        
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int Layouts[] = new int[]{R.layout.flip_1, R.layout.flip_2};
        for (int layout: Layouts)
		//XmlPullParser layout = null;
		flipper.addView(inflater.inflate(layout, null));
    }

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		// TODO Auto-generated method stub
		{
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				fromPosition = event.getX(); 				
				break;
			case MotionEvent.ACTION_UP:
				float secondPosition = event.getX();
				if (fromPosition > secondPosition)
				{
					flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_from_one));
					flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_from_two));
					flipper.showNext();
				}	
				else if (fromPosition < secondPosition)
				{
					flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_second_one));
					flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_second_two));
					flipper.showPrevious();
				}

			default:
				break;
			}
		}
		return true;
	}
}