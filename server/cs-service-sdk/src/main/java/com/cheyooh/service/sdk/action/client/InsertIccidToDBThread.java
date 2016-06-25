package com.cheyooh.service.sdk.action.client;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.sdk.db.dao.SdkCityAddrMapper;
import com.cheyooh.service.sdk.db.dao.SdkImsiIccidMapper;
import com.cheyooh.service.sdk.db.entity.SdkCityAddr;
import com.cheyooh.service.sdk.db.entity.SdkCityAddrExample;
import com.cheyooh.service.sdk.db.entity.SdkImsiIccid;
import com.cheyooh.service.sdk.db.entity.SdkImsiIccidExample;
import com.cheyooh.tools.log.Logger;

public class InsertIccidToDBThread extends Thread {
	private Logger logger = new Logger(this.getClass());
	private String iccidParam = "";
	private String imsiParam = "";

	public InsertIccidToDBThread(String iccidParam, String imsiParam) {
		this.iccidParam = iccidParam;
		this.imsiParam = imsiParam;
	}

	@Override
	public void run() {
		DAL dal = DALFactory.createDAL();
		try {
			SdkImsiIccidMapper sdkImsiIccidMapper = dal
					.getMapper(SdkImsiIccidMapper.class);
			SdkImsiIccidExample sdkImsiIccidExample = new SdkImsiIccidExample();
			sdkImsiIccidExample.createCriteria().andIccidEqualTo(iccidParam);
			SdkImsiIccid sdkImsiIccid = sdkImsiIccidMapper
					.selectOne(sdkImsiIccidExample);
			if (sdkImsiIccid == null) {
				SdkImsiIccid sdkImsiIccidNew = new SdkImsiIccid();
				sdkImsiIccidNew.setIccid(iccidParam);
				sdkImsiIccidNew.setImsi(imsiParam);
//				int opt = getOperatorByICCID(iccidParam);
				int opt = getOperatorByIMSI(imsiParam);
				logger.debug("the opt is =" + opt);
				sdkImsiIccidNew.setOperatorType(opt);
				int provinceId = getProvinceNoByICCID(dal, iccidParam,imsiParam);
				sdkImsiIccidNew.setProvinceNo(provinceId);
				if (opt == 3 && iccidParam.length() > 15) {
					String areacode = iccidParam.substring(10, 13);
					int areacodeInt = parseInteger(areacode);
					sdkImsiIccidNew.setAreaCode(areacodeInt);
				}
				sdkImsiIccidNew.setCreatedTime(new Date());
				sdkImsiIccidMapper.insert(sdkImsiIccidNew);
			} else {
				if (!iccidParam.equals(sdkImsiIccid.getIccid())) {
					sdkImsiIccid.setIccid(iccidParam);
//					int opt = getOperatorByICCID(iccidParam);
					int opt = getOperatorByIMSI(imsiParam);
					sdkImsiIccid.setOperatorType(opt);
					int provinceId = getProvinceNoByICCID(dal, iccidParam,imsiParam);
					sdkImsiIccid.setProvinceNo(provinceId);
					if (opt == 3 && iccidParam.length() > 15) {
						String areacode = iccidParam.substring(10, 13);
						int areacodeInt = parseInteger(areacode);
						sdkImsiIccid.setAreaCode(areacodeInt);
					}
					sdkImsiIccid.setUpdatedTime(new Date());
					sdkImsiIccidMapper.updateByPrimaryKey(sdkImsiIccid);
				}
			}

			dal.commit();
		} catch (Exception e) {
			logger.error("the run error is : " + e);
			return;
		} finally {
			dal.close();
		}

	}

	/**
	 * 根据iccid 计算运营商 1:中国移动 2:中国联通 3:中国电信
	 * 
	 * @param iccid
	 * @return
	 */
	private int getOperatorByICCID(String iccid) {
		int operatorId = 0;
		if (StringUtils.isNotEmpty(iccid)) {
			if (iccid.startsWith("898600") || iccid.startsWith("898602")
					|| iccid.startsWith("898605")|| iccid.startsWith("898607")
					|| iccid.startsWith("898609")) {
				// 中国移动
				operatorId = 1;
			} else if (iccid.startsWith("898601")) {
				// 中国联通
				operatorId = 2;
			} else if (iccid.startsWith("898603") || iccid.startsWith("898611")) {
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
	
}
