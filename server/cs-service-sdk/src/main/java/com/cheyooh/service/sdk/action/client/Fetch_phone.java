package com.cheyooh.service.sdk.action.client;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultContentList;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.db.dao.*;
import com.cheyooh.service.sdk.db.entity.*;
import com.cheyooh.service.sdk.idata.CmdGeneral;
import com.cheyooh.service.sdk.idata.ResultSmsUpPort;
import com.cheyooh.service.sdk.tools.GenerateTool;

public class Fetch_phone extends AbstractSdkClientService<CmdGeneral> {

	@Override
	protected boolean isLoginRequired() {

		return false;
	}

	@Override
	protected Result execute(CmdGeneral cmd) {
		DAL dal = DALFactory.createDAL();
		try {
			String imsiParam = cmd.getServiceContext().getRequest()
					.getParameter("imsi");
			if (StringUtils.isEmpty(imsiParam)) {
				imsiParam = "";
			}
			String imeiParam = cmd.getServiceContext().getRequest()
					.getParameter("imei");
			if (StringUtils.isEmpty(imeiParam)) {
				imeiParam = "";
			}
			String smscenternumber = cmd.getServiceContext().getRequest()
					.getParameter("smscenternumber");
			if (StringUtils.isEmpty(smscenternumber)) {
				smscenternumber = "";
			}else {
				if ("0".equals(smscenternumber)) {
					smscenternumber = "";
				} else if (smscenternumber.startsWith("+86")) {
					smscenternumber = smscenternumber.substring(3);
				} else if(smscenternumber.startsWith("86")){
					smscenternumber = smscenternumber.substring(2);
				}else if(smscenternumber.startsWith(";#386")){
					smscenternumber = smscenternumber.substring(5);
				}else if(smscenternumber.startsWith("1#86")){
					smscenternumber = smscenternumber.substring(4);
				}			
			}
			// String latitude =
			// cmd.getServiceContext().getRequest().getParameter("latitude");
			// String longitude =
			// cmd.getServiceContext().getRequest().getParameter("longitude");
			// logger.debug("the latitude and longitude is :" +
			// latitude+" , "+longitude);
			String iccid = cmd.getServiceContext().getRequest()
					.getParameter("iccid");
			if (StringUtils.isEmpty(iccid)) {
				iccid = "";
			}

			logger.info("the imsi =" + imsiParam + ",smscenternumber ="
					+ smscenternumber + ",iccid =" + iccid);

			int opId = 0;
			String upPort = "";
			String content="";
			String imsi1 = "";
			@SuppressWarnings("unused")
			String imsi2 = "";
			if (imsiParam.contains("|")) {
				String[] imsis = imsiParam.split("\\|");
				imsi1 = imsis[0];
				imsi2 = imsis[1];
				opId = getOperatorByIMSI(imsi1);
			} else if (imsiParam.contains(" ")) {
				String[] imsis = imsiParam.split(" ");
				imsi1 = imsis[0];
				imsi2 = imsis[1];
				opId = getOperatorByIMSI(imsi1);
			} else {
				imsi1 = imsiParam;
				opId = getOperatorByIMSI(imsi1);
			}

			if (StringUtils.isNotBlank(imsi1) && StringUtils.isNotEmpty(iccid)
					&& StringUtils.isNotBlank(iccid)) {
				// 处理iccid
				iccid = iccid.trim();
				SdkImsiIccidMapper sdkImsiIccidMapper = dal
						.getMapper(SdkImsiIccidMapper.class);
				SdkImsiIccidExample sdkImsiIccidExample = new SdkImsiIccidExample();
				sdkImsiIccidExample.createCriteria().andIccidEqualTo(iccid);
				SdkImsiIccid sdkImsiIccid = sdkImsiIccidMapper
						.selectOne(sdkImsiIccidExample);
				if (sdkImsiIccid == null) {
					SdkImsiIccid sdkImsiIccidNew = new SdkImsiIccid();
					sdkImsiIccidNew.setIccid(iccid);
					sdkImsiIccidNew.setImsi(imsi1);
//					int opt = getOperatorByICCID(iccid);
					int opt = getOperatorByIMSI(imsi1);
					logger.debug("the opt is =" + opt);
					sdkImsiIccidNew.setOperatorType(opt);
					int provinceId = getProvinceNoByICCID(dal, iccid,imsi1);
					sdkImsiIccidNew.setProvinceNo(provinceId);
					if (opt == 3 && iccid.length() > 15) {
						String areacode = iccid.substring(10, 13);
						int areacodeInt = parseInteger(areacode);
						sdkImsiIccidNew.setAreaCode(areacodeInt);
					}
					sdkImsiIccidNew.setCreatedTime(new Date());
					sdkImsiIccidMapper.insert(sdkImsiIccidNew);
				} else {
					if (!iccid.equals(sdkImsiIccid.getIccid())) {
						sdkImsiIccid.setIccid(iccid);
//						int opt = getOperatorByICCID(iccid);
						int opt = getOperatorByIMSI(imsi1);
						sdkImsiIccid.setOperatorType(opt);
						int provinceId = getProvinceNoByICCID(dal, iccid,imsi1);
						sdkImsiIccid.setProvinceNo(provinceId);
						if (opt == 3 && iccid.length() > 15) {
							String areacode = iccid.substring(10, 13);
							int areacodeInt = parseInteger(areacode);
							sdkImsiIccid.setAreaCode(areacodeInt);
						}
						sdkImsiIccid.setUpdatedTime(new Date());
						sdkImsiIccidMapper.updateByPrimaryKey(sdkImsiIccid);
					}
				}
			}

			if (StringUtils.isNotBlank(imsi1)
					&& StringUtils.isNotBlank(smscenternumber)) {
				// 处理短信中心号
				SdkTelephoneCenternumberMapper stcMapper = dal
						.getMapper(SdkTelephoneCenternumberMapper.class);
				SdkTelephoneCenternumberExample stcExample = new SdkTelephoneCenternumberExample();
				stcExample.createCriteria().andImsiEqualTo(imsi1);
				SdkTelephoneCenternumber stc = stcMapper.selectOne(stcExample);
				if (stc == null) {
					SdkTelephoneCenternumber stcNew = new SdkTelephoneCenternumber();
					stcNew.setImsi(imsi1);
					stcNew.setCenternumber(smscenternumber);
					stcNew.setCreatedTime(new Date());
					SdkCenternumberMapper sdkCenternumberMapper=dal.getMapper(SdkCenternumberMapper.class);
					SdkCenternumberExample sdkCenternumberExample=new SdkCenternumberExample();
					sdkCenternumberExample.createCriteria().andCenternumberEqualTo(smscenternumber);
					SdkCenternumber sdkCenternumber=sdkCenternumberMapper.selectOne(sdkCenternumberExample);
					if(sdkCenternumber!=null){
						stcNew.setProvinceNo(sdkCenternumber.getProvinceNo());
						stcNew.setAreaCode(sdkCenternumber.getAreaCode());
					}
					stcMapper.insert(stcNew);
				} else {
					if (!stc.getCenternumber().equals(smscenternumber)) {
						SdkCenternumberMapper sdkCenternumberMapper=dal.getMapper(SdkCenternumberMapper.class);
						SdkCenternumberExample sdkCenternumberExample=new SdkCenternumberExample();
						sdkCenternumberExample.createCriteria().andCenternumberEqualTo(smscenternumber);
						SdkCenternumber sdkCenternumber=sdkCenternumberMapper.selectOne(sdkCenternumberExample);
						if(sdkCenternumber!=null){
							stc.setProvinceNo(sdkCenternumber.getProvinceNo());
							stc.setAreaCode(sdkCenternumber.getAreaCode());
						}
						stc.setCenternumber(smscenternumber);
						stc.setUpdatedTime(new Date());
						stcMapper.updateByPrimaryKey(stc);
					}
				}
			}

			if (StringUtils.isNotBlank(imsi1)
					&& StringUtils.isNotBlank(imeiParam)) {
				// 处理手机号
				SdkTelephoneMapper sdkTelephoneMapper = dal
						.getMapper(SdkTelephoneMapper.class);
				SdkTelephoneExample sdkTelephoneExample = new SdkTelephoneExample();
				sdkTelephoneExample.createCriteria().andImeiEqualTo(imeiParam)
						.andImsiEqualTo(imsi1);
				SdkTelephone sdkTelephone = sdkTelephoneMapper
						.selectOne(sdkTelephoneExample);

				if (sdkTelephone == null) {
					SdkTelephoneExample sdkTelephoneExample2 = new SdkTelephoneExample();
					sdkTelephoneExample2.createCriteria().andImsiEqualTo(imsi1);
					SdkTelephone sdkTelephone2 = sdkTelephoneMapper
							.selectOne(sdkTelephoneExample2);
					if (sdkTelephone2 != null) {
						sdkTelephone2.setImei(imeiParam);
						sdkTelephoneMapper.updateByPrimaryKey(sdkTelephone2);
						// sdkTelephoneMapper.updateByExampleSelective(
						// sdkTelephone2, sdkTelephoneExample2);
					} else {
						SdkSmsMapper sdkSmsMapper = dal
								.getMapper(SdkSmsMapper.class);
						SdkSmsExample sdkSmsExample = new SdkSmsExample();
						sdkSmsExample.createCriteria()
								.andOperationTypeEqualTo(opId)
								.andUseStateEqualTo(1);
						SdkSms sdkSms = sdkSmsMapper.selectOne(sdkSmsExample);
						if (sdkSms != null) {
							upPort = sdkSms.getUpPort();
							String id=GenerateTool.createOrderNo();
							content=id;
							SdkXuanwu sdkXuanwu=new SdkXuanwu();
							sdkXuanwu.setId(id);
							sdkXuanwu.setImsi(imsi1);
							sdkXuanwu.setImei(imeiParam);
							sdkXuanwu.setCenternumber(smscenternumber);
							sdkXuanwu.setIccid(iccid);
							sdkXuanwu.setCreatedTime(new Date());
							SdkXuanwuMapper sdkXuanwuMapper=dal.getMapper(SdkXuanwuMapper.class);
							sdkXuanwuMapper.insert(sdkXuanwu);
//							XuanWuMmsGetPhoneThread xuanWuMmsGetPhoneThread=new XuanWuMmsGetPhoneThread();
//							xuanWuMmsGetPhoneThread.start();
							//以下是亿美平台接收短信信息的进程,现已不使用此平台
//							EmayUpMessageReceive emayUpMessageReceive = new EmayUpMessageReceive();
//							emayUpMessageReceive.start();
						}
					}
				}
			}

			//在表sdk_sms_game设置不发短信，则下发端口为空
			SdkSmsGameMapper sdkSmsGameMapper = dal
					.getMapper(SdkSmsGameMapper.class);
			SdkSmsGameExample sdkSmsGameExample = new SdkSmsGameExample();
			sdkSmsGameExample.createCriteria()
					.andGameIdEqualTo(game.getGameId()).andSendStateEqualTo(0);
			SdkSmsGame sdkSmsGame = sdkSmsGameMapper
					.selectOne(sdkSmsGameExample);
			if (sdkSmsGame != null) {
				upPort = "";
			}

			ResultContentList contentList = new ResultContentList();
			ResultSmsUpPort resultSmsUpPort = new ResultSmsUpPort();
			resultSmsUpPort.setSdkSmsUpPort(upPort);
			resultSmsUpPort.setSdkSmsContent(content);
			contentList.addContent(resultSmsUpPort);
			Result result = new Result(contentList);
			logger.debug("sdkSmsUpPort result : "
					+ result.getXml("Fetch_phone"));
			dal.commit();
			return result;

		} catch (Exception e) {
			logger.error("the get_sms_upport error is : " + e);
			return StatusCode.ERR_NOTFOUND();
		} finally {
			dal.close();
		}

	}

	/**
	 * 根据imsi 计算运营商 1:中国移动 2:中国联通 3:中国电信
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

	/**
	 * 根据iccid获取省份id
	 * 
	 * @param iccid
	 * @return
	 */
	private int getProvinceNoByICCID(DAL dal, String iccid,String imsi) {
		int provinceNo = 0;
		if (StringUtils.isNotEmpty(iccid) && iccid.length() > 10) {
//			int opId = getOperatorByICCID(iccid);
			int opId = getOperatorByIMSI(imsi);
			if (opId == 1) {
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
			} else if (opId == 2) {
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
			} else if (opId == 3) {
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
}
