package com.cheyooh.service.dal.db.#{db}.dao;

import java.util.List;
import java.util.Map;
import com.cheyooh.tools.utils.Pagination;
import org.apache.ibatis.session.RowBounds;

public interface #{dbUpper}DDQMapper {

	public List<Map<String,Object>>       DDQuery   (String queryId,Map<String,Object> param);
	
	public Map<String,Object>             DDQueryOne(String queryId,Map<String,Object> param);
	
	public Pagination<Map<String,Object>> DDQueryPage(String queryId,Map<String,Object> param,RowBounds bounds);
}
