package com.cheyooh.service.sdk.action.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultContentList;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkDDQMapper;
import com.cheyooh.service.sdk.idata.CmdGeneral;
import com.cheyooh.service.sdk.idata.ResultGameInfo;
import com.cheyooh.service.sdk.idata.ResultGroup;

/**
 * 该流程用于客户端向服务器端获取游戏新服信息。
 * 
 * @author zhouzg@cheyooh.com
 *
 */
public class New_game_server extends AbstractSdkClientService<CmdGeneral> {

	@Override
	protected boolean isLoginRequired() {
		 
		return false;
	}

	@Override
	protected Result execute(CmdGeneral cmd) {
		DAL dal=DALFactory.createDAL();
		try{
			SdkDDQMapper mapper=dal.getMapper(SdkDDQMapper.class);
			 
			List<Map<String,Object>> nss=mapper.DDQuery("selectGameNewServer",null);
 			
			ResultGroup[] gx=new ResultGroup[]{
					new ResultGroup(Cfg.msg.getString("sdk.new_server.group.1","今天开服")),			
					new ResultGroup(Cfg.msg.getString("sdk.new_server.group.2","即将开服")),			
					new ResultGroup(Cfg.msg.getString("sdk.new_server.group.3","已经开服"))
			};
			
			if(nss!=null)for(Map<String,Object> m:nss){			
			 	ResultGameInfo g=new ResultGameInfo();
				g.setTagName("game");
				g.setDl_url((String)m.get("game_dl"));
				g.setName((String)m.get("game_name"));
				g.setPackage_name((String)m.get("game_pkg"));
				g.setIcon_url(getImageUrl((String)m.get("game_icon")));
				g.setType((String)m.get("type"));
				
				int o1=((Number)m.get("o1")).intValue();
				if(o1==1){
					g.setTime("今天");										
				}else{				
					Date d=(Date)m.get("start_time");
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					g.setTime(sdf.format(d));
				}				
				gx[o1-1].addContent(g);
			}
			
			ResultContentList rcl=new ResultContentList();
			for(ResultGroup g:gx){
				if(g.getContents().size()>0){
					rcl.addContent(g);
				}
			}
			return new Result(rcl);
		}finally{
			dal.close();
		}
	}

}
