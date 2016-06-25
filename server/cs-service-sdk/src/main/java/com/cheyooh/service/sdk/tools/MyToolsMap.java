package com.cheyooh.service.sdk.tools;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class MyToolsMap implements Serializable {
	
	public static void main(String[] args) {
		Map<String, String> testMap = new HashMap<String, String>();
		testMap.put("b", "ccccc");
		testMap.put("d", "aaaaa");
		testMap.put("c", "abbbb");
		testMap.put("ad", "");
		testMap.put("a", "adddd");
//        Map<String,String> resultMap=testMap;
        //使用 Map按key进行升序排序
//        Map<String,String> resultMap=ascendingSortMapByKey(testMap);
        //使用 Map按key进行升序排序2
//        Map<String,String> resultMap=ascendingSortMapByKey2(testMap);
        //使用 Map按key进行降序排序
//        Map<String,String> resultMap=descendingSortMapByKey(testMap);
		//使用 Map按Value进行升序排序
//		Map<String,String> resultMap=ascendingSortMapByValue(testMap);
		//使用 Map按Value进行降序排序
		Map<String,String> resultMap=descendingSortMapByValue(testMap);
        for (Map.Entry<String, String> me : resultMap.entrySet()) {
			System.out.println(MessageFormat.format("{0}={1}", me.getKey(), me.getValue()));
		}
        System.out.println(resultMap.containsKey("aa"));
	}
	
	/**
	 * 使用 Map按key进行升序排序
	 * @param map
	 * @return
	 */
	public static Map<String,String> ascendingSortMapByKey(Map<String,String> map){
		if (map == null || map.isEmpty()) {  
	        return null;  
	    }  
		SortedMap<String, String> resultMap = new TreeMap<String, String>();
		resultMap.putAll(map);
		return resultMap;
	}
	
	/**
     * 使用 Map按key进行降序排序
     * @param map
     * @return
     */
    public static Map<String, String> descendingSortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
 
        Map<String, String> resultMap = new TreeMap<String, String>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        // 降序排序
                        return obj2.compareTo(obj1);
                    }
                });
        resultMap.putAll(map);
        return resultMap;
    }
	
    /**
     * 使用 Map按key进行升序排序2
     * @param map
     * @return
     */
    public static Map<String, String> ascendingSortMapByKey2(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
 
        Map<String, String> resultMap = new TreeMap<String, String>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        //升序排序
                        return obj1.compareTo(obj2);
                    }
                });
        resultMap.putAll(map);
        return resultMap;
    }
    
    /**
     * 使用 Map按Value进行升序排序
     * @param map
     * @return
     */
	public static Map<String,String> ascendingSortMapByValue(Map<String,String> map){
		if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> resultMap = new LinkedHashMap<String, String>();
        List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(
        		map.entrySet());
        Collections.sort(entryList,new Comparator<Map.Entry<String,String>>() {
            //升序排序
            public int compare(Entry<String, String> o1,
                    Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
            
        });
 
        Iterator<Map.Entry<String, String>> iter = entryList.iterator();
        Map.Entry<String, String> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            resultMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return resultMap;
    }
	
	/**
     * 使用 Map按Value进行降序排序
     * @param map
     * @return
     */
	public static Map<String,String> descendingSortMapByValue(Map<String,String> map){
		if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> resultMap = new LinkedHashMap<String, String>();
        List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(
        		map.entrySet());
        Collections.sort(entryList,new Comparator<Map.Entry<String,String>>() {
            //降序排序
            public int compare(Entry<String, String> o1,
                    Entry<String, String> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
            
        });
 
        Iterator<Map.Entry<String, String>> iter = entryList.iterator();
        Map.Entry<String, String> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            resultMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return resultMap;
    }
}
