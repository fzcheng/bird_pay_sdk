package com.cheyooh.service.sdk.action.notify;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.nuxeo.common.xmap.XMap;

import com.alipay.client.util.StringUtil;
import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Cmd;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultXJContent;
import com.cheyooh.service.sdk.db.dao.SdkGameWechatMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderWechatMapper;
import com.cheyooh.service.sdk.db.entity.SdkGameWechat;
import com.cheyooh.service.sdk.db.entity.SdkGameWechatExample;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderWechat;
import com.cheyooh.service.sdk.idata.gameserver.XmlWechatNotifyReq;
import com.cheyooh.service.sdk.idata.gameserver.XmlWechatNotifyResp;

public class WechatNotify extends AbstractNotifyService<Cmd> {

	@Override
	protected Result verify(Cmd cmd) {
		return null;
	}

	@Override
	protected Result execute(Cmd cmd) {
		Result result=null;
		XmlWechatNotifyResp xmlWechatNotifyResp=new XmlWechatNotifyResp();
		DAL dal = DALFactory.createDAL();
		String appkey="";
		//初始化默认失败
		String return_code="FAIL";
		String return_msg="参数格式校验错误";
		String orderNo="";
		String sign="";
		try{
			HttpServletRequest httpServletRequest = cmd.getServiceContext().getRequest();
			InputStream in = httpServletRequest.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in,
					"UTF-8"));
			StringBuffer xmlBuff = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				xmlBuff.append(line);
			}

			String xmlStr = xmlBuff.toString();
			logger.debug("the WechatNotify notify xmlStr is :" + xmlStr);
			
			XmlWechatNotifyReq xmlWechatNotifyReq = parseNotify(xmlStr);
			logger.debug("the WechatNotify notify xmlStr is :" + xmlWechatNotifyReq.toString());
			Map<String,String> map=getParam(xmlWechatNotifyReq);
			for(String key : map.keySet()){
				logger.debug("the WechatNotify participate sign param , the key ="+key+", the value ="+map.get(key));
			}
			
			SdkOrderMapper sdkOrderMapper = dal.getMapper(SdkOrderMapper.class);
			SdkOrder sdkOrder = sdkOrderMapper.selectByPrimaryKey(xmlWechatNotifyReq.getOut_trade_no());
			Integer gameId = null;
			if (sdkOrder != null) {
				gameId = sdkOrder.getGameId();
				orderNo=sdkOrder.getOrderNo();
				SdkGameWechatMapper sdkGameWechatMapper=dal.getMapper(SdkGameWechatMapper.class);
				SdkGameWechatExample sdkGameWechatExample=new SdkGameWechatExample();
				sdkGameWechatExample.createCriteria().andGameIdEqualTo(gameId);
				SdkGameWechat sdkGameWechat=sdkGameWechatMapper.selectOne(sdkGameWechatExample);
				if(sdkGameWechat!=null){
					appkey=sdkGameWechat.getKey();
				}else{
					return_code="FAIL";
					return_msg="无此订单号对应的游戏";
					logger.info("the WechatNotify appkey is null, the orderNo ="+orderNo);
				}
				SdkOrderWechatMapper sdkOrderWechatMapper=dal.getMapper(SdkOrderWechatMapper.class);
				SdkOrderWechat sdkOrderWechat=sdkOrderWechatMapper.selectByPrimaryKey(sdkOrder.getPayId());
				if(sdkOrderWechat!=null){
					sign=getSign(map,appkey);
					if(sign.equals(xmlWechatNotifyReq.getSign())){
						sdkOrderWechat.setNotifyResultCode(xmlWechatNotifyReq.getResult_code());
						if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getErr_code())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getErr_code())){
							sdkOrderWechat.setNotifyErrCode(xmlWechatNotifyReq.getErr_code());
						}
						if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getErr_code_des())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getErr_code_des())){
							sdkOrderWechat.setNotifyErrCodeDes(xmlWechatNotifyReq.getErr_code_des());
						}
						sdkOrderWechat.setOpenid(xmlWechatNotifyReq.getOpenid());
						if(xmlWechatNotifyReq.getIs_subscribe()!=null){
							sdkOrderWechat.setNotifyIsSubscribe(xmlWechatNotifyReq.getIs_subscribe());
						}
						sdkOrderWechat.setNotifyBankType(xmlWechatNotifyReq.getBank_type());
						String cashFee=xmlWechatNotifyReq.getCash_fee();
						int cashFeeInt=parseInteger(cashFee);
						sdkOrderWechat.setNotifyCashFee(cashFeeInt);
						if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getCash_fee_type())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getCash_fee_type())){
							sdkOrderWechat.setNotifyCashFeeType(xmlWechatNotifyReq.getCash_fee_type());
						}
						if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getCoupon_fee())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getCoupon_fee())){
							String couponFee=xmlWechatNotifyReq.getCoupon_fee();
							int couponFeeInt=parseInteger(couponFee);
							sdkOrderWechat.setNotifyCouponFee(couponFeeInt);
						}
						if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getCoupon_count())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getCoupon_count())){
							String couponCount=xmlWechatNotifyReq.getCoupon_count();
							int couponCountInt=parseInteger(couponCount);
							sdkOrderWechat.setNotifyCouponCount(couponCountInt);
						}
						String time_end_string=xmlWechatNotifyReq.getTime_end();
						Date time_end_date=StrToDate(time_end_string);
						sdkOrderWechat.setNotifyTimeEnd(time_end_date);
						sdkOrderWechat.setNotifyTransactionId(xmlWechatNotifyReq.getTransaction_id());
						if("SUCCESS".equals(xmlWechatNotifyReq.getResult_code())){
							sdkOrder.setStatus(1);
						}else if("FAIL".equals(xmlWechatNotifyReq.getResult_code())){
							sdkOrder.setStatus(3);
						}
						return_code="SUCCESS";
						return_msg="OK";
					}else{
						return_code="FAIL";
						return_msg="签名失败";
					}
				}else{
					return_code="FAIL";
					return_msg="无此sdkOrderWechat订单号";
				}
				sdkOrderWechatMapper.updateByPrimaryKey(sdkOrderWechat);
			} else {
				return_code="FAIL";
				return_msg="无此sdkOrder订单号";
				logger.info("the WechatNotify orderNo is null, the orderNo ="+orderNo);
			}
			sdkOrderMapper.updateByPrimaryKey(sdkOrder);
			dal.commit();
		}catch(Exception e){
			logger.error("the WechatNotify occur exception, the orderNo ="+orderNo+", the exception ="+e);
		}finally{
			dal.close();
		}
		xmlWechatNotifyResp.setReturn_code(return_code);
		xmlWechatNotifyResp.setReturn_msg(return_msg);
		result=response(xmlWechatNotifyResp.toXml());
		logger.debug("the WechatNotify result is :"
				+ xmlWechatNotifyResp.toXml());
		return result;
	}

	/**
	 * 解析xml
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	private XmlWechatNotifyReq parseNotify(String xml) throws Exception {
		XMap xmap = new XMap();
		xmap.register(XmlWechatNotifyReq.class);

		ByteArrayInputStream in = new ByteArrayInputStream(
				xml.getBytes("UTF-8"));
		XmlWechatNotifyReq notify = (XmlWechatNotifyReq) xmap.load(in);

		return notify;
	}

	private Result response(String msg) {
		return new Result(new ResultXJContent(msg, msg));
	}
	
	/**
	 * 获取xml里不为空的参数,除去sign参数
	 * @param xmlWechatNotifyReq
	 * @return
	 */
	private Map<String,String> getParam(XmlWechatNotifyReq xmlWechatNotifyReq){
		Map<String,String> result=new HashMap<String,String>();
		try{
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getReturn_code())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getReturn_code())){
				result.put("return_code", xmlWechatNotifyReq.getReturn_code());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getReturn_msg())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getReturn_msg())){
				result.put("return_msg", xmlWechatNotifyReq.getReturn_msg());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getAppid())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getAppid())){
				result.put("appid", xmlWechatNotifyReq.getAppid());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getMch_id())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getMch_id())){
				result.put("mch_id", xmlWechatNotifyReq.getMch_id());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getDevice_info())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getDevice_info())){
				result.put("device_info", xmlWechatNotifyReq.getDevice_info());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getNonce_str())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getNonce_str())){
				result.put("nonce_str", xmlWechatNotifyReq.getNonce_str());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getResult_code())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getResult_code())){
				result.put("result_code", xmlWechatNotifyReq.getResult_code());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getErr_code())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getErr_code())){
				result.put("err_code", xmlWechatNotifyReq.getErr_code());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getErr_code_des())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getErr_code_des())){
				result.put("err_code_des", xmlWechatNotifyReq.getErr_code_des());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getOpenid())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getOpenid())){
				result.put("openid", xmlWechatNotifyReq.getOpenid());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getIs_subscribe())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getIs_subscribe())){
				result.put("is_subscribe", xmlWechatNotifyReq.getIs_subscribe());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getTrade_type())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getTrade_type())){
				result.put("trade_type", xmlWechatNotifyReq.getTrade_type());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getBank_type())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getBank_type())){
				result.put("bank_type", xmlWechatNotifyReq.getBank_type());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getTotal_fee())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getTotal_fee())){
				result.put("total_fee", xmlWechatNotifyReq.getTotal_fee());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getFee_type())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getFee_type())){
				result.put("fee_type", xmlWechatNotifyReq.getFee_type());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getCash_fee())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getCash_fee())){
				result.put("cash_fee", xmlWechatNotifyReq.getCash_fee());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getCash_fee_type())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getCash_fee_type())){
				result.put("cash_fee_type", xmlWechatNotifyReq.getCash_fee_type());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getCoupon_fee())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getCoupon_fee())){
				result.put("coupon_fee", xmlWechatNotifyReq.getCoupon_fee());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getCoupon_count())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getCoupon_count())){
				result.put("coupon_count", xmlWechatNotifyReq.getCoupon_count());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getTransaction_id())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getTransaction_id())){
				result.put("transaction_id", xmlWechatNotifyReq.getTransaction_id());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getOut_trade_no())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getOut_trade_no())){
				result.put("out_trade_no", xmlWechatNotifyReq.getOut_trade_no());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getAttach())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getAttach())){
				result.put("attach", xmlWechatNotifyReq.getAttach());
			}
			if(StringUtil.isNotEmpty(xmlWechatNotifyReq.getTime_end())&&StringUtil.isNotBlank(xmlWechatNotifyReq.getTime_end())){
				result.put("time_end", xmlWechatNotifyReq.getTime_end());
			}
		}catch (Exception e) {
			logger.error("the WechatNotify getSign error ="+e);
			result=null;
		}
		return result;
	};
	
	/**
	 * 
	 * @param map里面的参数不为空
	 * @return
	 */
	private String getSign(Map<String, String> map,String appkey) {
		String result="";
		SortedMap<String,String> sortWithAscMap=new TreeMap<String, String>();
		sortWithAscMap.putAll(map);
		try{
			StringBuffer stringBuffer = new StringBuffer();
			int i=0;
			for(String key : sortWithAscMap.keySet()){
				if(i!=0){
					stringBuffer.append("&");
				}
				stringBuffer.append(key).append("=").append(sortWithAscMap.get(key));
				i++;
			}
			String stringA=stringBuffer.toString();
			String stringSignTemp=stringA+"&key="+appkey;
			result=DigestUtils.md5Hex(stringSignTemp).toUpperCase();
		}catch (Exception e) {
			logger.error("the WechatNotify getSign error ="+e);
			return null;
		}
		return result;
	}

	private Integer parseInteger(String str) {
		Integer num = null;
		try {
			if (StringUtils.isNotBlank(str)) {
				num = Integer.valueOf(str);
			}
		} catch (NumberFormatException e) {
			logger.warn("parse number string error! str = " + str);
		}
		return num;
	}
	
	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = null;
		if (str != null) {
			try {
				date = format.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	
	/**
	 * 测试微信wechat回调信息
	 * 
	 * @return
	 */
	private XmlWechatNotifyResp post_wechatNotify_message(Map<String, String> map) {
		HttpClient httpclient = new DefaultHttpClient();
		XmlWechatNotifyResp xmlWechatNotifyResp=new XmlWechatNotifyResp();
		try {
			String sendUrl = "http://leyo.magicbirds.cn/notify/m/WechatNotify";
			logger.debug("the WechatNotify send api = " + sendUrl);
			HttpPost post = new HttpPost(sendUrl);
			XmlWechatNotifyReq xmlWechatNotifyReq=new XmlWechatNotifyReq();
			xmlWechatNotifyReq.setReturn_code(map.get("return_code"));
			xmlWechatNotifyReq.setReturn_msg(map.get("return_msg"));
			xmlWechatNotifyReq.setAppid(map.get("appid"));
			xmlWechatNotifyReq.setMch_id(map.get("mch_id"));
			xmlWechatNotifyReq.setDevice_info(map.get("device_info"));
			xmlWechatNotifyReq.setNonce_str(map.get("nonce_str"));
			xmlWechatNotifyReq.setSign(map.get("sign"));
			xmlWechatNotifyReq.setResult_code(map.get("result_code"));
			xmlWechatNotifyReq.setErr_code(map.get("err_code"));
			xmlWechatNotifyReq.setErr_code_des(map.get("err_code_des"));
			xmlWechatNotifyReq.setOpenid(map.get("openid"));
			xmlWechatNotifyReq.setIs_subscribe(map.get("is_subscribe"));
			xmlWechatNotifyReq.setTrade_type(map.get("trade_type"));
			xmlWechatNotifyReq.setBank_type(map.get("bank_type"));
			xmlWechatNotifyReq.setTotal_fee(map.get("total_fee"));
			xmlWechatNotifyReq.setFee_type(map.get("fee_type"));
			xmlWechatNotifyReq.setCash_fee(map.get("cash_fee"));
			xmlWechatNotifyReq.setCash_fee_type(map.get("cash_fee_type"));
			xmlWechatNotifyReq.setCoupon_count(map.get("coupon_count"));
			xmlWechatNotifyReq.setCoupon_fee(map.get("coupon_fee"));
			xmlWechatNotifyReq.setTransaction_id(map.get("transaction_id"));
			xmlWechatNotifyReq.setOut_trade_no(map.get("out_trade_no"));
			xmlWechatNotifyReq.setAttach(map.get("attach"));
			xmlWechatNotifyReq.setTime_end(map.get("time_end"));
			String xmlBody =xmlWechatNotifyReq.toXml();
			logger.debug("the WechatNotify send xmlBody = " + xmlBody);
			post.setEntity(new ByteArrayEntity(xmlBody.getBytes()));
			HttpResponse response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
//			logger.debug("the pay_wechat response content ="+content);
			XMap xmap = new XMap();
			xmap.register(XmlWechatNotifyResp.class);
			ByteArrayInputStream in = new ByteArrayInputStream(
					content.getBytes("utf-8"));
			xmlWechatNotifyResp = (XmlWechatNotifyResp) xmap.load(in);
			logger.debug("the WechatNotify XmlWechatNotifyResp ="
					+ xmlWechatNotifyResp.toString());
		} catch (Exception e) {
			logger.error("the WechatNotify request error =" + e, e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return xmlWechatNotifyResp;
	}
	
	public static void main(String[] args) {
		WechatNotify x = new WechatNotify();
		Map<String, String> map = new HashMap<String, String>();
		/*XmlWechatNotifyReq [return_code=SUCCESS, return_msg=, appid=wxd9c0c13bacc6d9b0, mch_id=1301475301
		 * , device_info=, nonce_str=zrn6ai6tmcv2twae, sign=BC4727E738A7FAAF5AFB504645E49CFF, result_code=SUCCESS
		 * , err_code=, err_code_des=, openid=o1lAFwnmaMtqlYXOuaqeZQH5iCE4, is_subscribe=N, trade_type=APP
		 * , bank_type=CFT, total_fee=1, fee_type=CNY, cash_fee=1, cash_fee_type=, coupon_fee=, coupon_count=
		 * , transaction_id=1010120657201603123928690597, out_trade_no=1603121425110101, attach=
		 * , time_end=20160312142602]*/
		map.put("return_code", "SUCCESS");
		map.put("return_msg", "");
		map.put("appid", "wxd9c0c13bacc6d9b0");
		map.put("mch_id", "1301475301");
		map.put("device_info", "");
		map.put("nonce_str", "zrn6ai6tmcv2twae");
		map.put("sign", "BC4727E738A7FAAF5AFB504645E49CFF");
		map.put("result_code", "SUCCESS");
		map.put("err_code", "");
		map.put("err_code_des", "");
		map.put("openid", "o1lAFwnmaMtqlYXOuaqeZQH5iCE4");
		map.put("is_subscribe", "N");
		map.put("trade_type", "APP");
		map.put("bank_type", "CFT");
		map.put("total_fee", "1");
		map.put("fee_type", "CNY");
		map.put("cash_fee", "1");
		map.put("cash_fee_type", "");
		map.put("coupon_fee", "");
		map.put("coupon_count", "");
		map.put("transaction_id", "1010120657201603123928690597");
		map.put("out_trade_no","1603121425110101");
		map.put("attach", "");
		map.put("time_end", "20160312142602");
		XmlWechatNotifyResp xmlWechatNotifyResp = x.post_wechatNotify_message(map);
		System.out.println(xmlWechatNotifyResp.toXml());
	}

}
