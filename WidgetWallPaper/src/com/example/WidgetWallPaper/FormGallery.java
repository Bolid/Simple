package com.example.WidgetWallPaper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class FormGallery extends Activity {
    public String TAG = "FormGallery";
    @Override
    public void onCreate(Bundle saveIns){
        super.onCreate(saveIns);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.formgallery);
        Gallery gallery = (Gallery)findViewById(R.id.gallery);
        final ImageView imageView = (ImageView)findViewById(R.id.IV);
        //LoadContent loadContent = new LoadContent(null, null, null, null);
        //imageView.setImageBitmap(loadContent.loadImage("http://img-fotki.yandex.ru/get/6600/81563225.3de/0_738ec_c77fefa5_XL"));
        final Button but1 = (Button)findViewById(R.id.Hbut1);
        //AsyncLoadImage asyncLoadImage = new AsyncLoadImage(gallery);
        gallery.setAdapter(new MyAdapter(this, imageView));
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                but1.setText(i);
                //imageView.setImageResource(view);
            }
        });
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WallHistory wallHistory = new WallHistory();
                Log.v("История.", "Разбор истории начат");
                List list = new ArrayList();
                    list = wallHistory.getUrl();
                for (int i = 0; i<list.size(); i++)
                    Log.v(TAG, "Ссылки из истории: " + list.get(i) +"\n");
                Log.v("История.", "Разбор истории завершен");
            }
        });

    }
    public void onStop() {
        super.onStop();
        finish();
        Log.v(TAG, "Форма убита");

    }
}
