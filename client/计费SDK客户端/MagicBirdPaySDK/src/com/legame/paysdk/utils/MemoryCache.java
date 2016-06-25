package com.legame.paysdk.utils;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;

public class MemoryCache {
	private Map<Integer, SoftReference<Bitmap>> cache = Collections.synchronizedMap(new HashMap<Integer, SoftReference<Bitmap>>());

	public Bitmap get(Integer id){
		if(!cache.containsKey(id))
			return null;
		SoftReference<Bitmap> ref = cache.get(id);
		return ref.get();
	}

	public void put(Integer id, Bitmap bitmap){
		cache.put(id, new SoftReference<Bitmap>(bitmap));
	}

	public void clear() {
		cache.clear();
	}
}