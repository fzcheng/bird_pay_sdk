package com.cheyooh.service.dal.mybatis;

import java.lang.reflect.Method;
import java.util.List;

public interface MapperMethodInterceptor {

	public Object beforeCall(String statement,Object proxy, Method method, List<Object> args);
	
	public Object afterCall(String statement,Object proxy, Method method, List<Object> args,Object result);
}
