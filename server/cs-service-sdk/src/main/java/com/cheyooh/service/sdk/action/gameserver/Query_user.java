package com.cheyooh.service.sdk.action.gameserver;

import org.apache.commons.codec.digest.DigestUtils;

import com.cheyooh.service.framework.cfg.Cfg;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.db.entity.SdkUser;
import com.cheyooh.service.sdk.idata.ResultQueryUser;
import com.cheyooh.service.sdk.idata.gameserver.CmdQueryUser;
import com.cheyooh.service.sdk.tools.DBHelper;
import com.cheyooh.service.sdk.tools.GameSession;
import com.cheyooh.service.sdk.tools.SignParameter;

public class Query_user extends AbstractGameServerService<CmdQueryUser>{
	private GameSession gs;
	
	@Override
	protected Result verify(CmdQueryUser cmd) {	
		gs=new GameSession(cmd.getSid());
		
		if(gs.getGameId()<1 || gs.getUid()<1){
			return StatusCode.ERR_INVLAID().setMessage(Cfg.msg.getString("sdk.session.invalid","无效的会话,请重新登录!"));
		}else if(gs.isExpired()){
			return StatusCode.ERR_INVLAID().setMessage(Cfg.msg.getString("sdk.session.timeout","会话已过期,请重新登录!"));
		}else{
			return super.verify(cmd);
		}
	}

	@Override
	protected Result execute(CmdQueryUser cmd) {
		SdkUser user=DBHelper.getUserById(gs.getUid());
	 	if(user!=null){
			SignParameter sp=new SignParameter();
			sp.addParameter("uid",""+user.getUid());
			sp.addParameter("nickName", user.getNickName());
			String sign=sp.getSignString("&");
			sign+="&"+cp.getApiKey();
			String checksign=DigestUtils.md5Hex(sign);
			
			ResultQueryUser u=new ResultQueryUser();
			u.setUid(user.getUid());
			u.setNickName(user.getNickName());
			u.setChecksign(checksign);
			
			return new Result(u);
	 	}else{
	 		return StatusCode.ERR_PARAMETER().setMessage("用户不存在: "+gs.getUid());
	 	}
	}
}
