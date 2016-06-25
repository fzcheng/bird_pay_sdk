package com.cheyooh.service.sdk.action.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkGamePaysettingMapper;
import com.cheyooh.service.sdk.db.dao.SdkJifenSettingMapper;
import com.cheyooh.service.sdk.db.dao.SdkOperatorPayTypeMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMmdoMapper;
import com.cheyooh.service.sdk.db.entity.SdkGamePaysetting;
import com.cheyooh.service.sdk.db.entity.SdkGamePaysettingExample;
import com.cheyooh.service.sdk.db.entity.SdkJifenSetting;
import com.cheyooh.service.sdk.db.entity.SdkJifenSettingExample;
import com.cheyooh.service.sdk.db.entity.SdkOperatorPayType;
import com.cheyooh.service.sdk.db.entity.SdkOperatorPayTypeExample;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderMmdo;
import com.cheyooh.service.sdk.idata.CmdPay;
import com.cheyooh.service.sdk.idata.ResultPayMmdoBlock;
import com.cheyooh.service.sdk.idata.ResultPayMmdoCommand;
import com.cheyooh.service.sdk.idata.ResultPayMmdoCommandList;
import com.cheyooh.service.sdk.idata.ResultPayMmdoInfo;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Zwjfpay extends AbstractSdkClientService<CmdPay> {
	private int paymentType=11;
	private int operatorType=0;	
	private String imsiParam="";
	private String realImsiParam="";
	private String imeiParam = "";
	private String sdkverParam = "";
	private String ipParam="";
	private float amountParam=0;
	private float realamountParam=0;
	private Result finalresult = null;
	private String outtradeid = "";
	private String sendNumber="";
	private String sendContent="";
	private String shieldNumber="";
	private String shieldKeyword="";
	private float jifen=0;
	private int interval=0;
	private Date reqOrderNoTime = new Date();
	private String macParam="";
	private String payChannelCode="cmcczwjfjk";
	
	
	
	private String splitString = Cfg.cfg.getString("mmdo_imsi_spilt", "\\|");
	
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
	protected Result verify(CmdPay cmd) {
		DAL dal = DALFactory.createDAL();
		try {
			// 判断imsi
			imsiParam = cmd.getServiceContext().getRequest()
					.getParameter("imsi");
			if (imsiParam == null) {
				return StatusCode.ERR_PARAMETER().setMessage(
						"不存在imsi" + imsiParam);
			}
			
			operatorType = getOperatorByIMSI(imsiParam);
			
			if(operatorType!=1){
				return StatusCode.ERR_PARAMETER().setMessage(
						"非移动用户，不可使用此方式支付" + imsiParam);
			}

			// 匹配相关计费
			SdkOperatorPayTypeMapper sdkOperatorPayTypeMapper = dal.getMapper(SdkOperatorPayTypeMapper.class);
			SdkOperatorPayTypeExample sdkOperatorPayTypeExample = new SdkOperatorPayTypeExample();
			sdkOperatorPayTypeExample.createCriteria().andTypeEqualTo(paymentType).andVerLessThanOrEqualTo(cmd.getSdkver());
			List<SdkOperatorPayType> sdkOperatorPayTypeLists = sdkOperatorPayTypeMapper.selectByExample(sdkOperatorPayTypeExample);
			if(sdkOperatorPayTypeLists!=null && sdkOperatorPayTypeLists.size()>0){
				for (SdkOperatorPayType sdkOperatorPayType : sdkOperatorPayTypeLists) {
					if(sdkOperatorPayType.getMinPrice()<=cmd.getAmount() && sdkOperatorPayType.getMaxPrice()>=cmd.getAmount()){
						return super.verify(cmd);
					}
				}
			}
			// 匹配不到相关计费
			return StatusCode.ERR_PARAMETER().setMessage("不支持计费" + cmd.getAmount());
		} finally {
			dal.close();
		}
	}

	@Override
	protected Result execute(CmdPay cmd) {
		DAL dal = DALFactory.createDAL();
		try {
			// 判断imsi
			imsiParam = cmd.getServiceContext().getRequest().getParameter("imsi");
			String[] imsiArray = imsiParam.split(splitString);
			realImsiParam=imsiArray[0];
			operatorType = getOperatorByIMSI(imsiParam);
			imeiParam = cmd.getServiceContext().getRequest().getParameter("imei");			
			sdkverParam	=cmd.getServiceContext().getRequest().getParameter("sdkver");
			ipParam = cmd.getRequestHost();
			amountParam=cmd.getAmount();
			macParam=cmd.getServiceContext().getRequest().getParameter("mac");
			String[] shieldKeywordArraySub = null;
			String[] shieldNumberArraySub  = null;
			
			SdkGamePaysettingMapper sdkGamePaysettingMapper=dal.getMapper(SdkGamePaysettingMapper.class);
			SdkGamePaysettingExample sdkGamePaysettingExample=new SdkGamePaysettingExample();
			sdkGamePaysettingExample.createCriteria().andGameIdEqualTo(game.getGameId()).andUseStatusEqualTo(1);
			List<SdkGamePaysetting> SdkGamePaySettingList=sdkGamePaysettingMapper.selectByExample(sdkGamePaysettingExample);
			if(SdkGamePaySettingList!=null && SdkGamePaySettingList.size()>0){
				for(SdkGamePaysetting sdkGamePaysetting :SdkGamePaySettingList){
					SdkJifenSettingMapper sdkJifenSettingMapper=dal.getMapper(SdkJifenSettingMapper.class);
					SdkJifenSettingExample sdkJifenSettingExample=new SdkJifenSettingExample();
					sdkJifenSettingExample.createCriteria().andAmountEqualTo(amountParam).andUseStatusEqualTo(1).andIdEqualTo(sdkGamePaysetting.getPaysettingid());
					SdkJifenSetting sdkJifenSetting=sdkJifenSettingMapper.selectOne(sdkJifenSettingExample);
					if(sdkJifenSetting!=null){
						sendNumber=sdkJifenSetting.getNumber();
						sendContent=sdkJifenSetting.getContent();
						shieldKeywordArraySub = sdkJifenSetting.getShieldKeyword().split(splitString);
						shieldNumberArraySub = sdkJifenSetting.getShieldNumber().split(splitString);
//						shieldNumber=sdkJifenSetting.getShieldNumber();
//						shieldKeyword=sdkJifenSetting.getShieldKeyword();
						jifen=sdkJifenSetting.getJifen();
						realamountParam=sdkJifenSetting.getRealAmount();
						interval=sdkJifenSetting.getInterval();
					}
				}
			}
			SdkOrder order = createOrder(cmd, paymentType);	
			
			ResultPayMmdoCommand payMmdoCommand = new ResultPayMmdoCommand();
			payMmdoCommand.setContent(sendContent);
			payMmdoCommand.setNumber(sendNumber);
			String jifenString=String.valueOf(jifen);
			payMmdoCommand.setZwjf(jifenString);
			
			for (int i = 0; i < shieldKeywordArraySub.length; i++) {
				ResultPayMmdoBlock payMmdoBlock = new ResultPayMmdoBlock();
				payMmdoBlock.setKeyword(shieldKeywordArraySub[i]);
				payMmdoBlock.setNumber(shieldNumberArraySub[i]);
				payMmdoCommand.addContent(payMmdoBlock);
			}
			
			ResultPayMmdoCommandList payMmdoCommandList = new ResultPayMmdoCommandList();
			payMmdoCommandList.setImsi(imsiParam);
			payMmdoCommandList.setTime(interval);
			payMmdoCommandList.addContent(payMmdoCommand);
			
			ResultPayMmdoInfo payinfo = new ResultPayMmdoInfo();
			payinfo.setType("zwjfpay");
			payinfo.setOrder_no(order.getOrderNo());
			payinfo.addContent(payMmdoCommandList);
			finalresult=new Result(payinfo);
			
			SdkOrderMmdoMapper mmdoMapper = dal
					.getMapper(SdkOrderMmdoMapper.class);
			SdkOrderMmdo mmdo = new SdkOrderMmdo();
			mmdo.setReqImsi(imsiParam);
			mmdo.setReqOrderAmount(realamountParam);
			mmdo.setReqSendContent(sendContent);
			mmdo.setReqSendNumber(sendNumber);
			mmdo.setReqTime(reqOrderNoTime);
			mmdo.setGameId(gameSession.getGameId());
			mmdo.setOperationType(operatorType);
			mmdo.setUid(gameSession.getUid());
			mmdo.setImei(imeiParam);
			mmdo.setMacAddr(macParam);
			mmdo.setIpAddr(ipParam);
			mmdo.setRawData(imsiParam);
			mmdo.setPayChannelCode(payChannelCode);
			mmdo.setTradeid(outtradeid);
			mmdoMapper.insertSelective(mmdo);

			SdkOrderMapper mapperOrder = dal
					.getMapper(SdkOrderMapper.class);
			order.setPayId(mmdo.getPayId());
			order.setAmount(realamountParam);
			order.setSdkver(sdkverParam);
			mapperOrder.insert(order);

			dal.commit();
			logger.debug("the zwjfpay result is: "+ finalresult.getXml("zwjfpay"));
			return finalresult;

		} finally {
			dal.close();
		}
	}

	/**
	 * 计算运营商 1:中国移动 2:中国联通 3:中国电信
	 * 
	 * @param imsi
	 * @return
	 */
	private int getOperatorByIMSI(String imsi) {
		int operatorId = 0;
		if (StringUtils.isNotEmpty(imsi)) {
			if (imsi.startsWith("46000") || imsi.startsWith("46002")
					|| imsi.startsWith("46007") || imsi.startsWith("898600")) {
				// 中国移动
				operatorId = 1;
			} else if (imsi.startsWith("46001") || imsi.startsWith("46006")
					|| imsi.startsWith("46010")) {
				// 中国联通
				operatorId = 2;
			} else if (imsi.startsWith("46003") || imsi.startsWith("46005")
					|| imsi.startsWith("46011")) {
				// 中国电信
				operatorId = 3;
			}
		}
		return operatorId;
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

	private Date timeString2Date(String str) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date date = format.parse(str);
		return date;
	}

	private String date2TimeString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(date);
	}

	@Override
	protected boolean isLoginRequired() {
		// TODO 自动生成的方法存根
		return true;
	}

	public static void main(String[] args) {
//		String s="12345";
//		String[] imsiArray = s.split("\\|");
//		System.out.println(imsiArray[0]);
	}
}
