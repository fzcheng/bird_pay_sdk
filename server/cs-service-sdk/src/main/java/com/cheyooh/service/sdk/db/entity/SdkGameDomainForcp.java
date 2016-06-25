package com.cheyooh.service.sdk.db.entity;

public class SdkGameDomainForcp {
    /**<br/>
     * 字段: sdk_game_domain_forcp.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_game_domain_forcp.game_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     */
    private Integer gameId;

    /**<br/>
     * 字段: sdk_game_domain_forcp.domain<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 
     */
    private String domain;

    /**<br/>
     * 字段: sdk_game_domain_forcp.STATUS<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     */
    private Integer status;

    /**
     * @return sdk_game_domain_forcp.id: 
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_game_domain_forcp.id<br/>
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
     * @return sdk_game_domain_forcp.game_id: 
     */
    public Integer getGameId() {
        return gameId;
    }

    /**<br/>
     * 字段: sdk_game_domain_forcp.game_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param gameId: 
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return sdk_game_domain_forcp.domain: 
     */
    public String getDomain() {
        return domain;
    }

    /**<br/>
     * 字段: sdk_game_domain_forcp.domain<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * @param domain: 
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @return sdk_game_domain_forcp.STATUS: 
     */
    public Integer getStatus() {
        return status;
    }

    /**<br/>
     * 字段: sdk_game_domain_forcp.STATUS<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param status: 
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}