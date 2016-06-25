package com.cheyooh.service.sdk.action.client;

import java.util.List;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultContentList;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.db.dao.*;
import com.cheyooh.service.sdk.db.entity.*;
import com.cheyooh.service.sdk.idata.CmdGeneral;
import com.cheyooh.service.sdk.idata.ResultDomainforcp;

public class Get_domainlist_forcp extends AbstractSdkClientService<CmdGeneral> {
	@Override
	protected boolean isLoginRequired() {

		return false;
	}

	@Override
	protected Result execute(CmdGeneral cmd) {
		DAL dal = DALFactory.createDAL();
		try {
			SdkGameDomainForcpMapper sdkGameDomainForcpMapper=dal
					.getMapper(SdkGameDomainForcpMapper.class);
			SdkGameDomainForcpExample sdkGameDomainForcpExample=new SdkGameDomainForcpExample();
			sdkGameDomainForcpExample.createCriteria().andStatusEqualTo(1);
			List<SdkGameDomainForcp> SdkGameDomainForcps=sdkGameDomainForcpMapper.selectByExample(sdkGameDomainForcpExample);
			if(SdkGameDomainForcps!=null&&SdkGameDomainForcps.size()>0){
				ResultContentList contentList = new ResultContentList();
				for(SdkGameDomainForcp sdkGameDomainForcp: SdkGameDomainForcps){
					ResultDomainforcp resultDomainforcp = new ResultDomainforcp();
					//resultDomain.setId(sdkGameDomain.getId());
					resultDomainforcp.setGameid(sdkGameDomainForcp.getGameId());
					resultDomainforcp.setDomain(sdkGameDomainForcp.getDomain());
					//resultDomain.setStatus(sdkGameDomain.getStatus());
					contentList.addContent(resultDomainforcp);
				}
				Result result = new Result(contentList);
				logger.debug("domainforcp result : " + result.getXml("get_domainlist_forcp"));
				dal.commit();
				return result;
			}
			dal.commit();
			return StatusCode.ERR_NOTFOUND();

		} finally {
			dal.close();
		}

	}
}
