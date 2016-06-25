package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkUserAuto {
    /**<br/>
     * 字段: sdk_user_auto.device_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 后台自动注册产生用户ID
     */
    private String deviceId;

    /**<br/>
     * 字段: sdk_user_auto.imei<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 手机IMEI号
     */
    private String imei;

    /**<br/>
     * 字段: sdk_user_auto.reg_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 注册时间
     */
    private Date regTime;

    /**<br/>
     * 字段: sdk_user_auto.reg_game<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 注册游戏ID
     */
    private Integer regGame;

    /**<br/>
     * 字段: sdk_user_auto.reg_channel<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 注册渠道
     */
    private String regChannel;

    /**<br/>
     * 字段: sdk_user_auto.reg_version<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 
     */
    private String regVersion;

    /**<br/>
     * 字段: sdk_user_auto.reg_ip<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 注册IP
     */
    private String regIp;

    /**
     * @return sdk_user_auto.device_id: 后台自动注册产生用户ID
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**<br/>
     * 字段: sdk_user_auto.device_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * @param deviceId: 后台自动注册产生用户ID
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return sdk_user_auto.imei: 手机IMEI号
     */
    public String getImei() {
        return imei;
    }

    /**<br/>
     * 字段: sdk_user_auto.imei<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param imei: 手机IMEI号
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * @return sdk_user_auto.reg_time: 注册时间
     */
    public Date getRegTime() {
        return regTime;
    }

    /**<br/>
     * 字段: sdk_user_auto.reg_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param regTime: 注册时间
     */
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    /**
     * @return sdk_user_auto.reg_game: 注册游戏ID
     */
    public Integer getRegGame() {
        return regGame;
    }

    /**<br/>
     * 字段: sdk_user_auto.reg_game<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param regGame: 注册游戏ID
     */
    public void setRegGame(Integer regGame) {
        this.regGame = regGame;
    }

    /**
     * @return sdk_user_auto.reg_channel: 注册渠道
     */
    public String getRegChannel() {
        return regChannel;
    }

    /**<br/>
     * 字段: sdk_user_auto.reg_channel<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param regChannel: 注册渠道
     */
    public void setRegChannel(String regChannel) {
        this.regChannel = regChannel;
    }

    /**
     * @return sdk_user_auto.reg_version: 
     */
    public String getRegVersion() {
        return regVersion;
    }

    /**<br/>
     * 字段: sdk_user_auto.reg_version<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param regVersion: 
     */
    public void setRegVersion(String regVersion) {
        this.regVersion = regVersion;
    }

    /**
     * @return sdk_user_auto.reg_ip: 注册IP
     */
    public String getRegIp() {
        return regIp;
    }

    /**<br/>
     * 字段: sdk_user_auto.reg_ip<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param regIp: 注册IP
     */
    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }
}