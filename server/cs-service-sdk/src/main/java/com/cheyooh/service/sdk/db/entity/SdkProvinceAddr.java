package com.cheyooh.service.sdk.db.entity;

public class SdkProvinceAddr {
    /**<br/>
     * 字段: sdk_province_addr.id<br/>
     * 可空: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 省份地址编码
     */
    private Integer id;

    /**<br/>
     * 字段: sdk_province_addr.provincenm<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 240<br/>
     * 说明: 省份地址名称
     */
    private String provincenm;

    /**
     * @return sdk_province_addr.id: 省份地址编码
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_province_addr.id<br/>
     * 可空: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * @param id: 省份地址编码
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return sdk_province_addr.provincenm: 省份地址名称
     */
    public String getProvincenm() {
        return provincenm;
    }

    /**<br/>
     * 字段: sdk_province_addr.provincenm<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 240<br/>
     * @param provincenm: 省份地址名称
     */
    public void setProvincenm(String provincenm) {
        this.provincenm = provincenm;
    }
}