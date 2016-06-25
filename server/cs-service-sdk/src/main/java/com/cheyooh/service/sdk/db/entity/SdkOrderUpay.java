package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkOrderUpay {
    /**<br/>
     * 字段: sdk_order_upay.pay_id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer payId;

    /**<br/>
     * 字段: sdk_order_upay.req_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 请求时间
     */
    private Date reqTime;

    /**<br/>
     * 字段: sdk_order_upay.req_product_name<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 60<br/>
     * 说明: 商品名称
     */
    private String reqProductName;

    /**<br/>
     * 字段: sdk_order_upay.req_point<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 交易金额
     */
    private Float reqPoint;

    /**<br/>
     * 字段: sdk_order_upay.req_extraInfo<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 扩展信息
     */
    private String reqExtrainfo;

    /**<br/>
     * 字段: sdk_order_upay.req_timeout<br/>
     * 可空: true<br/>
     * 缺省: 60<br/>
     * 说明: 设置超时
     */
    private Integer reqTimeout;

    /**<br/>
     * 字段: sdk_order_upay.req_description<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 商品描述
     */
    private String reqDescription;

    /**<br/>
     * 字段: sdk_order_upay.req_showPayResult<br/>
     * 可空: true<br/>
     * 缺省: b'1'<br/>
     * 说明: 是否显示支付结果
     */
    private Boolean reqShowpayresult;

    /**<br/>
     * 字段: sdk_order_upay.notify_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 通知时间
     */
    private Date notifyTime;

    /**<br/>
     * 字段: sdk_order_upay.notify_tradeId<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 交易序号
     */
    private String notifyTradeid;

    /**<br/>
     * 字段: sdk_order_upay.notify_point<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 账单点数
     */
    private Integer notifyPoint;

    /**<br/>
     * 字段: sdk_order_upay.notify_amount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 请求成功点数
     */
    private Integer notifyAmount;

    /**<br/>
     * 字段: sdk_order_upay.notify_tradInfo<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 自定参数
     */
    private String notifyTradinfo;

    /**<br/>
     * 字段: sdk_order_upay.notify_ts<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 时间戳
     */
    private String notifyTs;

    /**<br/>
     * 字段: sdk_order_upay.notify_trade_status<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 订单状态
     */
    private String notifyTradeStatus;

    /**
     * @return sdk_order_upay.pay_id: 
     */
    public Integer getPayId() {
        return payId;
    }

    /**<br/>
     * 字段: sdk_order_upay.pay_id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param payId: 
     */
    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    /**
     * @return sdk_order_upay.req_time: 请求时间
     */
    public Date getReqTime() {
        return reqTime;
    }

    /**<br/>
     * 字段: sdk_order_upay.req_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param reqTime: 请求时间
     */
    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    /**
     * @return sdk_order_upay.req_product_name: 商品名称
     */
    public String getReqProductName() {
        return reqProductName;
    }

    /**<br/>
     * 字段: sdk_order_upay.req_product_name<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 60<br/>
     * @param reqProductName: 商品名称
     */
    public void setReqProductName(String reqProductName) {
        this.reqProductName = reqProductName;
    }

    /**
     * @return sdk_order_upay.req_point: 交易金额
     */
    public Float getReqPoint() {
        return reqPoint;
    }

    /**<br/>
     * 字段: sdk_order_upay.req_point<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param reqPoint: 交易金额
     */
    public void setReqPoint(Float reqPoint) {
        this.reqPoint = reqPoint;
    }

    /**
     * @return sdk_order_upay.req_extraInfo: 扩展信息
     */
    public String getReqExtrainfo() {
        return reqExtrainfo;
    }

    /**<br/>
     * 字段: sdk_order_upay.req_extraInfo<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * @param reqExtrainfo: 扩展信息
     */
    public void setReqExtrainfo(String reqExtrainfo) {
        this.reqExtrainfo = reqExtrainfo;
    }

    /**
     * @return sdk_order_upay.req_timeout: 设置超时
     */
    public Integer getReqTimeout() {
        return reqTimeout;
    }

    /**<br/>
     * 字段: sdk_order_upay.req_timeout<br/>
     * 可空: true<br/>
     * 缺省: 60<br/>
     * @param reqTimeout: 设置超时
     */
    public void setReqTimeout(Integer reqTimeout) {
        this.reqTimeout = reqTimeout;
    }

    /**
     * @return sdk_order_upay.req_description: 商品描述
     */
    public String getReqDescription() {
        return reqDescription;
    }

    /**<br/>
     * 字段: sdk_order_upay.req_description<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * @param reqDescription: 商品描述
     */
    public void setReqDescription(String reqDescription) {
        this.reqDescription = reqDescription;
    }

    /**
     * @return sdk_order_upay.req_showPayResult: 是否显示支付结果
     */
    public Boolean getReqShowpayresult() {
        return reqShowpayresult;
    }

    /**<br/>
     * 字段: sdk_order_upay.req_showPayResult<br/>
     * 可空: true<br/>
     * 缺省: b'1'<br/>
     * @param reqShowpayresult: 是否显示支付结果
     */
    public void setReqShowpayresult(Boolean reqShowpayresult) {
        this.reqShowpayresult = reqShowpayresult;
    }

    /**
     * @return sdk_order_upay.notify_time: 通知时间
     */
    public Date getNotifyTime() {
        return notifyTime;
    }

    /**<br/>
     * 字段: sdk_order_upay.notify_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param notifyTime: 通知时间
     */
    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    /**
     * @return sdk_order_upay.notify_tradeId: 交易序号
     */
    public String getNotifyTradeid() {
        return notifyTradeid;
    }

    /**<br/>
     * 字段: sdk_order_upay.notify_tradeId<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * @param notifyTradeid: 交易序号
     */
    public void setNotifyTradeid(String notifyTradeid) {
        this.notifyTradeid = notifyTradeid;
    }

    /**
     * @return sdk_order_upay.notify_point: 账单点数
     */
    public Integer getNotifyPoint() {
        return notifyPoint;
    }

    /**<br/>
     * 字段: sdk_order_upay.notify_point<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param notifyPoint: 账单点数
     */
    public void setNotifyPoint(Integer notifyPoint) {
        this.notifyPoint = notifyPoint;
    }

    /**
     * @return sdk_order_upay.notify_amount: 请求成功点数
     */
    public Integer getNotifyAmount() {
        return notifyAmount;
    }

    /**<br/>
     * 字段: sdk_order_upay.notify_amount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param notifyAmount: 请求成功点数
     */
    public void setNotifyAmount(Integer notifyAmount) {
        this.notifyAmount = notifyAmount;
    }

    /**
     * @return sdk_order_upay.notify_tradInfo: 自定参数
     */
    public String getNotifyTradinfo() {
        return notifyTradinfo;
    }

    /**<br/>
     * 字段: sdk_order_upay.notify_tradInfo<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * @param notifyTradinfo: 自定参数
     */
    public void setNotifyTradinfo(String notifyTradinfo) {
        this.notifyTradinfo = notifyTradinfo;
    }

    /**
     * @return sdk_order_upay.notify_ts: 时间戳
     */
    public String getNotifyTs() {
        return notifyTs;
    }

    /**<br/>
     * 字段: sdk_order_upay.notify_ts<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * @param notifyTs: 时间戳
     */
    public void setNotifyTs(String notifyTs) {
        this.notifyTs = notifyTs;
    }

    /**
     * @return sdk_order_upay.notify_trade_status: 订单状态
     */
    public String getNotifyTradeStatus() {
        return notifyTradeStatus;
    }

    /**<br/>
     * 字段: sdk_order_upay.notify_trade_status<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * @param notifyTradeStatus: 订单状态
     */
    public void setNotifyTradeStatus(String notifyTradeStatus) {
        this.notifyTradeStatus = notifyTradeStatus;
    }
}