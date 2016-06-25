package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkOrderShenzhoufu {
    /**<br/>
     * 字段: sdk_order_shenzhoufu.pay_id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 自增字段
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer payId;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createdTime;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 更改时间
     */
    private Date updatedTime;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 4<br/>
     * 说明: 支付类型 1-支付宝 2-微信 3-充值卡
     */
    private String type;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.order_no<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 订单编号
     */
    private String orderNo;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.merId<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 商户在神州付的唯一身份标识（6 位数字）
     */
    private String merid;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.version<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 版本号
     */
    private String version;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.payMoney<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * 说明: 订单金额(单位元)
     */
    private Float paymoney;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.pageReturnUrl<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 页面返回地址
     */
    private String pagereturnurl;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.serverReturnUrl<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 服务器返回地址
     */
    private String serverreturnurl;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.payResult<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 2<br/>
     * 说明: 订单结果 1：成功 0：失败
     */
    private String payresult;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.payDetails<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 订单详情
     */
    private String paydetails;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.errcode<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 失败原因代码
     */
    private String errcode;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.merUserName<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 用户在商户系统的唯一标识
     */
    private String merusername;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.merUserMail<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 用户在商户系统内注册的邮箱
     */
    private String merusermail;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.productUrl<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 订单详情地址
     */
    private String producturl;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.itemName<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 产品名称
     */
    private String itemname;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.itemDesc<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 产品描述
     */
    private String itemdesc;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.privateField<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 商户私有数据
     */
    private String privatefield;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.cardInfo<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 充值卡加密信息(神州付充值卡信息)
     */
    private String cardinfo;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.cardTypeCombine<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 4<br/>
     * 说明: 充值卡类型,数字0：移动；1：联通；2：电信(神州付充值卡信息)
     */
    private String cardtypecombine;

    /**<br/>
     * 字段: sdk_order_shenzhoufu.signString<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 证书签名串(神州付充值卡信息)
     */
    private String signstring;

    /**
     * @return sdk_order_shenzhoufu.pay_id: 自增字段
     */
    public Integer getPayId() {
        return payId;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.pay_id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param payId: 自增字段
     */
    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    /**
     * @return sdk_order_shenzhoufu.created_time: 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createdTime: 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return sdk_order_shenzhoufu.updated_time: 更改时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param updatedTime: 更改时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * @return sdk_order_shenzhoufu.type: 支付类型 1-支付宝 2-微信 3-充值卡
     */
    public String getType() {
        return type;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 4<br/>
     * @param type: 支付类型 1-支付宝 2-微信 3-充值卡
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return sdk_order_shenzhoufu.order_no: 订单编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.order_no<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param orderNo: 订单编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return sdk_order_shenzhoufu.merId: 商户在神州付的唯一身份标识（6 位数字）
     */
    public String getMerid() {
        return merid;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.merId<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * @param merid: 商户在神州付的唯一身份标识（6 位数字）
     */
    public void setMerid(String merid) {
        this.merid = merid;
    }

    /**
     * @return sdk_order_shenzhoufu.version: 版本号
     */
    public String getVersion() {
        return version;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.version<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * @param version: 版本号
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return sdk_order_shenzhoufu.payMoney: 订单金额(单位元)
     */
    public Float getPaymoney() {
        return paymoney;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.payMoney<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * @param paymoney: 订单金额(单位元)
     */
    public void setPaymoney(Float paymoney) {
        this.paymoney = paymoney;
    }

    /**
     * @return sdk_order_shenzhoufu.pageReturnUrl: 页面返回地址
     */
    public String getPagereturnurl() {
        return pagereturnurl;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.pageReturnUrl<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * @param pagereturnurl: 页面返回地址
     */
    public void setPagereturnurl(String pagereturnurl) {
        this.pagereturnurl = pagereturnurl;
    }

    /**
     * @return sdk_order_shenzhoufu.serverReturnUrl: 服务器返回地址
     */
    public String getServerreturnurl() {
        return serverreturnurl;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.serverReturnUrl<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * @param serverreturnurl: 服务器返回地址
     */
    public void setServerreturnurl(String serverreturnurl) {
        this.serverreturnurl = serverreturnurl;
    }

    /**
     * @return sdk_order_shenzhoufu.payResult: 订单结果 1：成功 0：失败
     */
    public String getPayresult() {
        return payresult;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.payResult<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 2<br/>
     * @param payresult: 订单结果 1：成功 0：失败
     */
    public void setPayresult(String payresult) {
        this.payresult = payresult;
    }

    /**
     * @return sdk_order_shenzhoufu.payDetails: 订单详情
     */
    public String getPaydetails() {
        return paydetails;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.payDetails<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * @param paydetails: 订单详情
     */
    public void setPaydetails(String paydetails) {
        this.paydetails = paydetails;
    }

    /**
     * @return sdk_order_shenzhoufu.errcode: 失败原因代码
     */
    public String getErrcode() {
        return errcode;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.errcode<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param errcode: 失败原因代码
     */
    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    /**
     * @return sdk_order_shenzhoufu.merUserName: 用户在商户系统的唯一标识
     */
    public String getMerusername() {
        return merusername;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.merUserName<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param merusername: 用户在商户系统的唯一标识
     */
    public void setMerusername(String merusername) {
        this.merusername = merusername;
    }

    /**
     * @return sdk_order_shenzhoufu.merUserMail: 用户在商户系统内注册的邮箱
     */
    public String getMerusermail() {
        return merusermail;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.merUserMail<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param merusermail: 用户在商户系统内注册的邮箱
     */
    public void setMerusermail(String merusermail) {
        this.merusermail = merusermail;
    }

    /**
     * @return sdk_order_shenzhoufu.productUrl: 订单详情地址
     */
    public String getProducturl() {
        return producturl;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.productUrl<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * @param producturl: 订单详情地址
     */
    public void setProducturl(String producturl) {
        this.producturl = producturl;
    }

    /**
     * @return sdk_order_shenzhoufu.itemName: 产品名称
     */
    public String getItemname() {
        return itemname;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.itemName<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * @param itemname: 产品名称
     */
    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    /**
     * @return sdk_order_shenzhoufu.itemDesc: 产品描述
     */
    public String getItemdesc() {
        return itemdesc;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.itemDesc<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * @param itemdesc: 产品描述
     */
    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    /**
     * @return sdk_order_shenzhoufu.privateField: 商户私有数据
     */
    public String getPrivatefield() {
        return privatefield;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.privateField<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param privatefield: 商户私有数据
     */
    public void setPrivatefield(String privatefield) {
        this.privatefield = privatefield;
    }

    /**
     * @return sdk_order_shenzhoufu.cardInfo: 充值卡加密信息(神州付充值卡信息)
     */
    public String getCardinfo() {
        return cardinfo;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.cardInfo<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param cardinfo: 充值卡加密信息(神州付充值卡信息)
     */
    public void setCardinfo(String cardinfo) {
        this.cardinfo = cardinfo;
    }

    /**
     * @return sdk_order_shenzhoufu.cardTypeCombine: 充值卡类型,数字0：移动；1：联通；2：电信(神州付充值卡信息)
     */
    public String getCardtypecombine() {
        return cardtypecombine;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.cardTypeCombine<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 4<br/>
     * @param cardtypecombine: 充值卡类型,数字0：移动；1：联通；2：电信(神州付充值卡信息)
     */
    public void setCardtypecombine(String cardtypecombine) {
        this.cardtypecombine = cardtypecombine;
    }

    /**
     * @return sdk_order_shenzhoufu.signString: 证书签名串(神州付充值卡信息)
     */
    public String getSignstring() {
        return signstring;
    }

    /**<br/>
     * 字段: sdk_order_shenzhoufu.signString<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * @param signstring: 证书签名串(神州付充值卡信息)
     */
    public void setSignstring(String signstring) {
        this.signstring = signstring;
    }
}