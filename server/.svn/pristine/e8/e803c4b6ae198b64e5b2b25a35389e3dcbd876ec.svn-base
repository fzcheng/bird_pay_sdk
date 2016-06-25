package com.cheyooh.service.sdk.idata.gameserver;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("request")
public class XmlCmccjidiIosResp {
	private static final String XML_RESP = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><request><userId>%s</userId><contentId>%s</contentId><consumeCode>%s</consumeCode><cpid>%s</cpid><hRet>%s</hRet><versionId>%s</versionId></request>";

	@XNode(value = "userId")
	private String userId = "";// 用户ID

	@XNode(value = "contentId")
	private String contentId = "";// 游戏ID

	@XNode(value = "consumeCode")
	private String consumeCode = "";// 道具id

	@XNode(value = "cpid")
	private String cpid = "";// cpid

	@XNode(value = "hRet")
	private int hRet;// 返回值

	@XNode(value = "versionId")
	private String versionId = "";// 版本号

	@XNode(value = "cpparam")
	private String cpparam = "";//

	@XNode(value = "packageID")
	private String packageID = "";//

	public XmlCmccjidiIosResp() {

	}

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

	public String getCpid() {
		return cpid;
	}

	public void setCpid(String cpid) {
		this.cpid = cpid;
	}

	public int gethRet() {
		return hRet;
	}

	public void sethRet(int hRet) {
		this.hRet = hRet;
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

	public String toXml() {
		return String.format(XML_RESP, userId, contentId, consumeCode, cpid,
				hRet, versionId);
	}

	public static void main(String[] args) {
		System.out.println(new XmlCmccjidiIosResp().toXml());
	}

}
