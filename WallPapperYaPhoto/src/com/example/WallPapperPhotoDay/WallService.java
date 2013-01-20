package com.example.WallPapperPhotoDay;

import android.app.IntentService;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.app.IntentService;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.ImageView;
import android.widget.TextView;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;


public class WallService extends IntentService{
    Boolean servWork = true;
    public WallService() {
        super("Name");
    }

    public  void onCreate(){
        super.onCreate();
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Random random = new Random();
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
                e.printStackTrace();
            }
            //LoadContent loadcontent = new LoadContent(url, getApplicationContext());
            //loadcontent.execute();
            SystemClock.sleep(3000);
        }
        url = "";
    }
    public void onDestroy(){
        servWork = false;
        super.onDestroy();

    }

   /* public void onStart(Intent intent, int id){
            method();
    }
    void method(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                String url = "";
                String fDate = "";
                int month = 0;
                int day = 0;
                int year = 0;
                Boolean servWork = true;
                while (servWork){
                    while (month==0)
                        month = random.nextInt(13);
                    if (month == 2)
                        day = random.nextInt(28);
                    else day = random.nextInt(30);
                    year = random.nextInt(5)+2008;
                    fDate = day+"-"+month+"-"+year;
                    url = "http://api-fotki.yandex.ru/api/podhistory/poddate;"+fDate+"/?limit=1";
                    /*LoadContent loadcontent = new LoadContent(url, getApplicationContext());
                    loadcontent.execute();
                    SystemClock.sleep(1000);
                }
            }
        });
    }*/
}
