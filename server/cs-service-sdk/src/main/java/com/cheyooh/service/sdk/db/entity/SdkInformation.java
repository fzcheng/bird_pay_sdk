package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkInformation {
    /**<br/>
     * 字段: sdk_information.info_id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 资讯ID(自增主键)
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer infoId;

    /**<br/>
     * 字段: sdk_information.game_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 游戏ID
     */
    private Integer gameId;

    /**<br/>
     * 字段: sdk_information.title<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 资讯标题
     */
    private String title;

    /**<br/>
     * 字段: sdk_information.type<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 资讯类型(新闻/攻略等)
     */
    private String type;

    /**<br/>
     * 字段: sdk_information.detail<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 2048<br/>
     * 说明: 资讯简介
     */
    private String detail;

    /**<br/>
     * 字段: sdk_information.weburl<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 1024<br/>
     * 说明: 资讯链接
     */
    private String weburl;

    /**<br/>
     * 字段: sdk_information.create_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 资讯时间(倒序排列)
     */
    private Date createTime;

    /**<br/>
     * 字段: sdk_information.status<br/>
     * 可空: false<br/>
     * 缺省: 1<br/>
     * 说明: 状态(1-有效,0-无效)
     */
    private Integer status;

    /**
     * @return sdk_information.info_id: 资讯ID(自增主键)
     */
    public Integer getInfoId() {
        return infoId;
    }

    /**<br/>
     * 字段: sdk_information.info_id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param infoId: 资讯ID(自增主键)
     */
    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    /**
     * @return sdk_information.game_id: 游戏ID
     */
    public Integer getGameId() {
        return gameId;
    }

    /**<br/>
     * 字段: sdk_information.game_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param gameId: 游戏ID
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return sdk_information.title: 资讯标题
     */
    public String getTitle() {
        return title;
    }

    /**<br/>
     * 字段: sdk_information.title<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param title: 资讯标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return sdk_information.type: 资讯类型(新闻/攻略等)
     */
    public String getType() {
        return type;
    }

    /**<br/>
     * 字段: sdk_information.type<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param type: 资讯类型(新闻/攻略等)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return sdk_information.detail: 资讯简介
     */
    public String getDetail() {
        return detail;
    }

    /**<br/>
     * 字段: sdk_information.detail<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 2048<br/>
     * @param detail: 资讯简介
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return sdk_information.weburl: 资讯链接
     */
    public String getWeburl() {
        return weburl;
    }

    /**<br/>
     * 字段: sdk_information.weburl<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 1024<br/>
     * @param weburl: 资讯链接
     */
    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    /**
     * @return sdk_information.create_time: 资讯时间(倒序排列)
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**<br/>
     * 字段: sdk_information.create_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param createTime: 资讯时间(倒序排列)
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return sdk_information.status: 状态(1-有效,0-无效)
     */
    public Integer getStatus() {
        return status;
    }

    /**<br/>
     * 字段: sdk_information.status<br/>
     * 可空: false<br/>
     * 缺省: 1<br/>
     * @param status: 状态(1-有效,0-无效)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}