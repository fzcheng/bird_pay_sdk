package com.cheyooh.service.sdk.action.client;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.basic.Service;
import com.cheyooh.service.framework.basic.ServiceContext;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.idata.CmdFastPay;
import com.cheyooh.service.sdk.idata.CmdPay;
import com.cheyooh.service.sdk.idata.ResultFastPay;
import com.cheyooh.service.sdk.idata.ResultPayMmdoFastInfo;
import com.cheyooh.service.sdk.idata.ResultPayMmdoInfo;
import com.cheyooh.service.sdk.idata.ResultPayMmdoInfoOrderList;
import com.cheyooh.service.sdk.idata.ResultPayinfo;

/**
 * 快捷支付
 * @author Com
 *
 */


public class Pay_fast extends AbstractSdkClientService<CmdFastPay>{

	protected Result verify(CmdFastPay cmd) {
		// TODO :发布时修改为1f
		if (cmd.getAmount() < 0.000001f) {
			return StatusCode.ERR_PARAMETER().setMessage("无效的支付金额: " + cmd.getAmount());
		}
		return super.verify(cmd);
	}
	
	private String splitString="\\|";
	
	@Override
	protected boolean isLoginRequired() {
		
		return true;
	}

	@Override
	protected Result execute(CmdFastPay cmd) {
		
		String[] imsiArray=cmd.getImsi().split(splitString);
		for(String imsi:imsiArray){
			int operatorId=getOperatorByIMSI(imsi);
			if(operatorId<=0){
				ResultFastPay resultFastPay=new ResultFastPay();
				resultFastPay.setPay_info("IMIS码错误");
				return new Result(resultFastPay);
			}
		}
		
		DAL dal = DALFactory.createDAL();
		try{
			ServiceContext rc = getServiceContext(cmd.getServiceContext().getRequest(), cmd.getServiceContext().getResponse()); 
			Result result=getResult("mmdo", new Pay_mmdo(), rc);
			logger.debug("the Pay_fast result is:"
					+ result.getXml("Pay_fast"));
			return result;
		
		}finally{
			dal.close();
		}
		
	}
	
	/**
	 * 获取对应支付方式的支付信息
	 * @param type 支付类型
	 * @param service 
	 * @param rc
	 * @return
	 */
	private Result getResult(String type,Service<CmdPay> service,ServiceContext rc){
		Result result=null;
		if(result==null){
			try {
				result=service.doService(rc);
			} catch (Exception e) {
				logger.error("the Pay_fast getResult appear error is :"+e);
				return StatusCode.NON_SERVICE();
			}
			
		}
		if(result!=null){
			if(result.getContent() instanceof ResultPayinfo){
				ResultPayinfo resiltPayInfo=(ResultPayinfo)result.getContent();
				if(resiltPayInfo!=null){
					ResultFastPay resultFastPay=new ResultFastPay();
					resultFastPay.setOrder_no(resiltPayInfo.getOrder_no());
					resultFastPay.setPay_info(resiltPayInfo.getPay_info());
					resultFastPay.setType(type);
					return new Result(resultFastPay);
				}
			}
			else if(result.getContent() instanceof ResultPayMmdoInfoOrderList){
				ResultPayMmdoInfoOrderList resultPayMmdoInfoOrderList=(ResultPayMmdoInfoOrderList)result.getContent();
				if(resultPayMmdoInfoOrderList!=null){
					Result r = new Result();
					r.setStatus(result.getStatus());
					r.setSubStatus(result.getSubStatus());
					r.setMessage(result.getMessage());
					r.setContent(resultPayMmdoInfoOrderList);
					return r;
				}
			}
			else{
				ResultPayMmdoInfo resiltPayInfo=(ResultPayMmdoInfo)result.getContent();
				if(resiltPayInfo!=null){
					ResultPayMmdoFastInfo payMmdoFastInfo=new ResultPayMmdoFastInfo();
					payMmdoFastInfo.setContents(resiltPayInfo.getContents());
					payMmdoFastInfo.setOrder_no(resiltPayInfo.getOrder_no());
					payMmdoFastInfo.setPay_info(resiltPayInfo.getPay_info());
					payMmdoFastInfo.setType(type);
					payMmdoFastInfo.setSms_type(resiltPayInfo.getSms_type());
					payMmdoFastInfo.setSms_pay_type(resiltPayInfo.getSms_pay_type());
					payMmdoFastInfo.setSms_content_type(resiltPayInfo.getSms_content_type());
					Result r = new Result();
					r.setStatus(result.getStatus());
					r.setSubStatus(result.getSubStatus());
					r.setMessage(result.getMessage());
					r.setContent(payMmdoFastInfo);
					//return new Result(payMmdoFastInfo);
					return r;
				}
			}
			return result;
			
		}
		return StatusCode.ERR_NOTFOUND();
	}
	
	protected ServiceContext getServiceContext(HttpServletRequest request, HttpServletResponse response){
		return new ServiceContext(request, response);
	}
	
	/**
	 * 计算运营商
	 * 1:中国移动
	 * 2:中国联通
	 * 3:中国电信
	 * @param imsi
	 * @return
	 */
	private int getOperatorByIMSI(String imsi){
		int operatorId=0;
		if(StringUtils.isNotEmpty(imsi)){
			if(imsi.startsWith("46000") ||imsi.startsWith("46002")|| imsi.startsWith("46007")){
				//中国移动
				operatorId=1;
			}else if(imsi.startsWith("46001")|| imsi.startsWith("46006")
					|| imsi.startsWith("46010")){
				// 中国联通
				operatorId=2;
			}else if(imsi.startsWith("46003") || imsi.startsWith("46005")
					|| imsi.startsWith("46011")){
				// 中国电信
				operatorId=3;
			}
		}
		return operatorId;
	}

}
