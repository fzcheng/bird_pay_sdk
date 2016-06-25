package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkNotifyMmdo {
    /**<br/>
     * 字段: sdk_notify_mmdo.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 主键
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_notify_mmdo.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createTime;

    /**<br/>
     * 字段: sdk_notify_mmdo.order_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 表sdk_order的订单编号(乐游订单号)
     */
    private String orderNo;

    /**<br/>
     * 字段: sdk_notify_mmdo.tradeid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 外部订单流水号
     */
    private String tradeid;

    /**<br/>
     * 字段: sdk_notify_mmdo.status<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 状态:1-成功,0-失败
     */
    private Integer status;

    /**<br/>
     * 字段: sdk_notify_mmdo.resultcode<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 返回的原始码
     */
    private String resultcode;

    /**<br/>
     * 字段: sdk_notify_mmdo.status_detail<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 状态进一步描述(如错误或异常的原因)
     */
    private String statusDetail;

    /**<br/>
     * 字段: sdk_notify_mmdo.game_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 游戏ID
     */
    private Integer gameId;

    /**<br/>
     * 字段: sdk_notify_mmdo.amount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * 说明: 订单金额
     */
    private Float amount;

    /**<br/>
     * 字段: sdk_notify_mmdo.operation_type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 运营商类型（1、移动，2、联通，3、电信）
     */
    private Integer operationType;

    /**<br/>
     * 字段: sdk_notify_mmdo.pay_channel_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 支付通道编号
     */
    private String payChannelCode;

    /**<br/>
     * 字段: sdk_notify_mmdo.additional_status<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 是否是补点计费,0:否,1:是
     */
    private Integer additionalStatus;

    /**<br/>
     * 字段: sdk_notify_mmdo.mobilephone<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 11<br/>
     * 说明: 手机号码
     */
    private String mobilephone;

    /**<br/>
     * 字段: sdk_notify_mmdo.province_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 省份地址编码
     */
    private Integer provinceNo;

    /**<br/>
     * 字段: sdk_notify_mmdo.appid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 外部应用编号
     */
    private String appid;

    /**<br/>
     * 字段: sdk_notify_mmdo.chargepoint<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 外部计费点编号
     */
    private String chargepoint;

    /**<br/>
     * 字段: sdk_notify_mmdo.paytype<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 25<br/>
     * 说明: 外部支付方式代码
     */
    private String paytype;

    /**
     * @return sdk_notify_mmdo.id: 主键
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_notify_mmdo.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param id: 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return sdk_notify_mmdo.create_time: 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**<br/>
     * 字段: sdk_notify_mmdo.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createTime: 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return sdk_notify_mmdo.order_no: 表sdk_order的订单编号(乐游订单号)
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**<br/>
     * 字段: sdk_notify_mmdo.order_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param orderNo: 表sdk_order的订单编号(乐游订单号)
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return sdk_notify_mmdo.tradeid: 外部订单流水号
     */
    public String getTradeid() {
        return tradeid;
    }

    /**<br/>
     * 字段: sdk_notify_mmdo.tradeid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param tradeid: 外部订单流水号
     */
    public void setTradeid(String tradeid) {
        this.tradeid = tradeid;
    }

    /**
     * @return sdk_notify_mmdo.status: 状态:1-成功,0-失败
     */
    public Integer getStatus() {
        return status;
    }

    /**<br/>
     * 字段: sdk_notify_mmdo.status<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param status: 状态:1-成功,0-失败
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return sdk_notify_mmdo.resultcode: 返回的原始码
     */
    public String getResultcode() {
        return resultcode;
    }

    /**<br/>
     * 字段: sdk_notify_mmdo.resultcode<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param resultcode: 返回的原始码
     */
    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    /**
     * @return sdk_notify_mmdo.status_detail: 状态进一步描述(如错误或异常的原因)
     */
    public String getStatusDetail() {
        return statusDetail;
    }

    /**<br/>
     * 字段: sdk_notify_mmdo.status_detail<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param statusDetail: 状态进一步描述(如错误或异常的原因)
     */
    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }

    /**
     * @return sdk_notify_mmdo.game_id: 游戏ID
     */
    public Integer getGameId() {
        return gameId;
    }

    /**<br/>
     * 字段: sdk_notify_mmdo.game_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param gameId: 游戏ID
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return sdk_notify_mmdo.amount: 订单金额
     */
    public Float getAmount() {
        return amount;
    }

    /**<br/>
     * 字段: sdk_notify_mmdo.amount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * @param amount: 订单金额
     */
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    /**
     * @return sdk_notify_mmdo.operation_type: 运营商类型（1、移动，2、联通，3、电信）
     */
    public Integer getOperationType() {
        return operationType;
    }

    /**<br/>
     * 字段: sdk_notify_mmdo.operation_type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param operationType: 运营商类型（1、移动，2、联通，3、电信）
     */
    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    /**
     * @return sdk_notify_mmdo.pay_channel_code: 支付通道编号
     */
    public String getPayChannelCode() {
        return payChannelCode;
    }

    /**<br/>
     * 字段: sdk_notify_mmdo.pay_channel_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param payChannelCode: 支付通道编号
     */
    public void setPayChannelCode(String payChannelCode) {
        this.payChannelCode = payChannelCode;
    }

    /**
     * @return sdk_notify_mmdo.additional_status: 是否是补点计费,0:否,1:是
     */
    public Integer getAdditionalStatus() {
        return additionalStatus;
    }

    /**<br/>
     * 字段: sdk_notify_mmdo.additional_status<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param additionalStatus: 是否是补点计费,0:否,1:是
     */
    public void setAdditionalStatus(Integer additionalStatus) {
        this.additionalStatus = additionalStatus;
    }

    /**
     * @return sdk_notify_mmdo.mobilephone: 手机号码
     */
    public String getMobilephone() {
        return mobilephone;
    }

    /**<br/>
     * 字段: sdk_notify_mmdo.mobilephone<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 11<br/>
     * @param mobilephone: 手机号码
     */
    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    /**
     * @return sdk_notify_mmdo.province_no: 省份地址编码
     */
    public Integer getProvinceNo() {
        return provinceNo;
    }

    /**<br/>
     * 字段: sdk_notify_mmdo.province_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param provinceNo: 省份地址编码
     */
    public void setProvinceNo(Integer provinceNo) {
        this.provinceNo = provinceNo;
    }

    /**
     * @return sdk_notify_mmdo.appid: 外部应用编号
     */
    public String getAppid() {
        return appid;
    }

    /**<br/>
     * 字段: sdk_notify_mmdo.appid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param appid: 外部应用编号
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    /**
     * @return sdk_notify_mmdo.chargepoint: 外部计费点编号
     */
    public String getChargepoint() {
        return chargepoint;
    }

    /**<br/>
     * 字段: sdk_notify_mmdo.chargepoint<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param chargepoint: 外部计费点编号
     */
    public void setChargepoint(String chargepoint) {
        this.chargepoint = chargepoint;
    }

    /**
     * @return sdk_notify_mmdo.paytype: 外部支付方式代码
     */
    public String getPaytype() {
        return paytype;
    }

    /**<br/>
     * 字段: sdk_notify_mmdo.paytype<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 25<br/>
     * @param paytype: 外部支付方式代码
     */
    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }
}