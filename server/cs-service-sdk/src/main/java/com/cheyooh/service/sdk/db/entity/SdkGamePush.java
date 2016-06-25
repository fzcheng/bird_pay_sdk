package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class SdkGamePush {
    /**<br/>
     * 字段: sdk_game_push.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_game_push.content_type<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     */
    private String contentType;

    /**<br/>
     * 字段: sdk_game_push.title<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 
     */
    private String title;

    /**<br/>
     * 字段: sdk_game_push.msg_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 
     */
    private String msgId;

    /**<br/>
     * 字段: sdk_game_push.msg_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 
     */
    private Date msgTime;

    /**<br/>
     * 字段: sdk_game_push.url<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 
     */
    private String url;

    /**<br/>
     * 字段: sdk_game_push.message<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 65535<br/>
     * 说明: 
     */
    private String message;

    /**
     * @return sdk_game_push.id: 
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_game_push.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param id: 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return sdk_game_push.content_type: 
     */
    public String getContentType() {
        return contentType;
    }

    /**<br/>
     * 字段: sdk_game_push.content_type<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param contentType: 
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * @return sdk_game_push.title: 
     */
    public String getTitle() {
        return title;
    }

    /**<br/>
     * 字段: sdk_game_push.title<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * @param title: 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return sdk_game_push.msg_id: 
     */
    public String getMsgId() {
        return msgId;
    }

    /**<br/>
     * 字段: sdk_game_push.msg_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * @param msgId: 
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    /**
     * @return sdk_game_push.msg_time: 
     */
    public Date getMsgTime() {
        return msgTime;
    }

    /**<br/>
     * 字段: sdk_game_push.msg_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param msgTime: 
     */
    public void setMsgTime(Date msgTime) {
        this.msgTime = msgTime;
    }

    /**
     * @return sdk_game_push.url: 
     */
    public String getUrl() {
        return url;
    }

    /**<br/>
     * 字段: sdk_game_push.url<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * @param url: 
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return sdk_game_push.message: 
     */
    public String getMessage() {
        return message;
    }

    /**<br/>
     * 字段: sdk_game_push.message<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 65535<br/>
     * @param message: 
     */
    public void setMessage(String message) {
        this.message = message;
    }
}