package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkGiftVcode extends SdkGiftVcodeKey {
    /**<br/>
     * 字段: sdk_gift_vcode.uid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 抽中用户的ID
     */
    private Integer uid;

    /**<br/>
     * 字段: sdk_gift_vcode.used_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 抽中的时间
     */
    private Date usedTime;

    /**
     * @return sdk_gift_vcode.uid: 抽中用户的ID
     */
    public Integer getUid() {
        return uid;
    }

    /**<br/>
     * 字段: sdk_gift_vcode.uid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param uid: 抽中用户的ID
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * @return sdk_gift_vcode.used_time: 抽中的时间
     */
    public Date getUsedTime() {
        return usedTime;
    }

    /**<br/>
     * 字段: sdk_gift_vcode.used_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param usedTime: 抽中的时间
     */
    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }
}