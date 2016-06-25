package com.cheyooh.service.sdk.idata.cmccmm;

import java.util.ArrayList;
import java.util.List;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XNodeList;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("UploadPgRequest")
public class XmlCmccmmUploadPgRequest {
	@XNode(value = "TransactionId")
	private String TransactionId = "";
	  
	  @XNode(value = "PltID")
	  private String PltID="";
	  
	  @XNode(value = "MD5sign")
	  private String MD5sign="";
	  
	  @XNode(value = "APPID")
	  private String APPID="";
	  
	  @XNode(value = "PID")
	  private String PID="";
	  
	  @XNode(value = "ProgramUrl")
	  private String ProgramUrl="";
	  
	  @XNode(value = "FileSize")
	  private String FileSize="";
	  
	  @XNode(value = "FileCRC32")
	  private String FileCRC32="";
	  
	  @XNode(value = "PublishToMM")
	  private String PublishToMM="";
	  
	  @XNodeList(componentType = XmlCmccmmPgExtSchema.class, type = ArrayList.class, value = "PgExtInfo")
	  private List<XmlCmccmmPgExtSchema> PgExtInfo;

	public String getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(String transactionId) {
		TransactionId = transactionId;
	}

	public String getPltID() {
		return PltID;
	}

	public void setPltID(String pltID) {
		PltID = pltID;
	}

	public String getMD5sign() {
		return MD5sign;
	}

	public void setMD5sign(String mD5sign) {
		MD5sign = mD5sign;
	}

	public String getAPPID() {
		return APPID;
	}

	public void setAPPID(String aPPID) {
		APPID = aPPID;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	public String getProgramUrl() {
		return ProgramUrl;
	}

	public void setProgramUrl(String programUrl) {
		ProgramUrl = programUrl;
	}

	public String getFileSize() {
		return FileSize;
	}

	public void setFileSize(String fileSize) {
		FileSize = fileSize;
	}

	public String getFileCRC32() {
		return FileCRC32;
	}

	public void setFileCRC32(String fileCRC32) {
		FileCRC32 = fileCRC32;
	}

	public String getPublishToMM() {
		return PublishToMM;
	}

	public void setPublishToMM(String publishToMM) {
		PublishToMM = publishToMM;
	}

	public List<XmlCmccmmPgExtSchema> getPgExtInfo() {
		return PgExtInfo;
	}

	public void setPgExtInfo(List<XmlCmccmmPgExtSchema> pgExtInfo) {
		PgExtInfo = pgExtInfo;
	}

	@Override
	public String toString() {
		return "XmlCmccmmUploadPgRequest [TransactionId=" + TransactionId
				+ ", PltID=" + PltID + ", MD5sign=" + MD5sign + ", APPID="
				+ APPID + ", PID=" + PID + ", ProgramUrl=" + ProgramUrl
				+ ", FileSize=" + FileSize + ", FileCRC32=" + FileCRC32
				+ ", PublishToMM=" + PublishToMM + ", PgExtInfo=" + PgExtInfo
				+ "]";
	}
	  
}
