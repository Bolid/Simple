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
import java.io.*;
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
    String urlSmall = null;
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
            urlSmall = mypar.urlSmall;
            Log.v(TAG, "URL фотки: "+url);
            bw.write("URL фотки: "+url);
            pasteImage(loadImage(url));
        } catch (Exception e){
            Log.e(TAG, "Ошибка инициализации парсера", e);
        }

    }

    public void load(){
        try {
            bw.write(String.valueOf(calendar.getTime()) + ": Дата: " + fDate + "\n");
            Log.v(TAG, "URL сервисного документа: " + url);
            bw.write(String.valueOf(calendar.getTime())+": URL сервисного документа: "+url+"\n");
        } catch (IOException ioe) {
            Log.e(TAG, "Ошибка: ", ioe);
        }
        InputSource inputSource = null;
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

    public BufferedInputStream loadImage(String url){
        BufferedInputStream Buf_srt = null;
        try {
            Log.v(TAG, "Получили URL: "+url);
            URL conn = new URL(url);
            Log.v(TAG, "Загрузка фото начата...");
            URLConnection URLcon = conn.openConnection();
            Buf_srt = new BufferedInputStream(URLcon.getInputStream(),8192);
            Log.v(TAG, "Загрузка фото завершена.");
            //Buf_srt.close();
            Log.v(TAG, "Отдаем BufferedInputStream.");
        }catch (MalformedURLException mue) {
            Log.e(TAG, "Ошибка преобразования адреса: ", mue);
        }catch (IOException ioe) {
            Log.e(TAG, "Ошибка загрузки фото по url: " + url, ioe);
        }catch (NetworkOnMainThreadException nomte){
            Log.e(TAG, "Ошибка загрузки фото: ", nomte);
        }
        return Buf_srt;
    }

    public void pasteImage(BufferedInputStream Buf_srt) throws IOException {
        Bitmap bitmap;
        Log.v(TAG, "Декодируем входящий поток в bitmap...");
        bitmap = BitmapFactory.decodeStream(Buf_srt);
        Log.v(TAG, "Декодирование завершено.");
        WallpaperManager wall = WallpaperManager.getInstance(context);
        if (servWork) {
            wall.setBitmap(bitmap);
            Log.v(TAG, "\nОбой установлены.");
            bw.write(String.valueOf(calendar.getTime()) + ": Обой установлены.\n");

            if (wallHistory != null){
                wallHistory.setUrl(url, savePhotoSmall(urlSmall), dateLoad);
                Log.v(TAG, "Время загрузки: " + dateLoad);
            }

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

    public String savePhotoSmall(String urlSmall){
        FileOutputStream fileOutputStream = null;
        Random random = new Random();

        String fileName = "";
        for (int i = 0; i < 16; i++)
            fileName = fileName + String.valueOf(random.nextInt(10));
        fileName = fileName + ".jpg";
        Log.v(TAG, "Имя фото: " + fileName);
        try
        {
            File file = new File(context.getExternalFilesDir(null).getPath());
            Log.v(TAG, "Путь1: "+file.getPath());
            //Environment.getExternalStorageDirectory().getAbsolutePath()+"/photos/");
            file.mkdir();
            fileOutputStream = new FileOutputStream(context.getExternalFilesDir(null).getAbsolutePath()+"/photos/"+fileName);

            //Environment.getExternalStorageDirectory()+"/photos/"+fileName);
            byte[] byteCont = new byte[2048];
            int length;
            BufferedInputStream bufferedInputStream = loadImage(urlSmall);
            while ((length = bufferedInputStream.read(byteCont)) != -1)
            {
               // fileOutputStream.write(byteCont, 0, length);
            }
            fileOutputStream.close();
        } catch (FileNotFoundException fnfe) {
            Log.e(TAG, "Ошибка открытия файла минифото. ", fnfe);
        } catch (IOException ioe) {
            Log.e(TAG, "Ошибка закрытия файла минифото. ", ioe);
        }
        return fileName;
    }

}
