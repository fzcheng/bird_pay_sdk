package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkOrderAlipay {
    /**<br/>
     * 字段: sdk_order_alipay.pay_id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 自增ID
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer payId;

    /**<br/>
     * 字段: sdk_order_alipay.req_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 请求时间
     */
    private Date reqTime;

    /**<br/>
     * 字段: sdk_order_alipay.req_partner<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 合作商户ID. 用签约支付宝账号登录ms.alipay.com后，在账户信息页面获取。
     */
    private String reqPartner;

    /**<br/>
     * 字段: sdk_order_alipay.req_seller<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 账户ID。用签约支付宝账号登录ms.alipay.com后，在账户信息页面获取。
     */
    private String reqSeller;

    /**<br/>
     * 字段: sdk_order_alipay.req_out_trade_no<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 外部交易号
     */
    private String reqOutTradeNo;

    /**<br/>
     * 字段: sdk_order_alipay.req_subject<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 出售商品
     */
    private String reqSubject;

    /**<br/>
     * 字段: sdk_order_alipay.req_body<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 商品描述
     */
    private String reqBody;

    /**<br/>
     * 字段: sdk_order_alipay.req_total_fee<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * 说明: 价格(单位元)
     */
    private Float reqTotalFee;

    /**<br/>
     * 字段: sdk_order_alipay.req_notify_url<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 支付宝通知地址
     */
    private String reqNotifyUrl;

    /**<br/>
     * 字段: sdk_order_alipay.notify_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 通知时间
     */
    private Date notifyTime;

    /**<br/>
     * 字段: sdk_order_alipay.notify_trade_status<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 用于判断交易状态，值有：
TRADE_FINISHED：表示交易成功完成
WAIT_BUYER_PAY：表示等待付款
     */
    private String notifyTradeStatus;

    /**<br/>
     * 字段: sdk_order_alipay.notify_total_fee<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * 说明: 交易金额
     */
    private Float notifyTotalFee;

    /**<br/>
     * 字段: sdk_order_alipay.notify_trade_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 支付宝交易号
     */
    private String notifyTradeNo;

    /**<br/>
     * 字段: sdk_order_alipay.notify_type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 通知类型
     */
    private String notifyType;

    /**<br/>
     * 字段: sdk_order_alipay.notify_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 通知校验ID
     */
    private String notifyId;

    /**<br/>
     * 字段: sdk_order_alipay.sign_type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 签名方式
     */
    private String signType;

    /**<br/>
     * 字段: sdk_order_alipay.buyer_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 买家支付宝账号对应的支付宝唯一用户号
     */
    private String buyerId;

    /**<br/>
     * 字段: sdk_order_alipay.buyer_email<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 买家支付宝账号对应的支付宝唯一用户号
     */
    private String buyerEmail;

    /**<br/>
     * 字段: sdk_order_alipay.gmt_create<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 交易创建时间
     */
    private Date gmtCreate;

    /**<br/>
     * 字段: sdk_order_alipay.gmt_payment<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 交易付款时间
     */
    private Date gmtPayment;

    /**<br/>
     * 字段: sdk_order_alipay.is_total_fee_adjust<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 4<br/>
     * 说明: 该交易是否调整过价格
     */
    private String isTotalFeeAdjust;

    /**<br/>
     * 字段: sdk_order_alipay.use_coupon<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 是否使用红包买家
     */
    private String useCoupon;

    /**<br/>
     * 字段: sdk_order_alipay.discount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 折扣,支付宝系统会把 discount 的值加到交易金额上，如果有折扣，本参数为负数，单位为元
     */
    private String discount;

    /**<br/>
     * 字段: sdk_order_alipay.refund_status<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 退款状态
     */
    private String refundStatus;

    /**<br/>
     * 字段: sdk_order_alipay.gmt_refund<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 退款时间
     */
    private Date gmtRefund;

    /**
     * @return sdk_order_alipay.pay_id: 自增ID
     */
    public Integer getPayId() {
        return payId;
    }

    /**<br/>
     * 字段: sdk_order_alipay.pay_id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param payId: 自增ID
     */
    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    /**
     * @return sdk_order_alipay.req_time: 请求时间
     */
    public Date getReqTime() {
        return reqTime;
    }

    /**<br/>
     * 字段: sdk_order_alipay.req_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param reqTime: 请求时间
     */
    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    /**
     * @return sdk_order_alipay.req_partner: 合作商户ID. 用签约支付宝账号登录ms.alipay.com后，在账户信息页面获取。
     */
    public String getReqPartner() {
        return reqPartner;
    }

    /**<br/>
     * 字段: sdk_order_alipay.req_partner<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param reqPartner: 合作商户ID. 用签约支付宝账号登录ms.alipay.com后，在账户信息页面获取。
     */
    public void setReqPartner(String reqPartner) {
        this.reqPartner = reqPartner;
    }

    /**
     * @return sdk_order_alipay.req_seller: 账户ID。用签约支付宝账号登录ms.alipay.com后，在账户信息页面获取。
     */
    public String getReqSeller() {
        return reqSeller;
    }

    /**<br/>
     * 字段: sdk_order_alipay.req_seller<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param reqSeller: 账户ID。用签约支付宝账号登录ms.alipay.com后，在账户信息页面获取。
     */
    public void setReqSeller(String reqSeller) {
        this.reqSeller = reqSeller;
    }

    /**
     * @return sdk_order_alipay.req_out_trade_no: 外部交易号
     */
    public String getReqOutTradeNo() {
        return reqOutTradeNo;
    }

    /**<br/>
     * 字段: sdk_order_alipay.req_out_trade_no<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param reqOutTradeNo: 外部交易号
     */
    public void setReqOutTradeNo(String reqOutTradeNo) {
        this.reqOutTradeNo = reqOutTradeNo;
    }

    /**
     * @return sdk_order_alipay.req_subject: 出售商品
     */
    public String getReqSubject() {
        return reqSubject;
    }

    /**<br/>
     * 字段: sdk_order_alipay.req_subject<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param reqSubject: 出售商品
     */
    public void setReqSubject(String reqSubject) {
        this.reqSubject = reqSubject;
    }

    /**
     * @return sdk_order_alipay.req_body: 商品描述
     */
    public String getReqBody() {
        return reqBody;
    }

    /**<br/>
     * 字段: sdk_order_alipay.req_body<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param reqBody: 商品描述
     */
    public void setReqBody(String reqBody) {
        this.reqBody = reqBody;
    }

    /**
     * @return sdk_order_alipay.req_total_fee: 价格(单位元)
     */
    public Float getReqTotalFee() {
        return reqTotalFee;
    }

    /**<br/>
     * 字段: sdk_order_alipay.req_total_fee<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * @param reqTotalFee: 价格(单位元)
     */
    public void setReqTotalFee(Float reqTotalFee) {
        this.reqTotalFee = reqTotalFee;
    }

    /**
     * @return sdk_order_alipay.req_notify_url: 支付宝通知地址
     */
    public String getReqNotifyUrl() {
        return reqNotifyUrl;
    }

    /**<br/>
     * 字段: sdk_order_alipay.req_notify_url<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param reqNotifyUrl: 支付宝通知地址
     */
    public void setReqNotifyUrl(String reqNotifyUrl) {
        this.reqNotifyUrl = reqNotifyUrl;
    }

    /**
     * @return sdk_order_alipay.notify_time: 通知时间
     */
    public Date getNotifyTime() {
        return notifyTime;
    }

    /**<br/>
     * 字段: sdk_order_alipay.notify_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param notifyTime: 通知时间
     */
    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    /**
     * @return sdk_order_alipay.notify_trade_status: 用于判断交易状态，值有：
TRADE_FINISHED：表示交易成功完成
WAIT_BUYER_PAY：表示等待付款
     */
    public String getNotifyTradeStatus() {
        return notifyTradeStatus;
    }

    /**<br/>
     * 字段: sdk_order_alipay.notify_trade_status<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * @param notifyTradeStatus: 用于判断交易状态，值有：
TRADE_FINISHED：表示交易成功完成
WAIT_BUYER_PAY：表示等待付款
     */
    public void setNotifyTradeStatus(String notifyTradeStatus) {
        this.notifyTradeStatus = notifyTradeStatus;
    }

    /**
     * @return sdk_order_alipay.notify_total_fee: 交易金额
     */
    public Float getNotifyTotalFee() {
        return notifyTotalFee;
    }

    /**<br/>
     * 字段: sdk_order_alipay.notify_total_fee<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * @param notifyTotalFee: 交易金额
     */
    public void setNotifyTotalFee(Float notifyTotalFee) {
        this.notifyTotalFee = notifyTotalFee;
    }

    /**
     * @return sdk_order_alipay.notify_trade_no: 支付宝交易号
     */
    public String getNotifyTradeNo() {
        return notifyTradeNo;
    }

    /**<br/>
     * 字段: sdk_order_alipay.notify_trade_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param notifyTradeNo: 支付宝交易号
     */
    public void setNotifyTradeNo(String notifyTradeNo) {
        this.notifyTradeNo = notifyTradeNo;
    }

    /**
     * @return sdk_order_alipay.notify_type: 通知类型
     */
    public String getNotifyType() {
        return notifyType;
    }

    /**<br/>
     * 字段: sdk_order_alipay.notify_type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param notifyType: 通知类型
     */
    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    /**
     * @return sdk_order_alipay.notify_id: 通知校验ID
     */
    public String getNotifyId() {
        return notifyId;
    }

    /**<br/>
     * 字段: sdk_order_alipay.notify_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * @param notifyId: 通知校验ID
     */
    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    /**
     * @return sdk_order_alipay.sign_type: 签名方式
     */
    public String getSignType() {
        return signType;
    }

    /**<br/>
     * 字段: sdk_order_alipay.sign_type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param signType: 签名方式
     */
    public void setSignType(String signType) {
        this.signType = signType;
    }

    /**
     * @return sdk_order_alipay.buyer_id: 买家支付宝账号对应的支付宝唯一用户号
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**<br/>
     * 字段: sdk_order_alipay.buyer_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param buyerId: 买家支付宝账号对应的支付宝唯一用户号
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * @return sdk_order_alipay.buyer_email: 买家支付宝账号对应的支付宝唯一用户号
     */
    public String getBuyerEmail() {
        return buyerEmail;
    }

    /**<br/>
     * 字段: sdk_order_alipay.buyer_email<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param buyerEmail: 买家支付宝账号对应的支付宝唯一用户号
     */
    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    /**
     * @return sdk_order_alipay.gmt_create: 交易创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**<br/>
     * 字段: sdk_order_alipay.gmt_create<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param gmtCreate: 交易创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * @return sdk_order_alipay.gmt_payment: 交易付款时间
     */
    public Date getGmtPayment() {
        return gmtPayment;
    }

    /**<br/>
     * 字段: sdk_order_alipay.gmt_payment<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param gmtPayment: 交易付款时间
     */
    public void setGmtPayment(Date gmtPayment) {
        this.gmtPayment = gmtPayment;
    }

    /**
     * @return sdk_order_alipay.is_total_fee_adjust: 该交易是否调整过价格
     */
    public String getIsTotalFeeAdjust() {
        return isTotalFeeAdjust;
    }

    /**<br/>
     * 字段: sdk_order_alipay.is_total_fee_adjust<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 4<br/>
     * @param isTotalFeeAdjust: 该交易是否调整过价格
     */
    public void setIsTotalFeeAdjust(String isTotalFeeAdjust) {
        this.isTotalFeeAdjust = isTotalFeeAdjust;
    }

    /**
     * @return sdk_order_alipay.use_coupon: 是否使用红包买家
     */
    public String getUseCoupon() {
        return useCoupon;
    }

    /**<br/>
     * 字段: sdk_order_alipay.use_coupon<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param useCoupon: 是否使用红包买家
     */
    public void setUseCoupon(String useCoupon) {
        this.useCoupon = useCoupon;
    }

    /**
     * @return sdk_order_alipay.discount: 折扣,支付宝系统会把 discount 的值加到交易金额上，如果有折扣，本参数为负数，单位为元
     */
    public String getDiscount() {
        return discount;
    }

    /**<br/>
     * 字段: sdk_order_alipay.discount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param discount: 折扣,支付宝系统会把 discount 的值加到交易金额上，如果有折扣，本参数为负数，单位为元
     */
    public void setDiscount(String discount) {
        this.discount = discount;
    }

    /**
     * @return sdk_order_alipay.refund_status: 退款状态
     */
    public String getRefundStatus() {
        return refundStatus;
    }

    /**<br/>
     * 字段: sdk_order_alipay.refund_status<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param refundStatus: 退款状态
     */
    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    /**
     * @return sdk_order_alipay.gmt_refund: 退款时间
     */
    public Date getGmtRefund() {
        return gmtRefund;
    }

    /**<br/>
     * 字段: sdk_order_alipay.gmt_refund<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param gmtRefund: 退款时间
     */
    public void setGmtRefund(Date gmtRefund) {
        this.gmtRefund = gmtRefund;
    }
}