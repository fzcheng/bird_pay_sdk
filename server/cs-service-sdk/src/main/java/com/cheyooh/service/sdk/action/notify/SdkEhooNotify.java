package com.cheyooh.service.sdk.action.notify;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Cmd;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultXJContent;
import com.cheyooh.service.sdk.db.dao.SdkNotifyMmdoMapper;
import com.cheyooh.service.sdk.db.entity.SdkNotifyMmdo;
import com.cheyooh.service.sdk.db.entity.SdkNotifyMmdoExample;
import com.cheyooh.service.sdk.idata.gameserver.JsonSdkEhooCBRes;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SdkEhooNotify extends AbstractNotifyService<Cmd> {
	
	private String RESPONSE_BODY = "{\"resultCode\":\"%d\"}";
	private String encryptKey="3bdbdcc8080128967311331a1d8269de";
	private int resultCode=300;
	
	private static final ObjectMapper mapper = new ObjectMapper();
	static {
		// mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		// or jackson 2.0
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		mapper.setSerializationInclusion(Include.NON_NULL);
		// jackson 1.9 and before
		// mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
		// false);
	}

	@Override
	protected Result verify(Cmd cmd) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cheyooh.service.framework.basic.Service#execute(com.cheyooh.service
	 * .framework.idata.Cmd)
	 */
	@SuppressWarnings("unused")
	@Override
	protected Result execute(Cmd cmd) {
		DAL dal = DALFactory.createDAL();
		try {
			InputStream inputStream = cmd.getServiceContext().getRequest()
					.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream, "UTF-8"));
			StringBuffer jsonBuff = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				jsonBuff.append(line);
			}
			String jsonBody = jsonBuff.toString();
			logger.info("the SdkEhooNotify json is :" + jsonBody);
			
			JsonSdkEhooCBRes jsonSdkEhooCBRes = mapper.readValue(jsonBody, JsonSdkEhooCBRes.class);
			logger.debug("the SdkEhooNotify jsonSdkEhooCBRes is :"+jsonSdkEhooCBRes.toString());
			
			Map<String, String> map=new HashMap<String, String>();
			String amt="";
			if(StringUtils.isNotBlank(jsonSdkEhooCBRes.getAmt())){
				map.put("amt", jsonSdkEhooCBRes.getAmt());
				amt=jsonSdkEhooCBRes.getAmt();
			}
			
			String appid="";
			if(StringUtils.isNotBlank(jsonSdkEhooCBRes.getAppid())){
				map.put("appid", jsonSdkEhooCBRes.getAppid());
				appid=jsonSdkEhooCBRes.getAppid();
			}
			
			String appname="";
			if(StringUtils.isNotBlank(jsonSdkEhooCBRes.getAppname())){
				map.put("appname", jsonSdkEhooCBRes.getAppname());
				appname=jsonSdkEhooCBRes.getAppname();
			}
			
			String merid="";
			if(StringUtils.isNotBlank(jsonSdkEhooCBRes.getMerid())){
				map.put("merid", jsonSdkEhooCBRes.getMerid());
				merid=jsonSdkEhooCBRes.getMerid();
			}
			
			String mername="";
			if(StringUtils.isNotBlank(jsonSdkEhooCBRes.getMername())){
				map.put("mername", jsonSdkEhooCBRes.getMername());
				mername=jsonSdkEhooCBRes.getMername();
			}
			
			String orderdate="";
			if(StringUtils.isNotBlank(jsonSdkEhooCBRes.getOrderdate())){
				map.put("orderdate", jsonSdkEhooCBRes.getOrderdate());
				orderdate=jsonSdkEhooCBRes.getOrderdate();
			}
			
			String orderid="";
			if(StringUtils.isNotBlank(jsonSdkEhooCBRes.getOrderid())){
				map.put("orderid", jsonSdkEhooCBRes.getOrderid());
				orderid=jsonSdkEhooCBRes.getOrderid();
			}
			
			String orderstatus="";
			if(StringUtils.isNotBlank(jsonSdkEhooCBRes.getOrderstatus())){
				map.put("orderstatus", jsonSdkEhooCBRes.getOrderstatus());
				orderstatus=jsonSdkEhooCBRes.getOrderstatus();
			}
			
			String paytype="";
			if(StringUtils.isNotBlank(jsonSdkEhooCBRes.getPaytype())){
				map.put("paytype", jsonSdkEhooCBRes.getPaytype());
				paytype=jsonSdkEhooCBRes.getPaytype();
			}
			
			String paytypename="";
			if(StringUtils.isNotBlank(jsonSdkEhooCBRes.getPaytypename())){
				map.put("paytypename", jsonSdkEhooCBRes.getPaytypename());
				paytypename=jsonSdkEhooCBRes.getPaytypename();
			}
			
			String chargepoint="";
			if(StringUtils.isNotBlank(jsonSdkEhooCBRes.getChargepoint())){
				map.put("chargepoint", jsonSdkEhooCBRes.getChargepoint());
				chargepoint=jsonSdkEhooCBRes.getChargepoint();
			}
			
			String chargepointname="";
			if(StringUtils.isNotBlank(jsonSdkEhooCBRes.getChargepointname())){
				map.put("chargepointname", jsonSdkEhooCBRes.getChargepointname());
				chargepointname=jsonSdkEhooCBRes.getChargepointname();
			}
			
			String operators="";
			if(StringUtils.isNotBlank(jsonSdkEhooCBRes.getOperators())){
				map.put("operators", jsonSdkEhooCBRes.getOperators());
				operators=jsonSdkEhooCBRes.getOperators();
			}
			
			String phone="";
			if(StringUtils.isNotBlank(jsonSdkEhooCBRes.getPhone())){
				map.put("phone", jsonSdkEhooCBRes.getPhone());
				phone=jsonSdkEhooCBRes.getPhone();
			}
			
			String province="";
			if(StringUtils.isNotBlank(jsonSdkEhooCBRes.getProvince())){
				map.put("province", jsonSdkEhooCBRes.getProvince());
				province=jsonSdkEhooCBRes.getProvince();
			}
			
			String reqOrderId="";
			if(StringUtils.isNotBlank(jsonSdkEhooCBRes.getReqOrderId())){
				map.put("reqOrderId", jsonSdkEhooCBRes.getReqOrderId());
				reqOrderId=jsonSdkEhooCBRes.getReqOrderId();
			}
			
			String imsi="";
			if(StringUtils.isNotBlank(jsonSdkEhooCBRes.getImsi())){
				map.put("imsi", jsonSdkEhooCBRes.getImsi());
				imsi=jsonSdkEhooCBRes.getImsi();
			}
			
			String subchannelid="";
			if(StringUtils.isNotBlank(jsonSdkEhooCBRes.getSubchannelid())){
				map.put("subchannelid", jsonSdkEhooCBRes.getSubchannelid());
				subchannelid=jsonSdkEhooCBRes.getSubchannelid();
			}
			
			String reserved1="";
			if(StringUtils.isNotBlank(jsonSdkEhooCBRes.getReserved1())){
				map.put("reserved1", jsonSdkEhooCBRes.getReserved1());
				reserved1=jsonSdkEhooCBRes.getReserved1();
			}
			
			String reserved2="";
			if(StringUtils.isNotBlank(jsonSdkEhooCBRes.getReserved2())){
				map.put("reserved2", jsonSdkEhooCBRes.getReserved2());
				reserved2=jsonSdkEhooCBRes.getReserved2();
			}
			
			String sign=jsonSdkEhooCBRes.getSign();
			
			String signature=getSignature(map);
			if(signature.equals(sign)){
				logger.debug("the SdkEhooNotify sign success");
				resultCode=200;
				
				SdkNotifyMmdoMapper sdkNotifyMmdoMapper=dal.getMapper(SdkNotifyMmdoMapper.class);
				if(StringUtils.isNotBlank(reqOrderId)&&StringUtils.isNumeric(reqOrderId)
						&&reqOrderId.startsWith("16")){
					SdkNotifyMmdoExample sdkNotifyMmdoExample=new SdkNotifyMmdoExample();
					sdkNotifyMmdoExample.createCriteria().andOrderNoEqualTo(reqOrderId);
					SdkNotifyMmdo sdkNotifyMmdo=sdkNotifyMmdoMapper.selectOne(sdkNotifyMmdoExample);
					if(sdkNotifyMmdo!=null){
						sdkNotifyMmdo.setTradeid(orderid);
						int leyoOrderStatus=0;
						if("200".equals(orderstatus)){
							leyoOrderStatus=1;
						}
						sdkNotifyMmdo.setStatus(leyoOrderStatus);
						sdkNotifyMmdo.setResultcode(orderstatus);
						String mobilephone=sdkNotifyMmdo.getMobilephone();
						int provinceNo=sdkNotifyMmdo.getProvinceNo();
						if(StringUtils.isNotBlank(phone)){
							mobilephone=phone;
						}
						if(StringUtils.isNotBlank(province)){
							provinceNo=getProvinceNo(province);
						}
						sdkNotifyMmdo.setMobilephone(mobilephone);
						sdkNotifyMmdo.setProvinceNo(provinceNo);
						sdkNotifyMmdo.setAppid(appid);
						sdkNotifyMmdo.setPaytype(paytype);
						sdkNotifyMmdoMapper.updateByPrimaryKeySelective(sdkNotifyMmdo);
						
					}else{
						resultCode=300;
					}
				}else{
					SdkNotifyMmdo sdkNotifyMmdoRecord=new SdkNotifyMmdo();
					Date time=new Date();
					sdkNotifyMmdoRecord.setCreateTime(time);
					sdkNotifyMmdoRecord.setTradeid(orderid);
					int leyoOrderStatus=0;
					if("200".equals(orderstatus)){
						leyoOrderStatus=1;
					}
					sdkNotifyMmdoRecord.setStatus(leyoOrderStatus);
					sdkNotifyMmdoRecord.setResultcode(orderstatus);
					int gameId=getGameId(appid);
					sdkNotifyMmdoRecord.setGameId(gameId);
					float amount=Float.valueOf(amt);
					sdkNotifyMmdoRecord.setAmount(amount);
					if(StringUtils.isNotBlank(operators)){
						int operationType=getOperatorType(operators);
						sdkNotifyMmdoRecord.setOperationType(operationType);
					}
					sdkNotifyMmdoRecord.setPayChannelCode("sdkehoo");
					sdkNotifyMmdoRecord.setMobilephone(phone);
					int provinceNo=0;
					if(StringUtils.isNotBlank(province)){
						provinceNo=getProvinceNo(province);
					}
					sdkNotifyMmdoRecord.setProvinceNo(provinceNo);
					sdkNotifyMmdoRecord.setAppid(appid);
					sdkNotifyMmdoRecord.setPaytype(paytype);
					sdkNotifyMmdoRecord.setChargepoint(chargepoint);
					sdkNotifyMmdoRecord.setAdditionalStatus(0);
					sdkNotifyMmdoMapper.insertSelective(sdkNotifyMmdoRecord);
				}
			}else{
				logger.debug("the SdkEhooNotify sign fail");
				resultCode=300;
			}
			dal.commit();
			return response(resultCode);
		} catch (Exception e) {
			logger.error("the SdkEhooNotify pay notify error!", e);
			return response(resultCode);
		} finally {
			dal.close();
		}
	}

	private Result response(int status) {
		String res = String.format(RESPONSE_BODY, status);
		return new Result(new ResultXJContent(res, res));
	}

	private String getSignature(Map<String, String> map) {
		String result = "";
		try {
			SortedMap<String, String> sortedMap = new TreeMap<String, String>();
			sortedMap.putAll(map);
			String s1 = "";
			int i = 0;
			int count = map.size();
			for (Map.Entry<String, String> me : sortedMap.entrySet()) {
				s1 = s1 + me.getKey() + "=" +"\""+ me.getValue()+"\"";
				i++;
				if (i < count) {
					s1 = s1 + "&";
				}
			}
			logger.debug("the SdkEhooNotify pay notify plaintext is :" + s1);
			result =DigestUtils.md5Hex(s1+encryptKey);
			result =DigestUtils.md5Hex(result+encryptKey);
			return result;
		} catch (Exception e) {
			logger.error("the SdkEhooNotify pay notify getSignature error =" + e);
			return "";
		}
	}

	/**
	 * 测试方法
	 * 
	 * @return
	 */
	private String getContent(String jsonBody) {
		HttpClient httpclient = new DefaultHttpClient();
		String content="";
		try {
			String sendUrl = "http://leyo.magicbirds.cn/api/m/SdkEhooNotify?";
			HttpPost post = new HttpPost(sendUrl);
//			JsonSdkEhooCBRes jsonSdkEhooCBRes = new JsonSdkEhooCBRes();
//			jsonSdkEhooCBRes.setAmt(map.get("amt"));
//			jsonSdkEhooCBRes.setAppid(map.get("appid"));
//			jsonSdkEhooCBRes.setAppname(map.get("appname"));
//			jsonSdkEhooCBRes.setMerid(map.get("merid"));
//			jsonSdkEhooCBRes.setMername(map.get("mername"));
//			jsonSdkEhooCBRes.setOrderdate(map.get("orderdate"));
//			jsonSdkEhooCBRes.setOrderid(map.get("orderid"));
//			jsonSdkEhooCBRes.setOrderstatus(map.get("orderstatus"));
//			jsonSdkEhooCBRes.setPaytype(map.get("paytype"));
//			jsonSdkEhooCBRes.setPaytypename(map.get("paytypename"));
//			jsonSdkEhooCBRes.setChargepoint(map.get("chargepoint"));
//			jsonSdkEhooCBRes.setChargepointname(map.get("chargepointname"));
//			jsonSdkEhooCBRes.setOperators(map.get("operators"));
//			jsonSdkEhooCBRes.setPhone(map.get("phone"));
//			jsonSdkEhooCBRes.setProvince(map.get("province"));
//			jsonSdkEhooCBRes.setReqOrderId(map.get("reqOrderId"));
//			jsonSdkEhooCBRes.setImsi(map.get("imsi"));
//			jsonSdkEhooCBRes.setSubchannelid(map.get("subchannelid"));
//			jsonSdkEhooCBRes.setReserved1(map.get("reserved1"));
//			jsonSdkEhooCBRes.setReserved2(map.get("reserved2"));
//			jsonSdkEhooCBRes.setSign(map.get("sign"));
			
//			String jsonBody = mapper.writeValueAsString(jsonSdkEhooCBRes);
			logger.debug("the SdkEhooNotify send jsonBody = " + jsonBody);
			post.setEntity(new ByteArrayEntity(jsonBody.getBytes()));
			HttpResponse response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("the SdkEhooNotify response = " + content);
		} catch (Exception e) {
			logger.error("the SdkEhooNotify request error is :" + e, e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return content;
	}

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToStr(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = format.format(date);
		return str;
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
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	private int getProvinceNo(String province){
		int provinceNo=0;
		switch (province) {
		case "云南": {
			provinceNo=53;
			break;
		}
		case "青海": {
			provinceNo=63;
			break;
		}
		case "江苏": {
			provinceNo=32;
			break;
		}
		case "安徽": {
			provinceNo=34;
			break;
		}
		case "陕西": {
			provinceNo=61;
			break;
		}
		case "北京": {
			provinceNo=11;
			break;
		}
		case "辽宁": {
			provinceNo=21;
			break;
		}
		case "河南": {
			provinceNo=41;
			break;
		}
		case "湖南": {
			provinceNo=43;
			break;
		}
		case "山东": {
			provinceNo=37;
			break;
		}
		case "浙江": {
			provinceNo=33;
			break;
		}
		case "海南": {
			provinceNo=46;
			break;
		}
		case "黑龙江": {
			provinceNo=23;
			break;
		}
		case "江西": {
			provinceNo=36;
			break;
		}
		case "广东": {
			provinceNo=44;
			break;
		}
		case "新疆": {
			provinceNo=65;
			break;
		}
		case "福建": {
			provinceNo=35;
			break;
		}
		case "上海": {
			provinceNo=31;
			break;
		}
		case "重庆": {
			provinceNo=50;
			break;
		}
		case "贵州": {
			provinceNo=52;
			break;
		}
		case "天津": {
			provinceNo=12;
			break;
		}
		case "河北": {
			provinceNo=13;
			break;
		}
		case "吉林": {
			provinceNo=22;
			break;
		}
		case "西藏": {
			provinceNo=54;
			break;
		}
		case "四川": {
			provinceNo=51;
			break;
		}
		case "内蒙古": {
			provinceNo=15;
			break;
		}
		case "甘肃": {
			provinceNo=62;
			break;
		}
		case "宁夏": {
			provinceNo=64;
			break;
		}
		case "湖北": {
			provinceNo=42;
			break;
		}
		case "山西": {
			provinceNo=14;
			break;
		}
		case "广西": {
			provinceNo=45;
			break;
		}
		default:{
			break;
		}
		}
		return provinceNo;
	}

	private int getGameId(String appid){
		int gameId=0;
		switch (appid) {
			case "1001": {
				gameId=100;
				break;
			}
			default:{
				break;
			}
		}
		return gameId;
	}
	
	private int getOperatorType(String operators){
		int result=0;
		switch (operators) {
			case "移动": {
				result=1;
				break;
			}
			case "联通": {
				result=2;
				break;
			}
			case "电信": {
				result=3;
				break;
			}
			default:{
				break;
			}
		}
		return result;
	}
	
	public static void main(String args[]) throws Exception {
		String jsonBody="{\"phone\":\"13332952961\",\"orderstatus\":\"200\",\"subchannelid\":\"\",\"orderdate\":\"2016-05-12 14:38:38\",\"appid\":\"1001\",\"chargepoint\":\"10\",\"paytypename\":\"短代\",\"mername\":\"乐付\",\"merid\":\"2065\",\"sign\":\"210825977eb5221240af08a1a4b4ab84\",\"amt\":\"1.0\",\"chargepointname\":\"测试1元\",\"appname\":\"测试应用\",\"paytype\":\"110000000000\",\"province\":\"广东\",\"operators\":\"电信\",\"imsi\":\"460030942942457\",\"orderid\":\"812638515\",\"reserved2\":\"\",\"reqOrderId\":\"1605121444090101\",\"reserved1\":\"\"}";
		SdkEhooNotify sdkEhooNotify=new SdkEhooNotify();
		String result =sdkEhooNotify.getContent(jsonBody);
		System.out.println(result);
	}
}
