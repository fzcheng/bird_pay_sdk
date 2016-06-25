package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkPhone {
    /**<br/>
     * 字段: sdk_phone.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_phone.province_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 省份编号
     */
    private Integer provinceId;

    /**<br/>
     * 字段: sdk_phone.province_name<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 省份名称
     */
    private String provinceName;

    /**<br/>
     * 字段: sdk_phone.city_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 城市编号
     */
    private Integer cityId;

    /**<br/>
     * 字段: sdk_phone.city_name<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 城市名称
     */
    private String cityName;

    /**<br/>
     * 字段: sdk_phone.area_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 区号
     */
    private Integer areaCode;

    /**<br/>
     * 字段: sdk_phone.H_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 手机号段
     */
    private Integer hCode;

    /**<br/>
     * 字段: sdk_phone.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createTime;

    /**<br/>
     * 字段: sdk_phone.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 更改时间
     */
    private Date updatedTime;

    /**
     * @return sdk_phone.id: 
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_phone.id<br/>
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
     * @return sdk_phone.province_id: 省份编号
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**<br/>
     * 字段: sdk_phone.province_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param provinceId: 省份编号
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * @return sdk_phone.province_name: 省份名称
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**<br/>
     * 字段: sdk_phone.province_name<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param provinceName: 省份名称
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * @return sdk_phone.city_id: 城市编号
     */
    public Integer getCityId() {
        return cityId;
    }

    /**<br/>
     * 字段: sdk_phone.city_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param cityId: 城市编号
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * @return sdk_phone.city_name: 城市名称
     */
    public String getCityName() {
        return cityName;
    }

    /**<br/>
     * 字段: sdk_phone.city_name<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param cityName: 城市名称
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * @return sdk_phone.area_code: 区号
     */
    public Integer getAreaCode() {
        return areaCode;
    }

    /**<br/>
     * 字段: sdk_phone.area_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param areaCode: 区号
     */
    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * @return sdk_phone.H_code: 手机号段
     */
    public Integer gethCode() {
        return hCode;
    }

    /**<br/>
     * 字段: sdk_phone.H_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param hCode: 手机号段
     */
    public void sethCode(Integer hCode) {
        this.hCode = hCode;
    }

    /**
     * @return sdk_phone.create_time: 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**<br/>
     * 字段: sdk_phone.create_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createTime: 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return sdk_phone.updated_time: 更改时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**<br/>
     * 字段: sdk_phone.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param updatedTime: 更改时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}