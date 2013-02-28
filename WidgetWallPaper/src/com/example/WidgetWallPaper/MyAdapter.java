package com.example.WidgetWallPaper;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    final String TAG = "AppAdapter";
    Boolean grid;
    int mGallary;
    Context mContext;
    //AsyncLoadImage asyncLoadImage;
    WallHistory wallHistory = new WallHistory();
    //Bitmap bitmap[];
    List list = new ArrayList();
    String nameSmall[];
    int  image[] = new int[4];
    public MyAdapter(Context context, Boolean grid) {
        this.mContext = context;
        this.grid = grid;
        this.list = wallHistory.getUrl();
        nameSmall = new String[list.size()];
        for (int i = 0; i < list.size(); i++){
            Log.v(TAG, "Имя: "+ list.get(i)+"\nIndex list: "+list.indexOf(list.get(i)));
        }
        /*list = wallHistory.getUrl();
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

        for (int i = 0; i < image.length; i++)
            switch (i){
                case 0: image[i] = R.drawable.aa;
                    break;
                case 1: image[i] = R.drawable.b;
                    break;
                case 2: image[i] = R.drawable.c;
                    break;
            }       */
        //}

    }

    @Override
    public int getCount() {
        return list.size();
        //return bitmap.length;
    }

    @Override
    public Object getItem(int i) {
        Log.v(TAG, "Индекс getItem: "+i);
        return i;
    }

    @Override
    public long getItemId(int i) {
        Log.v(TAG, "Индекс getItemId: "+i);
        return i;
    }

    @Override
    public View getView(int i, View view1, ViewGroup viewGroup) {
        ImageView view = new ImageView(mContext);
        //view.setImageBitmap(bitmap[i]);
        //view.setImageResource(R.drawable.aa);
        if (grid == false)
        {
        try{
            File file = new File(Environment.getExternalStorageDirectory()+"/photos/"+list.get(i));
                    //file.mkdir();
            Log.v(TAG, "Путь " + file.getPath()+"\nЭлемент: "+list.get(i));

        //Uri uri = Uri.fromFile(new File("/data/data/com.example.WidgetWallPaper/photos/photo.jpg"));
        //view.setImageDrawable(Drawable.createFromPath("/data/data/com.example.WidgetWallPaper/photos/photo.jpg"));
      //if (file.isFile()){
            Log.v(TAG, "Файл найден.\nУстанавливаем...");
            view.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
            view.setPadding(5, 25, 5, 25);
       //}
        //view.setLayoutParams(new Gallery.LayoutParams(140, 190));
        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //view.setBackgroundResource(mGallary);
        //Toast.makeText(mContext, Integer.toString(i), Toast.LENGTH_LONG).show();
        //Log.v(TAG, "Статус потока: " + String.valueOf(asyncLoadImage.getStatus()));
        }
        catch (ArrayIndexOutOfBoundsException aioobe){
            Log.e(TAG, "Ошибка: ", aioobe);
        }
        }
        return view;
    }




}
