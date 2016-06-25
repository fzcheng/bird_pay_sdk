package jeecg.ext.tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;

import jeecg.ext.online.db.DBFieldInfo;
import jeecg.ext.online.db.ExDatabaseService;
import jeecg.ext.online.db.entity.ExCgFormDataSource;
import jeecg.ext.online.db.entity.ExCgFormFieldEntity;
import jeecg.ext.online.db.entity.ExCgFormHeadEntity;

import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("onlineHeadFields")
public class OnlineHeadFields {
	@Resource(name = "commonService")
	private CommonService commonService;
	
	@Autowired
	private ExDatabaseService exDatabaseService;
	 
	private Map<String,DBFieldInfo> getFieldMeta(ExCgFormHeadEntity head){
		DataSource ds=exDatabaseService.getDatasource(head);
		
		String schema="";
		
		ExCgFormDataSource formds=head.getDatasource();
		if(formds!=null){
			String url=formds.getJdbcUrl();
			int x=url.lastIndexOf("/");
			if(x>0){
				schema=url.substring(x+1);
				x=schema.indexOf("?");
				if(x>0){
					schema=schema.substring(0,x);
				}
			}
		}else{
			schema="jeecg";
		}
		
		String tableName=head.getTableName();
		int p=tableName.indexOf(".");
		if(p>0){
			schema=tableName.substring(0,p);
			tableName=tableName.substring(p+1);
		}
		
		return loadFieldInfo(ds,schema,tableName);
	}
	
	public void updateTableFields(ExCgFormHeadEntity head){
		Map<String,DBFieldInfo> fs=getFieldMeta(head);
		if(fs.size()>0){
			List<ExCgFormFieldEntity> del=new ArrayList<ExCgFormFieldEntity>();
			List<ExCgFormFieldEntity> add=new ArrayList<ExCgFormFieldEntity>();
			
			Set<String> exists=new HashSet<String>();
			for(ExCgFormFieldEntity f:head.getCgformFields()){
				exists.add(f.getFieldName().toLowerCase());
				
				if(fs.containsKey(f.getFieldName().toLowerCase())==false){
					del.add(f);
				}
			}
			 
			for(DBFieldInfo f:fs.values()){
				if(exists.contains(f.getFieldName().toLowerCase())==false){
					ExCgFormFieldEntity fe=toFormFieldEntity(head,f);				 
					add.add(fe);
				}
			}
			if(add.size()>0){
				commonService.batchSave(add);
			}
			if(del.size()>0){
				commonService.deleteAllEntitie(del);
			}
		}
	}
	
	public void deleteTableFields(ExCgFormHeadEntity head){
		 
	}
	 
	public void addTableFields(ExCgFormHeadEntity head){
		Map<String,DBFieldInfo> fs=getFieldMeta(head);
		
		List<ExCgFormFieldEntity> entitys=new ArrayList<ExCgFormFieldEntity>();
		for(DBFieldInfo field:fs.values()){
			ExCgFormFieldEntity fe=toFormFieldEntity(head,field);
		 
			entitys.add(fe);
		}
		commonService.batchSave(entitys);
	}
	
	
	private ExCgFormFieldEntity toFormFieldEntity(ExCgFormHeadEntity head,DBFieldInfo field){
		ExCgFormFieldEntity fe=new ExCgFormFieldEntity();
		fe.setCgformHead(head);
		
		String c=field.getComment();
		if(StringUtils.isNotEmpty(c)){
			c=c.trim();
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<c.length();i++){
				char x=c.charAt(i);
				if(x==' ' || x=='\t' || x==':' || x==';' || x==',' || x=='\r' || x=='\n' || x=='('){
					break;
				}else{
					sb.append(x);
					if(sb.length()>10){
						break;
					}
				}
			}
			c=sb.toString();			
		}else{
			c=field.geFieldName();
		}
		if(c.length()>10){
			c=c.substring(0,10);
		}
		fe.setContent(c);
		
		fe.setFieldLength(200);
		fe.setFieldName(field.geFieldName().toLowerCase());
		
		if(field.isGeneratedKeys()){
			fe.setFieldValue("auto");
		}else{
			String def=field.getDefaultValue();
			if(StringUtils.isNotEmpty(def)){
				fe.setFieldValue(def);	
			}
		}
		
		fe.setId(UUID.randomUUID().toString().replaceAll("-",""));
		
		if(field.isPrimaryKey()){
			fe.setIsKey("Y");
		}else{
			fe.setIsKey("N");
		}
		
		fe.setIsNull(field.isNullable()?"Y":"N");
		
		fe.setIsQuery("Y");
		
		fe.setIsShow("Y");
		
		int m=(int)field.getMaxLength();
		if(m<=0){
			fe.setLength(256);
		}else{
			fe.setLength((int)m);
		}
		
		fe.setOrderNum(field.getIdx());
		
		fe.setPointLength(0);
		
		fe.setQueryMode("single");
		
		String ftype=field.getDataType().toLowerCase();
		if(ftype.startsWith("int")){
			fe.setShowType("text");
			fe.setType("Integer");
		}else if(ftype.equals("date")){
			fe.setShowType("date");
			fe.setType("Date");
		}else if(ftype.equals("datetime")){
			fe.setShowType("datetime");
			fe.setType("Datetime");
		}else if(ftype.equals("double") || ftype.equals("float")){
			fe.setShowType("text");
			fe.setType("Double");
		}else{
			fe.setShowType("text");
			fe.setType("String");
		}
		
		return fe;
	}
	
	private Map<String,DBFieldInfo> loadFieldInfo(DataSource datasource, String schema, String table){
		Map<String,DBFieldInfo> m = new LinkedHashMap<String,DBFieldInfo>();

		Connection connection =null;
		
		try {
			connection= datasource.getConnection();
			
			String sql = "select column_name,is_nullable,column_default,column_comment,character_maximum_length,extra,ORDINAL_POSITION,DATA_TYPE, COLUMN_KEY from INFORMATION_SCHEMA.Columns "
					+ " where table_schema='" + schema + "' and table_name='" + table + "' order by ORDINAL_POSITION";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {			
				DBFieldInfo fi = new DBFieldInfo(rs.getString(1), "YES".equalsIgnoreCase(rs.getString(2))
						, rs.getString(3), rs.getString(4), rs.getLong(5),"auto_increment".equalsIgnoreCase(rs.getString(6))
						,rs.getInt(7),rs.getString(8),"PRI".equalsIgnoreCase(rs.getString(9))
						);
				m.put(fi.getFieldName().toLowerCase(),fi);
			}
			rs.close();
			st.close();
			
			return m;
		}catch(SQLException e){
			throw new RuntimeException(e);
		} finally {
			if(connection!=null){
				try{ connection.close(); }catch(Exception e){}
			}
		}
 
	}
	 

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public ExDatabaseService getExDatabaseService() {
		return exDatabaseService;
	}

	public void setExDatabaseService(ExDatabaseService exDatabaseService) {
		this.exDatabaseService = exDatabaseService;
	}

	 

}
