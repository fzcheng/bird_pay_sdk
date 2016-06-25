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
import com.cheyooh.service.sdk.idata.ResultDomain;

public class Get_domainlist extends AbstractSdkClientService<CmdGeneral> {

	@Override
	protected boolean isLoginRequired() {

		return false;
	}

	@Override
	protected Result execute(CmdGeneral cmd) {
		DAL dal = DALFactory.createDAL();
		try {
			SdkGameDomainMapper sdkGameDomainMapper = dal
					.getMapper(SdkGameDomainMapper.class);
			SdkGameDomainExample sdkGameDomainExample = new SdkGameDomainExample();
			sdkGameDomainExample.createCriteria().andStatusEqualTo(1);
			List<SdkGameDomain> sdkGameDomains = sdkGameDomainMapper
					.selectByExample(sdkGameDomainExample);
			if (sdkGameDomains != null && sdkGameDomains.size() > 0) {
				ResultContentList contentList = new ResultContentList();
				contentList.setTagName("domainlist_info");
				for (SdkGameDomain sdkGameDomain : sdkGameDomains) {
					ResultDomain resultDomain = new ResultDomain();
					//resultDomain.setId(sdkGameDomain.getId());
					resultDomain.setDomain(sdkGameDomain.getDomain());
					//resultDomain.setStatus(sdkGameDomain.getStatus());
					contentList.addContent(resultDomain);
				}
				Result result = new Result(contentList);
				logger.debug("domain result : " + result.getXml("get_domainlist"));
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
