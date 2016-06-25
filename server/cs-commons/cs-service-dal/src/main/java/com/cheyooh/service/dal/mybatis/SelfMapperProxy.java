package com.cheyooh.service.dal.mybatis;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.session.SqlSession;

import com.cheyooh.service.dal.annotation.Flush;
import com.cheyooh.service.dal.util.DALUtils;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 * 
 */
public class SelfMapperProxy implements InvocationHandler, Serializable {

	private static final long serialVersionUID = -6424540398559729838L;

	private static final Set<String> OBJECT_METHODS = new HashSet<String>() {
		private static final long serialVersionUID = -1782950882770203582L;
		{
			add("toString");
			add("getClass");
			add("hashCode");
			add("equals");
			add("wait");
			add("notify");
			add("notifyAll");
		}
	};

	private SqlSession sqlSession;

	public <T> SelfMapperProxy(SqlSession sqlSession) {
		 this.sqlSession=sqlSession;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (!OBJECT_METHODS.contains(method.getName())) {
			Object result=null;
			final Class<?> declaringInterface = findDeclaringInterface(proxy, method);
			
			String statement=declaringInterface.getName()+"."+method.getName();
			
			MapperMethodInterceptor mmi=MMM.getInstance().getMapperMethodInterceptor(statement);
			if(mmi==null){
				Flush flush=method.getAnnotation(Flush.class);
				if(flush!=null){
					mmi=new FlushInterceptor();
				}
			}
			if(mmi!=null){
				List<Object> listArgs=DALUtils.array2List(args);
				result=mmi.beforeCall(statement,proxy, method, listArgs);				
				args=DALUtils.list2Array(listArgs);
			}
			
			if(result==null){
				final SelfMapperMethod mapperMethod = new SelfMapperMethod(declaringInterface, method, sqlSession);				
				result = mapperMethod.execute(args);
				
				if(mmi!=null){
					Object r=mmi.afterCall(statement,proxy, method, DALUtils.array2List(args), result);
					if(r!=null){
						result=r;
					}
				}								
			}
			
			if (result == null && method.getReturnType().isPrimitive()) {
				throw new BindingException("Mapper method '" + method.getName() + "' (" + method.getDeclaringClass()
						+ ") attempted to return null from a method with a primitive return type (" + method.getReturnType() + ").");
			}
			return result;
		}
		return null;
	}
	 
	private Class<?> findDeclaringInterface(Object proxy, Method method) {
		Class<?> declaringInterface = null;
		for (Class<?> iface : proxy.getClass().getInterfaces()) {
			try {
				Method m = iface.getMethod(method.getName(), method.getParameterTypes());
				if (declaringInterface != null) {
					throw new BindingException("Ambiguous method mapping.  Two mapper interfaces contain the identical method signature for " + method);
				} else if (m != null) {
					declaringInterface = iface;
				}
			} catch (Exception e) {
				// Intentionally ignore.
				// This is using exceptions for flow control,
				// but it's definitely faster.
			}
		}
		if (declaringInterface == null) {
			throw new BindingException("Could not find interface with the given method " + method);
		}
		return declaringInterface;
	} 

}