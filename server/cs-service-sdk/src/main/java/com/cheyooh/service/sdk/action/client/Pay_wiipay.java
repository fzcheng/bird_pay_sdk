package com.cheyooh.service.sdk.action.client;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.dal.mybatis.SqlStatementHandler;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.*;
import com.cheyooh.service.sdk.db.entity.*;
import com.cheyooh.service.sdk.idata.CmdPay;
import com.cheyooh.service.sdk.idata.ResultPayinfo;

public class Pay_wiipay  extends AbstractSdkClientService<CmdPay>{

	@Override
	protected boolean isLoginRequired() {
		
		return true;
	}
	
	protected Result verify(CmdPay cmd) {
		// TODO :发布时修改为1f
		if (cmd.getAmount() < 0.000001f) {
			return StatusCode.ERR_PARAMETER().setMessage("无效的支付金额: " + cmd.getAmount());
		}
		return super.verify(cmd);
	}

	@Override
	protected Result execute(CmdPay cmd) {
		 SdkOrder order=createOrder(cmd, 5);
		 SdkOrderWiipay wiipay=new SdkOrderWiipay();
		 
		 
		 DAL dal=DALFactory.createDAL();
		 SqlStatementHandler.startLogSql();
		 try{
			 SdkOrderWiipayMapper mapperWiipay=dal.getMapper(SdkOrderWiipayMapper.class);
			 SdkOrderMapper mapperOrder=dal.getMapper(SdkOrderMapper.class);		 
			 SdkWiipayPaycodeMapper wiiCodeMapper=dal.getMapper(SdkWiipayPaycodeMapper.class);
			 SdkWiipayPaycodeExample wiipayPaycodeExample=new SdkWiipayPaycodeExample();
			 BigDecimal priceCode=new BigDecimal(Float.toString(cmd.getAmount()));
			 wiipayPaycodeExample.createCriteria().andGameIdEqualTo(game.getGameId()).andPriceEqualTo(priceCode);
			 List<SdkWiipayPaycode> wiiPayCodeList= wiiCodeMapper.selectByExample(wiipayPaycodeExample);
			 for(String sql:SqlStatementHandler.getLogSql()){
					System.out.println(sql);
				}

			 if(wiiPayCodeList!=null && wiiPayCodeList.size()>0){
				 SdkWiipayPaycode wiiCode=wiiPayCodeList.get(0);
				 String payCode=wiiCode.getPayCode()+"";
				 wiipay.setReqTime(new Date(System.currentTimeMillis()));
				 wiipay.setReqAmt(cmd.getAmount());
				 wiipay.setReqOrder(order.getOrderNo());
				 wiipay.setReqPaycode(payCode);
				 mapperWiipay.insertSelective(wiipay);
				 order.setPayId(wiipay.getPayId());
				 mapperOrder.insert(order);
				 dal.commit();
			 }else{
				return  StatusCode.ERR_SYSTEM().setMessage(Cfg.msg.getString("sdk.system.pay.exception", "订单处理失败,请稍候再试!"));
			 }
			 
		 }finally{
			 dal.close();
		 }
		return new Result(new ResultPayinfo(order.getOrderNo(),wiipay.getReqPaycode()));
	}
	
	/** 
     * 使用java正则表达式去掉多余的.与0 
     * @param s 
     * @return  
     */  
    public  String subZeroAndDot(String s){  
        if(s.indexOf(".") > 0){  
            s = s.replaceAll("0+?$", "");//去掉多余的0  
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
        }  
        return s;  
    }  

}
