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
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class FullPhoto extends Activity{
    final String TAG = "com.example.WidgetWallPaper.FullPhoto";
    Boolean buttonShow = true;
<<<<<<< HEAD
    Bitmap bitmapDesktop;
    ProgressBar progressBar;
    LoadContent loadContent = new LoadContent(this, null, null, null, 0);
=======
    Bitmap imageBitmap = null;
>>>>>>> d92dc7c10095abedadd4d45aa6cae43bbfa8b0d1
    public void onCreate(Bundle savIns){
        super.onCreate(savIns);
        setContentView(R.layout.formfullphoto);
        final ImageView imageView = (ImageView)findViewById(R.id.imageView);
        final Button butDel = (Button)findViewById(R.id.butDel);
        final Button butSetWallPaper = (Button)findViewById(R.id.setWallPaper);
        final ArrayList<String> parameters = (ArrayList<String>)getIntent().getExtras().get("com.example.WidgetWallPaper.FormGallery");
        final LoadContent loadContent = new LoadContent(getBaseContext(), null, null, null, 0);
        new AsyncTask<String, Void, Bitmap>() {
            protected void onPreExecute(){
                progressBar = (ProgressBar)findViewById(R.id.progressBar);
            }
            @Override
            protected Bitmap doInBackground(String... strings) {
                try {
<<<<<<< HEAD
                    bitmapDesktop = loadContent.loadImage(strings[0]);
                    return bitmapDesktop;
=======
                    imageBitmap = loadContent.loadImage(strings[0]);
                    return imageBitmap;
>>>>>>> d92dc7c10095abedadd4d45aa6cae43bbfa8b0d1
                } catch (MalformedURLException e) {
                    Log.e(TAG, "Error mail: ", e);
                    return BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/photos/" + parameters.get(1));
                } catch (IOException e) {
                    Log.e(TAG, "Error: ", e);
                    return BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/photos/" + parameters.get(1));
                }
            }
            protected void onPostExecute(Bitmap bitmap){
                progressBar.setVisibility(View.INVISIBLE);
                if (bitmap != null)
                imageView.setImageBitmap(bitmap);
            }
        }.execute(parameters.get(0));

        butSetWallPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    loadContent.pasteImage(bitmapDesktop);
                } catch (Exception ioe) {
                    Log.e(TAG, "Error: ", ioe);
                }
            }
        });
<<<<<<< HEAD

=======
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
>>>>>>> d92dc7c10095abedadd4d45aa6cae43bbfa8b0d1
    }
}
