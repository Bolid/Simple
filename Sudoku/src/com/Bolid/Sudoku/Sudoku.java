package com.Bolid.Sudoku;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

public class Sudoku extends Activity {

    @Override
    public void onCreate(Bundle save){
        super.onCreate(save);
        setContentView(R.layout.sudoku_main);
        GridView gridView = (GridView)findViewById(R.id.gridView);
        TextView textView = (TextView)findViewById(R.id.textView);
    }
}
