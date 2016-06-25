package com.cheyooh.service.sdk.action.gameserver;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.idata.ResultQueryOrder;
import com.cheyooh.service.sdk.idata.gameserver.CmdQueryOrder;
import com.cheyooh.service.sdk.tools.DBHelper;

public class Query_order extends AbstractGameServerService<CmdQueryOrder>{
  
	@Override
	protected Result execute(CmdQueryOrder cmd) {	 
		SdkOrder order=DBHelper.getOrderById(cmd.getOrder_no());
		if(order!=null){
			ResultQueryOrder rqo=new ResultQueryOrder();
			try{
				List<String[]> nvs=DBHelper.createOrderParameters(order);
				for(String[] nv:nvs){					
					BeanUtils.setProperty(rqo,nv[0],nv[1]);
				}
				
				logger.info("Query order: "+cmd.getOrder_no()+", status: "+rqo.getStatus()+"("+rqo.getSstatus()+")");
				
				return new Result(rqo);
			}catch(Exception e){
				logger.error("Set order property exception: "+e,e);
				return StatusCode.ERR_SYSTEM().setMessage("Exception: "+e);
			}
		}else{
			logger.info("Query order not exist: "+cmd.getOrder_no());
			
			return StatusCode.ERR_PARAMETER().setMessage("无效的订单号: "+cmd.getOrder_no());
		}
	}

}
