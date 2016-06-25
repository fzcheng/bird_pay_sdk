package com.cheyooh.service.sdk.db.entity;

public class SdkGamePaysetting {
    /**<br/>
     * 字段: sdk_game_paysetting.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 主键
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_game_paysetting.game_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 游戏id
     */
    private Integer gameId;

    /**<br/>
     * 字段: sdk_game_paysetting.paysettingid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 游戏支付配置id
     */
    private Integer paysettingid;

    /**<br/>
     * 字段: sdk_game_paysetting.use_status<br/>
     * 可空: true<br/>
     * 缺省: 1<br/>
     * 长度: 10<br/>
     * 说明: 使用状态，0：停用；1：使用
     */
    private Integer useStatus;

    /**
     * @return sdk_game_paysetting.id: 主键
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_game_paysetting.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param id: 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return sdk_game_paysetting.game_id: 游戏id
     */
    public Integer getGameId() {
        return gameId;
    }

    /**<br/>
     * 字段: sdk_game_paysetting.game_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param gameId: 游戏id
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return sdk_game_paysetting.paysettingid: 游戏支付配置id
     */
    public Integer getPaysettingid() {
        return paysettingid;
    }

    /**<br/>
     * 字段: sdk_game_paysetting.paysettingid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param paysettingid: 游戏支付配置id
     */
    public void setPaysettingid(Integer paysettingid) {
        this.paysettingid = paysettingid;
    }

    /**
     * @return sdk_game_paysetting.use_status: 使用状态，0：停用；1：使用
     */
    public Integer getUseStatus() {
        return useStatus;
    }

    /**<br/>
     * 字段: sdk_game_paysetting.use_status<br/>
     * 可空: true<br/>
     * 缺省: 1<br/>
     * 长度: 10<br/>
     * @param useStatus: 使用状态，0：停用；1：使用
     */
    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }
}