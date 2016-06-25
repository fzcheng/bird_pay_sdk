package com.cheyooh.service.sdk.db.entity;

public class SdkGamePayment {
    /**<br/>
     * 字段: sdk_game_payment.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_game_payment.payment_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 支付方式
     */
    private Integer paymentId;

    /**<br/>
     * 字段: sdk_game_payment.game_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 游戏
     */
    private Integer gameId;

    /**<br/>
     * 字段: sdk_game_payment.pay_show<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 排序(从小到大)
     */
    private Integer payShow;

    /**<br/>
     * 字段: sdk_game_payment.idx<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 
     */
    private Integer idx;

    /**
     * @return sdk_game_payment.id: 
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_game_payment.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param id: 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return sdk_game_payment.payment_id: 支付方式
     */
    public Integer getPaymentId() {
        return paymentId;
    }

    /**<br/>
     * 字段: sdk_game_payment.payment_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param paymentId: 支付方式
     */
    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * @return sdk_game_payment.game_id: 游戏
     */
    public Integer getGameId() {
        return gameId;
    }

    /**<br/>
     * 字段: sdk_game_payment.game_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param gameId: 游戏
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return sdk_game_payment.pay_show: 排序(从小到大)
     */
    public Integer getPayShow() {
        return payShow;
    }

    /**<br/>
     * 字段: sdk_game_payment.pay_show<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param payShow: 排序(从小到大)
     */
    public void setPayShow(Integer payShow) {
        this.payShow = payShow;
    }

    /**
     * @return sdk_game_payment.idx: 
     */
    public Integer getIdx() {
        return idx;
    }

    /**<br/>
     * 字段: sdk_game_payment.idx<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param idx: 
     */
    public void setIdx(Integer idx) {
        this.idx = idx;
    }
}