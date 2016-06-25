package com.cheyooh.service.sdk.action.client;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.sdk.db.dao.SdkSmsGameMapper;
import com.cheyooh.service.sdk.db.dao.SdkUserAutoMapper;
import com.cheyooh.service.sdk.db.entity.SdkSmsGame;
import com.cheyooh.service.sdk.db.entity.SdkSmsGameExample;
import com.cheyooh.service.sdk.db.entity.SdkUserAuto;
import com.cheyooh.service.sdk.db.entity.SdkUserAutoExample;
import com.cheyooh.service.sdk.idata.CmdAutoRegister;
import com.cheyooh.service.sdk.idata.ResultAutoRegister;
import com.cheyooh.service.sdk.tools.GenerateTool;

/**
 * 说明：该流程用于客户端向服务器端获取device_id。
客户端的请求参数如下：
1.命令参数 m=auto_register
2.imei ---- 手机的imei号，必填。如果imei号获取错误，则使用手机的wifi MAC地址。

 * @author zhouzg@cheyooh.com
 *
 */
public class Auto_register extends AbstractSdkClientService<CmdAutoRegister> {
	private static final Logger logger = Logger.getLogger(Auto_register.class);
	@Override
	protected Result execute(CmdAutoRegister cmd) {
		DAL dal=DALFactory.createDAL();
		try{
			SdkUserAutoMapper mapper=dal.getMapper(SdkUserAutoMapper.class);
			SdkUserAutoExample example=new SdkUserAutoExample();
			example.createCriteria().andImeiEqualTo(cmd.getImei());
			List<SdkUserAuto> users=mapper.selectByExample(example);
			
			String device_id=GenerateTool.createUuid();
			SdkUserAuto user=null;
			if(users.size()>0){
				user=users.get(0);
				device_id=user.getDeviceId();
			}else{
				user=new SdkUserAuto();					
				user.setDeviceId(device_id);
				user.setImei(cmd.getImei()); 
				user.setRegChannel(cmd.getChannel());
				user.setRegGame(game.getGameId());
				user.setRegIp(cmd.getRequestHost());
				user.setRegTime(new Date());				
				user.setRegVersion(cmd.getVersion());
				
				mapper.insertSelective(user);
				dal.commit();
			} 
			
			String smstip="1";
			//在表sdk_sms_game设置是否弹出第一次登陆提示框
			SdkSmsGameMapper sdkSmsGameMapper = dal
					.getMapper(SdkSmsGameMapper.class);
			SdkSmsGameExample sdkSmsGameExample = new SdkSmsGameExample();
			sdkSmsGameExample.createCriteria()
					.andGameIdEqualTo(game.getGameId()).andSmstipEqualTo(0);
			SdkSmsGame sdkSmsGame = sdkSmsGameMapper
					.selectOne(sdkSmsGameExample);
			if (sdkSmsGame != null) {
				smstip="0";
			}
			
			ResultAutoRegister rag=new ResultAutoRegister();
			rag.setDevice_id(device_id);
			rag.setSmstip(smstip);
			return new Result(rag);
		}catch(Exception e){
			logger.error("auto_register error is "+e);
			return null;
		}finally{
			dal.close();
		} 					 
	}

	@Override
	protected boolean isLoginRequired() {		 
		return false;
	}

}
