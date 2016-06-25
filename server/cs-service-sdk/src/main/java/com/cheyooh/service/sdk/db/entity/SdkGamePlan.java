package com.cheyooh.service.sdk.db.entity;

public class SdkGamePlan {
    /**<br/>
     * 字段: sdk_game_plan.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_game_plan.plan_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 方案ID
     */
    private Integer planId;

    /**<br/>
     * 字段: sdk_game_plan.game_pkg<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 游戏包名
     */
    private String gamePkg;

    /**<br/>
     * 字段: sdk_game_plan.game_name<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 游戏名称
     */
    private String gameName;

    /**<br/>
     * 字段: sdk_game_plan.game_url<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 下载URL
     */
    private String gameUrl;

    /**<br/>
     * 字段: sdk_game_plan.game_icon<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 图标
     */
    private String gameIcon;

    /**<br/>
     * 字段: sdk_game_plan.game_rating<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 星级(1-10)
     */
    private Integer gameRating;

    /**<br/>
     * 字段: sdk_game_plan.game_category<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 游戏类别
     */
    private String gameCategory;

    /**<br/>
     * 字段: sdk_game_plan.idx<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 顺序位(从小到大)
     */
    private Integer idx;

    /**
     * @return sdk_game_plan.id: 
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_game_plan.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param id: 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return sdk_game_plan.plan_id: 方案ID
     */
    public Integer getPlanId() {
        return planId;
    }

    /**<br/>
     * 字段: sdk_game_plan.plan_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param planId: 方案ID
     */
    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    /**
     * @return sdk_game_plan.game_pkg: 游戏包名
     */
    public String getGamePkg() {
        return gamePkg;
    }

    /**<br/>
     * 字段: sdk_game_plan.game_pkg<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param gamePkg: 游戏包名
     */
    public void setGamePkg(String gamePkg) {
        this.gamePkg = gamePkg;
    }

    /**
     * @return sdk_game_plan.game_name: 游戏名称
     */
    public String getGameName() {
        return gameName;
    }

    /**<br/>
     * 字段: sdk_game_plan.game_name<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param gameName: 游戏名称
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * @return sdk_game_plan.game_url: 下载URL
     */
    public String getGameUrl() {
        return gameUrl;
    }

    /**<br/>
     * 字段: sdk_game_plan.game_url<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param gameUrl: 下载URL
     */
    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }

    /**
     * @return sdk_game_plan.game_icon: 图标
     */
    public String getGameIcon() {
        return gameIcon;
    }

    /**<br/>
     * 字段: sdk_game_plan.game_icon<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param gameIcon: 图标
     */
    public void setGameIcon(String gameIcon) {
        this.gameIcon = gameIcon;
    }

    /**
     * @return sdk_game_plan.game_rating: 星级(1-10)
     */
    public Integer getGameRating() {
        return gameRating;
    }

    /**<br/>
     * 字段: sdk_game_plan.game_rating<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param gameRating: 星级(1-10)
     */
    public void setGameRating(Integer gameRating) {
        this.gameRating = gameRating;
    }

    /**
     * @return sdk_game_plan.game_category: 游戏类别
     */
    public String getGameCategory() {
        return gameCategory;
    }

    /**<br/>
     * 字段: sdk_game_plan.game_category<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * @param gameCategory: 游戏类别
     */
    public void setGameCategory(String gameCategory) {
        this.gameCategory = gameCategory;
    }

    /**
     * @return sdk_game_plan.idx: 顺序位(从小到大)
     */
    public Integer getIdx() {
        return idx;
    }

    /**<br/>
     * 字段: sdk_game_plan.idx<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param idx: 顺序位(从小到大)
     */
    public void setIdx(Integer idx) {
        this.idx = idx;
    }
}