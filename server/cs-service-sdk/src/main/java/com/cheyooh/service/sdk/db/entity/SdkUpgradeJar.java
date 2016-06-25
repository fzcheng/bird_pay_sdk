package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkUpgradeJar {
    /**<br/>
     * 字段: sdk_upgrade_jar.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 自增主键
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_upgrade_jar.version_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 版本号(判断版本新旧的依据)
     */
    private String versionCode;

    /**<br/>
     * 字段: sdk_upgrade_jar.down_url<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 下载地址
     */
    private String downUrl;

    /**<br/>
     * 字段: sdk_upgrade_jar.status_tag<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 状态(0-禁用, 1-启用)
     */
    private Integer statusTag;

    /**<br/>
     * 字段: sdk_upgrade_jar.memo<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 备注
     */
    private String memo;

    /**<br/>
     * 字段: sdk_upgrade_jar.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 增加时间
     */
    private Date createTime;

    /**<br/>
     * 字段: sdk_upgrade_jar.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 更改时间
     */
    private Date updatedTime;

    /**
     * @return sdk_upgrade_jar.id: 自增主键
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_upgrade_jar.id<br/>
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
     * @return sdk_upgrade_jar.version_code: 版本号(判断版本新旧的依据)
     */
    public String getVersionCode() {
        return versionCode;
    }

    /**<br/>
     * 字段: sdk_upgrade_jar.version_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param versionCode: 版本号(判断版本新旧的依据)
     */
    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    /**
     * @return sdk_upgrade_jar.down_url: 下载地址
     */
    public String getDownUrl() {
        return downUrl;
    }

    /**<br/>
     * 字段: sdk_upgrade_jar.down_url<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param downUrl: 下载地址
     */
    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    /**
     * @return sdk_upgrade_jar.status_tag: 状态(0-禁用, 1-启用)
     */
    public Integer getStatusTag() {
        return statusTag;
    }

    /**<br/>
     * 字段: sdk_upgrade_jar.status_tag<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param statusTag: 状态(0-禁用, 1-启用)
     */
    public void setStatusTag(Integer statusTag) {
        this.statusTag = statusTag;
    }

    /**
     * @return sdk_upgrade_jar.memo: 备注
     */
    public String getMemo() {
        return memo;
    }

    /**<br/>
     * 字段: sdk_upgrade_jar.memo<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param memo: 备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return sdk_upgrade_jar.create_time: 增加时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**<br/>
     * 字段: sdk_upgrade_jar.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createTime: 增加时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return sdk_upgrade_jar.updated_time: 更改时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**<br/>
     * 字段: sdk_upgrade_jar.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param updatedTime: 更改时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}