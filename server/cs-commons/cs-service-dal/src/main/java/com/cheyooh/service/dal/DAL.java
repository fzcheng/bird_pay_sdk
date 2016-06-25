package com.cheyooh.service.dal;

import java.io.Closeable;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 * 
 */
public interface DAL extends Closeable{

	/**
	 * 关闭数据库资源
	 */
	void close();
	
	/**
	 * 提交数据变更
	 */
	void commit();

	/**
	 * 撤销数据修改
	 */
	void rollback();

	/**
	 * 设置数据库访问模式. 缺省值为AUTO(读访问从库,写访问主库)
	 */
	void setAccessMode(DALMode mode);
	
	/**
	 * 获取一个访问数据库表的接口实例
	 * 
	 * @param type: 接口类型
	 * @return: Mapper接口的实例
	 */
	<T> T getMapper(Class<T> type);

}
