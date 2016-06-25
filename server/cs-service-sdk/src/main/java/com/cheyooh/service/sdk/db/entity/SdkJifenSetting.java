package com.cheyooh.service.sdk.db.entity;

public class SdkJifenSetting {
    /**<br/>
     * 字段: sdk_jifen_setting.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 主键
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_jifen_setting.number<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 发送端口
     */
    private String number;

    /**<br/>
     * 字段: sdk_jifen_setting.content<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 发送内容
     */
    private String content;

    /**<br/>
     * 字段: sdk_jifen_setting.jifen<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * 说明: 对应的积分
     */
    private Float jifen;

    /**<br/>
     * 字段: sdk_jifen_setting.amount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * 说明: 发送金额
     */
    private Float amount;

    /**<br/>
     * 字段: sdk_jifen_setting.shield_keyword<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 屏蔽关键字
     */
    private String shieldKeyword;

    /**<br/>
     * 字段: sdk_jifen_setting.shield_number<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 屏蔽端口
     */
    private String shieldNumber;

    /**<br/>
     * 字段: sdk_jifen_setting.interval<br/>
     * 可空: true<br/>
     * 缺省: 5<br/>
     * 长度: 10<br/>
     * 说明: 发送间隔
     */
    private Integer interval;

    /**<br/>
     * 字段: sdk_jifen_setting.use_status<br/>
     * 可空: true<br/>
     * 缺省: 1<br/>
     * 长度: 10<br/>
     * 说明: 使用状态，0：停用；1：使用
     */
    private Integer useStatus;

    /**<br/>
     * 字段: sdk_jifen_setting.real_amount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * 说明: 实际发送金额
     */
    private Float realAmount;

    /**
     * @return sdk_jifen_setting.id: 主键
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_jifen_setting.id<br/>
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
     * @return sdk_jifen_setting.number: 发送端口
     */
    public String getNumber() {
        return number;
    }

    /**<br/>
     * 字段: sdk_jifen_setting.number<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param number: 发送端口
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return sdk_jifen_setting.content: 发送内容
     */
    public String getContent() {
        return content;
    }

    /**<br/>
     * 字段: sdk_jifen_setting.content<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param content: 发送内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return sdk_jifen_setting.jifen: 对应的积分
     */
    public Float getJifen() {
        return jifen;
    }

    /**<br/>
     * 字段: sdk_jifen_setting.jifen<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * @param jifen: 对应的积分
     */
    public void setJifen(Float jifen) {
        this.jifen = jifen;
    }

    /**
     * @return sdk_jifen_setting.amount: 发送金额
     */
    public Float getAmount() {
        return amount;
    }

    /**<br/>
     * 字段: sdk_jifen_setting.amount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * @param amount: 发送金额
     */
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    /**
     * @return sdk_jifen_setting.shield_keyword: 屏蔽关键字
     */
    public String getShieldKeyword() {
        return shieldKeyword;
    }

    /**<br/>
     * 字段: sdk_jifen_setting.shield_keyword<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * @param shieldKeyword: 屏蔽关键字
     */
    public void setShieldKeyword(String shieldKeyword) {
        this.shieldKeyword = shieldKeyword;
    }

    /**
     * @return sdk_jifen_setting.shield_number: 屏蔽端口
     */
    public String getShieldNumber() {
        return shieldNumber;
    }

    /**<br/>
     * 字段: sdk_jifen_setting.shield_number<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * @param shieldNumber: 屏蔽端口
     */
    public void setShieldNumber(String shieldNumber) {
        this.shieldNumber = shieldNumber;
    }

    /**
     * @return sdk_jifen_setting.interval: 发送间隔
     */
    public Integer getInterval() {
        return interval;
    }

    /**<br/>
     * 字段: sdk_jifen_setting.interval<br/>
     * 可空: true<br/>
     * 缺省: 5<br/>
     * 长度: 10<br/>
     * @param interval: 发送间隔
     */
    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    /**
     * @return sdk_jifen_setting.use_status: 使用状态，0：停用；1：使用
     */
    public Integer getUseStatus() {
        return useStatus;
    }

    /**<br/>
     * 字段: sdk_jifen_setting.use_status<br/>
     * 可空: true<br/>
     * 缺省: 1<br/>
     * 长度: 10<br/>
     * @param useStatus: 使用状态，0：停用；1：使用
     */
    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    /**
     * @return sdk_jifen_setting.real_amount: 实际发送金额
     */
    public Float getRealAmount() {
        return realAmount;
    }

    /**<br/>
     * 字段: sdk_jifen_setting.real_amount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * @param realAmount: 实际发送金额
     */
    public void setRealAmount(Float realAmount) {
        this.realAmount = realAmount;
    }
}