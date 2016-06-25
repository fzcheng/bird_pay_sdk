package com.cheyooh.service.sdk.idata.gameserver;

import org.nuxeo.common.xmap.annotation.XNode;

/**
 * @author Ljg
 * 
 */
public class XmlCmccGamebaseNotifyReq {
	
	@XNode(value = "userId")
	private String userId;// 用户伪码

	@XNode(value = "contentId")
	private String contentId;// 计费代码
	
	@XNode(value = "consumeCode")
	private String consumeCode;// 道具计费代码

	@XNode(value = "cpId")
	private String cpId;// 合作代码
	
	@XNode(value = "hRet")
	private String hRet;// 平台计费结果（状态码外码）0-成功 其他-失败

	@XNode(value = "status")
	private String status;// 返回状态（内码）
	
	@XNode(value = "versionId")
	private String versionId;// SDK版本号
	
	@XNode(value = "cpparam")
	private String cpparam;// CP透传参数

	@XNode(value = "packageID")
	private String packageID;// 套餐包ID(非局数据ID)

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getConsumeCode() {
		return consumeCode;
	}

	public void setConsumeCode(String consumeCode) {
		this.consumeCode = consumeCode;
	}

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public String gethRet() {
		return hRet;
	}

	public void sethRet(String hRet) {
		this.hRet = hRet;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getCpparam() {
		return cpparam;
	}

	public void setCpparam(String cpparam) {
		this.cpparam = cpparam;
	}

	public String getPackageID() {
		return packageID;
	}

	public void setPackageID(String packageID) {
		this.packageID = packageID;
	}

	@Override
	public String toString() {
		return "XmlCmccGamebaseNotifyReq [userId=" + userId + ", contentId="
				+ contentId + ", consumeCode=" + consumeCode + ", cpId=" + cpId
				+ ", hRet=" + hRet + ", status=" + status + ", versionId="
				+ versionId + ", cpparam=" + cpparam + ", packageID="
				+ packageID + "]";
	}

}
