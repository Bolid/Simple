package com.example.WidgetWallPaper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class FullPhoto extends Activity{
    final String TAG = "com.example.WidgetWallPaper.FullPhoto";
    Boolean buttonShow = true;
    Bitmap imageBitmap = null;
    public void onCreate(Bundle savIns){
        super.onCreate(savIns);
        setContentView(R.layout.formfullphoto);
        final ImageView imageView = (ImageView)findViewById(R.id.imageView);
        final Button butDel = (Button)findViewById(R.id.butDel);
        final Button butSave = (Button)findViewById(R.id.butSave);
        final ArrayList<String> parameters = (ArrayList<String>)getIntent().getExtras().get("com.example.WidgetWallPaper.FormGallery");
        final LoadContent loadContent = new LoadContent(getBaseContext(), null, null, null, 0);
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                try {
                    imageBitmap = loadContent.loadImage(strings[0]);
                    return imageBitmap;
                } catch (MalformedURLException e) {
                    Log.e(TAG, "Error mail: ", e);
                    return BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/photos/" + parameters.get(1));
                } catch (IOException e) {
                    Log.e(TAG, "Error: ", e);
                    return BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/photos/" + parameters.get(1));
                }
            }
            protected void onPostExecute(Bitmap bitmap){
                if (bitmap != null)
                imageView.setImageBitmap(bitmap);
            }
        }.execute(parameters.get(0));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (buttonShow) {
                    butDel.setVisibility(View.INVISIBLE);
                    butSave.setVisibility(View.INVISIBLE);
                    buttonShow = false;
                }
                else {
                    butDel.setVisibility(View.VISIBLE);
                    butSave.setVisibility(View.VISIBLE);
                    buttonShow = true;
                }*/
            }
        });
        butSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    loadContent.pasteImage(imageBitmap);
                } catch (IOException ioe) {
                    Log.e(TAG, "Error. ", ioe);
                }
            }
        });
    }
}
