package com.example.WidgetWallPaper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MyAdapter extends BaseAdapter {
    int  mGallery;
    Context mContext;
    int  image[] = new int[4];
    public MyAdapter(Context context) {
        this.mContext = context;
        for (int i = 0; i < image.length; i++){
            switch (i){
                case 0: image[i] = R.drawable.a;
                    break;
                case 1: image[i] = R.drawable.b;
                    break;
                case 2: image[i] = R.drawable.c;
                    break;
            }
        }

    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view1, ViewGroup viewGroup) {
        ImageView view = new ImageView(mContext);
        view.setImageResource(image[i]);
        //Toast.makeText(mContext, Integer.toString(i), Toast.LENGTH_LONG).show();
        return view;
    }
}
