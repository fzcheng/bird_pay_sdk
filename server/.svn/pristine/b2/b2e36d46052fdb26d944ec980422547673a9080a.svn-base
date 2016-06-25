package com.cheyooh.service.sdk.action.client;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderTenpayMapper;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderTenpay;
import com.cheyooh.service.sdk.idata.CmdPay;
import com.cheyooh.service.sdk.idata.ResultPayinfo;
import com.cheyooh.service.sdk.tools.StringTool;
import com.tenpay.client.TenpayHttpClient;
import com.tenpay.client.XMLClientResponseHandler;
import com.tenpay.wap.WapPayInitRequestHandler;

/**
 * 
 * 财付通支付
 * 
 * @author zhouzg@cheyooh.com
 * 
 */
public class Pay_tenpay extends AbstractSdkClientService<CmdPay> {
	protected Result verify(CmdPay cmd) {
		// TODO :发布时修改为1f
		if (cmd.getAmount() < 0.000001f) {
			return StatusCode.ERR_PARAMETER().setMessage("无效的支付金额: " + cmd.getAmount());
		}
		return super.verify(cmd);
	}
 
	@Override
	protected boolean isLoginRequired() {
		return true;
	}
	
	@Override
	protected Result execute(CmdPay cmd) {
		SdkOrder order=createOrder(cmd,2);
		SdkOrderTenpay tenpay=new SdkOrderTenpay();
		
		DAL dal=DALFactory.createDAL();
		try{
			SdkOrderTenpayMapper mapperTenpay=dal.getMapper(SdkOrderTenpayMapper.class);
			SdkOrderMapper       mapperOrder =dal.getMapper(SdkOrderMapper.class);
		 	 
			// 帐号(财付通商户号或者财付通帐号)
			String bargainor_id = Cfg.cfg.getString("sdk.pay.tenpay.p1_MerId");
			String callback     = Cfg.cfg.getString("sdk.pay.tenpay.notify");
			  
			tenpay.setReqAttach("gamesdk");
			tenpay.setReqBankType(0);
			tenpay.setReqBargainorId(bargainor_id);
			tenpay.setReqCharset("1");
			tenpay.setReqDesc(order.getOrderName());
			tenpay.setReqFeeType(1);
			tenpay.setReqNotifyUrl(callback);
			tenpay.setReqPurchaserId(null);
			tenpay.setReqSalePlat("211");
			tenpay.setReqSpBillno(order.getOrderNo());
			tenpay.setReqTime(order.getCreateTime());
			tenpay.setReqTimeExpire("");
			tenpay.setReqTimeStart(new SimpleDateFormat("yyyyMMddhhmmss").format(order.getCreateTime()));
			tenpay.setReqTotalFee(cmd.getAmount());
			tenpay.setReqVer("2.0");			 
			
			mapperTenpay.insertSelective(tenpay);
						
			order.setPayId(tenpay.getPayId());			 
			mapperOrder.insert(order);
			
			dal.commit();	
		}finally{
			dal.close();
		}
		 
		return submitOrder(order,tenpay);		 
	}

	private Result submitOrder(SdkOrder order,SdkOrderTenpay tenpay){			
		String pay_info=null;
		try{
			pay_info=sendToTenpay(order,tenpay);
		}catch(Exception e){		
			logger.error(e);
			
			orderException(order,tenpay,e.getMessage());
				
			return StatusCode.ERR_SYSTEM().setMessage(Cfg.msg.getString("sdk.system.pay.exception","订单处理失败,请稍候再试!"));			 
		}
		
		DAL dal=DALFactory.createDAL();		
		try{		 
			tenpay.setRespTime(new Date());
			// 修改
			tenpay.setRespTokenId(pay_info);
			 
			order.setStatus(2);
			
			updateOrder(dal,order,tenpay);
			
			pay_info="token_id="+pay_info+"&bargainor_id="+tenpay.getReqBargainorId();
			
			return new Result(new ResultPayinfo(order.getOrderNo(),pay_info));
		}finally{
			dal.close();
		}
		
	}

	
	private void orderException(SdkOrder order,SdkOrderTenpay tenpay,String e){
		DAL dal=DALFactory.createDAL();
		try{
			tenpay.setRespTime(new Date());
			tenpay.setRespErrInfo(StringTool.max(e,200));
							 
			order.setStatus(4);
			
			updateOrder(dal,order,tenpay);
			 						
		}finally{
			dal.close();
		}
	}
	
	private void updateOrder(DAL dal,SdkOrder order,SdkOrderTenpay tenpay){
		SdkOrderTenpayMapper mapperTenpay=dal.getMapper(SdkOrderTenpayMapper.class);
		SdkOrderMapper       mapperOrder =dal.getMapper(SdkOrderMapper.class);
		
		mapperTenpay.updateByPrimaryKeySelective(tenpay);
		mapperOrder.updateByPrimaryKeySelective(order);
		dal.commit();		
	}
	
	private String sendToTenpay(SdkOrder order, SdkOrderTenpay tenpay)throws Exception {		
		// 密钥
		String key      = Cfg.cfg.getString("sdk.pay.tenpay.keyValue");
		String callback = Cfg.cfg.getString("sdk.pay.tenpay.callback","http://www.360doo.com");
		// 创建支付初始化请求示例
		WapPayInitRequestHandler reqHandler = new WapPayInitRequestHandler(null, null);
		// 初始化
		reqHandler.init();
		// 设置密钥
		reqHandler.setKey(key);

		// -----------------------------
		// 设置请求参数
		// -----------------------------
		reqHandler.setParameter("desc", tenpay.getReqDesc());
		reqHandler.setParameter("bargainor_id", tenpay.getReqBargainorId());
		reqHandler.setParameter("sp_billno",tenpay.getReqSpBillno());		 
		reqHandler.setParameter("total_fee", ""+(int)(tenpay.getReqTotalFee()*100));
		reqHandler.setParameter("notify_url", tenpay.getReqNotifyUrl());
		reqHandler.setParameter("attach", tenpay.getReqAttach());
		
		reqHandler.setParameter("purchaser_id", tenpay.getReqPurchaserId());
		reqHandler.setParameter("callback_url",callback);
		reqHandler.setParameter("time_start",tenpay.getReqTimeStart());
		reqHandler.setParameter("time_expire",tenpay.getReqTimeExpire());
		
		// 获取请求带参数的url
		String requestUrl = reqHandler.getRequestURL();

		// 获取debug信息
		String debuginfo = reqHandler.getDebugInfo();
		logger.info("requestUrl:" + requestUrl+",  debuginfo: " + debuginfo);		 

		// 创建TenpayHttpClient，后台通信
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setCharset("utf-8");
		// 设置请求内容
		httpClient.setReqContent(requestUrl);
		// 远程调用
		if (httpClient.call()) {
			String resContent = httpClient.getResContent();
			logger.info("responseContent:" + resContent);

			// ----------------------
			// 应答处理,获取token_id
			// ----------------------
			XMLClientResponseHandler resHandler = new XMLClientResponseHandler();
			resHandler.setContent(resContent);
			resHandler.setCharset("utf-8");
			
			String token_id = resHandler.getParameter("token_id");
			if(StringUtils.isNotEmpty(token_id)){
				return token_id;
			}else{
				throw new RuntimeException("Get token fail: "+resContent);
			}
		}else{			
			throw new RuntimeException("Http call fail: "+httpClient.getErrInfo());
		}
		
	}

}
