package com.cheyooh.tools.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhouzg@cheyooh.com {MSN:zzgzhou@hotmail.com, QQ:11039850}
 * 
 */
public class MapList<K, V> extends HashMap<K, V> {
	private static final long serialVersionUID = 6672850078139210775L;

	private List<StoreValue> storeList = new ArrayList<StoreValue>();
	private Map<K, Integer> hPosition = new HashMap<K, Integer>();

	private int index;

	public synchronized V put(K key, V value) {
		V v = super.put(key, value);

		if (v == null) {
			hPosition.put(key, storeList.size());

			storeList.add(new StoreValue(value));
		}
		return v;

	}

	public synchronized V remove(Object key) {
		V v = super.remove(key);
		if (v != null) {
			int p = hPosition.remove(key);
			storeList.remove(p);
		}
		return v;
	}

	 
	public synchronized StoreValue next(){
		int count=size();
		 	
		StoreValue sv =NullStoreValue;		
		for(int i=0;i<count;i++){
			StoreValue tmp=fetchNext();			
			if(tmp!=NullStoreValue){
				V obj=tmp.getObj();
				
				if(obj instanceof StoreObjectQueryIndicator){
					StoreObjectQueryIndicator soqi=(StoreObjectQueryIndicator)obj;
					long delayto=soqi.getQueryDelayTo();
					if(delayto==0 || System.currentTimeMillis()>=delayto){
						sv=tmp;
						soqi.selected();
						break;
					}					  								
				}else{
					sv=tmp;
					break;
				}
			}else{
				break;
			}
		}
		
		if(sv!=NullStoreValue){
			sv.useCount++;
			sv.lastUsedTime = System.currentTimeMillis();
		}
 
		
		return sv;
	}



	private StoreValue fetchNext() {
		StoreValue v =get();
		
		index++;
		if (index >= size()) {
			index = 0;
		
			resetUseCount();
		} 
		return v;
	}
	
	private  StoreValue get() {
		if (index >= size()) {
			index = size() - 1;
		}
		return getStoreValue(index);
	}
	

	private StoreValue getStoreValue(int index) {
		if (index >= 0 && index < storeList.size()) {
			StoreValue sv = storeList.get(index);			

			return sv;
		} else {
			return NullStoreValue;
		}
	}


	private void resetUseCount() {
		for (StoreValue sv : storeList) {
			sv.setUseCount(0);
		}
	}
	
	private StoreValue NullStoreValue = new StoreValue(null);

	public static interface StoreObjectQueryIndicator{		 
		public long getQueryDelayTo();

		public void selected();		 
	}
	
	public class StoreValue {
		private V obj;
		private int useCount;
		private long lastUsedTime;
		
		private int  delay;
		
		public StoreValue(V obj) {
			this.obj = obj;
			useCount = 0;
			lastUsedTime = 0L;
		}

		public V getObj() {
			return obj;
		}

		public void setObj(V obj) {
			this.obj = obj;
		}

		public int getUseCount() {
			return useCount;
		}

		public void setUseCount(int useCount) {
			this.useCount = useCount;
		}

		public long getLastUsedTime() {
			return lastUsedTime;
		}

		public void setLastUsedTime(long lastUsedTime) {
			this.lastUsedTime = lastUsedTime;
		}

		public int getDelay() {
			return delay;
		}

		public void setDelay(int delay) {
			this.delay = delay;
		}
	}

}
