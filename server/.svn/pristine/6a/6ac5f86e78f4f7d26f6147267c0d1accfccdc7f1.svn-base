package com.cheyooh.service.sdk.idata.cmccmm;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.nuxeo.common.xmap.XMap;
import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XNodeList;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("AppQueryResponse")
public class XmlCmccmmAppQueryResponse {
	@XNode(value = "TransactionId")
	private String TransactionId = "";
	
	@XNode(value = "RspCode")
	private String RspCode = "";
	
	@XNode(value = "RspDesc")
	private String RspDesc = "";
	
	@XNodeList(componentType = XmlCmccmmAppInfoSchema.class, type = ArrayList.class, value = "AppInfo")
	private List<XmlCmccmmAppInfoSchema> AppInfo ;
	
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

	public List<XmlCmccmmAppInfoSchema> getAppInfo() {
		return AppInfo;
	}

	public void setAppInfo(List<XmlCmccmmAppInfoSchema> appInfo) {
		AppInfo = appInfo;
	}

	@Override
	public String toString() {
		return "XmlCmccmmAppQueryResponse [TransactionId=" + TransactionId
				+ ", RspCode=" + RspCode + ", RspDesc=" + RspDesc
				+ ", AppInfo=" + AppInfo + "]";
	}

	public static void main(String[] args) {
//		<?xml version="1.0" encoding="UTF-8"?>
//		<AppQueryResponse>
//		  <TransactionId>1605090917560101</TransactionId>
//		  <RspCode>0</RspCode>
//		  <RspDesc><![CDATA[接收成功！]]></RspDesc>
//		  <AppInfo>
//		    <APPID>300005607603</APPID>
//		    <Status><![CDATA[资料审核中]]></Status>
//		    <StatusNumber>01</StatusNumber>
//		    <Name><![CDATA[测试应用]]></Name>
//		    <AppDesc><![CDATA[应用介绍]]></AppDesc>
//		    <ContentFee>0</ContentFee>
//		    <ContentType><![CDATA[应用游戏]]></ContentType>
//		    <Type><![CDATA[策略]]></Type>
//		    <KeyWords><![CDATA[]]></KeyWords>
//		    <Version>1</Version>
//		    <Language>1</Language>
//		    <BussDate></BussDate>
//		    <Desc></Desc>
//		    <ProgramResults>
//		      <ProgramResult>
//		        <ProgramID>3069096</ProgramID>
//		        <Type>01</Type>
//		        <Status>01</Status>
//		        <SelfProgramStatus>05</SelfProgramStatus>
//		        <Program></Program>
//		        <Desc></Desc>
//		        <PackageName><![CDATA[com.aspire.demo]]></PackageName>
//		        <VersionCode>1</VersionCode>
//		        <VersionName>1.0</VersionName>
//		        <CopyRightXML></CopyRightXML>
//		        <MMIAPXML></MMIAPXML>
//		        <Md5Code>ECBBB444042E2A4C9E020B498FDF979F</Md5Code>
//		        <Changelocked>0</Changelocked>
//		      </ProgramResult>
//		    </ProgramResults>
//		    <PayCodeResults>
//		      <PayCodeResult>
//		        <PayCodeName><![CDATA[1元道具]]></PayCodeName>
//		        <PayCodeType>02</PayCodeType>
//		        <Days></Days>
//		        <Fee>200</Fee>
//		        <PayCode>30000560760301</PayCode>
//		        <SyncsubsURL><![CDATA[http://dev.leyogame.cn/api/m/CmccmmSyncAppOrderNotify]]></SyncsubsURL>
//		      </PayCodeResult>
//		      <PayCodeResult>
//		        <PayCodeName><![CDATA[2元道具]]></PayCodeName>
//		        <PayCodeType>02</PayCodeType>
//		        <Days></Days>
//		        <Fee>1000</Fee>
//		        <PayCode>30000560760302</PayCode>
//		        <SyncsubsURL><![CDATA[http://dev.leyogame.cn/api/m/CmccmmSyncAppOrderNotify]]></SyncsubsURL>
//		      </PayCodeResult>
//		    </PayCodeResults>
//		  </AppInfo>
//		</AppQueryResponse>
		
		try {
			String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
					+ "<AppQueryResponse>\n"
					+ "   <TransactionId>1605090917560101</TransactionId>\n"
					+ "   <RspCode>0</RspCode>\n"
					+ "   <RspDesc><![CDATA[接收成功！]]></RspDesc>\n"
					+ "   <AppInfo>\n"
					+ "   	<APPID>300005607603</APPID>\n"
					+ "   	<Status><![CDATA[资料审核中]]></Status>\n"
					+ "   	<StatusNumber>01</StatusNumber>\n"
					+ "   	<Name><![CDATA[测试应用]]></Name>\n"
					+ "   	<AppDesc><![CDATA[应用介绍]]></AppDesc>\n"
					+ "   	<ContentFee>0</ContentFee>\n"
					+ "   	<ContentType><![CDATA[应用游戏]]></ContentType>\n"
					+ "   	<Type><![CDATA[策略]]></Type>\n"
					+ "   	<KeyWords><![CDATA[]]></KeyWords>\n" 
					+ "     <Version>1</Version>\n"
					+ "     <Language>1</Language>\n"
					+ "     <BussDate></BussDate>\n"
					+ "     <Desc></Desc>\n"
					+ "     <ProgramResults>\n"
					+ "     	<ProgramResult>\n"
					+ "     		<ProgramID>3069096</ProgramID>\n"
					+ "     		<Type>01</Type>\n"
					+ "     		<Status>01</Status>\n"
					+ "     		<SelfProgramStatus>05</SelfProgramStatus>\n"
					+ "     		<Program></Program>\n"
					+ "     		<Desc></Desc>\n"
					+ "     		<PackageName><![CDATA[com.aspire.demo]]></PackageName>\n"
					+ "     		<VersionCode>1</VersionCode>\n"
					+ "     		<VersionName>1.0</VersionName>\n"
					+ "     		<CopyRightXML></CopyRightXML>\n"
					+ "     		<MMIAPXML></MMIAPXML>\n"
					+ "     		<Md5Code>ECBBB444042E2A4C9E020B498FDF979F</Md5Code>\n"
					+ "     		<Changelocked>0</Changelocked>\n"
					+ "     	</ProgramResult>\n"
					+ "     </ProgramResults>\n"
					+ "     <PayCodeResults>\n"
					+ "     	<PayCodeResult>\n"
					+ "     		<PayCodeName><![CDATA[1元道具]]></PayCodeName>\n"
					+ "     		<PayCodeType>02</PayCodeType>\n"
					+ "     		<Days></Days>\n"
					+ "     		<Fee>200</Fee>\n"
					+ "     		<PayCode>30000560760301</PayCode>\n"
					+ "     		<SyncsubsURL><![CDATA[http://dev.leyogame.cn/api/m/CmccmmSyncAppOrderNotify]]></SyncsubsURL>\n"
					+ "     	</PayCodeResult>\n"
					+ "     	<PayCodeResult>\n"
					+ "     		<PayCodeName><![CDATA[2元道具]]></PayCodeName>\n"
					+ "     		<PayCodeType>02</PayCodeType>\n"
					+ "     		<Days></Days>\n"
					+ "     		<Fee>1000</Fee>\n"
					+ "     		<PayCode>30000560760302</PayCode>\n"
					+ "     		<SyncsubsURL><![CDATA[http://dev.leyogame.cn/api/m/CmccmmSyncAppOrderNotify]]></SyncsubsURL>\n"
					+ "     	</PayCodeResult>\n"
					+ "     </PayCodeResults>\n"
					+ "    </AppInfo>\n"
					+ "</AppQueryResponse>";
			

			XmlCmccmmAppQueryResponse xmlCmccmmAppQueryResponse = new XmlCmccmmAppQueryResponse();
			XMap xmapresponse = new XMap();
			xmapresponse.register(XmlCmccmmAppQueryResponse.class);
			ByteArrayInputStream in;

			in = new ByteArrayInputStream(content.getBytes("UTF-8"));
			xmlCmccmmAppQueryResponse = (XmlCmccmmAppQueryResponse) xmapresponse.load(in);
			System.out.println(xmlCmccmmAppQueryResponse);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
