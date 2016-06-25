package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkNotifyXiaomisdk {
    /**<br/>
     * 字段: sdk_notify_xiaomisdk.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 自增字段
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.orderId<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 小米订单号
     */
    private String orderid;

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.appId<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 游戏ID
     */
    private String appid;

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.cpOrderId<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 开发商订单ID
     */
    private String cporderid;

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.cpUserInfo<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 开发商透传信息
     */
    private String cpuserinfo;

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.uid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 用户ID
     */
    private String uid;

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.orderStatus<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 订单状态，TRADE_SUCCESS 代表成功
     */
    private String orderstatus;

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.payFee<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * 说明: 支付金额,单位为分,即0.01 米币
     */
    private Float payfee;

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.productCode<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 商品代码
     */
    private String productcode;

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.productName<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 商品名称
     */
    private String productname;

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.productCount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 商品数量
     */
    private String productcount;

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.payTime<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 支付时间
     */
    private Date paytime;

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.orderConsumeType<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 订单类型：10：普通订单11：直充直消订单
     */
    private String orderconsumetype;

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.partnerGiftConsume<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 使用游戏券金额 （如果订单使用游戏券则有,long型），如果有则参与签名
     */
    private String partnergiftconsume;

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.signature<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 签名
     */
    private String signature;

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createTime;

    /**
     * @return sdk_notify_xiaomisdk.id: 自增字段
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.id<br/>
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
     * @return sdk_notify_xiaomisdk.orderId: 小米订单号
     */
    public String getOrderid() {
        return orderid;
    }

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.orderId<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param orderid: 小米订单号
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    /**
     * @return sdk_notify_xiaomisdk.appId: 游戏ID
     */
    public String getAppid() {
        return appid;
    }

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.appId<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param appid: 游戏ID
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    /**
     * @return sdk_notify_xiaomisdk.cpOrderId: 开发商订单ID
     */
    public String getCporderid() {
        return cporderid;
    }

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.cpOrderId<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param cporderid: 开发商订单ID
     */
    public void setCporderid(String cporderid) {
        this.cporderid = cporderid;
    }

    /**
     * @return sdk_notify_xiaomisdk.cpUserInfo: 开发商透传信息
     */
    public String getCpuserinfo() {
        return cpuserinfo;
    }

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.cpUserInfo<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param cpuserinfo: 开发商透传信息
     */
    public void setCpuserinfo(String cpuserinfo) {
        this.cpuserinfo = cpuserinfo;
    }

    /**
     * @return sdk_notify_xiaomisdk.uid: 用户ID
     */
    public String getUid() {
        return uid;
    }

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.uid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param uid: 用户ID
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return sdk_notify_xiaomisdk.orderStatus: 订单状态，TRADE_SUCCESS 代表成功
     */
    public String getOrderstatus() {
        return orderstatus;
    }

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.orderStatus<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param orderstatus: 订单状态，TRADE_SUCCESS 代表成功
     */
    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    /**
     * @return sdk_notify_xiaomisdk.payFee: 支付金额,单位为分,即0.01 米币
     */
    public Float getPayfee() {
        return payfee;
    }

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.payFee<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * @param payfee: 支付金额,单位为分,即0.01 米币
     */
    public void setPayfee(Float payfee) {
        this.payfee = payfee;
    }

    /**
     * @return sdk_notify_xiaomisdk.productCode: 商品代码
     */
    public String getProductcode() {
        return productcode;
    }

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.productCode<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param productcode: 商品代码
     */
    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    /**
     * @return sdk_notify_xiaomisdk.productName: 商品名称
     */
    public String getProductname() {
        return productname;
    }

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.productName<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param productname: 商品名称
     */
    public void setProductname(String productname) {
        this.productname = productname;
    }

    /**
     * @return sdk_notify_xiaomisdk.productCount: 商品数量
     */
    public String getProductcount() {
        return productcount;
    }

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.productCount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * @param productcount: 商品数量
     */
    public void setProductcount(String productcount) {
        this.productcount = productcount;
    }

    /**
     * @return sdk_notify_xiaomisdk.payTime: 支付时间
     */
    public Date getPaytime() {
        return paytime;
    }

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.payTime<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param paytime: 支付时间
     */
    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    /**
     * @return sdk_notify_xiaomisdk.orderConsumeType: 订单类型：10：普通订单11：直充直消订单
     */
    public String getOrderconsumetype() {
        return orderconsumetype;
    }

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.orderConsumeType<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param orderconsumetype: 订单类型：10：普通订单11：直充直消订单
     */
    public void setOrderconsumetype(String orderconsumetype) {
        this.orderconsumetype = orderconsumetype;
    }

    /**
     * @return sdk_notify_xiaomisdk.partnerGiftConsume: 使用游戏券金额 （如果订单使用游戏券则有,long型），如果有则参与签名
     */
    public String getPartnergiftconsume() {
        return partnergiftconsume;
    }

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.partnerGiftConsume<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * @param partnergiftconsume: 使用游戏券金额 （如果订单使用游戏券则有,long型），如果有则参与签名
     */
    public void setPartnergiftconsume(String partnergiftconsume) {
        this.partnergiftconsume = partnergiftconsume;
    }

    /**
     * @return sdk_notify_xiaomisdk.signature: 签名
     */
    public String getSignature() {
        return signature;
    }

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.signature<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * @param signature: 签名
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * @return sdk_notify_xiaomisdk.create_time: 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**<br/>
     * 字段: sdk_notify_xiaomisdk.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createTime: 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}