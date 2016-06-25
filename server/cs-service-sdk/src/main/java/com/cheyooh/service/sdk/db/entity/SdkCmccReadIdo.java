package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkCmccReadIdo {
    /**<br/>
     * 字段: sdk_cmcc_read_ido.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 编号
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_cmcc_read_ido.order_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 我们的订单编号
     */
    private String orderNo;

    /**<br/>
     * 字段: sdk_cmcc_read_ido.out_trade_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: ido订单编号
     */
    private String outTradeNo;

    /**<br/>
     * 字段: sdk_cmcc_read_ido.content<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 短信内容
     */
    private String content;

    /**<br/>
     * 字段: sdk_cmcc_read_ido.result_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 结果码, 0:失败,1:成功,2:clientHash验证失败,3:计费代码错误6:未知异常,
当结果码为非”1”数值时,其他两个参数应该为空,及时不为空也不是一个正常的订单,不能使用其进行订购操作
     */
    private String resultCode;

    /**<br/>
     * 字段: sdk_cmcc_read_ido.fee_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 计费代码
     */
    private String feeCode;

    /**<br/>
     * 字段: sdk_cmcc_read_ido.price<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * 说明: 计费金额
     */
    private Float price;

    /**<br/>
     * 字段: sdk_cmcc_read_ido.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createTime;

    /**<br/>
     * 字段: sdk_cmcc_read_ido.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 更改时间
     */
    private Date updatedTime;

    /**
     * @return sdk_cmcc_read_ido.id: 编号
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_cmcc_read_ido.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param id: 编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return sdk_cmcc_read_ido.order_no: 我们的订单编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**<br/>
     * 字段: sdk_cmcc_read_ido.order_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param orderNo: 我们的订单编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return sdk_cmcc_read_ido.out_trade_no: ido订单编号
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**<br/>
     * 字段: sdk_cmcc_read_ido.out_trade_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * @param outTradeNo: ido订单编号
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    /**
     * @return sdk_cmcc_read_ido.content: 短信内容
     */
    public String getContent() {
        return content;
    }

    /**<br/>
     * 字段: sdk_cmcc_read_ido.content<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * @param content: 短信内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return sdk_cmcc_read_ido.result_code: 结果码, 0:失败,1:成功,2:clientHash验证失败,3:计费代码错误6:未知异常,
当结果码为非”1”数值时,其他两个参数应该为空,及时不为空也不是一个正常的订单,不能使用其进行订购操作
     */
    public String getResultCode() {
        return resultCode;
    }

    /**<br/>
     * 字段: sdk_cmcc_read_ido.result_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param resultCode: 结果码, 0:失败,1:成功,2:clientHash验证失败,3:计费代码错误6:未知异常,
当结果码为非”1”数值时,其他两个参数应该为空,及时不为空也不是一个正常的订单,不能使用其进行订购操作
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * @return sdk_cmcc_read_ido.fee_code: 计费代码
     */
    public String getFeeCode() {
        return feeCode;
    }

    /**<br/>
     * 字段: sdk_cmcc_read_ido.fee_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param feeCode: 计费代码
     */
    public void setFeeCode(String feeCode) {
        this.feeCode = feeCode;
    }

    /**
     * @return sdk_cmcc_read_ido.price: 计费金额
     */
    public Float getPrice() {
        return price;
    }

    /**<br/>
     * 字段: sdk_cmcc_read_ido.price<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * @param price: 计费金额
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * @return sdk_cmcc_read_ido.create_time: 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**<br/>
     * 字段: sdk_cmcc_read_ido.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createTime: 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return sdk_cmcc_read_ido.updated_time: 更改时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**<br/>
     * 字段: sdk_cmcc_read_ido.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param updatedTime: 更改时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}