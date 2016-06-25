package com.legame.paysdk.utils;

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.drawable.Drawable;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013年11月6日
 * @version 1.0
 */
public class ResourceUtil {
	private ResourceUtil() {}
	
	public static int getAttr(Context context, String resName) {
		return getResourceId(context, "attr", resName);
	}
	
	public static int getColor(Context context, String resName) {
		return getResourceId(context, "color", resName);
	}
	
	public static int getColorRGB(Context context, String resName) {
		int resid = getResourceId(context, "color", resName);
		int rgb = context.getResources().getColor(resid);
		return rgb;
	}
	
	public static int getDimen(Context context, String resName) {
		return getResourceId(context, "dimen", resName);
	}
	
	public static int getDrawable(Context context, String resName) {
		return getResourceId(context, "drawable", resName);
	}
	
	public static Drawable getDrawableExt(Context context, String name) {
		int resid = getResourceId(context, "drawable", name);
		Drawable dr = context.getResources().getDrawable(resid);
		return dr;
	}
	
	public static int getId(Context context, String resName) {
		return getResourceId(context, "id", resName);
	}
	
	
	public static int getLayout(Context context, String resName) {
		return getResourceId(context, "layout", resName);
	}

	public static int getString(Context context, String resName) {
		return getResourceId(context, "string", resName);
	}
	
	public static String getStringExt(Context context, String resName) {
		return context.getString(getResourceId(context, "string", resName));
	}
	
	public static int getStyle(Context context, String resName) {
		return getResourceId(context, "style", resName);
	}
	
	public static int getAnim(Context context, String resName)
	{
		return getResourceId(context, "anim", resName);
	}
	
	public static int getResourceId(Context context, String clsName, String resName) {
		int id = -1;
		
		try {
			String pkgName = context.getPackageName();
//			String pkgName = "com.legame.paysdk";
			Class<?> clsResource = Class.forName(pkgName + ".R$" + clsName);
			Field field = clsResource.getField(resName);
			id = Integer.valueOf(field.get(null).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}
}
