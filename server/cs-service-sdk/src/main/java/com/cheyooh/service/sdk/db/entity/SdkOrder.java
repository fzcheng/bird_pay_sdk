package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkOrder {
    /**<br/>
     * 字段: sdk_order.order_no<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 订单编号
     */
    private String orderNo;

    /**<br/>
     * 字段: sdk_order.order_name<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 订单名称
     */
    private String orderName;

    /**<br/>
     * 字段: sdk_order.order_desc<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 订单描述
     */
    private String orderDesc;

    /**<br/>
     * 字段: sdk_order.uid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 用户ID
     */
    private Integer uid;

    /**<br/>
     * 字段: sdk_order.type<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 充值方式:
1- 支付宝
2- 财付通
3- 易宝(充值卡)
4- MO9
5- 微派

     */
    private Integer type;

    /**<br/>
     * 字段: sdk_order.pay_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 支付记录ID(根据recharge_type字段,对应具体支付表的主键)
     */
    private Integer payId;

    /**<br/>
     * 字段: sdk_order.amount<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * 说明: 订单金额(单位元)
     */
    private Float amount;

    /**<br/>
     * 字段: sdk_order.cp_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 游戏商ID
     */
    private Integer cpId;

    /**<br/>
     * 字段: sdk_order.game_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 游戏ID
     */
    private Integer gameId;

    /**<br/>
     * 字段: sdk_order.channel<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 渠道号
     */
    private String channel;

    /**<br/>
     * 字段: sdk_order.status<br/>
     * 可空: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 订单状态:
0-订单创建, 
1-支付成功, 
2-等待结果, 
3-支付失败,
4-订单异常
     */
    private Integer status;

    /**<br/>
     * 字段: sdk_order.status_detail<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 状态进一步描述(如错误或异常的原因)
     */
    private String statusDetail;

    /**<br/>
     * 字段: sdk_order.cp_ext<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 512<br/>
     * 说明: 游戏服务商扩展信息
     */
    private String cpExt;

    /**<br/>
     * 字段: sdk_order.query_status<br/>
     * 可空: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 
     */
    private Integer queryStatus;

    /**<br/>
     * 字段: sdk_order.query_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 
     */
    private Date queryTime;

    /**<br/>
     * 字段: sdk_order.notify_status<br/>
     * 可空: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 订单通知状态:
0-尚未通知
1-成功通知
2-通知失败
3-无需通知
<0-通知失败的次数

     */
    private Integer notifyStatus;

    /**<br/>
     * 字段: sdk_order.notify_request<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 2048<br/>
     * 说明: 请求URL(包括请求参数, 用于后台管理手动重发)
     */
    private String notifyRequest;

    /**<br/>
     * 字段: sdk_order.notify_response<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 最后一次通知游戏服务器响应的信息
     */
    private String notifyResponse;

    /**<br/>
     * 字段: sdk_order.notify_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 上一次通知的时间
     */
    private Date notifyTime;

    /**<br/>
     * 字段: sdk_order.create_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createTime;

    /**<br/>
     * 字段: sdk_order.complete_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 订单完成时间
     */
    private Date completeTime;

    /**<br/>
     * 字段: sdk_order.originalcode<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 返回的错误原始码
     */
    private String originalcode;

    /**<br/>
     * 字段: sdk_order.sdkver<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: sdk版本号
     */
    private String sdkver;

    /**<br/>
     * 字段: sdk_order.operation_type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 运营商类型（1、移动，2、联通，3、电信）
     */
    private Integer operationType;

    /**<br/>
     * 字段: sdk_order.mobilephone<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 11<br/>
     * 说明: 手机号码
     */
    private String mobilephone;

    /**<br/>
     * 字段: sdk_order.province_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 省份地址编码
     */
    private Integer provinceNo;

    /**
     * @return sdk_order.order_no: 订单编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**<br/>
     * 字段: sdk_order.order_no<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param orderNo: 订单编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return sdk_order.order_name: 订单名称
     */
    public String getOrderName() {
        return orderName;
    }

    /**<br/>
     * 字段: sdk_order.order_name<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param orderName: 订单名称
     */
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    /**
     * @return sdk_order.order_desc: 订单描述
     */
    public String getOrderDesc() {
        return orderDesc;
    }

    /**<br/>
     * 字段: sdk_order.order_desc<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param orderDesc: 订单描述
     */
    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    /**
     * @return sdk_order.uid: 用户ID
     */
    public Integer getUid() {
        return uid;
    }

    /**<br/>
     * 字段: sdk_order.uid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param uid: 用户ID
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * @return sdk_order.type: 充值方式:
1- 支付宝
2- 财付通
3- 易宝(充值卡)
4- MO9
5- 微派

     */
    public Integer getType() {
        return type;
    }

    /**<br/>
     * 字段: sdk_order.type<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param type: 充值方式:
1- 支付宝
2- 财付通
3- 易宝(充值卡)
4- MO9
5- 微派

     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return sdk_order.pay_id: 支付记录ID(根据recharge_type字段,对应具体支付表的主键)
     */
    public Integer getPayId() {
        return payId;
    }

    /**<br/>
     * 字段: sdk_order.pay_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param payId: 支付记录ID(根据recharge_type字段,对应具体支付表的主键)
     */
    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    /**
     * @return sdk_order.amount: 订单金额(单位元)
     */
    public Float getAmount() {
        return amount;
    }

    /**<br/>
     * 字段: sdk_order.amount<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * @param amount: 订单金额(单位元)
     */
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    /**
     * @return sdk_order.cp_id: 游戏商ID
     */
    public Integer getCpId() {
        return cpId;
    }

    /**<br/>
     * 字段: sdk_order.cp_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param cpId: 游戏商ID
     */
    public void setCpId(Integer cpId) {
        this.cpId = cpId;
    }

    /**
     * @return sdk_order.game_id: 游戏ID
     */
    public Integer getGameId() {
        return gameId;
    }

    /**<br/>
     * 字段: sdk_order.game_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param gameId: 游戏ID
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return sdk_order.channel: 渠道号
     */
    public String getChannel() {
        return channel;
    }

    /**<br/>
     * 字段: sdk_order.channel<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param channel: 渠道号
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * @return sdk_order.status: 订单状态:
0-订单创建, 
1-支付成功, 
2-等待结果, 
3-支付失败,
4-订单异常
     */
    public Integer getStatus() {
        return status;
    }

    /**<br/>
     * 字段: sdk_order.status<br/>
     * 可空: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * @param status: 订单状态:
0-订单创建, 
1-支付成功, 
2-等待结果, 
3-支付失败,
4-订单异常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return sdk_order.status_detail: 状态进一步描述(如错误或异常的原因)
     */
    public String getStatusDetail() {
        return statusDetail;
    }

    /**<br/>
     * 字段: sdk_order.status_detail<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param statusDetail: 状态进一步描述(如错误或异常的原因)
     */
    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }

    /**
     * @return sdk_order.cp_ext: 游戏服务商扩展信息
     */
    public String getCpExt() {
        return cpExt;
    }

    /**<br/>
     * 字段: sdk_order.cp_ext<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 512<br/>
     * @param cpExt: 游戏服务商扩展信息
     */
    public void setCpExt(String cpExt) {
        this.cpExt = cpExt;
    }

    /**
     * @return sdk_order.query_status: 
     */
    public Integer getQueryStatus() {
        return queryStatus;
    }

    /**<br/>
     * 字段: sdk_order.query_status<br/>
     * 可空: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * @param queryStatus: 
     */
    public void setQueryStatus(Integer queryStatus) {
        this.queryStatus = queryStatus;
    }

    /**
     * @return sdk_order.query_time: 
     */
    public Date getQueryTime() {
        return queryTime;
    }

    /**<br/>
     * 字段: sdk_order.query_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param queryTime: 
     */
    public void setQueryTime(Date queryTime) {
        this.queryTime = queryTime;
    }

    /**
     * @return sdk_order.notify_status: 订单通知状态:
0-尚未通知
1-成功通知
2-通知失败
3-无需通知
<0-通知失败的次数

     */
    public Integer getNotifyStatus() {
        return notifyStatus;
    }

    /**<br/>
     * 字段: sdk_order.notify_status<br/>
     * 可空: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * @param notifyStatus: 订单通知状态:
0-尚未通知
1-成功通知
2-通知失败
3-无需通知
<0-通知失败的次数

     */
    public void setNotifyStatus(Integer notifyStatus) {
        this.notifyStatus = notifyStatus;
    }

    /**
     * @return sdk_order.notify_request: 请求URL(包括请求参数, 用于后台管理手动重发)
     */
    public String getNotifyRequest() {
        return notifyRequest;
    }

    /**<br/>
     * 字段: sdk_order.notify_request<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 2048<br/>
     * @param notifyRequest: 请求URL(包括请求参数, 用于后台管理手动重发)
     */
    public void setNotifyRequest(String notifyRequest) {
        this.notifyRequest = notifyRequest;
    }

    /**
     * @return sdk_order.notify_response: 最后一次通知游戏服务器响应的信息
     */
    public String getNotifyResponse() {
        return notifyResponse;
    }

    /**<br/>
     * 字段: sdk_order.notify_response<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param notifyResponse: 最后一次通知游戏服务器响应的信息
     */
    public void setNotifyResponse(String notifyResponse) {
        this.notifyResponse = notifyResponse;
    }

    /**
     * @return sdk_order.notify_time: 上一次通知的时间
     */
    public Date getNotifyTime() {
        return notifyTime;
    }

    /**<br/>
     * 字段: sdk_order.notify_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param notifyTime: 上一次通知的时间
     */
    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    /**
     * @return sdk_order.create_time: 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**<br/>
     * 字段: sdk_order.create_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createTime: 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return sdk_order.complete_time: 订单完成时间
     */
    public Date getCompleteTime() {
        return completeTime;
    }

    /**<br/>
     * 字段: sdk_order.complete_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param completeTime: 订单完成时间
     */
    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    /**
     * @return sdk_order.originalcode: 返回的错误原始码
     */
    public String getOriginalcode() {
        return originalcode;
    }

    /**<br/>
     * 字段: sdk_order.originalcode<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * @param originalcode: 返回的错误原始码
     */
    public void setOriginalcode(String originalcode) {
        this.originalcode = originalcode;
    }

    /**
     * @return sdk_order.sdkver: sdk版本号
     */
    public String getSdkver() {
        return sdkver;
    }

    /**<br/>
     * 字段: sdk_order.sdkver<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * @param sdkver: sdk版本号
     */
    public void setSdkver(String sdkver) {
        this.sdkver = sdkver;
    }

    /**
     * @return sdk_order.operation_type: 运营商类型（1、移动，2、联通，3、电信）
     */
    public Integer getOperationType() {
        return operationType;
    }

    /**<br/>
     * 字段: sdk_order.operation_type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param operationType: 运营商类型（1、移动，2、联通，3、电信）
     */
    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    /**
     * @return sdk_order.mobilephone: 手机号码
     */
    public String getMobilephone() {
        return mobilephone;
    }

    /**<br/>
     * 字段: sdk_order.mobilephone<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 11<br/>
     * @param mobilephone: 手机号码
     */
    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    /**
     * @return sdk_order.province_no: 省份地址编码
     */
    public Integer getProvinceNo() {
        return provinceNo;
    }

    /**<br/>
     * 字段: sdk_order.province_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param provinceNo: 省份地址编码
     */
    public void setProvinceNo(Integer provinceNo) {
        this.provinceNo = provinceNo;
    }
}