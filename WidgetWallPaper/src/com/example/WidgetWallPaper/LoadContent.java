package com.example.WidgetWallPaper;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.NetworkOnMainThreadException;
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
import java.util.Date;
import java.util.Random;


public class LoadContent {
    final String TAG = "SERV";
    String stopServ = null;
    Calendar calendar = Calendar.getInstance();

    Context context;
    String dateLoad = null;
    WallHistory wallHistory;
    BufferedWriter bw = null;
    XMLReader xmlReader = null;
    String fDate = createDate();
    String url = "http://api-fotki.yandex.ru/api/podhistory/poddate;"+fDate+"/?limit=1";
    Boolean servWork = true;

    public LoadContent(Context context, BufferedWriter bw, WallHistory wallHistory, Boolean servWork) {
        this.context = context;
        this.bw = bw;
        this.wallHistory = wallHistory;
        this.servWork = servWork;
    }

    public void parse(InputSource inputSource){
        try {
            MyParser mypar = new MyParser();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(mypar);
            xmlReader.parse(inputSource);
            url = mypar.url;
            if (wallHistory != null)
                wallHistory.setUrl(url, dateLoad);
            Log.v(TAG, "URL фотки: "+url);
            bw.write("URL фотки: "+url);
            pasteImage(loadImage(url));
            return;
        } catch (Exception e){
            Log.e(TAG, "Ошибка инициализации парсера", e);
        }

    }

    public void load(){
        try {
            bw.write(String.valueOf(calendar.getTime())+": Дата: "+fDate+"\n");
            Log.v(TAG, "URL сервисного документа: " + url);
            if (wallHistory != null){
                //wallHistory.setUrl(url, dateLoad);
                Log.v(TAG, "Время загрузки: " + dateLoad);
            }
            bw.write(String.valueOf(calendar.getTime())+": URL сервисного документа: "+url+"\n");
        } catch (IOException ioe) {    }
        InputSource inputSource = null;
        //LOG
        try {
            URL conn = new URL(url);
            Log.v(TAG, "Работа службы: " + servWork);
            inputSource = new InputSource(conn.openStream());
            inputSource.setEncoding("UTF-8");
            parse(inputSource);
        }
        catch (MalformedURLException e){
            Log.e(TAG, "Ошибка URL: ", e);

        }
        catch (IOException e){
            Log.e(TAG, "Ошибка подключения: ", e);
            try{
                bw.write(String.valueOf(calendar.getTime())+": Ошибка подключения: "+String.valueOf(e)+"\n");
            }catch (IOException e1){
                Log.e(TAG, "Ошибка: ", e1);
            }
        }



    }

    public Bitmap loadImage(String url){
        Bitmap bitmap = null;
        try {
            //url = "http://img-fotki.yandex.ru/get/6600/81563225.3de/0_738ec_c77fefa5_XL";
            Log.v(TAG, "Получили URL из основного потока: "+url);
            URL conn = new URL(url);
            Log.v(TAG, "Загрузка фото начата...");
            URLConnection URLcon = conn.openConnection();
            BufferedInputStream Buf_srt = new BufferedInputStream(URLcon.getInputStream(),8192);
            Log.v(TAG, "Загрузка фото завершена.");
            Log.v(TAG, "Декодируем входящий поток в bitmap...");
            bitmap = BitmapFactory.decodeStream(Buf_srt);
            Log.v(TAG, "Декодирование завершено.");
            Buf_srt.close();
            Log.v(TAG, "Отдаем фото в формате bitmap.");
        }catch (MalformedURLException mue) {
            Log.e(TAG, "Ошибка преобразования адреса: ", mue);
        }catch (IOException ioe) {
            Log.e(TAG, "Ошибка загрузки фото по url: " + url, ioe);
        }catch (NetworkOnMainThreadException nomte){
            Log.e(TAG, "Ошибка загрузки фото: ", nomte);
        }
        return bitmap;
    }

    public void pasteImage(Bitmap bitmap) throws IOException {

        WallpaperManager wall = WallpaperManager.getInstance(context);
        if (servWork) {
            wall.setBitmap(bitmap);

            Log.v(TAG, "Обой установлены.");
            bw.write(String.valueOf(calendar.getTime()) + ": Обой установлены.\n");
        }

    }

    public String createDate(){
        int year;
        int month = 0;
        int day = 0;
        int tYear;
        int tDay;
        int tMonth;
        //String fDate = null;
        Random random = new Random();
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
        Log.v(TAG, "Дата: "+fDate);
        pattern.applyPattern("dd-MM-yyyy");
        dateLoad = String.valueOf(pattern.format(calendar.getTime()));
        Date date = new Date();
        //dateLoad = String.valueOf(date.getTime());
        Log.v(TAG,"Дата:  "+ date.getTime());
        return fDate;
    }

}
