package com.cheyooh.service.dal.mybatis;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALMode;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 *  
 */
public class DefaultDALImpl implements DAL{
	private SelfSqlSession session=new SelfSqlSession();
	
	public DefaultDALImpl(){
		
	}
	public void close() {
		session.close();				 
	}

	public void commit() {
		session.commit();
	}

	public void rollback() {
		session.rollback();
	}
	
	public void setAccessMode(DALMode mode){
		session.setAccessMode(mode);
	}

	public <T> T getMapper(Class<T> type) {
		return session.getMapper(type);	 
	}
}
