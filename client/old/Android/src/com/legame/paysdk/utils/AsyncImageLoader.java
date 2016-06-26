package com.legame.paysdk.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.legame.paysdk.network.utils.NetTools;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.webkit.URLUtil;
import android.widget.ImageView;


public class AsyncImageLoader {

	private static final String TAG = "AsyncImageLoader";

	private MemoryCache mMemoryCache;
	private FileCache mFileCache;
	private Map<ImageView, String> imageViews = 
			Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
	private ThreadPoolExecutor executorService;

	private static AsyncImageLoader mInstance = null;
	public static AsyncImageLoader getInstance(Context c){
		if(mInstance == null){
			mInstance = new AsyncImageLoader(c);
		}
		return mInstance;
	}

	private AsyncImageLoader(Context context){
		mMemoryCache = new MemoryCache();
		mFileCache = new FileCache(context);
		executorService = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);
	}

	/**
	 * 从本地缓存，或者缓存文件里面读取图片，如果没有直接返回null
	 * @param url
	 * @return
	 */
	public Bitmap getLocalImage(String url)
	{
		if(TextUtils.isEmpty(url)){
			return null;
		}
		Bitmap bitmap = mMemoryCache.get(url.hashCode());
		
		if(bitmap != null){
			return bitmap;
		}
		
		if(URLUtil.isHttpUrl(url)){ //from web
			File file = mFileCache.getFile(url);
			Bitmap b = decodeFile(file, true);
			if(b != null){
				return b; 
			}
		}else {
			File f = new File(url);
			return decodeFile(f, true);
		}
		return null;
	}
	
	public void displayImage(String url, ImageView imageView, int defaultId,
			boolean compress, boolean rotate){
		if(TextUtils.isEmpty(url)){
			imageView.setImageResource(defaultId);
			return;
		}

		imageViews.put(imageView, url);
		Bitmap bitmap = mMemoryCache.get(url.hashCode());
		
		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
//			LogUtil.d(TAG, "set bitmap");
		} else {
			imageView.setImageResource(defaultId);
			queuePhoto(null, url, imageView, defaultId, false, compress, rotate);
//			LogUtil.d(TAG, "defalut id...");
		}
	}
	
	public void displayImage(String url, ImageView imageView, int defaultId, boolean compress){
		displayImage(url, imageView, defaultId, compress, false);
	}
	
	public void displayImage(String url, ImageView imageView, int defaultId){
		displayImage(url, imageView, defaultId, false, false);
	}
	
	private Bitmap getRotateBitmap(Bitmap bitmap) {
		Matrix matrix = new Matrix();
		matrix.setRotate(90);
		Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		
		if (rotatedBitmap != bitmap) {
			bitmap.recycle();
			bitmap = null;
		}
		
		return rotatedBitmap;
	}
	
	public void displayBackground(Resources res, String url, ImageView imageView, int defaultId){
		if(TextUtils.isEmpty(url)){
			imageView.setBackgroundResource(defaultId);
			return;
		}

		imageViews.put(imageView, url);
		Bitmap bitmap = mMemoryCache.get(url.hashCode());
		if(bitmap != null){
			Drawable d = new BitmapDrawable(res, bitmap);
			imageView.setBackgroundDrawable(d);
//			LogUtil.d(TAG, "set drawable");
		}else{
			imageView.setBackgroundResource(defaultId);
			queuePhoto(res, url, imageView, defaultId, true);
//			LogUtil.d(TAG, "defalut id...");
		}
	}
	
	private void queuePhoto(Resources res, String url, ImageView imageView, int defaultId, boolean src){
		PhotoToLoad p = new PhotoToLoad(res, url, imageView, defaultId, src);
		executorService.submit(new ImagesLoader(p));
	}

	private void queuePhoto(Resources res, String url, ImageView imageView, int defaultId, boolean src,
			boolean compress, boolean isRotate){
		PhotoToLoad p = new PhotoToLoad(res, url, imageView, defaultId, src, compress, isRotate);
		executorService.submit(new ImagesLoader(p));
	}
	
	private Bitmap getBitmap(String url, Context c, boolean compress) {

		if(URLUtil.isHttpUrl(url)){ //from web
			File file = mFileCache.getFile(url);
			Bitmap b = decodeFile(file, compress);
			if(b != null){
				return b; 
			}   
			try {
				Bitmap bitmap = null;
				URL imageURL = new URL(url);
				HttpURLConnection conn = null;
				if(NetTools.isCmwap(c)){
					conn = NetTools.getCmwapConnect(url);
				}else{
					conn = (HttpURLConnection) imageURL.openConnection();
				}
				conn.setConnectTimeout(30000);
				conn.setReadTimeout(30000);
				conn.setInstanceFollowRedirects(true);
				NetTools.setCommonHttpHeader(conn);

				int statusCode = conn.getResponseCode();
				if(statusCode != 200){
					LogUtil.w(TAG, "http code error:" + statusCode);
					return null;
				}

				InputStream is = conn.getInputStream();
				OutputStream os = new FileOutputStream(file);
				IOTools.copyStream(is, os);
				os.close();
				bitmap = decodeFile(file, compress);
				return bitmap;
			} catch (Exception ex){
				ex.printStackTrace();
				LogUtil.w(TAG, "url:" + url);
				return null;
			}
		}else{ //local file
			File f = new File(url);
			return decodeFile(f, compress);
		}
	}

	private Bitmap decodeFile(File file, boolean compress) {
		if (!file.exists()) {
			return null;
		}
		
		Bitmap bitmap = null;
		
		try {
			if (compress) {

				BitmapFactory.Options op = new BitmapFactory.Options();
				op.inJustDecodeBounds = true;
				BitmapFactory.decodeStream(new FileInputStream(file), null, op);

				// Find the correct scale value. It should be the power of 2.
				final int REQUIRED_SIZE = 70;
				int width_tmp = op.outWidth;
				int height_tmp = op.outHeight;
				int scale = 1;
				while (true) {
					if (width_tmp / 2 < REQUIRED_SIZE
							|| height_tmp / 2 < REQUIRED_SIZE)
						break;
					width_tmp /= 2;
					height_tmp /= 2;
					scale *= 2;
				}

				BitmapFactory.Options op2 = new BitmapFactory.Options();
				op2.inSampleSize = scale;
				bitmap =  BitmapFactory.decodeStream(new FileInputStream(file),
						null, op2);

			} else {
				bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.w(TAG, "decodeFile erro.." + e.toString());
		} catch (OutOfMemoryError oom) {
			oom.printStackTrace();
			LogUtil.w(TAG, "decodeFile OOM Happend.." + oom.toString());
		}

		return bitmap;
	}

	//Task for the queue
	private class PhotoToLoad{
		public String url;
		public ImageView imageView;
		public int defaultId;
		private boolean src;
		public Resources res;
		private boolean isCompress;
		private boolean isRotate;
		
		public PhotoToLoad(Resources r, String u, ImageView i, int id, boolean s){
			res = r;
			url = u;
			imageView = i;
			defaultId = id;
			src = s;
		}
		
		public PhotoToLoad(Resources r, String u, ImageView i, int id, boolean s,
				boolean compress, boolean isRotate) {
			this(r, u, i, id, s);
			this.isCompress = compress;
			this.isRotate = isRotate;
		}
	}

	class ImagesLoader implements Runnable {
		PhotoToLoad photoToLoad;
		ImagesLoader(PhotoToLoad photoToLoad){
			this.photoToLoad = photoToLoad;
		}

		@Override
		public void run() {
			if(imageViewReused(photoToLoad))
				return;
			Bitmap bmp = getBitmap(photoToLoad.url,
					photoToLoad.imageView.getContext(),
					photoToLoad.isCompress);
			
			if (photoToLoad.isRotate) {
				bmp = getRotateBitmap(bmp);
			}
			
			mMemoryCache.put(photoToLoad.url.hashCode(), bmp);
			if(imageViewReused(photoToLoad))
				return;
			BitmapDisplayer bd = new BitmapDisplayer(photoToLoad.res, bmp, photoToLoad);
			Activity a = (Activity)photoToLoad.imageView.getContext();
			a.runOnUiThread(bd);
		}
	}

	boolean imageViewReused(PhotoToLoad photoToLoad){
		String tag = imageViews.get(photoToLoad.imageView);
		if(tag == null || !tag.equals(photoToLoad.url))
			return true;
		return false;
	}

	//Used to display bitmap in the UI thread
	class BitmapDisplayer implements Runnable{
		Bitmap bitmap;
		PhotoToLoad photoToLoad;
		Resources res;
		public BitmapDisplayer(Resources res, Bitmap b, PhotoToLoad p){
			bitmap = b;
			photoToLoad = p;
		}
		@SuppressWarnings("deprecation")
		public void run(){
			if(imageViewReused(photoToLoad))
				return;

			if(photoToLoad.src){
				if(bitmap != null){
					Drawable d = new BitmapDrawable(res, bitmap);
					photoToLoad.imageView.setBackgroundDrawable(d);
				}else{
					photoToLoad.imageView.setBackgroundResource(photoToLoad.defaultId);
				}
			}else{
				if(bitmap != null){
					photoToLoad.imageView.setImageBitmap(bitmap);
					//                photoToLoad.imageView.postInvalidate();
					//            	photoToLoad.imageView.startAnimation(
					//            			AnimationUtils.loadAnimation(photoToLoad.imageView.getContext(),
					//            					android.R.anim.fade_in));
				}else
					photoToLoad.imageView.setImageResource(photoToLoad.defaultId);
			}
		}
	}

	public void clearWaitQuque(){
		while(executorService.getQueue().size() > 0){
			Runnable r = executorService.getQueue().peek();
			executorService.remove(r);
		}
		executorService.purge();
	}

	public void clearCache() {
		mMemoryCache.clear();
		//mFileCache.clear();
	}
}
