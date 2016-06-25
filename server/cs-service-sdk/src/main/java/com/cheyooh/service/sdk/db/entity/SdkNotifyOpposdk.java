package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkNotifyOpposdk {
    /**<br/>
     * 字段: sdk_notify_opposdk.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 自增字段
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_notify_opposdk.notifyId<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: oppo订单号
     */
    private String notifyid;

    /**<br/>
     * 字段: sdk_notify_opposdk.partnerOrder<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 开发者订单号（客户端上传）
     */
    private String partnerorder;

    /**<br/>
     * 字段: sdk_notify_opposdk.productName<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 商品名称（客户端上传）
     */
    private String productname;

    /**<br/>
     * 字段: sdk_notify_opposdk.productDesc<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 商品描述（客户端上传）
     */
    private String productdesc;

    /**<br/>
     * 字段: sdk_notify_opposdk.price<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 商品价格(以分为单位)
     */
    private Integer price;

    /**<br/>
     * 字段: sdk_notify_opposdk.count<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 商品数量（一般为 1）
     */
    private Integer count;

    /**<br/>
     * 字段: sdk_notify_opposdk.attach<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 请求支付时上传的附加参数（客户端上传）
     */
    private String attach;

    /**<br/>
     * 字段: sdk_notify_opposdk.signature<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 512<br/>
     * 说明: 签名参数
     */
    private String signature;

    /**<br/>
     * 字段: sdk_notify_opposdk.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createTime;

    /**
     * @return sdk_notify_opposdk.id: 自增字段
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_notify_opposdk.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param id: 自增字段
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return sdk_notify_opposdk.notifyId: oppo订单号
     */
    public String getNotifyid() {
        return notifyid;
    }

    /**<br/>
     * 字段: sdk_notify_opposdk.notifyId<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param notifyid: oppo订单号
     */
    public void setNotifyid(String notifyid) {
        this.notifyid = notifyid;
    }

    /**
     * @return sdk_notify_opposdk.partnerOrder: 开发者订单号（客户端上传）
     */
    public String getPartnerorder() {
        return partnerorder;
    }

    /**<br/>
     * 字段: sdk_notify_opposdk.partnerOrder<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * @param partnerorder: 开发者订单号（客户端上传）
     */
    public void setPartnerorder(String partnerorder) {
        this.partnerorder = partnerorder;
    }

    /**
     * @return sdk_notify_opposdk.productName: 商品名称（客户端上传）
     */
    public String getProductname() {
        return productname;
    }

    /**<br/>
     * 字段: sdk_notify_opposdk.productName<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param productname: 商品名称（客户端上传）
     */
    public void setProductname(String productname) {
        this.productname = productname;
    }

    /**
     * @return sdk_notify_opposdk.productDesc: 商品描述（客户端上传）
     */
    public String getProductdesc() {
        return productdesc;
    }

    /**<br/>
     * 字段: sdk_notify_opposdk.productDesc<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * @param productdesc: 商品描述（客户端上传）
     */
    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }

    /**
     * @return sdk_notify_opposdk.price: 商品价格(以分为单位)
     */
    public Integer getPrice() {
        return price;
    }

    /**<br/>
     * 字段: sdk_notify_opposdk.price<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param price: 商品价格(以分为单位)
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * @return sdk_notify_opposdk.count: 商品数量（一般为 1）
     */
    public Integer getCount() {
        return count;
    }

    /**<br/>
     * 字段: sdk_notify_opposdk.count<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param count: 商品数量（一般为 1）
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return sdk_notify_opposdk.attach: 请求支付时上传的附加参数（客户端上传）
     */
    public String getAttach() {
        return attach;
    }

    /**<br/>
     * 字段: sdk_notify_opposdk.attach<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param attach: 请求支付时上传的附加参数（客户端上传）
     */
    public void setAttach(String attach) {
        this.attach = attach;
    }

    /**
     * @return sdk_notify_opposdk.signature: 签名参数
     */
    public String getSignature() {
        return signature;
    }

    /**<br/>
     * 字段: sdk_notify_opposdk.signature<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 512<br/>
     * @param signature: 签名参数
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * @return sdk_notify_opposdk.create_time: 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**<br/>
     * 字段: sdk_notify_opposdk.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createTime: 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}