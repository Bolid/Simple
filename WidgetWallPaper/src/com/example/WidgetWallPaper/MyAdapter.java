package com.example.WidgetWallPaper;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    final String TAG = "AppAdapter";
    int mGallary;
    Context mContext;
    ImageView imageView;
    AsyncLoadImage asyncLoadImage;
    WallHistory wallHistory = new WallHistory();
    Bitmap bitmap[];
    List list = new ArrayList();
    int  image[] = new int[4];
    public MyAdapter(Context context, ImageView imageView) {
        this.mContext = context;
        this.imageView = imageView;
        list = wallHistory.getUrl();
        bitmap = new Bitmap[list.size()];
        asyncLoadImage = new AsyncLoadImage(bitmap, list);
        try {
            asyncLoadImage.execute();
            Log.v(TAG, "Статус потока: " + String.valueOf(asyncLoadImage.getStatus()));

        } catch (Exception e) {
            Log.e(TAG, "Ошибка", e);
        }


        for (int j = 0; j < list.size(); j++){
            Log.v(TAG, "Ссылки из истории: " + list.get(j) + "\n");
        /*for (int i = 0; i < image.length; i++)
            switch (i){
                case 0: image[i] = R.drawable.a;
                    break;
                case 1: image[i] = R.drawable.b;
                    break;
                case 2: image[i] = R.drawable.c;
                    break;
            }  */
        }

    }

    @Override
    public int getCount() {
        //return image.length;
        return bitmap.length;
    }

    @Override
    public Object getItem(int i) {
        return image[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view1, ViewGroup viewGroup) {
        ImageView view = new ImageView(mContext);
        view.setImageBitmap(bitmap[i]);
        //view.setImageResource(image[i]);
        view.setPadding(25, 25, 25, 25);
        //view.setLayoutParams(new Gallery.LayoutParams(140, 190));
        view.setScaleType(ImageView.ScaleType.FIT_START);
        //view.setBackgroundResource(mGallary);
        //Toast.makeText(mContext, Integer.toString(i), Toast.LENGTH_LONG).show();
        //Log.v(TAG, "Статус потока: " + String.valueOf(asyncLoadImage.getStatus()));
        return view;
    }
}
