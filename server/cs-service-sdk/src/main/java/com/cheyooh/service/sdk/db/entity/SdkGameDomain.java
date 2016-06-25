package com.cheyooh.service.sdk.db.entity;

public class SdkGameDomain {
    /**<br/>
     * 字段: sdk_game_domain.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_game_domain.domain<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 
     */
    private String domain;

    /**<br/>
     * 字段: sdk_game_domain.status<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     */
    private Integer status;

    /**
     * @return sdk_game_domain.id: 
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_game_domain.id<br/>
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
     * @return sdk_game_domain.domain: 
     */
    public String getDomain() {
        return domain;
    }

    /**<br/>
     * 字段: sdk_game_domain.domain<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * @param domain: 
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @return sdk_game_domain.status: 
     */
    public Integer getStatus() {
        return status;
    }

    /**<br/>
     * 字段: sdk_game_domain.status<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param status: 
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}