package com.cheyooh.service.sdk.action.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderUnionpayMapper;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderUnionpay;
import com.cheyooh.service.sdk.idata.CmdPay;
import com.cheyooh.service.sdk.idata.ResultPayinfo;
import com.unionpay.upmp.sdk.service.UpmpService;


/**
 * 银联支付
 * @author Jaly
 *
 */
public class Pay_unionpay  extends AbstractSdkClientService<CmdPay>{

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
		// 5代表银联
		SdkOrder order=createOrder(cmd, 6);
		SdkOrderUnionpay unionpay=new SdkOrderUnionpay();
		DAL dal=DALFactory.createDAL();
		try{
			SdkOrderUnionpayMapper unionpayMapper=dal.getMapper(SdkOrderUnionpayMapper.class);
			SdkOrderMapper sdkOrderMapper=dal.getMapper(SdkOrderMapper.class);
			
			// 插入数据
			unionpay.setReqTime(order.getCreateTime());
			unionpay.setReqVersion(Cfg.cfg.getString("unionpay_version", "1.0.0"));
			unionpay.setReqCharset(Cfg.cfg.getString("unionpay_charset", "UTF-8"));
			unionpay.setReqTranstype("01");
			unionpay.setReqMerid(Cfg.cfg.getString("unionpay_mer.id", "123456"));
			unionpay.setReqBackendurl(Cfg.cfg.getString("unionpay_mer.back.end.url", ""));
			unionpay.setReqFrontendurl(Cfg.cfg.getString("unionpay_mer.back.end.url", ""));
			unionpay.setReqOrderdescription(order.getOrderDesc());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			unionpay.setReqOrdertime(sdf.format(order.getCreateTime()));
			//设置商户订单默认值，不然无法插入
			unionpay.setReqOrdernumber(order.getOrderNo());
			unionpay.setReqOrderamount(order.getAmount());
			// 人民币
			unionpay.setReqOrdercurrency("156");
			unionpayMapper.insertSelective(unionpay);
			order.setPayId(unionpay.getPayId());
			sdkOrderMapper.insert(order);
			dal.commit();
		}finally{
			dal.close();
		}
		return submitOrder(order,unionpay);
	}
	
	private Result submitOrder(SdkOrder order,SdkOrderUnionpay unionpay){
		String pay_info = null;
		try{
			pay_info = getSign(order,unionpay);
		}catch(Exception e){
			logger.error(e);

			orderException(order, unionpay, e.getMessage());
			return StatusCode.ERR_SYSTEM().setMessage(Cfg.msg.getString("sdk.system.pay.exception", "订单处理失败,请稍候再试!"));
		
		}
		DAL dal = DALFactory.createDAL();
		try{
			order.setStatus(2);

			updateOrder(dal, order, unionpay);
			return new Result(new ResultPayinfo(order.getOrderNo(),pay_info));
		} finally {
			dal.close();
		}
		
	}
	
	
	private void orderException(SdkOrder order, SdkOrderUnionpay unionpay,
			String message) {
		DAL dal = DALFactory.createDAL();
		try {
			 
			order.setStatus(4);

			updateOrder(dal, order, unionpay);
		} finally {
			dal.close();
		}
		
	}

	private void updateOrder(DAL dal, SdkOrder order, SdkOrderUnionpay unionpay) {
		
		SdkOrderUnionpayMapper unionpayMapper=dal.getMapper(SdkOrderUnionpayMapper.class);
		SdkOrderMapper sdkOrderMapper=dal.getMapper(SdkOrderMapper.class);
		sdkOrderMapper.updateByPrimaryKeySelective(order);
		unionpayMapper.updateByPrimaryKeySelective(unionpay);
		dal.commit();
	}

	private String getSign(SdkOrder order,SdkOrderUnionpay unionpay) throws Exception{
		// 请求要素
		String pay_Info="";
		Map<String, String> req = new HashMap<String, String>();
		req.put("version", unionpay.getReqVersion());// 版本号
		req.put("charset", unionpay.getReqCharset());// 字符编码
		req.put("transType", unionpay.getReqTranstype());// 交易类型
		req.put("merId", unionpay.getReqMerid());// 商户代码
		req.put("backEndUrl", unionpay.getReqBackendurl());// 通知URL
		//req.put("frontEndUrl", UpmpConfig.MER_FRONT_END_URL);// 前台通知URL(可选)
		req.put("orderDescription", unionpay.getReqOrderdescription());// 订单描述(可选)
		req.put("orderTime", unionpay.getReqOrdertime());// 交易开始日期时间yyyyMMddHHmmss
		//req.put("orderTimeout", "");// 订单超时时间yyyyMMddHHmmss(可选)
		req.put("orderNumber", unionpay.getReqOrdernumber());//订单号(商户根据自己需要生成订单号)
		req.put("orderAmount", String.valueOf((int)(unionpay.getReqOrderamount()*100)));// 订单金额(分，不能有小数位)
        req.put("orderCurrency", unionpay.getReqOrdercurrency());// 交易币种(可选)
        //req.put("reqReserved", "透传信息");// 请求方保留域(可选，用于透传商户信息)
                
        // 保留域填充方法
       // Map<String, String> merReservedMap = new HashMap<String, String>();
      //  merReservedMap.put("test", "test");
      //  req.put("merReserved", UpmpService.buildReserved(merReservedMap));// 商户保留域(可选)
		
		Map<String, String> resp = new HashMap<String, String>();
		boolean validResp = UpmpService.trade(req, resp);
		if(validResp){
			 if(resp.containsKey("tn") && resp.containsKey("respCode")){
				 pay_Info=resp.get("tn");
				 unionpay.setRespTn(resp.get("tn"));
				 unionpay.setRespTime(new Date(System.currentTimeMillis()));
				 unionpay.setRespRespcode(resp.get("respCode"));
				 if(resp.containsKey("respMsg")){
					 unionpay.setRespRespmsg(resp.get("respMsg"));
				 }
			 }
		}else {
			if(resp.containsKey("respCode")){
				 if(resp.containsKey("tn")){
					 unionpay.setRespTn(resp.get("tn"));
				 }
				 unionpay.setRespTn(resp.get("tn"));
				 unionpay.setRespTime(new Date(System.currentTimeMillis()));
				 unionpay.setRespRespcode(resp.get("respCode"));
				 if(resp.containsKey("respMsg")){
					 unionpay.setRespRespmsg(resp.get("respMsg"));
				 }
			 }
			throw new Exception(unionpay.getRespRespmsg());
		}
		return pay_Info;
	}
	

}
