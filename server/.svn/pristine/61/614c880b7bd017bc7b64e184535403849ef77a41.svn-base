package com.cheyooh.service.__SERVICE_NAME.idata;

import com.cheyooh.service.framework.idata.Cmd;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 * 
 * 接收HTTP请求: 通用参数
 * 
 */
public class CmdGeneral extends Cmd {
	private static final long serialVersionUID = -1353221384032868598L;

	/**
	 * ---- 用户id，必填。由服务器产生， 具体产生协议参见“自动注册”、“用户注册流程”和“用户登陆流程”部分。
	 */
	private String uid;

	/**
	 * ---- 客户端版本号，必填.格式为：n.m.x, 比如：1.3.2
	 */
	private String ver;

	/**
	 * --- 客户端渠道号，必填。渠道号中可以带中文，例如：T001官网V132
	 */
	private String channel;

	/**
	 * ---- 验证签名，必填，用以保证服务器区分是否是正常客户端的请求。key的计算规则如下： key =
	 * md5(uid+SecrectKey+ver+channel)，其中SecrectKey为同时保存在客户端和服务端的一个密钥，其值详见代码。
	 */
	private String key;

	/**
	 * --- 用于区分客户端属于Android A版本（va）还是Android
	 * B版本（vb）。IOS版本其值为IOS。可选参数，默认值为Android A版本（va）
	 */
	private String tagversion;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTagversion() {
		return tagversion;
	}

	public void setTagversion(String tagversion) {
		this.tagversion = tagversion;
	}

}