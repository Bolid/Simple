package com.example.WidgetWallPaper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    final String TAG = "AppAdapter";
    int mGallary;
    int bitmapHeight;
    int bitmapWidth;
    int pixels[];
    Context mContext;
    WallHistory wallHistory = new WallHistory();
    List list = new ArrayList();
    String nameSmall[];
    int  image[] = new int[4];
    final int maxMemory = (int)(Runtime.getRuntime().maxMemory() / 1024);
    final int casheSize = maxMemory / 2;
    private LruCache<String, Bitmap> mMemoryCashe;
    public MyAdapter(Context context) {
        Log.v(TAG, "Память: " + maxMemory);
        this.mContext = context;
        this.list = wallHistory.getUrl();
        nameSmall = new String[list.size()];
        for (int i = 0; i < list.size(); i++){
            Log.v(TAG, "Имя: "+ list.get(i)+"\nIndex list: "+list.indexOf(list.get(i)));
        }
        mMemoryCashe = new LruCache<String, Bitmap>(casheSize){
            @Override
            protected int sizeOf (String key, Bitmap bitmap){
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public void addBitmapToMemoryCache(String key){
        if (getBitmapFromMemCache(key) == null)
            mMemoryCashe.put(key, BitmapFactory.decodeFile(key));
    }

    public Bitmap getBitmapFromMemCache(String key){
        return mMemoryCashe.get(key);
    }

    public void clearBitmapToMemoryCache(String key){
        if (getBitmapFromMemCache(key) != null)
            mMemoryCashe.remove(key);
    }

    @Override
    public int getCount() {
        return list.size();
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
        /*BitmapFactory bitmapFactory = new BitmapFactory();
        Bitmap origBitmap = Bitmap.createBitmap(bitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/photos/" + list.get(i)));
        //Log.v(TAG, "Width (до кадрирования) = "+origBitmap.getWidth()+"\nДлина (до кадрирования) = "+origBitmap.getHeight());
        Bitmap bitmap = null;
        try{
            if (origBitmap.getWidth() > origBitmap.getHeight()){
                Log.v(TAG, "Книжная ориентация");
                bitmapHeight = origBitmap.getHeight(); //высота
                bitmapWidth = origBitmap.getHeight(); //ширина
                pixels = new int[bitmapHeight * bitmapWidth];
                bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
                origBitmap.getPixels(pixels, 0, bitmapWidth, (origBitmap.getWidth()-origBitmap.getHeight())/2, 0, bitmapWidth, bitmapHeight);
                bitmap.setPixels(pixels,0,bitmapWidth,0,0,bitmapWidth,bitmapHeight);

                bitmap = Bitmap.createScaledBitmap(bitmap, 720/3-4, 720/3-4, false);
            }
            else{
                Log.v(TAG, "Альбомная ориентация");
                bitmapHeight = origBitmap.getWidth();
                bitmapWidth = origBitmap.getWidth();
                int pixels[] = new int[bitmapHeight * bitmapWidth];
                bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
                origBitmap.getPixels(pixels, 0, bitmapHeight,0, (origBitmap.getHeight()-origBitmap.getWidth())/2, bitmapWidth, bitmapHeight);
                bitmap.setPixels(pixels, 0, bitmapHeight, 0, 0, bitmapWidth, bitmapHeight);
                bitmap = Bitmap.createScaledBitmap(bitmap, 720/3-4, 720/3-4, false);
            }
            //Log.v(TAG, "Width = "+bitmap.getWidth()+"\nДлина = "+bitmap.getHeight());
        }catch (IllegalStateException ise){
            Log.e(TAG, "Ошибка bitmap: ", ise);
        }catch (IllegalArgumentException iae){
            Log.e(TAG, "Ошибка bitmap: ", iae);
        }catch (ArrayIndexOutOfBoundsException aioobe){
            Log.e(TAG, "Ошибка bitmap: ", aioobe);
        }catch (OutOfMemoryError oome){
            Log.e(TAG, "НЕДОСТАТОЧНО ПАМЯТИ!", oome);
            //bitmap.recycle();
            FormGallery formGallery = new FormGallery();
            //formGallery.onDestroy();
            formGallery = null;
            System.gc();
        }
         //Drawable drawable = Drawable.createFromPath(Environment.getExternalStorageDirectory() +"/photos/" + list.get(i));
        //drawable.setBounds(25,25,25,25);*/
        ImageView view = new ImageView(mContext);
        //view.setImageBitmap(bitmap);
        //view.setImageBitmap(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() +"/photos/" + list.get(i)));
        File file = new File(Environment.getExternalStorageDirectory() + "/photos/" + list.get(i));
        if (getBitmapFromMemCache(file.getPath()) != null){
            view.setImageBitmap(getBitmapFromMemCache(file.getPath()));
            Log.v(TAG, "Взято из кэша");
        }
        else {
            BitmapAddWorkerTask task = new BitmapAddWorkerTask();
            task.execute(file.getPath());
            view.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
            Log.v(TAG, "Нет ничего");
        }
        view.setPadding(1, 1, 1, 1);
        /*try{
           // Log.v(TAG, "Путь " + Environment.getExternalStorageDirectory() + "/photos/" + list.get(i) + "\nЭлемент: " + i);
            //Log.v(TAG, "Файл найден.\nУстанавливаем...");
            //if (origBitmap.getWidth() > origBitmap.getHeight())
                //view.setImageBitmap(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() +"/photos/" + list.get(i)));
            view.setPadding(5, 5, 5, 5);
            //view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //drawable = null;
        }
        catch (ArrayIndexOutOfBoundsException aioobe){
            Log.e(TAG, "Ошибка: ", aioobe);
        }catch (Exception e){
            Log.e(TAG, "Ошибка: ", e);
        }
        bitmapFactory = null;
        origBitmap = null;
        SoftReference softReference = new SoftReference(bitmap, referenceQueue);
        bitmap = null;
        System.gc();
        Log.e(TAG, "Доступность ссылки: "+softReference.get());
        //bitmap.recycle();*/
        return view;
    }

    public void loadBitmap(String resId, ImageView imageView){
        final String imageKey = resId;
        final Bitmap bitmap = getBitmapFromMemCache(imageKey);
        if (bitmap != null){
            imageView.setImageBitmap(bitmap);
            Log.v(TAG, "Взято из кэша");
        }
        else{
            imageView.setImageBitmap(BitmapFactory.decodeFile(resId));
            BitmapAddWorkerTask task = new BitmapAddWorkerTask();
            task.execute(resId);
            Log.v(TAG, "Нет ничего");
        };
    }
    class BitmapAddWorkerTask extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            bitmap = Bitmap.createBitmap(BitmapFactory.decodeFile(params[0]));
            addBitmapToMemoryCache(params[0]);
            return bitmap;
        }
    }
    class BitmapDelWorkedTask extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... params) {
            clearBitmapToMemoryCache(params[0]);
            return null;
        }
    }




}
