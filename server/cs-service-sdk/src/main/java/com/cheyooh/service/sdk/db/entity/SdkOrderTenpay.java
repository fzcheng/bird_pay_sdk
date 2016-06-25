package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkOrderTenpay {
    /**<br/>
     * 字段: sdk_order_tenpay.pay_id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer payId;

    /**<br/>
     * 字段: sdk_order_tenpay.req_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 请求时间
     */
    private Date reqTime;

    /**<br/>
     * 字段: sdk_order_tenpay.req_ver<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 版本号,ver 默认值是1.0。目前版本ver 取值应为2.0
     */
    private String reqVer;

    /**<br/>
     * 字段: sdk_order_tenpay.req_sale_plat<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 必填 请求来源，填211
     */
    private String reqSalePlat;

    /**<br/>
     * 字段: sdk_order_tenpay.req_charset<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 可选 1 ：UTF-8, 2 ：GB2312, 默认为1
     */
    private String reqCharset;

    /**<br/>
     * 字段: sdk_order_tenpay.req_bank_type<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 银行类型:财付通支付填0
     */
    private Integer reqBankType;

    /**<br/>
     * 字段: sdk_order_tenpay.req_desc<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 商品描述,32 个字符以内
     */
    private String reqDesc;

    /**<br/>
     * 字段: sdk_order_tenpay.req_purchaser_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 用户(买方)的财付通帐户(QQ 或EMAIL)。
     */
    private String reqPurchaserId;

    /**<br/>
     * 字段: sdk_order_tenpay.req_bargainor_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 商户号,由财付通统一分配的10 位正整数(120XXXXXXX)号
     */
    private String reqBargainorId;

    /**<br/>
     * 字段: sdk_order_tenpay.req_sp_billno<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 商户系统内部的定单号,32 个字符内的字母和数字
     */
    private String reqSpBillno;

    /**<br/>
     * 字段: sdk_order_tenpay.req_total_fee<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 总金额,以分为单位,不允许包含任何字、符号
     */
    private Float reqTotalFee;

    /**<br/>
     * 字段: sdk_order_tenpay.req_fee_type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 现金支付币种,目前只支持人民币,默认值是1-人民币
     */
    private Integer reqFeeType;

    /**<br/>
     * 字段: sdk_order_tenpay.req_notify_url<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 接收财付通通知的URL,需给绝对路径，255 字符内
     */
    private String reqNotifyUrl;

    /**<br/>
     * 字段: sdk_order_tenpay.req_attach<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 必填 商户附加信息,可做扩展参数，255 字符内,在支付成功后原样返回给notify_url
     */
    private String reqAttach;

    /**<br/>
     * 字段: sdk_order_tenpay.req_time_start<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 订单生成时间，格式为yyyymmddhhmmss，如2009 年12 月25 日9 点10 分10 秒表示为20091225091010。时区为GMT+8 beijing。该时间取自商户服务器
     */
    private String reqTimeStart;

    /**<br/>
     * 字段: sdk_order_tenpay.req_time_expire<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 订单失效时间，格式为yyyymmddhhmmss，如2009 年12 月27 日9 点10 分10 秒表示为20091227091010。时区为GMT+8 beijing。该时间取自商户服务器
     */
    private String reqTimeExpire;

    /**<br/>
     * 字段: sdk_order_tenpay.resp_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 响应时间
     */
    private Date respTime;

    /**<br/>
     * 字段: sdk_order_tenpay.resp_err_info<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 响应的错误信息
     */
    private String respErrInfo;

    /**<br/>
     * 字段: sdk_order_tenpay.resp_token_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 财付通生成的订单token_id
     */
    private String respTokenId;

    /**<br/>
     * 字段: sdk_order_tenpay.notify_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 通知时间
     */
    private Date notifyTime;

    /**<br/>
     * 字段: sdk_order_tenpay.notify_ver<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 版本号,ver 默认值是1.0。目前版本ver 取值应为2.0
     */
    private String notifyVer;

    /**<br/>
     * 字段: sdk_order_tenpay.notify_charset<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 1 UTF-8, 2 GB2312
     */
    private String notifyCharset;

    /**<br/>
     * 字段: sdk_order_tenpay.notify_bank_type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 银行类型:财付通支付填0
     */
    private Integer notifyBankType;

    /**<br/>
     * 字段: sdk_order_tenpay.notify_bank_billno<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 银行订单号，若为财付通余额支付则为空
     */
    private String notifyBankBillno;

    /**<br/>
     * 字段: sdk_order_tenpay.notify_pay_result<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 支付结果： 0—成功；其它—失败
     */
    private Integer notifyPayResult;

    /**<br/>
     * 字段: sdk_order_tenpay.notify_pay_info<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 支付结果信息，支付成功时为空
     */
    private String notifyPayInfo;

    /**<br/>
     * 字段: sdk_order_tenpay.notify_purchase_alias<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 买家唯一标识，由财付通生成。注意不同于purchase_id 财付通帐户。
     */
    private String notifyPurchaseAlias;

    /**<br/>
     * 字段: sdk_order_tenpay.notify_bargainor_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 卖方账号（商户spid）
     */
    private String notifyBargainorId;

    /**<br/>
     * 字段: sdk_order_tenpay.notify_transaction_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 财付通交易号(订单号)
     */
    private String notifyTransactionId;

    /**<br/>
     * 字段: sdk_order_tenpay.notify_sp_billno<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 商户系统内部的定单号，此参数仅在对账时提供。
     */
    private String notifySpBillno;

    /**<br/>
     * 字段: sdk_order_tenpay.notify_total_fee<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 订单总金额，以分为单位
     */
    private Float notifyTotalFee;

    /**<br/>
     * 字段: sdk_order_tenpay.notify_fee_type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 现金支付币种
     */
    private String notifyFeeType;

    /**<br/>
     * 字段: sdk_order_tenpay.notify_attach<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 商家数据包，原样返回
     */
    private String notifyAttach;

    /**<br/>
     * 字段: sdk_order_tenpay.notify_time_end<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 支付完成时间，格式为yymmddhhmmss，如2009 年12 月27 日9 点10分10 秒表示为20091227091010。时区为GMT+8 beijing。该时间取自财付通服务器
     */
    private String notifyTimeEnd;

    /**
     * @return sdk_order_tenpay.pay_id: 
     */
    public Integer getPayId() {
        return payId;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.pay_id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param payId: 
     */
    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    /**
     * @return sdk_order_tenpay.req_time: 请求时间
     */
    public Date getReqTime() {
        return reqTime;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.req_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param reqTime: 请求时间
     */
    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    /**
     * @return sdk_order_tenpay.req_ver: 版本号,ver 默认值是1.0。目前版本ver 取值应为2.0
     */
    public String getReqVer() {
        return reqVer;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.req_ver<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param reqVer: 版本号,ver 默认值是1.0。目前版本ver 取值应为2.0
     */
    public void setReqVer(String reqVer) {
        this.reqVer = reqVer;
    }

    /**
     * @return sdk_order_tenpay.req_sale_plat: 必填 请求来源，填211
     */
    public String getReqSalePlat() {
        return reqSalePlat;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.req_sale_plat<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param reqSalePlat: 必填 请求来源，填211
     */
    public void setReqSalePlat(String reqSalePlat) {
        this.reqSalePlat = reqSalePlat;
    }

    /**
     * @return sdk_order_tenpay.req_charset: 可选 1 ：UTF-8, 2 ：GB2312, 默认为1
     */
    public String getReqCharset() {
        return reqCharset;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.req_charset<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param reqCharset: 可选 1 ：UTF-8, 2 ：GB2312, 默认为1
     */
    public void setReqCharset(String reqCharset) {
        this.reqCharset = reqCharset;
    }

    /**
     * @return sdk_order_tenpay.req_bank_type: 银行类型:财付通支付填0
     */
    public Integer getReqBankType() {
        return reqBankType;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.req_bank_type<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param reqBankType: 银行类型:财付通支付填0
     */
    public void setReqBankType(Integer reqBankType) {
        this.reqBankType = reqBankType;
    }

    /**
     * @return sdk_order_tenpay.req_desc: 商品描述,32 个字符以内
     */
    public String getReqDesc() {
        return reqDesc;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.req_desc<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param reqDesc: 商品描述,32 个字符以内
     */
    public void setReqDesc(String reqDesc) {
        this.reqDesc = reqDesc;
    }

    /**
     * @return sdk_order_tenpay.req_purchaser_id: 用户(买方)的财付通帐户(QQ 或EMAIL)。
     */
    public String getReqPurchaserId() {
        return reqPurchaserId;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.req_purchaser_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param reqPurchaserId: 用户(买方)的财付通帐户(QQ 或EMAIL)。
     */
    public void setReqPurchaserId(String reqPurchaserId) {
        this.reqPurchaserId = reqPurchaserId;
    }

    /**
     * @return sdk_order_tenpay.req_bargainor_id: 商户号,由财付通统一分配的10 位正整数(120XXXXXXX)号
     */
    public String getReqBargainorId() {
        return reqBargainorId;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.req_bargainor_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * @param reqBargainorId: 商户号,由财付通统一分配的10 位正整数(120XXXXXXX)号
     */
    public void setReqBargainorId(String reqBargainorId) {
        this.reqBargainorId = reqBargainorId;
    }

    /**
     * @return sdk_order_tenpay.req_sp_billno: 商户系统内部的定单号,32 个字符内的字母和数字
     */
    public String getReqSpBillno() {
        return reqSpBillno;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.req_sp_billno<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param reqSpBillno: 商户系统内部的定单号,32 个字符内的字母和数字
     */
    public void setReqSpBillno(String reqSpBillno) {
        this.reqSpBillno = reqSpBillno;
    }

    /**
     * @return sdk_order_tenpay.req_total_fee: 总金额,以分为单位,不允许包含任何字、符号
     */
    public Float getReqTotalFee() {
        return reqTotalFee;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.req_total_fee<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param reqTotalFee: 总金额,以分为单位,不允许包含任何字、符号
     */
    public void setReqTotalFee(Float reqTotalFee) {
        this.reqTotalFee = reqTotalFee;
    }

    /**
     * @return sdk_order_tenpay.req_fee_type: 现金支付币种,目前只支持人民币,默认值是1-人民币
     */
    public Integer getReqFeeType() {
        return reqFeeType;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.req_fee_type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param reqFeeType: 现金支付币种,目前只支持人民币,默认值是1-人民币
     */
    public void setReqFeeType(Integer reqFeeType) {
        this.reqFeeType = reqFeeType;
    }

    /**
     * @return sdk_order_tenpay.req_notify_url: 接收财付通通知的URL,需给绝对路径，255 字符内
     */
    public String getReqNotifyUrl() {
        return reqNotifyUrl;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.req_notify_url<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * @param reqNotifyUrl: 接收财付通通知的URL,需给绝对路径，255 字符内
     */
    public void setReqNotifyUrl(String reqNotifyUrl) {
        this.reqNotifyUrl = reqNotifyUrl;
    }

    /**
     * @return sdk_order_tenpay.req_attach: 必填 商户附加信息,可做扩展参数，255 字符内,在支付成功后原样返回给notify_url
     */
    public String getReqAttach() {
        return reqAttach;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.req_attach<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * @param reqAttach: 必填 商户附加信息,可做扩展参数，255 字符内,在支付成功后原样返回给notify_url
     */
    public void setReqAttach(String reqAttach) {
        this.reqAttach = reqAttach;
    }

    /**
     * @return sdk_order_tenpay.req_time_start: 订单生成时间，格式为yyyymmddhhmmss，如2009 年12 月25 日9 点10 分10 秒表示为20091225091010。时区为GMT+8 beijing。该时间取自商户服务器
     */
    public String getReqTimeStart() {
        return reqTimeStart;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.req_time_start<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param reqTimeStart: 订单生成时间，格式为yyyymmddhhmmss，如2009 年12 月25 日9 点10 分10 秒表示为20091225091010。时区为GMT+8 beijing。该时间取自商户服务器
     */
    public void setReqTimeStart(String reqTimeStart) {
        this.reqTimeStart = reqTimeStart;
    }

    /**
     * @return sdk_order_tenpay.req_time_expire: 订单失效时间，格式为yyyymmddhhmmss，如2009 年12 月27 日9 点10 分10 秒表示为20091227091010。时区为GMT+8 beijing。该时间取自商户服务器
     */
    public String getReqTimeExpire() {
        return reqTimeExpire;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.req_time_expire<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param reqTimeExpire: 订单失效时间，格式为yyyymmddhhmmss，如2009 年12 月27 日9 点10 分10 秒表示为20091227091010。时区为GMT+8 beijing。该时间取自商户服务器
     */
    public void setReqTimeExpire(String reqTimeExpire) {
        this.reqTimeExpire = reqTimeExpire;
    }

    /**
     * @return sdk_order_tenpay.resp_time: 响应时间
     */
    public Date getRespTime() {
        return respTime;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.resp_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param respTime: 响应时间
     */
    public void setRespTime(Date respTime) {
        this.respTime = respTime;
    }

    /**
     * @return sdk_order_tenpay.resp_err_info: 响应的错误信息
     */
    public String getRespErrInfo() {
        return respErrInfo;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.resp_err_info<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param respErrInfo: 响应的错误信息
     */
    public void setRespErrInfo(String respErrInfo) {
        this.respErrInfo = respErrInfo;
    }

    /**
     * @return sdk_order_tenpay.resp_token_id: 财付通生成的订单token_id
     */
    public String getRespTokenId() {
        return respTokenId;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.resp_token_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param respTokenId: 财付通生成的订单token_id
     */
    public void setRespTokenId(String respTokenId) {
        this.respTokenId = respTokenId;
    }

    /**
     * @return sdk_order_tenpay.notify_time: 通知时间
     */
    public Date getNotifyTime() {
        return notifyTime;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.notify_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param notifyTime: 通知时间
     */
    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    /**
     * @return sdk_order_tenpay.notify_ver: 版本号,ver 默认值是1.0。目前版本ver 取值应为2.0
     */
    public String getNotifyVer() {
        return notifyVer;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.notify_ver<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param notifyVer: 版本号,ver 默认值是1.0。目前版本ver 取值应为2.0
     */
    public void setNotifyVer(String notifyVer) {
        this.notifyVer = notifyVer;
    }

    /**
     * @return sdk_order_tenpay.notify_charset: 1 UTF-8, 2 GB2312
     */
    public String getNotifyCharset() {
        return notifyCharset;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.notify_charset<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param notifyCharset: 1 UTF-8, 2 GB2312
     */
    public void setNotifyCharset(String notifyCharset) {
        this.notifyCharset = notifyCharset;
    }

    /**
     * @return sdk_order_tenpay.notify_bank_type: 银行类型:财付通支付填0
     */
    public Integer getNotifyBankType() {
        return notifyBankType;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.notify_bank_type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param notifyBankType: 银行类型:财付通支付填0
     */
    public void setNotifyBankType(Integer notifyBankType) {
        this.notifyBankType = notifyBankType;
    }

    /**
     * @return sdk_order_tenpay.notify_bank_billno: 银行订单号，若为财付通余额支付则为空
     */
    public String getNotifyBankBillno() {
        return notifyBankBillno;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.notify_bank_billno<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param notifyBankBillno: 银行订单号，若为财付通余额支付则为空
     */
    public void setNotifyBankBillno(String notifyBankBillno) {
        this.notifyBankBillno = notifyBankBillno;
    }

    /**
     * @return sdk_order_tenpay.notify_pay_result: 支付结果： 0—成功；其它—失败
     */
    public Integer getNotifyPayResult() {
        return notifyPayResult;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.notify_pay_result<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param notifyPayResult: 支付结果： 0—成功；其它—失败
     */
    public void setNotifyPayResult(Integer notifyPayResult) {
        this.notifyPayResult = notifyPayResult;
    }

    /**
     * @return sdk_order_tenpay.notify_pay_info: 支付结果信息，支付成功时为空
     */
    public String getNotifyPayInfo() {
        return notifyPayInfo;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.notify_pay_info<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param notifyPayInfo: 支付结果信息，支付成功时为空
     */
    public void setNotifyPayInfo(String notifyPayInfo) {
        this.notifyPayInfo = notifyPayInfo;
    }

    /**
     * @return sdk_order_tenpay.notify_purchase_alias: 买家唯一标识，由财付通生成。注意不同于purchase_id 财付通帐户。
     */
    public String getNotifyPurchaseAlias() {
        return notifyPurchaseAlias;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.notify_purchase_alias<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param notifyPurchaseAlias: 买家唯一标识，由财付通生成。注意不同于purchase_id 财付通帐户。
     */
    public void setNotifyPurchaseAlias(String notifyPurchaseAlias) {
        this.notifyPurchaseAlias = notifyPurchaseAlias;
    }

    /**
     * @return sdk_order_tenpay.notify_bargainor_id: 卖方账号（商户spid）
     */
    public String getNotifyBargainorId() {
        return notifyBargainorId;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.notify_bargainor_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param notifyBargainorId: 卖方账号（商户spid）
     */
    public void setNotifyBargainorId(String notifyBargainorId) {
        this.notifyBargainorId = notifyBargainorId;
    }

    /**
     * @return sdk_order_tenpay.notify_transaction_id: 财付通交易号(订单号)
     */
    public String getNotifyTransactionId() {
        return notifyTransactionId;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.notify_transaction_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param notifyTransactionId: 财付通交易号(订单号)
     */
    public void setNotifyTransactionId(String notifyTransactionId) {
        this.notifyTransactionId = notifyTransactionId;
    }

    /**
     * @return sdk_order_tenpay.notify_sp_billno: 商户系统内部的定单号，此参数仅在对账时提供。
     */
    public String getNotifySpBillno() {
        return notifySpBillno;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.notify_sp_billno<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param notifySpBillno: 商户系统内部的定单号，此参数仅在对账时提供。
     */
    public void setNotifySpBillno(String notifySpBillno) {
        this.notifySpBillno = notifySpBillno;
    }

    /**
     * @return sdk_order_tenpay.notify_total_fee: 订单总金额，以分为单位
     */
    public Float getNotifyTotalFee() {
        return notifyTotalFee;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.notify_total_fee<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param notifyTotalFee: 订单总金额，以分为单位
     */
    public void setNotifyTotalFee(Float notifyTotalFee) {
        this.notifyTotalFee = notifyTotalFee;
    }

    /**
     * @return sdk_order_tenpay.notify_fee_type: 现金支付币种
     */
    public String getNotifyFeeType() {
        return notifyFeeType;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.notify_fee_type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param notifyFeeType: 现金支付币种
     */
    public void setNotifyFeeType(String notifyFeeType) {
        this.notifyFeeType = notifyFeeType;
    }

    /**
     * @return sdk_order_tenpay.notify_attach: 商家数据包，原样返回
     */
    public String getNotifyAttach() {
        return notifyAttach;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.notify_attach<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * @param notifyAttach: 商家数据包，原样返回
     */
    public void setNotifyAttach(String notifyAttach) {
        this.notifyAttach = notifyAttach;
    }

    /**
     * @return sdk_order_tenpay.notify_time_end: 支付完成时间，格式为yymmddhhmmss，如2009 年12 月27 日9 点10分10 秒表示为20091227091010。时区为GMT+8 beijing。该时间取自财付通服务器
     */
    public String getNotifyTimeEnd() {
        return notifyTimeEnd;
    }

    /**<br/>
     * 字段: sdk_order_tenpay.notify_time_end<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param notifyTimeEnd: 支付完成时间，格式为yymmddhhmmss，如2009 年12 月27 日9 点10分10 秒表示为20091227091010。时区为GMT+8 beijing。该时间取自财付通服务器
     */
    public void setNotifyTimeEnd(String notifyTimeEnd) {
        this.notifyTimeEnd = notifyTimeEnd;
    }
}