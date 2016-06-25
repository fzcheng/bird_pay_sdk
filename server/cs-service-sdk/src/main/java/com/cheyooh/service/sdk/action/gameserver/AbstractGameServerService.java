package com.cheyooh.service.sdk.action.gameserver;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.framework.basic.Service;
import com.cheyooh.service.framework.idata.Cmd;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.entity.SdkGameCp;
import com.cheyooh.service.sdk.tools.DBHelper;
import com.cheyooh.service.sdk.tools.SignParameter;

public abstract class AbstractGameServerService <C extends Cmd> extends Service<C>{
	protected SdkGameCp     cp;
	
	protected Result verify(C cmd){
		HttpServletRequest request=cmd.getServiceContext().getRequest();
		
		String s=request.getParameter("cpid");
		if(!StringUtils.isEmpty(s)){
			int cpid=Integer.parseInt(s);
			cp=DBHelper.getGameCpById(cpid);
			if(cp!=null){
				String secret=cp.getApiKey();
				
				SignParameter sp=new SignParameter();
				sp.addParameters(request.getParameterMap());				 
				String sign=sp.getSignString("&")+"&"+secret;
				String md5=DigestUtils.md5Hex(sign);
				String checksign=request.getParameter("checksign");
				if(!md5.equalsIgnoreCase(checksign)){
					if(Cfg.cfg.getBoolean("sdk.debug",true) && request.getParameter("debug")!=null){
						return StatusCode.ERR_INVLAID().setMessage("非法的访问! debug: "+md5);
					}else{
						return StatusCode.ERR_INVLAID().setMessage("非法的访问!");
					}
				}else{
					return null;
				}
			}else{
				return StatusCode.ERR_INVLAID().setMessage("无效的参数cpid: "+cpid);
			}						 
		}else{
			return StatusCode.ERR_PARAMETER().setMessage("缺少参数: cpid");
		}
	}
}
