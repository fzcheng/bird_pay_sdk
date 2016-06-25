package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkCuccZhangyunzyVerify {
    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: ID
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.order_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 订单号
     */
    private String orderNo;

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.mobile<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 手机号
     */
    private String mobile;

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.out_trade_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 外部订单号
     */
    private String outTradeNo;

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.price<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 支付金额(分)
     */
    private Integer price;

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.verify_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 验证码
     */
    private String verifyCode;

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.game_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 游戏ID
     */
    private Integer gameId;

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.apply_resultCode<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 申请支付响应返回码
     */
    private String applyResultcode;

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.apply_resultMsg<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 申请支付响应返回信息
     */
    private String applyResultmsg;

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.confirm_resultCode<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 确认支付响应返回码
     */
    private String confirmResultcode;

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.confirm_resultMsg<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 确认支付响应返回信息
     */
    private String confirmResultmsg;

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createdTime;

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 修改时间
     */
    private Date updatedTime;

    /**
     * @return sdk_cucc_zhangyunzy_verify.id: ID
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.id<br/>
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
     * @return sdk_cucc_zhangyunzy_verify.order_no: 订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.order_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param orderNo: 订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return sdk_cucc_zhangyunzy_verify.mobile: 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.mobile<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * @param mobile: 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return sdk_cucc_zhangyunzy_verify.out_trade_no: 外部订单号
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.out_trade_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param outTradeNo: 外部订单号
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    /**
     * @return sdk_cucc_zhangyunzy_verify.price: 支付金额(分)
     */
    public Integer getPrice() {
        return price;
    }

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.price<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param price: 支付金额(分)
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * @return sdk_cucc_zhangyunzy_verify.verify_code: 验证码
     */
    public String getVerifyCode() {
        return verifyCode;
    }

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.verify_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * @param verifyCode: 验证码
     */
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    /**
     * @return sdk_cucc_zhangyunzy_verify.game_id: 游戏ID
     */
    public Integer getGameId() {
        return gameId;
    }

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.game_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param gameId: 游戏ID
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return sdk_cucc_zhangyunzy_verify.apply_resultCode: 申请支付响应返回码
     */
    public String getApplyResultcode() {
        return applyResultcode;
    }

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.apply_resultCode<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param applyResultcode: 申请支付响应返回码
     */
    public void setApplyResultcode(String applyResultcode) {
        this.applyResultcode = applyResultcode;
    }

    /**
     * @return sdk_cucc_zhangyunzy_verify.apply_resultMsg: 申请支付响应返回信息
     */
    public String getApplyResultmsg() {
        return applyResultmsg;
    }

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.apply_resultMsg<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param applyResultmsg: 申请支付响应返回信息
     */
    public void setApplyResultmsg(String applyResultmsg) {
        this.applyResultmsg = applyResultmsg;
    }

    /**
     * @return sdk_cucc_zhangyunzy_verify.confirm_resultCode: 确认支付响应返回码
     */
    public String getConfirmResultcode() {
        return confirmResultcode;
    }

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.confirm_resultCode<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param confirmResultcode: 确认支付响应返回码
     */
    public void setConfirmResultcode(String confirmResultcode) {
        this.confirmResultcode = confirmResultcode;
    }

    /**
     * @return sdk_cucc_zhangyunzy_verify.confirm_resultMsg: 确认支付响应返回信息
     */
    public String getConfirmResultmsg() {
        return confirmResultmsg;
    }

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.confirm_resultMsg<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param confirmResultmsg: 确认支付响应返回信息
     */
    public void setConfirmResultmsg(String confirmResultmsg) {
        this.confirmResultmsg = confirmResultmsg;
    }

    /**
     * @return sdk_cucc_zhangyunzy_verify.created_time: 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createdTime: 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return sdk_cucc_zhangyunzy_verify.updated_time: 修改时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**<br/>
     * 字段: sdk_cucc_zhangyunzy_verify.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param updatedTime: 修改时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}