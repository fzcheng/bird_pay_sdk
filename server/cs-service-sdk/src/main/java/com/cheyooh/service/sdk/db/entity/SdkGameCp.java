package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkGameCp {
    /**<br/>
     * 字段: sdk_game_cp.cp_id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 游戏商ID
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer cpId;

    /**<br/>
     * 字段: sdk_game_cp.name<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 游戏商名称
     */
    private String name;

    /**<br/>
     * 字段: sdk_game_cp.api_key<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 用于游戏服务器和SDK服务器交互的Key
     */
    private String apiKey;

    /**<br/>
     * 字段: sdk_game_cp.pwd<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 后台登录密码MD5值
     */
    private String pwd;

    /**<br/>
     * 字段: sdk_game_cp.email<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 邮件地址
     */
    private String email;

    /**<br/>
     * 字段: sdk_game_cp.create_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 创建时间
     */
    private Date createTime;

    /**<br/>
     * 字段: sdk_game_cp.memo<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 1024<br/>
     * 说明: 备注
     */
    private String memo;

    /**
     * @return sdk_game_cp.cp_id: 游戏商ID
     */
    public Integer getCpId() {
        return cpId;
    }

    /**<br/>
     * 字段: sdk_game_cp.cp_id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param cpId: 游戏商ID
     */
    public void setCpId(Integer cpId) {
        this.cpId = cpId;
    }

    /**
     * @return sdk_game_cp.name: 游戏商名称
     */
    public String getName() {
        return name;
    }

    /**<br/>
     * 字段: sdk_game_cp.name<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * @param name: 游戏商名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return sdk_game_cp.api_key: 用于游戏服务器和SDK服务器交互的Key
     */
    public String getApiKey() {
        return apiKey;
    }

    /**<br/>
     * 字段: sdk_game_cp.api_key<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param apiKey: 用于游戏服务器和SDK服务器交互的Key
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * @return sdk_game_cp.pwd: 后台登录密码MD5值
     */
    public String getPwd() {
        return pwd;
    }

    /**<br/>
     * 字段: sdk_game_cp.pwd<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param pwd: 后台登录密码MD5值
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * @return sdk_game_cp.email: 邮件地址
     */
    public String getEmail() {
        return email;
    }

    /**<br/>
     * 字段: sdk_game_cp.email<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param email: 邮件地址
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return sdk_game_cp.create_time: 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**<br/>
     * 字段: sdk_game_cp.create_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param createTime: 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return sdk_game_cp.memo: 备注
     */
    public String getMemo() {
        return memo;
    }

    /**<br/>
     * 字段: sdk_game_cp.memo<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 1024<br/>
     * @param memo: 备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }
}