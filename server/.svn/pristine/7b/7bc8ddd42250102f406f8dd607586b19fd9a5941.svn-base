package com.cheyooh.service.sdk.action.client;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkUserMapper;
import com.cheyooh.service.sdk.db.entity.SdkUser;
import com.cheyooh.service.sdk.idata.CmdRefreshSid;
import com.cheyooh.service.sdk.idata.ResultUserLogin;
import com.cheyooh.service.sdk.tools.CacheManager;
import com.cheyooh.service.sdk.tools.GameSession;


/**
说明：当客户端sid失效的时候，使用本协议重新获取sid

 * @author zhouzg@cheyooh.com
 *
 */
public class Refresh_sid extends AbstractSdkClientService<CmdRefreshSid> {

	@Override
	protected boolean isLoginRequired() {		 
		return false;
	}
	
	protected Result verify(CmdRefreshSid cmd){
		if(StringUtils.isEmpty(cmd.getSid())){
			return StatusCode.ERR_PARAMETER().setMessage("缺少参数: sid");
		}
		return super.verify(cmd);
	}

	@Override
	protected Result execute(CmdRefreshSid cmd) {
		DAL dal=DALFactory.createDAL();
		try{
			GameSession old_gs=new GameSession(cmd.getSid()); 
			if(old_gs.getUid()>0 && old_gs.getGameId()>0){
				SdkUserMapper mapper=dal.getMapper(SdkUserMapper.class);
				SdkUser user=mapper.selectByPrimaryKey(old_gs.getUid());
				if(user!=null){				
					GameSession new_gs=new GameSession(old_gs.getUid(),game.getGameId());
									
					ResultUserLogin rul=new ResultUserLogin();
					rul.setBind_phone(StringUtils.isNotEmpty(user.getMobile()));
					rul.setNick_name(user.getNickName());
					rul.setSid(new_gs.getSid());
					rul.setValid_time((int)(new_gs.getValidTime()/1000));
					 
					// 重新设置缓存
					CacheManager.setValue(new_gs.getSid(), new_gs.getSid());
					
					return new Result(rul);
				}else{
					return StatusCode.ERR_NOTFOUND().setMessage(Cfg.msg.getString("sdk.client.user_not_found","用户不存在."));
				}
			}else{
				return StatusCode.ERR_INVLAID().setMessage("非法的SID!");
			}		
		}finally{
			dal.close();
		}
	}
  

}
