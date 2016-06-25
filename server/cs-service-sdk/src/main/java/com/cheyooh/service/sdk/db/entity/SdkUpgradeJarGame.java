package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkUpgradeJarGame {
    /**<br/>
     * 字段: sdk_upgrade_jar_game.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 自增主键
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_upgrade_jar_game.version_code<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 版本号(判断版本新旧的依据)
     */
    private String versionCode;

    /**<br/>
     * 字段: sdk_upgrade_jar_game.game_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 游戏ID
     */
    private Integer gameId;

    /**<br/>
     * 字段: sdk_upgrade_jar_game.status_tag<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 状态(0-禁用, 1-启用)
     */
    private Integer statusTag;

    /**<br/>
     * 字段: sdk_upgrade_jar_game.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 增加时间
     */
    private Date createTime;

    /**<br/>
     * 字段: sdk_upgrade_jar_game.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 更改时间
     */
    private Date updatedTime;

    /**
     * @return sdk_upgrade_jar_game.id: 自增主键
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_upgrade_jar_game.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param id: 自增主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return sdk_upgrade_jar_game.version_code: 版本号(判断版本新旧的依据)
     */
    public String getVersionCode() {
        return versionCode;
    }

    /**<br/>
     * 字段: sdk_upgrade_jar_game.version_code<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param versionCode: 版本号(判断版本新旧的依据)
     */
    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    /**
     * @return sdk_upgrade_jar_game.game_id: 游戏ID
     */
    public Integer getGameId() {
        return gameId;
    }

    /**<br/>
     * 字段: sdk_upgrade_jar_game.game_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param gameId: 游戏ID
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return sdk_upgrade_jar_game.status_tag: 状态(0-禁用, 1-启用)
     */
    public Integer getStatusTag() {
        return statusTag;
    }

    /**<br/>
     * 字段: sdk_upgrade_jar_game.status_tag<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param statusTag: 状态(0-禁用, 1-启用)
     */
    public void setStatusTag(Integer statusTag) {
        this.statusTag = statusTag;
    }

    /**
     * @return sdk_upgrade_jar_game.create_time: 增加时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**<br/>
     * 字段: sdk_upgrade_jar_game.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createTime: 增加时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return sdk_upgrade_jar_game.updated_time: 更改时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**<br/>
     * 字段: sdk_upgrade_jar_game.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param updatedTime: 更改时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}