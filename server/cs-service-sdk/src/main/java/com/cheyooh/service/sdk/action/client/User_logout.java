package com.cheyooh.service.sdk.action.client;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkUserMapper;
import com.cheyooh.service.sdk.db.entity.SdkUser;
import com.cheyooh.service.sdk.idata.CmdUserLogout;
import com.cheyooh.service.sdk.tools.CacheManager;
import com.cheyooh.service.sdk.tools.GameSession;


/**
 * 说明：该协议用于用户的注销，清除sid
 * @author Com
 *
 */
public class User_logout extends AbstractSdkClientService<CmdUserLogout> {

	@Override
	protected boolean isLoginRequired() {

		return true;
	}
	
	
	protected Result verify(CmdUserLogout cmd){
		if(StringUtils.isEmpty(cmd.getSid())){
			return StatusCode.ERR_PARAMETER().setMessage("缺少参数: sid");
		}
		return super.verify(cmd);
	}

	@Override
	protected Result execute(CmdUserLogout cmd) {
		DAL dal=DALFactory.createDAL();
		try {
			GameSession old_gs=new GameSession(cmd.getSid()); 
			if(old_gs.getUid()>0 && old_gs.getGameId()>0){
				SdkUserMapper mapper=dal.getMapper(SdkUserMapper.class);
				SdkUser user=mapper.selectByPrimaryKey(old_gs.getUid());
				if(user!=null){
					// 删除之前缓存
					CacheManager.removeKey(cmd.getSid());
					return StatusCode.SUCCESS();
				}else{
					return StatusCode.ERR_NOTFOUND().setMessage(Cfg.msg.getString("sdk.client.user_not_found","用户不存在."));
				}
			}else {
				return StatusCode.ERR_INVLAID().setMessage("非法的SID!");
			}
		}  
		finally{
			dal.close();
		}

	}



}
