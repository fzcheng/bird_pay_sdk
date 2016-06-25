package com.cheyooh.service.sdk.db.entity;

public class SdkGiftVcodeKey {
    /**<br/>
     * 字段: sdk_gift_vcode.gift_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 礼包ID
     */
    private Integer giftId;

    /**<br/>
     * 字段: sdk_gift_vcode.vcode<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 验证码
     */
    private String vcode;

    /**
     * @return sdk_gift_vcode.gift_id: 礼包ID
     */
    public Integer getGiftId() {
        return giftId;
    }

    /**<br/>
     * 字段: sdk_gift_vcode.gift_id<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param giftId: 礼包ID
     */
    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }

    /**
     * @return sdk_gift_vcode.vcode: 验证码
     */
    public String getVcode() {
        return vcode;
    }

    /**<br/>
     * 字段: sdk_gift_vcode.vcode<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param vcode: 验证码
     */
    public void setVcode(String vcode) {
        this.vcode = vcode;
    }
}