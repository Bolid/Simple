package com.sample.sliding_2;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Sliding_2Activity extends Activity 
implements OnClickListener

{
   

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.page_fix);
        View ViewP = (ViewPager)findViewById(R.id.view_pager);
        
        LayoutInflater inflater = LayoutInflater.from(this);
        List<View> pages = new ArrayList<View>();
               
        
        
        ViewP =  inflater.inflate(R.layout.page_fix, null);
        pages.add(ViewP);
        
        ViewP = inflater.inflate(R.layout.page_fix, null);
        pages.add(ViewP);
        
        
        
        
        SamplePagerAdapter pagerAdapter = new SamplePagerAdapter (pages);
        ViewPager viewPager = new ViewPager(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        
        setContentView(viewPager);
        
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
