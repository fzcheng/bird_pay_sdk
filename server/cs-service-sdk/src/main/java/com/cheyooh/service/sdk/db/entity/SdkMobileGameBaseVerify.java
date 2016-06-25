package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkMobileGameBaseVerify {
    /**<br/>
     * 字段: sdk_mobile_game_base_verify.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: ID
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_mobile_game_base_verify.order_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 订单号
     */
    private String orderNo;

    /**<br/>
     * 字段: sdk_mobile_game_base_verify.mobile<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 手机号
     */
    private String mobile;

    /**<br/>
     * 字段: sdk_mobile_game_base_verify.out_trade_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 外部订单号
     */
    private String outTradeNo;

    /**<br/>
     * 字段: sdk_mobile_game_base_verify.price<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 支付金额(分)
     */
    private Integer price;

    /**<br/>
     * 字段: sdk_mobile_game_base_verify.verify_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 验证码
     */
    private String verifyCode;

    /**<br/>
     * 字段: sdk_mobile_game_base_verify.apply_raw_data<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 1024<br/>
     * 说明: 申请支付响应数据
     */
    private String applyRawData;

    /**<br/>
     * 字段: sdk_mobile_game_base_verify.confirm_raw_data<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 1024<br/>
     * 说明: 确认支付响应数据
     */
    private String confirmRawData;

    /**<br/>
     * 字段: sdk_mobile_game_base_verify.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createdTime;

    /**<br/>
     * 字段: sdk_mobile_game_base_verify.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 修改时间
     */
    private Date updatedTime;

    /**
     * @return sdk_mobile_game_base_verify.id: ID
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_mobile_game_base_verify.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param id: ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return sdk_mobile_game_base_verify.order_no: 订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**<br/>
     * 字段: sdk_mobile_game_base_verify.order_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param orderNo: 订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return sdk_mobile_game_base_verify.mobile: 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**<br/>
     * 字段: sdk_mobile_game_base_verify.mobile<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * @param mobile: 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return sdk_mobile_game_base_verify.out_trade_no: 外部订单号
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**<br/>
     * 字段: sdk_mobile_game_base_verify.out_trade_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param outTradeNo: 外部订单号
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    /**
     * @return sdk_mobile_game_base_verify.price: 支付金额(分)
     */
    public Integer getPrice() {
        return price;
    }

    /**<br/>
     * 字段: sdk_mobile_game_base_verify.price<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param price: 支付金额(分)
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * @return sdk_mobile_game_base_verify.verify_code: 验证码
     */
    public String getVerifyCode() {
        return verifyCode;
    }

    /**<br/>
     * 字段: sdk_mobile_game_base_verify.verify_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * @param verifyCode: 验证码
     */
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    /**
     * @return sdk_mobile_game_base_verify.apply_raw_data: 申请支付响应数据
     */
    public String getApplyRawData() {
        return applyRawData;
    }

    /**<br/>
     * 字段: sdk_mobile_game_base_verify.apply_raw_data<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 1024<br/>
     * @param applyRawData: 申请支付响应数据
     */
    public void setApplyRawData(String applyRawData) {
        this.applyRawData = applyRawData;
    }

    /**
     * @return sdk_mobile_game_base_verify.confirm_raw_data: 确认支付响应数据
     */
    public String getConfirmRawData() {
        return confirmRawData;
    }

    /**<br/>
     * 字段: sdk_mobile_game_base_verify.confirm_raw_data<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 1024<br/>
     * @param confirmRawData: 确认支付响应数据
     */
    public void setConfirmRawData(String confirmRawData) {
        this.confirmRawData = confirmRawData;
    }

    /**
     * @return sdk_mobile_game_base_verify.created_time: 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**<br/>
     * 字段: sdk_mobile_game_base_verify.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createdTime: 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return sdk_mobile_game_base_verify.updated_time: 修改时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**<br/>
     * 字段: sdk_mobile_game_base_verify.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param updatedTime: 修改时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}