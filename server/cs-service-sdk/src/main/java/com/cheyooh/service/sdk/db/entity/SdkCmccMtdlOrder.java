package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkCmccMtdlOrder {
    /**<br/>
     * 字段: sdk_cmcc_mtdl_order.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 主键
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_cmcc_mtdl_order.order_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 订单号
     */
    private String orderNo;

    /**<br/>
     * 字段: sdk_cmcc_mtdl_order.amount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * 说明: 金额(元)
     */
    private Float amount;

    /**<br/>
     * 字段: sdk_cmcc_mtdl_order.game_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 游戏id
     */
    private Integer gameId;

    /**<br/>
     * 字段: sdk_cmcc_mtdl_order.status<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 支付状态，0：支付申请，1：支付成功，2：支付失败
     */
    private Integer status;

    /**<br/>
     * 字段: sdk_cmcc_mtdl_order.status_detail<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 状态描述
     */
    private String statusDetail;

    /**<br/>
     * 字段: sdk_cmcc_mtdl_order.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 更新时间
     */
    private Date updatedTime;

    /**<br/>
     * 字段: sdk_cmcc_mtdl_order.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createdTime;

    /**
     * @return sdk_cmcc_mtdl_order.id: 主键
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_cmcc_mtdl_order.id<br/>
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
     * @return sdk_cmcc_mtdl_order.order_no: 订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**<br/>
     * 字段: sdk_cmcc_mtdl_order.order_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param orderNo: 订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return sdk_cmcc_mtdl_order.amount: 金额(元)
     */
    public Float getAmount() {
        return amount;
    }

    /**<br/>
     * 字段: sdk_cmcc_mtdl_order.amount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * @param amount: 金额(元)
     */
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    /**
     * @return sdk_cmcc_mtdl_order.game_id: 游戏id
     */
    public Integer getGameId() {
        return gameId;
    }

    /**<br/>
     * 字段: sdk_cmcc_mtdl_order.game_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param gameId: 游戏id
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return sdk_cmcc_mtdl_order.status: 支付状态，0：支付申请，1：支付成功，2：支付失败
     */
    public Integer getStatus() {
        return status;
    }

    /**<br/>
     * 字段: sdk_cmcc_mtdl_order.status<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param status: 支付状态，0：支付申请，1：支付成功，2：支付失败
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return sdk_cmcc_mtdl_order.status_detail: 状态描述
     */
    public String getStatusDetail() {
        return statusDetail;
    }

    /**<br/>
     * 字段: sdk_cmcc_mtdl_order.status_detail<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param statusDetail: 状态描述
     */
    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }

    /**
     * @return sdk_cmcc_mtdl_order.updated_time: 更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**<br/>
     * 字段: sdk_cmcc_mtdl_order.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param updatedTime: 更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * @return sdk_cmcc_mtdl_order.created_time: 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**<br/>
     * 字段: sdk_cmcc_mtdl_order.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createdTime: 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}