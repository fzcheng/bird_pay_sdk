package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkPropsSetting {
    /**<br/>
     * 字段: sdk_props_setting.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_props_setting.props_name<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 道具名称
     */
    private String propsName;

    /**<br/>
     * 字段: sdk_props_setting.amount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * 说明: 单价(元)
     */
    private Float amount;

    /**<br/>
     * 字段: sdk_props_setting.props_desc<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 道具功能
     */
    private String propsDesc;

    /**<br/>
     * 字段: sdk_props_setting.props_alias<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 道具别名
     */
    private String propsAlias;

    /**<br/>
     * 字段: sdk_props_setting.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createdTime;

    /**<br/>
     * 字段: sdk_props_setting.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 更改时间
     */
    private Date updatedTime;

    /**<br/>
     * 字段: sdk_props_setting.game_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 游戏id
     */
    private Integer gameId;

    /**<br/>
     * 字段: sdk_props_setting.operator_pay_channel_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 运营商支付渠道配置id
     */
    private Integer operatorPayChannelId;

    /**<br/>
     * 字段: sdk_props_setting.use_status<br/>
     * 可空: true<br/>
     * 缺省: 1<br/>
     * 长度: 10<br/>
     * 说明: 使用状态，0：停用；1：使用
     */
    private Integer useStatus;

    /**<br/>
     * 字段: sdk_props_setting.propsid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 道具ID
     */
    private String propsid;

    /**
     * @return sdk_props_setting.id: 
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_props_setting.id<br/>
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
     * @return sdk_props_setting.props_name: 道具名称
     */
    public String getPropsName() {
        return propsName;
    }

    /**<br/>
     * 字段: sdk_props_setting.props_name<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param propsName: 道具名称
     */
    public void setPropsName(String propsName) {
        this.propsName = propsName;
    }

    /**
     * @return sdk_props_setting.amount: 单价(元)
     */
    public Float getAmount() {
        return amount;
    }

    /**<br/>
     * 字段: sdk_props_setting.amount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * @param amount: 单价(元)
     */
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    /**
     * @return sdk_props_setting.props_desc: 道具功能
     */
    public String getPropsDesc() {
        return propsDesc;
    }

    /**<br/>
     * 字段: sdk_props_setting.props_desc<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param propsDesc: 道具功能
     */
    public void setPropsDesc(String propsDesc) {
        this.propsDesc = propsDesc;
    }

    /**
     * @return sdk_props_setting.props_alias: 道具别名
     */
    public String getPropsAlias() {
        return propsAlias;
    }

    /**<br/>
     * 字段: sdk_props_setting.props_alias<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param propsAlias: 道具别名
     */
    public void setPropsAlias(String propsAlias) {
        this.propsAlias = propsAlias;
    }

    /**
     * @return sdk_props_setting.created_time: 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**<br/>
     * 字段: sdk_props_setting.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createdTime: 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return sdk_props_setting.updated_time: 更改时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**<br/>
     * 字段: sdk_props_setting.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param updatedTime: 更改时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * @return sdk_props_setting.game_id: 游戏id
     */
    public Integer getGameId() {
        return gameId;
    }

    /**<br/>
     * 字段: sdk_props_setting.game_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param gameId: 游戏id
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return sdk_props_setting.operator_pay_channel_id: 运营商支付渠道配置id
     */
    public Integer getOperatorPayChannelId() {
        return operatorPayChannelId;
    }

    /**<br/>
     * 字段: sdk_props_setting.operator_pay_channel_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param operatorPayChannelId: 运营商支付渠道配置id
     */
    public void setOperatorPayChannelId(Integer operatorPayChannelId) {
        this.operatorPayChannelId = operatorPayChannelId;
    }

    /**
     * @return sdk_props_setting.use_status: 使用状态，0：停用；1：使用
     */
    public Integer getUseStatus() {
        return useStatus;
    }

    /**<br/>
     * 字段: sdk_props_setting.use_status<br/>
     * 可空: true<br/>
     * 缺省: 1<br/>
     * 长度: 10<br/>
     * @param useStatus: 使用状态，0：停用；1：使用
     */
    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    /**
     * @return sdk_props_setting.propsid: 道具ID
     */
    public String getPropsid() {
        return propsid;
    }

    /**<br/>
     * 字段: sdk_props_setting.propsid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param propsid: 道具ID
     */
    public void setPropsid(String propsid) {
        this.propsid = propsid;
    }
}