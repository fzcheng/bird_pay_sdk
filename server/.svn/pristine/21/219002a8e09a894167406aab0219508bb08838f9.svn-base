package com.cheyooh.service.dal.util;

import java.util.ArrayList;
import java.util.List;

public class DALUtils {
	public static List<Object> array2List(Object[] args){
		if(args!=null){
			List<Object> list=new ArrayList<Object>();
			for(Object arg:args){
				list.add(arg);
			}
			return list;
		}else{
			return null;
		}
	}
	
	public static Object[] list2Array(List<Object> args){
		if(args!=null){
			Object[] array=new Object[args.size()];
			for(int i=0;i<args.size();i++){
				array[i]=args.get(i);
			}
			return array;
		}else{
			return null;
		}
	}
}
