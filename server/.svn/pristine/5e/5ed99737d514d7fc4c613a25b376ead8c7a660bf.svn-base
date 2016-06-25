package com.cheyooh.service.sdk.action.client;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultContentPage;
import com.cheyooh.service.sdk.db.dao.SdkInformationMapper;
import com.cheyooh.service.sdk.db.entity.SdkInformation;
import com.cheyooh.service.sdk.db.entity.SdkInformationExample;
import com.cheyooh.service.sdk.idata.CmdGeneral;
import com.cheyooh.service.sdk.idata.ResultInformation;
import com.cheyooh.tools.utils.Pagination;

/**
 * 
 * 该流程用于客户端向服务器端获取资讯列表。
 * 
 * 
 * @author zhouzg@cheyooh.com
 *
 */
public class Info_list extends AbstractSdkClientService<CmdGeneral> {

	@Override
	protected boolean isLoginRequired() {		 
		return false;
	}

	@Override
	protected Result execute(CmdGeneral cmd) {
		DAL dal=DALFactory.createDAL();
		try{
			SdkInformationMapper mapper=dal.getMapper(SdkInformationMapper.class);
			SdkInformationExample example=new SdkInformationExample();
			example.setOrderByClause("create_time desc");
			example.createCriteria().andGameIdEqualTo(game.getGameId()).andStatusEqualTo(1);
			Pagination<SdkInformation> page=mapper.selectByExample(example, new RowBounds(pageIndex*pageSize,pageSize));
			ResultContentPage rcp=new ResultContentPage("info_list",page);
			List<SdkInformation> infos=page.getList();
			if(infos!=null)for(SdkInformation info:infos){
				ResultInformation c=new ResultInformation();
				c.setSummary(info.getDetail());
				c.setTitle(info.getTitle());
				c.setType(info.getType());
				c.setUrl(info.getWeburl());
				rcp.addContent(c);
			}
			return new Result(rcp);
		}finally{
			dal.close();
		}
	}

}
