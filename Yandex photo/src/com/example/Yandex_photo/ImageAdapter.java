package com.example.Yandex_photo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	private int mGallery;
	private Context mContext;
	//private Bitmap bitmap;
	private final int mImage[] = new int[4];
	public ImageAdapter(Context context){
		//this.bitmap = bitmap; 
		mContext = context;
		for (int i = 0; i < mImage.length; i++){ 
			switch (i){
			case 0:
				mImage[0] = R.drawable.img_1;
				break;
			case 1:
				mImage[1] = R.drawable.img_2;
				break;
			case 2:
				mImage[2] = R.drawable.img_3;
				break;
			case 3:
				mImage[3] = R.drawable.img_4;
				break;
			}
		}
	} 
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mImage.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mImage[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return mImage[position];
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView view = new ImageView(mContext);
		view.setImageResource(mImage[position]);
		//view.setImageBitmap(bitmap);
		view.setPadding(20, 0, 20, 0);
		//view.setLayoutParams(new Gallery.LayoutParams(250,250));
		//view.setScaleType(ImageView.ScaleType.MATRIX);
		view.setBackgroundResource(mGallery);
		return view;
	}
	
}