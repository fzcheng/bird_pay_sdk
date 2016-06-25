package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkGamePayrepeat {
    /**<br/>
     * 字段: sdk_game_payrepeat.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_game_payrepeat.game_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 游戏ID
     */
    private Integer gameId;

    /**<br/>
     * 字段: sdk_game_payrepeat.usestate<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 是否使用本通道支付失败后选择下一个支付通道功能
     */
    private Integer usestate;

    /**<br/>
     * 字段: sdk_game_payrepeat.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createdTime;

    /**<br/>
     * 字段: sdk_game_payrepeat.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 更改时间
     */
    private Date updatedTime;

    /**
     * @return sdk_game_payrepeat.id: 
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_game_payrepeat.id<br/>
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
     * @return sdk_game_payrepeat.game_id: 游戏ID
     */
    public Integer getGameId() {
        return gameId;
    }

    /**<br/>
     * 字段: sdk_game_payrepeat.game_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param gameId: 游戏ID
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return sdk_game_payrepeat.usestate: 是否使用本通道支付失败后选择下一个支付通道功能
     */
    public Integer getUsestate() {
        return usestate;
    }

    /**<br/>
     * 字段: sdk_game_payrepeat.usestate<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param usestate: 是否使用本通道支付失败后选择下一个支付通道功能
     */
    public void setUsestate(Integer usestate) {
        this.usestate = usestate;
    }

    /**
     * @return sdk_game_payrepeat.created_time: 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**<br/>
     * 字段: sdk_game_payrepeat.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createdTime: 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return sdk_game_payrepeat.updated_time: 更改时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**<br/>
     * 字段: sdk_game_payrepeat.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param updatedTime: 更改时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}