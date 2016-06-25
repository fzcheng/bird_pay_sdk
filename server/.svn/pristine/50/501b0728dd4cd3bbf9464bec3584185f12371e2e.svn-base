package com.cheyooh.service.dal.mybatis.parse;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.cheyooh.tools.log.Logger;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 *  
 */
public class DBSXMLConfigBuilder {
	  static Logger logger=new Logger(DBSXMLConfigBuilder.class);
	
	  private InputStream inputStream;
	  
	  private static final String NODE_NAME_COMMON = "common";
	  private static final String NODE_NAME_DB = "db";
	  private static final String NODE_NAME_MAPPER = "mapper";
	  private static final String ATTRIBUTE_NAME_URL = "url";
	  private static final String ATTRIBUTE_NAME_RESOURCE = "resource";
	  
	  public DBSXMLConfigBuilder(InputStream inputStream) {
	   	this.inputStream = inputStream;
	  }

	  public List<SelfDBConfigEntity> parse() 
	  {
		  Map<String ,String > commonMap = new HashMap<String,String>();
		  List<SelfDBConfigEntity> list = new ArrayList<SelfDBConfigEntity>();
		  try 
		  {
			  SAXBuilder builder = new SAXBuilder(false);  
			  Document document = builder.build(this.inputStream);   
			  Element dbs = document.getRootElement(); 
			  
			  //解析common公共节点
			  Element commons = dbs.getChild(NODE_NAME_COMMON);
			  List<Attribute> as = commons.getAttributes();
			  for(Attribute a : as)
			  {
				  commonMap.put(a.getName(), a.getValue());
			  }
			  //解析db节点
			  List<Element> dbNodes = dbs.getChildren(NODE_NAME_DB);
			  for(Element dbElement : dbNodes)
			  {
				  Map<String ,String > dbMap = new HashMap<String,String>();
				  SelfDBConfigEntity dbConfig = new SelfDBConfigEntity();
				  //追加公共属性
				  dbConfig.setAll(commonMap);
				  
				  //读取基本属性
				  List<Attribute> ass = dbElement.getAttributes();
				  for(Attribute a : ass)
				  {
					  dbMap.put(a.getName(), a.getValue());
				  } 
				  dbConfig.setAll(dbMap);
				  
				  //解析mapper
				  List<Element> mapperNodes = dbElement.getChildren(NODE_NAME_MAPPER);
				  for(Element mapperElement : mapperNodes)
				  {
					  String url = mapperElement.getAttributeValue(ATTRIBUTE_NAME_URL);
					  if(StringUtils.isNotEmpty(url))
					  {
						  dbConfig.addUrlMapper(url);
					  }
					  String resource = mapperElement.getAttributeValue(ATTRIBUTE_NAME_RESOURCE);
					  if(StringUtils.isNotEmpty(resource))
					  {
						  dbConfig.addResourceMappers(resource);
					  }
				  }
				  list.add(dbConfig);
			  }
			  
			} catch (Exception e) {
				
			}   
			return list;  
	  }
	
}