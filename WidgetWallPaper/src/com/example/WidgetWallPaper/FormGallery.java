package com.example.WidgetWallPaper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.*;

import java.util.ArrayList;

public class FormGallery extends Activity {
    public String TAG = "com.example.WidgetWallPaper.FormGallery";
    Boolean grid = false;
    GridView gridView;
    MyAdapter myAdapter;
    @Override
    public void onCreate(Bundle saveIns){
        super.onCreate(saveIns);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.formgallery);
        final ImageView imageView = (ImageView)findViewById(R.id.imageView);
        final Gallery gallery = (Gallery)findViewById(R.id.galleryView);
        gridView = (GridView)findViewById(R.id.gridView);
        final ImageButton butViewGallery = (ImageButton)findViewById(R.id.butViewGallery);
        final ImageButton butViewGrid = (ImageButton)findViewById(R.id.butViewGrid);
        butViewGallery.setVisibility(View.INVISIBLE);
        butViewGrid.setVisibility(View.INVISIBLE);
        SharedPreferences mSetting = getSharedPreferences("AppSetting", Context.MODE_PRIVATE);
        final int sizePicMini = mSetting.getInt("sizePicMini", 154);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aa);
        myAdapter = new MyAdapter(this, bitmap, mSetting.getInt("sizePicMini", 154));
        gridView.setAdapter(myAdapter);
        //gallery.setAdapter(myAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    Log.v(TAG, "Name photo: " + myAdapter.listNamePhoto.get(i));
                    Intent intentStartFormFullPhoto = new Intent(getBaseContext(), FullPhoto.class);
                    ArrayList<String> parameters = new ArrayList<String>(2);
                    parameters.add(0, myAdapter.listUrlPhoto.get(i).toString());
                    parameters.add(1, myAdapter.listNamePhoto.get(i).toString());
                    intentStartFormFullPhoto.putExtra(TAG, parameters);
                    startActivity(intentStartFormFullPhoto);
                }
                catch (Exception e){
                    Log.e(TAG, "Error: " + e);
                }
            }
        });
        butViewGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //File file = new File("/data/data/com.example.WidgetWallPaper/photos/");
                //file.delete();
                gallery.setAdapter(myAdapter);
                butViewGrid.setEnabled(true);
                butViewGallery.setEnabled(false);
            }
        });
        butViewGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //File file = new File(Environment.getExternalStorageDirectory()+"/photos/1932383216649687.jpg");
                //file.mkdir();
                //imageView.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
               // Log.v(TAG, "Путь к файлу 1932383216649687.jpg: "+file.getPath());
                gridView.setAdapter(myAdapter);
                butViewGallery.setEnabled(true);
                butViewGrid.setEnabled(false);
            }
        });

    }
    public void onStop() {
        super.onStop();
//        myAdapter = null;
//        gridView = null;
        //finish();
        System.gc();
        Log.v(TAG, "Форма убита");

    }
    public void onDestroy(){
        super.onDestroy();
    }
}
