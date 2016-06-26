package com.cheyooh.service.sdk.action.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkCenternumberMapper;
import com.cheyooh.service.sdk.db.dao.SdkCityAddrMapper;
import com.cheyooh.service.sdk.db.dao.SdkGameBlacklistMapper;
import com.cheyooh.service.sdk.db.dao.SdkGameMapper;
import com.cheyooh.service.sdk.db.dao.SdkGameWhitelistMapper;
import com.cheyooh.service.sdk.db.dao.SdkImsiIccidMapper;
import com.cheyooh.service.sdk.db.dao.SdkMmdoSettingMapper;
import com.cheyooh.service.sdk.db.dao.SdkMmdoShieldMapper;
import com.cheyooh.service.sdk.db.dao.SdkNotifyMmdoMapper;
import com.cheyooh.service.sdk.db.dao.SdkOperatorPayChannelMapper;
import com.cheyooh.service.sdk.db.dao.SdkOperatorPayLimitMapper;
import com.cheyooh.service.sdk.db.dao.SdkOperatorPayTypeMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMmdoMapper;
import com.cheyooh.service.sdk.db.dao.SdkTelephoneCenternumberMapper;
import com.cheyooh.service.sdk.db.dao.SdkTelephoneMapper;
import com.cheyooh.service.sdk.db.entity.SdkCenternumber;
import com.cheyooh.service.sdk.db.entity.SdkCenternumberExample;
import com.cheyooh.service.sdk.db.entity.SdkCityAddr;
import com.cheyooh.service.sdk.db.entity.SdkCityAddrExample;
import com.cheyooh.service.sdk.db.entity.SdkGame;
import com.cheyooh.service.sdk.db.entity.SdkGameBlacklist;
import com.cheyooh.service.sdk.db.entity.SdkGameBlacklistExample;
import com.cheyooh.service.sdk.db.entity.SdkGameExample;
import com.cheyooh.service.sdk.db.entity.SdkGameWhitelist;
import com.cheyooh.service.sdk.db.entity.SdkGameWhitelistExample;
import com.cheyooh.service.sdk.db.entity.SdkImsiIccid;
import com.cheyooh.service.sdk.db.entity.SdkImsiIccidExample;
import com.cheyooh.service.sdk.db.entity.SdkMmdoSetting;
import com.cheyooh.service.sdk.db.entity.SdkMmdoSettingExample;
import com.cheyooh.service.sdk.db.entity.SdkMmdoShield;
import com.cheyooh.service.sdk.db.entity.SdkNotifyMmdo;
import com.cheyooh.service.sdk.db.entity.SdkOperatorPayChannel;
import com.cheyooh.service.sdk.db.entity.SdkOperatorPayChannelExample;
import com.cheyooh.service.sdk.db.entity.SdkOperatorPayLimit;
import com.cheyooh.service.sdk.db.entity.SdkOperatorPayLimitExample;
import com.cheyooh.service.sdk.db.entity.SdkOperatorPayType;
import com.cheyooh.service.sdk.db.entity.SdkOperatorPayTypeExample;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderMmdo;
import com.cheyooh.service.sdk.db.entity.SdkOrderMmdoExample;
import com.cheyooh.service.sdk.db.entity.SdkTelephone;
import com.cheyooh.service.sdk.db.entity.SdkTelephoneCenternumber;
import com.cheyooh.service.sdk.db.entity.SdkTelephoneCenternumberExample;
import com.cheyooh.service.sdk.db.entity.SdkTelephoneExample;
import com.cheyooh.service.sdk.idata.CmdPay;
import com.cheyooh.service.sdk.idata.ResultPayMmdoBlock;
import com.cheyooh.service.sdk.idata.ResultPayMmdoChargeTip;
import com.cheyooh.service.sdk.idata.ResultPayMmdoCommand;
import com.cheyooh.service.sdk.idata.ResultPayMmdoCommandList;
import com.cheyooh.service.sdk.idata.ResultPayMmdoInfo;
import com.cheyooh.service.sdk.idata.ResultPayMmdoInfoOrderList;
//import com.cheyooh.service.sdk.idata.ResultSdkEhooParam;
import com.cheyooh.service.sdk.idata.gameserver.BillingPeriodVo;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Pay_mmdo extends AbstractSdkClientService<CmdPay> {
	private String imsi_Param = "";
	private String realimsi_Param = "";
	private String imei_Param = "";
	private String iccid_Param = "";
	private String dbiccid_Param = "";
	private String ip_Param = "";
	private String mac_Param="";
	private String sdkver_Param = "";

	private boolean ifwhitelist = false;
	private int operatorsId = 0;
	private int mobilephoneProvinceno = 0; // 手机号所在省份
	private String mobilephoneNumber = "";// 手机号码
	private float realamount = 0;
	private Result finalresult = null;
	private String supplementTimeInterval = "";
	private Boolean ifSameChannel = true;
	private String outtradeid = "";
	private Date reqOrderNoTime = new Date();
	private int dmLimitResult = 0;
	private String mobilephoneCenterNumber = "";// 短信中心号
	private boolean orderIfOver30m = false;

	private String splitString = Cfg.cfg.getString("mmdo_imsi_spilt", "\\|");
	private static final Integer mmdoType = 9;
	private static final String splitContentString = Cfg.cfg.getString(
			"mmdo_content_spilt", "#");
	private static final String spiltSubContentString = Cfg.cfg.getString(
			"mmdo_sub_content_spilt", "\\|");
	private static final String Time_Interval = Cfg.cfg
			.getString("sdk.game.request.timeinterval");
	
	//private static final String  sdkehoo= "sdkehoo";

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
			HttpServletRequest httpServletRequest = cmd.getServiceContext().getRequest();
			imsi_Param = httpServletRequest.getParameter("imsi");
			if (imsi_Param == null) {
				return StatusCode.ERR_PARAMETER().setMessage(
						"不存在imsi" + imsi_Param);
			}

			realimsi_Param = imsi_Param;
			if (imsi_Param.contains("|")) {
				String[] mobilephoneimsis = imsi_Param.split("\\|");
				realimsi_Param = mobilephoneimsis[0];
			}

			// 黑名单判断
			SdkGameBlacklistMapper sdkGameBlacklistMapper = dal
					.getMapper(SdkGameBlacklistMapper.class);
			SdkGameBlacklistExample sdkGameBlacklistExample = new SdkGameBlacklistExample();
			sdkGameBlacklistExample.createCriteria().andImsiEqualTo(
					realimsi_Param);
			List<SdkGameBlacklist> sdkGameBlacklists = sdkGameBlacklistMapper
					.selectByExample(sdkGameBlacklistExample);
			if (sdkGameBlacklists != null && sdkGameBlacklists.size() > 0) {
				for (SdkGameBlacklist sdkGameBlacklist : sdkGameBlacklists) {
					if (realimsi_Param.equals(sdkGameBlacklist.getImsi())) {
						return StatusCode.ERR_PARAMETER().setMessage("黑名单信息");
					}
				}
			}

			// 白名单判断
			SdkGameWhitelistMapper sdkGameWhitelistMapper = dal
					.getMapper(SdkGameWhitelistMapper.class);
			SdkGameWhitelistExample sdkGameWhitelistExample = new SdkGameWhitelistExample();
			sdkGameWhitelistExample.createCriteria().andImsiEqualTo(
					realimsi_Param);
			List<SdkGameWhitelist> sdkGameWhitelists = sdkGameWhitelistMapper
					.selectByExample(sdkGameWhitelistExample);
			if (sdkGameWhitelists != null && sdkGameWhitelists.size() > 0) {
				ifwhitelist = true;
			}

			// 匹配相关计费
			SdkOperatorPayTypeMapper operatorPayTypeMapper = dal
					.getMapper(SdkOperatorPayTypeMapper.class);
			SdkOperatorPayTypeExample operatorPayTypeExample = new SdkOperatorPayTypeExample();
			operatorPayTypeExample.createCriteria().andTypeEqualTo(mmdoType);
			List<SdkOperatorPayType> operatorPayTypeList = operatorPayTypeMapper
					.selectByExample(operatorPayTypeExample);
			String[] imsiArray = imsi_Param.split(splitString);
			for (String imsi : imsiArray) {
				for (SdkOperatorPayType sdkOperatorPayType : operatorPayTypeList) {
					if (cmd.getSdkver().compareToIgnoreCase(
							sdkOperatorPayType.getVer()) < 0) {
						continue;
					}
					int operatorsId = getOperatorByIMSI(imsi);
					if (operatorsId == sdkOperatorPayType.getOperator()
							&& cmd.getAmount() <= sdkOperatorPayType
									.getMaxPrice()
							&& cmd.getAmount() >= sdkOperatorPayType
									.getMinPrice()) {
						// 是否超
						realimsi_Param = imsi;
						return super.verify(cmd);
					}
				}
			}
			// 匹配不到相关计费
			return StatusCode.ERR_PARAMETER().setMessage(
					"不支持计费" + cmd.getAmount());
		} finally {
			dal.close();
		}
	}

	@SuppressWarnings("unused")
	@Override
	protected Result execute(CmdPay cmd) {
		DAL dal = DALFactory.createDAL();
		try {
			HttpServletRequest httpServletRequest = cmd.getServiceContext().getRequest();
			long sy = System.currentTimeMillis();
			long sy1 = System.currentTimeMillis();
			logger.info("1---" + sy + "time is :" + (sy1 - sy));

			operatorsId = getOperatorByIMSI(realimsi_Param);
			imei_Param = httpServletRequest.getParameter("imei");
			ip_Param = cmd.getRequestHost();
			mac_Param=httpServletRequest.getParameter("mac");
			iccid_Param = httpServletRequest.getParameter("iccid");
			if (!StringUtils.isNotBlank(iccid_Param)) {
				iccid_Param = "";
			}
			dbiccid_Param = httpServletRequest.getParameter("dbiccid");
			sdkver_Param = cmd.getSdkver();
			if (!StringUtils.isNotBlank(sdkver_Param)) {
				sdkver_Param = "";
			}

			// 如果表sdk_telephone有记录手机号码和imsi关系，则获取手机号码和所在的省份id
			SdkTelephoneExample phoneExample = new SdkTelephoneExample();
			phoneExample.createCriteria().andImsiEqualTo(realimsi_Param);
			phoneExample.setOrderByClause("created_time desc");
			SdkTelephoneMapper phoneMapper = dal
					.getMapper(SdkTelephoneMapper.class);
			SdkTelephone mobilephone = phoneMapper.selectOne(phoneExample);
			if (mobilephone != null) {
				mobilephoneProvinceno = mobilephone.getProvinceNo();
				mobilephoneNumber = mobilephone.getMobilephone();
			}

			if (StringUtils.isNotEmpty(dbiccid_Param)
					&& StringUtils.isNotBlank(dbiccid_Param)
					&& StringUtils.isNotBlank(realimsi_Param)) {
				// 新开线程，把iccid写入数据库
				InsertIccidToDBThread insertIccidToDBThread = new InsertIccidToDBThread(
						dbiccid_Param, realimsi_Param);
				insertIccidToDBThread.start();
			}

			if (StringUtils.isEmpty(iccid_Param)
					|| StringUtils.isBlank(iccid_Param)) {
				SdkImsiIccidMapper sdkImsiIccidMapper = dal
						.getMapper(SdkImsiIccidMapper.class);
				SdkImsiIccidExample sdkImsiIccidExample = new SdkImsiIccidExample();
				sdkImsiIccidExample.createCriteria().andImsiEqualTo(
						realimsi_Param);
				SdkImsiIccid sdkImsiIccid = sdkImsiIccidMapper
						.selectOne(sdkImsiIccidExample);
				if (sdkImsiIccid != null) {
					if (StringUtils.isNotEmpty(sdkImsiIccid.getIccid())
							&& StringUtils.isNotBlank(sdkImsiIccid.getIccid())) {
						iccid_Param = sdkImsiIccid.getIccid();
					}
				}
			}

			if (StringUtils.isEmpty(mobilephoneCenterNumber)
					|| StringUtils.isBlank(mobilephoneCenterNumber)) {
				SdkTelephoneCenternumberExample stcExample = new SdkTelephoneCenternumberExample();
				stcExample.createCriteria().andImsiEqualTo(realimsi_Param);
				SdkTelephoneCenternumberMapper stcMapper = dal
						.getMapper(SdkTelephoneCenternumberMapper.class);
				SdkTelephoneCenternumber stc = stcMapper.selectOne(stcExample);
				if (stc != null) {
					if (StringUtils.isNotEmpty(stc.getCenternumber())
							&& StringUtils.isNotBlank(stc.getCenternumber())) {
						mobilephoneCenterNumber = stc.getCenternumber();
					}
				}
			}

			// 若手机号码库找不到，则通过iccid查找省份
			if (mobilephoneProvinceno == 0
					&& StringUtils.isNotEmpty(iccid_Param)
					&& StringUtils.isNotBlank(iccid_Param)
					&& StringUtils.isNotBlank(realimsi_Param)
					&& iccid_Param.length() > 15) {
				mobilephoneProvinceno = getProvinceNoByICCID(dal,
						iccid_Param.trim(), realimsi_Param);
			}

			// 若手机号码查找省份未找到，iccid号码库也找不到，则通过短信中心号段查找省份
			if (mobilephoneProvinceno == 0
					&& StringUtils.isNotBlank(realimsi_Param)
					&& StringUtils.isNotBlank(mobilephoneCenterNumber)) {

				SdkCenternumberExample scExample = new SdkCenternumberExample();
				scExample.createCriteria().andCenternumberEqualTo(
						mobilephoneCenterNumber);
				SdkCenternumberMapper scMapper = dal
						.getMapper(SdkCenternumberMapper.class);
				SdkCenternumber sc = scMapper.selectOne(scExample);
				if (sc != null) {
					mobilephoneProvinceno = sc.getProvinceNo();
				}
			}
			// 省份过滤完毕

			// 运营商、sdk支持最低版本的计费渠道
			SdkOperatorPayChannelExample payChannelExample = new SdkOperatorPayChannelExample();
			payChannelExample.createCriteria()
					.andOperatorTypeEqualTo(operatorsId)
					.andSdkMinVerLessThanOrEqualTo(cmd.getSdkver());
			SdkOperatorPayChannelMapper payChannelMapper = dal
					.getMapper(SdkOperatorPayChannelMapper.class);
			List<SdkOperatorPayChannel> payChannels = payChannelMapper
					.selectByExample(payChannelExample);

			if (payChannels == null || payChannels.size() < 1) {
				return StatusCode.ERR_NOTFOUND().setMessage("未找到运营商");
			}

			Map<Integer, SdkOperatorPayChannel> payChannelMap = new HashMap<Integer, SdkOperatorPayChannel>();
			List<Integer> payChannelIds = new ArrayList<Integer>();
			for (SdkOperatorPayChannel channel : payChannels) {
				payChannelIds.add(channel.getId());
				payChannelMap.put(channel.getId(), channel);
			}

			// 获取游戏特定计费配置,没设置，使用系统默认配置
			SdkMmdoSettingExample mmdoSettingExample = new SdkMmdoSettingExample();
			mmdoSettingExample.createCriteria()
					.andAmountEqualTo(cmd.getAmount())
					.andUseStatusEqualTo(1)
					.andOperatorPayChannelIdIn(payChannelIds)
					.andGameIdEqualTo(game.getGameId());
			mmdoSettingExample.setOrderByClause("amount desc, idx asc");
			SdkMmdoSettingMapper mmdoSettingMapper = dal
					.getMapper(SdkMmdoSettingMapper.class);
			List<SdkMmdoSetting> mmdoSettings = mmdoSettingMapper
					.selectByExample(mmdoSettingExample);

			// 不存在计费
			if (mmdoSettings == null || mmdoSettings.size() < 1) {
				return StatusCode.ERR_NOTFOUND().setMessage("未找到短计费");
			}

			boolean limit = false;
			boolean shield = false;
			boolean enablePay_proshield = false;
			boolean enablePay_dmlimit = false;
			// 此循环：如果是省份屏蔽，则进行下一个支付，直到其中一个可以计费支付为止，则跳出循环

			for (SdkMmdoSetting mmdoSetting : mmdoSettings) {
				limit = false;
				shield = false;
				SdkOperatorPayChannel sdkOperatorPayChannel = payChannelMap
						.get(mmdoSetting.getOperatorPayChannelId());

				// 判断是否省份屏蔽,如果没有屏蔽，则返回0，否则返回非0
				Integer pF = checkProvince(mobilephoneProvinceno,
						mobilephoneNumber, sdkOperatorPayChannel);
				if (pF != 0) {
					shield = true;
					continue;
				}
				enablePay_proshield = true;

				// 是否超日月限额,如果没有限额，则返回0表示未超过，否则返回非0
				dmLimitResult = isOverLimitPay(dal, operatorsId, imsi_Param,
						sdkOperatorPayChannel);
				// 是否是在白名单里，如果是，则没有日月限额限制，否则有限制
				if (ifwhitelist) {
					dmLimitResult = 0;
				}

				if (dmLimitResult == 1 || dmLimitResult == 2) {
					limit = true;
					continue;
				}
				if ((dmLimitResult == 3 || dmLimitResult == 4)
						&& !"sdkxqtpay".equals(sdkOperatorPayChannel
								.getChannelCode())) {
					limit = true;
					continue;
				}
				enablePay_dmlimit = true;

				// 为补点计费方式，循环返回添加相应短信指令
				String leyoadditional = httpServletRequest.getParameter("leyoadditional");
				// 为兼容旧版本sdk，需客户端传入参数leyoadditional，并把additionalcount作为补点计费的循环次数
				int totalcount = 1;
				// 判断实际发送金额是否为空，如为空，则用amount,不为空，则用realamount
				realamount = mmdoSetting.getAmount();
				if (mmdoSetting.getRealAmount() != null) {
					realamount = mmdoSetting.getRealAmount();
				}
				String[] additionallist = null;
				if (StringUtils.isNotEmpty(leyoadditional)
						&& StringUtils.isNotBlank(leyoadditional)
						&& mmdoSetting.getAdditional() == 1
						&& StringUtils.isNotEmpty(mmdoSetting.getAddList())
						&& StringUtils.isNotBlank(mmdoSetting.getAddList())) {
					additionallist = mmdoSetting.getAddList().split("#");
					totalcount = 1 + additionallist.length;
				}
				int optChannelId = mmdoSetting.getOperatorPayChannelId();
				ResultPayMmdoInfoOrderList resultPayMmdoInfoOrderList = new ResultPayMmdoInfoOrderList();
				resultPayMmdoInfoOrderList.setOrder_list_type("additional");

				ResultPayMmdoInfo finalpayinfo = null;

				for (int sendsmsnum = 0; sendsmsnum < totalcount; sendsmsnum++) {

					logger.debug("the sendsmsnum " + (sendsmsnum + 1)
							+ " mmdoSettingId is :" + mmdoSetting.getId());
					if (totalcount > 1 && sendsmsnum != 0) {
						// 获取补点计费的计费点，第一个循环当sendsmsnum=0时不会跳到这里
						mmdoSettingExample.clear();
						mmdoSettingExample.createCriteria().andIdEqualTo(
								parseInteger(additionallist[sendsmsnum - 1]));
						mmdoSetting = mmdoSettingMapper
								.selectOne(mmdoSettingExample);
						// 重新把补点计费的金额放在realamount
						realamount = mmdoSetting.getAmount();

						if (mmdoSetting.getOperatorPayChannelId() != optChannelId) {
							ifSameChannel = false;
							optChannelId = mmdoSetting
									.getOperatorPayChannelId();
						}

					}
					// 不存在计费
					if (mmdoSetting == null) {
						logger.error("the SdkMmdoSetting is not found");
						break;
					}

					SdkOrder order = createOrder(cmd, mmdoType);

					logger.info("the SdkMmdoSetting is : orderNo ="
							+ order.getOrderNo() + ", gameid = "
							+ mmdoSetting.getGameId() + ", number = "
							+ mmdoSetting.getNumber() + ", content = "
							+ mmdoSetting.getContent() + ", price = "
							+ realamount + ", channelID = "
							+ mmdoSetting.getOperatorPayChannelId() + ", id = "
							+ mmdoSetting.getId());

					SdkOperatorPayChannel channel = payChannelMap
							.get(mmdoSetting.getOperatorPayChannelId());

					// 判断订单是否重复,默认是30秒
					if (!judgeReqOrderNoTime(dal, operatorsId, imsi_Param,
							channel, reqOrderNoTime)) {
						int t = parseInteger(Time_Interval);
						if (channel.getReqTimeinterval() != null) {
							t = channel.getReqTimeinterval();
						}
						int t1 = Math.abs(t);
						// 在第一次成功的情况下30秒内重复点击，记录失败订单信息，sdk_order的status状态为3
						SdkOrder faileOrder = createOrder(cmd, mmdoType);
						insertFaileOrderInfo(cmd, dal, mmdoSettings.get(0),
								faileOrder, channel, 3, "30秒内请求的订单");
						return StatusCode.ERR_PARAMETER().setMessage(
								"请" + t1 + "秒后支付！");
					}
					orderIfOver30m = judgeReqOrderTimeOver30m(dal, operatorsId,
							imsi_Param, channel, reqOrderNoTime);

					ResultPayMmdoInfo payinfo = new ResultPayMmdoInfo();
					payinfo.setOrder_no(order.getOrderNo());
					payinfo.setSms_pay_type(channel.getSmsType());
					String sms_content_type = "1";
					String channelCode = channel.getChannelCode();
					if (channel.getSmsContentType() == 2) {
						// 若以二进制方式作为发送短信内容，则在渠道标志后加上"@data"
						sms_content_type = "2";
					} else {
						sms_content_type = "1";
					}
					payinfo.setSms_type(channelCode);
					payinfo.setSms_content_type(sms_content_type);
					payinfo.setType("mmdo");
					payinfo.setSms_type("sms");

					// 计费屏蔽设置
					// provinceFlag为计费屏蔽标识符，0代表没有屏蔽正常计费，1代表“未找到计费省”，2代表“计费时段屏蔽”，3代表“计费号段屏蔽”，4代表“计费省份屏蔽”
					Integer provinceFlag = checkProvince(mobilephoneProvinceno,
							mobilephoneNumber, channel);

					if (provinceFlag != 0) {
						String failedetail = "";
						if (provinceFlag == 3) {
							failedetail = "计费号段屏蔽";
						} else {
							failedetail = "省份屏蔽";
						}
						// 省份屏蔽原因，sdk_order的status状态为5，为省份屏蔽失败订单
						insertFaileOrderInfo(cmd, dal, mmdoSetting, order,
								channel, 5, failedetail);
						break;
					}
					
					// 设置是否弹出二次确认框
					String originalGameName = "";
					String chargetip = String.valueOf(mmdoSetting
							.getChargetip());
					String chargesuceesstip = String.valueOf(mmdoSetting
							.getChargesuceesstip());
					String loadingtipmin = "3";
					if (mmdoSetting.getLoadingtipmin() == null) {
						loadingtipmin = "3";
					} else {
						loadingtipmin = String.valueOf(mmdoSetting
								.getLoadingtipmin());
					}

					String chargefailtip = String.valueOf(mmdoSetting
							.getChargefailtip());
					if (StringUtils.isNotBlank(mmdoSetting
							.getChargetipPeriods())) {
						if (!judgeTime(mmdoSetting.getChargetipPeriods())) {
							chargetip = "0";
						}
					}
					if (StringUtils.isNotBlank(mmdoSetting
							.getChargesuceesstipPeriods())) {
						if (!judgeTime(mmdoSetting.getChargesuceesstipPeriods())) {
							chargesuceesstip = "0";
						}
					}
					Integer originalGameId = mmdoSetting.getOriginalGameId();
					SdkGameMapper originalGameNameMapper = dal
							.getMapper(SdkGameMapper.class);
					SdkGameExample originalGameNameExample = new SdkGameExample();
					originalGameNameExample.createCriteria().andGameIdEqualTo(
							originalGameId);
					SdkGame originalSdkGame = originalGameNameMapper
							.selectOne(originalGameNameExample);
					if (originalSdkGame != null) {
						originalGameName = originalSdkGame.getName();
					}
					if (StringUtils.isNotBlank(mmdoSetting
							.getOriginalGameName())
							&& StringUtils.isNotEmpty(mmdoSetting
									.getOriginalGameName())) {
						originalGameName = mmdoSetting.getOriginalGameName();
					}
					ResultPayMmdoChargeTip resultPayMmdoChargeTip = new ResultPayMmdoChargeTip();
					resultPayMmdoChargeTip.setGamename(originalGameName);
					resultPayMmdoChargeTip.setChargetip(chargetip);
					resultPayMmdoChargeTip
							.setChargesuceesstip(chargesuceesstip);
					resultPayMmdoChargeTip.setLoadingtipmin(loadingtipmin);
					resultPayMmdoChargeTip.setChargefailtip(chargefailtip);
					String sendingtip = "正在加载中...";
					if (StringUtils.isNotBlank(mmdoSetting.getSendingtip())
							&& StringUtils.isNotEmpty(mmdoSetting
									.getSendingtip())) {
						sendingtip = mmdoSetting.getSendingtip();
					}
					resultPayMmdoChargeTip.setSendingtip(sendingtip);
					payinfo.addContent(resultPayMmdoChargeTip);

//					if (sdkehoo.equals(channel.getChannelCode())) {
//						ResultSdkEhooParam resultSdkEhooParam = new ResultSdkEhooParam();
//						resultSdkEhooParam.setOrderNo(order.getOrderNo());
//						String chargePoint=mmdoSetting.getContent();
//						resultSdkEhooParam.setChargePoint(chargePoint);
//						payinfo.addContent(resultSdkEhooParam);
//					}
					// 保存下发信息
					String[] numberArray = mmdoSetting.getNumber().split(
							splitContentString);
					String[] contentArray = mmdoSetting.getContent().split(
							splitContentString);
					String[] shieldKeywordArray = mmdoSetting
							.getShieldKeyword().split(splitContentString);
					String[] shieldNumberArray = mmdoSetting.getShieldNumber()
							.split(splitContentString);

					ResultPayMmdoCommandList payMmdoCommandList = new ResultPayMmdoCommandList();
					payMmdoCommandList.setImsi(imsi_Param);
					payMmdoCommandList.setTime(mmdoSetting.getInterval());
					SdkMmdoShieldMapper mmdoShieldMapper = dal
							.getMapper(SdkMmdoShieldMapper.class);

					StringBuffer numberBuff = new StringBuffer();
					int operatorType = operatorsId;
					for (int i = 0; i < numberArray.length; i++) {

						String content = "";
						String number = numberArray[i];
						String wapurl = "";
						if (operatorType == 1) {
							//移动通道
							long sy31 = System.currentTimeMillis();
							logger.info("31---" + sy + "time is :"
									+ (sy31 - sy) + ",orderNo ="
									+ order.getOrderNo());

						} else if (operatorType == 2) {
							//联通通道
							long sy32 = System.currentTimeMillis();
							logger.info("32---" + sy + "time is :"
									+ (sy32 - sy) + ",orderNo ="
									+ order.getOrderNo());
							

						} else {
							// 电信通道
							long sy33 = System.currentTimeMillis();
							logger.info("33---" + sy + "time is :"
									+ (sy33 - sy) + ",orderNo ="
									+ order.getOrderNo());
						}

						ResultPayMmdoCommand payMmdoCommand = new ResultPayMmdoCommand();
						payMmdoCommand.setContent(content);
						payMmdoCommand.setNumber(number);
						payMmdoCommand.setWapurl(wapurl);

						String[] shieldKeywordArraySub = shieldKeywordArray[i]
							.split(spiltSubContentString);
						String[] shieldNumberArraySub = shieldNumberArray[i]
								.split(spiltSubContentString);

						for (int j = 0; j < shieldKeywordArraySub.length; j++) {
							ResultPayMmdoBlock payMmdoBlock = new ResultPayMmdoBlock();
							payMmdoBlock
									.setKeyword(shieldKeywordArraySub[j]);
							payMmdoBlock.setNumber(shieldNumberArraySub[j]);
							payMmdoCommand.addContent(payMmdoBlock);
							// 保存下发信息
							SdkMmdoShield mmdoShield = new SdkMmdoShield();
							mmdoShield.setCreateTime(new Date());
							mmdoShield.setImsi(imsi_Param);
							mmdoShield.setInterval(mmdoSetting
									.getInterval());
							mmdoShield.setSendContent(content);
							mmdoShield.setSendNumber(number);
							mmdoShield
									.setShieldKeyword(shieldKeywordArraySub[j]);
							mmdoShield
									.setShieldNumber(shieldNumberArraySub[j]);
							mmdoShieldMapper.insertSelective(mmdoShield);
						}

						payMmdoCommandList.addContent(payMmdoCommand);
					}

					payinfo.addContent(payMmdoCommandList);

					SdkOrderMmdoMapper mmdoMapper = dal
							.getMapper(SdkOrderMmdoMapper.class);
					SdkOrderMmdo mmdo = new SdkOrderMmdo();
					mmdo.setReqImsi(realimsi_Param);
					mmdo.setReqOrderAmount(realamount);
					mmdo.setReqSendContent(mmdoSetting.getContent());
					if (numberBuff.length() > 0) {
						mmdo.setReqSendNumber(numberBuff.substring(0,
								numberBuff.length() - 1));
					} else {
						mmdo.setReqSendNumber(mmdoSetting.getNumber());
					}
					mmdo.setReqTime(reqOrderNoTime);
					mmdo.setGameId(gameSession.getGameId());
					mmdo.setOperationType(operatorsId);
					mmdo.setUid(gameSession.getUid());
					mmdo.setImei(imei_Param);
					mmdo.setMacAddr(mac_Param);
					mmdo.setIpAddr(ip_Param);
					mmdo.setRawData(imsi_Param);
					mmdo.setPayChannelCode(channel.getChannelCode());
					if (mmdoSetting.getTjpropsname() != null) {
						mmdo.setTjpropsname(mmdoSetting.getTjpropsname());
					}
					mmdo.setTradeid(outtradeid);
					resultPayMmdoInfoOrderList.addContent(payinfo);
					if (totalcount == 1) {
						finalpayinfo = payinfo;
					} else if (totalcount > 1 && sendsmsnum != 0) {
						mmdo.setAdditionalStatus(1);
					}
					if (!orderIfOver30m) {
						// order.setStatusDetail("30秒内请求的订单");
						mmdo.setOverThirtym(1);
					} else {
						mmdo.setOverThirtym(0);
					}
					mmdo.setMobilephone(mobilephoneNumber);
					mmdo.setProvinceNo(mobilephoneProvinceno);
					mmdo.setIccid(iccid_Param);
					mmdo.setCenternumber(mobilephoneCenterNumber);
					mmdoMapper.insertSelective(mmdo);

					SdkOrderMapper mapperOrder = dal
							.getMapper(SdkOrderMapper.class);
					order.setPayId(mmdo.getPayId());
					order.setAmount(realamount);
					order.setSdkver(sdkver_Param);
					order.setOperationType(operatorsId);
					order.setMobilephone(mobilephoneNumber);
					order.setProvinceNo(mobilephoneProvinceno);
					mapperOrder.insert(order);

					// 回调表插入一条记录
					insertSdkNotifyMmdoInfo(dal, order, mmdo, channel);

					dal.commit();
				}// 如果有补点，则结束补点层

				finalresult = null;
				if (totalcount > 1) {
					// --设置补点计费的时间间隔--
					if (ifSameChannel) {
						Integer seconds = parseInteger(Time_Interval);
						int t = Math.abs(seconds);
						supplementTimeInterval = String.valueOf(t);
					} else {
						supplementTimeInterval = Cfg.cfg
								.getString("sdk.game.supplement.request.difer.timeinterval");
					}
					resultPayMmdoInfoOrderList
							.setOrderlisttime(supplementTimeInterval);
					// ----
					finalresult = new Result(resultPayMmdoInfoOrderList);
				} else {
					finalresult = new Result(finalpayinfo);
				}
				logger.debug("the Pay_mmdo result is: "
						+ finalresult.getXml("Pay_mmdo"));

				// 省份屏蔽，结束循环
				if (!shield) {
					break;
				}

				// 超日月限额，结束循环
				if (!limit) {
					break;
				}
			}
			// 省份屏蔽,一条短信计费配置都没有，则添加失败订单记录
			if (!enablePay_proshield) {
				SdkOrder faileOrder = createOrder(cmd, mmdoType);
				SdkOperatorPayChannel faileOrderSdkOperatorPayChannel = payChannelMap
						.get(mmdoSettings.get(0).getOperatorPayChannelId());
				realamount = mmdoSettings.get(0).getAmount();
				if (mmdoSettings.get(0).getRealAmount() != null) {
					realamount = mmdoSettings.get(0).getRealAmount();
				}
				// 省份屏蔽原因，sdk_order的status状态为5，为省份屏蔽失败订单
				insertFaileOrderInfo(cmd, dal, mmdoSettings.get(0), faileOrder,
						faileOrderSdkOperatorPayChannel, 5, "省份屏蔽");

				// 判断是否省份屏蔽,如果没有屏蔽，则返回0，否则返回非0
				Integer faileOrderpF = checkProvince(mobilephoneProvinceno,
						mobilephoneNumber, faileOrderSdkOperatorPayChannel);
				if (faileOrderpF == 1) {
					return StatusCode.ERR_NOTFOUND().setMessage("未找到计费省");
				} else if (faileOrderpF == 2) {
					return StatusCode.ERR_NOTFOUND().setMessage("计费时段屏蔽");
				} else if (faileOrderpF == 3) {
					return StatusCode.ERR_NOTFOUND().setMessage("计费号段屏蔽");
				} else if (faileOrderpF == 4) {
					return StatusCode.ERR_NOTFOUND().setMessage("计费省份屏蔽");
				}
			}

			// 日月超额限制,一条短信计费配置都没有，则添加失败订单记录
			if (!enablePay_dmlimit) {
				SdkOrder faileOrder = createOrder(cmd, mmdoType);
				SdkOperatorPayChannel faileOrderSdkOperatorPayChannel = payChannelMap
						.get(mmdoSettings.get(0).getOperatorPayChannelId());
				realamount = mmdoSettings.get(0).getAmount();
				if (mmdoSettings.get(0).getRealAmount() != null) {
					realamount = mmdoSettings.get(0).getRealAmount();
				}
				String failedetail = "";
				Integer status = 0;
				if (dmLimitResult == 1) {
					failedetail = "超通道日限额";
					status = 12;
				} else if (dmLimitResult == 2) {
					failedetail = "超通道月限额";
					status = 13;
				} else if (dmLimitResult == 3) {
					failedetail = "超运营商日限";
					status = 12;
				} else if (dmLimitResult == 4) {
					failedetail = "超运营商月限";
					status = 13;
				}

				// 日月超额限制
				insertFaileOrderInfo(cmd, dal, mmdoSettings.get(0), faileOrder,
						faileOrderSdkOperatorPayChannel, status, failedetail);
				return StatusCode.ERR_NOTFOUND().setMessage(failedetail);
			}

			long sy10 = System.currentTimeMillis();
			logger.info("10---" + sy + "time is :" + (sy10 - sy));

			return finalresult;

		} finally {
			dal.close();
		}
	}

	/***
	 * 
	 * 是否超过限额
	 * 
	 * @param dal
	 * @param operation
	 * @return 0、表示未超过 。1、(对通道判断)表示当天超过。 2、(对通道判断)表示当月超过
	 *         。3、(对运营商判断)表示当天超过。4、(对运营商判断)表示当月超过。
	 */
	private Integer isOverLimitPay(DAL dal, int operation, String imsi,
			SdkOperatorPayChannel channel) {

		SdkOperatorPayLimitExample limitExample = new SdkOperatorPayLimitExample();
		limitExample.createCriteria().andOperatorTypeEqualTo(operation);
		SdkOperatorPayLimitMapper limitMapper = dal
				.getMapper(SdkOperatorPayLimitMapper.class);
		SdkOperatorPayLimit sdkOperatorPayLimit = limitMapper
				.selectOne(limitExample);

		// 微信支付的paychannelcode字符串
		String payChannelCode1 = "sdkxqtpay";

		// (对运营商判断)
		float operatorDaylimit = 200;
		float operatorMonthlimit = 500;
		if (sdkOperatorPayLimit != null) {
			operatorDaylimit = sdkOperatorPayLimit.getDayLimit().floatValue();
			operatorMonthlimit = sdkOperatorPayLimit.getMonthLimit()
					.floatValue();
		}

		// (对通道判断)
		float channelDaylimit = 200;
		float channelMonthlimit = 500;
		if (channel.getDayLimit() != null && channel.getMonthLimit() != null) {
			channelDaylimit = channel.getDayLimit();
			channelMonthlimit = channel.getMonthLimit();
		}

		// 计算当天
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date startDay = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date endDay = calendar.getTime();

		// 更改为按手机号限制消费
		SdkOrderMmdoMapper orderMmdoMapper = dal
				.getMapper(SdkOrderMmdoMapper.class);
		SdkOrderMmdoExample mmdoExample = new SdkOrderMmdoExample();
		mmdoExample.createCriteria().andReqImsiEqualTo(imsi)
				.andRespStatusEqualTo(1)
				.andReqTimeGreaterThanOrEqualTo(startDay)
				.andOperationTypeEqualTo(operation)
				.andPayChannelCodeNotEqualTo(payChannelCode1);
		List<SdkOrderMmdo> channelSdkOrderMmdolist = orderMmdoMapper
				.selectByExample(mmdoExample);

		// 计算当月
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date startMonth = calendar.getTime();
		mmdoExample = new SdkOrderMmdoExample();
		Date now = new Date();
		mmdoExample.createCriteria().andReqImsiEqualTo(imsi)
				.andRespStatusEqualTo(1)
				.andReqTimeGreaterThanOrEqualTo(startMonth)
				.andOperationTypeEqualTo(operation)
				.andPayChannelCodeNotEqualTo(payChannelCode1);
		List<SdkOrderMmdo> monthSdkOrderMmdoList = orderMmdoMapper
				.selectByExample(mmdoExample);

		// (对运营商判断)计算当天总和
		float operatorDayAmountTotal = 0;
		for (SdkOrderMmdo orderMmdo : channelSdkOrderMmdolist) {
			operatorDayAmountTotal += orderMmdo.getReqOrderAmount()
					.floatValue();
			if (operatorDayAmountTotal >= operatorDaylimit) {
				logger.info("uid = " + gameSession.getUid() + ", imsi = "
						+ imsi + ", pay total " + operatorDayAmountTotal
						+ ", out of operator daily limit " + operatorDaylimit
						+ ", startDay = " + startDay + ", endDay = " + endDay);
				return 3;
			}
		}

		// (对运营商判断)计算当月总和
		float operatorMonthAmountTotal = 0;
		for (SdkOrderMmdo orderMmdo : monthSdkOrderMmdoList) {
			operatorMonthAmountTotal += orderMmdo.getReqOrderAmount();
			if (operatorMonthAmountTotal >= operatorMonthlimit) {
				logger.info("uid = " + gameSession.getUid() + ", imsi = "
						+ imsi + ", pay total " + operatorMonthAmountTotal
						+ ", out of operator month limit " + operatorMonthlimit
						+ ", startMonth = " + startMonth + ", endMonth = "
						+ now);
				return 4;
			}

		}

		// (对通道判断)计算当天总和
		float channelDayAmountTotal = 0;
		for (SdkOrderMmdo orderMmdo : channelSdkOrderMmdolist) {
			channelDayAmountTotal += orderMmdo.getReqOrderAmount().floatValue();
			if (channelDayAmountTotal >= channelDaylimit) {
				logger.info("uid = " + gameSession.getUid() + ", imsi = "
						+ imsi + ", pay total " + channelDayAmountTotal
						+ ", out of channel daily limit " + channelDaylimit
						+ ", startDay = " + startDay + ", endDay = " + endDay);
				return 1;
			}
		}

		// (对通道判断)计算当月总和
		float channelMonthAmountTotal = 0;
		for (SdkOrderMmdo orderMmdo : monthSdkOrderMmdoList) {
			channelMonthAmountTotal += orderMmdo.getReqOrderAmount();
			if (channelMonthAmountTotal >= channelMonthlimit) {
				logger.info("uid = " + gameSession.getUid() + ", imsi = "
						+ imsi + ", pay total " + channelMonthAmountTotal
						+ ", out of channel month limit " + channelMonthlimit
						+ ", startMonth = " + startMonth + ", endMonth = "
						+ now);
				return 2;
			}

		}

		return 0;
	}

	/**
	 * 如果大于预设值可以支付，则返回true，如果小于预设值不可以支付，则返回false
	 * 
	 * @param dal
	 * @param operation
	 * @param imsi
	 * @return
	 */
	private Boolean judgeReqOrderNoTime(DAL dal, int operation, String imsi,
			SdkOperatorPayChannel channel, Date d) {
		// 判断订单是否重复,默认是30秒
		Boolean flag = true;
		Integer reqorderNoseconds = parseInteger(Time_Interval);
		if (channel.getReqTimeinterval() != null) {
			reqorderNoseconds = channel.getReqTimeinterval();
			reqorderNoseconds = 0 - reqorderNoseconds;
		}
		Date pretime = addSecond(d, reqorderNoseconds);

		SdkOrderMmdoMapper orderMmdoMapper = dal
				.getMapper(SdkOrderMmdoMapper.class);
		SdkOrderMmdoExample mmdoExample = new SdkOrderMmdoExample();
		// 更改为按手机号限制消费
		logger.debug("imsi =" + imsi + ", pretime =" + pretime
				+ ", operation =" + operation + ", reqorderNoseconds ="
				+ reqorderNoseconds + ", d =" + d);
		mmdoExample.createCriteria().andReqImsiEqualTo(imsi)
				.andRespStatusEqualTo(1)
				.andReqTimeGreaterThanOrEqualTo(pretime)
				.andReqTimeLessThanOrEqualTo(reqOrderNoTime)
				.andOperationTypeEqualTo(operation);
		List<SdkOrderMmdo> orderMmdoList = orderMmdoMapper
				.selectByExample(mmdoExample);

		if (orderMmdoList != null && orderMmdoList.size() > 0) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 判断订单是否是30秒之外，如果大于预设值30秒，则返回true，如果小于预设值30秒，则返回false
	 * 
	 * @param dal
	 * @param operation
	 * @param imsi
	 * @return
	 */
	private Boolean judgeReqOrderTimeOver30m(DAL dal, int operation,
			String imsi, SdkOperatorPayChannel channel, Date d) {
		// 判断订单是否重复,默认是30秒
		Boolean flag = true;
		Integer reqorderNoseconds = parseInteger(Time_Interval);
		if (channel.getReqTimeinterval() != null) {
			reqorderNoseconds = channel.getReqTimeinterval();
			reqorderNoseconds = 0 - reqorderNoseconds;
		}
		Date pretime = addSecond(d, reqorderNoseconds);

		SdkOrderMmdoMapper orderMmdoMapper = dal
				.getMapper(SdkOrderMmdoMapper.class);
		SdkOrderMmdoExample mmdoExample = new SdkOrderMmdoExample();
		// 更改为按手机号限制消费
		logger.debug("imsi =" + imsi + ", pretime =" + pretime
				+ ", operation =" + operation + ", reqorderNoseconds ="
				+ reqorderNoseconds + ", d =" + d);
		mmdoExample.createCriteria().andReqImsiEqualTo(imsi)
				.andReqTimeGreaterThanOrEqualTo(pretime)
				.andReqTimeLessThanOrEqualTo(reqOrderNoTime)
				.andOperationTypeEqualTo(operation);
		List<SdkOrderMmdo> orderMmdoList = orderMmdoMapper
				.selectByExample(mmdoExample);

		if (orderMmdoList != null && orderMmdoList.size() > 0) {
			flag = false;
		}
		return flag;
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

	public Date addSecond(Date date, int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);
		return calendar.getTime();
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

	/**
	 * 判断是否在规定时间之内，如果在规定时间之内则返回true，否则返回false，参数如果为空，则返回false。
	 * 
	 * @return
	 */
	private boolean judgeTime(String setTime) {
		boolean result = false;

		if (StringUtils.isEmpty(setTime) || StringUtils.isBlank(setTime)) {
			// 如果参数为空则返回false
			result = false;
		}

		TypeReference<List<BillingPeriodVo>> typeRef = new TypeReference<List<BillingPeriodVo>>() {
		};
		try {
			List<BillingPeriodVo> periods = mapper.readValue(setTime, typeRef);
			Date time = timeString2Date(date2TimeString(new Date()));
			for (BillingPeriodVo period : periods) {
				Date begin = timeString2Date(period.getBeginTime());
				Date end = timeString2Date(period.getEndTime());
				if (time.before(begin) || time.after(end)) {
					// 在这个时间点之外
					result = false;
				} else {
					// 在这个时间点之内
					result = true;
				}
			}
		} catch (Exception e) {
			logger.error("judge time is error , error is :", e);
			result = false;
		}
		return result;
	}

	/**
	 * 计费屏蔽设置
	 * 
	 * @param mobilephoneProvinceno
	 * @param mobilephoneNumber
	 * @param sdkOperatorPayChannel
	 * @return 
	 *         provinceFlag为计费屏蔽标识符，0代表没有屏蔽正常计费，1代表“未找到计费省”，2代表“计费时段屏蔽”，3代表“计费号段屏蔽
	 *         ”，4代表“计费省份屏蔽”
	 */
	private Integer checkProvince(Integer mobilephoneProvinceno,
			String mobilephoneNumber,
			SdkOperatorPayChannel sdkOperatorPayChannel) {
		Integer provinceFlag = 0;
		if (mobilephoneProvinceno != 0
				&& StringUtils.isNotBlank(mobilephoneNumber)) {
			// 根据sdk_telephone手机号判断
			if (sdkOperatorPayChannel.getBillingProvinceId() != null
					&& !sdkOperatorPayChannel.getBillingProvinceId().equals(
							mobilephoneProvinceno)) {
				provinceFlag = 1;
			}
			if (StringUtils.isNotBlank(sdkOperatorPayChannel
					.getBillingPeriods())) {
				if (!judgeTime(sdkOperatorPayChannel.getBillingPeriods())) {
					provinceFlag = 2;
				}
			}
			if (StringUtils.isNotBlank(sdkOperatorPayChannel
					.getShieldingSegments())) {
				String[] segments = sdkOperatorPayChannel
						.getShieldingSegments().split(",");
				for (String seg : segments) {
					if (mobilephoneNumber.startsWith(seg)) {
						provinceFlag = 3;
					}
				}
			}
			if (StringUtils.isNotBlank(sdkOperatorPayChannel
					.getShieldingProvinceIds())) {
				String[] provinceIds = sdkOperatorPayChannel
						.getShieldingProvinceIds().split(",");
				List<String> idList = Arrays.asList(provinceIds);
				if (idList.contains(String.valueOf(mobilephoneProvinceno))) {
					provinceFlag = 4;
				}
			}
		} else if (mobilephoneProvinceno != 0) {
			// 通过sdk_telephone_centernumber短信中心号判断
			if (StringUtils.isNotBlank(sdkOperatorPayChannel
					.getShieldingProvinceIds())) {
				String[] provinceIds = sdkOperatorPayChannel
						.getShieldingProvinceIds().split(",");
				List<String> idList = Arrays.asList(provinceIds);
				if (idList.contains(String.valueOf(mobilephoneProvinceno))) {
					provinceFlag = 4;
				}
			}
		}
		return provinceFlag;
	}

	/**
	 * 根据iccid获取省份id
	 * 
	 * @param iccid
	 * @return
	 */
	private int getProvinceNoByICCID(DAL dal, String iccid, String imsi) {
		int provinceNo = 0;
		if (StringUtils.isNotEmpty(iccid) && iccid.length() > 10) {
			// int operatorsId = getOperatorByICCID(iccid);
			int operatorsId = getOperatorByIMSI(imsi);
			if (operatorsId == 1) {
				String ss = iccid.substring(8, 10);
				/*
				 * 北京01 天津02 河北03 山西04 内蒙古05 辽宁06 吉林07 黑龙江08 上海09 江苏10 浙江11 安徽12
				 * 福建13 江西14 山东15 河南16 湖北17 湖南18 广东19 广西20 海南21 四川22 贵州23 云南24
				 * 西藏25 陕西26 甘肃27 青海28 宁夏29 新疆30 重庆31
				 */
				if ("01".equals(ss)) {
					provinceNo = 11;
				} else if ("02".equals(ss)) {
					provinceNo = 12;
				} else if ("03".equals(ss)) {
					provinceNo = 13;
				} else if ("04".equals(ss)) {
					provinceNo = 14;
				} else if ("05".equals(ss)) {
					provinceNo = 15;
				} else if ("06".equals(ss)) {
					provinceNo = 21;
				} else if ("07".equals(ss)) {
					provinceNo = 22;
				} else if ("08".equals(ss)) {
					provinceNo = 23;
				} else if ("09".equals(ss)) {
					provinceNo = 31;
				} else if ("10".equals(ss)) {
					provinceNo = 32;
				} else if ("11".equals(ss)) {
					provinceNo = 33;
				} else if ("12".equals(ss)) {
					provinceNo = 34;
				} else if ("13".equals(ss)) {
					provinceNo = 35;
				} else if ("14".equals(ss)) {
					provinceNo = 36;
				} else if ("15".equals(ss)) {
					provinceNo = 37;
				} else if ("16".equals(ss)) {
					provinceNo = 41;
				} else if ("17".equals(ss)) {
					provinceNo = 42;
				} else if ("18".equals(ss)) {
					provinceNo = 43;
				} else if ("19".equals(ss)) {
					provinceNo = 44;
				} else if ("20".equals(ss)) {
					provinceNo = 45;
				} else if ("21".equals(ss)) {
					provinceNo = 46;
				} else if ("22".equals(ss)) {
					provinceNo = 51;
				} else if ("23".equals(ss)) {
					provinceNo = 52;
				} else if ("24".equals(ss)) {
					provinceNo = 53;
				} else if ("25".equals(ss)) {
					provinceNo = 54;
				} else if ("26".equals(ss)) {
					provinceNo = 61;
				} else if ("27".equals(ss)) {
					provinceNo = 62;
				} else if ("28".equals(ss)) {
					provinceNo = 63;
				} else if ("29".equals(ss)) {
					provinceNo = 64;
				} else if ("30".equals(ss)) {
					provinceNo = 65;
				} else if ("31".equals(ss)) {
					provinceNo = 50;
				}
			} else if (operatorsId == 2) {
				String ss = iccid.substring(9, 11);
				/*
				 * 10内蒙古 11北京 13天津 17山东 18河北 19山西 30安徽 31上海 34江苏 36浙江 38福建 50海南
				 * 51广东 59广西 70青海 71湖北 74湖南 75江西 76河南 79西藏 81四川 83重庆 84陕西 85贵州
				 * 86云南 87甘肃 88宁夏 89新疆 90吉林 91辽宁 97黑龙江
				 */
				if ("10".equals(ss)) {
					provinceNo = 15;
				} else if ("11".equals(ss)) {
					provinceNo = 11;
				} else if ("13".equals(ss)) {
					provinceNo = 12;
				} else if ("17".equals(ss)) {
					provinceNo = 37;
				} else if ("18".equals(ss)) {
					provinceNo = 13;
				} else if ("19".equals(ss)) {
					provinceNo = 14;
				} else if ("30".equals(ss)) {
					provinceNo = 34;
				} else if ("31".equals(ss)) {
					provinceNo = 31;
				} else if ("34".equals(ss)) {
					provinceNo = 32;
				} else if ("36".equals(ss)) {
					provinceNo = 33;
				} else if ("38".equals(ss)) {
					provinceNo = 35;
				} else if ("50".equals(ss)) {
					provinceNo = 46;
				} else if ("51".equals(ss)) {
					provinceNo = 44;
				} else if ("59".equals(ss)) {
					provinceNo = 45;
				} else if ("70".equals(ss)) {
					provinceNo = 63;
				} else if ("71".equals(ss)) {
					provinceNo = 42;
				} else if ("74".equals(ss)) {
					provinceNo = 43;
				} else if ("75".equals(ss)) {
					provinceNo = 36;
				} else if ("76".equals(ss)) {
					provinceNo = 41;
				} else if ("79".equals(ss)) {
					provinceNo = 54;
				} else if ("81".equals(ss)) {
					provinceNo = 51;
				} else if ("83".equals(ss)) {
					provinceNo = 50;
				} else if ("84".equals(ss)) {
					provinceNo = 61;
				} else if ("85".equals(ss)) {
					provinceNo = 52;
				} else if ("86".equals(ss)) {
					provinceNo = 53;
				} else if ("87".equals(ss)) {
					provinceNo = 62;
				} else if ("88".equals(ss)) {
					provinceNo = 64;
				} else if ("89".equals(ss)) {
					provinceNo = 65;
				} else if ("90".equals(ss)) {
					provinceNo = 22;
				} else if ("91".equals(ss)) {
					provinceNo = 21;
				} else if ("97".equals(ss)) {
					provinceNo = 23;
				}
			} else if (operatorsId == 3) {
				String areacode = iccid.substring(10, 13);
				int areacodeInt = parseInteger(areacode);
				SdkCityAddrMapper sdkCityAddrMapper = dal
						.getMapper(SdkCityAddrMapper.class);
				SdkCityAddrExample sdkCityAddrExample = new SdkCityAddrExample();
				sdkCityAddrExample.createCriteria().andIdEqualTo(areacodeInt);
				SdkCityAddr sdkCityAddr = sdkCityAddrMapper
						.selectOne(sdkCityAddrExample);
				if (sdkCityAddr != null) {
					String provinceId = sdkCityAddr.getProvinceId();
					if (provinceId.length() >= 2) {
						provinceId = provinceId.substring(0, 2);
					}
					provinceNo = parseInteger(provinceId);
				}
			}
		}
		return provinceNo;
	}

	/**
	 * 用于在订单失败的情况下，在sdk_order表和sdk_order_mmdo表插入一条记录。
	 */
	private void insertFaileOrderInfo(CmdPay cmd, DAL dal,
			SdkMmdoSetting sdkMmdoSetting, SdkOrder sdkOrder,
			SdkOperatorPayChannel channel, int sdkOrder_status,
			String sdkOrder_statusdetail) {
		SdkOrderMmdoMapper mmdoMapper = dal.getMapper(SdkOrderMmdoMapper.class);
		SdkOrderMmdo mmdo = new SdkOrderMmdo();
		mmdo.setReqImsi(imsi_Param);
		mmdo.setReqOrderAmount(realamount);
		mmdo.setReqSendContent(sdkMmdoSetting.getContent());
		mmdo.setReqSendNumber(sdkMmdoSetting.getNumber());
		mmdo.setReqTime(reqOrderNoTime);
		mmdo.setGameId(gameSession.getGameId());
		mmdo.setOperationType(operatorsId);
		mmdo.setUid(gameSession.getUid());
		mmdo.setImei(imei_Param);
		mmdo.setMacAddr(mac_Param);
		mmdo.setIpAddr(cmd.getRequestHost());
		mmdo.setRawData(imsi_Param);
		mmdo.setPayChannelCode(channel.getChannelCode());
		if (sdkMmdoSetting.getTjpropsname() != null) {
			mmdo.setTjpropsname(sdkMmdoSetting.getTjpropsname());
		}
		mmdo.setTradeid(outtradeid);
		mmdo.setRespStatus(0);
		if (!orderIfOver30m) {
			mmdo.setOverThirtym(1);
		} else {
			mmdo.setOverThirtym(0);
		}
		mmdo.setMobilephone(mobilephoneNumber);
		mmdo.setProvinceNo(mobilephoneProvinceno);
		mmdo.setIccid(iccid_Param);
		mmdo.setCenternumber(mobilephoneCenterNumber);

		mmdoMapper.insertSelective(mmdo);

		SdkOrderMapper mapperOrder = dal.getMapper(SdkOrderMapper.class);
		sdkOrder.setPayId(mmdo.getPayId());
		sdkOrder.setAmount(realamount);
		sdkOrder.setStatus(sdkOrder_status);
		sdkOrder.setStatusDetail(sdkOrder_statusdetail);
		sdkOrder.setSdkver(sdkver_Param);
		mapperOrder.insert(sdkOrder);

		// 添加回调订单
		insertSdkNotifyMmdoInfo(dal, sdkOrder, mmdo, channel);

		dal.commit();
	}

	/**
	 * 对sdk_notify_mmdo表插入一条数据，状态初始化为0，即默认失败
	 * 
	 * @param dal
	 * @param sdkOrder
	 * @param sdkOrderMmdo
	 * @param channel
	 */
	private void insertSdkNotifyMmdoInfo(DAL dal, SdkOrder sdkOrder,
			SdkOrderMmdo sdkOrderMmdo, SdkOperatorPayChannel channel) {
		SdkNotifyMmdoMapper sdkNotifyMmdoMapper = dal
				.getMapper(SdkNotifyMmdoMapper.class);
		SdkNotifyMmdo sdkNotifyMmdo = new SdkNotifyMmdo();
		sdkNotifyMmdo.setOrderNo(sdkOrder.getOrderNo());
		if (StringUtils.isNotBlank(sdkOrderMmdo.getTradeid())) {
			sdkNotifyMmdo.setTradeid(sdkOrderMmdo.getTradeid());
		}
		sdkNotifyMmdo.setChargepoint(sdkOrderMmdo.getReqSendContent());
		SdkTelephoneMapper sdkTelephoneMapper = dal
				.getMapper(SdkTelephoneMapper.class);
		SdkTelephoneExample sdkTelephoneExample = new SdkTelephoneExample();
		String imsi = "";
		if (StringUtils.isNotBlank(sdkOrderMmdo.getReqImsi())) {
			imsi = sdkOrderMmdo.getReqImsi();
		}
		sdkTelephoneExample.createCriteria().andImsiEqualTo(imsi);
		SdkTelephone sdkTelephone = sdkTelephoneMapper
				.selectOne(sdkTelephoneExample);
		if (sdkTelephone != null) {
			sdkNotifyMmdo.setProvinceNo(sdkTelephone.getProvinceNo());
			sdkNotifyMmdo.setMobilephone(sdkTelephone.getMobilephone());
		}
		sdkNotifyMmdo.setGameId(sdkOrderMmdo.getGameId());
		sdkNotifyMmdo.setAmount(sdkOrderMmdo.getReqOrderAmount());
		sdkNotifyMmdo.setCreateTime(new Date());
		sdkNotifyMmdo.setOperationType(sdkOrderMmdo.getOperationType());
		sdkNotifyMmdo.setPayChannelCode(sdkOrderMmdo.getPayChannelCode());
		int additionalStatus = 0;
		if (sdkOrderMmdo.getAdditionalStatus() == null) {
			additionalStatus = 0;
		} else {
			additionalStatus = 1;
		}
		sdkNotifyMmdo.setAdditionalStatus(additionalStatus);
		// 初始化状态为0
		sdkNotifyMmdo.setStatus(0);
		sdkNotifyMmdoMapper.insertSelective(sdkNotifyMmdo);
		dal.commit();
	}

	@Override
	protected boolean isLoginRequired() {
		return true;
	}

	public static void main(String args[]) throws Exception {
		// Pay_mmdo p=new Pay_mmdo();
		// String phone_chengdu="15928876607";
		// String paycode="800000000107";
		// String clientOrderNum="1512161020440101";
		// // String phone_neimenggu="13674767652";
		// JsonCmccMopingDongman
		// res=p.getCmccMopingDongmanContent(phone_chengdu,paycode,clientOrderNum);
		// System.out.println(res.toString());
		// System.out.println(res.getUrl());

		String result = "3333";
		if (!StringUtils.isNotBlank(result)) {
			System.out.println("2222");
		}
	}
}
