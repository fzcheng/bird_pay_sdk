package com.cheyooh.service.framework.utils.binding;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.framework.utils.annotation.Mapping;
import com.cheyooh.service.framework.utils.annotation.NullNone;
import com.cheyooh.service.framework.utils.annotation.Transient;
import com.cheyooh.tools.log.Logger;

public class ClassHelper {
	static Logger logger=new Logger(ClassHelper.class);
	
	private final static DateConverter dateConverter=new DateConverter();	
	
	private static ConcurrentHashMap<String, MetaClass> hBeanClasses = new ConcurrentHashMap<String, MetaClass>();
	public static MetaClass getMetaClass(Object bean) {
		String name = bean.getClass().getName();
		MetaClass mc = hBeanClasses.get(name);
		if (mc == null) {
			mc = loadMetaClass(bean.getClass());
		}
		return mc;
	}

	public static void setAttribute(Object bean, FGS fgs, String value) {
		try {			 
			Object v = null;
			Class<?> type=fgs.getField().getType();
			if(Date.class.isAssignableFrom(type)){
				v=dateConverter.convert(type, value);
			}else{
				v=ConvertUtils.convert(value, type);
			}
			Method set = fgs.getSetMethod();
			set.invoke(bean, v);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	public static void setObject(Object bean, FGS fgs, Object obj) {
		try {			 			 
			Method set = fgs.getSetMethod();
			set.invoke(bean, obj);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	public static void setAttribute(Object bean, FGS fgs, File value) {
		try {			 
			Method set = fgs.getSetMethod();
			set.invoke(bean, value);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	public static String getAttribute(Object bean, FGS fgs) {
		try {			 
			Method get = fgs.getGetMethod();
			Object r=get.invoke(bean);
			if(r instanceof Date){
				String format="yyyy-MM-dd HH:mm:ss";
				SimpleDateFormat sdf=new SimpleDateFormat(format);
				return sdf.format((Date)r);
			}else{
				return r==null?null:r.toString();
			}
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public static Object getObject(Object bean, FGS fgs) {
		try {			 
			Method get = fgs.getGetMethod();
			Object r=get.invoke(bean);
			return r;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}	 
	
	private synchronized static MetaClass loadMetaClass(Class<?> clazz) {
		String name = clazz.getName();
		if (hBeanClasses.containsKey(name)) {
			return hBeanClasses.get(name);
		} else {
			MetaClass mc = new MetaClass(clazz);
			hBeanClasses.put(name, mc);
			return mc;
		}
	}

	public static class MetaClass {
		private Class<?> clazz;
		private Map<String, FGS> hMappingFields = new LinkedHashMap<String, FGS>();
		private List<String> fieldNames=new ArrayList<String>();
		
		public MetaClass(Class<?> clazz) {
			this.clazz = clazz;
			loadClassInfo();
		}

		private void loadClassInfo() {
			List<Field> fields=new ArrayList<Field>();
			fetchFields(fields,clazz);						
			for (Field f : fields) {
				int modifiers = f.getModifiers();
				Transient ignore = f.getAnnotation(Transient.class);
				if (ignore == null && !Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers)) {
					FGS fgs = createFGS(f);					
					String[] names = getParameterNames(f);
					for(String name:names){
						String lower_name=name.toLowerCase();
						if (hMappingFields.containsKey(lower_name)) {
							logger.error("Duplicate name: " + name);
						} else {
							hMappingFields.put(lower_name, fgs);													
						}
					}
					fieldNames.add(fgs.getFieldName());
				} else {
					logger.debug("Ignore field: " + clazz.getName() + "." + f.getName());
				}
			}
		}
		
		private void fetchFields(List<Field> fields,Class<?> clazz){			 
			Field[] fs=clazz.getDeclaredFields();
			for(Field f:fs){
				fields.add(f);
			}
			
			Class<?> su=clazz.getSuperclass();
			if(su!=null){
				fetchFields(fields,su);
			}						
		}
		

		private FGS createFGS(Field f) {
			FGS fgs = new FGS();
			fgs.setField(f);

			String fn = f.getName();
			String m = fn.substring(0, 1).toUpperCase();
			if (fn.length() > 1) {
				m += fn.substring(1);
			}

			String get = "get" + m;
			String set = "set" + m;
			if (f.getType() == Boolean.class || f.getType() == boolean.class) {
				get = "is" + m;
			}

			fgs.setGetMethod(getMethod(get));
			fgs.setSetMethod(getMethod(set, f.getType()));

			NullNone nn = f.getAnnotation(NullNone.class);
			if (nn != null) {
				fgs.setNullNone(true);
			}
			return fgs;
		}

		private Method getMethod(String name, Class<?>... parameterTypes) {
			try {
				return clazz.getMethod(name, parameterTypes);
			} catch (NoSuchMethodException e) {
				logger.warn("Method not found: " + clazz.getName() + "." + name);

				return null;
			}
		}

		public FGS getMapppingField(String name) {
			return hMappingFields.get(name.toLowerCase());
		}

		public Collection<String> getNames(){			
			return fieldNames;
		}
		
		public Collection<FGS> getMappingFields(){
			return hMappingFields.values();
		}
		
		private String[] getParameterNames(Field f) {
			String pn = f.getName();

			Mapping pm = f.getAnnotation(Mapping.class);
			if (pm != null) {
				if(StringUtils.isEmpty( pm.alias()) ){
					return new String[]{pm.name()};
				}else{
					return new String[]{pm.name(),pm.alias()};
				}				
			}else{
				return new String[]{pn};			
			}
		}
	}

	private static class DateConverter implements Converter {
		@SuppressWarnings("rawtypes")
		public Object convert(Class type, Object value) {
			if (value == null) {
				return null;
			}
			if (value instanceof Date) {
				return value;
			}

			if (value instanceof Long) {
				Long longValue = (Long) value;
				return new Date(longValue.longValue());
			}

			if (value instanceof String) {
				String d=(String)value;
				String format="yyyy-MM-dd";
				if(d.indexOf(" ")>0){
					int px=d.indexOf(":");
					if(px>0){
						if(d.indexOf(":",px+1)>0){
							format="yyyy-MM-dd HH:mm:ss";
						}else{
							format="yyyy-MM-dd HH:mm";
						}
					}else{
						format="yyyy-MM-dd HH";
					}
				}
				if(!StringUtils.isEmpty(d)){
					try {					
						
						SimpleDateFormat sdf=new SimpleDateFormat(format);
						return sdf.parse(d);
					} catch (ParseException e) {
						logger.error("Invalid date: "+d+", format: "+format);
					}
				}
			}
			return null;
		}

	}
	
	public static class FGS {
		private Field field;
		private Method getMethod;
		private Method setMethod;
		
		private boolean nullNone=false;;
		
	 
		public Field getField() {
			return field;
		}

		public void setField(Field field) {
			this.field = field;
		}

		public Method getGetMethod() {
			return getMethod;
		}

		public void setGetMethod(Method getMethod) {
			this.getMethod = getMethod;
		}

		public Method getSetMethod() {
			return setMethod;
		}

		public void setSetMethod(Method setMethod) {
			this.setMethod = setMethod;
		}
		
		public String getAttribute(Object bean){
			return ClassHelper.getAttribute(bean,this);
		}

		public boolean isNullNone() {
			return nullNone;
		}

		public void setNullNone(boolean nullNone) {
			this.nullNone = nullNone;
		}

		public String getFieldName() {
			return field.getName();
		}
		 

	}
}
