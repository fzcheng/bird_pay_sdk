package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkGameAdvertisementList {
    /**<br/>
     * 字段: sdk_game_advertisement_list.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_game_advertisement_list.advertisement_url<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 广告地址
     */
    private String advertisementUrl;

    /**<br/>
     * 字段: sdk_game_advertisement_list.icon_url<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 广告图片地址
     */
    private String iconUrl;

    /**<br/>
     * 字段: sdk_game_advertisement_list.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createdTime;

    /**<br/>
     * 字段: sdk_game_advertisement_list.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 更改时间
     */
    private Date updatedTime;

    /**<br/>
     * 字段: sdk_game_advertisement_list.ifshow<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 是否展示,0:不展示 1:显示
     */
    private Integer ifshow;

    /**
     * @return sdk_game_advertisement_list.id: 
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_game_advertisement_list.id<br/>
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
     * @return sdk_game_advertisement_list.advertisement_url: 广告地址
     */
    public String getAdvertisementUrl() {
        return advertisementUrl;
    }

    /**<br/>
     * 字段: sdk_game_advertisement_list.advertisement_url<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * @param advertisementUrl: 广告地址
     */
    public void setAdvertisementUrl(String advertisementUrl) {
        this.advertisementUrl = advertisementUrl;
    }

    /**
     * @return sdk_game_advertisement_list.icon_url: 广告图片地址
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**<br/>
     * 字段: sdk_game_advertisement_list.icon_url<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * @param iconUrl: 广告图片地址
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    /**
     * @return sdk_game_advertisement_list.created_time: 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**<br/>
     * 字段: sdk_game_advertisement_list.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createdTime: 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return sdk_game_advertisement_list.updated_time: 更改时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**<br/>
     * 字段: sdk_game_advertisement_list.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param updatedTime: 更改时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * @return sdk_game_advertisement_list.ifshow: 是否展示,0:不展示 1:显示
     */
    public Integer getIfshow() {
        return ifshow;
    }

    /**<br/>
     * 字段: sdk_game_advertisement_list.ifshow<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param ifshow: 是否展示,0:不展示 1:显示
     */
    public void setIfshow(Integer ifshow) {
        this.ifshow = ifshow;
    }
}