package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkDanglesdkPoint {
    /**<br/>
     * 字段: sdk_danglesdk_point.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 自增字段
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_danglesdk_point.gameid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 乐游游戏id
     */
    private Integer gameid;

    /**<br/>
     * 字段: sdk_danglesdk_point.point_name<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 计费点名称
     */
    private String pointName;

    /**<br/>
     * 字段: sdk_danglesdk_point.point<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 计费点编号
     */
    private String point;

    /**<br/>
     * 字段: sdk_danglesdk_point.price<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * 说明: 商品价格(以元为单位)
     */
    private Float price;

    /**<br/>
     * 字段: sdk_danglesdk_point.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createTime;

    /**
     * @return sdk_danglesdk_point.id: 自增字段
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_danglesdk_point.id<br/>
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
     * @return sdk_danglesdk_point.gameid: 乐游游戏id
     */
    public Integer getGameid() {
        return gameid;
    }

    /**<br/>
     * 字段: sdk_danglesdk_point.gameid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param gameid: 乐游游戏id
     */
    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }

    /**
     * @return sdk_danglesdk_point.point_name: 计费点名称
     */
    public String getPointName() {
        return pointName;
    }

    /**<br/>
     * 字段: sdk_danglesdk_point.point_name<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * @param pointName: 计费点名称
     */
    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    /**
     * @return sdk_danglesdk_point.point: 计费点编号
     */
    public String getPoint() {
        return point;
    }

    /**<br/>
     * 字段: sdk_danglesdk_point.point<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param point: 计费点编号
     */
    public void setPoint(String point) {
        this.point = point;
    }

    /**
     * @return sdk_danglesdk_point.price: 商品价格(以元为单位)
     */
    public Float getPrice() {
        return price;
    }

    /**<br/>
     * 字段: sdk_danglesdk_point.price<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * @param price: 商品价格(以元为单位)
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * @return sdk_danglesdk_point.create_time: 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**<br/>
     * 字段: sdk_danglesdk_point.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createTime: 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}