package com.cheyooh.service.sdk.action.notify;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alipay.client.base.PartnerConfig;
import com.alipay.client.security.RSA;
import com.alipay.client.util.AlipayCore;
import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Cmd;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultXJContent;
import com.cheyooh.service.sdk.db.dao.SdkOrderAlipayMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderAlipay;

/**
 * 支付宝异步通知
 * 
 * @author zhouzg@cheyooh.com
 *
 */
public class AlipayNotify extends AbstractNotifyService<Cmd> {
 	@Override
	protected Result verify(Cmd cmd) {
 		return null;
	}

	@Override
	protected Result execute(Cmd cmd) {		 
		HttpServletRequest httpServletRequest = cmd.getServiceContext()
				.getRequest();
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> alipayMap = new HashMap<String, String>();
		Enumeration<String> paramNames = httpServletRequest
				.getParameterNames();
		// 获取所有的参数名
		while (paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();
			// 得到参数名
			String value = httpServletRequest.getParameter(name);
			// 通过参数名获取对应的值
			logger.debug("支付宝回调信息获取的参数值: "
					+ MessageFormat.format("{0}={1}", name, value));
			map.put(name, value);
			if ("m".equals(name)) {

			} else {
				alipayMap.put(name, value);
			}
		}
		// 获得通知签名
		String sign = alipayMap.get("sign");
		
		//过滤空值、sign与sign_type参数
    	Map<String, String> sParaNew = AlipayCore.paraFilter(alipayMap);
        //获取待签名字符串
        String preSignStr = AlipayCore.createLinkString(sParaNew);
		 
		boolean verified =getSignVeryfy(alipayMap,sign);
		// 验证签名通过
		if (verified) {
			// 根据交易状态处理业务逻辑
			// 当交易状态成功，处理业务逻辑成功。回写success
		    logger.info("订单为"+alipayMap.get("out_trade_no")+"接收支付宝系统通知验证签名成功！");
			return processNotify(alipayMap);
		} else {
			logger.error("接收支付宝系统通知验证签名失败，请检查！,preSignStr is :"+preSignStr+", sign="+sign);
			
			return fail("fail");
		}
	}
	
	private Result processNotify(Map<String, String> params){
		DAL dal=DALFactory.createDAL();
		try{
			SdkOrderAlipayMapper sdkOrderAlipayMapper=dal.getMapper(SdkOrderAlipayMapper.class);
			SdkOrderMapper sdkOrderMapper=dal.getMapper(SdkOrderMapper.class);
			
			SdkOrder order=sdkOrderMapper.selectByPrimaryKey(params.get("out_trade_no"));
			if(order!=null && order.getStatus()!=1){	
				SdkOrderAlipay alipay=sdkOrderAlipayMapper.selectByPrimaryKey(order.getPayId());
				if(alipay!=null){					
					setupOrderStatus(params,order,alipay);
					sdkOrderAlipayMapper.updateByPrimaryKey(alipay);
					sdkOrderMapper.updateByPrimaryKey(order);
					dal.commit();
					
					NotifyOrder.doNotify(order);
					
					logger.info("Paid status: "+order.getStatus()+"("+alipay.getNotifyTradeStatus()+"), payid: "+order.getPayId());
					
 					return success();
				}else{
					return fail("fail: OrderPay not exists: "+order.getPayId());
				}
			}else if(order==null){
				return fail("fail: OrderNo not exists: "+params.get("out_trade_no"));
			}else{
				logger.info("Order success notify already, orderno:  "+order.getOrderNo()+"("+order.getStatus()+" "+order.getStatusDetail()+"), payid: "+order.getPayId());
				return success();
			}
		
		}catch(Exception e){
			logger.error(e);
			
			return fail("Exception: "+e.getMessage()+", class: ".getClass());
		}finally{
			dal.close();
		}		 
	}
	
	private void setupOrderStatus(Map<String, String> params, SdkOrder order, SdkOrderAlipay alipay) {
		String notify_time_string=params.get("notify_time");
		Date notify_time=StrToDate(notify_time_string);
		alipay.setNotifyTime(notify_time);
		String total_fee=params.get("total_fee");
		float total_fee_float=Float.valueOf(total_fee);
		alipay.setNotifyTotalFee(total_fee_float);
		alipay.setNotifyTradeNo(params.get("trade_no"));
		alipay.setNotifyTradeStatus(params.get("trade_status"));
		alipay.setNotifyType(params.get("notify_type"));
		alipay.setNotifyId(params.get("notify_id"));
		alipay.setSignType(params.get("sign_type"));
		alipay.setBuyerId(params.get("buyer_id"));
		alipay.setBuyerEmail(params.get("buyer_emai"));
		String gmt_create_string=params.get("gmt_create");
		Date gmt_create_time=StrToDate(gmt_create_string);
		if(gmt_create_time!=null){
			alipay.setGmtCreate(gmt_create_time);
		}else{
			alipay.setGmtCreate(order.getCreateTime());
		}
		String gmt_payment_string=params.get("gmt_payment");
		Date gmt_payment_time=StrToDate(gmt_payment_string);
		if(gmt_payment_time!=null){
			alipay.setGmtPayment(gmt_payment_time);
		}else{
			alipay.setGmtPayment(order.getCreateTime());
		}
		alipay.setIsTotalFeeAdjust(params.get("is_total_fee_adjust"));
		alipay.setUseCoupon(params.get("use_coupon"));
		alipay.setDiscount(params.get("discount"));
		alipay.setRefundStatus(params.get("refund_status"));
		String gmt_refund=params.get("gmt_refund");
		Date gmt_refund_time=StrToDate(gmt_refund);
		if(gmt_refund_time!=null){
			alipay.setGmtRefund(gmt_refund_time);
		}
		
		if(alipay.getNotifyTradeStatus().equalsIgnoreCase("TRADE_FINISHED")||alipay.getNotifyTradeStatus().equalsIgnoreCase("TRADE_SUCCESS")){
			order.setCompleteTime(alipay.getNotifyTime());
			//订单成功
			order.setStatus(1);
		}else if(alipay.getNotifyTradeStatus().equalsIgnoreCase("WAIT_BUYER_PAY")==false){
			order.setCompleteTime(alipay.getNotifyTime());
			//订单失败
			order.setStatus(3);
		}
		
		order.setStatusDetail("ALI."+alipay.getNotifyTradeStatus());
	}
		
	private Result success(){
		return new Result(new ResultXJContent("success","success"));
	}
	
	private Result fail(String msg){
		//log
		return new Result(new ResultXJContent(msg,msg));
	}

	/**
     * 根据反馈回来的信息，生成签名结果
     * @param Params 通知返回来的参数数组
     * @param sign 比对的签名结果
     * @return 生成的签名结果
     */
	private static boolean getSignVeryfy(Map<String, String> Params, String sign) {
    	//过滤空值、sign与sign_type参数
    	Map<String, String> sParaNew = AlipayCore.paraFilter(Params);
        //获取待签名字符串
        String preSignStr = AlipayCore.createLinkString(sParaNew);
        //获得签名验证结果
        boolean isSign = false;
        isSign = RSA.verify(preSignStr, sign, PartnerConfig.RSA_ALIPAY_PUBLIC, "utf-8");
        return isSign;
    }

	/**
	 * 测试alipay回调信息
	 * @return
	 */
	private String post_alipayNotify_message(Map<String,Object> map){
		String result="";
		HttpClient httpclient = new DefaultHttpClient();
		try {
			String sendUrl = "http://leyo.magicbirds.cn/api/m/AlipayNotify";
			logger.info("the AlipayNotify api =" + sendUrl);
			HttpPost httpost = new HttpPost(sendUrl);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Map.Entry<String, Object> me : map.entrySet()) {
				String mapKey=me.getKey();
				switch(mapKey){
				case "discount":{
						nvps.add(new BasicNameValuePair("discount", (String) map
						.get("discount")));
						break;
						}
				case "payment_type":{
					nvps.add(new BasicNameValuePair("payment_type", (String) map
					.get("payment_type")));
					break;
					}
				case "subject":{
					nvps.add(new BasicNameValuePair("subject", (String) map
					.get("subject")));
					break;
					}
				case "trade_no":{
					nvps.add(new BasicNameValuePair("trade_no", (String) map
					.get("trade_no")));
					break;
					}
				case "buyer_email":{
					nvps.add(new BasicNameValuePair("buyer_email", (String) map
					.get("buyer_email")));
					break;
					}
				case "gmt_create":{
					nvps.add(new BasicNameValuePair("gmt_create", (String) map
					.get("gmt_create")));
					break;
					}
				case "notify_type":{
					nvps.add(new BasicNameValuePair("notify_type", (String) map
					.get("notify_type")));
					break;
					}
				case "quantity":{
					nvps.add(new BasicNameValuePair("quantity", (String) map
					.get("quantity")));
					break;
					}
				case "out_trade_no":{
					nvps.add(new BasicNameValuePair("out_trade_no", (String) map
					.get("out_trade_no")));
					break;
					}
				case "seller_id":{
					nvps.add(new BasicNameValuePair("seller_id", (String) map
					.get("seller_id")));
					break;
					}
				case "notify_time":{
					nvps.add(new BasicNameValuePair("notify_time", (String) map
					.get("notify_time")));
					break;
					}
				case "body":{
					nvps.add(new BasicNameValuePair("body", (String) map
					.get("body")));
					break;
					}
				case "trade_status":{
					nvps.add(new BasicNameValuePair("trade_status", (String) map
					.get("trade_status")));
					break;
					}
				case "is_total_fee_adjust":{
					nvps.add(new BasicNameValuePair("is_total_fee_adjust", (String) map
					.get("is_total_fee_adjust")));
					break;
					}
				case "total_fee":{
					nvps.add(new BasicNameValuePair("total_fee", (String) map
					.get("total_fee")));
					break;
					}
				case "seller_email":{
					nvps.add(new BasicNameValuePair("seller_email", (String) map
					.get("seller_email")));
					break;
					}
				case "price":{
					nvps.add(new BasicNameValuePair("price", (String) map
					.get("price")));
					break;
					}
				case "buyer_id":{
					nvps.add(new BasicNameValuePair("buyer_id", (String) map
					.get("buyer_id")));
					break;
					}
				case "notify_id":{
					nvps.add(new BasicNameValuePair("notify_id", (String) map
					.get("notify_id")));
					break;
					}
				case "use_coupon":{
					nvps.add(new BasicNameValuePair("use_coupon", (String) map
					.get("use_coupon")));
					break;
					}
				case "sign_type":{
					nvps.add(new BasicNameValuePair("sign_type", (String) map
					.get("sign_type")));
					break;
					}
				case "sign":{
					nvps.add(new BasicNameValuePair("sign", (String) map
					.get("sign")));
					break;
					}
				default:break; 
				}
			}
			httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			result = content;
		} catch (Exception e) {
			logger.error("the alipay request error =" + e, e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		if(str!=null){
			try {
				date = format.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
	
	public static void main(String[] args) {
		AlipayNotify x = new AlipayNotify();
		Map<String, Object> map = new HashMap<String, Object>();	
		map.put("discount", "0.00");
		map.put("payment_type","1");
		map.put("subject", "内测游戏");
		map.put("trade_no", "2016030421001004420251167110");
		map.put("buyer_email", "13751673713");
		map.put("gmt_create", "2016-03-04 18:10:43");
		map.put("notify_type", "trade_status_sync");
		map.put("quantity", "1");
		map.put("out_trade_no", "1603041810100101");
		map.put("seller_id", "2088121306650932");
		map.put("notify_time", "2016-03-05 18:34:22");
		map.put("body", "内测游戏");
		map.put("trade_status", "WAIT_BUYER_PAY");
		map.put("is_total_fee_adjust", "Y");
		map.put("total_fee", "0.01");
		map.put("seller_email", "molixiaoniaozhifu@magicbirds.cn");
		map.put("price", "0.01");
		map.put("buyer_id", "2088212185664423");
		map.put("notify_id", "6c413b8ee65778de16ae25b89bad57bj8q");
		map.put("use_coupon", "N");
		map.put("sign_type", "RSA");
		map.put("sign", "YZ3kgmVlCT5e8rgIuWwOXA6NzO0TAH43n34w9mLqw2RzML/oW3grItaTSCkcn3oh0rosqmVifQkRhQu9o+w0zWSWM1JBmY012m8kSERDXHg3ZW3SyFAh3AK2d7iEWui/9sWD7oPtsAmoEZZoqmP2Uu4lH1Etb2JnTc7wHlxUxks=");
		String result = x.post_alipayNotify_message(map);
		System.out.println(result);
	}

	
}
