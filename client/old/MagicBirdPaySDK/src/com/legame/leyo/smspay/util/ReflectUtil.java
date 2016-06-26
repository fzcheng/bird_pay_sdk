package com.legame.leyo.smspay.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 类说明：处理反射类相关,这个是用来判断FastPaymentResaultData类有没有增加新属性
 * @author Kaiguang
 * @date 2015/4/15
 *
 */
public class ReflectUtil {
	
	private Class clzzObj;
	
	public ReflectUtil(String className){
		try {
//			clzzObj = ClassLoader.getSystemClassLoader().loadClass(className);
			clzzObj = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			clzzObj = null;
		}
	}
	
	public Class getClassObj(){
		return clzzObj;
	}
	
	public int getFieldCount(){
		if(clzzObj == null){
			return -1;
		}
		return clzzObj.getDeclaredFields().length;
	}
	
	public boolean hasField(String fieldName){
		for(Field field:clzzObj.getDeclaredFields()){
			if(fieldName.equals(field.getName())){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasMethod(String methodName){
		
		if(clzzObj == null){
			return false;
		}
		
		Method[] methods = clzzObj.getMethods(); 
		for(Method method:methods){
			if(methodName.equals(method.getName()))
				return true;
		}
		
		return false;
	}
}
