package com.cheyooh.service.sdk.idata.cmccmm;

import java.io.ByteArrayInputStream;

import org.nuxeo.common.xmap.XMap;
import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("TestOrderResponse")
public class XmlCmccmmTestOrderResponse {

	// <?xml version="1.0" encoding="UTF-8"?>
	// <TestOrderResponse>
	// <TransactionId>1605061709080101</TransactionId>
	// <RspCode>0</RspCode>
	// <RspDesc>QueryTestOrderReq success
	// companyCode=444670,appId=300005607603,payCode=30000560760310</RspDesc>
	// <TestOrderInfos>
	// <TestOrderInfo>
	// <PayCode>30000560760310</PayCode>
	// <PayCodeName><![CDATA[测试应用-20金币]]></PayCodeName>
	// <PayCodeType>购买类-可重复购买</PayCodeType>
	// <Mobile>13425134343</Mobile>
	// <TestOrderDate>2016-05-06 15:59:24</TestOrderDate>
	// <TestOrderResult><![CDATA[通过]]></TestOrderResult>
	// <TestOrderSyncStatus><![CDATA[同步成功]]></TestOrderSyncStatus>
	// </TestOrderInfo>
	// </TestOrderInfos>
	// </TestOrderResponse>

	private static final String XML_RESP = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<TestOrderResponse>\n"
			+ "   <TransactionId>1605061709080101</TransactionId>\n"
			+ "   <RspCode>0</RspCode>\n"
			+ "   <RspDesc>QueryTestOrderReq success companyCode=444670,appId=300005607603,payCode=30000560760310</RspDesc>\n"
			+ "   <TestOrderInfos>\n"
			+ "   	<TestOrderInfo>\n"
			+ "   	<PayCode>30000560760310</PayCode>\n"
			+ "   	<PayCodeName><![CDATA[测试应用-20金币]]></PayCodeName>\n"
			+ "   	<PayCodeType>购买类-可重复购买</PayCodeType>\n"
			+ "   	<Mobile>13425134343</Mobile>\n"
			+ "   	<TestOrderDate>2016-05-06 15:59:24</TestOrderDate>\n"
			+ "   	<TestOrderResult><![CDATA[通过]]></TestOrderResult>\n"
			+ "   	<TestOrderSyncStatus><![CDATA[同步成功]]></TestOrderSyncStatus>\n"
			+ "   	</TestOrderInfo>\n"
			+ "   </TestOrderInfos>\n"
			+ "</TestOrderResponse>";

	@XNode(value = "TransactionId")
	private String TransactionId = "";

	@XNode(value = "RspCode")
	private String RspCode = "";

	@XNode(value = "RspDesc")
	private String RspDesc = "";

	@XNode(value = "TestOrderInfos")
	private XmlCmccmmTestOrderInfo TestOrderInfos;

	public String getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(String transactionId) {
		TransactionId = transactionId;
	}

	public String getRspCode() {
		return RspCode;
	}

	public void setRspCode(String rspCode) {
		RspCode = rspCode;
	}

	public String getRspDesc() {
		return RspDesc;
	}

	public void setRspDesc(String rspDesc) {
		RspDesc = rspDesc;
	}


	public XmlCmccmmTestOrderInfo getTestOrderInfos() {
		return TestOrderInfos;
	}

	public void setTestOrderInfos(XmlCmccmmTestOrderInfo testOrderInfos) {
		TestOrderInfos = testOrderInfos;
	}

	@Override
	public String toString() {
		return "XmlCmccmmTestOrderResponse [TransactionId=" + TransactionId
				+ ", RspCode=" + RspCode + ", RspDesc=" + RspDesc
				+ ", TestOrderInfos=" + TestOrderInfos + "]";
	}

	public static void main(String[] args) {
		try {
			String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
					+ "<TestOrderResponse>\n"
					+ "   <TransactionId>1605061709080101</TransactionId>\n"
					+ "   <RspCode>0</RspCode>\n"
					+ "   <RspDesc>QueryTestOrderReq success companyCode=444670,appId=300005607603,payCode=30000560760310</RspDesc>\n"
					+ "   <TestOrderInfos>\n"
					+ "   	<TestOrderInfo>\n"
					+ "   	<PayCode>30000560760310</PayCode>\n"
					+ "   	<PayCodeName>&lt;![CDATA[测试应用-20金币]]&gt;</PayCodeName>\n"
					+ "   	<PayCodeType>购买类-可重复购买</PayCodeType>\n"
					+ "   	<Mobile>13425134343</Mobile>\n"
					+ "   	<TestOrderDate>2016-05-06 15:59:24</TestOrderDate>\n"
					+ "   	<TestOrderResult>&lt;![CDATA[通过]]&gt;</TestOrderResult>\n"
					+ "   	<TestOrderSyncStatus>&lt;![CDATA[同步成功]]&gt;</TestOrderSyncStatus>\n"
					+ "   	</TestOrderInfo>\n" 
					+ "   </TestOrderInfos>\n"
					+ "</TestOrderResponse>";

			XmlCmccmmTestOrderResponse xmlCmccmmTestOrderResponse = new XmlCmccmmTestOrderResponse();
			XMap xmapresponse = new XMap();
			xmapresponse.register(XmlCmccmmTestOrderResponse.class);
			ByteArrayInputStream in;

			in = new ByteArrayInputStream(content.getBytes("UTF-8"));
			xmlCmccmmTestOrderResponse = (XmlCmccmmTestOrderResponse) xmapresponse
					.load(in);
			System.out.println(xmlCmccmmTestOrderResponse.toString());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}
