package com.cheyooh.service.sdk.action.client;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkUserMapper;
import com.cheyooh.service.sdk.db.entity.SdkUser;
import com.cheyooh.service.sdk.idata.CmdGeneral;
import com.cheyooh.service.sdk.idata.ResultCheckBindPhone;

/**
说明:该协议用于查询用户赂是否绑定过手机号
客户端的请求参数如下：
1.命令参数 m=check_bind_phone
2.固定参数
3.sid

 * @author zhouzg@cheyooh.com
 *
 */
public class Check_bind_phone extends AbstractSdkClientService<CmdGeneral> {

	@Override
	protected Result execute(CmdGeneral cmd) {
		DAL dal=DALFactory.createDAL();
		try{
			SdkUserMapper mapper=dal.getMapper(SdkUserMapper.class);
			SdkUser user=mapper.selectByPrimaryKey(gameSession.getUid());
			if(user!=null){
				boolean bind=user.getBind()!=null && user.getBind()==1;
				
				return new Result(new ResultCheckBindPhone(bind));				
			}else{
				return StatusCode.ERR_NOTFOUND().setMessage(Cfg.msg.getString("sdk.client.login.fail_user","用户或密码不正确!"));
			}
		}finally{
			dal.close();
		}
	}

	@Override
	protected boolean isLoginRequired() {		 
		return true;
	}
	
	

}
