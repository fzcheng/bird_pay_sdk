package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkPhoneSegment {
    /**<br/>
     * 字段: sdk_phone_segment.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_phone_segment.province_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 省份地址编码
     */
    private Integer provinceNo;

    /**<br/>
     * 字段: sdk_phone_segment.area_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 城市区号
     */
    private Integer areaCode;

    /**<br/>
     * 字段: sdk_phone_segment.phone_segment_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 手机号段
     */
    private Integer phoneSegmentCode;

    /**<br/>
     * 字段: sdk_phone_segment.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createTime;

    /**<br/>
     * 字段: sdk_phone_segment.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 更改时间
     */
    private Date updatedTime;

    /**<br/>
     * 字段: sdk_phone_segment.operation_type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 运营商类型（1、移动，2、联通，3、电信）
     */
    private Integer operationType;

    /**
     * @return sdk_phone_segment.id: 
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_phone_segment.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param id: 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return sdk_phone_segment.province_no: 省份地址编码
     */
    public Integer getProvinceNo() {
        return provinceNo;
    }

    /**<br/>
     * 字段: sdk_phone_segment.province_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param provinceNo: 省份地址编码
     */
    public void setProvinceNo(Integer provinceNo) {
        this.provinceNo = provinceNo;
    }

    /**
     * @return sdk_phone_segment.area_code: 城市区号
     */
    public Integer getAreaCode() {
        return areaCode;
    }

    /**<br/>
     * 字段: sdk_phone_segment.area_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param areaCode: 城市区号
     */
    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * @return sdk_phone_segment.phone_segment_code: 手机号段
     */
    public Integer getPhoneSegmentCode() {
        return phoneSegmentCode;
    }

    /**<br/>
     * 字段: sdk_phone_segment.phone_segment_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param phoneSegmentCode: 手机号段
     */
    public void setPhoneSegmentCode(Integer phoneSegmentCode) {
        this.phoneSegmentCode = phoneSegmentCode;
    }

    /**
     * @return sdk_phone_segment.create_time: 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**<br/>
     * 字段: sdk_phone_segment.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createTime: 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return sdk_phone_segment.updated_time: 更改时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**<br/>
     * 字段: sdk_phone_segment.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param updatedTime: 更改时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * @return sdk_phone_segment.operation_type: 运营商类型（1、移动，2、联通，3、电信）
     */
    public Integer getOperationType() {
        return operationType;
    }

    /**<br/>
     * 字段: sdk_phone_segment.operation_type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param operationType: 运营商类型（1、移动，2、联通，3、电信）
     */
    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }
}