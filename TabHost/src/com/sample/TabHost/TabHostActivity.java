package com.sample.TabHost;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class TabHostActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TabHost tabs = (TabHost)findViewById(R.id.TH);
        tabs.setup();
        tabs.addTab(R.id.ED_1, "Hello" , "Hello");
        //TabHost.TabSpec
        //spec = tabs.newTabSpec("tag1");
        //spec.setContent(R.id.ED_1);
        //spec.setIndicator("Документ №1");
        //tabs.addTab(spec);
        
       // spec = tabs.newTabSpec("tag2");
       // spec.setContent(R.id.ED_2);
       // spec.setIndicator("Документ №2");
       //.addTab(spec);
        
       // tabs.setCurrentTab(0);
    }
}