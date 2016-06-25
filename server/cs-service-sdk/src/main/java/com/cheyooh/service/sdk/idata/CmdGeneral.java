package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.Cmd;
import com.cheyooh.service.framework.utils.annotation.NotNull;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 * 
 * 接收HTTP请求: 通用参数
 * 
 */
public class CmdGeneral extends Cmd {
	private static final long serialVersionUID = -1353221384032868598L;
 	
	/**
	 * 客户端Key
	 */
	@NotNull
	private String appkey;
  
	/**
	 * --- 客户端渠道号，必填。渠道号中可以带中文，例如：T001官网V132
	 */
	@NotNull
	private String channel;

	/**
	 * ---- 验证签名，必填，用以保证服务器区分是否是正常客户端的请求 
	 */
	@NotNull
	private String checksign;

	/**
	 * 软件版本号
	 */
	@NotNull
	private String version;
	
	/**
	 * 会话ID
	 */
	private String sid;
	
	
	/**
	 * 版本代码
	 */
	private int versionCode;
	
	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getChecksign() {
		return checksign;
	}

	public void setChecksign(String checksign) {
		this.checksign = checksign;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	 
 

}
