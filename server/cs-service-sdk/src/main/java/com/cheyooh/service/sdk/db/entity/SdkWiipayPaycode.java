package com.cheyooh.service.sdk.db.entity;

import java.math.BigDecimal;

public class SdkWiipayPaycode {
    /**<br/>
     * 字段: sdk_wiipay_paycode.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: PayCode主键
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_wiipay_paycode.name<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 计费项名
     */
    private String name;

    /**<br/>
     * 字段: sdk_wiipay_paycode.game_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 游戏ID
     */
    private Integer gameId;

    /**<br/>
     * 字段: sdk_wiipay_paycode.price<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 价格(元)(与微派对应)
     */
    private BigDecimal price;

    /**<br/>
     * 字段: sdk_wiipay_paycode.pay_code<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 计费项编号(微派)
     */
    private String payCode;

    /**<br/>
     * 字段: sdk_wiipay_paycode.pakage_name<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 包名
     */
    private String pakageName;

    /**
     * @return sdk_wiipay_paycode.id: PayCode主键
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_wiipay_paycode.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param id: PayCode主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return sdk_wiipay_paycode.name: 计费项名
     */
    public String getName() {
        return name;
    }

    /**<br/>
     * 字段: sdk_wiipay_paycode.name<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * @param name: 计费项名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return sdk_wiipay_paycode.game_id: 游戏ID
     */
    public Integer getGameId() {
        return gameId;
    }

    /**<br/>
     * 字段: sdk_wiipay_paycode.game_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param gameId: 游戏ID
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return sdk_wiipay_paycode.price: 价格(元)(与微派对应)
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**<br/>
     * 字段: sdk_wiipay_paycode.price<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param price: 价格(元)(与微派对应)
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return sdk_wiipay_paycode.pay_code: 计费项编号(微派)
     */
    public String getPayCode() {
        return payCode;
    }

    /**<br/>
     * 字段: sdk_wiipay_paycode.pay_code<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * @param payCode: 计费项编号(微派)
     */
    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    /**
     * @return sdk_wiipay_paycode.pakage_name: 包名
     */
    public String getPakageName() {
        return pakageName;
    }

    /**<br/>
     * 字段: sdk_wiipay_paycode.pakage_name<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * @param pakageName: 包名
     */
    public void setPakageName(String pakageName) {
        this.pakageName = pakageName;
    }
}