package com.example.WallPapperPhotoDay;

import android.app.WallpaperManager;
import android.content.Context;
import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public class LoadContent extends AsyncTask<Void, Integer, Bitmap>{
	String url;
	String userName;
	String title;
	Bitmap bitmap = null;
	Context a;

    LoadContent(String par1, Context par2){
		this.url = par1;
		this.a = par2;
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
