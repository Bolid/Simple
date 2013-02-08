package com.example.WidgetWallPaper;

import android.app.IntentService;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        Bitmap bitmap = null;
        myParser mypar = null;
        URL conn = null;
        InputSource inputSource = null;
        XMLReader xmlReader = null;
        BufferedInputStream Buf_srt = null;
        SharedPreferences mSetting = getSharedPreferences("AppSetting", Context.MODE_PRIVATE);
        WallpaperManager wall = WallpaperManager.getInstance(getApplicationContext());
        String url = "";
        String fDate = "";
        try {
            Buf_srt = new BufferedInputStream(null);
            inputSource = new InputSource();
            mypar = new myParser();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            xmlReader = saxParser.getXMLReader();
        } catch (Exception e){
            Log.e(TAG, "Ошибка инициализации парсера", e);
        }
        while (servWork){
            try {
                Log.v(TAG, "-------------------------");
                fDate = createDate();
                Log.i(TAG, "Дата: "+fDate);
            }
            catch (Exception e) {
                Log.e(TAG, "Ошибка создания даты: ", e);
            }

            url = "http://api-fotki.yandex.ru/api/podhistory/poddate;"+fDate+"/?limit=1";
            Log.v(TAG, "URL сервисного документа: "+url);
            try{
                conn = new URL(url);
                inputSource = new InputSource(conn.openStream());
                inputSource.setEncoding("UTF-8");
                xmlReader.setContentHandler(mypar);
                xmlReader.parse(inputSource);
                url = mypar.url;
                Log.v(TAG, "URL фотки: "+url);
                conn = new URL(url);
                URLConnection URLcon = conn.openConnection();
                Buf_srt = new BufferedInputStream(URLcon.getInputStream(),8192);
                bitmap = BitmapFactory.decodeStream(Buf_srt);
                Buf_srt.close();
                wall.setBitmap(bitmap);
            }
            catch (Exception e){
                Log.e(TAG, "Ошибка закачки: ", e);

            }
            //LoadContent loadcontent = new LoadContent(url, getApplicationContext());
            //loadcontent.execute();
            Log.v(TAG, "Период скачивания: "+Integer.toString(mSetting.getInt("periodLoad", 86400)*1000));
            bitmap = null;
            //saxParser = null;
            inputSource = null;
            url = null;
            conn = null;
            inputSource = null;
            Buf_srt = null;
            System.gc();
            SystemClock.sleep(mSetting.getInt("periodLoad", 86400)*1000);
        }
        url = "";


    }
    public void onDestroy()
    {
        servWork = false;
        super.onDestroy();
    }


    public String createDate(){
        int year;
        int month = 0;
        int day = 0;
        int tYear;
        int tDay;
        int tMonth;
        String fDate = null;
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat pattern = new SimpleDateFormat();
        pattern.applyPattern("yyyy");
        tYear = Integer.parseInt(pattern.format(calendar.getTime())); //текущий год
        year = random.nextInt(tYear - 2007);
        year = year + 2008; //готовый год
        pattern.applyPattern("MM");
        tMonth = Integer.parseInt(pattern.format(calendar.getTime())); //текущий месяц
        pattern.applyPattern("dd");
        tDay = Integer.parseInt(pattern.format(calendar.getTime())); //текущий день
        while (month == 0 || (month > tMonth && year == tYear))
            month = random.nextInt(13); //готовый месяц
        while (day == 0 || (day > tDay && month == tMonth && year == tYear))
            if (month == 4 || month == 6 || month == 9 || month == 11)
                day = random.nextInt(31);
            else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                day = random.nextInt(32);
            else if (month == 2)
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
                    day = random.nextInt(30);
                else day = random.nextInt(29);
        fDate = day+"-"+month+"-"+year;
        year = 0;
        month = 0;
        day = 0;
        tYear = 0;
        tDay = 0;
        tMonth = 0;
        random = null;
        calendar = null;
        pattern = null;
        System.gc();


        return fDate;
    }


}
