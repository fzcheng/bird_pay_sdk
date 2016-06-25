package com.cheyooh.service.sdk.db.entity;

import java.util.Date;

public class GmiPkg {
    /**<br/>
     * 字段: gmi_pkg.pkg<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 包名
     */
    private String pkg;

    /**<br/>
     * 字段: gmi_pkg.name<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 名称
     */
    private String name;

    /**<br/>
     * 字段: gmi_pkg.ename<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 英文名
     */
    private String ename;

    /**<br/>
     * 字段: gmi_pkg.version<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 版本号(1.0.0)
     */
    private String version;

    /**<br/>
     * 字段: gmi_pkg.summary<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 2048<br/>
     * 说明: 简介
     */
    private String summary;

    /**<br/>
     * 字段: gmi_pkg.comments<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 简评
     */
    private String comments;

    /**<br/>
     * 字段: gmi_pkg.publish_date<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 发布时间
     */
    private Date publishDate;

    /**<br/>
     * 字段: gmi_pkg.publisher<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 发布者
     */
    private String publisher;

    /**<br/>
     * 字段: gmi_pkg.language<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 语言
     */
    private String language;

    /**<br/>
     * 字段: gmi_pkg.fee<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 资费(0-免费)
     */
    private Float fee;

    /**<br/>
     * 字段: gmi_pkg.size<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 包大小( 如: 1.7M)
     */
    private String size;

    /**<br/>
     * 字段: gmi_pkg.category<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 游戏类别
     */
    private String category;

    /**<br/>
     * 字段: gmi_pkg.sub_category<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 子分类(多个间逗号分开)
     */
    private String subCategory;

    /**<br/>
     * 字段: gmi_pkg.from_url<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 512<br/>
     * 说明: 源地址
     */
    private String fromUrl;

    /**<br/>
     * 字段: gmi_pkg.source<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 数据来源(如: play.google.com)
     */
    private String source;

    /**<br/>
     * 字段: gmi_pkg.min_sdk<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 支持的最小SDK版本(如: 2.1)
     */
    private String minSdk;

    /**<br/>
     * 字段: gmi_pkg.screen<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 支持屏幕大小(小 中 大 超大)
     */
    private String screen;

    /**<br/>
     * 字段: gmi_pkg.star<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 星级(1-10)
     */
    private Integer star;

    /**<br/>
     * 字段: gmi_pkg.hot<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 热度(0-100)
     */
    private Integer hot;

    /**<br/>
     * 字段: gmi_pkg.down_url<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 下载URL
     */
    private String downUrl;

    /**<br/>
     * 字段: gmi_pkg.idx<br/>
     * 可空: false<br/>
     * 缺省: 10000<br/>
     * 说明: 默认排序值
     */
    private Integer idx;

    /**<br/>
     * 字段: gmi_pkg.img_small_wh<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 小图尺寸(wXh) 如: 320X480
     */
    private String imgSmallWh;

    /**<br/>
     * 字段: gmi_pkg.img_large_wh<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 大图尺寸(wXh) 如: 320X480
     */
    private String imgLargeWh;

    /**<br/>
     * 字段: gmi_pkg.create_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 创建时间
     */
    private Date createTime;

    /**<br/>
     * 字段: gmi_pkg.status<br/>
     * 可空: false<br/>
     * 缺省: 0<br/>
     * 说明: 记录状态(0-缺省, 1-正常)
     */
    private Integer status;

    /**<br/>
     * 字段: gmi_pkg.icon<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16777215<br/>
     * 说明: 图标
     */
    private byte[] icon;

    /**
     * @return gmi_pkg.pkg: 包名
     */
    public String getPkg() {
        return pkg;
    }

    /**<br/>
     * 字段: gmi_pkg.pkg<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * @param pkg: 包名
     */
    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    /**
     * @return gmi_pkg.name: 名称
     */
    public String getName() {
        return name;
    }

    /**<br/>
     * 字段: gmi_pkg.name<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * @param name: 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return gmi_pkg.ename: 英文名
     */
    public String getEname() {
        return ename;
    }

    /**<br/>
     * 字段: gmi_pkg.ename<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * @param ename: 英文名
     */
    public void setEname(String ename) {
        this.ename = ename;
    }

    /**
     * @return gmi_pkg.version: 版本号(1.0.0)
     */
    public String getVersion() {
        return version;
    }

    /**<br/>
     * 字段: gmi_pkg.version<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param version: 版本号(1.0.0)
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return gmi_pkg.summary: 简介
     */
    public String getSummary() {
        return summary;
    }

    /**<br/>
     * 字段: gmi_pkg.summary<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 2048<br/>
     * @param summary: 简介
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return gmi_pkg.comments: 简评
     */
    public String getComments() {
        return comments;
    }

    /**<br/>
     * 字段: gmi_pkg.comments<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param comments: 简评
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return gmi_pkg.publish_date: 发布时间
     */
    public Date getPublishDate() {
        return publishDate;
    }

    /**<br/>
     * 字段: gmi_pkg.publish_date<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param publishDate: 发布时间
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * @return gmi_pkg.publisher: 发布者
     */
    public String getPublisher() {
        return publisher;
    }

    /**<br/>
     * 字段: gmi_pkg.publisher<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * @param publisher: 发布者
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * @return gmi_pkg.language: 语言
     */
    public String getLanguage() {
        return language;
    }

    /**<br/>
     * 字段: gmi_pkg.language<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * @param language: 语言
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return gmi_pkg.fee: 资费(0-免费)
     */
    public Float getFee() {
        return fee;
    }

    /**<br/>
     * 字段: gmi_pkg.fee<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param fee: 资费(0-免费)
     */
    public void setFee(Float fee) {
        this.fee = fee;
    }

    /**
     * @return gmi_pkg.size: 包大小( 如: 1.7M)
     */
    public String getSize() {
        return size;
    }

    /**<br/>
     * 字段: gmi_pkg.size<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * @param size: 包大小( 如: 1.7M)
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * @return gmi_pkg.category: 游戏类别
     */
    public String getCategory() {
        return category;
    }

    /**<br/>
     * 字段: gmi_pkg.category<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * @param category: 游戏类别
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return gmi_pkg.sub_category: 子分类(多个间逗号分开)
     */
    public String getSubCategory() {
        return subCategory;
    }

    /**<br/>
     * 字段: gmi_pkg.sub_category<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * @param subCategory: 子分类(多个间逗号分开)
     */
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    /**
     * @return gmi_pkg.from_url: 源地址
     */
    public String getFromUrl() {
        return fromUrl;
    }

    /**<br/>
     * 字段: gmi_pkg.from_url<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 512<br/>
     * @param fromUrl: 源地址
     */
    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
    }

    /**
     * @return gmi_pkg.source: 数据来源(如: play.google.com)
     */
    public String getSource() {
        return source;
    }

    /**<br/>
     * 字段: gmi_pkg.source<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param source: 数据来源(如: play.google.com)
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return gmi_pkg.min_sdk: 支持的最小SDK版本(如: 2.1)
     */
    public String getMinSdk() {
        return minSdk;
    }

    /**<br/>
     * 字段: gmi_pkg.min_sdk<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * @param minSdk: 支持的最小SDK版本(如: 2.1)
     */
    public void setMinSdk(String minSdk) {
        this.minSdk = minSdk;
    }

    /**
     * @return gmi_pkg.screen: 支持屏幕大小(小 中 大 超大)
     */
    public String getScreen() {
        return screen;
    }

    /**<br/>
     * 字段: gmi_pkg.screen<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * @param screen: 支持屏幕大小(小 中 大 超大)
     */
    public void setScreen(String screen) {
        this.screen = screen;
    }

    /**
     * @return gmi_pkg.star: 星级(1-10)
     */
    public Integer getStar() {
        return star;
    }

    /**<br/>
     * 字段: gmi_pkg.star<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param star: 星级(1-10)
     */
    public void setStar(Integer star) {
        this.star = star;
    }

    /**
     * @return gmi_pkg.hot: 热度(0-100)
     */
    public Integer getHot() {
        return hot;
    }

    /**<br/>
     * 字段: gmi_pkg.hot<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param hot: 热度(0-100)
     */
    public void setHot(Integer hot) {
        this.hot = hot;
    }

    /**
     * @return gmi_pkg.down_url: 下载URL
     */
    public String getDownUrl() {
        return downUrl;
    }

    /**<br/>
     * 字段: gmi_pkg.down_url<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param downUrl: 下载URL
     */
    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    /**
     * @return gmi_pkg.idx: 默认排序值
     */
    public Integer getIdx() {
        return idx;
    }

    /**<br/>
     * 字段: gmi_pkg.idx<br/>
     * 可空: false<br/>
     * 缺省: 10000<br/>
     * @param idx: 默认排序值
     */
    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    /**
     * @return gmi_pkg.img_small_wh: 小图尺寸(wXh) 如: 320X480
     */
    public String getImgSmallWh() {
        return imgSmallWh;
    }

    /**<br/>
     * 字段: gmi_pkg.img_small_wh<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * @param imgSmallWh: 小图尺寸(wXh) 如: 320X480
     */
    public void setImgSmallWh(String imgSmallWh) {
        this.imgSmallWh = imgSmallWh;
    }

    /**
     * @return gmi_pkg.img_large_wh: 大图尺寸(wXh) 如: 320X480
     */
    public String getImgLargeWh() {
        return imgLargeWh;
    }

    /**<br/>
     * 字段: gmi_pkg.img_large_wh<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * @param imgLargeWh: 大图尺寸(wXh) 如: 320X480
     */
    public void setImgLargeWh(String imgLargeWh) {
        this.imgLargeWh = imgLargeWh;
    }

    /**
     * @return gmi_pkg.create_time: 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**<br/>
     * 字段: gmi_pkg.create_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param createTime: 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return gmi_pkg.status: 记录状态(0-缺省, 1-正常)
     */
    public Integer getStatus() {
        return status;
    }

    /**<br/>
     * 字段: gmi_pkg.status<br/>
     * 可空: false<br/>
     * 缺省: 0<br/>
     * @param status: 记录状态(0-缺省, 1-正常)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return gmi_pkg.icon: 图标
     */
    public byte[] getIcon() {
        return icon;
    }

    /**<br/>
     * 字段: gmi_pkg.icon<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 16777215<br/>
     * @param icon: 图标
     */
    public void setIcon(byte[] icon) {
        this.icon = icon;
    }
}