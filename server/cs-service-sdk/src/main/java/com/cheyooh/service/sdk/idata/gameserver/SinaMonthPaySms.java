package com.cheyooh.service.sdk.idata.gameserver;

public class SinaMonthPaySms {
	   /*
	    * 产品名称,本域定义为发起支付的应用的产品名称,可选域
	    */
		private  String appName;
		/*
		 * 产品类型,本域定义了发起应用的产品类别,可选域
		 */
		private  String appType ;
		/*
		 * 商户订单号,本域定义商户的订单号，该号由商户上送，且在该商户中可唯一定义某笔订单,支付类交易，必选
		 */
		private String orderNum ;
		
		/*
		 * 商户订单金额
		 */
		private String orderAmt;
		
		private String sessionId;
		
		private String payURL;
		
		private String respCode;
		
		private String respMsg;
							
		/*
		 * 签名域,为防报文被篡改或抵赖，本域定义了由约定签名算法对交易过程中的关键信息和密钥进行签名,必填域
		 */
		private String sign ;

		public String getAppName() {
			return appName;
		}

		public void setAppName(String appName) {
			this.appName = appName;
		}

		public String getAppType() {
			return appType;
		}

		public void setAppType(String appType) {
			this.appType = appType;
		}

		public String getOrderNum() {
			return orderNum;
		}

		public void setOrderNum(String orderNum) {
			this.orderNum = orderNum;
		}

		public String getOrderAmt() {
			return orderAmt;
		}

		public void setOrderAmt(String orderAmt) {
			this.orderAmt = orderAmt;
		}

		public String getSessionId() {
			return sessionId;
		}

		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}

		public String getPayURL() {
			return payURL;
		}

		public void setPayURL(String payURL) {
			this.payURL = payURL;
		}

		public String getRespCode() {
			return respCode;
		}

		public void setRespCode(String respCode) {
			this.respCode = respCode;
		}

		public String getRespMsg() {
			return respMsg;
		}

		public void setRespMsg(String respMsg) {
			this.respMsg = respMsg;
		}

		public String getSign() {
			return sign;
		}

		public void setSign(String sign) {
			this.sign = sign;
		}
		
		
}
