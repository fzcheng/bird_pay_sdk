package com.cheyooh.service.sdk.action.client;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultContentList;
import com.cheyooh.service.sdk.db.dao.SdkSwbInfoMapper;
import com.cheyooh.service.sdk.db.entity.SdkSwbInfo;
import com.cheyooh.service.sdk.db.entity.SdkSwbInfoExample;
import com.cheyooh.service.sdk.idata.CmdGeneral;
import com.cheyooh.service.sdk.idata.ResultGetSwbCmcc;

/**
 * 三网- 中国移动返回appid和appkey值
 * 
 */
public class Get_swb_cmcc extends AbstractSdkClientService<CmdGeneral> {

	@Override
	protected boolean isLoginRequired() {

		return false;
	}

	@Override
	protected Result execute(CmdGeneral cmd) {
		DAL dal = DALFactory.createDAL();
		try {
			SdkSwbInfoMapper sdkSwbInfoMapper = dal
					.getMapper(SdkSwbInfoMapper.class);
			SdkSwbInfoExample sdkSwbInfoExample = new SdkSwbInfoExample();
			sdkSwbInfoExample.createCriteria()
					.andGameIdEqualTo(game.getGameId())
					.andOperatorTypeEqualTo(1).andUseStatusEqualTo(1);
			SdkSwbInfo sdkSwbInfo = sdkSwbInfoMapper
					.selectOne(sdkSwbInfoExample);
			String appId = "";
			String appKey = "";
			ResultGetSwbCmcc resultGetSwbCmcc = new ResultGetSwbCmcc();
			if (sdkSwbInfo != null) {
				appId = sdkSwbInfo.getAppId();
				appKey = sdkSwbInfo.getAppKey();
			}
			ResultContentList contentList = new ResultContentList();
			contentList.setTagName("item");
			resultGetSwbCmcc.setAppId(appId);
			resultGetSwbCmcc.setAppKey(appKey);
			contentList.addContent(resultGetSwbCmcc);
			Result result = new Result(contentList);
			logger.debug("swb_cmcc result : " + result.getXml("Get_swb_cmcc"));
			dal.commit();
			return result;
		} finally {
			dal.close();
		}
	}

}
