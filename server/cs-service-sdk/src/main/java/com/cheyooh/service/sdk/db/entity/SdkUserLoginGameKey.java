package com.cheyooh.service.sdk.db.entity;

public class SdkUserLoginGameKey {
    /**<br/>
     * 字段: sdk_user_login_game.uid<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 用户ID
     */
    private Integer uid;

    /**<br/>
     * 字段: sdk_user_login_game.game_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 游戏ID
     */
    private Integer gameId;

    /**
     * @return sdk_user_login_game.uid: 用户ID
     */
    public Integer getUid() {
        return uid;
    }

    /**<br/>
     * 字段: sdk_user_login_game.uid<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param uid: 用户ID
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * @return sdk_user_login_game.game_id: 游戏ID
     */
    public Integer getGameId() {
        return gameId;
    }

    /**<br/>
     * 字段: sdk_user_login_game.game_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param gameId: 游戏ID
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }
}