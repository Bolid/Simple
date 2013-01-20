package com.example.Yandex_photo;

import java.io.BufferedInputStream;

import android.app.Activity;
import android.app.Application;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.pm.ActivityInfo;

import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ImageView;
import android.widget.TextView;

class ACDemo extends AsyncTask<Void, Integer, Bitmap>{
	String url;
	String userName;
	String title;
	Bitmap bitmap = null;
	Context a;
	ImageView IM;
	TextView TX;
	
	ACDemo(String par1, TextView par2, ImageView par3, Context par4){
		this.url = par1;
		this.TX = par2;
		this.IM = par3;
		this.a=par4;
	}
	@Override
	protected Bitmap doInBackground(Void... params) {
		WallpaperManager wall = WallpaperManager.getInstance(a);
		
		// TODO Auto-generated method stub
		try {
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
			userName = mypar.userName;
			title = mypar.title;
			conn = new URL(url);
			URLConnection URLcon = conn.openConnection();
			BufferedInputStream Buf_srt = new BufferedInputStream(URLcon.getInputStream(),8192);
			bitmap = BitmapFactory.decodeStream(Buf_srt);
			Buf_srt.close();
			//SystemClock.sleep(3000);
			wall.setBitmap(bitmap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	} 
	protected void onPostExecute(Bitmap res){
	}
}
