package com.example.WidgetWallPaper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.*;

public class FormGallery extends Activity {
    public String TAG = "FormGallery";
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
        //AsyncLoadImage asyncLoadImage = new AsyncLoadImage(gallery);
        myAdapter = new MyAdapter(this, grid);
        //gallery.setAdapter(myAdapter);

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v(TAG, "Адаптер: " + i + view);
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
                grid = true;
            }
        });

    }
    public void onStop() {
        super.onStop();
        finish();
        Log.v(TAG, "Форма убита");

    }
}
