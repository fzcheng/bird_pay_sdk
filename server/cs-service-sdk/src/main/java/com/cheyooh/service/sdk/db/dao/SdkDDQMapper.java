package com.cheyooh.service.sdk.db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.cheyooh.service.sdk.db.entity.SdkUserLoginGame;
import com.cheyooh.tools.utils.Pagination;

public interface SdkDDQMapper {

	public List<Map<String,Object>>       DDQuery   (String queryId,Map<String,Object> param);
	
	public Map<String,Object>             DDQueryOne(String queryId,Map<String,Object> param);
	
	public Pagination<Map<String,Object>> DDQueryPage(String queryId,Map<String,Object> param,RowBounds bounds);
	
	public int                            DDQUpdate  (String queryId,Map<String,Object> param);
	
	
	public int insertSdkUserLoginGameOnDuplicateUpdate(SdkUserLoginGame loginGame);
}