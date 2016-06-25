package com.cheyooh.service.sdk.db.entity;


public class SdkMmdoSetting {
    /**<br/>
     * 字段: sdk_mmdo_setting.id<br/>
     * 主键: 自动增长<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 主键
     */
    @com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey
    private Integer id;

    /**<br/>
     * 字段: sdk_mmdo_setting.number<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 发送端口
     */
    private String number;

    /**<br/>
     * 字段: sdk_mmdo_setting.content<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 发送内容前缀,实际内容添加game_id
     */
    private String content;

    /**<br/>
     * 字段: sdk_mmdo_setting.amount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     */
    private Float amount;

    /**<br/>
     * 字段: sdk_mmdo_setting.shield_keyword<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 屏蔽关键字
     */
    private String shieldKeyword;

    /**<br/>
     * 字段: sdk_mmdo_setting.shield_number<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 屏蔽端口
     */
    private String shieldNumber;

    /**<br/>
     * 字段: sdk_mmdo_setting.interval<br/>
     * 可空: true<br/>
     * 缺省: 5<br/>
     * 长度: 10<br/>
     * 说明: 发送间隔
     */
    private Integer interval;

    /**<br/>
     * 字段: sdk_mmdo_setting.operation_type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 运营商类型（1、移动，2、联通，3、电信）
     */
    private Integer operationType;

    /**<br/>
     * 字段: sdk_mmdo_setting.idx<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 优先级
     */
    private Integer idx;

    /**<br/>
     * 字段: sdk_mmdo_setting.sdk_version<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 15<br/>
     * 说明: sdk支持最低版本
     */
    private String sdkVersion;

    /**<br/>
     * 字段: sdk_mmdo_setting.date_limit<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * 说明: 每天的限制金额
     */
    private Float dateLimit;

    /**<br/>
     * 字段: sdk_mmdo_setting.month_limit<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * 说明: 月限制金额
     */
    private Float monthLimit;

    /**<br/>
     * 字段: sdk_mmdo_setting.game_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 游戏id
     */
    private Integer gameId;

    /**<br/>
     * 字段: sdk_mmdo_setting.operator_pay_channel_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 运营商支付渠道配置id
     */
    private Integer operatorPayChannelId;

    /**<br/>
     * 字段: sdk_mmdo_setting.use_status<br/>
     * 可空: true<br/>
     * 缺省: 1<br/>
     * 长度: 3<br/>
     * 说明: 使用状态，0：停用；1：使用
     */
    private Byte useStatus;

    /**<br/>
     * 字段: sdk_mmdo_setting.chargetip<br/>
     * 可空: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 计费提示框弹出状态:1 弹出，0 不弹出
     */
    private Integer chargetip;

    /**<br/>
     * 字段: sdk_mmdo_setting.chargesuceesstip<br/>
     * 可空: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 计费成功提示框弹出状态:1 弹出，0 不弹出
     */
    private Integer chargesuceesstip;

    /**<br/>
     * 字段: sdk_mmdo_setting.original_game_id<br/>
     * 可空: true<br/>
     * 缺省: 100<br/>
     * 长度: 10<br/>
     * 说明: 最初游戏ID
     */
    private Integer originalGameId;

    /**<br/>
     * 字段: sdk_mmdo_setting.chargetip_periods<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 计费提示框打开时段列表
     */
    private String chargetipPeriods;

    /**<br/>
     * 字段: sdk_mmdo_setting.chargesuceesstip_periods<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 计费成功提示框打开时段列表
     */
    private String chargesuceesstipPeriods;

    /**<br/>
     * 字段: sdk_mmdo_setting.original_game_name<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 代计费原游戏名
     */
    private String originalGameName;

    /**<br/>
     * 字段: sdk_mmdo_setting.propsid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 道具ID
     */
    private String propsid;

    /**<br/>
     * 字段: sdk_mmdo_setting.additional<br/>
     * 可空: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 是否补点计费,0-不需要,1-需要
     */
    private Integer additional;

    /**<br/>
     * 字段: sdk_mmdo_setting.add_list<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 补点计费对应的序号
     */
    private String addList;

    /**<br/>
     * 字段: sdk_mmdo_setting.real_amount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * 说明: 实际发送金额
     */
    private Float realAmount;

    /**<br/>
     * 字段: sdk_mmdo_setting.sendingtip<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 支付中提示字符
     */
    private String sendingtip;

    /**<br/>
     * 字段: sdk_mmdo_setting.tjpropsname<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 统计的道具名称
     */
    private String tjpropsname;

    /**<br/>
     * 字段: sdk_mmdo_setting.loadingtipmin<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 支付loading提示框弹出时间秒数
     */
    private Integer loadingtipmin;

    /**<br/>
     * 字段: sdk_mmdo_setting.chargefailtip<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 支付失败提示框弹出状态:1-弹出,0-不弹出
     */
    private Integer chargefailtip;

    /**
     * @return sdk_mmdo_setting.id: 主键
     */
    public Integer getId() {
        return id;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.id<br/>
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
     * @return sdk_mmdo_setting.number: 发送端口
     */
    public String getNumber() {
        return number;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.number<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param number: 发送端口
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return sdk_mmdo_setting.content: 发送内容前缀,实际内容添加game_id
     */
    public String getContent() {
        return content;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.content<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param content: 发送内容前缀,实际内容添加game_id
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return sdk_mmdo_setting.amount: 
     */
    public Float getAmount() {
        return amount;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.amount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param amount: 
     */
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    /**
     * @return sdk_mmdo_setting.shield_keyword: 屏蔽关键字
     */
    public String getShieldKeyword() {
        return shieldKeyword;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.shield_keyword<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * @param shieldKeyword: 屏蔽关键字
     */
    public void setShieldKeyword(String shieldKeyword) {
        this.shieldKeyword = shieldKeyword;
    }

    /**
     * @return sdk_mmdo_setting.shield_number: 屏蔽端口
     */
    public String getShieldNumber() {
        return shieldNumber;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.shield_number<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * @param shieldNumber: 屏蔽端口
     */
    public void setShieldNumber(String shieldNumber) {
        this.shieldNumber = shieldNumber;
    }

    /**
     * @return sdk_mmdo_setting.interval: 发送间隔
     */
    public Integer getInterval() {
        return interval;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.interval<br/>
     * 可空: true<br/>
     * 缺省: 5<br/>
     * 长度: 10<br/>
     * @param interval: 发送间隔
     */
    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    /**
     * @return sdk_mmdo_setting.operation_type: 运营商类型（1、移动，2、联通，3、电信）
     */
    public Integer getOperationType() {
        return operationType;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.operation_type<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param operationType: 运营商类型（1、移动，2、联通，3、电信）
     */
    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    /**
     * @return sdk_mmdo_setting.idx: 优先级
     */
    public Integer getIdx() {
        return idx;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.idx<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param idx: 优先级
     */
    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    /**
     * @return sdk_mmdo_setting.sdk_version: sdk支持最低版本
     */
    public String getSdkVersion() {
        return sdkVersion;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.sdk_version<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 15<br/>
     * @param sdkVersion: sdk支持最低版本
     */
    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    /**
     * @return sdk_mmdo_setting.date_limit: 每天的限制金额
     */
    public Float getDateLimit() {
        return dateLimit;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.date_limit<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * @param dateLimit: 每天的限制金额
     */
    public void setDateLimit(Float dateLimit) {
        this.dateLimit = dateLimit;
    }

    /**
     * @return sdk_mmdo_setting.month_limit: 月限制金额
     */
    public Float getMonthLimit() {
        return monthLimit;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.month_limit<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * @param monthLimit: 月限制金额
     */
    public void setMonthLimit(Float monthLimit) {
        this.monthLimit = monthLimit;
    }

    /**
     * @return sdk_mmdo_setting.game_id: 游戏id
     */
    public Integer getGameId() {
        return gameId;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.game_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param gameId: 游戏id
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return sdk_mmdo_setting.operator_pay_channel_id: 运营商支付渠道配置id
     */
    public Integer getOperatorPayChannelId() {
        return operatorPayChannelId;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.operator_pay_channel_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param operatorPayChannelId: 运营商支付渠道配置id
     */
    public void setOperatorPayChannelId(Integer operatorPayChannelId) {
        this.operatorPayChannelId = operatorPayChannelId;
    }

    /**
     * @return sdk_mmdo_setting.use_status: 使用状态，0：停用；1：使用
     */
    public Byte getUseStatus() {
        return useStatus;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.use_status<br/>
     * 可空: true<br/>
     * 缺省: 1<br/>
     * 长度: 3<br/>
     * @param useStatus: 使用状态，0：停用；1：使用
     */
    public void setUseStatus(Byte useStatus) {
        this.useStatus = useStatus;
    }

    /**
     * @return sdk_mmdo_setting.chargetip: 计费提示框弹出状态:1 弹出，0 不弹出
     */
    public Integer getChargetip() {
        return chargetip;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.chargetip<br/>
     * 可空: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * @param chargetip: 计费提示框弹出状态:1 弹出，0 不弹出
     */
    public void setChargetip(Integer chargetip) {
        this.chargetip = chargetip;
    }

    /**
     * @return sdk_mmdo_setting.chargesuceesstip: 计费成功提示框弹出状态:1 弹出，0 不弹出
     */
    public Integer getChargesuceesstip() {
        return chargesuceesstip;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.chargesuceesstip<br/>
     * 可空: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * @param chargesuceesstip: 计费成功提示框弹出状态:1 弹出，0 不弹出
     */
    public void setChargesuceesstip(Integer chargesuceesstip) {
        this.chargesuceesstip = chargesuceesstip;
    }

    /**
     * @return sdk_mmdo_setting.original_game_id: 最初游戏ID
     */
    public Integer getOriginalGameId() {
        return originalGameId;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.original_game_id<br/>
     * 可空: true<br/>
     * 缺省: 100<br/>
     * 长度: 10<br/>
     * @param originalGameId: 最初游戏ID
     */
    public void setOriginalGameId(Integer originalGameId) {
        this.originalGameId = originalGameId;
    }

    /**
     * @return sdk_mmdo_setting.chargetip_periods: 计费提示框打开时段列表
     */
    public String getChargetipPeriods() {
        return chargetipPeriods;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.chargetip_periods<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param chargetipPeriods: 计费提示框打开时段列表
     */
    public void setChargetipPeriods(String chargetipPeriods) {
        this.chargetipPeriods = chargetipPeriods;
    }

    /**
     * @return sdk_mmdo_setting.chargesuceesstip_periods: 计费成功提示框打开时段列表
     */
    public String getChargesuceesstipPeriods() {
        return chargesuceesstipPeriods;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.chargesuceesstip_periods<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * @param chargesuceesstipPeriods: 计费成功提示框打开时段列表
     */
    public void setChargesuceesstipPeriods(String chargesuceesstipPeriods) {
        this.chargesuceesstipPeriods = chargesuceesstipPeriods;
    }

    /**
     * @return sdk_mmdo_setting.original_game_name: 代计费原游戏名
     */
    public String getOriginalGameName() {
        return originalGameName;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.original_game_name<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param originalGameName: 代计费原游戏名
     */
    public void setOriginalGameName(String originalGameName) {
        this.originalGameName = originalGameName;
    }

    /**
     * @return sdk_mmdo_setting.propsid: 道具ID
     */
    public String getPropsid() {
        return propsid;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.propsid<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param propsid: 道具ID
     */
    public void setPropsid(String propsid) {
        this.propsid = propsid;
    }

    /**
     * @return sdk_mmdo_setting.additional: 是否补点计费,0-不需要,1-需要
     */
    public Integer getAdditional() {
        return additional;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.additional<br/>
     * 可空: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * @param additional: 是否补点计费,0-不需要,1-需要
     */
    public void setAdditional(Integer additional) {
        this.additional = additional;
    }

    /**
     * @return sdk_mmdo_setting.add_list: 补点计费对应的序号
     */
    public String getAddList() {
        return addList;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.add_list<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * @param addList: 补点计费对应的序号
     */
    public void setAddList(String addList) {
        this.addList = addList;
    }

    /**
     * @return sdk_mmdo_setting.real_amount: 实际发送金额
     */
    public Float getRealAmount() {
        return realAmount;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.real_amount<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * @param realAmount: 实际发送金额
     */
    public void setRealAmount(Float realAmount) {
        this.realAmount = realAmount;
    }

    /**
     * @return sdk_mmdo_setting.sendingtip: 支付中提示字符
     */
    public String getSendingtip() {
        return sendingtip;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.sendingtip<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param sendingtip: 支付中提示字符
     */
    public void setSendingtip(String sendingtip) {
        this.sendingtip = sendingtip;
    }

    /**
     * @return sdk_mmdo_setting.tjpropsname: 统计的道具名称
     */
    public String getTjpropsname() {
        return tjpropsname;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.tjpropsname<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * @param tjpropsname: 统计的道具名称
     */
    public void setTjpropsname(String tjpropsname) {
        this.tjpropsname = tjpropsname;
    }

    /**
     * @return sdk_mmdo_setting.loadingtipmin: 支付loading提示框弹出时间秒数
     */
    public Integer getLoadingtipmin() {
        return loadingtipmin;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.loadingtipmin<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param loadingtipmin: 支付loading提示框弹出时间秒数
     */
    public void setLoadingtipmin(Integer loadingtipmin) {
        this.loadingtipmin = loadingtipmin;
    }

    /**
     * @return sdk_mmdo_setting.chargefailtip: 支付失败提示框弹出状态:1-弹出,0-不弹出
     */
    public Integer getChargefailtip() {
        return chargefailtip;
    }

    /**<br/>
     * 字段: sdk_mmdo_setting.chargefailtip<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * @param chargefailtip: 支付失败提示框弹出状态:1-弹出,0-不弹出
     */
    public void setChargefailtip(Integer chargefailtip) {
        this.chargefailtip = chargefailtip;
    }
}