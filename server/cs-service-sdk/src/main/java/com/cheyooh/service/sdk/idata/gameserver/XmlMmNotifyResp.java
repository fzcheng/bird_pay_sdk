package com.cheyooh.service.sdk.idata.gameserver;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

/**
 * @author lig
 */

@XObject("SyncAppOrderResp")
public class XmlMmNotifyResp {
	private static final String XML_RESP = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SyncAppOrderResp><TransactionID>%s</TransactionID><MsgType>%s</MsgType><Version>%s</Version><hRet>%s</hRet></SyncAppOrderResp>";

	@XNode(value = "TransactionID")
	private String TransactionID = "";// 交易编号
	
	@XNode(value = "MsgType")
	private String msgType = "";// 消息类型

	@XNode(value = "Version")
	private String version = "";// 版本号

	@XNode(value = "hRet")
	private int hRet;// 返回值

	public XmlMmNotifyResp(){
		
	}
	
	public String getTransactionID() {
		return TransactionID;
	}



	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}



	public String getMsgType() {
		return msgType;
	}



	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}



	public String getVersion() {
		return version;
	}



	public void setVersion(String version) {
		this.version = version;
	}



	public int gethRet() {
		return hRet;
	}



	public void sethRet(int hRet) {
		this.hRet = hRet;
	}

	public String toXml() {
	    return String.format(XML_RESP, TransactionID, msgType, version, hRet);
	  }

	public static void main(String[] args) {
		System.out.println(new XmlMmNotifyResp().toXml());
	}

}
