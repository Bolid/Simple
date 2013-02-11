package com.example.WidgetWallPaper;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


public class loadContent{
    final String TAG = "SERV";
    Context context;
    public loadContent(Context context) {
        this.context = context;
    }
    BufferedWriter bw = null;
    XMLReader xmlReader = null;
    String fDate = createDate();
    String url = "http://api-fotki.yandex.ru/api/podhistory/poddate;"+fDate+"/?limit=1";



    public void parse(InputSource inputSource){
        try {
            myParser mypar = new myParser();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(mypar);
            xmlReader.parse(inputSource);
            url = mypar.url;
            //LOG
            loadImage(url);
            return;
        } catch (Exception e){
            Log.e(TAG, "Ошибка инициализации парсера", e);
        }

    }
    public void load(){
        InputSource inputSource = null;
        //LOG
        try {
            URL conn = new URL(url);
            inputSource = new InputSource(conn.openStream());
            inputSource.setEncoding("UTF-8");
            parse(inputSource);
        }
        catch (Exception e){
            Log.e(TAG, "Ошибка подключения: ", e);
        }



    }
    public void loadImage(String url){
        try {
            URL conn = new URL(url);
            URLConnection URLcon = conn.openConnection();
            BufferedInputStream Buf_srt = new BufferedInputStream(URLcon.getInputStream(),8192);
            Bitmap bitmap = null;
            bitmap = BitmapFactory.decodeStream(Buf_srt);
            Buf_srt.close();
            WallpaperManager wall = WallpaperManager.getInstance(context);
            wall.setBitmap(bitmap);
        }catch (MalformedURLException e) {
            Log.e(TAG, "Ошибка преобразования адреса: ",e);
        }catch (IOException e) {
            Log.e(TAG, "Ошибка загрузки фото: ", e);
        }
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
