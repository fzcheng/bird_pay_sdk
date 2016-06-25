package com.cheyooh.service.sdk.action.client;

import org.apache.ibatis.session.RowBounds;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultContentPage;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.db.dao.SdkGamePlanMapper;
import com.cheyooh.service.sdk.db.dao.SdkGameUsedPlanMapper;
import com.cheyooh.service.sdk.db.entity.SdkGamePlan;
import com.cheyooh.service.sdk.db.entity.SdkGamePlanExample;
import com.cheyooh.service.sdk.db.entity.SdkGameUsedPlan;
import com.cheyooh.service.sdk.db.entity.SdkGameUsedPlanExample;
import com.cheyooh.service.sdk.idata.CmdGameParam;
import com.cheyooh.service.sdk.idata.ResultGameInfo;
import com.cheyooh.service.sdk.tools.ResultHelper;
import com.cheyooh.tools.utils.Pagination;

public class Hg_recommend_list  extends AbstractSdkClientService<CmdGameParam>{

	@Override
	protected boolean isLoginRequired() {
		return true;
	}

	@Override
	protected Result execute(CmdGameParam cmd) {	
		DAL dal=DALFactory.createDAL();
		try {
			SdkGameUsedPlanMapper mapperPlan=dal.getMapper(SdkGameUsedPlanMapper.class);
			SdkGameUsedPlanExample examplePlan=new SdkGameUsedPlanExample();
			examplePlan.createCriteria().andGameIdEqualTo(game.getGameId());
			SdkGameUsedPlan plan=mapperPlan.selectOne(examplePlan);
			if(plan!=null){
				SdkGamePlanMapper mapper=dal.getMapper(SdkGamePlanMapper.class);				
				SdkGamePlanExample example=new SdkGamePlanExample();
				example.createCriteria().andPlanIdEqualTo(plan.getPlanId());
				example.setOrderByClause("idx asc");
				
				RowBounds rows=new RowBounds(cmd.getPageIndex()*cmd.getPageSize(),cmd.getPageSize());
				Pagination<SdkGamePlan> page=mapper.selectByExample(example,rows);
				
				int count=mapper.countByExample(example);
				page.setTotal(count);
				ResultContentPage content = new ResultContentPage("games", page);
				if (page.getList() != null)
					for (SdkGamePlan g : page.getList()) {
						ResultGameInfo game = toResultGame(g);
						content.addContent(game);
					}
				return new Result(content);
			}else{
				return StatusCode.SUCCESS();
			}
		}finally{
			dal.close();
		} 
	}
	
	private ResultGameInfo toResultGame(SdkGamePlan g) {
		ResultGameInfo game=new ResultGameInfo();
		game.setCategory(g.getGameCategory());
		game.setIcon_url(g.getGameIcon());
		game.setName(g.getGameName());
		game.setPackage_name(g.getGamePkg());
		game.setRating(ResultHelper.formatStar5(g.getGameRating()));
		game.setDl_url(g.getGameUrl());
		return game;
	}	


}

