package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkNotifyUcsasdk {
    /**<br/>
     * 字段: sdk_notify_ucsasdk.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 自增字段
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_notify_ucsasdk.tradeId<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: uc九游订单号
     */
    private String tradeid;

    /**<br/>
     * 字段: sdk_notify_ucsasdk.tradeTime<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 订单交易时间
     */
    private Date tradetime;

    /**<br/>
     * 字段: sdk_notify_ucsasdk.orderId<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 由游戏传递的订单号
     */
    private String orderid;

    /**<br/>
     * 字段: sdk_notify_ucsasdk.gameId<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 游戏编号
     */
    private String gameid;

    /**<br/>
     * 字段: sdk_notify_ucsasdk.amount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * 说明: 支付金额,单位：元
     */
    private Float amount;

    /**<br/>
     * 字段: sdk_notify_ucsasdk.payType<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 支付方式代码
     */
    private String paytype;

    /**<br/>
     * 字段: sdk_notify_ucsasdk.attachInfo<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 游戏合作商自定义参数
     */
    private String attachinfo;

    /**<br/>
     * 字段: sdk_notify_ucsasdk.orderStatus<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 订单状态,S-成功支付 F-支付失败
     */
    private String orderstatus;

    /**<br/>
     * 字段: sdk_notify_ucsasdk.failedDesc<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 订单失败原因详细描述,如果是成功支付，则为空串
     */
    private String faileddesc;

    /**<br/>
     * 字段: sdk_notify_ucsasdk.ver<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 接口版本号
     */
    private String ver;

    /**<br/>
     * 字段: sdk_notify_ucsasdk.signature<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 签名参数
     */
    private String signature;

    /**<br/>
     * 字段: sdk_notify_ucsasdk.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createTime;

    /**
     * @return sdk_notify_ucsasdk.id: 自增字段
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_notify_ucsasdk.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param id: 自增字段
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return sdk_notify_ucsasdk.tradeId: uc九游订单号
     */
    public String getTradeid() {
        return tradeid;
    }

    /**<br/>
     * 字段: sdk_notify_ucsasdk.tradeId<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param tradeid: uc九游订单号
     */
    public void setTradeid(String tradeid) {
        this.tradeid = tradeid;
    }

    /**
     * @return sdk_notify_ucsasdk.tradeTime: 订单交易时间
     */
    public Date getTradetime() {
        return tradetime;
    }

    /**<br/>
     * 字段: sdk_notify_ucsasdk.tradeTime<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param tradetime: 订单交易时间
     */
    public void setTradetime(Date tradetime) {
        this.tradetime = tradetime;
    }

    /**
     * @return sdk_notify_ucsasdk.orderId: 由游戏传递的订单号
     */
    public String getOrderid() {
        return orderid;
    }

    /**<br/>
     * 字段: sdk_notify_ucsasdk.orderId<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param orderid: 由游戏传递的订单号
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    /**
     * @return sdk_notify_ucsasdk.gameId: 游戏编号
     */
    public String getGameid() {
        return gameid;
    }

    /**<br/>
     * 字段: sdk_notify_ucsasdk.gameId<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param gameid: 游戏编号
     */
    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    /**
     * @return sdk_notify_ucsasdk.amount: 支付金额,单位：元
     */
    public Float getAmount() {
        return amount;
    }

    /**<br/>
     * 字段: sdk_notify_ucsasdk.amount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * @param amount: 支付金额,单位：元
     */
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    /**
     * @return sdk_notify_ucsasdk.payType: 支付方式代码
     */
    public String getPaytype() {
        return paytype;
    }

    /**<br/>
     * 字段: sdk_notify_ucsasdk.payType<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param paytype: 支付方式代码
     */
    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    /**
     * @return sdk_notify_ucsasdk.attachInfo: 游戏合作商自定义参数
     */
    public String getAttachinfo() {
        return attachinfo;
    }

    /**<br/>
     * 字段: sdk_notify_ucsasdk.attachInfo<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param attachinfo: 游戏合作商自定义参数
     */
    public void setAttachinfo(String attachinfo) {
        this.attachinfo = attachinfo;
    }

    /**
     * @return sdk_notify_ucsasdk.orderStatus: 订单状态,S-成功支付 F-支付失败
     */
    public String getOrderstatus() {
        return orderstatus;
    }

    /**<br/>
     * 字段: sdk_notify_ucsasdk.orderStatus<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param orderstatus: 订单状态,S-成功支付 F-支付失败
     */
    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    /**
     * @return sdk_notify_ucsasdk.failedDesc: 订单失败原因详细描述,如果是成功支付，则为空串
     */
    public String getFaileddesc() {
        return faileddesc;
    }

    /**<br/>
     * 字段: sdk_notify_ucsasdk.failedDesc<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param faileddesc: 订单失败原因详细描述,如果是成功支付，则为空串
     */
    public void setFaileddesc(String faileddesc) {
        this.faileddesc = faileddesc;
    }

    /**
     * @return sdk_notify_ucsasdk.ver: 接口版本号
     */
    public String getVer() {
        return ver;
    }

    /**<br/>
     * 字段: sdk_notify_ucsasdk.ver<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * @param ver: 接口版本号
     */
    public void setVer(String ver) {
        this.ver = ver;
    }

    /**
     * @return sdk_notify_ucsasdk.signature: 签名参数
     */
    public String getSignature() {
        return signature;
    }

    /**<br/>
     * 字段: sdk_notify_ucsasdk.signature<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * @param signature: 签名参数
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * @return sdk_notify_ucsasdk.create_time: 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**<br/>
     * 字段: sdk_notify_ucsasdk.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createTime: 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}