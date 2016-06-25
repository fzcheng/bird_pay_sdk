package com.cheyooh.service.sdk.action.client;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMo9Mapper;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderMo9;
import com.cheyooh.service.sdk.idata.CmdMo9Pay;
import com.cheyooh.service.sdk.idata.ResultPayinfo;
import com.mokredit.payment.Md5Encrypt;

public class Pay_mo9  extends AbstractSdkClientService<CmdMo9Pay>{

	protected Result verify(CmdMo9Pay cmd) {
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
	protected Result execute(CmdMo9Pay cmd) {
		//mo9支付方式
		 SdkOrder order=createOrder(cmd, 4);
		 SdkOrderMo9 mo9=new SdkOrderMo9();
		 DAL dal=DALFactory.createDAL();
		 try {
			SdkOrderMo9Mapper mapperMo9=dal.getMapper(SdkOrderMo9Mapper.class);
			SdkOrderMapper mapperOrder=dal.getMapper(SdkOrderMapper.class);
			
			mo9.setReqBody(order.getOrderName());
			mo9.setReqNotifyUrl(Cfg.cfg.getString("sdk.pay.mo9.notify_url"));
			mo9.setReqOutTradeNo(order.getOrderNo());
			mo9.setReqPartner(Cfg.cfg.getString("sdk.pay.mo9.pay_to_email"));
			mo9.setReqSeller(Cfg.cfg.getString("sdk.pay.mo9.app_id"));
			mo9.setReqSubject(order.getOrderName());
			mo9.setReqTime(order.getCreateTime());
			mo9.setReqTotalFee(order.getAmount());
			String clString="CN";
			if(StringUtils.isNotEmpty(cmd.getCl())){
				clString=cmd.getCl();
			}
			mo9.setReqCl(clString);
			String currencyString="CNY";
			if(StringUtils.isNotEmpty(cmd.getCurrency())){
				currencyString=cmd.getCurrency();
			}
			mo9.setReqCurrency(currencyString);
			mapperMo9.insertSelective(mo9);
			
			order.setPayId(mo9.getPayId());
			mapperOrder.insert(order);
			dal.commit();
			
		}finally{
			dal.close();
		}
		return submitOrder(order,mo9);
	}

	private Result submitOrder(SdkOrder order, SdkOrderMo9 mo9) {
		 String pay_info=null;
		 try {
			 pay_info = getSign(order,mo9);
		} catch (Exception e) {
			logger.error(e);
			orderException(order, mo9, e.getMessage());

			return StatusCode.ERR_SYSTEM().setMessage(Cfg.msg.getString("sdk.system.pay.exception", "订单处理失败,请稍候再试!"));
		}
		 DAL dal = DALFactory.createDAL();
			try {
				order.setStatus(2);

				updateOrder(dal, order, mo9);

				return new Result(new ResultPayinfo(order.getOrderNo(),pay_info));
			} finally {
				dal.close();
			}
	}
	
	private void orderException(SdkOrder order, SdkOrderMo9 mo9, String e) {
		DAL dal = DALFactory.createDAL();
		try {
			 
			order.setStatus(4);

			updateOrder(dal, order, mo9);
		} finally {
			dal.close();
		}
	}
	private void updateOrder(DAL dal, SdkOrder order, SdkOrderMo9 mo9) {
		SdkOrderMo9Mapper mapperMo9=dal.getMapper(SdkOrderMo9Mapper.class);
		SdkOrderMapper mapperOrder=dal.getMapper(SdkOrderMapper.class);

		mapperMo9.updateByPrimaryKeySelective(mo9);
		mapperOrder.updateByPrimaryKeySelective(order);
		dal.commit();
	}

	private String getSign(SdkOrder order, SdkOrderMo9 mo9)  throws Exception{
		
		String pay_uri=Cfg.cfg.getString("sdk.pay.mo9.pay_uri");
		Map<String,String> payParams = new HashMap<String,String>();
		payParams.put("pay_to_email", mo9.getReqPartner());
		payParams.put("app_id", mo9.getReqSeller());
		payParams.put("version", Cfg.cfg.getString("sdk.pay.mo9.version"));	
		payParams.put("notify_url", mo9.getReqNotifyUrl());
		payParams.put("invoice", mo9.getReqOutTradeNo());
		payParams.put("payer_id", order.getUid()+"");
		payParams.put("extra_param", order.getOrderNo());
		/**
		 * 用户所在的所有地区的国家缩写，例如中国为“CN”,美国“US”.
		 * 如果你的系统中已经保存了用户的所在地信息则可以直接使用，如果你的系统中没有用户所在地信息，则建议你按如下
		 * 优先级设置用户归属地信息：
		 * 1.使用手机终端SIM卡所在的归属地.TelephonyManager.getSimCountryIso()
		 * 2.使用手机终端所在的IP地址归属地.
		 * 3.使用手机操作系统的Local信息.Context.getResources().getConfiguration().locale.getCountry()
		 * 4.根据你的游戏运营情况，自定义归属地信息。例如你的游戏百分之九十都是中国用户，则可以直接设置为“CN”。
		 */
		payParams.put("lc", mo9.getReqCl());
		/***交易金额*/
		payParams.put("amount", String.valueOf(mo9.getReqTotalFee()));
		/**交易的货币类型*/
		payParams.put("currency", mo9.getReqCurrency());
		/***交易的商品名称*/
		payParams.put("item_name", mo9.getReqSubject());
		String sign  =  Cfg.cfg.getString("sdk.pay.mo9.RSA_PRIVATE");
		sign= Md5Encrypt.sign(payParams,sign);
		payParams.put("sign", sign);
		/***
		 * 拼接请求参数,请在提交支付请求时使用UTF-8编码，以免item_name等包含汉字或特殊字符的属性不能正确识别.
		 */
		StringBuffer queryStr = new StringBuffer();
		Set<String> keys = payParams.keySet();
		for(String key:keys)
		{
			//将请求参数进行URL编码
			String value=null;
			try
			{
				value = URLEncoder.encode((String)payParams.get(key), "UTF-8");
			} catch (UnsupportedEncodingException e)
			{
				throw new IllegalArgumentException("封装支付请求URL失败.	",e);
			}
			queryStr.append("&"+key+"="+value);
		}
		
		return pay_uri+"&"+queryStr;

	}

}
