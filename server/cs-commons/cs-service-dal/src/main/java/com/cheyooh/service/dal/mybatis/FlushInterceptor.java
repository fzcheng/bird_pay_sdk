package com.cheyooh.service.dal.mybatis;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.cheyooh.service.dal.util.DALUtils;
import com.cheyooh.tools.log.Logger;

public class FlushInterceptor  implements MapperMethodInterceptor{
	static Logger logger=new Logger(FlushInterceptor.class);
	
	public Object beforeCall(String statement,Object proxy, Method method, List<Object> args){
		ConfigurationManager cm=ConfigurationManager.getInstance();
		
		SqlSession session=cm.openWriteSession(statement);
		try{
			Class<?> i=proxy.getClass().getInterfaces()[0];
			Object mapper=session.getMapper(i);
			
			Object r=method.invoke(mapper, DALUtils.list2Array(args));
			session.commit();
			return r;
		}catch(Exception e){
			logger.error("Flush statement: "+statement+", exception: "+e,e);
		}finally{
			session.close();
		} 
					 
		return null;
	}

	public Object afterCall(String statement,Object proxy, Method method, List<Object> args,Object result){		 
		return null;
	}

}
