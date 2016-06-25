package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkUserLoginGame extends SdkUserLoginGameKey {
    /**<br/>
     * 字段: sdk_user_login_game.first_login_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 首次登录该游戏的时间
     */
    private Date firstLoginTime;

    /**<br/>
     * 字段: sdk_user_login_game.first_login_channel<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 首次登录该游戏的渠道
     */
    private String firstLoginChannel;

    /**<br/>
     * 字段: sdk_user_login_game.first_login_version<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 首次登录该游戏的版本
     */
    private String firstLoginVersion;

    /**<br/>
     * 字段: sdk_user_login_game.last_login_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 最后一次登录该游戏的时间
     */
    private Date lastLoginTime;

    /**<br/>
     * 字段: sdk_user_login_game.last_login_channel<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 最后登录该游戏的渠道
     */
    private String lastLoginChannel;

    /**<br/>
     * 字段: sdk_user_login_game.last_login_version<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 最后登录该游戏的版本
     */
    private String lastLoginVersion;

    /**<br/>
     * 字段: sdk_user_login_game.login_times<br/>
     * 可空: false<br/>
     * 缺省: 0<br/>
     * 说明: 登录次数
     */
    private Integer loginTimes;

    /**
     * @return sdk_user_login_game.first_login_time: 首次登录该游戏的时间
     */
    public Date getFirstLoginTime() {
        return firstLoginTime;
    }

    /**<br/>
     * 字段: sdk_user_login_game.first_login_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param firstLoginTime: 首次登录该游戏的时间
     */
    public void setFirstLoginTime(Date firstLoginTime) {
        this.firstLoginTime = firstLoginTime;
    }

    /**
     * @return sdk_user_login_game.first_login_channel: 首次登录该游戏的渠道
     */
    public String getFirstLoginChannel() {
        return firstLoginChannel;
    }

    /**<br/>
     * 字段: sdk_user_login_game.first_login_channel<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param firstLoginChannel: 首次登录该游戏的渠道
     */
    public void setFirstLoginChannel(String firstLoginChannel) {
        this.firstLoginChannel = firstLoginChannel;
    }

    /**
     * @return sdk_user_login_game.first_login_version: 首次登录该游戏的版本
     */
    public String getFirstLoginVersion() {
        return firstLoginVersion;
    }

    /**<br/>
     * 字段: sdk_user_login_game.first_login_version<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param firstLoginVersion: 首次登录该游戏的版本
     */
    public void setFirstLoginVersion(String firstLoginVersion) {
        this.firstLoginVersion = firstLoginVersion;
    }

    /**
     * @return sdk_user_login_game.last_login_time: 最后一次登录该游戏的时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**<br/>
     * 字段: sdk_user_login_game.last_login_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param lastLoginTime: 最后一次登录该游戏的时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * @return sdk_user_login_game.last_login_channel: 最后登录该游戏的渠道
     */
    public String getLastLoginChannel() {
        return lastLoginChannel;
    }

    /**<br/>
     * 字段: sdk_user_login_game.last_login_channel<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param lastLoginChannel: 最后登录该游戏的渠道
     */
    public void setLastLoginChannel(String lastLoginChannel) {
        this.lastLoginChannel = lastLoginChannel;
    }

    /**
     * @return sdk_user_login_game.last_login_version: 最后登录该游戏的版本
     */
    public String getLastLoginVersion() {
        return lastLoginVersion;
    }

    /**<br/>
     * 字段: sdk_user_login_game.last_login_version<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param lastLoginVersion: 最后登录该游戏的版本
     */
    public void setLastLoginVersion(String lastLoginVersion) {
        this.lastLoginVersion = lastLoginVersion;
    }

    /**
     * @return sdk_user_login_game.login_times: 登录次数
     */
    public Integer getLoginTimes() {
        return loginTimes;
    }

    /**<br/>
     * 字段: sdk_user_login_game.login_times<br/>
     * 可空: false<br/>
     * 缺省: 0<br/>
     * @param loginTimes: 登录次数
     */
    public void setLoginTimes(Integer loginTimes) {
        this.loginTimes = loginTimes;
    }
}