package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkGame {
    /**<br/>
     * 字段: sdk_game.game_id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 游戏ID
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer gameId;

    /**<br/>
     * 字段: sdk_game.name<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 游戏名称
     */
    private String name;

    /**<br/>
     * 字段: sdk_game.cp_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 所属游戏商ID
     */
    private Integer cpId;

    /**<br/>
     * 字段: sdk_game.app_key<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 用于和SDK服务器交互的KEY
     */
    private String appKey;

    /**<br/>
     * 字段: sdk_game.create_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createTime;

    /**<br/>
     * 字段: sdk_game.callback_recharge<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 充值结果通知地址
     */
    private String callbackRecharge;

    /**<br/>
     * 字段: sdk_game.game_url<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 游戏链接地址
     */
    private String gameUrl;

    /**<br/>
     * 字段: sdk_game.status<br/>
     * 可空: false<br/>
     * 缺省: 1<br/>
     * 长度: 10<br/>
     * 说明: 游戏状态(1-游戏, 0-无效)
     */
    private Integer status;

    /**<br/>
     * 字段: sdk_game.memo<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 124<br/>
     * 说明: 备注
     */
    private String memo;

    /**<br/>
     * 字段: sdk_game.weipaykey<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 微派Key，用于客户端打包，服务端只保存显示
     */
    private String weipaykey;

    /**<br/>
     * 字段: sdk_game.package_name<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 游戏包名
     */
    private String packageName;

    /**<br/>
     * 字段: sdk_game.force_tag<br/>
     * 可空: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 升级类型:
0-不升级
1-建议升级
2-强制升级
     */
    private Integer forceTag;

    /**<br/>
     * 字段: sdk_game.version<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 15<br/>
     * 说明: SDK版本
     */
    private String version;

    /**<br/>
     * 字段: sdk_game.if_push<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 是否推送广告列表。0:不推送 1:推送
     */
    private Integer ifPush;

    /**
     * @return sdk_game.game_id: 游戏ID
     */
    public Integer getGameId() {
        return gameId;
    }

    /**<br/>
     * 字段: sdk_game.game_id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param gameId: 游戏ID
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return sdk_game.name: 游戏名称
     */
    public String getName() {
        return name;
    }

    /**<br/>
     * 字段: sdk_game.name<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param name: 游戏名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return sdk_game.cp_id: 所属游戏商ID
     */
    public Integer getCpId() {
        return cpId;
    }

    /**<br/>
     * 字段: sdk_game.cp_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param cpId: 所属游戏商ID
     */
    public void setCpId(Integer cpId) {
        this.cpId = cpId;
    }

    /**
     * @return sdk_game.app_key: 用于和SDK服务器交互的KEY
     */
    public String getAppKey() {
        return appKey;
    }

    /**<br/>
     * 字段: sdk_game.app_key<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param appKey: 用于和SDK服务器交互的KEY
     */
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    /**
     * @return sdk_game.create_time: 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**<br/>
     * 字段: sdk_game.create_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createTime: 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return sdk_game.callback_recharge: 充值结果通知地址
     */
    public String getCallbackRecharge() {
        return callbackRecharge;
    }

    /**<br/>
     * 字段: sdk_game.callback_recharge<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param callbackRecharge: 充值结果通知地址
     */
    public void setCallbackRecharge(String callbackRecharge) {
        this.callbackRecharge = callbackRecharge;
    }

    /**
     * @return sdk_game.game_url: 游戏链接地址
     */
    public String getGameUrl() {
        return gameUrl;
    }

    /**<br/>
     * 字段: sdk_game.game_url<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param gameUrl: 游戏链接地址
     */
    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }

    /**
     * @return sdk_game.status: 游戏状态(1-游戏, 0-无效)
     */
    public Integer getStatus() {
        return status;
    }

    /**<br/>
     * 字段: sdk_game.status<br/>
     * 可空: false<br/>
     * 缺省: 1<br/>
     * 长度: 10<br/>
     * @param status: 游戏状态(1-游戏, 0-无效)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return sdk_game.memo: 备注
     */
    public String getMemo() {
        return memo;
    }

    /**<br/>
     * 字段: sdk_game.memo<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 124<br/>
     * @param memo: 备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return sdk_game.weipaykey: 微派Key，用于客户端打包，服务端只保存显示
     */
    public String getWeipaykey() {
        return weipaykey;
    }

    /**<br/>
     * 字段: sdk_game.weipaykey<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * @param weipaykey: 微派Key，用于客户端打包，服务端只保存显示
     */
    public void setWeipaykey(String weipaykey) {
        this.weipaykey = weipaykey;
    }

    /**
     * @return sdk_game.package_name: 游戏包名
     */
    public String getPackageName() {
        return packageName;
    }

    /**<br/>
     * 字段: sdk_game.package_name<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * @param packageName: 游戏包名
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * @return sdk_game.force_tag: 升级类型:
0-不升级
1-建议升级
2-强制升级
     */
    public Integer getForceTag() {
        return forceTag;
    }

    /**<br/>
     * 字段: sdk_game.force_tag<br/>
     * 可空: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * @param forceTag: 升级类型:
0-不升级
1-建议升级
2-强制升级
     */
    public void setForceTag(Integer forceTag) {
        this.forceTag = forceTag;
    }

    /**
     * @return sdk_game.version: SDK版本
     */
    public String getVersion() {
        return version;
    }

    /**<br/>
     * 字段: sdk_game.version<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 15<br/>
     * @param version: SDK版本
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return sdk_game.if_push: 是否推送广告列表。0:不推送 1:推送
     */
    public Integer getIfPush() {
        return ifPush;
    }

    /**<br/>
     * 字段: sdk_game.if_push<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param ifPush: 是否推送广告列表。0:不推送 1:推送
     */
    public void setIfPush(Integer ifPush) {
        this.ifPush = ifPush;
    }
}