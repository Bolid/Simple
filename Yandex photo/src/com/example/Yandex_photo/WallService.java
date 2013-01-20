package com.example.Yandex_photo;

import android.app.WallpaperManager;
import android.content.Intent;
import android.app.Service;
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


public class WallService extends Service {
    /*public WallService(Context par1){
        this.a = par1;
    };       */
    TextView TX;
    ImageView IM;
    Random random = new Random();
    String url = "";
    String fDate = "";
    int month = 0;
    int day = 0;
    int year = 0;
    Boolean servWork = true;
    String userName;
    String title;
    Bitmap bitmap = null;
    public  void onCreate(){
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
    public void onStart(Intent intent, int id){
        WallpaperManager wall = WallpaperManager.getInstance(this);
        while (servWork){
            while (month==0)
                month = random.nextInt(13);
            if (month == 2)
                day = random.nextInt(28);
            else day = random.nextInt(30);
            year = random.nextInt(5)+2008;
            fDate = day+"-"+month+"-"+year;
            url = "http://api-fotki.yandex.ru/api/podhistory/poddate;"+fDate+"/?limit=1";
           ACDemo loadFile = new ACDemo(url, TX, IM, getApplicationContext());
           loadFile.execute();

           SystemClock.sleep(1000*180);
       }
    }
}
