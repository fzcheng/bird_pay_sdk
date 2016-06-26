package com.legame.paysdk.network.engine.cachestrategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.legame.paysdk.utils.Config;
import com.legame.paysdk.utils.IOTools;

/** 
 * 类说明：   
 * @author  Toby.chen
 * @date    2013-4-10
 * @version 1.0
 */

public class CacheStrategy {
	
	private boolean mCacheable = false;
	private long mCacheTime = 1 * 24 * 60 * 60 * 1000; // 默认缓存时间为1天
	private String mUrl = null;
	private String mCacheDir = Config.CACHE_DIR;
	
	public CacheStrategy(boolean cacheable){
		mCacheable = cacheable;
	}
	
	public CacheStrategy(boolean cacheable, String url){
		mUrl = url;
		mCacheable = cacheable;
	}
	
	public CacheStrategy(boolean cacheable, long cacheTime, String url){
		mUrl = url;
		mCacheable = cacheable;
		mCacheTime = cacheTime;	
	}
	
	public boolean isCacheable(){
		return mCacheable;
	}
	
	public long getCacheTime(){
		return mCacheTime;
	}
	
	public void setCacheTime(long time){
		mCacheTime = time;
	}
	
	public String getUrl(){
		return mUrl;
	}
	
	public void setUrl(String url){
		mUrl = url;
	}
	
	protected File getCacheFile(){
		if(mUrl == null){
			return null;
		}
		
		return new File(Config.CACHE_DIR + File.separator
				+ mUrl.hashCode());
	}
	
	public boolean isNeedToDoNetWork(){
		
		File cacheFile = getCacheFile();
		
		if(cacheFile == null){
			return true;
		}
		
		if (mCacheable && cacheFile.exists()) {
			long inteval = System.currentTimeMillis()
					- cacheFile.lastModified();
			if (Math.abs(inteval) > mCacheTime) { // 需要更新
				cacheFile.delete();
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	
	public void saveContentToCacheFile(InputStream is) throws IOException{
		File cacheFile = getCacheFile();
		
		if(is == null || cacheFile == null || !mCacheable){
			return;
		}
		
		if(cacheFile.exists()){
			cacheFile.delete();
		}
		
		OutputStream os = new FileOutputStream(cacheFile);
		IOTools.copyStream(is, os);
		os.close();
	}
	
	public InputStream getInputStreamFromCahceFile() throws FileNotFoundException{
		File cacheFile = getCacheFile();
		return new FileInputStream(cacheFile);
	}
	
	public void deleteCahceFile(){
		File cacheFile = getCacheFile();
		if(cacheFile != null && cacheFile.exists()){
			cacheFile.delete();
		}
	}
	
	public void setCacheDir(String dir) {
		mCacheDir = dir;
	}
	
	public String getCacheDir() {
		return mCacheDir;
	}

}
