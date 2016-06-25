package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkSmsGame {
    /**<br/>
     * 字段: sdk_sms_game.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_sms_game.game_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 游戏ID
     */
    private Integer gameId;

    /**<br/>
     * 字段: sdk_sms_game.send_state<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 是否发送短信获取手机号:1 发送，0 不发送
     */
    private Integer sendState;

    /**<br/>
     * 字段: sdk_sms_game.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createdTime;

    /**<br/>
     * 字段: sdk_sms_game.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 更改时间
     */
    private Date updatedTime;

    /**<br/>
     * 字段: sdk_sms_game.smstip<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 第一次登陆时发短信和联网提示框弹出状态:1-弹出,0-不弹出
     */
    private Integer smstip;

    /**
     * @return sdk_sms_game.id: 
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_sms_game.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param id: 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return sdk_sms_game.game_id: 游戏ID
     */
    public Integer getGameId() {
        return gameId;
    }

    /**<br/>
     * 字段: sdk_sms_game.game_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param gameId: 游戏ID
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return sdk_sms_game.send_state: 是否发送短信获取手机号:1 发送，0 不发送
     */
    public Integer getSendState() {
        return sendState;
    }

    /**<br/>
     * 字段: sdk_sms_game.send_state<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param sendState: 是否发送短信获取手机号:1 发送，0 不发送
     */
    public void setSendState(Integer sendState) {
        this.sendState = sendState;
    }

    /**
     * @return sdk_sms_game.created_time: 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**<br/>
     * 字段: sdk_sms_game.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createdTime: 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return sdk_sms_game.updated_time: 更改时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**<br/>
     * 字段: sdk_sms_game.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param updatedTime: 更改时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * @return sdk_sms_game.smstip: 第一次登陆时发短信和联网提示框弹出状态:1-弹出,0-不弹出
     */
    public Integer getSmstip() {
        return smstip;
    }

    /**<br/>
     * 字段: sdk_sms_game.smstip<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param smstip: 第一次登陆时发短信和联网提示框弹出状态:1-弹出,0-不弹出
     */
    public void setSmstip(Integer smstip) {
        this.smstip = smstip;
    }
}