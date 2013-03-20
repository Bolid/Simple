package com.Bolid.Sudoku;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class ArrayAdapterSudoku extends ArrayAdapter<String> {

    Context mContext;
    public ArrayAdapterSudoku(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.mContext = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        TextView textView = (TextView)convertView;


        return null;
    }
}
