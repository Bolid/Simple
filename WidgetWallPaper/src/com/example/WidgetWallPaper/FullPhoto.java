package com.example.WidgetWallPaper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FullPhoto extends Activity{
    Boolean buttonShow = true;
    public void onCreate(Bundle savIns){
        super.onCreate(savIns);
        setContentView(R.layout.formfullphoto);
        final ImageView imageView = (ImageView)findViewById(R.id.imageView);
        final Button butDel = (Button)findViewById(R.id.butDel);
        final Button butSave = (Button)findViewById(R.id.butSave);
        String urlFullImage = getIntent().getExtras().getString("com.example.WidgetWallPaper.FormGallery");
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                LoadContent loadContent = new LoadContent(null, null, null, null, 0);
                return loadContent.loadImage("http://img-fotki.yandex.ru/get/4105/beysbolka.e/0_397aa_4adbd49b_XL");
            }
            protected void onPostExecute(Bitmap bitmap){
                Log.v("com.example.WidgetWallPaper.FormGallery", bitmap.toString());
                imageView.setImageBitmap(bitmap);
            }
        }.execute(null);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonShow) {
                    butDel.setVisibility(View.INVISIBLE);
                    butSave.setVisibility(View.INVISIBLE);
                    buttonShow = false;
                }
                else {
                    butDel.setVisibility(View.VISIBLE);
                    butSave.setVisibility(View.VISIBLE);
                    buttonShow = true;
                }
            }
        });
    }
}
