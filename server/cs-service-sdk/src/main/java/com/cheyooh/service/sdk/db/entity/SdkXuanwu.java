package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkXuanwu {
    /**<br/>
     * 字段: sdk_xuanwu.id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 唯一编号,按订单号规则生成
     */
    private String id;

    /**<br/>
     * 字段: sdk_xuanwu.imsi<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 手机国际移动用户识别码
     */
    private String imsi;

    /**<br/>
     * 字段: sdk_xuanwu.imei<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 手机IMEI号
     */
    private String imei;

    /**<br/>
     * 字段: sdk_xuanwu.centernumber<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 短信中心号段
     */
    private String centernumber;

    /**<br/>
     * 字段: sdk_xuanwu.iccid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 集成电路卡识别码（固化在手机SIM卡中）
     */
    private String iccid;

    /**<br/>
     * 字段: sdk_xuanwu.mobilephone<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 11<br/>
     * 说明: 手机号码
     */
    private String mobilephone;

    /**<br/>
     * 字段: sdk_xuanwu.specNumber<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 30<br/>
     * 说明: 玄武分配的端口号
     */
    private String specnumber;

    /**<br/>
     * 字段: sdk_xuanwu.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createdTime;

    /**<br/>
     * 字段: sdk_xuanwu.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 更改时间
     */
    private Date updatedTime;

    /**
     * @return sdk_xuanwu.id: 唯一编号,按订单号规则生成
     */
    public String getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_xuanwu.id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param id: 唯一编号,按订单号规则生成
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return sdk_xuanwu.imsi: 手机国际移动用户识别码
     */
    public String getImsi() {
        return imsi;
    }

    /**<br/>
     * 字段: sdk_xuanwu.imsi<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * @param imsi: 手机国际移动用户识别码
     */
    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    /**
     * @return sdk_xuanwu.imei: 手机IMEI号
     */
    public String getImei() {
        return imei;
    }

    /**<br/>
     * 字段: sdk_xuanwu.imei<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * @param imei: 手机IMEI号
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * @return sdk_xuanwu.centernumber: 短信中心号段
     */
    public String getCenternumber() {
        return centernumber;
    }

    /**<br/>
     * 字段: sdk_xuanwu.centernumber<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * @param centernumber: 短信中心号段
     */
    public void setCenternumber(String centernumber) {
        this.centernumber = centernumber;
    }

    /**
     * @return sdk_xuanwu.iccid: 集成电路卡识别码（固化在手机SIM卡中）
     */
    public String getIccid() {
        return iccid;
    }

    /**<br/>
     * 字段: sdk_xuanwu.iccid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * @param iccid: 集成电路卡识别码（固化在手机SIM卡中）
     */
    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    /**
     * @return sdk_xuanwu.mobilephone: 手机号码
     */
    public String getMobilephone() {
        return mobilephone;
    }

    /**<br/>
     * 字段: sdk_xuanwu.mobilephone<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 11<br/>
     * @param mobilephone: 手机号码
     */
    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    /**
     * @return sdk_xuanwu.specNumber: 玄武分配的端口号
     */
    public String getSpecnumber() {
        return specnumber;
    }

    /**<br/>
     * 字段: sdk_xuanwu.specNumber<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 30<br/>
     * @param specnumber: 玄武分配的端口号
     */
    public void setSpecnumber(String specnumber) {
        this.specnumber = specnumber;
    }

    /**
     * @return sdk_xuanwu.created_time: 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**<br/>
     * 字段: sdk_xuanwu.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createdTime: 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return sdk_xuanwu.updated_time: 更改时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**<br/>
     * 字段: sdk_xuanwu.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param updatedTime: 更改时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}