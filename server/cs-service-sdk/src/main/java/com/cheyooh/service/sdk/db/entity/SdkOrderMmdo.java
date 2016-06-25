package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkOrderMmdo {
    /**<br/>
     * 字段: sdk_order_mmdo.pay_id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 主键
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer payId;

    /**<br/>
     * 字段: sdk_order_mmdo.req_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 请求时间
     */
    private Date reqTime;

    /**<br/>
     * 字段: sdk_order_mmdo.req_order_amount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * 说明: 订单金额
     */
    private Float reqOrderAmount;

    /**<br/>
     * 字段: sdk_order_mmdo.req_imsi<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 手机IMSI,逗号分隔
     */
    private String reqImsi;

    /**<br/>
     * 字段: sdk_order_mmdo.req_send_number<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 发送端口（4元10658035619003，2元10658035619004）
     */
    private String reqSendNumber;

    /**<br/>
     * 字段: sdk_order_mmdo.req_send_content<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 发送指令
     */
    private String reqSendContent;

    /**<br/>
     * 字段: sdk_order_mmdo.resp_imsi<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 实际发送使用的Imsi
     */
    private String respImsi;

    /**<br/>
     * 字段: sdk_order_mmdo.resp_send_number<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 实际发送端口
     */
    private String respSendNumber;

    /**<br/>
     * 字段: sdk_order_mmdo.resp_send_content<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 实际发送指令
     */
    private String respSendContent;

    /**<br/>
     * 字段: sdk_order_mmdo.resp_status<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 0表示失败，1表示成功（更新order数据）
     */
    private Integer respStatus;

    /**<br/>
     * 字段: sdk_order_mmdo.resp_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 响应时间
     */
    private Date respTime;

    /**<br/>
     * 字段: sdk_order_mmdo.game_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 游戏id
     */
    private Integer gameId;

    /**<br/>
     * 字段: sdk_order_mmdo.uid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 用户ID
     */
    private Integer uid;

    /**<br/>
     * 字段: sdk_order_mmdo.operation_type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 运营商类型（1、移动，2、联通，3、电信）
     */
    private Integer operationType;

    /**<br/>
     * 字段: sdk_order_mmdo.imei<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 手机IMEI号
     */
    private String imei;

    /**<br/>
     * 字段: sdk_order_mmdo.mac_addr<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 手机mac地址
     */
    private String macAddr;

    /**<br/>
     * 字段: sdk_order_mmdo.ip_addr<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 手机ip地址
     */
    private String ipAddr;

    /**<br/>
     * 字段: sdk_order_mmdo.raw_data<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 2048<br/>
     * 说明: 响应原始数据
     */
    private String rawData;

    /**<br/>
     * 字段: sdk_order_mmdo.pay_channel_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 支付通道编号
     */
    private String payChannelCode;

    /**<br/>
     * 字段: sdk_order_mmdo.additional_status<br/>
     * 可空: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 是否是补点计费,0:否,1:是
     */
    private Integer additionalStatus;

    /**<br/>
     * 字段: sdk_order_mmdo.tjpropsname<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 统计的道具名称
     */
    private String tjpropsname;

    /**<br/>
     * 字段: sdk_order_mmdo.tradeid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 外部订单流水号
     */
    private String tradeid;

    /**<br/>
     * 字段: sdk_order_mmdo.over_thirtym<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 是否是30秒之内订单,0-否,1-是
     */
    private Integer overThirtym;

    /**<br/>
     * 字段: sdk_order_mmdo.mobilephone<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 11<br/>
     * 说明: 手机号码
     */
    private String mobilephone;

    /**<br/>
     * 字段: sdk_order_mmdo.province_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 省份地址编码
     */
    private Integer provinceNo;

    /**<br/>
     * 字段: sdk_order_mmdo.iccid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 30<br/>
     * 说明: 集成电路卡识别码（固化在手机SIM卡中）
     */
    private String iccid;

    /**<br/>
     * 字段: sdk_order_mmdo.centernumber<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 30<br/>
     * 说明: 短信中心号段
     */
    private String centernumber;

    /**<br/>
     * 字段: sdk_order_mmdo.paytactics<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 800<br/>
     * 说明: 循环支付通道策略,以逗号分隔
     */
    private String paytactics;

    /**<br/>
     * 字段: sdk_order_mmdo.next_paytactics<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 下一个策略
     */
    private Integer nextPaytactics;

    /**
     * @return sdk_order_mmdo.pay_id: 主键
     */
    public Integer getPayId() {
        return payId;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.pay_id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param payId: 主键
     */
    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    /**
     * @return sdk_order_mmdo.req_time: 请求时间
     */
    public Date getReqTime() {
        return reqTime;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.req_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param reqTime: 请求时间
     */
    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    /**
     * @return sdk_order_mmdo.req_order_amount: 订单金额
     */
    public Float getReqOrderAmount() {
        return reqOrderAmount;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.req_order_amount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * @param reqOrderAmount: 订单金额
     */
    public void setReqOrderAmount(Float reqOrderAmount) {
        this.reqOrderAmount = reqOrderAmount;
    }

    /**
     * @return sdk_order_mmdo.req_imsi: 手机IMSI,逗号分隔
     */
    public String getReqImsi() {
        return reqImsi;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.req_imsi<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * @param reqImsi: 手机IMSI,逗号分隔
     */
    public void setReqImsi(String reqImsi) {
        this.reqImsi = reqImsi;
    }

    /**
     * @return sdk_order_mmdo.req_send_number: 发送端口（4元10658035619003，2元10658035619004）
     */
    public String getReqSendNumber() {
        return reqSendNumber;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.req_send_number<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param reqSendNumber: 发送端口（4元10658035619003，2元10658035619004）
     */
    public void setReqSendNumber(String reqSendNumber) {
        this.reqSendNumber = reqSendNumber;
    }

    /**
     * @return sdk_order_mmdo.req_send_content: 发送指令
     */
    public String getReqSendContent() {
        return reqSendContent;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.req_send_content<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * @param reqSendContent: 发送指令
     */
    public void setReqSendContent(String reqSendContent) {
        this.reqSendContent = reqSendContent;
    }

    /**
     * @return sdk_order_mmdo.resp_imsi: 实际发送使用的Imsi
     */
    public String getRespImsi() {
        return respImsi;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.resp_imsi<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * @param respImsi: 实际发送使用的Imsi
     */
    public void setRespImsi(String respImsi) {
        this.respImsi = respImsi;
    }

    /**
     * @return sdk_order_mmdo.resp_send_number: 实际发送端口
     */
    public String getRespSendNumber() {
        return respSendNumber;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.resp_send_number<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param respSendNumber: 实际发送端口
     */
    public void setRespSendNumber(String respSendNumber) {
        this.respSendNumber = respSendNumber;
    }

    /**
     * @return sdk_order_mmdo.resp_send_content: 实际发送指令
     */
    public String getRespSendContent() {
        return respSendContent;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.resp_send_content<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * @param respSendContent: 实际发送指令
     */
    public void setRespSendContent(String respSendContent) {
        this.respSendContent = respSendContent;
    }

    /**
     * @return sdk_order_mmdo.resp_status: 0表示失败，1表示成功（更新order数据）
     */
    public Integer getRespStatus() {
        return respStatus;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.resp_status<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param respStatus: 0表示失败，1表示成功（更新order数据）
     */
    public void setRespStatus(Integer respStatus) {
        this.respStatus = respStatus;
    }

    /**
     * @return sdk_order_mmdo.resp_time: 响应时间
     */
    public Date getRespTime() {
        return respTime;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.resp_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param respTime: 响应时间
     */
    public void setRespTime(Date respTime) {
        this.respTime = respTime;
    }

    /**
     * @return sdk_order_mmdo.game_id: 游戏id
     */
    public Integer getGameId() {
        return gameId;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.game_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param gameId: 游戏id
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return sdk_order_mmdo.uid: 用户ID
     */
    public Integer getUid() {
        return uid;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.uid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param uid: 用户ID
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * @return sdk_order_mmdo.operation_type: 运营商类型（1、移动，2、联通，3、电信）
     */
    public Integer getOperationType() {
        return operationType;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.operation_type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param operationType: 运营商类型（1、移动，2、联通，3、电信）
     */
    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    /**
     * @return sdk_order_mmdo.imei: 手机IMEI号
     */
    public String getImei() {
        return imei;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.imei<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param imei: 手机IMEI号
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * @return sdk_order_mmdo.mac_addr: 手机mac地址
     */
    public String getMacAddr() {
        return macAddr;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.mac_addr<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param macAddr: 手机mac地址
     */
    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    /**
     * @return sdk_order_mmdo.ip_addr: 手机ip地址
     */
    public String getIpAddr() {
        return ipAddr;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.ip_addr<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * @param ipAddr: 手机ip地址
     */
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    /**
     * @return sdk_order_mmdo.raw_data: 响应原始数据
     */
    public String getRawData() {
        return rawData;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.raw_data<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 2048<br/>
     * @param rawData: 响应原始数据
     */
    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    /**
     * @return sdk_order_mmdo.pay_channel_code: 支付通道编号
     */
    public String getPayChannelCode() {
        return payChannelCode;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.pay_channel_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param payChannelCode: 支付通道编号
     */
    public void setPayChannelCode(String payChannelCode) {
        this.payChannelCode = payChannelCode;
    }

    /**
     * @return sdk_order_mmdo.additional_status: 是否是补点计费,0:否,1:是
     */
    public Integer getAdditionalStatus() {
        return additionalStatus;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.additional_status<br/>
     * 可空: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * @param additionalStatus: 是否是补点计费,0:否,1:是
     */
    public void setAdditionalStatus(Integer additionalStatus) {
        this.additionalStatus = additionalStatus;
    }

    /**
     * @return sdk_order_mmdo.tjpropsname: 统计的道具名称
     */
    public String getTjpropsname() {
        return tjpropsname;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.tjpropsname<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param tjpropsname: 统计的道具名称
     */
    public void setTjpropsname(String tjpropsname) {
        this.tjpropsname = tjpropsname;
    }

    /**
     * @return sdk_order_mmdo.tradeid: 外部订单流水号
     */
    public String getTradeid() {
        return tradeid;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.tradeid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param tradeid: 外部订单流水号
     */
    public void setTradeid(String tradeid) {
        this.tradeid = tradeid;
    }

    /**
     * @return sdk_order_mmdo.over_thirtym: 是否是30秒之内订单,0-否,1-是
     */
    public Integer getOverThirtym() {
        return overThirtym;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.over_thirtym<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param overThirtym: 是否是30秒之内订单,0-否,1-是
     */
    public void setOverThirtym(Integer overThirtym) {
        this.overThirtym = overThirtym;
    }

    /**
     * @return sdk_order_mmdo.mobilephone: 手机号码
     */
    public String getMobilephone() {
        return mobilephone;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.mobilephone<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 11<br/>
     * @param mobilephone: 手机号码
     */
    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    /**
     * @return sdk_order_mmdo.province_no: 省份地址编码
     */
    public Integer getProvinceNo() {
        return provinceNo;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.province_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param provinceNo: 省份地址编码
     */
    public void setProvinceNo(Integer provinceNo) {
        this.provinceNo = provinceNo;
    }

    /**
     * @return sdk_order_mmdo.iccid: 集成电路卡识别码（固化在手机SIM卡中）
     */
    public String getIccid() {
        return iccid;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.iccid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 30<br/>
     * @param iccid: 集成电路卡识别码（固化在手机SIM卡中）
     */
    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    /**
     * @return sdk_order_mmdo.centernumber: 短信中心号段
     */
    public String getCenternumber() {
        return centernumber;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.centernumber<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 30<br/>
     * @param centernumber: 短信中心号段
     */
    public void setCenternumber(String centernumber) {
        this.centernumber = centernumber;
    }

    /**
     * @return sdk_order_mmdo.paytactics: 循环支付通道策略,以逗号分隔
     */
    public String getPaytactics() {
        return paytactics;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.paytactics<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 800<br/>
     * @param paytactics: 循环支付通道策略,以逗号分隔
     */
    public void setPaytactics(String paytactics) {
        this.paytactics = paytactics;
    }

    /**
     * @return sdk_order_mmdo.next_paytactics: 下一个策略
     */
    public Integer getNextPaytactics() {
        return nextPaytactics;
    }

    /**<br/>
     * 字段: sdk_order_mmdo.next_paytactics<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param nextPaytactics: 下一个策略
     */
    public void setNextPaytactics(Integer nextPaytactics) {
        this.nextPaytactics = nextPaytactics;
    }
}