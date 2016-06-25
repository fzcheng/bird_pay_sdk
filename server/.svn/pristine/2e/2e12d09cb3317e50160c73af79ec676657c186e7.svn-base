package com.cheyooh.service.sdk.action.client;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkUserMapper;
import com.cheyooh.service.sdk.db.entity.SdkUser;
import com.cheyooh.service.sdk.idata.CmdChangeNickname;

/**
 * 
 * 说明：该协议用于用户修改昵称
 * 
 * @author zhouzg@cheyooh.com
 *
 */

public class Change_nickname extends AbstractSdkClientService<CmdChangeNickname> {

	@Override
	protected boolean isLoginRequired() {		 
		return true;
	}

	@Override
	protected Result execute(CmdChangeNickname cmd) {
		DAL dal=DALFactory.createDAL();
		try{
			SdkUserMapper userMapper=dal.getMapper(SdkUserMapper.class);
			SdkUser user=userMapper.selectByPrimaryKey(gameSession.getUid());
			if(user!=null){
				user.setNickName(cmd.getNickname());
				userMapper.updateByPrimaryKeySelective(user);
				dal.commit();
				
				return StatusCode.SUCCESS();
			}else{
				return StatusCode.ERR_NOTFOUND().setMessage(Cfg.msg.getString("sdk.client.user_not_found","用户不存在."));
			}
			
		
		}finally{
			dal.close();
		}
	}

}
