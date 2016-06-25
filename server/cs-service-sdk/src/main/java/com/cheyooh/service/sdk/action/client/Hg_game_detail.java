package com.cheyooh.service.sdk.action.client;

import java.util.List;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;



import com.cheyooh.service.sdk.db.dao.GmiPkgMapper;
import com.cheyooh.service.sdk.db.dao.GmiScreenshotMapper;
import com.cheyooh.service.sdk.db.entity.GmiPkg;
import com.cheyooh.service.sdk.db.entity.GmiScreenshot;
import com.cheyooh.service.sdk.db.entity.GmiScreenshotExample;
import com.cheyooh.service.sdk.idata.CmdGameParam;
import com.cheyooh.service.sdk.idata.ResultGameDetail;
import com.cheyooh.service.sdk.tools.ResultHelper;

public class Hg_game_detail extends AbstractSdkClientService<CmdGameParam>{

	@Override
	protected boolean isLoginRequired() {
		
		return true;
	}

	@Override
	protected Result execute(CmdGameParam cmd) {
		DAL dal =DALFactory.createDAL();
		try{
			
			GmiPkgMapper mapper = dal.getMapper(GmiPkgMapper.class);
			GmiPkg g=mapper.selectByPrimaryKey(cmd.getPackage_name());
			if(g!=null){
				GmiScreenshotMapper gmiScreenshotMapper=dal.getMapper(GmiScreenshotMapper.class);
				GmiScreenshotExample gmiScreenshotExample=new GmiScreenshotExample();
				gmiScreenshotExample.createCriteria().andPkgEqualTo(g.getPkg());
				List<GmiScreenshot> sss=gmiScreenshotMapper.selectByExample(gmiScreenshotExample);
				ResultGameDetail detail=ResultHelper.toResultGameDetail(g,sss);
				return new Result(detail);
			}else{
				return StatusCode.ERR_NOTFOUND().setMessage("Game pkg not found: "+cmd.getPackage_name());
			}
		}finally{
			dal.close();
		}

	}

}
