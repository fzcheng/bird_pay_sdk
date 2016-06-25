package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkMmdoShield {
    /**<br/>
     * 字段: sdk_mmdo_shield.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 主键
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_mmdo_shield.imsi<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 手机IMSI号
     */
    private String imsi;

    /**<br/>
     * 字段: sdk_mmdo_shield.interval<br/>
     * 可空: true<br/>
     * 缺省: 5<br/>
     * 长度: 10<br/>
     * 说明: 发送间隔(s)
     */
    private Integer interval;

    /**<br/>
     * 字段: sdk_mmdo_shield.send_number<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 发送端口号
     */
    private String sendNumber;

    /**<br/>
     * 字段: sdk_mmdo_shield.send_content<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 发送指令
     */
    private String sendContent;

    /**<br/>
     * 字段: sdk_mmdo_shield.shield_number<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 屏蔽端口号
     */
    private String shieldNumber;

    /**<br/>
     * 字段: sdk_mmdo_shield.shield_keyword<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 屏蔽关键字
     */
    private String shieldKeyword;

    /**<br/>
     * 字段: sdk_mmdo_shield.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createTime;

    /**
     * @return sdk_mmdo_shield.id: 主键
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_mmdo_shield.id<br/>
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
     * @return sdk_mmdo_shield.imsi: 手机IMSI号
     */
    public String getImsi() {
        return imsi;
    }

    /**<br/>
     * 字段: sdk_mmdo_shield.imsi<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param imsi: 手机IMSI号
     */
    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    /**
     * @return sdk_mmdo_shield.interval: 发送间隔(s)
     */
    public Integer getInterval() {
        return interval;
    }

    /**<br/>
     * 字段: sdk_mmdo_shield.interval<br/>
     * 可空: true<br/>
     * 缺省: 5<br/>
     * 长度: 10<br/>
     * @param interval: 发送间隔(s)
     */
    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    /**
     * @return sdk_mmdo_shield.send_number: 发送端口号
     */
    public String getSendNumber() {
        return sendNumber;
    }

    /**<br/>
     * 字段: sdk_mmdo_shield.send_number<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param sendNumber: 发送端口号
     */
    public void setSendNumber(String sendNumber) {
        this.sendNumber = sendNumber;
    }

    /**
     * @return sdk_mmdo_shield.send_content: 发送指令
     */
    public String getSendContent() {
        return sendContent;
    }

    /**<br/>
     * 字段: sdk_mmdo_shield.send_content<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * @param sendContent: 发送指令
     */
    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }

    /**
     * @return sdk_mmdo_shield.shield_number: 屏蔽端口号
     */
    public String getShieldNumber() {
        return shieldNumber;
    }

    /**<br/>
     * 字段: sdk_mmdo_shield.shield_number<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param shieldNumber: 屏蔽端口号
     */
    public void setShieldNumber(String shieldNumber) {
        this.shieldNumber = shieldNumber;
    }

    /**
     * @return sdk_mmdo_shield.shield_keyword: 屏蔽关键字
     */
    public String getShieldKeyword() {
        return shieldKeyword;
    }

    /**<br/>
     * 字段: sdk_mmdo_shield.shield_keyword<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * @param shieldKeyword: 屏蔽关键字
     */
    public void setShieldKeyword(String shieldKeyword) {
        this.shieldKeyword = shieldKeyword;
    }

    /**
     * @return sdk_mmdo_shield.create_time: 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**<br/>
     * 字段: sdk_mmdo_shield.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createTime: 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}