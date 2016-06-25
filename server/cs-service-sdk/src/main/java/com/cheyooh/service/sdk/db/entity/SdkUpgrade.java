package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkUpgrade {
    /**<br/>
     * 字段: sdk_upgrade.upgrade_id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 自增主键
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer upgradeId;

    /**<br/>
     * 字段: sdk_upgrade.game_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 游戏ID
     */
    private Integer gameId;

    /**<br/>
     * 字段: sdk_upgrade.channel<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 渠道号(支持正在表达式)
     */
    private String channel;

    /**<br/>
     * 字段: sdk_upgrade.regex<br/>
     * 可空: false<br/>
     * 缺省: 0<br/>
     * 说明: 是否采用正则表达式匹配渠道号(1-是,0-否)
     */
    private Integer regex;

    /**<br/>
     * 字段: sdk_upgrade.version<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 最新版本号
     */
    private String version;

    /**<br/>
     * 字段: sdk_upgrade.version_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 版本号(判断版本新旧的依据)
     */
    private Integer versionCode;

    /**<br/>
     * 字段: sdk_upgrade.force_tag<br/>
     * 可空: false<br/>
     * 缺省: 0<br/>
     * 说明: 升级类型:
0-不升级
1-建议升级
2-强制升级
     */
    private Integer forceTag;

    /**<br/>
     * 字段: sdk_upgrade.down_url<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 下载地址
     */
    private String downUrl;

    /**<br/>
     * 字段: sdk_upgrade.status_tag<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 状态(0-禁用, 1-启用)
     */
    private Integer statusTag;

    /**<br/>
     * 字段: sdk_upgrade.memo<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 
     */
    private String memo;

    /**<br/>
     * 字段: sdk_upgrade.create_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 增加时间
     */
    private Date createTime;

    /**
     * @return sdk_upgrade.upgrade_id: 自增主键
     */
    public Integer getUpgradeId() {
        return upgradeId;
    }

    /**<br/>
     * 字段: sdk_upgrade.upgrade_id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param upgradeId: 自增主键
     */
    public void setUpgradeId(Integer upgradeId) {
        this.upgradeId = upgradeId;
    }

    /**
     * @return sdk_upgrade.game_id: 游戏ID
     */
    public Integer getGameId() {
        return gameId;
    }

    /**<br/>
     * 字段: sdk_upgrade.game_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param gameId: 游戏ID
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return sdk_upgrade.channel: 渠道号(支持正在表达式)
     */
    public String getChannel() {
        return channel;
    }

    /**<br/>
     * 字段: sdk_upgrade.channel<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param channel: 渠道号(支持正在表达式)
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * @return sdk_upgrade.regex: 是否采用正则表达式匹配渠道号(1-是,0-否)
     */
    public Integer getRegex() {
        return regex;
    }

    /**<br/>
     * 字段: sdk_upgrade.regex<br/>
     * 可空: false<br/>
     * 缺省: 0<br/>
     * @param regex: 是否采用正则表达式匹配渠道号(1-是,0-否)
     */
    public void setRegex(Integer regex) {
        this.regex = regex;
    }

    /**
     * @return sdk_upgrade.version: 最新版本号
     */
    public String getVersion() {
        return version;
    }

    /**<br/>
     * 字段: sdk_upgrade.version<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param version: 最新版本号
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return sdk_upgrade.version_code: 版本号(判断版本新旧的依据)
     */
    public Integer getVersionCode() {
        return versionCode;
    }

    /**<br/>
     * 字段: sdk_upgrade.version_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param versionCode: 版本号(判断版本新旧的依据)
     */
    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    /**
     * @return sdk_upgrade.force_tag: 升级类型:
0-不升级
1-建议升级
2-强制升级
     */
    public Integer getForceTag() {
        return forceTag;
    }

    /**<br/>
     * 字段: sdk_upgrade.force_tag<br/>
     * 可空: false<br/>
     * 缺省: 0<br/>
     * @param forceTag: 升级类型:
0-不升级
1-建议升级
2-强制升级
     */
    public void setForceTag(Integer forceTag) {
        this.forceTag = forceTag;
    }

    /**
     * @return sdk_upgrade.down_url: 下载地址
     */
    public String getDownUrl() {
        return downUrl;
    }

    /**<br/>
     * 字段: sdk_upgrade.down_url<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param downUrl: 下载地址
     */
    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    /**
     * @return sdk_upgrade.status_tag: 状态(0-禁用, 1-启用)
     */
    public Integer getStatusTag() {
        return statusTag;
    }

    /**<br/>
     * 字段: sdk_upgrade.status_tag<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param statusTag: 状态(0-禁用, 1-启用)
     */
    public void setStatusTag(Integer statusTag) {
        this.statusTag = statusTag;
    }

    /**
     * @return sdk_upgrade.memo: 
     */
    public String getMemo() {
        return memo;
    }

    /**<br/>
     * 字段: sdk_upgrade.memo<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param memo: 
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return sdk_upgrade.create_time: 增加时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**<br/>
     * 字段: sdk_upgrade.create_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param createTime: 增加时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}