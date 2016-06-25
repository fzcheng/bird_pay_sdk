package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkGameEmbedsdk {
    /**<br/>
     * 字段: sdk_game_embedsdk.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: ID
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_game_embedsdk.pay_channel_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 支付通道编号
     */
    private String payChannelCode;

    /**<br/>
     * 字段: sdk_game_embedsdk.game_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 游戏ID
     */
    private Integer gameId;

    /**<br/>
     * 字段: sdk_game_embedsdk.appid<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 外接SDK的唯一标识
     */
    private String appid;

    /**<br/>
     * 字段: sdk_game_embedsdk.appkey<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: key
     */
    private String appkey;

    /**<br/>
     * 字段: sdk_game_embedsdk.appsecret<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 512<br/>
     * 说明: secret
     */
    private String appsecret;

    /**<br/>
     * 字段: sdk_game_embedsdk.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createdTime;

    /**<br/>
     * 字段: sdk_game_embedsdk.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 修改时间
     */
    private Date updatedTime;

    /**
     * @return sdk_game_embedsdk.id: ID
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_game_embedsdk.id<br/>
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
     * @return sdk_game_embedsdk.pay_channel_code: 支付通道编号
     */
    public String getPayChannelCode() {
        return payChannelCode;
    }

    /**<br/>
     * 字段: sdk_game_embedsdk.pay_channel_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param payChannelCode: 支付通道编号
     */
    public void setPayChannelCode(String payChannelCode) {
        this.payChannelCode = payChannelCode;
    }

    /**
     * @return sdk_game_embedsdk.game_id: 游戏ID
     */
    public Integer getGameId() {
        return gameId;
    }

    /**<br/>
     * 字段: sdk_game_embedsdk.game_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param gameId: 游戏ID
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return sdk_game_embedsdk.appid: 外接SDK的唯一标识
     */
    public String getAppid() {
        return appid;
    }

    /**<br/>
     * 字段: sdk_game_embedsdk.appid<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param appid: 外接SDK的唯一标识
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    /**
     * @return sdk_game_embedsdk.appkey: key
     */
    public String getAppkey() {
        return appkey;
    }

    /**<br/>
     * 字段: sdk_game_embedsdk.appkey<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * @param appkey: key
     */
    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    /**
     * @return sdk_game_embedsdk.appsecret: secret
     */
    public String getAppsecret() {
        return appsecret;
    }

    /**<br/>
     * 字段: sdk_game_embedsdk.appsecret<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 512<br/>
     * @param appsecret: secret
     */
    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    /**
     * @return sdk_game_embedsdk.created_time: 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**<br/>
     * 字段: sdk_game_embedsdk.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createdTime: 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return sdk_game_embedsdk.updated_time: 修改时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**<br/>
     * 字段: sdk_game_embedsdk.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param updatedTime: 修改时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}