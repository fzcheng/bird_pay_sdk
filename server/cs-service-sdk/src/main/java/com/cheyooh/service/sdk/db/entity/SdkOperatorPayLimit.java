package com.cheyooh.service.sdk.db.entity;

public class SdkOperatorPayLimit {
    /**<br/>
     * 字段: sdk_operator_pay_limit.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 主键
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_operator_pay_limit.operator_type<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 3<br/>
     * 说明: 运营商，1：中国移动，2：中国联通，3：中国电信
     */
    private Integer operatorType;

    /**<br/>
     * 字段: sdk_operator_pay_limit.day_limit<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 每日限额
     */
    private Float dayLimit;

    /**<br/>
     * 字段: sdk_operator_pay_limit.month_limit<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 每月限额
     */
    private Float monthLimit;

    /**
     * @return sdk_operator_pay_limit.id: 主键
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_operator_pay_limit.id<br/>
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
     * @return sdk_operator_pay_limit.operator_type: 运营商，1：中国移动，2：中国联通，3：中国电信
     */
    public Integer getOperatorType() {
        return operatorType;
    }

    /**<br/>
     * 字段: sdk_operator_pay_limit.operator_type<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 3<br/>
     * @param operatorType: 运营商，1：中国移动，2：中国联通，3：中国电信
     */
    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    /**
     * @return sdk_operator_pay_limit.day_limit: 每日限额
     */
    public Float getDayLimit() {
        return dayLimit;
    }

    /**<br/>
     * 字段: sdk_operator_pay_limit.day_limit<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param dayLimit: 每日限额
     */
    public void setDayLimit(Float dayLimit) {
        this.dayLimit = dayLimit;
    }

    /**
     * @return sdk_operator_pay_limit.month_limit: 每月限额
     */
    public Float getMonthLimit() {
        return monthLimit;
    }

    /**<br/>
     * 字段: sdk_operator_pay_limit.month_limit<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param monthLimit: 每月限额
     */
    public void setMonthLimit(Float monthLimit) {
        this.monthLimit = monthLimit;
    }
}