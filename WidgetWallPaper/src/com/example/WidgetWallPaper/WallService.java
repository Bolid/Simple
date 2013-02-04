package com.example.WidgetWallPaper;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;


public class WallService extends IntentService{
    final String TAG = "Serv";
    Boolean servWork = true;
    public WallService(){
        super("WallService");
    }
    public void onCreate(){
        super.onCreate();
    }
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        /*Random random = new Random();
        Bitmap bitmap = null;
        WallpaperManager wall = WallpaperManager.getInstance(getApplicationContext());
        String url = "";
        String fDate = "";
        int month = 0;
        int day = 0;
        int year = 0;
        while (servWork){
            while (month==0)
                month = random.nextInt(13);
            if (month == 2)
                day = random.nextInt(28);
            else day = random.nextInt(30);
            year = random.nextInt(5)+2008;
            fDate = day+"-"+month+"-"+year;
            url = "http://api-fotki.yandex.ru/api/podhistory/poddate;"+fDate+"/?limit=1";
            try{
                URL conn = new URL(url);
                InputSource inputSource = new InputSource(conn.openStream());
                inputSource.setEncoding("UTF-8");
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();
                XMLReader xmlReader = saxParser.getXMLReader();
                myParser mypar = new myParser();
                xmlReader.setContentHandler(mypar);
                xmlReader.parse(inputSource);
                url = mypar.url;
                conn = new URL(url);
                URLConnection URLcon = conn.openConnection();
                BufferedInputStream Buf_srt = new BufferedInputStream(URLcon.getInputStream(),8192);
                bitmap = BitmapFactory.decodeStream(Buf_srt);
                Buf_srt.close();
                wall.setBitmap(bitmap);
            }
            catch (Exception e){
                Log.e(TAG, "Ошибка: ", e);
            }
            //LoadContent loadcontent = new LoadContent(url, getApplicationContext());
            //loadcontent.execute();
            SystemClock.sleep(3000);
        }
        url = "";      */
        try {
            fShow();
        }catch (Exception e){
            Log.e(TAG, "Ошибка формы", e);
        }


    }
    public void onDestroy()
    {
        servWork = false;
        super.onDestroy();
    }
    public void fShow(){
        try {
            Intent show = new Intent(getBaseContext(), ButActiv.class);
            show.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(show);
        }catch (Exception e)
        {
            Log.e(TAG, "Ошибка загрузки формы: ", e);
        }

    }
}
