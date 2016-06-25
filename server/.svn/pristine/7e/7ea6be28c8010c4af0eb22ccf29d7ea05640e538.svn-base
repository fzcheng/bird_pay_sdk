package com.cheyooh.service.sdk.action.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkUpgradeJarGameMapper;
import com.cheyooh.service.sdk.db.dao.SdkUpgradeJarMapper;
import com.cheyooh.service.sdk.db.entity.SdkUpgradeJar;
import com.cheyooh.service.sdk.db.entity.SdkUpgradeJarExample;
import com.cheyooh.service.sdk.db.entity.SdkUpgradeJarGame;
import com.cheyooh.service.sdk.db.entity.SdkUpgradeJarGameExample;
import com.cheyooh.service.sdk.idata.CmdGeneral;
import com.cheyooh.service.sdk.idata.ResultUpgradeJar;

/**
 * 说明：该协议用于判断当前渠道的该游戏是否需要升级jar包 客户端的请求参数如下： 1.命令参数 m=upgrade_jar
 * 
 * @author ljg
 * 
 */

public class Upgrade_Jar extends AbstractSdkClientService<CmdGeneral> {

	@Override
	protected Result execute(CmdGeneral cmd) {
		DAL dal = DALFactory.createDAL();
		try {
			String v="";
			SdkUpgradeJarGameMapper sdkUpgradeJarGameMapper=dal.getMapper(SdkUpgradeJarGameMapper.class);
			SdkUpgradeJarGameExample sdkUpgradeJarGameExample=new SdkUpgradeJarGameExample();
			sdkUpgradeJarGameExample.createCriteria().andGameIdEqualTo(game.getGameId()).andStatusTagEqualTo(1);
			List<SdkUpgradeJarGame> sdkUpgradeJarGames=sdkUpgradeJarGameMapper.selectByExample(sdkUpgradeJarGameExample);
			
			List<String> vs=new ArrayList<String>();
			if(sdkUpgradeJarGames!=null){
				for(SdkUpgradeJarGame sdkUpgradeJarGame:sdkUpgradeJarGames){
					vs.add(sdkUpgradeJarGame.getVersionCode());
				}
				v=return_top_versioncode(vs);
				logger.debug("the top versioncode is :"+v);
			}
			
			SdkUpgradeJarMapper sdkUpgradeJarMapper = dal
					.getMapper(SdkUpgradeJarMapper.class);
			SdkUpgradeJarExample sdkUpgradeJarExample = new SdkUpgradeJarExample();
			sdkUpgradeJarExample.createCriteria().andVersionCodeEqualTo(v).andStatusTagEqualTo(1);

			SdkUpgradeJar sdkUpgradeJar = sdkUpgradeJarMapper
					.selectOne(sdkUpgradeJarExample);
			String down_url = null;
			boolean ifdownload = false;
			String resultversioncode="";
			
			String version_code_jar = cmd.getServiceContext().getRequest()
					.getParameter("version_code_jar");
			logger.debug("the request version_code_jar is :"+version_code_jar);
//			logger.debug("the sdkUpgradeJar top versioncode is :"+sdkUpgradeJar.getVersionCode());
//			logger.info("the request version_code_jar is :"+version_code_jar);
			if(sdkUpgradeJar!=null){
				logger.info("the sdkUpgradeJar top versioncode is :"+sdkUpgradeJar.getVersionCode());
				if(compare_versioncode(sdkUpgradeJar.getVersionCode(),version_code_jar)){
					ifdownload=true;
					resultversioncode=sdkUpgradeJar.getVersionCode();
					String basefilepath=Cfg.cfg.getString("sdk.jar.url");
					logger.debug("the sdk.jar.url basefilepath is :"+basefilepath);
//					logger.info("the sdk.jar.url basefilepath is :"+basefilepath);
					down_url=basefilepath+sdkUpgradeJar.getDownUrl();
					logger.debug("the upgradejar downurl is : "+down_url);
					logger.info("the upgradejar downurl is : "+down_url);
				}else{
					down_url="";
				}
			}else{
				down_url="";
			}
			
			ResultUpgradeJar resultUpgradeJar=new ResultUpgradeJar();
			resultUpgradeJar.setDown_url(down_url);
			resultUpgradeJar.setIfdownload(ifdownload);
			resultUpgradeJar.setVersion_code_jar(resultversioncode);
			Result result = new Result(resultUpgradeJar);
//			logger.debug("Upgrade_Jar result : " + result.getXml("Upgrade_Jar"));
			
			dal.commit();
			return result;
		} catch (Exception e) {
			logger.error("the upgradeJar error is :" + e);
			return null;
		} finally {
			dal.close();
		}
	}

	@Override
	protected boolean isLoginRequired() {
		return false;
	}
	/*
	 * 版本号的形式为"0.0.0"
	 * 版本号的比较
	 * 传入的版本号versioncode和最新版本号standard比较
	 * 如果小于则为true
	 * 如果大于或等于则为false
	 */
	
	private boolean compare_versioncode(String standard,String versioncode){
		boolean result=false;
		if(standard.contains(".")&&versioncode.contains(".")){
			String[] standardArray=standard.split("\\.");
			String[] versioncodeArray=versioncode.split("\\.");
			if(standardArray.length==3&&versioncodeArray.length==3){
				for(int i=0;i<standardArray.length;i++){
					if(parseInteger(versioncodeArray[i])<parseInteger(standardArray[i])){
						result=true;
						break;
					}
				}
			}
			
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

	private String return_top_versioncode(List<String> v){
		String result="";
		if(v.size()==1){
			result=v.get(0);
		}else{
			for(int i=0;i<v.size();i++){
				if(i==(v.size()-1)){
					break;
				}
				result=v.get(i);
				if(!compare_versioncode(result, v.get(i+1))){
					result=v.get(i+1);
				}
			}
		}
		return result;
	}
}
