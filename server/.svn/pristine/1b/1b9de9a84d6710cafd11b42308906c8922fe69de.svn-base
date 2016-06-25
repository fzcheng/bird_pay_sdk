package com.cheyooh.service.dal.mybatis;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.ibatis.builder.BaseBuilder;
import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.type.TypeHandler;

import com.cheyooh.service.dal.mybatis.parse.SelfDBConfigEntity;
import com.cheyooh.service.dal.util.JarFiles;
import com.cheyooh.tools.log.Logger;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 *  
 */
public class SelfXMLConfigBuilder extends BaseBuilder {
	  static Logger logger=new Logger(SelfXMLConfigBuilder.class);
	
	  private boolean parsed;
	  private String environment;

	  private SelfDBConfigEntity selfDBConfigEntity = null;
	  
	  public SelfXMLConfigBuilder(SelfDBConfigEntity selfDBConfigEntity) {
	    this(selfDBConfigEntity, null, null);
	  }

	  public SelfXMLConfigBuilder(SelfDBConfigEntity selfDBConfigEntity, String environment) {
	    this(selfDBConfigEntity, environment, null);
	  }

	  public SelfXMLConfigBuilder(SelfDBConfigEntity selfDBConfigEntity, String environment, Properties props) {
	    super(new SelfConfiguration());
	    
	    this.selfDBConfigEntity = selfDBConfigEntity;
	    ErrorContext.instance().resource("SQL Mapper Configuration");
	    this.configuration.setVariables(props);
	    this.parsed = false;
	    this.environment = environment;
	  }

	  public SelfConfiguration parse() {
	    if (parsed) {
	      throw new BuilderException("Each MapperConfigParser can only be used once.");
	    }
	    parsed = true;
	    parseConfiguration();
	    return (SelfConfiguration)configuration;
	  }

	  private void parseConfiguration() {
	    try {
	    	//针对db.xml来解析成mybatis可以识别的对象。
	    	//分两步解析
	    	//1.解析dialect，mode到properties
	    	//2.解析数据库的相关信息environments
	    	//3.解析mappers到mapper里
	      //typeAliasesElement(root.evalNode("typeAliases"));
	      //pluginElement(root.evalNode("plugins"));
	      //objectFactoryElement(root.evalNode("objectFactory"));
	      //objectWrapperFactoryElement(root.evalNode("objectWrapperFactory"));
	      propertiesElement();
	      //settingsElement(root.evalNode("settings"));
	      environmentsElement();
	      //typeHandlerElement(root.evalNode("typeHandlers"));
	      mapperElement();
	    } catch (Exception e) {
	      throw new BuilderException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
	    }
	  }

	  private void typeAliasesElement(XNode parent) {
	    if (parent != null) {
	      for (XNode child : parent.getChildren()) {
	        String alias = child.getStringAttribute("alias");
	        String type = child.getStringAttribute("type");
	        try {
	          Class<?> clazz = Resources.classForName(type);
	          if (alias == null) {
	            typeAliasRegistry.registerAlias(clazz);
	          } else {
	            typeAliasRegistry.registerAlias(alias, clazz);
	          }
	        } catch (ClassNotFoundException e) {
	          throw new BuilderException("Error registering typeAlias for '" + alias + "'. Cause: " + e, e);
	        }
	      }
	    }
	  }

	  private void pluginElement(XNode parent) throws Exception {
	    if (parent != null) {
	      for (XNode child : parent.getChildren()) {
	        String interceptor = child.getStringAttribute("interceptor");
	        Properties properties = child.getChildrenAsProperties();
	        Interceptor interceptorInstance = (Interceptor) resolveClass(interceptor).newInstance();
	        interceptorInstance.setProperties(properties);
	        configuration.addInterceptor(interceptorInstance);
	      }
	    }
	  }


	  private void objectFactoryElement(XNode context) throws Exception {
	    if (context != null) {
	      String type = context.getStringAttribute("type");
	      Properties properties = context.getChildrenAsProperties();
	      ObjectFactory factory = (ObjectFactory) resolveClass(type).newInstance();
	      factory.setProperties(properties);
	      configuration.setObjectFactory(factory);
	    }
	  }
	  
	  private void objectWrapperFactoryElement(XNode context) throws Exception {
	    if (context != null) {
	      String type = context.getStringAttribute("type");
	      ObjectWrapperFactory factory = (ObjectWrapperFactory) resolveClass(type).newInstance();
	      configuration.setObjectWrapperFactory(factory);
	    }
	  }

	  //设置属性定义
	  private void propertiesElement() throws Exception {
		  
		Properties p = new Properties();
		p.put(SelfDBConfigEntity.KEY_dialect, this.selfDBConfigEntity.getDialect());
		p.put(SelfDBConfigEntity.KEY_mode, this.selfDBConfigEntity.getMode());
	    configuration.setVariables(p);
	  }

	  private void settingsElement(XNode context) throws Exception {
	    if (context != null) {
	      Properties props = context.getChildrenAsProperties();
	      // Check that all settings are known to the configuration class
	      MetaClass metaConfig = MetaClass.forClass(Configuration.class);
	      for (Object key : props.keySet()) {
	        if (!metaConfig.hasSetter(String.valueOf(key))) {
	          throw new BuilderException("The setting " + key + " is not known.  Make sure you spelled it correctly (case sensitive).");
	        }
	      }
	      configuration.setAutoMappingBehavior(AutoMappingBehavior.valueOf(stringValueOf(props.getProperty("autoMappingBehavior"), "PARTIAL")));
	      configuration.setCacheEnabled(booleanValueOf(props.getProperty("cacheEnabled"), true));
	      configuration.setLazyLoadingEnabled(booleanValueOf(props.getProperty("lazyLoadingEnabled"), false));
	      configuration.setAggressiveLazyLoading(booleanValueOf(props.getProperty("aggressiveLazyLoading"), true));
	      configuration.setMultipleResultSetsEnabled(booleanValueOf(props.getProperty("multipleResultSetsEnabled"), true));
	      configuration.setUseColumnLabel(booleanValueOf(props.getProperty("useColumnLabel"), true));
	      configuration.setUseGeneratedKeys(booleanValueOf(props.getProperty("useGeneratedKeys"), false));
	      configuration.setDefaultExecutorType(ExecutorType.valueOf(stringValueOf(props.getProperty("defaultExecutorType"), "SIMPLE")));
	      configuration.setDefaultStatementTimeout(integerValueOf(props.getProperty("defaultStatementTimeout"), null));
	    }
	  }

	  private void environmentsElement() throws Exception 
	  {
	      if (environment == null) {
	        environment = this.selfDBConfigEntity.getId();
	      }
          TransactionFactory txFactory = transactionManagerElement();
          DataSourceFactory dsFactory = dataSourceElement();
          Environment.Builder environmentBuilder = new Environment.Builder(environment)
              .transactionFactory(txFactory)
              .dataSource(dsFactory.getDataSource());
          configuration.setEnvironment(environmentBuilder.build());
	  }

	  private TransactionFactory transactionManagerElement() throws Exception {
	      String type = this.selfDBConfigEntity.getTransactionManagerType();
	      TransactionFactory factory = (TransactionFactory) resolveClass(type).newInstance();
	      return factory;
	  }

	  private DataSourceFactory dataSourceElement() throws Exception {
	      String type = this.selfDBConfigEntity.getDataSourceType();
	      DataSourceFactory factory = (DataSourceFactory) resolveClass(type).newInstance();
	      Properties p = new Properties();
	      p.setProperty(SelfDBConfigEntity.KEY_driver, this.selfDBConfigEntity.getDriver());
          p.setProperty(SelfDBConfigEntity.KEY_url, this.selfDBConfigEntity.getUrl());
          p.setProperty(SelfDBConfigEntity.KEY_username, this.selfDBConfigEntity.getUsername());
          p.setProperty(SelfDBConfigEntity.KEY_password, this.selfDBConfigEntity.getPassword());
          p.setProperty(SelfDBConfigEntity.KEY_poolMaximumActiveConnections, this.selfDBConfigEntity.getPoolMaximumActiveConnections());
          p.setProperty(SelfDBConfigEntity.KEY_poolMaximumIdleConnections, this.selfDBConfigEntity.getPoolMaximumIdleConnections());
          p.setProperty(SelfDBConfigEntity.KEY_poolPingEnabled, this.selfDBConfigEntity.getPoolPingEnabled());
          p.setProperty(SelfDBConfigEntity.KEY_poolPingQuery, this.selfDBConfigEntity.getPoolPingQuery());
          p.setProperty(SelfDBConfigEntity.KEY_poolPingConnectionsNotUsedFor, this.selfDBConfigEntity.getPoolPingConnectionsNotUsedFor());
	      factory.setProperties(p);
	      return factory;
	  }


	  private void typeHandlerElement(XNode parent) throws Exception {
	    if (parent != null) {
	      for (XNode child : parent.getChildren()) {
	        String javaType = child.getStringAttribute("javaType");
	        String jdbcType = child.getStringAttribute("jdbcType");
	        String handler = child.getStringAttribute("handler");

	        Class<?> javaTypeClass = resolveClass(javaType);
	        TypeHandler typeHandlerInstance = (TypeHandler) resolveClass(handler).newInstance();

	        if (jdbcType == null) {
	          typeHandlerRegistry.register(javaTypeClass, typeHandlerInstance);
	        } else {
	          typeHandlerRegistry.register(javaTypeClass, resolveJdbcType(jdbcType), typeHandlerInstance);
	        }
	      }
	    }
	  }


	  private void mapperElement() throws Exception 
	  {
		  InputStream inputStream = null;
		  //处理mapper resource 地方
		  for(String resource : this.selfDBConfigEntity.getResourceMappers())
		  {
	          ErrorContext.instance().resource(resource);
	          try{
	        	  
		          File rf=Resources.getResourceAsFile(resource);
		          String filepath = rf.getPath();
		          filepath = filepath.replace("file:", "");
		          //对打包后包是jar这种情况做处理
		          if(filepath.contains(".jar!"))
		          {
		        	  String jarFileName = filepath.split("!")[0];
		        	  String inFileName = filepath.split("!")[1];
		        	  if(inFileName.endsWith(".xml"))
		        	  {
		        		  	try {
		        		  		inputStream = JarFiles.getJarFile(jarFileName, inFileName);
		        		  		String fd = "file://" + jarFileName+ "!" + inFileName ;
		        		  		//String fd ="file://"+rf.getAbsolutePath().replaceAll("\\\\","/");
		        		  		XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, fd, configuration.getSqlFragments());
		        		  		mapperParser.parse();
							} catch (Exception e) {
								logger.error("Exception Mappers's Resourse Error: " + filepath,e);
							}
		        		  	finally{
		        		  		JarFiles.closeInputStream(inputStream);
		        		  	}
		        	  }
		        	  else
		        	  {
		        		  
		        		  Map<String,InputStream> iss = JarFiles.getJarFiles(jarFileName, inFileName);
		        		  for(Entry<String, InputStream> entry : iss.entrySet())
		        		  {
		        			  	try {
		        			  		String name = entry.getKey();
		        			  		inputStream = entry.getValue();
		        			  		// String fd ="file://"+jarFileName.replaceAll("\\\\","/") + name;
		        			  		String fd = "file://" + jarFileName+ "!" + name;
		        			  		XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, fd, configuration.getSqlFragments());
		        			  		mapperParser.parse();
								} catch (Exception e) {
									logger.error("Exception Mappers's Resourse Error: " + entry.getKey(),e);
								}
		        			  	finally{
		        			  		JarFiles.closeInputStream(inputStream);
		        			  	}
		        		  }
		        		  
		        	  }
		          }
		          else
		          {
		        	  //处理非jar包的形式，就是配置文件不再jar包的情况
		        	  if(rf.exists())
		        	  {
		        		  if(rf.isFile())
		        		  {
		        			  	try {
		        			  		inputStream = Resources.getResourceAsStream(resource);
		        			  		String fd ="file://"+rf.getAbsolutePath().replaceAll("\\\\","/");
		        			  		XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, fd, configuration.getSqlFragments());
		        			  		mapperParser.parse();
								} catch (Exception e) {
									logger.error("Exception Mappers's Resourse Error: " + rf.getAbsolutePath(),e);
								}
		        			  	finally{
		        			  		JarFiles.closeInputStream(inputStream);
		        			  	}
		        		  }
		        		  else
		        		  {
		        			  for(File f : rf.listFiles())
		        			  {
		        				  if(f.isFile())
		        				  {
		        					  	try {
		        					  		inputStream = Resources.getResourceAsStream(resource + File.separator + f.getName());
		        					  		String fd="file://"+ f.getAbsolutePath().replaceAll("\\\\","/");
		        					  		XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, fd, configuration.getSqlFragments());
		        					  		mapperParser.parse();  
										} catch (Exception e) {
											logger.error("Exception Mappers's Resourse Error: " + f.getAbsolutePath(),e);
										}
		        					  	finally
		        					  	{
		        					  		JarFiles.closeInputStream(inputStream);
		        					  	}
		        				  }
		        			  }
		        		  }
		        		  
		        	  }		          
		          }
	          }catch(Exception e){
	        	  logger.error("Exception load resource: "+resource+", "+e,e);
	          }  
		  }
		  
		  //处理mapper URl 地方
		  for(String url : this.selfDBConfigEntity.getUrlMappers())
		  {
	          ErrorContext.instance().resource(url);
	          try{
		          if(url.indexOf(":")>1)
		          {//URL
		        	  	try {
		        	  		inputStream = Resources.getUrlAsStream(url);
		        	  		SelfXMLMapperBuilder mapperParser = new SelfXMLMapperBuilder(inputStream, configuration, url, configuration.getSqlFragments());
		        	  		mapperParser.parse();
						} catch (Exception e) {
							logger.error("Exception Mappers's URL Error: " + url,e);
						}
		        	  	finally{
		        	  		JarFiles.closeInputStream(inputStream);
		        	  	}
		          }else{//Local file
		        	  if(url.indexOf(":")>0 || url.startsWith("/")){
		        		  //绝对路径
		        	  }else{	        		  
		        		  File fcfg = new File(ConfigurationManager.CFG_ROOT_PATH);
		        		  url = fcfg.getAbsolutePath() + File.separator + url;
		        	  }
		        	  File rf = new File(url);
		        	  if(rf.isFile())
		        	  {
		        		  	try {
		        		  		inputStream=new FileInputStream(url); 
		        		  		url="file://"+url;
		        		  		SelfXMLMapperBuilder mapperParser = new SelfXMLMapperBuilder(inputStream, configuration, url, configuration.getSqlFragments());
		        		  		mapperParser.parse();
							} catch (Exception e) {
								logger.error("Exception Mappers's URL Error: " + url,e);
							}
		        		  	finally{
		        		  		JarFiles.closeInputStream(inputStream);
		        		  	}
		        	  }
		        	  else if(rf.isDirectory())
		        	  {
		        		  for(File f : rf.listFiles())
		        		  {
		        			  if(f.isFile())
		        			  {
		        				  	try {
		        				  		String fd = "file://"+ f.getAbsolutePath().replaceAll("\\\\","/");
		        				  		//url="file://"+url + File.separator + f.getName();
		        				  		inputStream=new FileInputStream(fd); 
		        				  		SelfXMLMapperBuilder mapperParser = new SelfXMLMapperBuilder(inputStream, configuration, fd, configuration.getSqlFragments());
		        				  		mapperParser.parse();  
									} catch (Exception e) {
										logger.error("Exception Mappers's URL Error: " + f.getAbsolutePath(),e);
									}
		        				  	finally{
		        				  		JarFiles.closeInputStream(inputStream);
		        				  	}
		        			  }
		        		  }
		        	  }else{
		        		  logger.error("File not found: "+rf.getAbsolutePath());
		        	  }

		          }
	          }catch(Exception e){
	        	  logger.error("Exception load url: "+url+", "+e,e);
	          }
		  }
	  }
	}