package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkGift {
    /**<br/>
     * 字段: sdk_gift.gift_id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 礼包ID(自增主键)
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer giftId;

    /**<br/>
     * 字段: sdk_gift.game_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 游戏ID
     */
    private Integer gameId;

    /**<br/>
     * 字段: sdk_gift.title<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 礼包名称
     */
    private String title;

    /**<br/>
     * 字段: sdk_gift.image<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 图片存储路径
     */
    private String image;

    /**<br/>
     * 字段: sdk_gift.detail<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 2048<br/>
     * 说明: 详细介绍
     */
    private String detail;

    /**<br/>
     * 字段: sdk_gift.remain<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 剩余数
     */
    private Integer remain;

    /**<br/>
     * 字段: sdk_gift.total<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 总数
     */
    private Integer total;

    /**<br/>
     * 字段: sdk_gift.begin_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 开始时间(空值表示立即开始)
     */
    private Date beginTime;

    /**<br/>
     * 字段: sdk_gift.end_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 截止时间
     */
    private Date endTime;

    /**<br/>
     * 字段: sdk_gift.create_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 创建时间
     */
    private Date createTime;

    /**<br/>
     * 字段: sdk_gift.vcodes<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 65535<br/>
     * 说明: 验证码列表(逗号或分号隔开)
     */
    private String vcodes;

    /**
     * @return sdk_gift.gift_id: 礼包ID(自增主键)
     */
    public Integer getGiftId() {
        return giftId;
    }

    /**<br/>
     * 字段: sdk_gift.gift_id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param giftId: 礼包ID(自增主键)
     */
    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }

    /**
     * @return sdk_gift.game_id: 游戏ID
     */
    public Integer getGameId() {
        return gameId;
    }

    /**<br/>
     * 字段: sdk_gift.game_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param gameId: 游戏ID
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return sdk_gift.title: 礼包名称
     */
    public String getTitle() {
        return title;
    }

    /**<br/>
     * 字段: sdk_gift.title<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param title: 礼包名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return sdk_gift.image: 图片存储路径
     */
    public String getImage() {
        return image;
    }

    /**<br/>
     * 字段: sdk_gift.image<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param image: 图片存储路径
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return sdk_gift.detail: 详细介绍
     */
    public String getDetail() {
        return detail;
    }

    /**<br/>
     * 字段: sdk_gift.detail<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 2048<br/>
     * @param detail: 详细介绍
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return sdk_gift.remain: 剩余数
     */
    public Integer getRemain() {
        return remain;
    }

    /**<br/>
     * 字段: sdk_gift.remain<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param remain: 剩余数
     */
    public void setRemain(Integer remain) {
        this.remain = remain;
    }

    /**
     * @return sdk_gift.total: 总数
     */
    public Integer getTotal() {
        return total;
    }

    /**<br/>
     * 字段: sdk_gift.total<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param total: 总数
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return sdk_gift.begin_time: 开始时间(空值表示立即开始)
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**<br/>
     * 字段: sdk_gift.begin_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param beginTime: 开始时间(空值表示立即开始)
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * @return sdk_gift.end_time: 截止时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**<br/>
     * 字段: sdk_gift.end_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param endTime: 截止时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return sdk_gift.create_time: 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**<br/>
     * 字段: sdk_gift.create_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param createTime: 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return sdk_gift.vcodes: 验证码列表(逗号或分号隔开)
     */
    public String getVcodes() {
        return vcodes;
    }

    /**<br/>
     * 字段: sdk_gift.vcodes<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 65535<br/>
     * @param vcodes: 验证码列表(逗号或分号隔开)
     */
    public void setVcodes(String vcodes) {
        this.vcodes = vcodes;
    }
}