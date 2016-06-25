package com.cheyooh.service.sdk.action.client;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.sdk.db.dao.SdkSmsGameMapper;
import com.cheyooh.service.sdk.db.dao.SdkSmsMapper;
import com.cheyooh.service.sdk.db.dao.SdkTelephoneMapper;
import com.cheyooh.service.sdk.db.dao.SdkUpgradeMapper;
import com.cheyooh.service.sdk.db.dao.SdkXuanwuMapper;
import com.cheyooh.service.sdk.db.entity.SdkSms;
import com.cheyooh.service.sdk.db.entity.SdkSmsExample;
import com.cheyooh.service.sdk.db.entity.SdkSmsGame;
import com.cheyooh.service.sdk.db.entity.SdkSmsGameExample;
import com.cheyooh.service.sdk.db.entity.SdkTelephone;
import com.cheyooh.service.sdk.db.entity.SdkTelephoneExample;
import com.cheyooh.service.sdk.db.entity.SdkUpgrade;
import com.cheyooh.service.sdk.db.entity.SdkUpgradeExample;
import com.cheyooh.service.sdk.db.entity.SdkXuanwu;
import com.cheyooh.service.sdk.idata.CmdGeneral;
import com.cheyooh.service.sdk.idata.ResultUpgrade;
import com.cheyooh.service.sdk.tools.GenerateTool;

/**
 * 说明：该协议用于判断当前取道的该游戏是否需要升级 客户端的请求参数如下： 1.命令参数 m=upgrade
 * 
 * @author zhouzg@cheyooh.com
 * 
 */
public class Upgrade extends AbstractSdkClientService<CmdGeneral> {

	private String imsiParam = "";
	private String imeiParam = "";

	@SuppressWarnings("unused")
	@Override
	protected Result execute(CmdGeneral cmd) {
		DAL dal = DALFactory.createDAL();
		try {
			logger.debug("Enter the Upgrade! ");
			SdkUpgradeMapper mapper = dal.getMapper(SdkUpgradeMapper.class);
			SdkUpgradeExample example = new SdkUpgradeExample();
			example.createCriteria().andGameIdEqualTo(game.getGameId())
					.andChannelEqualTo(cmd.getChannel());

			SdkUpgrade u = mapper.selectOne(example);

			String down_url = null;
			boolean force = false;
			int versionCode = 0;
			String Sv1 = "";
			if (u != null) {
				versionCode = u.getVersionCode();
				Sv1 = cmd.getServiceContext().getRequest()
						.getParameter("version_code");
				int v1 = Integer.valueOf(Sv1);

				int v2 = u.getVersionCode() == null ? 0 : u.getVersionCode();
				if (v2 > v1) {
					switch (u.getForceTag()) {
					case 2:
						force = true;
						down_url = u.getDownUrl();
						break;
					case 1:
						force = false;
						down_url = u.getDownUrl();
						break;
					}
				}
			}

			ResultUpgrade ru = new ResultUpgrade();
			ru.setStatus(down_url != null);
			ru.setForce(force);
			ru.setDownload_url(down_url);
			ru.setVersionCode(versionCode);

			boolean status = false;
			if (down_url != null) {
				status = true;
			}

			// 下载条件：status为true,down_url不为空，force为false为建议更新，force为true为强制更新
			logger.info("the Upgrade is : appkey =" + cmd.getAppkey()
					+ ", status =" + status + ", force =" + force
					+ ", down_url =" + down_url + ", req_versionCode =" + Sv1
					+ ", db_versionCode =" + versionCode + ", req_channel ="
					+ cmd.getChannel());

			imsiParam = cmd.getServiceContext().getRequest()
					.getParameter("imsi");
			imeiParam = cmd.getServiceContext().getRequest()
					.getParameter("imei");
			if (StringUtils.isEmpty(imsiParam)) {
				imsiParam = "";
			}
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
			String iccid = cmd.getServiceContext().getRequest()
					.getParameter("iccid");
			if (StringUtils.isEmpty(iccid)) {
				iccid = "";
			}
			// logger.debug("the imsiParam is :" + imsiParam);
			// logger.debug("the imeiParam is :" + imeiParam);

			int opId = 0;
			// String upPort = "";
			String imsi1 = "";
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

			if (StringUtils.isNotBlank(imsi1)
					&& StringUtils.isNotBlank(imeiParam)) {
				SdkTelephoneMapper sdkTelephoneMapper = dal
						.getMapper(SdkTelephoneMapper.class);
				SdkTelephoneExample sdkTelephoneExample = new SdkTelephoneExample();
				sdkTelephoneExample.createCriteria().andImeiEqualTo(imeiParam)
						.andImsiEqualTo(imsi1);
				SdkTelephone sdkTelephone = sdkTelephoneMapper
						.selectOne(sdkTelephoneExample);

				if (sdkTelephone != null) {
					ru.setPhone(String.valueOf(sdkTelephone.getMobilephone()));
					ru.setUpport("");
				} else {
					SdkTelephoneExample sdkTelephoneExample2 = new SdkTelephoneExample();
					sdkTelephoneExample2.createCriteria().andImsiEqualTo(imsi1);
					SdkTelephone sdkTelephone2 = sdkTelephoneMapper
							.selectOne(sdkTelephoneExample2);
					if (sdkTelephone2 != null) {
						ru.setPhone(String.valueOf(sdkTelephone2
								.getMobilephone()));
						ru.setUpport("");
						sdkTelephone2.setImei(imeiParam);
						sdkTelephoneMapper.updateByPrimaryKey(sdkTelephone2);
					} else {
						ru.setPhone("");
						SdkSmsMapper sdkSmsMapper = dal
								.getMapper(SdkSmsMapper.class);
						SdkSmsExample sdkSmsExample = new SdkSmsExample();
						sdkSmsExample.createCriteria()
								.andOperationTypeEqualTo(opId)
								.andUseStateEqualTo(1);
						SdkSms sdkSms = sdkSmsMapper.selectOne(sdkSmsExample);
						if (sdkSms != null) {
							ru.setUpport(sdkSms.getUpPort());
							String id=GenerateTool.createOrderNo();
							ru.setSdkSmsContent(id);
							logger.debug("the Upgrade Upport ="+sdkSms.getUpPort()+", content ="+id);
							
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
							//以下是亿美平台接收短信信息的进程
//							EmayUpMessageReceive emayUpMessageReceive = new EmayUpMessageReceive();
//							emayUpMessageReceive.start();
						} else {
							ru.setUpport("");
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
				ru.setUpport("");
			}
			
			dal.commit();
			logger.debug("the Upgrade result is: "
					+ (new Result(ru)).getXml("Upgrade"));
			return new Result(ru);
		} catch (Exception e) {
			logger.error("the upgrade error is :" + e);
			return null;
		} finally {
			dal.close();
		}
	}

	@Override
	protected boolean isLoginRequired() {
		return false;
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
