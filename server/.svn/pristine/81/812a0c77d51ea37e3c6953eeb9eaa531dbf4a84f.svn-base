package com.cheyooh.service.sdk.db.dao;

import com.cheyooh.service.sdk.db.entity.SdkLogAccess;

public interface SdkLogAccessMapper {
    /**
     * 插入日志记录到数据库
     * record.tableName字段必须被设置
     * 
     * @param record
     * @return
     */
    int insertLogAccess(SdkLogAccess record);
    
    /**
     * 如果日志表不存在则创建该日志表(表明由字段: record.tableName 指定)
     * 
     * @param record
     * @return
     */
    int createLogAccessIfNotExists(SdkLogAccess record);
  
}