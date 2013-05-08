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
import java.util.Calendar;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    final String TAG = "com.example.WidgetWallPaper.AppAdapter";
    Boolean pasteBitmap = false;
    ImageView view = null;
    int idVisibleView = 0;
    File file = null;
    Calendar calendar = Calendar.getInstance();
    Long time = Long.valueOf(0);
    Context mContext;
    WallHistory wallHistory = new WallHistory();
    List listNamePhoto = new ArrayList();
    List listURLFullPhoto = new ArrayList();
    String nameSmall[];
    final int maxMemory = (int)(Runtime.getRuntime().maxMemory() / 1024);
    final int casheSize = maxMemory / 4;
    private LruCache<String, Bitmap> mMemoryCashe;
    Bitmap bitmap = null;
    public MyAdapter(Context context, Bitmap bitmap, int sizePr) {
        this.bitmap = Bitmap.createScaledBitmap(bitmap, sizePr, sizePr, false);
        Log.v(TAG, "Память: " + maxMemory);
        this.mContext = context;
        this.listNamePhoto = wallHistory.getUrl();
        nameSmall = new String[listNamePhoto.size()];
        mMemoryCashe = new LruCache<String, Bitmap>(casheSize){
            @Override
            protected int sizeOf (String key, Bitmap bitmap){
                return bitmap.getByteCount() / 1024;
            }
        };

        BitmapInsertAllCache bitmapInsertAllCache = new BitmapInsertAllCache();
        bitmapInsertAllCache.execute();
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
        return listNamePhoto.size();
    }

    @Override
    public Object getItem(int i) {
        Log.v(TAG, "Индекс getItem: "+i);
        return i;
    }

    @Override
    public long getItemId(int i) {
       // Log.v(TAG, "Индекс getItemId: " + i + " " + view);
        return i;
    }

    public int getViewTypeCount(){
        super.getViewTypeCount();
        Log.v(TAG, "Мы тут 0");
        return 1500;
    }

    public Boolean isScroll(Long update){
        //time = Long.valueOf((calendar.get(Calendar.MINUTE))*60000 + (calendar.get(Calendar.SECOND))*1000 + (calendar.get(Calendar.MILLISECOND)));
        Log.v(TAG, String.valueOf(update) +" "+ String.valueOf(time)+" "+ String.valueOf(update - time));
        if (update - time > 300){
            time = update;
            return true;
        }
        else{
            time = update;
            return false;
        }
    }

    public boolean isEmpty(){
        super.isEmpty();
        return false;
    }

    @Override
    public View getView(final int i, View view1, ViewGroup viewGroup) {
        idVisibleView = i;
        if (view1 == null)
            view = new ImageView(mContext);
        else
            view = (ImageView)view1;
        //Log.v(TAG,"Селектед: " + viewGroup.isSelected());
        file = new File(Environment.getExternalStorageDirectory() + "/photos/" + listNamePhoto.get(i));
        if (getBitmapFromMemCache(file.getPath()) != null){
            view.setImageBitmap(getBitmapFromMemCache(file.getPath()));
            //Log.v(TAG, "Взято из кэша " + viewGroup.isShown());
        }
        else {
            //BitmapAddWorkerTask task = new BitmapAddWorkerTask();
            //task.execute(file.getPath());
            //view.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
            calendar = Calendar.getInstance();

            try {
                view.setImageBitmap(bitmap);
                Log.v(TAG,"Селектед: " + i);
                new AsyncTask<View, String, Bitmap>(){
                    private ImageView v;
                    private int j = i;
                    private String path = file.getPath();

                        @Override
                        protected Bitmap doInBackground(View... params) {
                            v = (ImageView) params[0];
                            addBitmapToMemoryCache(path);
                            return getBitmapFromMemCache(path);
                        }

                        protected void onPostExecute(Bitmap bitmap1){
                            Log.v(TAG, "Мы тут 2 " + j + " " + i + " " + v.getId());
                            v.getId();
                               v.setImageBitmap(bitmap1);}
                    }.execute(view);
            }
            catch (ArrayIndexOutOfBoundsException e){
                Log.e(TAG, "Error: ", e);
            }
        }
        view.setPadding(1, 1, 1, 1);
        return view;
    }

    class BitmapInsertAllCache extends AsyncTask<String, Void,Void>{

        @Override
        protected Void doInBackground(String... strings) {
            int index = 0;
            if (listNamePhoto.size() > 20)
                index = 20;
            else index = listNamePhoto.size();
            for (int i = 0; i < index; i++){
                addBitmapToMemoryCache(Environment.getExternalStorageDirectory() +"/photos/"+listNamePhoto.get(i));
                Log.v(TAG, i + " Записали в кеш изображение: " + listNamePhoto.get(i));
            }
            return null;
        }
    }
}
