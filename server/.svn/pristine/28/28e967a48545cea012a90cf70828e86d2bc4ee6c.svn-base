package com.cheyooh.service.framework.basic;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.framework.cfg.Cfg;
import com.cheyooh.service.framework.idata.Cmd;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.framework.utils.annotation.Match;
import com.cheyooh.service.framework.utils.annotation.NotNull;
import com.cheyooh.service.framework.utils.binding.ClassHelper;
import com.cheyooh.service.framework.utils.binding.ClassHelper.FGS;
import com.cheyooh.service.framework.utils.binding.ClassHelper.MetaClass;
import com.cheyooh.service.framework.utils.binding.RequestDataBinding;
import com.cheyooh.tools.log.Logger;

public abstract class Service<C extends Cmd> {
	protected Logger logger=new Logger(this.getClass());
	protected C _cmd;
	public Result doService(ServiceContext rc)throws ServletException, IOException{
		Result r=null;
		long t=System.currentTimeMillis();
		try{		
			C cmd=getCmd(rc);
			if(cmd==null){
				cmd=createCmd();
				if(cmd!=null){
					RequestDataBinding.fromRequest(rc.getRequest(), cmd);
				}else{
					throw new RuntimeException("Cmd is null, Maybe override the method: getCmd().");
				}				
			}
			_cmd=cmd;
			
			cmd.setServiceContext(rc);
			
			Result v=doDefaultVerify(cmd);
			if(v==null){
				v=verify(cmd);
			}
			if(v==null){
				Result c=execute(cmd);
				
				rc.setResult(c);		
			}else{
				rc.setResult(v);
			}
			
			r=rc.getResult();
			if(r.getType()==null){
				r.setType(cmd.getCmd());			
			}
			
			r.setTimeService((int)(System.currentTimeMillis()-t));
			log(_cmd,r);
			
			return r;
		}catch(Throwable e){
			logException(rc,e);
			
			if(e instanceof RuntimeException){
				throw (RuntimeException)e;
			}else{
				throw new RuntimeException(e);
			}
		}		
	}		
	
	protected void log(C cmd, Result r){		 
		
	}
	protected void logException(ServiceContext rc,Throwable e){
		
	}
	
	@SuppressWarnings("unchecked")
	private C createCmd(){
		Class<?> c=getGenericType(0);
		if(c!=Object.class){
			try{
				C r= (C)c.newInstance();				
				return r;						
			}catch(Exception e){
				logger.error("Create instance error: "+c,e);
			}
		}
		return null;
	}
	
	private Class<?> getGenericType(int index) {
		Type genType = getClass().getGenericSuperclass();
		for(int i=0;i<2;i++){
			if (!(genType instanceof ParameterizedType)) {
				if(getClass().getSuperclass()!=null){
					genType=getClass().getSuperclass().getGenericSuperclass();
				}else{
					break;
				}
			}else{
				break;
			}
		}
		
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			throw new RuntimeException("Index outof bounds");
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class<?>) params[index];
	}

	protected C getCmd(ServiceContext rc){
		return null;
	}
	
	/**
	 * 根据字段备注来校验
	 * @param cmd
	 * @return
	 */
	protected Result doDefaultVerify(C cmd){
		MetaClass mc=ClassHelper.getMetaClass(cmd);
		Collection<FGS> fgs=mc.getMappingFields();
		for(FGS f:fgs){		
			//非空检查
			Result r=verifyNotNull(f,cmd);
			if(r!=null){
				return r;
			}
						
			//内容格式检查
			r=verifyMatch(f,cmd);
			if(r!=null){
				return r;
			}
		}
		return null;
	}
	
	private Result verifyNotNull(FGS f,C cmd){
		NotNull nn=f.getField().getAnnotation(NotNull.class);
		String value=f.getAttribute(cmd);
		if(nn!=null && value==null){
			String message=StringUtils.isNotEmpty(nn.message())?nn.message():"缺少参数: "+f.getField().getName();
			if(Cfg.msg!=null){
				String key=cmd.getCmd()+"."+f.getField().getName()+".null";
				message=Cfg.msg.getString(key, message);
			}
			return StatusCode.ERR_PARAMETER().setMessage(message);
		}
		return null;
	}
	
	private Result verifyMatch(FGS f,C cmd){
		Match ma=f.getField().getAnnotation(Match.class);
		String value=f.getAttribute(cmd);
		if(ma!=null && value!=null){
			String type=ma.type();
			String regex=ma.regex();
			
			if("email".equalsIgnoreCase(type)){
				regex="^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
			}else if("mobile".equalsIgnoreCase(type)){
				regex="^1\\d{10}$";
			}else if("lpn".equalsIgnoreCase(type)){
				regex="^[\u4E00-\u9FA5][a-zA-z][\\da-zA-z]{5,6}";
			}else if("password".equalsIgnoreCase(type)){
				regex="^[^\u4e00-\u9fa5\n\r\t]{4,20}$";
			}
			
			if(Cfg.msg!=null){
				regex=Cfg.msg.getString("regex."+type,regex);
			}
			
			if(StringUtils.isNotEmpty(regex) && !value.matches(regex)){
				String message=StringUtils.isNotEmpty(ma.message())?ma.message():"参数格式错误: "+f.getField().getName();
				if(Cfg.msg!=null){
					String key=cmd.getCmd()+"."+f.getField().getName()+".notmatch";
					message=Cfg.msg.getString(key, message);
				}
				return StatusCode.ERR_PARAMETER().setMessage(message);
			}
		}
		
		return null;
	}
	
	protected abstract Result verify(C cmd);	
	protected abstract Result execute(C cmd);
}
