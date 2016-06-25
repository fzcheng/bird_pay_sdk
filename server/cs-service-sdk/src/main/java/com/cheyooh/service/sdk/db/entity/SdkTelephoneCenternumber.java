package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkTelephoneCenternumber {
    /**<br/>
     * 字段: sdk_telephone_centernumber.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: id
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_telephone_centernumber.imsi<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 手机国际移动用户识别码
     */
    private String imsi;

    /**<br/>
     * 字段: sdk_telephone_centernumber.centernumber<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 30<br/>
     * 说明: 短信中心号段
     */
    private String centernumber;

    /**<br/>
     * 字段: sdk_telephone_centernumber.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     */
    private Date createdTime;

    /**<br/>
     * 字段: sdk_telephone_centernumber.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 更改时间
     */
    private Date updatedTime;

    /**<br/>
     * 字段: sdk_telephone_centernumber.province_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 省份地址编码
     */
    private Integer provinceNo;

    /**<br/>
     * 字段: sdk_telephone_centernumber.area_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 城市区号
     */
    private Integer areaCode;

    /**
     * @return sdk_telephone_centernumber.id: id
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_telephone_centernumber.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param id: id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return sdk_telephone_centernumber.imsi: 手机国际移动用户识别码
     */
    public String getImsi() {
        return imsi;
    }

    /**<br/>
     * 字段: sdk_telephone_centernumber.imsi<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * @param imsi: 手机国际移动用户识别码
     */
    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    /**
     * @return sdk_telephone_centernumber.centernumber: 短信中心号段
     */
    public String getCenternumber() {
        return centernumber;
    }

    /**<br/>
     * 字段: sdk_telephone_centernumber.centernumber<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 30<br/>
     * @param centernumber: 短信中心号段
     */
    public void setCenternumber(String centernumber) {
        this.centernumber = centernumber;
    }

    /**
     * @return sdk_telephone_centernumber.created_time: 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**<br/>
     * 字段: sdk_telephone_centernumber.created_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param createdTime: 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return sdk_telephone_centernumber.updated_time: 更改时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**<br/>
     * 字段: sdk_telephone_centernumber.updated_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * @param updatedTime: 更改时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * @return sdk_telephone_centernumber.province_no: 省份地址编码
     */
    public Integer getProvinceNo() {
        return provinceNo;
    }

    /**<br/>
     * 字段: sdk_telephone_centernumber.province_no<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param provinceNo: 省份地址编码
     */
    public void setProvinceNo(Integer provinceNo) {
        this.provinceNo = provinceNo;
    }

    /**
     * @return sdk_telephone_centernumber.area_code: 城市区号
     */
    public Integer getAreaCode() {
        return areaCode;
    }

    /**<br/>
     * 字段: sdk_telephone_centernumber.area_code<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param areaCode: 城市区号
     */
    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }
}