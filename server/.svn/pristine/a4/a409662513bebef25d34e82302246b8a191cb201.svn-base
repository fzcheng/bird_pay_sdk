package com.cheyooh.service.sdk.action.client;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkUserMapper;
import com.cheyooh.service.sdk.db.entity.SdkUser;
import com.cheyooh.service.sdk.idata.CmdOneKeyRegister;
import com.cheyooh.service.sdk.idata.ResultOneKeyRegister;
import com.cheyooh.service.sdk.tools.CryptoUtil;
import com.cheyooh.service.sdk.tools.GenerateTool;

/**
 * 说明：该协议用于用户的一键注册获取用户名和密码
客户端的请求参数如下：
1.命令参数 m=one_key_register
2.device_id ----- 自动注册返回的device_id

 * @author zhouzg@cheyooh.com
 *
 */
public class One_key_register extends AbstractSdkClientService<CmdOneKeyRegister> {

	@Override
	protected Result execute(CmdOneKeyRegister cmd) {
		DAL dal=DALFactory.createDAL();
		try{
			String password=GenerateTool.createPassword();
			
			SdkUser user=new SdkUser();
			user.setDeviceId(cmd.getDevice_id());
			user.setBind(0);
			user.setLoginTimes(0);
			user.setPwd(DigestUtils.md5Hex(password));
			user.setRegChannel(cmd.getChannel());
			user.setRegVersion(cmd.getVersion());
			user.setRegGame(game.getGameId());
			user.setRegIp(cmd.getRequestHost());
			user.setRegTime(new Date());	
			user.setStatus(1);
			SdkUserMapper mapper=dal.getMapper(SdkUserMapper.class);
			mapper.insertSelective(user);
			
			String nn=Cfg.cfg.getString("sdk.client.register.nickname","Player%1");
			nn=StringUtils.replace(nn, "%1", ""+user.getUid());
			user.setNickName(nn);
			mapper.updateByPrimaryKeySelective(user);
			
			dal.commit();
			
			ResultOneKeyRegister rug=new ResultOneKeyRegister();
			rug.setName(user.getUid());
			 // 修改密码方式
			password=CryptoUtil.encrypt(password);
			rug.setPassword(password);				 
			return new Result(rug);
		}finally{
			dal.close();
		}			
		 
	}

	@Override
	protected boolean isLoginRequired() {
		 
		return false;
	}

//	public static void main(String[] args) {
//    System.out.println(DigestUtils.md5Hex("res2828793"));
//  }
}
