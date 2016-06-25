package com.cheyooh.service.sdk.idata.notify;

import com.cheyooh.service.framework.idata.Cmd;

/**
 * 
 * 易宝-非银行卡专业版（组合支付）支付结果返回参数列表
 * 
 * @author zhouzg@cheyooh.com
 *
 */
public class CmdYeepayOrder extends Cmd{ 
	private static final long serialVersionUID = 7595253953355112783L;
	
	/**
	 *   业务类型  Max(20) 固定值 ”ChargeCardDirect”. 1
	 */
	private String r0_Cmd; 
	
	/**
	 *   支付结果   值为“1”, 代表支付成功.非“1”失败。 2
	 */
	private int r1_Code;
	
	/**
	 *   商户编号  Max(11) 商户在易宝支付系统的唯一身份标识.获取方式见“如何获得商户编号” 3
	 */
	private String p1_MerId; 
	
	/**
	 *   商户订单号  Max(50) 易宝支付返回商户订单号 4
	 */
	private String p2_Order; 
	
	/**
	 * 成功金额  Max(20) 保留两位小数,不足两位小数的将保留一位！(如 0.10 将返回0.1, 0会返回0.0) . 商户收到该返回数据后,一定用自己数据库中存储的金额与该金额进行比较. 5
	 */
	private float p3_Amt;
	 
	/**
	 *   支付方式  Max(10) 支付卡对应的支付渠道编码 6
	 */
	private String p4_FrpId; 
	
	/**
	 *   卡序列号组  Max(300) 多张卡以半角逗号分隔 7
	 */
	private String p5_CardNo;
	
	/**
	 *   确认金额组  Max(100) 卡支付的金额组，多张卡以半角逗号分隔.保留两位小数,不足两位小数的将保留一位！(如 0.10 将返回0.1, 0会返回0.0). 8
	 */
	private String p6_confirmAmount; 
	 
	/**
	 *   实际金额组  Max(100) 卡原有的金额组，多张卡以半角逗号分隔.保留两位小数,不足两位小数的将保留一位！(如 0.10 将返回0.1, 0会返回0.0). 9
	 */
	private String p7_realAmount; 
	

	/**
	 *   卡状态组  Max(100) 0：销卡成功，订单成功
	1：销卡成功，订单失败
	7：卡号卡密或卡面额不符合规则
	1002：本张卡密您提交过于频繁，请您稍后再试
	1003：不支持的卡类型（比如电信地方卡）
	1004：密码错误或充值卡无效
	1006：充值卡无效
	1007：卡内余额不足
	1008：余额卡过期（有效期1个月）
	1010：此卡正在处理中
	10000：未知错误
	2005：此卡已使用
	2006：卡密在系统处理中
	2007：该卡为假卡
	2008：该卡种正在维护
	2009：浙江省移动维护
	2010：江苏省移动维护
	2011：福建省移动维护
	2012：辽宁省移动维护
	2013：该卡已被锁定
	2014：系统繁忙，请稍后再试

	下面为易宝e卡通返回的错误代码
	3001：卡不存在
	3002：卡已使用过
	3003：卡已作废
	3004：卡已冻结
	3005：卡未激活
	3006：密码不正确
	3007：卡正在处理中
	3101：系统错误
	3102：卡已过期
	 */
	private String p8_cardStatus;
	
	/**
	 * 
	 * 扩展信息  Max(100) 返回提交的扩展信息 11
	 */	  
	private String p9_MP; 
	 
	/**
	 *   支付余额   注：此项仅为订单成功,并且需要订单较验时才会有值。失败订单的余额返部返回原卡密中 12
	 */
	private String pb_BalanceAmt; 
	
	/**
	 *   余额卡号   注：此项仅为订单成功,并且需要订单较验时才会有值 13
	 */
	private String pc_BalanceAct;
	
	 /**
	  * 签名数据  Max(32) 产生hmac需要两个参数，并调用相关API.
	       参数1: STR，列表中的参数值按照签名顺序拼接所产生的字符串，注意null要转换为 ””，并确保无乱码.
		参数2: 商户密钥.见"如何获得商户密钥"
		各语言范例已经提供封装好了的方法用于生成此参数。
		如果以上两个参数有错误，则该参数必然错误，见"抱歉,交易签名无效." 
	  */
	private String hmac;

	public String getR0_Cmd() {
		return r0_Cmd;
	}

	public void setR0_Cmd(String r0_Cmd) {
		this.r0_Cmd = r0_Cmd;
	}

	public int getR1_Code() {
		return r1_Code;
	}

	public void setR1_Code(int r1_Code) {
		this.r1_Code = r1_Code;
	}

	public String getP1_MerId() {
		return p1_MerId;
	}

	public void setP1_MerId(String p1_MerId) {
		this.p1_MerId = p1_MerId;
	}

	public String getP2_Order() {
		return p2_Order;
	}

	public void setP2_Order(String p2_Order) {
		this.p2_Order = p2_Order;
	}

	public float getP3_Amt() {
		return p3_Amt;
	}

	public void setP3_Amt(float p3_Amt) {
		this.p3_Amt = p3_Amt;
	}

	public String getP4_FrpId() {
		return p4_FrpId;
	}

	public void setP4_FrpId(String p4_FrpId) {
		this.p4_FrpId = p4_FrpId;
	}

	public String getP5_CardNo() {
		return p5_CardNo;
	}

	public void setP5_CardNo(String p5_CardNo) {
		this.p5_CardNo = p5_CardNo;
	}

	public String getP6_confirmAmount() {
		return p6_confirmAmount;
	}

	public void setP6_confirmAmount(String p6_confirmAmount) {
		this.p6_confirmAmount = p6_confirmAmount;
	}

	public String getP7_realAmount() {
		return p7_realAmount;
	}

	public void setP7_realAmount(String p7_realAmount) {
		this.p7_realAmount = p7_realAmount;
	}

	public String getP8_cardStatus() {
		return p8_cardStatus;
	}

	public void setP8_cardStatus(String p8_cardStatus) {
		this.p8_cardStatus = p8_cardStatus;
	}

	public String getP9_MP() {
		return p9_MP;
	}

	public void setP9_MP(String p9_MP) {
		this.p9_MP = p9_MP;
	}

	public String getPb_BalanceAmt() {
		return pb_BalanceAmt;
	}

	public void setPb_BalanceAmt(String pb_BalanceAmt) {
		this.pb_BalanceAmt = pb_BalanceAmt;
	}

	public String getPc_BalanceAct() {
		return pc_BalanceAct;
	}

	public void setPc_BalanceAct(String pc_BalanceAct) {
		this.pc_BalanceAct = pc_BalanceAct;
	}

	public String getHmac() {
		return hmac;
	}

	public void setHmac(String hmac) {
		this.hmac = hmac;
	}
	

}
