package com.cheyooh.service.sdk.action.client;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkMobileVcodeMapper;
import com.cheyooh.service.sdk.db.dao.SdkUserMapper;
import com.cheyooh.service.sdk.db.entity.SdkMobileVcode;
import com.cheyooh.service.sdk.db.entity.SdkMobileVcodeExample;
import com.cheyooh.service.sdk.db.entity.SdkUser;
import com.cheyooh.service.sdk.db.entity.SdkUserExample;
import com.cheyooh.service.sdk.idata.CmdVerifyPhone;
import com.cheyooh.service.sdk.idata.ResultOneKeyRegister;
import com.cheyooh.service.sdk.tools.CryptoUtil;
import com.cheyooh.service.sdk.tools.GenerateTool;
import com.cheyooh.service.sdk.tools.StringTool;

public class Retrive_account extends AbstractSdkClientService<CmdVerifyPhone> {

	@Override
	protected boolean isLoginRequired() {		 
		return false;
	}

	@Override
	protected Result execute(CmdVerifyPhone cmd) {
		DAL dal=DALFactory.createDAL();
		try{			 
			SdkMobileVcodeMapper vcodeMapper=dal.getMapper(SdkMobileVcodeMapper.class);
			SdkMobileVcodeExample example=new SdkMobileVcodeExample();
			example.createCriteria().andVcodeEqualTo(cmd.getVcode()).andMobileEqualTo(cmd.getPhone()).andTypeEqualTo(2);
			example.setOrderByClause("create_time desc");
			List<SdkMobileVcode> codes=vcodeMapper.selectByExample(example);
			if(codes!=null && codes.size()>0){
				String exp=Cfg.cfg.getString("sdk.client.bind_phone.vcode.expired","30m");
				long le=StringTool.getTimeMillis(exp);				
				
				SdkMobileVcode code=codes.get(0);
				long ts=System.currentTimeMillis()-code.getCreateTime().getTime();
				if(ts<le){
					SdkUserMapper userMapper=dal.getMapper(SdkUserMapper.class);
					SdkUserExample exampleUser=new SdkUserExample();
					exampleUser.createCriteria().andMobileEqualTo(code.getMobile()).andBindEqualTo(1);
					List<SdkUser> users=userMapper.selectByExample(exampleUser);
					if(users!=null && users.size()>0){
						SdkUser user=users.get(0);
						
						String password=GenerateTool.createPassword();
						
						user.setPwd(DigestUtils.md5Hex(password));
						userMapper.updateByPrimaryKeySelective(user);
						dal.commit();
						
						ResultOneKeyRegister content=new ResultOneKeyRegister();
						content.setName(user.getUid());
						password=CryptoUtil.encrypt(password);
						content.setPassword(password);
						return new Result(content);
					}else{
						return StatusCode.ERR_NOTFOUND().setMessage(Cfg.msg.getString("sdk.client.user_not_found","用户不存在."));
					}
				} 
			}			
			return StatusCode.ERR_INVLAID().setMessage(Cfg.msg.getString("sdk.client.bind_phone.vcode.invalid"));			
		}finally{
			dal.close();
		}
		
	}

}
