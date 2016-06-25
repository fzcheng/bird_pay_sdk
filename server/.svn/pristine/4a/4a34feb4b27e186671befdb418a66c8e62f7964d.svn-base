package com.cheyooh.service.sdk.action.client;

import java.net.URLEncoder;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkMobileVcodeMapper;
import com.cheyooh.service.sdk.db.entity.SdkMobileVcode;
import com.cheyooh.service.sdk.idata.CmdVerifyPhoneSms;
import com.cheyooh.service.sdk.tools.GenerateTool;
import com.cheyooh.tools.http.HttpResult;
import com.cheyooh.tools.http.HttpUtils;

/**
 * 找回帐号
 * 
 * @author zhouzg@cheyooh.com
 *
 */

public class Retrive_account_sms extends AbstractSdkClientService<CmdVerifyPhoneSms> {

	@Override
	protected boolean isLoginRequired() {		 
		return false;
	}

	@Override
	protected Result execute(CmdVerifyPhoneSms cmd) {
		DAL dal=DALFactory.createDAL();
		try{			 
			int len  =Cfg.cfg.getInt("sdk.client.sms.vcode.length",6);
			String vcode=GenerateTool.getRandomString(len);
			
			SdkMobileVcode pv=new SdkMobileVcode();
			pv.setMobile(cmd.getPhone());			 
			pv.setCreateTime(new Date());
			pv.setType(2);
			pv.setVcode(vcode);
			
			 
			String msgContent = Cfg.msg.getString("sdk.client.retrive_account.sms");
			msgContent=StringUtils.replace(msgContent, "%1",vcode);
			String url=Cfg.cfg.getString("sdk.client.send_message_url","");
			try {
				url=url.replace("{0}", cmd.getPhone()).replace("{1}", URLEncoder.encode(msgContent,"utf-8"));
				HttpResult result=HttpUtils.newGetRequest(url).sendRequest();
				String dataString=result.getBody();
				int index=dataString.indexOf("error=\"0\"");
				 
				if(index<=0){
					String errorMsg=Cfg.msg.getString("sdk.client.send_message.error","验证码发送失败");
					return StatusCode.EXPIRED().setMessage(errorMsg);
				}
			} catch (Exception e) {

			}
			
			SdkMobileVcodeMapper vcodeMapper=dal.getMapper(SdkMobileVcodeMapper.class);
			vcodeMapper.insert(pv);
			
		 
			dal.commit();				
			
			return StatusCode.SUCCESS();
		}finally{
			dal.close();
		}
	}

}
