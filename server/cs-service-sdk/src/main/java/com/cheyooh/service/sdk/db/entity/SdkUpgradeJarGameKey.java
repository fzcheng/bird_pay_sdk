package com.cheyooh.service.sdk.db.entity;

public class SdkUpgradeJarGameKey {
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
}