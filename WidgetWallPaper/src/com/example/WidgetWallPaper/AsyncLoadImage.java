package com.example.WidgetWallPaper;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AsyncLoadImage extends AsyncTask {
    LoadContent loadContent = new LoadContent(null, null, null, null);
    Bitmap[] bitmap;
    List list = new ArrayList();
    public AsyncLoadImage(Bitmap[] bitmap, List list){
        this.bitmap = bitmap;
        this.list = list;
    }

    @Override
    protected Object doInBackground(Object... objects) {
        for (int i = 0; i < list.size(); i++){
            //bitmap[i] = loadContent.loadImage(String.valueOf(list.get(i)));
            Log.v("Дочерний поток: ", "Работает");
        }
        return null;
    }
}
